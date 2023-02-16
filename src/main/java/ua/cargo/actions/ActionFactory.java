package ua.cargo.actions;
import ua.cargo.actions.implementation.*;
import ua.cargo.services.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

import static ua.cargo.actions.constants.ActionNames.*;


public final class ActionFactory {
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        ACTION_MAP.put(DEFAULT_ACTION, new DefaultAction());
        ACTION_MAP.put(SIGN_UP_ACTION, new SignUpAction(ServiceFactory.getInstance().getUserService()));
        ACTION_MAP.put(SIGN_IN_ACTION, new SignInAction(ServiceFactory.getInstance().getUserService()));
        ACTION_MAP.put(SIGN_OUT_ACTION, new SignOutAction());
        ACTION_MAP.put(CHANGE_PASSWORD_ACTION, new ChangePasswordAction(ServiceFactory.getInstance().getUserService()));
        ACTION_MAP.put(DELETE_USER_ACTION, new DeleteUserAction(ServiceFactory.getInstance().getUserService()));
        ACTION_MAP.put(EDIT_PROFILE_ACTION, new EditProfileAction(ServiceFactory.getInstance().getUserService()));
        ACTION_MAP.put(CREATE_ROUTE_ACTION, new CreateRouteAction(ServiceFactory.getInstance().getRouteService(), ServiceFactory.getInstance().getCityService()));
        ACTION_MAP.put(CREATE_CITY_ACTION, new CreateCityAction(ServiceFactory.getInstance().getCityService()));
        ACTION_MAP.put(DELETE_CITY_ACTION, new DeleteCityAction(ServiceFactory.getInstance().getCityService()));
        ACTION_MAP.put(CALCULATE_ACTION, new CalculateAction(ServiceFactory.getInstance().getCityService(), ServiceFactory.getInstance().getRouteService(), ServiceFactory.getInstance().getCalculateService()));
        ACTION_MAP.put(DIRECTIONS_ACTION, new DirectionsAction(ServiceFactory.getInstance().getCityService(), ServiceFactory.getInstance().getRouteService()));
        ACTION_MAP.put(CREATE_ORDER_ACTION, new CreateOrderAction(ServiceFactory.getInstance().getRouteService(), ServiceFactory.getInstance().getOrderService()));
        ACTION_MAP.put(MY_ORDER_ACTION, new MyOrderAction(ServiceFactory.getInstance().getOrderService()));
        ACTION_MAP.put(MANAGE_ORDER_ACTION, new ManageOrderAction(ServiceFactory.getInstance().getOrderService(), ServiceFactory.getInstance().getCityService()));
        ACTION_MAP.put(DOWNLOAD_REPORT_ACTION, new ReportPdfAction(ServiceFactory.getInstance().getOrderService(), ServiceFactory.getInstance().getPdfService()));
        ACTION_MAP.put(ROUTES_ACTION, new RoutesAction());
        ACTION_MAP.put(INVOICE_ACTION, new InvoiceAction(ServiceFactory.getInstance().getInvoiceService()));
        ACTION_MAP.put(PAY_ACTION, new PayAction(ServiceFactory.getInstance().getOrderService()));
        ACTION_MAP.put(MANAGE_ROLE_ACTION, new ManageRoleAction(ServiceFactory.getInstance().getUserService()));
    }

    private ActionFactory() {}

    public static ActionFactory getActionFactory() {
        return ACTION_FACTORY;
    }

    public Action createAction(String actionName) {
        return ACTION_MAP.getOrDefault(actionName, new DefaultAction());
    }
}