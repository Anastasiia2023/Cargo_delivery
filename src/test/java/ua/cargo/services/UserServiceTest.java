package ua.cargo.services;


import ua.cargo.dao.UserDAO;
import ua.cargo.dto.CityDTO;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.*;
import static ua.cargo.entities.enums.Role.CUSTOMER;
import static ua.cargo.entities.enums.Role.MANAGER;
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
        User user = new User(id, "Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net", "+380967477", LocalDate.parse("1988-02-23"));
        user.setRole(CUSTOMER);
        Optional<User> userOptional=Optional.of(user);

        Mockito.when(userDAO.getById(anyLong())).thenReturn(userOptional);

        UserDTO result = userService.getById(id);

        Assert.assertEquals(user.getId(), result.getId());
        Assert.assertEquals(user.getName(), result.getName());
        Assert.assertEquals(user.getPassword(), result.getPassword());
        Assert.assertEquals(user.getEmail(), result.getEmail());
        Assert.assertEquals(user.getPhone(), result.getPhone());
        Assert.assertEquals(user.getSurname(), result.getSurname());
        Assert.assertEquals(user.getDateBirth(), result.getDateBirth());

    }
    @Test(expected = ServiceException.class)
    public void  getByIdExceptionTest() throws DAOException, ServiceException {
        long id = 1L;

        Mockito.when(userDAO.getById(anyLong())).thenThrow(new DAOException(null));

        userService.getById(id);

    }

    @Test(expected = ServiceException.class)
    public void updateWithExceptionTest() throws ServiceException, DAOException {

        UserDTO userDTO = new UserDTO("Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net", "+380967477", LocalDate.parse("1998-02-23"));

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
        UserDTO userDTO = new UserDTO( "Mim", "Lil", "qawsed%H","edrftOgkd@ukk.net", "+380967477", LocalDate.parse("1998-02-23"));

        Mockito.doThrow(new DAOException(null)).when(userDAO).add(anyObject());

        userService.add(userDTO);
    }

    @Test
    public void SignInTest() throws DAOException, ServiceException {
        long id = 1L;
        String password = "qawsed%H";
        String login = "edrftOgkd@ukk.net";

        User user = new User(id, "Mim", "Lil", encode(password),login, "+380967477", LocalDate.parse("1998-02-23"));
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

        User user = new User(id, "Mim", "Lil", encode(password),login, "+380967477", LocalDate.parse("1998-02-23"));
        user.setRole(Role.MANAGER);
        Optional<User> userOptional=Optional.of(user);

        Mockito.when(userDAO.getById(anyLong())).thenReturn(userOptional);
        Mockito.doThrow(new DAOException(null)).when(userDAO).updatePassword(any());

        userService.changePassword(user.getId(), password, newPass, confirmPass );


    }

    @Test
    public void getAllTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(35);
        String query = "";
        User user = new User(id, "Anna", "Benn", "1q2w3e4qT", "qawsedrfvnd@ukr.net", "+38096547934", CUSTOMER );
        List<User> userList = List.of(user);

        Mockito.when(userDAO.getAll(pagination.getOffset(), pagination.getRecords(), query)).thenReturn(userList);

        List<UserDTO> result = userService.getAll(pagination, query);

        Assert.assertEquals(user.getId(), result.get(0).getId());
        Assert.assertEquals(user.getName(), result.get(0).getName());
        Assert.assertEquals(user.getSurname(), result.get(0).getSurname());
        Assert.assertEquals(user.getPassword(), result.get(0).getPassword());
        Assert.assertEquals(user.getEmail(), result.get(0).getEmail());
        Assert.assertEquals(user.getPhone(), result.get(0).getPhone());

    }

    @Test
    public void getAllIsOrderedTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(35);
        pagination.setSortField("");
        pagination.setOrder("");
        String query = "";
        User user = new User(id, "Anna", "Benn", "1q2w3e4qT", "qawsedrfvnd@ukr.net", "+38096547934", CUSTOMER );
        List<User> userList = List.of(user);

        Mockito.when(userDAO.getAll(pagination.getOffset(), pagination.getRecords(), pagination.getSortField(), pagination.getOrder(), query)).thenReturn(userList);

        List<UserDTO> result = userService.getAll(pagination, query);

        Assert.assertEquals(user.getId(), result.get(0).getId());
        Assert.assertEquals(user.getName(), result.get(0).getName());
        Assert.assertEquals(user.getSurname(), result.get(0).getSurname());
        Assert.assertEquals(user.getPassword(), result.get(0).getPassword());
        Assert.assertEquals(user.getEmail(), result.get(0).getEmail());
        Assert.assertEquals(user.getPhone(), result.get(0).getPhone());

    }

    @Test(expected = ServiceException.class)
    public void  getAllExceptionTest() throws DAOException, ServiceException {
        long id = 1L;
        Pagination pagination = new Pagination();
        pagination.setOffset(10);
        pagination.setRecords(35);
        String query = "";

        Mockito.when(userDAO.getAll(pagination.getOffset(), pagination.getRecords(), query)).thenThrow(new DAOException(null));

        userService.getAll(pagination, query);

    }

    @Test
    public void getNumberOfRecordsTest() throws DAOException, ServiceException {
        int records = 45;
        String query = "";

        Mockito.when(userDAO.getNumberOfRecords(query)).thenReturn(records);

        int result = userService.getNumberOfRecords("");

        Assert.assertEquals(records, result);
    }

    @Test(expected = ServiceException.class)
    public void  getNumberOfRecordsExceptionTest() throws DAOException, ServiceException {
        String query = "";

        Mockito.when(userDAO.getNumberOfRecords(query)).thenThrow(new DAOException(null));

        userService.getNumberOfRecords("");


    }

    @Test(expected = ServiceException.class)
    public void updateRoleCustomerWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        Role role = CUSTOMER;

        Mockito.doThrow(new DAOException(null)).when(userDAO).updateRole(id, MANAGER);

        userService.updateRole(id, role);
    }
    @Test(expected = ServiceException.class)
    public void updateRoleManagerWithExceptionTest() throws ServiceException, DAOException {
        long id = 1L;
        Role roles = MANAGER;

        Mockito.doThrow(new DAOException(null)).when(userDAO).updateRole(id, CUSTOMER);

        userService.updateRole(id, roles);
    }




}
