package ua.cargo.dao;

import ua.cargo.entities.Route;
import ua.cargo.exceptions.DAOException;

import java.util.List;
import java.util.Optional;

public interface RouteDAO extends EntityDAO <Route> {

    List<Route> getAll(int offset, int records) throws DAOException;
    List<Route> getAll(int offset, int records, String sortedValue, String order) throws DAOException;
    Optional<Route> getByCities(long cityFromId, long cityToId) throws DAOException;
    Optional<Route> getByCities(long cityFromId, long cityToId, int offset, int records) throws DAOException;
    Optional<Route> getByCities(long cityFromId, long cityToId, int offset, int records, String sortedValue, String order) throws DAOException;
    List<Route> getByCityFrom(long cityFromId) throws DAOException;
    List<Route> getByCityFrom(long cityToId, int offset, int records) throws DAOException;
    List<Route> getByCityFrom(long cityFromId, int offset, int records, String sortedValue, String order) throws DAOException;

    List<Route> getByCityTo(long cityToId) throws DAOException;
    List<Route> getByCityTo(long cityToId, int offset, int records) throws DAOException;
    List<Route> getByCityTo(long cityToId, int offset, int records, String sortedValue, String order) throws DAOException;
    int getNumberOfRecords() throws DAOException;
    int getNumberOfRecordsByCityFrom(long cityFromId) throws DAOException;
    int getNumberOfRecordsByCityTo(long cityToId) throws DAOException;

}


