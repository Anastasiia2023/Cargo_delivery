package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.cargo.actions.constants.ActionNames.CREATE_ROUTE_ACTION;
import static ua.cargo.utils.ActionUtil.*;

public class RoutesAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return getActionToRedirect(CREATE_ROUTE_ACTION, getParameterData(request));
    }

}