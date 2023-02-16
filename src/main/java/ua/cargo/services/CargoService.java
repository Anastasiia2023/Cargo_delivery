package ua.cargo.services;

import ua.cargo.exceptions.ServiceException;

import java.util.List;

public interface CargoService<T> {
    T getById(long id) throws ServiceException;

    List<T> getAll() throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(long id) throws ServiceException;

    void add(T entity) throws ServiceException;
}