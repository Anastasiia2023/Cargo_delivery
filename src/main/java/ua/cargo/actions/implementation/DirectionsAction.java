package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class DirectionsAction implements Action {

    private final CityService cityService;
    private final RouteService routeService;

    public DirectionsAction(CityService cityService, RouteService routeService) {
        this.cityService = cityService;
        this.routeService = routeService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CityDTO> listCity = cityService.getAll();
            request.setAttribute("listCity", listCity);
            transferListFromSessionToRequest(request, "listRoute");
            transferCityDTOFromSessionToRequest(request, "cityFrom");
            transferCityDTOFromSessionToRequest(request, "cityTo");
            transferStringFromSessionToRequest(request, Parameters.OFFSET);
            transferStringFromSessionToRequest(request, Parameters.RECORDS);
            transferStringFromSessionToRequest(request, Parameters.TOTAL_RECORDS);
            paginate(request);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return Pages.DIRECTION_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        try {
            long cityFromId;
            long cityToId;
            List<RouteDTO> result = new ArrayList<>();
            Pagination pagination = getPaginationData(request);
            if (!request.getParameter(Parameters.CITY_FROM_ID).isEmpty() &&!request.getParameter(Parameters.CITY_TO_ID).isEmpty()) {
                cityFromId = Long.parseLong(request.getParameter(Parameters.CITY_FROM_ID));
                cityToId = Long.parseLong(request.getParameter(Parameters.CITY_TO_ID));
                result.add(routeService.getByCities(cityFromId, cityToId, pagination));
                CityDTO cityFrom = cityService.getById(cityFromId);
                request.getSession().setAttribute("cityFrom", cityFrom);
                CityDTO cityTo = cityService.getById(cityToId);
                request.getSession().setAttribute("cityTo", cityTo);
                request.getSession().setAttribute(Parameters.TOTAL_RECORDS, result.size());
            } else if (!request.getParameter(Parameters.CITY_FROM_ID).isEmpty()) {
                cityFromId = Long.parseLong(request.getParameter(Parameters.CITY_FROM_ID));
                result.addAll(routeService.getByCityFrom(cityFromId, pagination));
                CityDTO cityFrom = cityService.getById(cityFromId);
                request.getSession().setAttribute("cityFrom", cityFrom);
                request.getSession().setAttribute(Parameters.TOTAL_RECORDS, routeService.getNumberOfRecordsByCityFrom(cityFromId));
            } else if (!request.getParameter(Parameters.CITY_TO_ID).isEmpty()) {
                cityToId = Long.parseLong(request.getParameter(Parameters.CITY_TO_ID));
                result.addAll(routeService.getByCityTo(cityToId, pagination));
                CityDTO cityTo = cityService.getById(cityToId);
                request.getSession().setAttribute("cityTo", cityTo);
                request.getSession().setAttribute(Parameters.TOTAL_RECORDS, routeService.getNumberOfRecordsByCityTo(cityToId));
            } else {
                result.addAll(routeService.getAll(pagination));
                request.getSession().setAttribute(Parameters.TOTAL_RECORDS, routeService.getNumberOfRecords());
            }

            request.getSession().setAttribute("listRoute", result);

        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.DIRECTIONS_ACTION);
    }

}