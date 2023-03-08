package ua.cargo.dao.impl;

import ua.cargo.connection.DataSource;
import ua.cargo.dao.OrderDAO;
import ua.cargo.dao.RouteDAO;
import ua.cargo.dao.UserDAO;
import ua.cargo.dao.constants.SQLFields;
import ua.cargo.entities.Order;
import ua.cargo.entities.Route;
import ua.cargo.entities.User;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.cargo.dao.constants.OrderSQLQueries.*;
import static ua.cargo.dao.constants.OrderSQLQueries.GET_NUMBER_OF_RECORDS;


public class OrderDAOImpl implements OrderDAO {

    private RouteDAO routeDAO;
    private UserDAO userDAO;

    public OrderDAOImpl(RouteDAO routeDAO, UserDAO userDAO) {
        this.routeDAO = routeDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void add(Order order) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            setFieldsForOrder(order, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Order> getById(long id) throws DAOException {
        Order order = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = createOrder(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> getAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public void update(Order order) throws DAOException {
    }

    @Override
    public void delete(long id) throws DAOException {

    }

    @Override
    public List<Order> getAllByUser(long userId, int offset, int records, String filterQuery) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_ORDERS_BY_USER_PAGINATED, filterQuery))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, records);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAllByUser(long userId, int offset, int records, String sortedValue, String order, String filterQuery) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_ORDERS_BY_USER_PAGINATED_ORDERED, filterQuery))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, sortedValue);
            preparedStatement.setString(3, order);
            preparedStatement.setInt(4, records);
            preparedStatement.setInt(5, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll(String filterQuery) throws DAOException {
        List<Order> orders = new ArrayList<>();
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
            if (filterQuery.contains("city")) {
                query = JOIN_ROUTE + query;
            }
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_ORDERS, query))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll(int offset, int records, String filterQuery) throws DAOException {
        List<Order> orders = new ArrayList<>();
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
            if (filterQuery.contains("city")) {
                query = JOIN_ROUTE + query;
            }
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_ORDERS_PAGINATED, query))) {
            preparedStatement.setInt(1, records);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll(int offset, int records, String sortedValue, String order, String filterQuery) throws DAOException {
        List<Order> orders = new ArrayList<>();
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
            if (filterQuery.contains("city")) {
                query = JOIN_ROUTE + query;
            }
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_ORDERS_PAGINATED_ORDERED, query))) {
            preparedStatement.setString(1, sortedValue);
            preparedStatement.setString(2, order);
            preparedStatement.setInt(3, records);
            preparedStatement.setInt(4, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public int getNumberOfRecordsByUserId(long userId , String filterQuery) throws DAOException {
        int numberOfRecords = 0;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_NUMBER_OF_RECORDS_BY_USER_ID, filterQuery))) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfRecords = resultSet.getInt(SQLFields.NUMBER_OF_RECORDS);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    @Override
    public int getNumberOfRecords(String filterQuery) throws DAOException {
        int numberOfRecords = 0;
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
            if (filterQuery.contains("city")) {
                query = JOIN_ROUTE + query;
            }
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_NUMBER_OF_RECORDS, query))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfRecords = resultSet.getInt(SQLFields.NUMBER_OF_RECORDS);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    @Override
    public void updateInvoiced(long orderId) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_INVOICE)) {
            preparedStatement.setBoolean(1, Boolean.TRUE);
            preparedStatement.setString(2, OrderStatus.PROCESSED.getValue());
            preparedStatement.setLong(3, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updatePayment(long orderId, Boolean waitingPayment, OrderStatus status) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENT)) {
            preparedStatement.setBoolean(1, waitingPayment);
            preparedStatement.setString(2, status.getValue());
            preparedStatement.setLong(3, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private void setFieldsForOrder(Order order, PreparedStatement preparedStatement) throws SQLException {
        int k = 0;
        preparedStatement.setString(++k, order.getStatus().getValue());
        preparedStatement.setObject(++k, order.getEstimatedDeliveryDate());
        preparedStatement.setObject(++k, order.getCreationDate());
        preparedStatement.setDouble(++k, order.getWeight());
        preparedStatement.setDouble(++k, order.getVolume());
        preparedStatement.setLong(++k, order.getRoute().getId());
        preparedStatement.setDouble(++k, order.getPrice());
        preparedStatement.setLong(++k,order.getUser().getId());
        preparedStatement.setBoolean(++k, order.isInvoiced());
        preparedStatement.setBoolean(++k, order.isWaitingPayment());
    }

    private Order createOrder(ResultSet resultSet) throws SQLException, DAOException {
        Route route = routeDAO.getById(resultSet.getLong(SQLFields.ROUTE_ID)).orElse(null);
        User user = userDAO.getById(resultSet.getLong(SQLFields.USER_ID)).orElse(null);
        return new Order(resultSet.getLong(SQLFields.ID),
                OrderStatus.getOrderStatus(resultSet.getString(SQLFields.ORDER_STATUSES)),
                LocalDate.parse(resultSet.getString(SQLFields.DELIVERY_DATE)),
                LocalDate.parse(resultSet.getString(SQLFields.CREATION_DATE)),
                resultSet.getDouble(SQLFields.WEIGHT),
                resultSet.getDouble(SQLFields.VOLUME),
                route,
                resultSet.getDouble(SQLFields.PRICE),
                user,
                resultSet.getBoolean(SQLFields.INVOICED),
                resultSet.getBoolean(SQLFields.WAITING_PAYMENT));
    }
}
