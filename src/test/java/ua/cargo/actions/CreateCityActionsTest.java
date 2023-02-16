package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.CreateCityAction;
import ua.cargo.dto.CityDTO;
import ua.cargo.entities.City;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.mockito.Mockito.*;



public class CreateCityActionsTest {
    private  CityService cityService;
    private CreateCityAction createCityAction;


    @Before
    public void init()
    {
        cityService = Mockito.mock(CityService.class);
        createCityAction = new CreateCityAction(cityService);
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

        String result = createCityAction.execute(request, response);

        Assert.assertEquals(Pages.CREATE_CITY_PAGE, result);
    }

    @Test
    public void executePostTest() throws ServiceException {
        long id = 1L;
        City city = new City(id, "Krakow");
        String expResult = "controller?action=create-city";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameters.CITY)).thenReturn(city.getCity());
        doNothing().when(cityService).add(any());

        String result = createCityAction.execute(request, response);

        Assert.assertEquals(expResult, result);
    }
}
