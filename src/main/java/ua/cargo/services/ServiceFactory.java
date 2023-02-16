package ua.cargo.services;


import ua.cargo.dao.DAOFactory;
import ua.cargo.services.impl.*;
import ua.cargo.services.impl.*;

public final class ServiceFactory {

    private final RouteService routeService;
    private final CityService cityService;
    private final UserService userService;
    private final CalculateService calculateService;
    private final OrderService orderService;

    private final PdfService pdfService;
    private final InvoiceService invoiceService;

    private ServiceFactory() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.routeService = new RouteServiceImpl(daoFactory.getRouteDAO());
        this.cityService = new CityServiceImpl(daoFactory.getCityDAO());
        this.userService = new UserServiceImpl(daoFactory.getUserDAO());
        this.calculateService = new CalculateServiceImpl(getRouteService());
        this.orderService = new OrderServiceImpl(daoFactory.getOrderDAO(), calculateService);
        this.pdfService = new PdfService();
        this.invoiceService = new InvoiceServiceImpl(daoFactory.getInvoiceDAO(), pdfService, userService, orderService);
    }

    public static ServiceFactory getInstance() {
        return new ServiceFactory();
    }

    public RouteService getRouteService() {
        return routeService;
    }

    public CityService getCityService() {
        return cityService;
    }

    public UserService getUserService() {
        return userService;
    }

    public CalculateService getCalculateService() {
        return calculateService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public PdfService getPdfService() { return pdfService; }

    public InvoiceService getInvoiceService() { return invoiceService; }
}