package ua.cargo.utils;


import ua.cargo.actions.constants.Parameters;
import ua.cargo.dto.CityDTO;
import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.ParcelDTO;
import ua.cargo.dto.UserDTO;
import ua.cargo.entities.enums.OrderStatus;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CityService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static ua.cargo.actions.constants.Pages.CONTROLLER_PAGE;
import static ua.cargo.actions.constants.Parameters.*;


public final class ActionUtil {
    private static final Set<String> PARAMETERS = new HashSet<>();

    static {
        PARAMETERS.add(OFFSET);
        PARAMETERS.add(RECORDS);
        PARAMETERS.add(ORDER);
        PARAMETERS.add(SORT);
        PARAMETERS.add(DATE_FROM);
        PARAMETERS.add(DATE_TO);
        PARAMETERS.add(ORDER_STATUS);
        PARAMETERS.add(CITY_TO_ID);
        PARAMETERS.add(CITY_FROM_ID);
    }

    public static boolean isPostMethod(HttpServletRequest request) {
        return request.getMethod().equals("POST");
    }

    public static String getPath(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(CURRENT_PATH);
    }

    public static String[] getParameterData(HttpServletRequest request) {
        int countFiltersInRequest = 0;
        for (String filter : PARAMETERS) {
            if (isNotEmpty(request.getParameter(filter))) {
                countFiltersInRequest++;
            }
        }

        String[] filters = new String[countFiltersInRequest * 2];
        int i = 0;
        for (String filter : PARAMETERS) {
            if (isNotEmpty(request.getParameter(filter))) {
                filters[i] = filter;
                filters[i + 1] = request.getParameter(filter);
                i += 2;
            }
        }
        return filters;

    }

    public static String getActionToRedirect(String action, String... parameters) {
        String base = CONTROLLER_PAGE + "?" + ACTION + "=" + action;
        StringJoiner stringJoiner = new StringJoiner("&", "&", "");
        for (int i = 0; i < parameters.length; i += 2) {
            stringJoiner.add(parameters[i] + "=" + parameters[i + 1]);
        }
        return base + (parameters.length > 0 ? stringJoiner : "");
    }

    public static void transferStringFromSessionToRequest(HttpServletRequest request, String attributeName) {
        String attributeValue = String.valueOf(request.getSession().getAttribute(attributeName));
        if (attributeValue != "null") {
            request.setAttribute(attributeName, attributeValue);
            request.getSession().removeAttribute(attributeName);
        }
    }

    public static void transferUserDTOFromSessionToRequest(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER);
        if (user != null) {
            request.setAttribute(USER, user);
            request.getSession().removeAttribute(USER);
        }
    }

    public static void transferParcelDTOFromSessionToRequest(HttpServletRequest request) {
        ParcelDTO parcel = (ParcelDTO) request.getSession().getAttribute("parcel");
        if (parcel != null) {
            request.setAttribute("parcel", parcel);
            request.getSession().removeAttribute("parcel");
        }
    }

    public static void transferCityDTOFromSessionToRequest(HttpServletRequest request, String attributeName) {
        CityDTO city = (CityDTO) request.getSession().getAttribute(attributeName);
        if (city != null) {
            request.setAttribute(attributeName, city);
            request.getSession().removeAttribute(attributeName);
        }
    }

    public static void transferListFromSessionToRequest(HttpServletRequest request, String attributeName) {
        List attributeValue = (List) request.getSession().getAttribute(attributeName);
        if (attributeValue != null) {
            request.setAttribute(attributeName, attributeValue);
            request.getSession().removeAttribute(attributeName);
        }
    }

    public static void setOrderAttributes(HttpServletRequest request, List<OrderDTO> result) {
        request.setAttribute(LIST_ORDERS_STATUS, OrderStatus.values());
        request.setAttribute(LIST_ORDERS, result);
        if (isNotNull(request.getParameter(DATE_FROM))) {
            LocalDate dateFrom = LocalDate.parse(request.getParameter(DATE_FROM));
            request.setAttribute("dateFrom", dateFrom);
        }
        if (isNotNull(request.getParameter(DATE_TO))) {
            LocalDate dateTo = LocalDate.parse(request.getParameter(DATE_TO));
            request.setAttribute("dateTo", dateTo);
        }
        String orderStatus = request.getParameter(ORDER_STATUS);
        request.setAttribute(ORDER_STATUS_ATTRIBUTE,  isNotEmpty(orderStatus) ? OrderStatus.getOrderStatus(orderStatus) : null);
    }

    public static void setManagerOrderAttributes(HttpServletRequest request, CityService cityService, List<OrderDTO> result) throws ServiceException {
        setOrderAttributes(request, result);
        String cityFrom = request.getParameter(Parameters.CITY_FROM_ID);
        request.setAttribute(Parameters.CITY_FROM, isNotEmpty(cityFrom) ? cityService.getById(Long.parseLong(cityFrom)) : null);
        String cityTo = request.getParameter(Parameters.CITY_TO_ID);
        request.setAttribute(Parameters.CITY_TO, isNotEmpty(cityTo) ? cityService.getById(Long.parseLong(cityTo)) : null);
    }

    private static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    private static boolean isNotNull(Object obj) {
        return obj != null && ((String) obj).length()>0 && !obj.equals("0");
    }

    private ActionUtil() {
    }
}