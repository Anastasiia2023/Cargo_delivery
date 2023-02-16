package ua.cargo.dao.impl;


import ua.cargo.dao.*;

public class DAOFactory extends ua.cargo.dao.DAOFactory {
    private UserDAO userDAO;
    private RouteDAO routeDAO;
    private CityDAO cityDAO;
    private OrderDAO orderDAO;
    private InvoiceDAO invoiceDAO;

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
    public RouteDAO getRouteDAO() {
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl(getCityDAO());
        }
        return routeDAO;
    }

    @Override
    public CityDAO getCityDAO() {
        if (cityDAO == null) {
            cityDAO = new CityDAOImpl();
        }
        return cityDAO;
    }

    @Override
    public OrderDAO getOrderDAO() {
        if (orderDAO == null) {
            orderDAO = new OrderDAOImpl(getRouteDAO(), getUserDAO());
        }
        return orderDAO;
    }

    @Override
    public InvoiceDAO getInvoiceDAO() {
        if (invoiceDAO == null) {
            invoiceDAO = new InvoiceDAOImpl(getUserDAO(), getOrderDAO());
        }
        return invoiceDAO;
    }

}