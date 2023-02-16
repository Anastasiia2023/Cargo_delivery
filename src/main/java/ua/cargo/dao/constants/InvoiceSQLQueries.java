package ua.cargo.dao.constants;

public final class InvoiceSQLQueries {
    public static final String ADD_INVOICE = "INSERT INTO invoice (id, date, path, order_id, user_id) VALUES (?,?,?,?,?)";
    public static final String GET_INVOICES = "SELECT * FROM invoice";
    public static final String DELETE_INVOICE = "DELETE FROM invoice WHERE id=?";
    public static final String GET_INVOICE_BY_ID = "SELECT * FROM invoice WHERE id=?";

    private InvoiceSQLQueries() {}
}

