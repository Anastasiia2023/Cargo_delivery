package ua.cargo.actions.implementation;


import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.IncorrectPasswordException;
import ua.cargo.exceptions.NoSuchUserException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.cargo.utils.ActionUtil.*;

public class SignInAction implements Action {
    private final UserService userService;

    public SignInAction(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, Parameters.EMAIL);
        transferStringFromSessionToRequest(request, Parameters.ERROR);
        return getPath(request);
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        String path = Pages.PROFILE_PAGE;
        String email = request.getParameter(Parameters.EMAIL);
        String password = request.getParameter(Parameters.PASSWORD);
        try {
            setLoggedUser(request, userService.signIn(email, password));
            return path;
        } catch (NoSuchUserException | IncorrectPasswordException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
            request.getSession().setAttribute(Parameters.EMAIL, email);
            path = Pages.SIGN_IN_PAGE;
        }
        request.getSession().setAttribute(Parameters.CURRENT_PATH, path);
        return getActionToRedirect(ActionNames.SIGN_IN_ACTION) ;
    }

    private static void setLoggedUser(HttpServletRequest request, UserDTO user) {
        request.getSession().setAttribute(Parameters.LOGGED_USER, user);
        request.getSession().setAttribute(Parameters.ROLE, user.getRole());
    }
}