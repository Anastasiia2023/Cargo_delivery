package ua.cargo.entities;

import ua.cargo.entities.enums.OrderStatus;

import java.time.LocalDate;

public class Order {
    private static final long serialVersionUID = 1L;
    private long id;
    private OrderStatus status;
    private Payment payment;
    private LocalDate estimatedDeliveryDate;
    private LocalDate creationDate;
    private double weight;
    private double volume;
    private Route route;
    private String addressFrom;
    private String addressTo;
    private double price;
    private User user;
    private boolean invoiced;
    private boolean waitingPayment;

    public Order(long id) {
        this.id = id;
    }

    public Order(OrderStatus status, Payment payment, LocalDate estimatedDeliveryDate, LocalDate creationDate,
                 double weight, double volume, Route route, String addressFrom, String addressTo, double price, User user) {
        this.status = status;
        this.payment = payment;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.creationDate = creationDate;
        this.weight = weight;
        this.volume = volume;
        this.route = route;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.price = price;
        this.user = user;
    }

    public Order(OrderStatus status, LocalDate estimatedDeliveryDate, LocalDate creationDate, double weight,
                 double volume, Route route, double price, User user) {
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.creationDate = creationDate;
        this.weight = weight;
        this.volume = volume;
        this.route = route;
        this.price = price;
        this.user = user;
    }

    public Order(long id, OrderStatus status, LocalDate estimatedDeliveryDate, LocalDate creationDate,
                 double weight, double volume, Route route, double price, User user, boolean invoiced, boolean waitingPayment) {
        this.id = id;
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.creationDate = creationDate;
        this.weight = weight;
        this.volume = volume;
        this.route = route;
        this.price = price;
        this.user = user;
        this.invoiced = invoiced;
        this.waitingPayment=waitingPayment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public boolean isWaitingPayment() {
        return waitingPayment;
    }

    public void setWaitingPayment(boolean waitingPayment) {
        this.waitingPayment = waitingPayment;
    }
}
