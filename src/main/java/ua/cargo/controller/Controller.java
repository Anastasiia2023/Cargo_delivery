 package ua.cargo.controller;

import ua.cargo.actions.Action;
import ua.cargo.actions.ActionFactory;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ua.cargo.actions.constants.Pages.ERROR_PAGE;
import static ua.cargo.actions.constants.Parameters.ACTION;


public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private static final ActionFactory ACTION_FACTORY = ActionFactory.getActionFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = process(request, response);
        if (isAttributesPresent(request)) {
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            response.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(process(request, response));
    }

    private String process(HttpServletRequest request, HttpServletResponse response) {
        Action action = ACTION_FACTORY.createAction(request.getParameter(ACTION));
        String path = ERROR_PAGE;
        try {
            path = action.execute(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return path;
    }

    private boolean isAttributesPresent (HttpServletRequest request) {
        return request.getAttributeNames().hasMoreElements();
    }
}