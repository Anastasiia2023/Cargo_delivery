package ua.cargo.actions.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.DuplicateTitleException;
import ua.cargo.exceptions.IncorrectFormatException;
import ua.cargo.exceptions.PasswordMatchingException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.InvoiceService;
import ua.cargo.services.OrderService;
import ua.cargo.utils.ActionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static ua.cargo.actions.constants.Pages.MY_ORDER_PAGE;
import static ua.cargo.actions.constants.Parameters.ORDER_ID;
import static ua.cargo.actions.constants.Parameters.USER_ID;
import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.FilterUtil.getOrderFilterQueryString;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class PayAction implements Action {
    private final OrderService orderService;

    public PayAction(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            orderService.confirmPayment(Long.parseLong(request.getParameter(ORDER_ID)));
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MANAGE_ORDER_ACTION);
    }

    private String executePost(HttpServletRequest request) {
        try {
            orderService.completePayment(Long.parseLong(request.getParameter(ORDER_ID)));
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MY_ORDER_ACTION);
    }

}
