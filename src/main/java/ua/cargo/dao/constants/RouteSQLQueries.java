package ua.cargo.dao.constants;

public final class RouteSQLQueries {
    public static final String ADD_ROUTE = "INSERT INTO route (city_from_id, city_to_id, distance, terms) VALUES (?,?,?,?)";
    public static final String GET_ROUTES = "SELECT * FROM route";
    public static final String GET_ROUTES_PAGINATED = "SELECT * FROM route LIMIT ? OFFSET ?";
    public static final String GET_ROUTES_PAGINATED_ORDERED = "SELECT * FROM route ORDER BY %s %s LIMIT ? OFFSET ?";
    public static final String UPDATE_ROUTE = "UPDATE route SET distance=? WHERE id=?";
    public static final String DELETE_ROUTE = "DELETE FROM route WHERE id=?";
    public static final String GET_ROUTE_BY_ID = "SELECT * FROM route WHERE id=?";
    public static final String GET_ROUTE_BY_CITIES = "SELECT * FROM route WHERE city_from_id=? AND city_to_id=?";

    public static final String GET_ROUTE_BY_CITIES_PAGINATED = "SELECT * FROM route WHERE city_from_id=? AND city_to_id=? LIMIT ? OFFSET ?";

    public static final String GET_ROUTE_BY_CITIES_PAGINATED_ORDERED = "SELECT * FROM route WHERE city_from_id=? AND city_to_id=? ORDER BY %s %s LIMIT ? OFFSET ?";
    public static final String GET_ROUTES_BY_CITY_FROM = "SELECT * FROM route WHERE city_from_id=?";
    public static final String GET_ROUTES_BY_CITY_FROM_PAGINATED = "SELECT * FROM route WHERE city_from_id=? LIMIT ? OFFSET ?";
    public static final String GET_ROUTES_BY_CITY_FROM_PAGINATED_ORDERED = "SELECT * FROM route WHERE city_from_id=? ORDER BY %s %s LIMIT ? OFFSET ?";
    public static final String GET_ROUTES_BY_CITY_TO = "SELECT * FROM route WHERE city_to_id=?";
    public static final String GET_ROUTES_BY_CITY_TO_PAGINATED = "SELECT * FROM route WHERE city_to_id=? LIMIT ? OFFSET ?";
    public static final String GET_ROUTES_BY_CITY_TO_PAGINATED_ORDERED = "SELECT * FROM route WHERE city_to_id=? ORDER BY %s %s LIMIT ? OFFSET ?";
    public static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(*) AS numberOfRecords FROM route";
    public static final String GET_NUMBER_OF_RECORDS_BY_CITY_FROM = "SELECT COUNT(*) AS numberOfRecords FROM route WHERE city_from_id=?";
    public static final String GET_NUMBER_OF_RECORDS_BY_CITY_TO = "SELECT COUNT(*) AS numberOfRecords FROM route WHERE city_to_id=?";

    private RouteSQLQueries() {}
}