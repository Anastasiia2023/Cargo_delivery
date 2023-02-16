package ua.cargo.services;

import ua.cargo.dto.OrderDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.ServiceException;

import java.util.List;

public interface OrderService extends CargoService<OrderDTO>{
    void createOrder(OrderDTO order, boolean packageService) throws ServiceException;
    List<OrderDTO> getAllByUser(long userId, Pagination pagination, String filterQuery) throws ServiceException;

    List<OrderDTO> getAll(Pagination pagination, String filterQuery) throws ServiceException;

    List<OrderDTO> getAll(String filterQuery) throws ServiceException;
    int getNumberOfRecordsByUserId(long userId, String filterQuery) throws ServiceException;

    int getNumberOfRecords(String filterQuery) throws ServiceException;

    void invoiceAdded(OrderDTO order) throws ServiceException;
    void updatePayment(long orderId, Boolean waitingPayment, OrderStatus status) throws ServiceException;
    void completePayment(long orderId) throws ServiceException;
    void confirmPayment(long orderId) throws ServiceException;
}