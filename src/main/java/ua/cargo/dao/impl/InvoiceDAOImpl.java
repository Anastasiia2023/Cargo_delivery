package ua.cargo.dao.impl;

import ua.cargo.connection.DataSource;
import ua.cargo.dao.InvoiceDAO;
import ua.cargo.dao.OrderDAO;
import ua.cargo.dao.UserDAO;
import ua.cargo.dao.constants.SQLFields;
import ua.cargo.entities.*;
import ua.cargo.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ua.cargo.dao.constants.InvoiceSQLQueries.ADD_INVOICE;
import static ua.cargo.dao.constants.InvoiceSQLQueries.GET_INVOICE_BY_ID;
import static ua.cargo.dao.constants.RouteSQLQueries.GET_ROUTE_BY_ID;
import static ua.cargo.dao.constants.SQLFields.*;

public class InvoiceDAOImpl implements InvoiceDAO {
    private UserDAO userDAO;
    private OrderDAO orderDAO;

    public InvoiceDAOImpl(UserDAO userDAO, OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void add(Invoice invoice) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_INVOICE)) {
            preparedStatement.setObject(1, invoice.getId());
            preparedStatement.setObject(2, invoice.getDate());
            preparedStatement.setString(3, invoice.getPath());
            preparedStatement.setLong(4, invoice.getOrder().getId());
            preparedStatement.setLong(5, invoice.getUser().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Invoice> getById(long id) throws DAOException {
        Invoice invoice = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                invoice = createInvoice(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(invoice);
    }

    @Override
    public List<Invoice> getAll() throws DAOException {
        return null;
    }

    @Override
    public void update(Invoice invoice) throws DAOException {

    }

    @Override
    public void delete(long id) throws DAOException {

    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException, DAOException {
        Order order = orderDAO.getById(resultSet.getLong(ORDER_ID)).orElse(null);
        User user = userDAO.getById(resultSet.getLong(USER_ID)).orElse(null);
        return new Invoice(resultSet.getLong(ID),
                LocalDate.parse(resultSet.getString(DATE)),
                resultSet.getString(PATH),
                user,
                order);
    }
}
