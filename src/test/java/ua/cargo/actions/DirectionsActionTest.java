package ua.cargo.actions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.cargo.actions.Action;
import ua.cargo.actions.constants.ActionNames;
import ua.cargo.actions.constants.Pages;
import ua.cargo.actions.constants.Parameters;
import ua.cargo.actions.implementation.DeleteUserAction;
import ua.cargo.actions.implementation.DirectionsAction;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.entities.Pagination;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;
import ua.cargo.services.RouteService;
import ua.cargo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.cargo.utils.ActionUtil.*;
import static ua.cargo.utils.PaginationUtil.getPaginationData;
import static ua.cargo.utils.PaginationUtil.paginate;

public class DirectionsActionTest {
    private  CityService cityService;
    private  RouteService routeService;
    private DirectionsAction directionsAction;

    @Before
    public void init()
    {
        cityService = Mockito.mock(CityService.class);
        routeService = Mockito.mock(RouteService.class);
        directionsAction = new DirectionsAction(cityService, routeService);
    }
    @Test
    public void executeGetTest() throws ServiceException {
        long id = 1L;
        CityDTO cityDTO = new CityDTO(id, "Nice");
        List<CityDTO> cityDTOList = List.of(cityDTO);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        Mockito.when(cityService.getAll()).thenReturn(cityDTOList);

        String result = directionsAction.execute(request, response);

        Assert.assertEquals(Pages.DIRECTION_PAGE, result);
    }



}
