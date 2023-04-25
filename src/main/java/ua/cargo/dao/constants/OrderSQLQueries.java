package ua.cargo.dao.constants;

public final class OrderSQLQueries {
    public static final String ADD_ORDER = "INSERT INTO orders (statuses, delivery_date, creation_date, weight, volume, route_id, price, user_id, invoiced, waiting_payment) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_ORDER = "SELECT * FROM orders";
    public static final String GET_ORDERS_BY_USER_PAGINATED = "SELECT * FROM orders WHERE user_id=? %s LIMIT ? OFFSET ?";
    public static final String GET_ORDERS_BY_USER_PAGINATED_ORDERED = "SELECT * FROM orders WHERE user_id=? %s ORDER BY %s %s LIMIT ? OFFSET ?";
    public static final String GET_ORDERS = "SELECT * FROM orders %s";
    public static final String GET_ORDERS_PAGINATED = "SELECT * FROM orders %s LIMIT ? OFFSET ?";
    public static final String GET_ORDERS_PAGINATED_ORDERED = "SELECT * FROM orders %s ORDER BY %s %s LIMIT ? OFFSET ?";
    public static final String GET_NUMBER_OF_RECORDS_BY_USER_ID = "SELECT COUNT(*) AS numberOfRecords FROM orders WHERE user_id=? %s";
    public static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) AS numberOfRecords FROM orders %s";
    public static final String JOIN_ROUTE = " as o JOIN route as r ON o.route_id = r.id ";
    public static final String UPDATE_ORDER_INVOICE = "UPDATE orders SET invoiced=?, statuses=? WHERE id=?";
    public static final String UPDATE_PAYMENT = "UPDATE orders SET waiting_payment=?, statuses=? WHERE id=?";
    public static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";
    public static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";

    private OrderSQLQueries() {}
}