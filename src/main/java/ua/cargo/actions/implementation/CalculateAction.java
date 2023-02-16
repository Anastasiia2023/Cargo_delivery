package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.dto.ParcelDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CalculateService;
import ua.cargo.services.CityService;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.Collectors;

import static ua.cargo.utils.ActionUtil.*;

public class CalculateAction implements Action {

    private final CityService cityService;
    private final RouteService routeService;
    private final CalculateService calculateService;

    public CalculateAction(CityService cityService, RouteService routeService, CalculateService calculateService) {
        this.cityService = cityService;
        this.routeService = routeService;
        this.calculateService = calculateService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CityDTO> listCity = cityService.getAll();
            List<RouteDTO> listRoute = routeService.getAll();
            listRoute= listRoute.stream().sorted(Comparator.comparing(RouteDTO::getCityFrom)).collect(Collectors.toList());
            request.setAttribute("listCity", listCity);
            request.setAttribute("listRoute", listRoute);
            transferStringFromSessionToRequest(request, "totalCost");
            transferParcelDTOFromSessionToRequest(request);
            transferStringFromSessionToRequest(request, "chosenRoute");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return Pages.CALCULATE_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        ParcelDTO parcelDTO = getParcelDTO(request);
        try {
            double totalCost = calculateService.calculateParcel(parcelDTO);
            request.getSession().setAttribute("totalCost", totalCost);
            request.getSession().setAttribute("parcel", parcelDTO);
            request.getSession().setAttribute("chosenRoute", getChosenRoute(parcelDTO));
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.CALCULATE_ACTION);
    }

    private ParcelDTO getParcelDTO(HttpServletRequest request) {
        boolean packageService = request.getParameter(Parameters.PACKAGE_SERVICE) == null ? Boolean.FALSE : Boolean.TRUE;
        return new ParcelDTO(Long.parseLong(request.getParameter(Parameters.ROUTE_ID)),
                Integer.parseInt(request.getParameter(Parameters.COST)),
                Double.parseDouble(request.getParameter(Parameters.WEIGHT)),
                Double.parseDouble(request.getParameter(Parameters.LENGTH)),
                Double.parseDouble(request.getParameter(Parameters.HEIGHT)),
                Double.parseDouble(request.getParameter(Parameters.WIDTH)),
                request.getParameter(Parameters.PACKAGE_TYPE),
                packageService);
    }

    private String getChosenRoute(ParcelDTO parcelDTO) throws ServiceException {
        RouteDTO route = routeService.getById(parcelDTO.getRouteId());
        return String.format("%s-%s", route.getCityFrom(), route.getCityTo());
    }

}