package ua.cargo.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static ua.cargo.actions.constants.Pages.SIGN_IN_PAGE;
import static ua.cargo.actions.constants.ParameterValues.ACCESS_DENIED;
import static ua.cargo.actions.constants.Parameters.*;
import static ua.cargo.filters.domain.Domain.getDomain;

public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Integer role = (Integer) httpRequest.getSession().getAttribute(ROLE);
        if (isAccessDenied(httpRequest, role)) {
            httpRequest.setAttribute(MESSAGE, ACCESS_DENIED);
            request.getRequestDispatcher(SIGN_IN_PAGE).forward(request, response);
        } else {
            chain.doFilter(httpRequest, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private boolean isAccessDenied(HttpServletRequest request, Integer role) {
        return (getDomain(request.getServletPath(), request.getParameter(ACTION), role).checkAccess());
    }
}