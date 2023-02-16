package ua.cargo.services.impl;

import ua.cargo.dao.RouteDAO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.Route;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.NoSuchElementException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.RouteService;

import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ConvertorUtil.convertDTOToRoute;
import static ua.cargo.utils.ConvertorUtil.convertRouteToDTO;
import static ua.cargo.utils.PaginationUtil.isOrdered;

public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO;

    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @Override
    public RouteDTO getById(long routeId) throws ServiceException {
        RouteDTO routeDTO;
        try {
            Route route = routeDAO.getById(routeId).orElseThrow(NoSuchElementException::new);
            routeDTO = convertRouteToDTO(route);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routeDTO;
    }

    @Override
    public List<RouteDTO> getAll(Pagination pagination) throws ServiceException {
        List<RouteDTO> routes = new ArrayList<>();
        try {
            List<Route> routeList;
            if (isOrdered(pagination)) {
                routeList = routeDAO.getAll(pagination.getOffset(), pagination.getRecords(), pagination.getSortField(), pagination.getOrder());
            } else {
                routeList = routeDAO.getAll(pagination.getOffset(), pagination.getRecords());
            }
            routeList.forEach(route -> routes.add(convertRouteToDTO(route)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routes;
    }

    @Override
    public RouteDTO getByCities(long cityFromId, long cityToId) throws ServiceException {
        RouteDTO routeDTO;
        try {
            Route route = routeDAO.getByCities(cityFromId, cityToId).orElseThrow(NoSuchElementException::new);
            routeDTO = convertRouteToDTO(route);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routeDTO;
    }

    @Override
    public RouteDTO getByCities(long cityFromId, long cityToId, Pagination pagination) throws ServiceException {
        RouteDTO routeDTO;
        try {
            Route route;
            if (isOrdered(pagination)) {
                route = routeDAO.getByCities(cityFromId, cityToId, pagination.getOffset(), pagination.getRecords(),
                        pagination.getSortField(), pagination.getOrder()).orElseThrow(NoSuchElementException::new);
            } else {
                route = routeDAO.getByCities(cityFromId, cityToId, pagination.getOffset(), pagination.getRecords()).orElseThrow(NoSuchElementException::new);
            }
            routeDTO = convertRouteToDTO(route);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routeDTO;
    }

    @Override
    public List<RouteDTO> getByCityFrom(long cityFromId) throws ServiceException {
        List<RouteDTO> routes = new ArrayList<>();
        try {
            List<Route> routeList = routeDAO.getByCityFrom(cityFromId);
            routeList.forEach(route -> routes.add(convertRouteToDTO(route)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routes;
    }

    @Override
    public List<RouteDTO> getByCityFrom(long cityFromId, Pagination pagination) throws ServiceException {
        List<RouteDTO> routes = new ArrayList<>();
        try {
            List<Route> routeList;
            if (isOrdered(pagination)) {
                routeList = routeDAO.getByCityFrom(cityFromId, pagination.getOffset(), pagination.getRecords(),
                        pagination.getSortField(), pagination.getOrder());
            } else {
                routeList = routeDAO.getByCityFrom(cityFromId, pagination.getOffset(), pagination.getRecords());
            }
            routeList.forEach(route -> routes.add(convertRouteToDTO(route)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routes;
    }

    @Override
    public List<RouteDTO> getByCityTo(long cityToId, Pagination pagination) throws ServiceException {
        List<RouteDTO> routes = new ArrayList<>();
        try {
            List<Route> routeList;
            if (isOrdered(pagination)) {
                routeList = routeDAO.getByCityTo(cityToId, pagination.getOffset(), pagination.getRecords(),
                        pagination.getSortField(), pagination.getOrder());
            } else {
                routeList = routeDAO.getByCityTo(cityToId, pagination.getOffset(), pagination.getRecords());
            }
            routeList.forEach(route -> routes.add(convertRouteToDTO(route)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routes;
    }

    @Override
    public List<RouteDTO> getByCityTo(long cityToId) throws ServiceException {
        List<RouteDTO> routes = new ArrayList<>();
        try {
            List<Route> routeList = routeDAO.getByCityTo(cityToId);
            routeList.forEach(route -> routes.add(convertRouteToDTO(route)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routes;
    }

    @Override
    public List<RouteDTO> getAll() throws ServiceException {
        List<RouteDTO> routes = new ArrayList<>();
        try {
            List<Route> routeList = routeDAO.getAll();
            routeList.forEach(route -> routes.add(convertRouteToDTO(route)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return routes;
    }

    @Override
    public void update(RouteDTO routeDTO) throws ServiceException {
        Route route = convertDTOToRoute(routeDTO);
        try {
            routeDAO.update(route);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            routeDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(RouteDTO routeDTO) throws ServiceException {
        Route route = convertDTOToRoute(routeDTO);
        try {
            routeDAO.add(route);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public int getNumberOfRecords() throws ServiceException {
        int records;
        try {
            records = routeDAO.getNumberOfRecords();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return records;
    }

    @Override
    public int getNumberOfRecordsByCityFrom(long cityFromId) throws ServiceException {
        int records;
        try {
            records = routeDAO.getNumberOfRecordsByCityFrom(cityFromId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return records;
    }

    @Override
    public int getNumberOfRecordsByCityTo(long cityToId) throws ServiceException {
        int records;
        try {
            records = routeDAO.getNumberOfRecordsByCityTo(cityToId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return records;
    }
}
