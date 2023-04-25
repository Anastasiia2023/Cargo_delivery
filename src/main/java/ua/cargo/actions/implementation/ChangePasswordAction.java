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

/**
 * This is ChangePasswordAction class. Accessible by any logged user. Allows to change user's password.
 *
 * @author Anastasiia Shevchuk
 * @version 1.0
 */

public class ChangePasswordAction implements Action {
    private final UserService userService;

    public ChangePasswordAction(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    /**
     * Called from doGet method in front-controller. Obtains required path and transfer attributes from session
     * to request
     *
     * @param request to get message or error attribute from session and put it in request
     * @return profile page
     */

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, Parameters.MESSAGE);
        transferStringFromSessionToRequest(request, Parameters.ERROR);
        return PROFILE_PAGE;
    }

    /**
     * Called from doPost method in front-controller. Tries to change users password via service
     *
     * @param request to get users id and all passwords. Also, to set message in case of successful deleting and error
     * in another case
     *
     * @return path to redirect to executeGet method through front-controller
     */

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