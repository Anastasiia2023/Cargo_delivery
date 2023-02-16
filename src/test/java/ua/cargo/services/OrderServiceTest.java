package ua.cargo.services;

import ua.cargo.dao.CityDAO;
import ua.cargo.dao.OrderDAO;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.*;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.cargo.services.impl.CalculateServiceImpl;
import ua.cargo.services.impl.CityServiceImpl;
import ua.cargo.services.impl.OrderServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.*;

import static ua.cargo.entities.enums.OrderStatus.PLACED;
import static ua.cargo.entities.enums.PaymentStatus.WAITING_FOR_PAYMENT;
import static ua.cargo.entities.enums.PaymentStatus.WAITING_INVOICE;
import static ua.cargo.entities.enums.PaymentType.BANK_TRANSFER;
import static ua.cargo.entities.enums.PaymentType.CREDIT_CARD;
import static ua.cargo.utils.ConvertorUtil.*;
import static ua.cargo.utils.PaginationUtil.isOrdered;

public class OrderServiceTest {


    private OrderDAO orderDAO;
    private CalculateService calculateService;
    private OrderService orderService;

    @Before
    public void init() {
        orderDAO = Mockito.mock(OrderDAO.class);
        calculateService = Mockito.mock(CalculateService.class);
        orderService = new OrderServiceImpl(orderDAO, calculateService);
    }
    @Test
    public void getByIdTest() throws DAOException, ServiceException {
        long id = 1L;
        Payment payment = new Payment(400, WAITING_INVOICE,CREDIT_CARD );
        City cityFrom = new City(id, "Milan");
        City cityTo = new City(id, "Rome");
        Route route = new Route(id, cityFrom, cityTo, 575, 2);
        User user = new User (id);
        Order order = new Order(PLACED, payment, LocalDate.now(), LocalDate.now(), 20, 300, route, "", "", 650, user);
        Optional<Order> orderOptional=Optional.of(order);

        Mockito.when(orderDAO.getById(anyLong())).thenReturn(orderOptional);

        OrderDTO result = orderService.getById(id);

        Assert.assertEquals(order.getId(), result.getId());
        Assert.assertEquals(order.getRoute().getId(), result.getRouteId());
        Assert.assertEquals(order.getStatus().getValue(), result.getStatus());
        Assert.assertEquals(order.getCreationDate(), result.getCreationDate());
        Assert.assertEquals(order.getEstimatedDeliveryDate(), result.getEstimatedDeliveryDate());
        Assert.assertEquals(order.getUser().getId(), result.getUserId());
        Assert.assertEquals(order.getWeight(), result.getWeight(), 0.00000001);
        Assert.assertEquals(order.getPrice(), result.getPrice(), 0.00000001);
        Assert.assertEquals(order.getVolume(), result.getVolume(), 0.00000001);
    }
    @Test(expected = ServiceException.class)
    public void  getByIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(orderDAO.getById(anyLong())).thenThrow(new DAOException(null));

        orderService.getById(id);

    }

    @Test(expected = ServiceException.class)
    public void addWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;

        OrderDTO orderDTO = new OrderDTO(30, 50, id, 500, "Documents", id);

        Mockito.doThrow(new DAOException(null)).when(orderDAO).add(anyObject());

        orderService.add(orderDTO);
    }

    @Test
    public void getAllByUserTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);

        Order order = new Order(id, PLACED, LocalDate.now(), LocalDate.now(), 20, 40, route, 500, user, false, false);

        List<Order> ordersList = List.of(order);

        Mockito.when(orderDAO.getAllByUser(anyLong(), anyInt(), anyInt(), any())).thenReturn(ordersList);

        List<OrderDTO> result = orderService.getAllByUser(user.getId(), pagination, "");

        Assert.assertEquals(order.getId(), result.get(0).getId());
        Assert.assertEquals(order.getStatus().getValue(), result.get(0).getStatus());
        Assert.assertEquals(order.getRoute().getId(), result.get(0).getRouteId());
        Assert.assertEquals(order.getUser().getId(), result.get(0).getUserId());

    }

    @Test
    public void getAllByUserIsOrderedTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);

        Order order = new Order(id, PLACED, LocalDate.now(), LocalDate.now(), 20, 40, route, 500, user, false, false);

        List<Order> ordersList = List.of(order);

        Mockito.when(orderDAO.getAllByUser(anyLong(), anyInt(), anyInt(), anyString(), any(), anyString())).thenReturn(ordersList);

        List<OrderDTO> result = orderService.getAllByUser(user.getId(), pagination, "");

        Assert.assertEquals(order.getId(), result.get(0).getId());
        Assert.assertEquals(order.getStatus().getValue(), result.get(0).getStatus());
        Assert.assertEquals(order.getRoute().getId(), result.get(0).getRouteId());
        Assert.assertEquals(order.getUser().getId(), result.get(0).getUserId());

    }

    @Test (expected = ServiceException.class)
    public void getAllByUserExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);
        String filterQuery = "TestQuery";

        Mockito.when(orderDAO.getAllByUser(anyLong(), anyInt(), anyInt(), any())).thenThrow(new DAOException(null));

        orderService.getAllByUser(user.getId(), pagination, filterQuery);



    }

    @Test
    public void getAllTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);

        Order order = new Order(id, OrderStatus.PROCESSED, LocalDate.now(), LocalDate.now(), 20, 40, route, 500, user, false, false);

        List<Order> ordersList = List.of(order);

        Mockito.when(orderDAO.getAll(anyInt(), anyInt(), any())).thenReturn(ordersList);

        List<OrderDTO> result = orderService.getAll(pagination, "");

        Assert.assertEquals(order.getId(), result.get(0).getId());
        Assert.assertEquals(order.getStatus().getValue(), result.get(0).getStatus());
        Assert.assertEquals(order.getRoute().getId(), result.get(0).getRouteId());
        Assert.assertEquals(order.getPrice(), result.get(0).getPrice(), 0.000001);
        Assert.assertEquals(order.getWeight(), result.get(0).getWeight(), 0.000001);


    }

    @Test
    public void getAllIsOrderedTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(40);
        pagination.setSortField("");
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);

        Order order = new Order(id, OrderStatus.PROCESSED, LocalDate.now(), LocalDate.now(), 20, 40, route, 500, user, false, false);

        List<Order> ordersList = List.of(order);

        Mockito.when(orderDAO.getAll(anyInt(), anyInt(), anyString(),any(), anyString())).thenReturn(ordersList);

        List<OrderDTO> result = orderService.getAll(pagination, "");

        Assert.assertEquals(order.getId(), result.get(0).getId());
        Assert.assertEquals(order.getStatus().getValue(), result.get(0).getStatus());
        Assert.assertEquals(order.getRoute().getId(), result.get(0).getRouteId());
        Assert.assertEquals(order.getPrice(), result.get(0).getPrice(), 0.000001);
        Assert.assertEquals(order.getWeight(), result.get(0).getWeight(), 0.000001);


    }

    @Test (expected = ServiceException.class)
    public void getAllExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(20);
        pagination.setRecords(50);
        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);
        String filterQuery = "TestQuery1";

        Mockito.when(orderDAO.getAll(anyInt(), anyInt(), any())).thenThrow(new DAOException(null));

        orderService.getAll(pagination, filterQuery);



    }

    @Test
    public void getAllSecondTest() throws DAOException, ServiceException {
        long id = 1L;

        Route route = new Route(id);
        route.setCityFrom(new City(id));
        route.setCityTo(new City(id));
        User user = new User(id);

        Order order = new Order(id, OrderStatus.DELIVERED, LocalDate.now(), LocalDate.now(), 20, 40, route, 500, user, false, false);

        List<Order> ordersList = List.of(order);

        Mockito.when(orderDAO.getAll(any())).thenReturn(ordersList);

        List<OrderDTO> result = orderService.getAll("");

        Assert.assertEquals(order.getId(), result.get(0).getId());
        Assert.assertEquals(order.getStatus().getValue(), result.get(0).getStatus());
        Assert.assertEquals(order.getRoute().getId(), result.get(0).getRouteId());
        Assert.assertEquals(order.getPrice(), result.get(0).getPrice(), 0.000001);
        Assert.assertEquals(order.getVolume(), result.get(0).getVolume(), 0.000001);

    }

    @Test (expected = ServiceException.class)
    public void getAllSecondExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        String filterQuery = "TestQuery14";

        Mockito.when(orderDAO.getAll( any())).thenThrow(new DAOException(null));

        orderService.getAll( filterQuery);

    }


    @Test
    public void getNumberOfRecordsByUserIdTest() throws DAOException, ServiceException {
        long id = 1L;
        int records = 5;

        Mockito.when(orderDAO.getNumberOfRecordsByUserId(anyLong(), any())).thenReturn(records);

        int result = orderService.getNumberOfRecordsByUserId(id, "");

        Assert.assertEquals(records, result);


    }

    @Test
    public void getNumberOfRecordsByUserIdNotEmptyFilterTest() throws DAOException, ServiceException {
        long id = 1L;
        String filterQuery = "TestQuery";
        int records = 10;

        Mockito.when(orderDAO.getNumberOfRecordsByUserId(anyLong(), any())).thenReturn(records);

        int result = orderService.getNumberOfRecordsByUserId(id, filterQuery);

        Assert.assertEquals(records, result);


    }

    @Test (expected = ServiceException.class)
    public void getNumberOfRecordsByUserIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        String filterQuery = "";


        Mockito.when(orderDAO.getNumberOfRecordsByUserId(anyLong(), any())).thenThrow(new DAOException(null));

         orderService.getNumberOfRecordsByUserId(id, filterQuery);

    }

    @Test
    public void getNumberOfRecordsNotEmptyFilterTest() throws DAOException, ServiceException {

        String filterQuery = "TestQuery";
        int records = 35;

        Mockito.when(orderDAO.getNumberOfRecords( any())).thenReturn(records);

        int result = orderService.getNumberOfRecords(filterQuery);

        Assert.assertEquals(records, result);


    }

    @Test
    public void getNumberOfRecordsTest() throws DAOException, ServiceException {

        int records = 43;

        Mockito.when(orderDAO.getNumberOfRecords( any())).thenReturn(records);

        int result = orderService.getNumberOfRecords("");

        Assert.assertEquals(records, result);


    }

    @Test (expected = ServiceException.class)
    public void getNumberOfRecordsExceptionTest() throws DAOException, ServiceException {
        String filterQuery = "";

        Mockito.when(orderDAO.getNumberOfRecords( any())).thenThrow(new DAOException(null));

        orderService.getNumberOfRecords(filterQuery);

    }

    @Test(expected = ServiceException.class)
    public void invoiceAddedExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        OrderDTO orderDTO = new OrderDTO(30, 50, id, 500, "Documents", id);

        Mockito.doThrow(new DAOException(null)).when( orderDAO).updateInvoiced(orderDTO.getId());

        orderService.invoiceAdded(orderDTO);

    }

    @Test(expected = ServiceException.class)
    public void updatePaymentExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        Payment payment = new Payment(400, WAITING_INVOICE,CREDIT_CARD );
        City cityFrom = new City(id, "Milan");
        City cityTo = new City(id, "Rome");
        Route route = new Route(id, cityFrom, cityTo, 575, 2);
        User user = new User (id);
        Order order = new Order(PLACED, payment, LocalDate.now(), LocalDate.now(), 20, 300, route, "", "", 650, user);

        Mockito.doThrow(new DAOException(null)).when(orderDAO).updatePayment(anyLong(), anyBoolean(), any());

        orderService.updatePayment(order.getId(), Boolean.TRUE, order.getStatus());

    }

    @Test(expected = ServiceException.class)
    public void completePaymentExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        Payment payment = new Payment(400, WAITING_INVOICE,CREDIT_CARD );
        City cityFrom = new City(id, "Milan");
        City cityTo = new City(id, "Rome");
        Route route = new Route(id, cityFrom, cityTo, 575, 2);
        User user = new User (id);
        Order order = new Order(PLACED, payment, LocalDate.now(), LocalDate.now(), 20, 300, route, "", "", 650, user);

        Mockito.doThrow(new DAOException(null)).when(orderDAO).updatePayment(anyLong(), anyBoolean(), any());

        orderService.updatePayment(order.getId(), Boolean.TRUE, order.getStatus());

    }

    @Test
    public void CreateOrderTest() throws DAOException, ServiceException {
        double price = 125;
        OrderDTO orderDTO = new OrderDTO(2, 2, 1, 10, "Parcel", 1);

        Mockito.doNothing().when(orderDAO).add(anyObject());
        Mockito.when(calculateService.calculateOrder(any(), anyBoolean())).thenReturn(price);

        orderService.createOrder(orderDTO, Boolean.TRUE);

        Assert.assertEquals(price, orderDTO.getPrice(), 0.000001);
        Assert.assertNotNull(orderDTO.getCreationDate());
        Assert.assertNotNull(orderDTO.getStatus());
    }

}
