package ua.cargo.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.cargo.dao.CityDAO;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.City;
import ua.cargo.entities.Route;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.impl.CityServiceImpl;


import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static ua.cargo.utils.ConvertorUtil.convertCityToDTO;


public class CityServiceTest {
    private  CityDAO cityDAO;
    private  CityService cityService;


    @Before
    public void init()
    {
        cityDAO = Mockito.mock(CityDAO.class);
        cityService = new CityServiceImpl(cityDAO);
    }

    @Test
     public void getByIdTest() throws DAOException, ServiceException {
        long id = 1L;
        City city = new City(id, "Rome");
        Optional <City> cityOptional=Optional.of(city);

        Mockito.when(cityDAO.getById(anyLong())).thenReturn(cityOptional);

        CityDTO result = cityService.getById(id);

        Assert.assertEquals(city.getId(), result.getId());
        Assert.assertEquals(city.getCity(), result.getName());
    }
    @Test(expected = ServiceException.class)
    public void  getByIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(cityDAO.getById(anyLong())).thenThrow(new DAOException(null));

        cityService.getById(id);

    }

    @Test
    public void getAllTest() throws DAOException, ServiceException {
        long id = 1L;
        City city = new City(id, "Rome");
        List<City> cityList = List.of(city);

        Mockito.when(cityDAO.getAll()).thenReturn(cityList);

        List<CityDTO> result = cityService.getAll();

        Assert.assertEquals(city.getId(), result.get(0).getId());
        Assert.assertEquals(city.getCity(), result.get(0).getName());

    }

    @Test(expected = ServiceException.class)
    public void  getAllExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(cityDAO.getAll()).thenThrow(new DAOException(null));

        cityService.getAll();

    }

    @Test(expected = ServiceException.class)
    public void updateWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        CityDTO cityDTO = new CityDTO(id, "Berlin");

        Mockito.doThrow(new DAOException(null)).when(cityDAO).update(anyObject());

        cityService.update(cityDTO);
    }

    @Test(expected = ServiceException.class)
    public void deleteWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        CityDTO cityDTO = new CityDTO(id, "Berlin");

        Mockito.doThrow(new DAOException(null)).when(cityDAO).delete(anyLong());

        cityService.delete(cityDTO.getId());
    }
    @Test(expected = ServiceException.class)
    public void addWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        CityDTO cityDTO = new CityDTO(id, "Berlin");

        Mockito.doThrow(new DAOException(null)).when(cityDAO).add(anyObject());

        cityService.add(cityDTO);
    }









}
