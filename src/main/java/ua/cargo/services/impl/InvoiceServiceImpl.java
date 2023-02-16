package ua.cargo.services.impl;

import ua.cargo.dao.InvoiceDAO;
import ua.cargo.dto.InvoiceDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Invoice;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.NoSuchElementException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.InvoiceService;
import ua.cargo.services.OrderService;
import ua.cargo.services.UserService;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

import static ua.cargo.utils.ConvertorUtil.*;

public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceDAO invoiceDAO;
    private final PdfService pdfService;
    private final UserService userService;
    private final OrderService orderService;

    public InvoiceServiceImpl(InvoiceDAO invoiceDAO, PdfService pdfService, UserService userService, OrderService orderService) {
        this.invoiceDAO = invoiceDAO;
        this.pdfService = pdfService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public InvoiceDTO getById(long id) throws ServiceException {
        InvoiceDTO invoiceDTO;
        try {
            Invoice invoice = invoiceDAO.getById(id).orElseThrow(NoSuchElementException::new);
            invoiceDTO = convertInvoiceToDTO(invoice);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return invoiceDTO;
    }

    @Override
    public List<InvoiceDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public void update(InvoiceDTO entity) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    @Override
    public void add(InvoiceDTO invoiceDTO) throws ServiceException {
        Invoice invoice = convertDTOToInvoice(invoiceDTO);
        try {
            invoiceDAO.add(invoice);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(InvoiceDTO invoiceDTO, String locale) throws ServiceException {
        invoiceDTO.setDate(LocalDate.now());
        OrderDTO orderDTO = orderService.getById(invoiceDTO.getOrderId());
        invoiceDTO.setId(orderDTO.getId());
        pdfService.createInvoicePdf(orderDTO,
                userService.getById(invoiceDTO.getUserId()),
                invoiceDTO,
                locale);
        orderService.invoiceAdded(orderDTO);
        add(invoiceDTO);
    }

    @Override
    public ByteArrayOutputStream getPDFInvoice(InvoiceDTO invoiceDTO) throws ServiceException {
        Invoice invoice;
        try {
            invoice = invoiceDAO.getById(invoiceDTO.getOrderId()).orElse(null);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return pdfService.readInvoicePdf(invoice.getPath());
    }

    @Override
    public ByteArrayOutputStream getPDFInvoice(InvoiceDTO invoiceDTO, UserDTO userDTO) throws ServiceException {
        validateUser(invoiceDTO.getUserId(), userDTO.getId());
        OrderDTO orderDTO = orderService.getById(invoiceDTO.getOrderId());
        if (orderDTO.getStatus().equals(OrderStatus.PROCESSED.getValue())) {
            orderService.updatePayment(invoiceDTO.getOrderId(), Boolean.TRUE, OrderStatus.PROCESSED);
        }
        return getPDFInvoice(invoiceDTO);
    }

    private void validateUser(long invoiceUserId, long currentUserId) throws ServiceException {
        if (invoiceUserId != currentUserId) {
            throw new ServiceException("This invoice doesn't belong to user");
        }
    }
}
