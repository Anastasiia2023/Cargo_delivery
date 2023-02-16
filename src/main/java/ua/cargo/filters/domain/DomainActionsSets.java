package ua.cargo.filters.domain;

import java.util.*;

import static ua.cargo.actions.constants.ActionNames.*;

public final class DomainActionsSets {
    private DomainActionsSets() {}

    private static final Set<String> ANONYMOUS_USER_ACTIONS = new HashSet<>();
    private static final Set<String> LOGGED_USER_ACTIONS = new HashSet<>();
    private static final Set<String> CUSTOMER_ACTIONS = new HashSet<>();
    private static final Set<String> MANAGER_ACTIONS = new HashSet<>();
    private static final Set<String> SUPERADMIN_ACTIONS = new HashSet<>();

    static {
        ANONYMOUS_USER_ACTIONS.add(DEFAULT_ACTION);
        ANONYMOUS_USER_ACTIONS.add(SIGN_IN_ACTION);
        ANONYMOUS_USER_ACTIONS.add(SIGN_UP_ACTION);
        ANONYMOUS_USER_ACTIONS.add(PASSWORD_RESET_ACTION);
        ANONYMOUS_USER_ACTIONS.add(ERROR_ACTION);
        ANONYMOUS_USER_ACTIONS.add(SIGN_OUT_ACTION);
        ANONYMOUS_USER_ACTIONS.add(CALCULATE_ACTION);
        ANONYMOUS_USER_ACTIONS.add(DIRECTIONS_ACTION);
    }

    static {
        LOGGED_USER_ACTIONS.addAll(ANONYMOUS_USER_ACTIONS);
        LOGGED_USER_ACTIONS.add(EDIT_PROFILE_ACTION);
        LOGGED_USER_ACTIONS.add(CHANGE_PASSWORD_ACTION);
        LOGGED_USER_ACTIONS.add(DELETE_USER_ACTION);
        LOGGED_USER_ACTIONS.add(INVOICE_ACTION);
        LOGGED_USER_ACTIONS.add(PAY_ACTION);
    }

    static {
        CUSTOMER_ACTIONS.addAll(LOGGED_USER_ACTIONS);
        CUSTOMER_ACTIONS.add(CREATE_ORDER_ACTION);
        CUSTOMER_ACTIONS.add(MY_ORDER_ACTION);
    }

    static {
        MANAGER_ACTIONS.addAll(LOGGED_USER_ACTIONS);
        MANAGER_ACTIONS.add(CREATE_CITY_ACTION);
        MANAGER_ACTIONS.add(DELETE_CITY_ACTION);
        MANAGER_ACTIONS.add(CREATE_ROUTE_ACTION);
        MANAGER_ACTIONS.add(DELETE_ROUTE_ACTION);
        MANAGER_ACTIONS.add(MANAGE_ORDER_ACTION);
        MANAGER_ACTIONS.add(DOWNLOAD_REPORT_ACTION);
        MANAGER_ACTIONS.add(ROUTES_ACTION);
    }

    static {
        SUPERADMIN_ACTIONS.addAll(ANONYMOUS_USER_ACTIONS);
        SUPERADMIN_ACTIONS.add(EDIT_PROFILE_ACTION);
        SUPERADMIN_ACTIONS.add(CHANGE_PASSWORD_ACTION);
        SUPERADMIN_ACTIONS.add(MANAGE_ROLE_ACTION);
    }

    public static Set<String>  getAnonymousUserActions() {
        return ANONYMOUS_USER_ACTIONS;
    }

    public static Set<String>  getCustomerActions() {
        return CUSTOMER_ACTIONS;
    }

    public static Set<String>  getManagerActions() {
        return MANAGER_ACTIONS;
    }

    public static Set<String> getSuperadminActions() {return SUPERADMIN_ACTIONS;}

}