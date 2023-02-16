package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.ChangePasswordAction;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.*;
import ua.cargo.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import static ua.cargo.actions.constants.Pages.PROFILE_PAGE;

public class ChangePasswordActionTest {
    private  UserService userService;
    private ChangePasswordAction changePasswordAction;

    @Before
    public void init()
    {
        userService = Mockito.mock(UserService.class);
        changePasswordAction = new ChangePasswordAction(userService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);

        String result = changePasswordAction.execute(request, response);

        Assert.assertEquals(PROFILE_PAGE, result);


    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        String oldPassword = "1q2w3e4r5t^";
        String password = "0o9i8u7y6T";
        String confirmPassword = "0o9i8u7y6T";
        String expResult = "controller?action=change-password";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UserDTO userDTO = new UserDTO(id, "Nino", "Archi", password, "wkmffeifiw@ulr.net");

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Parameters.LOGGED_USER)).thenReturn(userDTO);
        when(request.getParameter(Parameters.LOGGED_USER)).thenReturn(String.valueOf(id));
        when(request.getParameter(Parameters.OLD_PASSWORD)).thenReturn(oldPassword);
        when(request.getParameter(Parameters.PASSWORD)).thenReturn(password);
        when(request.getParameter(Parameters.CONFIRM_PASSWORD)).thenReturn(confirmPassword);

        doNothing().when(userService).changePassword(id, oldPassword, password, confirmPassword);

        String result = changePasswordAction.execute(request, response);

        Assert.assertEquals(expResult, result);


    }



}
