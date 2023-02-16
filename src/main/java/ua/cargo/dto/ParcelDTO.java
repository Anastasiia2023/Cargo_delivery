package ua.cargo.dto;

import java.io.Serializable;

public class ParcelDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    long routeId;
    int cost;
    double weight;
    double length;
    double height;
    double width;
    String packageType;
    boolean packageService;

    public ParcelDTO(long routeId, int cost, double weight, double length, double height, double width, String packageType, boolean packageService) {
        this.routeId = routeId;
        this.cost = cost;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.packageType = packageType;
        this.packageService = packageService;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public boolean isPackageService() {
        return packageService;
    }

    public void setPackageService(boolean packageService) {
        this.packageService = packageService;
    }
}
