package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.ManageOrderAction;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static ua.cargo.actions.constants.Parameters.*;

public class ManageOrderActionTest {
    private OrderService orderService;
    private CityService cityService;
    private ManageOrderAction manageOrderAction;

    @Before
    public void init()
    {
        orderService = Mockito.mock(OrderService.class);
        cityService = Mockito.mock(CityService.class);
        manageOrderAction = new ManageOrderAction(orderService, cityService);
    }
    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        OrderDTO orderDTO = new OrderDTO(20,30, id, 450, "Cargo", id);
        CityDTO cityDTO = new CityDTO(id, "Rome");
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(25);
        pagination.setOrder("");
        String status = "Placed";
        List<OrderDTO> orderDTOList = List.of(orderDTO);
        List<CityDTO> cityDTOList = List.of(cityDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(pagination.getOffset()));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(pagination.getRecords()));
        when(request.getParameter(ORDER)).thenReturn(pagination.getOrder());
        when(request.getParameter(Parameters.CITY_FROM_ID)).thenReturn(String.valueOf(id));
        when(request.getParameter(Parameters.CITY_TO_ID)).thenReturn(String.valueOf(id));
        when(request.getParameter(ORDER_STATUS)).thenReturn(status);
        Mockito.when(orderService.getAll(any(), anyString())).thenReturn(orderDTOList);
        Mockito.when(cityService.getAll()).thenReturn(cityDTOList);

        String result = manageOrderAction.execute(request, response);

        Assert.assertEquals(Pages.REPORTS_PAGE, result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        String expResult = "controller?action=manage-order";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);

        String result = manageOrderAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }
}
