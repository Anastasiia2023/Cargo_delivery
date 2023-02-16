package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.DuplicateTitleException;
import ua.cargo.exceptions.IncorrectFormatException;
import ua.cargo.exceptions.PasswordMatchingException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ua.cargo.utils.ActionUtil.getActionToRedirect;
import static ua.cargo.utils.ActionUtil.isPostMethod;
import static ua.cargo.actions.constants.ActionNames.*;
import static ua.cargo.actions.constants.Pages.*;
import static ua.cargo.actions.constants.Parameters.*;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class CreateRouteAction implements Action {
    private final RouteService routeService;
    private final CityService cityService;

    public CreateRouteAction(RouteService routeService, CityService cityService) {
        this.routeService = routeService;
        this.cityService = cityService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        Pagination pagination = getPaginationData(request);
        try {
            List<CityDTO> listCity = cityService.getAll();
            request.setAttribute("listCity", listCity);
            request.setAttribute("listRoute", routeService.getAll(pagination));
            request.setAttribute(Parameters.TOTAL_RECORDS, routeService.getNumberOfRecords());
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        paginate(request);
        return CREATE_ROUTE_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        RouteDTO route = getRouteDTO(request);
        try {
            routeService.add(route);
        } catch (IncorrectFormatException | PasswordMatchingException | DuplicateTitleException e) {
            request.getSession().setAttribute(ROUTE, route);
            request.getSession().setAttribute(ERROR, e.getMessage());
        }
        return getActionToRedirect(CREATE_ROUTE_ACTION);
    }

    private RouteDTO getRouteDTO(HttpServletRequest request) {
        return new RouteDTO(Long.parseLong(request.getParameter(CITY_FROM_ID)), Long.parseLong(request.getParameter(CITY_TO_ID)), Integer.parseInt(request.getParameter(DISTANCE)), Integer.parseInt(request.getParameter(TERMS)));
    }
}
