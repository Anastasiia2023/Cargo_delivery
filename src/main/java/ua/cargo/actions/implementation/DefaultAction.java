package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.INDEX_PAGE;
    }
}