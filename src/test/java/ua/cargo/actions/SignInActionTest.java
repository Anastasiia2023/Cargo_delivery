package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.SignInAction;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static ua.cargo.entities.enums.Role.CUSTOMER;

public class SignInActionTest {
    private UserService userService;
    private SignInAction signInAction;

    @Before
    public void init()
    {
        userService = Mockito.mock(UserService.class);
        signInAction = new SignInAction(userService);
    }
    @Test

    public void executeGetTest() throws ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);

        String result = signInAction.execute(request, response);

        Assert.assertNull(result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        String password = "0o9i8u7y6T";
        String expResult = "profile.jsp";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UserDTO userDTO = new UserDTO(id, "Nino", "Archi", password, "wkmffeifiw@ulr.net", "+340597743", LocalDate.parse("1996-04-23"), CUSTOMER);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Parameters.LOGGED_USER)).thenReturn(userDTO);
        when(request.getParameter(Parameters.LOGGED_USER)).thenReturn(String.valueOf(id));
        when(request.getParameter(Parameters.EMAIL)).thenReturn(userDTO.getEmail());
        when(request.getParameter(Parameters.PASSWORD)).thenReturn(userDTO.getPassword());

        Mockito.when(userService.signIn(userDTO.getEmail(), userDTO.getPassword())).thenReturn(userDTO);

        String result = signInAction.execute(request, response);

        Assert.assertEquals(expResult, result);


    }
}
