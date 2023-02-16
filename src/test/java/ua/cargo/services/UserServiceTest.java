package ua.cargo.services;


import ua.cargo.dao.UserDAO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.enums.Role;
import ua.cargo.exceptions.DAOException;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.impl.UserServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.entities.*;

import java.util.Optional;

import static org.mockito.Matchers.*;
import static ua.cargo.utils.PasswordHashUtil.encode;

public class UserServiceTest {
    private  UserDAO userDAO;
    private UserService userService;

    @Before
    public void init() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl(userDAO);

    }

    @Test
    public void getByIdTest() throws DAOException, ServiceException {
        long id = 1L;
        User user = new User(id, "Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net", "+380967477", "23/02/1998");
        user.setRole(Role.CUSTOMER);
        Optional<User> userOptional=Optional.of(user);

        Mockito.when(userDAO.getById(anyLong())).thenReturn(userOptional);

        UserDTO result = userService.getById(id);

        Assert.assertEquals(user.getId(), result.getId());
        Assert.assertEquals(user.getName(), result.getName());
        Assert.assertEquals(user.getPassword(), result.getPassword());
        Assert.assertEquals(user.getEmail(), result.getEmail());
        Assert.assertEquals(user.getPhone(), result.getPhone());
        Assert.assertEquals(user.getSurname(), result.getSurname());
        Assert.assertEquals(user.getDate_birth(), result.getDate_birth());

    }
    @Test(expected = ServiceException.class)
    public void  getByIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(userDAO.getById(anyLong())).thenThrow(new DAOException(null));

        userService.getById(id);

    }

    @Test(expected = ServiceException.class)
    public void updateWithExceptionTest() throws ServiceException, DAOException {

        UserDTO userDTO = new UserDTO("Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net", "+380967477", "23/02/1998");

        Mockito.doThrow(new DAOException(null)).when(userDAO).update(anyObject());

        userService.update(userDTO);
    }

    @Test(expected = ServiceException.class)
    public void deleteWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        UserDTO userDTO = new UserDTO(id, "Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net");

        Mockito.doThrow(new DAOException(null)).when(userDAO).delete(anyLong());

        userService.delete(userDTO.getId());
    }
    @Test(expected = ServiceException.class)
    public void addWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        UserDTO userDTO = new UserDTO( "Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net", "+380967477", "23/02/1998");

        Mockito.doThrow(new DAOException(null)).when(userDAO).add(anyObject());

        userService.add(userDTO);
    }

    @Test
    public void SignInTest() throws DAOException, ServiceException {
        long id = 1L;
        String password = "qawsed%H";
        String login = "edrftOgkd@ukk.net";

        User user = new User(id, "Mim", "Lil", encode(password),login, "+380967477", "23/02/1998");
        user.setRole(Role.MANAGER);
        Optional<User> userOptional=Optional.of(user);

        Mockito.when(userDAO.getByEmail(anyString())).thenReturn(userOptional);

        UserDTO result = userService.signIn(login, password);

        Assert.assertEquals(user.getId(), result.getId());
        Assert.assertEquals(user.getPassword(), result.getPassword());
        Assert.assertEquals(user.getEmail(), result.getEmail());

    }
    @Test(expected = ServiceException.class)
    public void SignInExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        String password = "qawsed%H";
        String login = "edrftOgkd@ukk.net";

        Mockito.when(userDAO.getByEmail(anyString())).thenThrow(new DAOException(null));

        userService.signIn(login, password);
    }

    @Test (expected = ServiceException.class)
    public void changePasswordTest() throws DAOException, ServiceException {
        long id = 1L;
        String password = "qawsed%H";
        String newPass = "qawsed%Hrf3";
        String confirmPass = "qawsed%Hrf3";

        String login = "edrftOgkd@ukk.net";

        User user = new User(id, "Mim", "Lil", encode(password),login, "+380967477", "23/02/1998");
        user.setRole(Role.MANAGER);
        Optional<User> userOptional=Optional.of(user);

        Mockito.when(userDAO.getById(anyLong())).thenReturn(userOptional);
        Mockito.doThrow(new DAOException(null)).when(userDAO).updatePassword(any());

        userService.changePassword(user.getId(), user.getPassword(), newPass, confirmPass );


    }



}
