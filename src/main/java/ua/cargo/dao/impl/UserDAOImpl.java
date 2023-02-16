package ua.cargo.dao.impl;


import ua.cargo.connection.DataSource;
import ua.cargo.dao.UserDAO;
import ua.cargo.dao.constants.SQLFields;
import ua.cargo.entities.Order;
import ua.cargo.entities.User;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.cargo.dao.constants.UserSQLQueries.*;
import static ua.cargo.entities.enums.Role.getRole;


public class UserDAOImpl implements UserDAO {

    @Override
    public void add(User user) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            setStatementFieldsForAddMethod(user, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<User> getById(long userId) throws DAOException {
        User user = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            int k = 0;
            preparedStatement.setLong(++k, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getByEmail(String email) throws DAOException {
        User user = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            int k = 0;
            preparedStatement.setString(++k, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUser(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> getSorted(String query) throws DAOException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_SORTED, query))) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUser(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public int getNumberOfOrders(String filter) throws DAOException {
        int numberOfRecords = 0;
        String query = String.format(GET_NUMBER_OF_RECORDS, filter);
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfRecords = resultSet.getInt(SQLFields.NUMBER_OF_RECORDS);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }


    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            setStatementFieldsForUpdateMethod(user, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updatePassword(User user) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            int k = 0;
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setLong(++k, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateRole(long id, Role role) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE)) {
            int k = 0;
            preparedStatement.setInt(++k, role.getValue());
            preparedStatement.setLong(++k, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int getNumberOfRecords(String filterQuery) throws DAOException {
        int numberOfRecords = 0;
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
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
    public List<User> getAll(int offset, int records, String filterQuery) throws DAOException {
        List<User> users = new ArrayList<>();
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_USERS_PAGINATED, query))) {
            preparedStatement.setInt(1, records);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> getAll(int offset, int records, String sortedValue, String order, String filterQuery) throws DAOException {
        List<User> users = new ArrayList<>();
        String query = "";
        if (filterQuery.length() > 0) {
            query += "WHERE " + filterQuery;
        }
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(GET_USERS_PAGINATED_ORDERED, query))) {
            preparedStatement.setString(1, sortedValue);
            preparedStatement.setString(2, order);
            preparedStatement.setInt(3, records);
            preparedStatement.setInt(4, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public void delete(long userId) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            int k = 0;
            preparedStatement.setLong(++k, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getInt(SQLFields.ID), resultSet.getString(SQLFields.NAME), resultSet.getString(SQLFields.SURNAME), resultSet.getString(SQLFields.PASSWORD), resultSet.getString(SQLFields.EMAIL), resultSet.getString(SQLFields.PHONE), getRole(resultSet.getInt(SQLFields.ROLE)));
        user.setId(resultSet.getInt(SQLFields.ID));
        return user;
    }

    private void setStatementFieldsForAddMethod(User user, PreparedStatement preparedStatement) throws SQLException {
        int k = 0;
        preparedStatement.setString(++k, user.getEmail());
        preparedStatement.setString(++k, user.getPassword());
        preparedStatement.setString(++k, user.getName());
        preparedStatement.setString(++k, user.getSurname());
        preparedStatement.setString(++k, user.getPhone());
        preparedStatement.setInt(++k, user.getRole().getValue());
    }

    private static void setStatementFieldsForUpdateMethod(User user, PreparedStatement preparedStatement)
            throws SQLException {
        int k = 0;
        preparedStatement.setString(++k, user.getEmail());
        preparedStatement.setString(++k, user.getName());
        preparedStatement.setString(++k, user.getSurname());
        preparedStatement.setString(++k, user.getPhone());
        preparedStatement.setLong(++k, user.getId());
    }
}