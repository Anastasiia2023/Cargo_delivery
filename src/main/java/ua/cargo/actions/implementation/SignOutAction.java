package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.Pages;
import ua.cargo.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        HttpSession session = request.getSession();
        session.invalidate();
        return Pages.SIGN_IN_PAGE;
    }
}