package ua.cargo.services;

import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.ServiceException;

import java.util.List;

/**
 * UserService interface.
 * Implement all methods in concrete UserService
 *
 * @author Anastasiia Shevchuk
 * @version 1.0
 */


public interface UserService extends CargoService<UserDTO>{

    /**
     * Obtains necessary User entity and checks if password matches
     * @param login - to find user in database
     * @param password - to check if matches with user password
     * @return UserDTO - that matches User entity
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    UserDTO signIn(String login, String password) throws ServiceException;

    /**
     * Obtains necessary User entity
     * @param email - to find user in database
     * @return UserDTO - that matches User entity
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    UserDTO getByEmail(String email) throws ServiceException;

    List<UserDTO> getSortedUsers(String query) throws ServiceException;

    /**
     * Calls DAO to update User with new password
     * @param userId id to find user by
     * @param password - old password
     * @param newPass - new password
     * @param confirmPass - should match new password
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    void changePassword(long userId, String password, String newPass, String confirmPass) throws ServiceException;

    /**
     * Calls DAO to get all users
     * @param pagination -
     * @param filterQuery - conditions for such Users
     * @return list of UserDTOs
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    List<UserDTO> getAll(Pagination pagination, String filterQuery) throws ServiceException;

    /**
     * Calls DAO to get number of all records that match filter
     * @param filterQuery - conditions for such Users
     * @return number of records that match demands
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    int getNumberOfRecords(String filterQuery) throws ServiceException;

    /**
     * Calls DAO to update User with new role
     * @param id -  id to find user by
     * @param role - user role
     * @throws ServiceException - may wrap DAOException or be thrown by another mistakes
     */
    void updateRole(long id, Role role) throws ServiceException;
}
