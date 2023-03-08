package ua.cargo.dao.constants;

public final class UserSQLQueries {
    public static final String ADD_USER = "INSERT INTO user (email, password, name, surname, phone, birthday, role) VALUES (?,?,?,?,?,?,?)";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
    public static final String GET_USERS = "SELECT * FROM user";
    public static final String GET_SORTED = "SELECT * FROM user %s";
    public static final String GET_NUMBER_OF_RECORDS = "SELECT COUNT(id) AS numberOfRecords FROM user %s";
    public static final String UPDATE_USER = "UPDATE user SET email=?, name=?, surname=?, phone=?, birthday=?, address=? WHERE id=?";
    public static final String UPDATE_PASSWORD = "UPDATE user SET password=? WHERE id=?";
    public static final String UPDATE_ROLE = "UPDATE user SET role=? WHERE id=?";
    public static final String SET_ROLE = "UPDATE user SET role_id=? WHERE email=?";

    public static final String GET_USERS_PAGINATED = "SELECT * FROM user %s LIMIT ? OFFSET ?";
    public static final String GET_USERS_PAGINATED_ORDERED = "SELECT * FROM user %s ORDER BY ? ? LIMIT ? OFFSET ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE id=?";


    private UserSQLQueries() {}
}