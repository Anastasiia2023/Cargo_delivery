package ua.cargo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String status;

    private String packageType;
    private long paymentId;
    private String paymentStatus;
    private String paymentType;
    private LocalDate estimatedDeliveryDate;
    private LocalDate creationDate;
    private double weight;
    private double volume;
    private long routeId;

    private String cityFrom;

    private String cityTo;
    private String addressFrom;
    private String addressTo;
    private double price;

    private long userId;

    private boolean invoiced;
    private boolean waitingPayment;

    public OrderDTO(double weight, double volume, long routeId, double price, String packageType, long userId) {
        this.weight = weight;
        this.volume = volume;
        this.routeId = routeId;
        this.price = price;
        this.packageType = packageType;
        this.userId = userId;
    }

    public OrderDTO(long id, String status, LocalDate estimatedDeliveryDate, LocalDate creationDate, double weight,
                    double volume, long routeId, String cityFrom, String cityTo, double price,
                    long userId, boolean invoiced, boolean waitingPayment) {
        this.id = id;
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.creationDate = creationDate;
        this.weight = weight;
        this.volume = volume;
        this.routeId = routeId;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.price = price;
        this.userId = userId;
        this.invoiced = invoiced;
        this.waitingPayment = waitingPayment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
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

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
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
