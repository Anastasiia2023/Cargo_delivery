package ua.cargo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class InvoiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    long id;
    LocalDate date;
    String path;
    long orderId;
    long userId;

    public InvoiceDTO(long id, LocalDate date, String path, long orderId, long userId) {
        this.id = id;
        this.date = date;
        this.path = path;
        this.orderId = orderId;
        this.userId = userId;
    }

    public InvoiceDTO(long orderId, long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
