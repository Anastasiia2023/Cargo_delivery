package ua.cargo.dao;

import ua.cargo.entities.Order;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.DAOException;

import java.util.List;

public interface OrderDAO extends EntityDAO <Order> {
    List<Order> getAllByUser(long userId, int offset, int records, String filterQuery) throws DAOException;
    List<Order> getAllByUser(long userId, int offset, int records, String sortedValue, String order, String filterQuery) throws DAOException;
    List<Order> getAll(String filterQuery) throws DAOException;
    List<Order> getAll(int offset, int records, String filterQuery) throws DAOException;
    List<Order> getAll(int offset, int records, String sortedValue, String order, String filterQuery) throws DAOException;
    int getNumberOfRecordsByUserId(long userId, String filterQuery) throws DAOException;
    int getNumberOfRecords(String filterQuery) throws DAOException;
    void updateInvoiced(long orderId) throws DAOException;
    void updatePayment(long orderId, Boolean waitingPayment, OrderStatus status) throws DAOException;
}


