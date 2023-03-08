package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.EditProfileAction;
import ua.cargo.dto.UserDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.mockito.Mockito.*;


public class EditProfileActionTest {
    private  UserService userService;
    private EditProfileAction editProfileAction;

    @Before
    public void init()
    {
        userService = Mockito.mock(UserService.class);
        editProfileAction = new EditProfileAction(userService);
    }
    @Test
    public void executeGetTest() throws ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);


        String result = editProfileAction.execute(request, response);

        Assert.assertEquals(Pages.EDIT_PROFILE_PAGE, result);
    }
    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        String expResult = "controller?action=edit-profile";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        UserDTO userDTO = new UserDTO(id, "Nino", "Archi", "wkmffeifiw@ulr.net", "+3807549575", LocalDate.parse("1997-02-23"));

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Parameters.LOGGED_USER)).thenReturn(userDTO);
        when(request.getParameter(Parameters.NAME)).thenReturn(userDTO.getName());
        when(request.getParameter(Parameters.SURNAME)).thenReturn(userDTO.getSurname());
        when(request.getParameter(Parameters.EMAIL)).thenReturn(userDTO.getEmail());
        when(request.getParameter(Parameters.PHONE)).thenReturn(userDTO.getPhone());
        when(request.getParameter(Parameters.DATE_OF_BIRTH)).thenReturn(userDTO.getDateBirth().toString());
        doNothing().when(userService).update(userDTO);

        String result = editProfileAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }


}
