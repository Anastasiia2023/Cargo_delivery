package ua.cargo.dao.constants;

public final class ReportSQLQueries {
    public static final String ADD_REPORT = "INSERT INTO report (topic, event_id, user_id) VALUES (?, ?, ?)";

    public static final String EDIT_REPORT = "UPDATE report SET topic=? WHERE id=?";

    public static final String DELETE_REPORT = "DELETE FROM report WHERE id=?";

    private ReportSQLQueries() {}
}