package ua.cargo.services;

import ua.cargo.dto.CityDTO;
import ua.cargo.exceptions.ServiceException;


public interface CityService extends CargoService<CityDTO>{
    CityDTO getByName(String name) throws ServiceException;
}
