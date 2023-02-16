package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.CalculateAction;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CalculateService;
import ua.cargo.services.CityService;
import ua.cargo.services.RouteService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;


import static org.mockito.Matchers.any;

import static org.mockito.Mockito.*;


public class CalculateActionTest {
    private CityService cityService;
    private RouteService routeService;
    private CalculateService calculateService;
    private CalculateAction calculateAction;

    @Before
    public void init()
    {
        cityService = Mockito.mock(CityService.class);
        routeService = Mockito.mock(RouteService.class);
        calculateService = Mockito.mock(CalculateService.class);
        calculateAction = new CalculateAction(cityService, routeService, calculateService);
    }



    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        int distance = 567;
        int terms = 2;
        CityDTO cityDTO = new CityDTO(id, "Rome");
        List<CityDTO> cityDTOList = List.of(cityDTO);
        RouteDTO routeDTO = new RouteDTO(id, "Rome", id, "Milan", distance, terms);
        List<RouteDTO> routeDTOList = List.of(routeDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        Mockito.when(cityService.getAll()).thenReturn(cityDTOList);
        Mockito.when(routeService.getAll()).thenReturn(routeDTOList);

        String result = calculateAction.execute(request, response);

        Assert.assertEquals(Pages.CALCULATE_PAGE, result);


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
        RouteDTO routeDTO = new RouteDTO(id,id,400,1);
        String expResult = "controller?action=calculate";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

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

        when(calculateService.calculateParcel(any())).thenReturn(totalCost);
        when(routeService.getById(anyLong())).thenReturn(routeDTO);

        String result = calculateAction.execute(request, response);

        Assert.assertEquals(expResult, result);



    }
}
