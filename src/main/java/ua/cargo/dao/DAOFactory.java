package ua.cargo.dao;


public abstract class DAOFactory {
    private static DAOFactory instance;

    protected DAOFactory() {}

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new ua.cargo.dao.impl.DAOFactory();
        }

        return instance;
    }

    public abstract UserDAO getUserDAO();

    public abstract RouteDAO getRouteDAO();
    public abstract CityDAO getCityDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract InvoiceDAO getInvoiceDAO();
}