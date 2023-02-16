package ua.cargo.dao.impl;

import ua.cargo.connection.DataSource;
import ua.cargo.dao.CityDAO;
import ua.cargo.dao.constants.SQLFields;
import ua.cargo.entities.City;
import ua.cargo.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.cargo.dao.constants.CitySQLQueries.*;


public class CityDAOImpl implements CityDAO {
    @Override
    public void add(City city) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CITY)) {
            preparedStatement.setString(1, city.getCity());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Optional<City> getById(long cityId) throws DAOException {
        City city = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITY_BY_ID)) {
            preparedStatement.setLong(1, cityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city = new City(resultSet.getLong(SQLFields.ID), resultSet.getString(SQLFields.CITY_NAME));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return  Optional.ofNullable(city);
    }

    @Override
    public List<City> getAll() throws DAOException {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getLong(SQLFields.ID), resultSet.getString(SQLFields.CITY_NAME)));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cities;
    }

    @Override
    public void update(City city) throws DAOException {
        return;

    }

    @Override
    public void delete(long id) throws DAOException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public Optional<City> getByName(String name) throws DAOException {
        City city = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITY_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city = new City(resultSet.getLong(SQLFields.ID), resultSet.getString(SQLFields.CITY_NAME));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return  Optional.ofNullable(city);
    }
}
