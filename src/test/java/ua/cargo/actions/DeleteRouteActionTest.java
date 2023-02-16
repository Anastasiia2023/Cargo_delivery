package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.DeleteRouteAction;
import ua.cargo.dto.RouteDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;



public class DeleteRouteActionTest {
    private  RouteService routeService;
    private DeleteRouteAction deleteRouteAction;

    @Before
    public void init()
    {
        routeService = Mockito.mock(RouteService.class);
        deleteRouteAction = new DeleteRouteAction(routeService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        int distance = 230;
        int terms = 1;
        RouteDTO routeDTO = new RouteDTO(id, id, distance, terms );
        List<RouteDTO> routeDTOList = List.of(routeDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        Mockito.when(routeService.getAll()).thenReturn(routeDTOList);

        String result = deleteRouteAction.execute(request, response);

        Assert.assertEquals(Pages.DELETE_ROUTE_PAGE, result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        String expResult = "controller?action=delete-route";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameters.ROUTE_ID)).thenReturn(String.valueOf(id));
        doNothing().when(routeService).delete(anyLong());

        String result = deleteRouteAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }
}
