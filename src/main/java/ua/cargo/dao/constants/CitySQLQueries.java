package ua.cargo.dao.constants;

public final class CitySQLQueries {
    public static final String ADD_CITY = "INSERT INTO city (city) VALUES (?)";
    public static final String GET_CITIES = "SELECT * FROM city";
    public static final String DELETE_CITY = "DELETE FROM city WHERE id=?";
    public static final String GET_CITY_BY_ID = "SELECT * FROM city WHERE id=?";
    public static final String GET_CITY_BY_NAME = "SELECT * FROM city WHERE name=?";


    private CitySQLQueries() {}
}

