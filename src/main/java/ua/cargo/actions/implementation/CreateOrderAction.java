package ua.cargo.actions.implementation;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.OrderService;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ua.cargo.utils.ActionUtil.*;

public class CreateOrderAction implements Action {

    private final RouteService routeService;
    private final OrderService orderService;

    public CreateOrderAction(RouteService routeService, OrderService orderService) {
        this.routeService = routeService;
        this.orderService = orderService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return isPostMethod(request) ? executePost(request) : executeGet(request, response);
    }

    private String executeGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<RouteDTO> listRoute = routeService.getAll();
            listRoute= listRoute.stream().sorted(Comparator.comparing(RouteDTO::getCityFrom)).collect(Collectors.toList());
            request.setAttribute("listRoute", listRoute);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return Pages.CREATE_ORDER_PAGE;
    }

    private String executePost(HttpServletRequest request) {
        OrderDTO orderDTO = getOrderDTO(request);
        boolean packageService = request.getParameter(Parameters.PACKAGE_SERVICE) == null ? Boolean.FALSE : Boolean.TRUE;
        try {
            orderService.createOrder(orderDTO, packageService);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Parameters.ERROR, e.getMessage());
        }
        return getActionToRedirect(ActionNames.MY_ORDER_ACTION);
    }

    private OrderDTO getOrderDTO(HttpServletRequest request) {
        double volume = Double.parseDouble(request.getParameter(Parameters.LENGTH))*Double.parseDouble(request.getParameter(Parameters.HEIGHT))*
                Double.parseDouble(request.getParameter(Parameters.WIDTH));
        double weight = Math.max(volume/4000, Double.parseDouble(request.getParameter(Parameters.WEIGHT)));
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Parameters.LOGGED_USER);
        return new OrderDTO(weight, volume, Long.parseLong(request.getParameter(Parameters.ROUTE_ID)),
                Double.parseDouble(request.getParameter(Parameters.COST)), request.getParameter(Parameters.PACKAGE_TYPE), userDTO.getId());
    }

}