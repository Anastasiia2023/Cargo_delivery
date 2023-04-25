package ua.cargo.utils;


import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import static ua.cargo.actions.constants.Parameters.*;
import static ua.cargo.dao.constants.SQLFields.*;
import static ua.cargo.dao.constants.SQLFields.ROLE;

public final class FilterUtil {

    private static final Set<String> ORDER_FILTERS = new HashSet<>();

    static {
        ORDER_FILTERS.add(DATE_FROM);
        ORDER_FILTERS.add(DATE_TO);
        ORDER_FILTERS.add(ORDER_STATUS);
    }

    public static void filter(HttpServletRequest request) {
        int offset = getInt((String) request.getAttribute(OFFSET), 0, 0);
        int records = getInt((String) request.getAttribute(RECORDS), 1, 5);
        int totalRecords = getInt(request.getAttribute(TOTAL_RECORDS) + "", 0, 0);
        setAttributes(request, totalRecords, records, offset);
    }

    private static void setAttributes(HttpServletRequest request, int totalRecords, int records, int offset) {
        int pages = totalRecords / records + (totalRecords % records != 0 ? 1 : 0);
        int currentPage = offset / records + 1;
        int startPage = currentPage == pages ? Math.max(currentPage - 2, 1)
                : Math.max(currentPage - 1, 1);
        int endPage = Math.min(startPage + 2, pages);
        request.setAttribute(OFFSET, offset);
        request.setAttribute(RECORDS, records);
        request.setAttribute(PAGES, pages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(START, startPage);
        request.setAttribute(END, endPage);
    }

    private static int getInt(String value, int min, int defaultValue) {
        try {
            int records = Integer.parseInt(value);
            if (records >= min) {
                return records;
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static String getOrderFilterQueryString(HttpServletRequest request) {
        StringJoiner stringJoiner = new StringJoiner(" AND ", "", "");
        if (isNotNull(request.getParameter(ORDER_STATUS))) {
            stringJoiner.add(ORDER_STATUSES + "='" + request.getParameter(ORDER_STATUS)+"'");
        }
        if (isNotNull(request.getParameter(DATE_FROM))) {
            stringJoiner.add(CREATION_DATE+">='"+request.getParameter(DATE_FROM)+"'");
        }
        if (isNotNull(request.getParameter(DATE_TO))) {
            stringJoiner.add(CREATION_DATE+"<='"+request.getParameter(DATE_TO)+"'");
        }
        if (isNotNull(request.getParameter(CITY_FROM_ID))) {
            stringJoiner.add(CITY_FROM_ID_SQL+"='"+request.getParameter(CITY_FROM_ID)+"'");
        }
        if (isNotNull(request.getParameter(CITY_TO_ID))) {
            stringJoiner.add(CITY_TO_ID_SQL+"='"+request.getParameter(CITY_TO_ID)+"'");
        }
        return stringJoiner.toString();
    }

    public static String getUserFilterQueryString(HttpServletRequest request) {
        StringJoiner stringJoiner = new StringJoiner(" AND ", "", "");
        if (isNotNull(request.getParameter(USER_ROLE))) {
            stringJoiner.add(ROLE + "='" + request.getParameter(USER_ROLE)+"'");
        }
        return stringJoiner.toString();
    }

    private static boolean isNotNull(Object obj) {
        return obj != null && ((String) obj).length()>0 && !obj.equals("0");
    }

    private FilterUtil() {
    }
}
