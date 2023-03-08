package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ParameterValues;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.DuplicateEmailException;
import ua.cargo.exceptions.IncorrectFormatException;
import ua.cargo.exceptions.PasswordMatchingException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;

import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.actions.constants.ActionNames.SIGN_UP_ACTION;
import static ua.cargo.actions.constants.Pages.SIGN_IN_PAGE;
import static ua.cargo.actions.constants.Pages.SIGN_UP_PAGE;

public class SignUpAction implements Action {
    private final UserService userService;

    public SignUpAction(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request);
    }

    private String executeGet(HttpServletRequest request) {
        transferStringFromSessionToRequest(request, Parameters.MESSAGE);
        transferStringFromSessionToRequest(request, Parameters.ERROR);
        transferUserDTOFromSessionToRequest(request);
        return getPath(request);
    }

    private String executePost(HttpServletRequest request) throws ServiceException {
        String path = SIGN_IN_PAGE;
        UserDTO user = getUserDTO(request);
        request.getSession().setAttribute(Parameters.USER, user);
        try {
            userService.add(user);
            request.getSession().setAttribute(Parameters.MESSAGE, ParameterValues.SUCCEED_REGISTER);
        } catch (IncorrectFormatException | PasswordMatchingException | DuplicateEmailException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
            path = SIGN_UP_PAGE;
        }
        request.getSession().setAttribute(Parameters.CURRENT_PATH, path);
        return getActionToRedirect(SIGN_UP_ACTION);
    }

    private UserDTO getUserDTO(HttpServletRequest request) {
        return new UserDTO(request.getParameter(Parameters.NAME), request.getParameter(Parameters.SURNAME), request.getParameter(Parameters.PASSWORD),
                request.getParameter(Parameters.EMAIL), request.getParameter(Parameters.PHONE), LocalDate.parse(request.getParameter(Parameters.DATE_OF_BIRTH)));
    }
}