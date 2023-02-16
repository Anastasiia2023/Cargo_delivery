package ua.cargo.services.impl;

import ua.cargo.dao.CityDAO;
import ua.cargo.dto.CityDTO;
import ua.cargo.entities.City;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.NoSuchElementException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;

import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ConvertorUtil.*;

public class CityServiceImpl implements CityService {
    private final CityDAO cityDAO;

    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public CityDTO getById(long cityId) throws ServiceException {
        CityDTO cityDTO;
        try {
            City city = cityDAO.getById(cityId).orElseThrow(NoSuchElementException::new);
            cityDTO = convertCityToDTO(city);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cityDTO;
    }

    @Override
    public List<CityDTO> getAll() throws ServiceException {
        List<CityDTO> cities = new ArrayList<>();
        try {
            List<City> cityList= cityDAO.getAll();
            cityList.forEach(city -> cities.add(convertCityToDTO(city)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cities;
    }

    @Override
    public void update(CityDTO cityDTO) throws ServiceException {
        City city = convertDTOToCity(cityDTO);
        try {
            cityDAO.update(city);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            cityDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(CityDTO cityDTO) throws ServiceException {
        City city = convertDTOToCity(cityDTO);
        try {
            cityDAO.add(city);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CityDTO getByName(String name) throws ServiceException {
        return null;
    }
}
