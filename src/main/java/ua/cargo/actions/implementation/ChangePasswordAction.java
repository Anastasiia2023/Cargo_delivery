package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.ParameterValues;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.*;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.actions.constants.Pages.PROFILE_PAGE;

public class ChangePasswordAction implements Action {
    private final UserService userService;

    public ChangePasswordAction(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, Parameters.MESSAGE);
        transferStringFromSessionToRequest(request, Parameters.ERROR);
        return PROFILE_PAGE;
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        try {
            userServiceChangePassword(request);
            request.getSession().setAttribute(Parameters.MESSAGE, ParameterValues.SUCCEED_UPDATE);
        } catch (IncorrectFormatException | IncorrectPasswordException | NoSuchUserException |
                 PasswordMatchingException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.CHANGE_PASSWORD_ACTION);
    }

    private void userServiceChangePassword(HttpServletRequest request) throws ServiceException {
        long id = ((UserDTO) request.getSession().getAttribute(Parameters.LOGGED_USER)).getId();
        String oldPassword = request.getParameter(Parameters.OLD_PASSWORD);
        String password = request.getParameter(Parameters.PASSWORD);
        String confirmPassword = request.getParameter(Parameters.CONFIRM_PASSWORD);
        userService.changePassword(id, oldPassword, password, confirmPassword);
    }
}