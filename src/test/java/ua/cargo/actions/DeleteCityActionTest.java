package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.DeleteCityAction;
import ua.cargo.dto.CityDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.mockito.Mockito.*;


public class DeleteCityActionTest {
    private  CityService cityService;
    private DeleteCityAction deleteCityAction;

    @Before
    public void init()
    {
        cityService = Mockito.mock(CityService.class);

        deleteCityAction = new DeleteCityAction(cityService);
    }

    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        CityDTO cityDTO = new CityDTO(id, "Rome");
        List<CityDTO> cityDTOList = List.of(cityDTO);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        Mockito.when(cityService.getAll()).thenReturn(cityDTOList);

        String result = deleteCityAction.execute(request, response);

        Assert.assertEquals(Pages.CREATE_CITY_PAGE, result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        String expResult = "controller?action=delete-city";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameters.CITY_ID)).thenReturn(String.valueOf(id));
        doNothing().when(cityService).delete(anyLong());

        String result = deleteCityAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }
}
