package ua.cargo.actions;

import ua.cargo.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}