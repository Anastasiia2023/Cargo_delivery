package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.MyOrderAction;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.cargo.actions.constants.Parameters.*;
import static ua.cargo.actions.constants.Parameters.ORDER_STATUS;
import static ua.cargo.actions.constants.Pages.MY_ORDER_PAGE;

public class MyOrderActionTest {
    private OrderService orderService;
    private MyOrderAction myOrderAction;

    @Before
    public void init()
    {
        orderService = Mockito.mock(OrderService.class);
        myOrderAction = new MyOrderAction(orderService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        String password = "0o9i8u7y6T";
        OrderDTO orderDTO = new OrderDTO(20,30, id, 450, "Package", id);
        UserDTO userDTO = new UserDTO(id, "Nino", "Archi", password, "wkmffeifiw@ulr.net");
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(25);
        pagination.setOrder("");
        String status = "Processed";
        List<OrderDTO> orderDTOList = List.of(orderDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Parameters.LOGGED_USER)).thenReturn(userDTO);
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(pagination.getOffset()));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(pagination.getRecords()));
        when(request.getParameter(ORDER)).thenReturn(pagination.getOrder());

        when(request.getParameter(ORDER_STATUS)).thenReturn(status);
        Mockito.when(orderService.getAllByUser(anyLong(), any(), anyString())).thenReturn(orderDTOList);

        String result = myOrderAction.execute(request, response);

        Assert.assertEquals(MY_ORDER_PAGE, result);
    }
    @Test
    public void executePostTest() throws ServiceException {
        String expResult = "controller?action=my-order";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);

        String result = myOrderAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }
}
