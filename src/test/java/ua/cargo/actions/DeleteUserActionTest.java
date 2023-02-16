package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.DeleteUserAction;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class DeleteUserActionTest {
    private  UserService userService;
    private DeleteUserAction deleteUserAction;

    @Before
    public void init()
    {
       userService = Mockito.mock(UserService.class);
        deleteUserAction = new DeleteUserAction(userService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);


        String result = deleteUserAction.execute(request, response);

        Assert.assertEquals(Pages.SIGN_IN_PAGE, result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        String password = "0o9i8u7y6T";
        String expResult = "controller?action=delete-user";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        UserDTO userDTO = new UserDTO(id, "Nino", "Archi", password, "wkmffeifiw@ulr.net");

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Parameters.LOGGED_USER)).thenReturn(userDTO);
        doNothing().when(userService).delete(anyLong());

        String result = deleteUserAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }

}
