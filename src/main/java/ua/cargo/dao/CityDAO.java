package ua.cargo.dao;

import ua.cargo.entities.City;
import ua.cargo.exceptions.DAOException;

import java.util.Optional;


public interface CityDAO extends EntityDAO <City>{

    Optional<City> getByName(String name) throws DAOException;

}
