package ua.cargo.entities.enums;

public enum OrderStatus {
    PLACED("Placed"),
    PROCESSED("Processed"),
    PAYED("Payed"),
    WAITING_CARGO("Waiting Parcel"),
    ON_THE_WAY("On the way"),
    DELIVERED("Delivered"),
    ERROR("Error");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static OrderStatus getOrderStatus(String value) {
        if (value == null) return ERROR;
        for (OrderStatus orderStatus: OrderStatus.values()) {
            if (orderStatus.value.equals(value)) {
                return orderStatus;
            }
        }
        return ERROR;
    }
}
