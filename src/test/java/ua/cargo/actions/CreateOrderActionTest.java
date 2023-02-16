package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.CreateCityAction;
import ua.cargo.actions.implementation.CreateOrderAction;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.City;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.OrderService;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static ua.cargo.utils.ActionUtil.*;

public class CreateOrderActionTest {

    private RouteService routeService;
    private OrderService orderService;
    private CreateOrderAction createOrderAction;

    @Before
    public void init() {
        routeService = Mockito.mock(RouteService.class);
        orderService = Mockito.mock(OrderService.class);
        createOrderAction = new CreateOrderAction(routeService, orderService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        String cityFrom = "Lviv";
        String cityTo = "Kharkov";
        RouteDTO routeDTO = new RouteDTO(id, cityFrom, id, cityTo, 850, 2);
        List<RouteDTO> routeDTOList = List.of(routeDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        Mockito.when(routeService.getAll()).thenReturn(routeDTOList);

        String result = createOrderAction.execute(request, response);

        Assert.assertEquals(Pages.CREATE_ORDER_PAGE, result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        int cost = 345;
        double weight = 12;
        double length = 20;
        double height = 30;
        double width = 40;
        double totalCost = 230;
        String password = "0o9i8u7y6T";
        RouteDTO routeDTO = new RouteDTO(id, id, 400, 1);
        String expResult = "controller?action=my-order";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        UserDTO userDTO = new UserDTO(id, "Nino", "Archi", password, "wkmffeifiw@ulr.net");

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameters.PACKAGE_SERVICE)).thenReturn(null);

        when(request.getParameter(Parameters.ROUTE_ID)).thenReturn(String.valueOf(id));
        when(request.getParameter(Parameters.COST)).thenReturn(String.valueOf(cost));
        when(request.getParameter(Parameters.WEIGHT)).thenReturn(String.valueOf(weight));
        when(request.getParameter(Parameters.LENGTH)).thenReturn(String.valueOf(length));
        when(request.getParameter(Parameters.HEIGHT)).thenReturn(String.valueOf(height));
        when(request.getParameter(Parameters.WIDTH)).thenReturn(String.valueOf(width));

        when(request.getParameter(Parameters.PACKAGE_TYPE)).thenReturn("Cargo");
        when(session.getAttribute(Parameters.LOGGED_USER)).thenReturn(userDTO);

        doNothing().when(orderService).createOrder(any(), anyBoolean());
        when(routeService.getById(anyLong())).thenReturn(routeDTO);

        String result = createOrderAction.execute(request, response);

        Assert.assertEquals(expResult, result);


    }
}
