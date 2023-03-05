package ua.cargo.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.dao.InvoiceDAO;
import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.*;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.impl.InvoiceServiceImpl;
import ua.cargo.services.impl.PdfService;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Matchers.*;
import static ua.cargo.entities.enums.OrderStatus.PLACED;
import static ua.cargo.entities.enums.PaymentStatus.WAITING_INVOICE;
import static ua.cargo.entities.enums.PaymentType.CREDIT_CARD;

public class InvoiceServiceTest {
    private InvoiceDAO invoiceDAO;
    private PdfService pdfService;
    private UserService userService;
    private OrderService orderService;
    private InvoiceService invoiceService;

    @Before
    public void init() {
        invoiceDAO = Mockito.mock(InvoiceDAO.class);
        pdfService = Mockito.mock(PdfService.class);
        userService = Mockito.mock(UserService.class);
        orderService = Mockito.mock(OrderService.class);
        invoiceService = new InvoiceServiceImpl(invoiceDAO, pdfService, userService, orderService);
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
        String path = "";
        Invoice invoice = new Invoice(id, LocalDate.now(), path, user, order);
        Optional<Invoice> invoiceOptional=Optional.of(invoice);

        Mockito.when(invoiceDAO.getById(anyLong())).thenReturn(invoiceOptional);

        InvoiceDTO result = invoiceService.getById(id);

        Assert.assertEquals(invoice.getId(), result.getId());

    }

    @Test(expected = ServiceException.class)
    public void  getByIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(invoiceDAO.getById(anyLong())).thenThrow(new DAOException(null));

        invoiceService.getById(id);

    }

    @Test(expected = ServiceException.class)
    public void addWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        String path = "";
        InvoiceDTO invoiceDTO = new InvoiceDTO(id,LocalDate.now(), path, id, id );

        Mockito.doThrow(new DAOException(null)).when(invoiceDAO).add(anyObject());

        invoiceService.add(invoiceDTO);
    }

    @Test
    public void addWithLocaleTest() throws ServiceException, DAOException {
        long id = 1L;
        String locale = "en";
        String path = "";
        OrderDTO orderDTO = new OrderDTO(30, 50, id, 500, "Documents", id);
        orderDTO.setId(id);
        InvoiceDTO invoiceDTO = new InvoiceDTO(orderDTO.getId(),LocalDate.now(), path, id, id );
        UserDTO userDTO = new UserDTO(id, "Nini", "Lolo", "ldfefe3","ekdfirjiv@gfds.com" );

        Mockito.doNothing().when(invoiceDAO).add(anyObject());
        Mockito.when(orderService.getById(anyLong())).thenReturn(orderDTO);
        Mockito.when(userService.getById(anyLong())).thenReturn(userDTO);
        Mockito.doNothing().when(pdfService).createInvoicePdf(anyObject(), anyObject(), anyObject(), anyString());
        Mockito.doNothing().when(orderService).invoiceAdded(anyObject());

        invoiceService.add(invoiceDTO,locale);
    }

    @Test
    public void getPDFInvoiceTest()throws ServiceException, DAOException {
        long id = 1L;
        Payment payment = new Payment(400, WAITING_INVOICE,CREDIT_CARD );
        City cityFrom = new City(id, "Milan");
        City cityTo = new City(id, "Rome");
        Route route = new Route(id, cityFrom, cityTo, 575, 2);
        User user = new User (id);
        Order order = new Order(PLACED, payment, LocalDate.now(), LocalDate.now(), 20, 300, route, "", "", 650, user);
        order.setId(id);
        String path = "";
        Invoice invoice = new Invoice(id, LocalDate.now(), path, user, order);
        InvoiceDTO invoiceDTO = new InvoiceDTO(id,LocalDate.now(), path, id, id );
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Mockito.when(invoiceDAO.getById(anyLong())).thenReturn(Optional.of(invoice));
        Mockito.when(pdfService.readInvoicePdf(anyString())).thenReturn(byteArrayOutputStream);

        ByteArrayOutputStream result = invoiceService.getPDFInvoice(invoiceDTO);

        Assert.assertEquals(byteArrayOutputStream, result);
    }
    @Test(expected = ServiceException.class)
    public void  getPDFInvoiceExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        String path = "";
        InvoiceDTO invoiceDTO = new InvoiceDTO(id,LocalDate.now(), path, id, id );

        Mockito.when(invoiceDAO.getById(anyLong())).thenThrow(new DAOException(null));

        invoiceService.getPDFInvoice(invoiceDTO);

    }
    @Test
    public void getPDFInvoiceWithUserTest()throws ServiceException, DAOException {
        long id = 1L;
        Payment payment = new Payment(400, WAITING_INVOICE,CREDIT_CARD );
        City cityFrom = new City(id, "Milan");
        City cityTo = new City(id, "Rome");
        Route route = new Route(id, cityFrom, cityTo, 575, 2);
        User user = new User (id);
        Order order = new Order(PLACED, payment, LocalDate.now(), LocalDate.now(), 20, 300, route, "", "", 650, user);
        order.setId(id);
        String path = "";
        Invoice invoice = new Invoice(id, LocalDate.now(), path, user, order);
        InvoiceDTO invoiceDTO = new InvoiceDTO(id,LocalDate.now(), path, id, id );
        UserDTO userDTO = new UserDTO(id, "Name", "Surname", "email.com", "+38054326", "23/02/1997");
        OrderDTO orderDTO = new OrderDTO(30, 50, id, 500, "Documents", id);
        orderDTO.setStatus(OrderStatus.PROCESSED.getValue());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Mockito.when(invoiceDAO.getById(anyLong())).thenReturn(Optional.of(invoice));
        Mockito.when(orderService.getById(anyLong())).thenReturn(orderDTO);
        Mockito.doNothing().when(orderService).updatePayment(anyLong(),anyBoolean(),any());
        Mockito.when(pdfService.readInvoicePdf(anyString())).thenReturn(byteArrayOutputStream);

        ByteArrayOutputStream result = invoiceService.getPDFInvoice(invoiceDTO, userDTO);

        Assert.assertEquals(byteArrayOutputStream, result);
    }




}
