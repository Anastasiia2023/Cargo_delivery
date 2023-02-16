package ua.cargo.services;

import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.ParcelDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.exceptions.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.cargo.services.impl.CalculateServiceImpl;

import java.time.LocalDate;

import static org.mockito.Matchers.anyLong;

public class CalculateServiceTest {

    private RouteService routeService;
    private CalculateService calculateService;

    @Before
    public void init()
    {
        routeService = Mockito.mock(RouteService.class);
        calculateService = new CalculateServiceImpl(routeService);
    }

    @Test
    public void calculateParcelTest() throws ServiceException {
        long routeId = 1L;
        int cost = 250;
        double weight = 0.5;
        double length = 10.2;
        double height = 5.5;
        double width = 3;
        String packageType = "Package";
        boolean packageService = Boolean.TRUE;
        ParcelDTO parcelDTO = new ParcelDTO(routeId, cost, weight, length, height, width, packageType, packageService);
        RouteDTO routeDTO = new RouteDTO(1L, 2L, 450,1);
        double expected = 127.5;

        Mockito.when(routeService.getById(anyLong())).thenReturn(routeDTO);

        double result = calculateService.calculateParcel(parcelDTO);

        Assert.assertEquals(expected, result, 0.000001);
    }
    @Test
    public void calculateOrderCargoTest() throws ServiceException {
        long routeId = 1L;
        double price = 250;
        double weight = 33;
        double volume = 60000;
        String packageType = "Cargo";
        long userId = 1L;
        LocalDate date = LocalDate.now();
        OrderDTO orderDTO = new OrderDTO(weight, volume, routeId, price, packageType, userId);
        orderDTO.setCreationDate(date);
        RouteDTO routeDTO = new RouteDTO(1L, 2L, 450,1);
        double expected = 409.5;

        Mockito.when(routeService.getById(anyLong())).thenReturn(routeDTO);

        double result = calculateService.calculateOrder(orderDTO, Boolean.TRUE);

        Assert.assertEquals(expected, result, 0.000001);
    }
    @Test
    public void calculateOrderDocumentTest() throws ServiceException {
        long routeId = 1L;
        double price = 650;
        double weight = 3;
        double volume = 3000;
        String packageType = "Documents";
        long userId = 1L;
        LocalDate date = LocalDate.now();
        OrderDTO orderDTO = new OrderDTO(weight, volume, routeId, price, packageType, userId);
        orderDTO.setCreationDate(date);
        RouteDTO routeDTO = new RouteDTO(1L, 2L, 820,2);
        double expected = 137.5;

        Mockito.when(routeService.getById(anyLong())).thenReturn(routeDTO);

        double result = calculateService.calculateOrder(orderDTO, Boolean.TRUE);

        Assert.assertEquals(expected, result, 0.000001);
    }



}
