package ua.cargo.dao;


import ua.cargo.entities.Order;
import ua.cargo.entities.User;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.DAOException;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {

    Optional<User> getByEmail(String email) throws DAOException;

    List<User> getSorted(String query) throws DAOException;

    int getNumberOfOrders(String filter) throws DAOException;

    void updatePassword(User user) throws DAOException;

    void updateRole(long id, Role role) throws DAOException;

    int getNumberOfRecords(String filterQuery) throws DAOException;

    List<User> getAll(int offset, int records, String filterQuery) throws DAOException;
    List<User> getAll(int offset, int records, String sortedValue, String order, String filterQuery) throws DAOException;
}