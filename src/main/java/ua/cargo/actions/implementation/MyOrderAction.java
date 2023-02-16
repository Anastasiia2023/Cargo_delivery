package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.OrderService;
import ua.cargo.utils.ActionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.actions.constants.Pages.MY_ORDER_PAGE;
import static ua.cargo.utils.FilterUtil.getOrderFilterQueryString;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class MyOrderAction implements Action {
    private final OrderService orderService;

    public MyOrderAction(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Parameters.LOGGED_USER);
        List<OrderDTO> result = new ArrayList<>();
        Pagination pagination = getPaginationData(request);
        String filterQuery = getOrderFilterQueryString(request);
        try {
            result.addAll(orderService.getAllByUser(userDTO.getId(), pagination, filterQuery));
            request.setAttribute(Parameters.TOTAL_RECORDS, orderService.getNumberOfRecordsByUserId(userDTO.getId(), filterQuery));
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        setOrderAttributes(request, result);
        transferStringFromSessionToRequest(request, Parameters.OFFSET);
        transferStringFromSessionToRequest(request, Parameters.RECORDS);
        paginate(request);
        return MY_ORDER_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        return ActionUtil.getActionToRedirect(ActionNames.MY_ORDER_ACTION, getParameterData(request));
    }

}