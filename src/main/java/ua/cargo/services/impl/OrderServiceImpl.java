package ua.cargo.services.impl;

import ua.cargo.dao.OrderDAO;
import ua.cargo.dto.OrderDTO;

import ua.cargo.entities.Order;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.NoSuchElementException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CalculateService;
import ua.cargo.services.OrderService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ConvertorUtil.*;
import static ua.cargo.utils.PaginationUtil.isOrdered;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final CalculateService calculateService;

    public OrderServiceImpl(OrderDAO orderDAO, CalculateService calculateService) {
        this.orderDAO = orderDAO;
        this.calculateService = calculateService;
    }

    @Override
    public OrderDTO getById(long id) throws ServiceException {
        OrderDTO orderDTO;
        try {
            Order order = orderDAO.getById(id).orElseThrow(NoSuchElementException::new);
            orderDTO = convertOrderToDTO(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public void update(OrderDTO order) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    @Override
    public void add(OrderDTO orderDTO) throws ServiceException {
        Order order = convertDTOToOrder(orderDTO);
        try {
            orderDAO.add(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createOrder(OrderDTO orderDTO, boolean packageService) throws ServiceException {
        orderDTO.setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        orderDTO.setPrice(calculateService.calculateOrder(orderDTO, packageService));
        orderDTO.setStatus(OrderStatus.PLACED.getValue());
        orderDTO.setInvoiced(Boolean.FALSE);
        add(orderDTO);
    }

    @Override
    public List<OrderDTO> getAllByUser(long userId, Pagination pagination, String filterQuery) throws ServiceException {
        List<OrderDTO> orders = new ArrayList<>();
        if (filterQuery.length() > 0) {
            filterQuery = "AND " + filterQuery;
        }
        try {
            List<Order> orderList;
            if (isOrdered(pagination)) {
                orderList = orderDAO.getAllByUser(userId, pagination.getOffset(), pagination.getRecords(), pagination.getSortField(), pagination.getOrder(), filterQuery);
            } else {
                orderList = orderDAO.getAllByUser(userId, pagination.getOffset(), pagination.getRecords(), filterQuery);
            }
            orderList.forEach(order -> orders.add(convertOrderToDTO(order)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<OrderDTO> getAll(Pagination pagination, String filterQuery) throws ServiceException {
        List<OrderDTO> orders = new ArrayList<>();
        try {
            List<Order> orderList;
            if (isOrdered(pagination)) {
                orderList = orderDAO.getAll(pagination.getOffset(), pagination.getRecords(), pagination.getSortField(), pagination.getOrder(), filterQuery);
            } else {
                orderList = orderDAO.getAll(pagination.getOffset(), pagination.getRecords(), filterQuery);
            }
            orderList.forEach(order -> orders.add(convertOrderToDTO(order)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<OrderDTO> getAll(String filterQuery) throws ServiceException {
        List<OrderDTO> orders = new ArrayList<>();
        try {
            List<Order> orderList = orderDAO.getAll(filterQuery);
            orderList.forEach(order -> orders.add(convertOrderToDTO(order)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public int getNumberOfRecordsByUserId(long userId, String filterQuery) throws ServiceException {
        int records;
        if (filterQuery.length() > 0) {
            filterQuery = "AND " + filterQuery;
        }
        try {
            records = orderDAO.getNumberOfRecordsByUserId(userId, filterQuery);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return records;
    }

    @Override
    public int getNumberOfRecords(String filterQuery) throws ServiceException {
        int records;
        try {
            records = orderDAO.getNumberOfRecords(filterQuery);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return records;
    }

    @Override
    public void invoiceAdded(OrderDTO order) throws ServiceException {
        try {
            orderDAO.updateInvoiced(order.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updatePayment(long orderId, Boolean waitingPayment, OrderStatus status) throws ServiceException {
        try {
            orderDAO.updatePayment(orderId, waitingPayment, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void completePayment(long orderId) throws ServiceException {
        updatePayment(orderId, Boolean.FALSE, OrderStatus.PAYED);
    }

    @Override
    public void confirmPayment(long orderId) throws ServiceException {
        updatePayment(orderId, Boolean.FALSE, OrderStatus.WAITING_CARGO);
    }
}
