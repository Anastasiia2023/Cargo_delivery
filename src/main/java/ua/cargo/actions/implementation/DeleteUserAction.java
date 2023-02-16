package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.ParameterValues;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.NoSuchUserException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.ActionUtil.transferStringFromSessionToRequest;

public class DeleteUserAction implements Action {
    private final UserService userService;

    public DeleteUserAction(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        transferStringFromSessionToRequest(request, Parameters.MESSAGE);
        transferStringFromSessionToRequest(request, Parameters.ERROR);
        return Pages.SIGN_IN_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        UserDTO sessionUser = (UserDTO) request.getSession().getAttribute(Parameters.LOGGED_USER);
        try {
            userService.delete(sessionUser.getId());
            request.getSession().setAttribute(Parameters.MESSAGE, ParameterValues.SUCCEED_DELETE);
        } catch (NoSuchUserException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.DELETE_USER_ACTION);
    }
}
