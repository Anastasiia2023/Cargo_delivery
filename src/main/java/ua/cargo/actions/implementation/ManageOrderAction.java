package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.OrderService;
import ua.cargo.utils.ActionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.FilterUtil.getOrderFilterQueryString;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class ManageOrderAction implements Action {
    private final OrderService orderService;
    private final CityService cityService;

    public ManageOrderAction(OrderService orderService, CityService cityService) {
        this.orderService = orderService;
        this.cityService = cityService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        List<OrderDTO> result = new ArrayList<>();
        Pagination pagination = getPaginationData(request);
        String filterQuery = getOrderFilterQueryString(request);
        try {
            result.addAll(orderService.getAll(pagination, filterQuery));
            request.setAttribute(Parameters.TOTAL_RECORDS, orderService.getNumberOfRecords(filterQuery));
            String cityFrom = request.getParameter(Parameters.CITY_FROM_ID);
            request.setAttribute(Parameters.CITY_FROM, cityFrom == null ? null : cityService.getById(Long.parseLong(cityFrom)));
            String cityTo = request.getParameter(Parameters.CITY_TO_ID);
            request.setAttribute(Parameters.CITY_TO, cityTo == null ? null : cityService.getById(Long.parseLong(cityTo)));
            List<CityDTO> listCity = cityService.getAll();
            request.setAttribute(Parameters.LIST_CITY, listCity);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        setOrderAttributes(request, result);
        transferStringFromSessionToRequest(request, Parameters.OFFSET);
        transferStringFromSessionToRequest(request, Parameters.RECORDS);
        paginate(request);
        return Pages.REPORTS_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        return ActionUtil.getActionToRedirect(ActionNames.MANAGE_ORDER_ACTION, getParameterData(request));
    }

}