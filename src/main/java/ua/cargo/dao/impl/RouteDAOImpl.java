package ua.cargo.dao.impl;

import ua.cargo.connection.DataSource;
import ua.cargo.dao.CityDAO;
import ua.cargo.dao.RouteDAO;
import ua.cargo.entities.City;
import ua.cargo.entities.Route;
import ua.cargo.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.cargo.dao.constants.RouteSQLQueries.*;
import static ua.cargo.dao.constants.SQLFields.*;

public class RouteDAOImpl implements RouteDAO {

    private CityDAO cityDAO;

    public RouteDAOImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public void add(Route route) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROUTE)) {
            setFieldsForRoute(route, preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Route> getAll() throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getAll(int offset, int records) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_PAGINATED)) {
            preparedStatement.setInt(1, records);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getAll(int offset, int records, String sortedValue, String order) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_PAGINATED_ORDERED)) {
            preparedStatement.setString(1, sortedValue);
            preparedStatement.setString(2, order);
            preparedStatement.setInt(3, records);
            preparedStatement.setInt(4, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public void update(Route route) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROUTE)) {
            preparedStatement.setInt(1, route.getDistance());
            preparedStatement.setLong(2, route.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROUTE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Route> getById(long routeId) throws DAOException {
        Route route = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTE_BY_ID)) {
            preparedStatement.setLong(1, routeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route = createRoute(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(route);
    }

    @Override
    public Optional<Route> getByCities(long cityFromId, long cityToId) throws DAOException {
        Route route = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTE_BY_CITIES)) {
            preparedStatement.setLong(1, cityFromId);
            preparedStatement.setLong(2, cityToId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route = createRoute(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(route);
    }

    @Override
    public Optional<Route> getByCities(long cityFromId, long cityToId, int offset, int records) throws DAOException {
        Route route = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTE_BY_CITIES_PAGINATED)) {
            preparedStatement.setLong(1, cityFromId);
            preparedStatement.setLong(2, cityToId);
            preparedStatement.setInt(3, records);
            preparedStatement.setInt(4, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route = createRoute(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(route);
    }

    @Override
    public Optional<Route> getByCities(long cityFromId, long cityToId, int offset, int records, String sortedValue, String order) throws DAOException {
        Route route = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTE_BY_CITIES_PAGINATED_ORDERED)) {
            preparedStatement.setLong(1, cityFromId);
            preparedStatement.setLong(2, cityToId);
            preparedStatement.setString(3, sortedValue);
            preparedStatement.setString(4, order);
            preparedStatement.setInt(5, records);
            preparedStatement.setInt(6, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                route = createRoute(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(route);
    }

    @Override
    public List<Route> getByCityFrom(long cityFromId) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_CITY_FROM)) {
            preparedStatement.setLong(1, cityFromId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getByCityFrom(long cityFromId, int offset, int records) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_CITY_FROM_PAGINATED)) {
            preparedStatement.setLong(1, cityFromId);
            preparedStatement.setInt(2, records);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getByCityFrom(long cityFromId, int offset, int records, String sortedValue, String order) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_CITY_FROM_PAGINATED_ORDERED)) {
            preparedStatement.setLong(1, cityFromId);
            preparedStatement.setString(2, sortedValue);
            preparedStatement.setString(3, order);
            preparedStatement.setInt(4, records);
            preparedStatement.setInt(5, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getByCityTo(long cityToId) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_CITY_TO)) {
            preparedStatement.setLong(1, cityToId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getByCityTo(long cityToId, int offset, int records) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_CITY_TO_PAGINATED)) {
            preparedStatement.setLong(1, cityToId);
            preparedStatement.setInt(2, records);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public List<Route> getByCityTo(long cityToId, int offset, int records, String sortedValue, String order) throws DAOException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTES_BY_CITY_TO_PAGINATED_ORDERED)) {
            preparedStatement.setLong(1, cityToId);
            preparedStatement.setString(2, sortedValue);
            preparedStatement.setString(3, order);
            preparedStatement.setInt(4, records);
            preparedStatement.setInt(5, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routes.add(createRoute(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return routes;
    }

    @Override
    public int getNumberOfRecords() throws DAOException {
        int numberOfRecords = 0;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_NUMBER_OF_RECORDS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfRecords = resultSet.getInt(NUMBER_OF_RECORDS);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    @Override
    public int getNumberOfRecordsByCityFrom(long cityFromId) throws DAOException {
        int numberOfRecords = 0;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_NUMBER_OF_RECORDS_BY_CITY_FROM)) {
            preparedStatement.setLong(1, cityFromId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfRecords = resultSet.getInt(NUMBER_OF_RECORDS);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    @Override
    public int getNumberOfRecordsByCityTo(long cityToId) throws DAOException {
        int numberOfRecords = 0;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_NUMBER_OF_RECORDS_BY_CITY_TO)) {
            preparedStatement.setLong(1, cityToId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfRecords = resultSet.getInt(NUMBER_OF_RECORDS);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return numberOfRecords;
    }

    private void setFieldsForRoute(Route route, PreparedStatement preparedStatement) throws SQLException {
        int k = 0;
        preparedStatement.setLong(++k, route.getCityFrom().getId());
        preparedStatement.setLong(++k, route.getCityTo().getId());
        preparedStatement.setInt(++k, route.getDistance());
        preparedStatement.setInt(++k, route.getTerms());
    }

    private Route createRoute(ResultSet resultSet) throws SQLException, DAOException {
        City cityFrom = cityDAO.getById(resultSet.getLong(CITY_FROM_ID_SQL)).orElse(null);
        City cityTo = cityDAO.getById(resultSet.getLong(CITY_TO_ID_SQL)).orElse(null);
        return new Route(resultSet.getLong(ID), cityFrom, cityTo, resultSet.getInt(DISTANCE), resultSet.getInt(TERMS));
    }
}
