package ua.cargo.services;

import ua.cargo.dao.RouteDAO;
import ua.cargo.dto.RouteDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.entities.*;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.impl.RouteServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyInt;

public class RouteServiceTest {

    private RouteDAO routeDAO;
    private RouteService routeService;

    @Before
    public void init() {
        routeDAO = Mockito.mock(RouteDAO.class);
        routeService = new RouteServiceImpl(routeDAO);

    }

    @Test
    public void getByIdTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        Optional<Route> routeOptional = Optional.of(route);

        Mockito.when(routeDAO.getById(anyLong())).thenReturn(routeOptional);

        RouteDTO result = routeService.getById(id);

        Assert.assertEquals(route.getId(), result.getId());

    }

    @Test(expected = ServiceException.class)
    public void getByIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(routeDAO.getById(anyLong())).thenThrow(new DAOException(null));

        routeService.getById(id);

    }

    @Test
    public void getAllTest() throws DAOException, ServiceException {
        long id = 1L;
        City cityFrom = new City(23, "Rome");
        City cityTo = new City(24, "Milano");
        Route routes = new Route(id, cityFrom, cityTo, 900, 2);
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);


        List<Route> routesList = List.of(routes);

        Mockito.when(routeDAO.getAll(anyInt(), anyInt())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getAll(pagination);

        Assert.assertEquals(routes.getId(), result.get(0).getId());
        Assert.assertEquals(routes.getCityFrom().getCity(), result.get(0).getCityFrom());
        Assert.assertEquals(routes.getCityTo().getCity(), result.get(0).getCityTo());
        Assert.assertEquals(routes.getDistance(), result.get(0).getDistance());
        Assert.assertEquals(routes.getCityFrom().getId(), result.get(0).getCityFromId());
        Assert.assertEquals(routes.getCityTo().getId(), result.get(0).getCityToId());


    }

    @Test
    public void getAllisOrderedTest() throws DAOException, ServiceException {
        long id = 1L;
        City cityFrom = new City(14, "Berlin");
        City cityTo = new City(45, "Krakow");
        Route routes = new Route(id, cityFrom, cityTo, 900, 2);
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        pagination.setOrder("");


        List<Route> routesList = List.of(routes);

        Mockito.when(routeDAO.getAll(anyInt(), anyInt(), anyString(), anyString())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getAll(pagination);

        Assert.assertEquals(routes.getId(), result.get(0).getId());
        Assert.assertEquals(routes.getCityFrom().getCity(), result.get(0).getCityFrom());
        Assert.assertEquals(routes.getCityTo().getCity(), result.get(0).getCityTo());
        Assert.assertEquals(routes.getDistance(), result.get(0).getDistance());
        Assert.assertEquals(routes.getCityFrom().getId(), result.get(0).getCityFromId());
        Assert.assertEquals(routes.getCityTo().getId(), result.get(0).getCityToId());


    }

    @Test(expected = ServiceException.class)
    public void getAllExceptionTest() throws DAOException, ServiceException {
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);

        Mockito.when(routeDAO.getAll(anyInt(), anyInt())).thenThrow(new DAOException(null));

        routeService.getAll(pagination);

    }

    @Test
    public void getByCitiesTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));

        Optional<Route> routeOptional = Optional.of(route);

        Mockito.when(routeDAO.getByCities(anyLong(), anyLong())).thenReturn(routeOptional);

        RouteDTO result = routeService.getByCities(id, id);

        Assert.assertEquals(route.getId(), result.getId());
        Assert.assertEquals(route.getCityFrom().getId(), result.getCityFromId());
        Assert.assertEquals(route.getCityTo().getId(), result.getCityToId());


    }

    @Test(expected = ServiceException.class)
    public void getByCitiesExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(routeDAO.getByCities(anyLong(), anyLong())).thenThrow(new DAOException(null));

        routeService.getByCities(id, id);

    }

    @Test
    public void getByCitiesPaginationTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        pagination.setOrder("");

        Optional<Route> routeOptional = Optional.of(route);

        Mockito.when(routeDAO.getByCities(anyLong(), anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(routeOptional);

        RouteDTO result = routeService.getByCities(id, id, pagination);

        Assert.assertEquals(route.getId(), result.getId());
        Assert.assertEquals(route.getCityFrom().getId(), result.getCityFromId());
        Assert.assertEquals(route.getCityTo().getId(), result.getCityToId());

    }

    @Test
    public void getByCitiesPaginationSecondTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);


        Optional<Route> routeOptional = Optional.of(route);

        Mockito.when(routeDAO.getByCities(anyLong(), anyLong(), anyInt(), anyInt())).thenReturn(routeOptional);

        RouteDTO result = routeService.getByCities(id, id, pagination);

        Assert.assertEquals(route.getId(), result.getId());
        Assert.assertEquals(route.getCityFrom().getId(), result.getCityFromId());
        Assert.assertEquals(route.getCityTo().getId(), result.getCityToId());

    }

    @Test(expected = ServiceException.class)
    public void getByCitiesPaginationExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);

        Mockito.when(routeDAO.getByCities(anyLong(), anyLong(), anyInt(), anyInt())).thenThrow(new DAOException(null));

        routeService.getByCities(id, id, pagination);

    }

    @Test
    public void getByCityFromTest() throws DAOException, ServiceException {
        long id = 1L;
        City cityFrom = new City(23);
        City cityTo = new City(24);
        Route routes = new Route(id, cityFrom, cityTo, 900, 2);

        List<Route> routesList = List.of(routes);

        Mockito.when(routeDAO.getByCityFrom(anyLong())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getByCityFrom(id);

        Assert.assertEquals(routes.getId(), result.get(0).getId());
        Assert.assertEquals(routes.getCityFrom().getId(), result.get(0).getCityFromId());
    }

    @Test(expected = ServiceException.class)
    public void getByCityFromExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(routeDAO.getByCityFrom(anyLong())).thenThrow(new DAOException(null));

        routeService.getByCityFrom(id);

    }

    @Test
    public void getByCityFromPaginationIsOrderedTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(34));
        route.setCityTo(new City(65));
        route.setDistance(765);

        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        pagination.setOrder("");


        List<Route> routesList = List.of(route);

        Mockito.when(routeDAO.getByCityFrom(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getByCityFrom(id, pagination);

        Assert.assertEquals(route.getId(), result.get(0).getId());
        Assert.assertEquals(route.getCityFrom().getId(), result.get(0).getCityFromId());


    }

    @Test
    public void getByCityFromPaginationTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(24));
        route.setCityTo(new City(12));
        route.setDistance(456);

        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);


        List<Route> routesList = List.of(route);

        Mockito.when(routeDAO.getByCityFrom(anyLong(), anyInt(), anyInt())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getByCityFrom(id, pagination);

        Assert.assertEquals(route.getId(), result.get(0).getId());
        Assert.assertEquals(route.getCityFrom().getId(), result.get(0).getCityFromId());


    }

    @Test(expected = ServiceException.class)
    public void getByCityFromPaginationExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(24));
        route.setCityTo(new City(12));
        route.setDistance(900);

        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        pagination.setOrder("");

        Mockito.when(routeDAO.getByCityFrom(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenThrow(new DAOException(null));

        routeService.getByCityFrom(id, pagination);

    }

    @Test
    public void getByCityToPaginationIsOrderedTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(12));
        route.setCityTo(new City(15));
        route.setDistance(452);

        Pagination pagination = new Pagination();
        pagination.setOffset(9);
        pagination.setRecords(34);
        pagination.setSortField("");
        pagination.setOrder("");

        List<Route> routesList = List.of(route);

        Mockito.when(routeDAO.getByCityTo(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getByCityTo(route.getCityTo().getId(), pagination);

        Assert.assertEquals(route.getId(), result.get(0).getId());
        Assert.assertEquals(route.getCityTo().getId(), result.get(0).getCityToId());


    }

    @Test
    public void getByCityToPaginationTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(16));
        route.setCityTo(new City(19));
        route.setDistance(987);

        Pagination pagination = new Pagination();
        pagination.setOffset(45);
        pagination.setRecords(54);


        List<Route> routesList = List.of(route);

        Mockito.when(routeDAO.getByCityTo(anyLong(), anyInt(), anyInt())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getByCityTo(route.getCityTo().getId(), pagination);

        Assert.assertEquals(route.getId(), result.get(0).getId());
        Assert.assertEquals(route.getCityTo().getId(), result.get(0).getCityToId());


    }

    @Test(expected = ServiceException.class)
    public void getByCityToPaginationExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        Route route = new Route(id);
        route.setCityFrom(new City(24));
        route.setCityTo(new City(12));
        route.setDistance(900);

        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        pagination.setOrder("");

        Mockito.when(routeDAO.getByCityTo(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenThrow(new DAOException(null));

        routeService.getByCityTo(route.getCityTo().getId(), pagination);

    }

    @Test
    public void getByCityToTest() throws DAOException, ServiceException {
        long id = 1L;
        City cityFrom = new City(54);
        City cityTo = new City(4);
        Route routes = new Route(id, cityFrom, cityTo, 860, 2);

        List<Route> routesList = List.of(routes);

        Mockito.when(routeDAO.getByCityTo(anyLong())).thenReturn(routesList);

        List<RouteDTO> result = routeService.getByCityTo(id);

        Assert.assertEquals(routes.getId(), result.get(0).getId());
        Assert.assertEquals(routes.getCityTo().getId(), result.get(0).getCityToId());
    }

    @Test(expected = ServiceException.class)
    public void getByCityToExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(routeDAO.getByCityTo(anyLong())).thenThrow(new DAOException(null));

        routeService.getByCityTo(id);

    }
    @Test
    public void getAllEmptyParamTest() throws DAOException, ServiceException {
        long id = 1L;
        City cityFrom = new City(23);
        City cityTo = new City(24);
        Route routes = new Route(id, cityFrom, cityTo, 900, 2);

        List<Route> routesList = List.of(routes);

        Mockito.when(routeDAO.getAll()).thenReturn(routesList);

        List<RouteDTO> result = routeService.getAll();

        Assert.assertEquals(routes.getId(), result.get(0).getId());
        Assert.assertEquals(routes.getCityFrom().getId(), result.get(0).getCityFromId());
        Assert.assertEquals(routes.getCityTo().getId(), result.get(0).getCityToId());
        Assert.assertEquals(routes.getDistance(), result.get(0).getDistance());
    }

    @Test(expected = ServiceException.class)
    public void getAllEmptyParamExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        City cityFrom = new City(23);
        City cityTo = new City(24);
        Route routes = new Route(id, cityFrom, cityTo, 900, 2);


        Mockito.when(routeDAO.getAll()).thenThrow(new DAOException(null));

        routeService.getAll();

    }



    @Test(expected = ServiceException.class)
    public void updateWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        int distance = 345;
        RouteDTO routeDTO = new RouteDTO(id, id, id, distance, 1);

        Mockito.doThrow(new DAOException(null)).when(routeDAO).update(anyObject());

        routeService.update(routeDTO);
    }

    @Test(expected = ServiceException.class)
    public void deleteWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        int distance = 345;
        RouteDTO routeDTO = new RouteDTO(id, id, id, distance, 1);

        Mockito.doThrow(new DAOException(null)).when(routeDAO).delete(anyLong());

        routeService.delete(routeDTO.getId());
    }
    @Test(expected = ServiceException.class)
    public void addWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        int distance = 123;
        RouteDTO routeDTO = new RouteDTO(id, id, id, distance, 1);

        Mockito.doThrow(new DAOException(null)).when(routeDAO).add(anyObject());

        routeService.add(routeDTO);
    }

    @Test
    public void getNumberOfRecordsTest() throws DAOException, ServiceException {
        int records = 43;

        Mockito.when(routeDAO.getNumberOfRecords()).thenReturn(records);

        int result = routeService.getNumberOfRecords();

        Assert.assertEquals(records, result);


    }

    @Test (expected = ServiceException.class)
    public void getNumberOfRecordsExceptionTest() throws DAOException, ServiceException {
        int records = 43;

        Mockito.when(routeDAO.getNumberOfRecords()).thenThrow(new DAOException(null));

        routeService.getNumberOfRecords();

    }

    @Test
    public void getNumberOfRecordsByCityFromTest() throws DAOException, ServiceException {
        City city = new City(45);
        int records = 13;

        Mockito.when(routeDAO.getNumberOfRecordsByCityFrom(city.getId())).thenReturn(records);

        int result = routeService.getNumberOfRecordsByCityFrom(city.getId());

        Assert.assertEquals(records, result);


    }

    @Test (expected = ServiceException.class)
    public void getNumberOfRecordsByCityFromExceptionTest() throws DAOException, ServiceException {
        City city = new City(45);
        int records = 13;

        Mockito.when(routeDAO.getNumberOfRecordsByCityFrom(city.getId())).thenThrow(new DAOException(null));

        routeService.getNumberOfRecordsByCityFrom(city.getId());

    }

    @Test
    public void getNumberOfRecordsByCityToTest() throws DAOException, ServiceException {
        City city = new City(45);
        int records = 12;

        Mockito.when(routeDAO.getNumberOfRecordsByCityTo(city.getId())).thenReturn(records);

        int result = routeService.getNumberOfRecordsByCityTo(city.getId());

        Assert.assertEquals(records, result);


    }

    @Test (expected = ServiceException.class)
    public void getNumberOfRecordsByCityToExceptionTest() throws DAOException, ServiceException {
        City city = new City(45);
        int records = 12;

        Mockito.when(routeDAO.getNumberOfRecordsByCityTo(city.getId())).thenThrow(new DAOException(null));

        routeService.getNumberOfRecordsByCityTo(city.getId());

    }





}
