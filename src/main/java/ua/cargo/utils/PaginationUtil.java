package ua.cargo.utils;


import ua.cargo.entities.Pagination;

import javax.servlet.http.HttpServletRequest;

import static ua.cargo.actions.constants.Parameters.*;

public final class PaginationUtil {

    public static void paginate(HttpServletRequest request) {
        int offset = getInt((String) request.getAttribute(OFFSET), 0, 0);
        int records = getInt((String) request.getAttribute(RECORDS), 1, 5);
        int totalRecords = getInt(request.getAttribute(TOTAL_RECORDS)+"", 0, 0);
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

    public static Pagination getPaginationData(HttpServletRequest request) {
        Pagination paginationData = new Pagination();
        paginationData.setOffset(getInt(request.getParameter(OFFSET),0, 0));
        paginationData.setRecords(getInt(request.getParameter(RECORDS), 1, 5));
        paginationData.setOrder(request.getParameter(ORDER));
        request.getSession().setAttribute(OFFSET, paginationData.getOffset());
        request.getSession().setAttribute(RECORDS, paginationData.getRecords());
        return paginationData;
    }

    public static boolean isOrdered(Pagination pagination) {
        return pagination.getSortField() != null;
    }

    private PaginationUtil() {}
}
