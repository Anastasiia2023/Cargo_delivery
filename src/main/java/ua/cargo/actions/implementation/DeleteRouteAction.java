package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.RouteDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.cargo.utils.ActionUtil.getActionToRedirect;
import static ua.cargo.utils.ActionUtil.isPostMethod;

public class DeleteRouteAction implements Action {
    private final RouteService routeService;

    public DeleteRouteAction(RouteService routeService) {
        this.routeService = routeService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<RouteDTO> listRoute = routeService.getAll();
            request.setAttribute("listRoutes", listRoute);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return Pages.DELETE_ROUTE_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        routeService.delete(Long.parseLong(request.getParameter(Parameters.ROUTE_ID)));
        return getActionToRedirect(ActionNames.DELETE_ROUTE_ACTION);
    }
}
