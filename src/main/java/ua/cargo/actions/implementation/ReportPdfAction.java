package ua.cargo.actions.implementation;


import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.OrderDTO;
import ua.cargo.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cargo.services.OrderService;
import ua.cargo.services.impl.PdfService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.FilterUtil.getOrderFilterQueryString;

public class ReportPdfAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(ReportPdfAction.class);

    private final OrderService orderService;
    private final PdfService pdfService;

    public ReportPdfAction(OrderService orderService, PdfService pdfService) {
        this.orderService = orderService;
        this.pdfService = pdfService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String filterQuery = getOrderFilterQueryString(request);
        try {
            List<OrderDTO> orders = orderService.getAll(filterQuery);
            String locale = (String) request.getSession().getAttribute(Parameters.LOCALE);
            ByteArrayOutputStream usersPdf = pdfService.createOrdersPdf(request.getServletContext(), orders, locale);
            setResponse(response, usersPdf);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MANAGE_ORDER_ACTION);
    }

    private void setResponse(HttpServletResponse response, ByteArrayOutputStream output) {
        response.setContentType("application/pdf");
        response.setContentLength(output.size());
        response.setHeader("Content-Disposition", "attachment; filename=\"orders.pdf\"");
        try (OutputStream outputStream = response.getOutputStream()) {
            output.writeTo(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}