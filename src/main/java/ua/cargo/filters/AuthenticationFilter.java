package ua.cargo.filters;

import ua.cargo.filters.domain.Domain;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static ua.cargo.actions.constants.Pages.SIGN_IN_PAGE;
import static ua.cargo.actions.constants.ParameterValues.ACCESS_DENIED;
import static ua.cargo.actions.constants.Parameters.*;
import static ua.cargo.filters.domain.Domain.getDomain;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (isNoLoggedUser(httpRequest) && isAccessDenied(httpRequest)) {
            httpRequest.setAttribute(MESSAGE, ACCESS_DENIED);
            request.getRequestDispatcher(SIGN_IN_PAGE).forward(request, response);
        } else {
            chain.doFilter(httpRequest, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private static boolean isNoLoggedUser(HttpServletRequest request) {
        return request.getSession().getAttribute(LOGGED_USER) == null;
    }

    private boolean isAccessDenied(HttpServletRequest request) {
        return (Domain.getDomain(request.getServletPath(), request.getParameter(ACTION)).checkAccess());
    }
}