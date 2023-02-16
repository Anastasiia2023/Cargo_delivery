package ua.cargo.filters.domain;

import java.util.HashSet;
import java.util.Set;

import static ua.cargo.actions.constants.Pages.*;

public final class DomainPagesSets {
    private DomainPagesSets() {}

    private static final Set<String> ANONYMOUS_USER_PAGES = new HashSet<>();

    private static final Set<String> LOGGED_USER_PAGES = new HashSet<>();
    private static final Set<String> CUSTOMER_PAGES = new HashSet<>();
    private static final Set<String> MANAGER_PAGES = new HashSet<>();
    private static final Set<String> SUPERADMIN_PAGES = new HashSet<>();

    static {
        ANONYMOUS_USER_PAGES.add(CONTROLLER_PAGE);
        ANONYMOUS_USER_PAGES.add(INDEX_PAGE);
        ANONYMOUS_USER_PAGES.add(SIGN_IN_PAGE);
        ANONYMOUS_USER_PAGES.add(SIGN_UP_PAGE);
        ANONYMOUS_USER_PAGES.add(RESET_PASSWORD_PAGE);
        ANONYMOUS_USER_PAGES.add(ERROR_PAGE);
        ANONYMOUS_USER_PAGES.add(ABOUT_PAGE);
        ANONYMOUS_USER_PAGES.add(CONTACTS_PAGE);
        ANONYMOUS_USER_PAGES.add(CALCULATE_PAGE);
        ANONYMOUS_USER_PAGES.add(DIRECTION_PAGE);
    }

    static {
        LOGGED_USER_PAGES.addAll(ANONYMOUS_USER_PAGES);
        LOGGED_USER_PAGES.add(PROFILE_PAGE);
    }

    static {
        CUSTOMER_PAGES.addAll(LOGGED_USER_PAGES);
        CUSTOMER_PAGES.add(CREATE_ORDER_PAGE);
        CUSTOMER_PAGES.add(MY_ORDER_PAGE);

    }
    static {
        MANAGER_PAGES.addAll(LOGGED_USER_PAGES);
        MANAGER_PAGES.add(REPORTS_PAGE);
        MANAGER_PAGES.add(CREATE_CITY_PAGE);
        MANAGER_PAGES.add(CREATE_ROUTE_PAGE);
    }

    static {
        SUPERADMIN_PAGES.addAll(LOGGED_USER_PAGES);
        SUPERADMIN_PAGES.add(ADMIN_PAGE);
    }

    public static Set<String>  getAnonymousUserPages() {
        return ANONYMOUS_USER_PAGES;
    }
    public static Set<String>  getCustomerPages() {
        return CUSTOMER_PAGES;
    }
    public static Set<String>  getManagerPages() {
        return MANAGER_PAGES;
    }
    public static Set<String>  getSuperadminPages() {
        return SUPERADMIN_PAGES;
    }
}