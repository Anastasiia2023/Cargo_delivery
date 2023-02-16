package ua.cargo.services.impl;

import ua.cargo.dao.UserDAO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.entities.enums.Role;
import ua.cargo.entities.User;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.NoSuchElementException;
import ua.cargo.exceptions.NoSuchUserException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static ua.cargo.utils.ConvertorUtil.*;
import static ua.cargo.utils.PaginationUtil.isOrdered;
import static ua.cargo.utils.PasswordHashUtil.encode;
import static ua.cargo.utils.PasswordHashUtil.verify;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDTO getById(long id) throws ServiceException {
        UserDTO userDTO;
        try {
            User user = userDAO.getById(id).orElseThrow(NoSuchElementException::new);
            userDTO = convertUserToDTO(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public void update(UserDTO userDTO) throws ServiceException {
        User user = convertDTOToUser(userDTO);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(UserDTO userDTO) throws ServiceException {
        userDTO.setPassword(encode(userDTO.getPassword()));
        User user = convertDTOToUser(userDTO);
        user.setRole(Role.CUSTOMER);
        try {
            userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDTO signIn(String email, String password) throws ServiceException {
        UserDTO userDTO;
        try {
            User user = userDAO.getByEmail(email).orElseThrow(NoSuchUserException::new);
            verify(password, user.getPassword());
            userDTO = convertUserToDTO(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userDTO;
    }

    @Override
    public UserDTO getByEmail(String email) throws ServiceException {
        return null;
    }

    @Override
    public List<UserDTO> getSortedUsers(String query) throws ServiceException {
        return null;
    }

    @Override
    public void changePassword(long userId, String password, String newPass, String confirmPass) throws ServiceException {
        try {
            User user = userDAO.getById(userId).orElseThrow(NoSuchUserException::new);
            verify(password, user.getPassword());
            user.setPassword(encode(newPass));
            userDAO.updatePassword(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserDTO> getAll(Pagination pagination, String filterQuery) throws ServiceException {
        List<UserDTO> users = new ArrayList<>();
        try {
            List<User> userList;
            if (isOrdered(pagination)) {
                userList = userDAO.getAll(pagination.getOffset(), pagination.getRecords(), pagination.getSortField(), pagination.getOrder(), filterQuery);
            } else {
                userList = userDAO.getAll(pagination.getOffset(), pagination.getRecords(), filterQuery);
            }
            userList.forEach(user -> users.add(convertUserToDTO(user)));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public int getNumberOfRecords(String filterQuery) throws ServiceException {
        int records;
        try {
            records = userDAO.getNumberOfRecords(filterQuery);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return records;
    }

    @Override
    public void updateRole(long id, Role role) throws ServiceException {
        try {
        switch (role.getValue()) {
            case 2:
                userDAO.updateRole(id, Role.getRole(3));
                break;
            case 3:
                userDAO.updateRole(id, Role.getRole(2));
        }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
