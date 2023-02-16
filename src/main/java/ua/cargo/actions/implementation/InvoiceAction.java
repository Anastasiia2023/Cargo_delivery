package ua.cargo.actions.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.DuplicateTitleException;
import ua.cargo.exceptions.IncorrectFormatException;
import ua.cargo.exceptions.PasswordMatchingException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.InvoiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static ua.cargo.actions.constants.Parameters.ORDER_ID;
import static ua.cargo.actions.constants.Parameters.USER_ID;
import static ua.cargo.utils.ActionUtil.getActionToRedirect;
import static ua.cargo.utils.ActionUtil.isPostMethod;

public class InvoiceAction implements Action {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceAction.class);
    private final InvoiceService invoiceService;

    public InvoiceAction(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Parameters.LOGGED_USER);
            InvoiceDTO invoiceDTO = getInvoiceDTO(request);
            ByteArrayOutputStream usersPdf;
            if (userDTO.getRole() != Role.MANAGER.getValue()) {
                usersPdf = invoiceService.getPDFInvoice(invoiceDTO, userDTO);
            } else {
                usersPdf = invoiceService.getPDFInvoice(invoiceDTO);
            }
            setResponse(response, usersPdf);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MANAGE_ORDER_ACTION);
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        InvoiceDTO invoiceDTO = getInvoiceDTO(request);
        try {
            String locale = (String) request.getSession().getAttribute(Parameters.LOCALE);
            invoiceService.add(invoiceDTO, locale);
        } catch (IncorrectFormatException | PasswordMatchingException | DuplicateTitleException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MANAGE_ORDER_ACTION);
    }

    private InvoiceDTO getInvoiceDTO(HttpServletRequest request) {
        return new InvoiceDTO(Long.parseLong(request.getParameter(ORDER_ID)),
                Long.parseLong(request.getParameter(USER_ID)));
    }

    private void setResponse(HttpServletResponse response, ByteArrayOutputStream output) {
        response.setContentType("application/pdf");
        response.setContentLength(output.size());
        response.setHeader("Content-Disposition", "attachment; filename=\"invoice.pdf\"");
        try (OutputStream outputStream = response.getOutputStream()) {
            output.writeTo(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
