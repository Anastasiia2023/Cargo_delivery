package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import ua.cargo.actions.implementation.CreateRouteAction;
import ua.cargo.dto.CityDTO;

import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import static ua.cargo.actions.constants.Pages.*;
import static ua.cargo.actions.constants.Parameters.*;


public class CreateRouteActionTest {
    private  RouteService routeService;
    private  CityService cityService;
    private CreateRouteAction createRouteAction;

    @Before
    public void init()
    {
        cityService = Mockito.mock(CityService.class);
        routeService = Mockito.mock(RouteService.class);
        createRouteAction = new CreateRouteAction(routeService, cityService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        CityDTO cityDTO = new CityDTO(id, "Rome");
        int offset = 10;
        int records = 25;
        String order = "";

        List<CityDTO> cityDTOList = List.of(cityDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(offset));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(records));
        when(request.getParameter(ORDER)).thenReturn(order);
        Mockito.when(cityService.getAll()).thenReturn(cityDTOList);

        String result = createRouteAction.execute(request, response);

        Assert.assertEquals(CREATE_ROUTE_PAGE, result);
    }
    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        int distance = 560;
        int terms = 2;
        String expResult = "controller?action=create-route";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(CITY_FROM_ID)).thenReturn(String.valueOf(id));
        when(request.getParameter(CITY_TO_ID)).thenReturn(String.valueOf(id));
        when(request.getParameter(DISTANCE)).thenReturn(String.valueOf(distance));
        when(request.getParameter(TERMS)).thenReturn(String.valueOf(terms));

        doNothing().when(routeService).add(any());

        String result = createRouteAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }




}
