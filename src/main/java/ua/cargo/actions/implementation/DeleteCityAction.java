package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ua.cargo.utils.ActionUtil.getActionToRedirect;
import static ua.cargo.utils.ActionUtil.isPostMethod;

public class DeleteCityAction implements Action {
    private final CityService cityService;

    public DeleteCityAction(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CityDTO> listCity = cityService.getAll();
            request.setAttribute("listCity", listCity);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return Pages.CREATE_CITY_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        cityService.delete(Long.parseLong(request.getParameter(Parameters.CITY_ID)));
        return getActionToRedirect(ActionNames.DELETE_CITY_ACTION);
    }
}
