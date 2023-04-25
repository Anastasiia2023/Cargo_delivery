package ua.cargo.services;

import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;

import java.util.List;

/**
 * RouteService interface.
 * Implement all methods in concrete RouteService
 *
 * @author Anastasiia Shevchuk
 * @version 1.0
 */

public interface RouteService extends CargoService<RouteDTO>{

    /**
     * Calls DAO to get all routes
     * @param pagination -
     * @return list of RouteDTOs
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    List<RouteDTO> getAll(Pagination pagination) throws ServiceException;

    /**
     * Calls DAO to get all routes
     * @param cityFromId - id to find city
     * @param cityToId - id to find city
     * @return  RouteDTO
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    RouteDTO getByCities(long cityFromId, long cityToId) throws ServiceException;

    RouteDTO getByCities(long cityFromId, long cityToId, Pagination pagination) throws ServiceException;

    /**
     * Calls DAO to get all routes
     * @param cityFromId - id to find all cityFrom
     * @return list of RouteDTOs
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    List<RouteDTO> getByCityFrom(long cityFromId) throws ServiceException;

    List<RouteDTO> getByCityFrom(long cityFromId, Pagination pagination) throws ServiceException;

    /**
     * Calls DAO to get all routes
     * @param cityToId - id to find all cityTo
     * @return list of RouteDTOs
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    List<RouteDTO> getByCityTo(long cityToId) throws ServiceException;

    List<RouteDTO> getByCityTo(long cityToId, Pagination pagination) throws ServiceException;

    /**
     * Calls DAO to get number of all records
     * @return number of records
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    int getNumberOfRecords() throws ServiceException;

    /**
     * Calls DAO to get number of all records that match filter
     * @param cityFromId - conditions for such Users
     * @return number of records that match demands
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    int getNumberOfRecordsByCityFrom(long cityFromId) throws ServiceException;
    int getNumberOfRecordsByCityTo(long cityToId) throws ServiceException;
}
