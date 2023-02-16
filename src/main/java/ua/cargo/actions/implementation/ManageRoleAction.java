package ua.cargo.actions.implementation;


import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static ua.cargo.actions.constants.Pages.ADMIN_PAGE;
import static ua.cargo.actions.constants.Parameters.USER_ID;
import static ua.cargo.actions.constants.Parameters.USER_ROLE;
import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.ActionUtil.transferStringFromSessionToRequest;
import static ua.cargo.utils.FilterUtil.getUserFilterQueryString;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class ManageRoleAction implements Action {
    private final UserService userService;

    public ManageRoleAction(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        Pagination pagination = getPaginationData(request);
        String filterQuery = getUserFilterQueryString(request);
        try {
            List<UserDTO> users = userService.getAll(pagination, filterQuery);
            request.setAttribute(Parameters.TOTAL_RECORDS, userService.getNumberOfRecords(filterQuery));
            String role = request.getParameter(Parameters.USER_ROLE);
            request.setAttribute(Parameters.USER_ROLES, role == null ? null : Role.getRole(Integer.valueOf(role)));
            request.setAttribute(Parameters.LIST_USERS, users);
            request.setAttribute(Parameters.ROLES, Role.values());
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        transferStringFromSessionToRequest(request, Parameters.OFFSET);
        transferStringFromSessionToRequest(request, Parameters.RECORDS);
        paginate(request);
        return ADMIN_PAGE;
    }

    private String executePost(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter(USER_ID));
        Role role = Role.getRole(Integer.valueOf(request.getParameter(USER_ROLE)));
        try {
            userService.updateRole(id, role);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MANAGE_ROLE_ACTION, getParameterData(request));
    }
}
