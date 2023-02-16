package ua.cargo.services;

import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.ServiceException;

import java.util.List;


public interface UserService extends CargoService<UserDTO>{
    UserDTO signIn(String login, String password) throws ServiceException;

    UserDTO getByEmail(String email) throws ServiceException;

    List<UserDTO> getSortedUsers(String query) throws ServiceException;
    void changePassword(long userId, String password, String newPass, String confirmPass) throws ServiceException;

    List<UserDTO> getAll(Pagination pagination, String filterQuery) throws ServiceException;

    int getNumberOfRecords(String filterQuery) throws ServiceException;

    void updateRole(long id, Role role) throws ServiceException;
}
