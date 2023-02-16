package ua.cargo.entities;

import java.time.LocalDate;

public class Invoice {
    private static final long serialVersionUID = 1L;
    private long id;
    LocalDate date;
    private String path;
    private User user;
    private Order order;

    public Invoice(long id) {
        this.id = id;
    }

    public Invoice(long id, LocalDate date, String path, User user, Order order) {
        this.id = id;
        this.date = date;
        this.path = path;
        this.user = user;
        this.order = order;
    }

    public Invoice(User user, Order order) {
        this.user = user;
        this.order = order;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
