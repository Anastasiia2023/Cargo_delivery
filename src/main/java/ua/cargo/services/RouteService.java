package ua.cargo.services;

import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;

import java.util.List;

public interface RouteService extends CargoService<RouteDTO>{

    List<RouteDTO> getAll(Pagination pagination) throws ServiceException;
    RouteDTO getByCities(long cityFromId, long cityToId) throws ServiceException;

    RouteDTO getByCities(long cityFromId, long cityToId, Pagination pagination) throws ServiceException;
    List<RouteDTO> getByCityFrom(long cityFromId) throws ServiceException;

    List<RouteDTO> getByCityFrom(long cityFromId, Pagination pagination) throws ServiceException;

    List<RouteDTO> getByCityTo(long cityToId) throws ServiceException;

    List<RouteDTO> getByCityTo(long cityToId, Pagination pagination) throws ServiceException;

    int getNumberOfRecords() throws ServiceException;
    int getNumberOfRecordsByCityFrom(long cityFromId) throws ServiceException;
    int getNumberOfRecordsByCityTo(long cityToId) throws ServiceException;
}
