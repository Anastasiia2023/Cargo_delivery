package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.exceptions.DuplicateTitleException;
import ua.cargo.exceptions.IncorrectFormatException;
import ua.cargo.exceptions.PasswordMatchingException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ua.cargo.utils.ActionUtil.getActionToRedirect;
import static ua.cargo.utils.ActionUtil.isPostMethod;

public class CreateCityAction implements Action {
    private final CityService cityService;

    public CreateCityAction(CityService cityService) {
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
        CityDTO city = getCityDTO(request);
        try {
            cityService.add(city);
            return getActionToRedirect(ActionNames.CREATE_CITY_ACTION);
        } catch (IncorrectFormatException | PasswordMatchingException | DuplicateTitleException e) {
            request.getSession().setAttribute(Parameters.CITY, city);
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.CREATE_CITY_ACTION);
    }

    private CityDTO getCityDTO(HttpServletRequest request) {
        return new CityDTO(request.getParameter(Parameters.CITY));
    }
}
