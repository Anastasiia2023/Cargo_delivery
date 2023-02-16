package ua.cargo.services.impl;

import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.ParcelDTO;
import ua.cargo.dto.RouteDTO;
import ua.cargo.exceptions.ServiceException;
import ua.cargo.services.CalculateService;
import ua.cargo.services.RouteService;

import java.time.LocalDate;

public class CalculateServiceImpl implements CalculateService {

    private final RouteService routeService;

    public CalculateServiceImpl(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public double calculateParcel(ParcelDTO parcel) throws ServiceException {
        RouteDTO route = routeService.getById(parcel.getRouteId());
        double weight = getPrimaryWeight(parcel);
        return getTotalCost(parcel.getCost(), parcel.getPackageType(), parcel.isPackageService(), route.getDistance(), weight);
    }

    @Override
    public double calculateOrder(OrderDTO order, boolean packageService) throws ServiceException {
        RouteDTO route = routeService.getById(order.getRouteId());
        LocalDate date = order.getCreationDate();
        date = date.plusDays(route.getDistance()/400);
        order.setEstimatedDeliveryDate(date);
        return getTotalCost(order.getPrice(), order.getPackageType(), packageService, route.getDistance(), order.getWeight());
    }

    private double getTotalCost(double cost, String packageType, boolean packageService, int distance, double weight) {
        double totalCost = cost * 0.05;
        switch (packageType) {
            case "Documents":
                if (packageService) {
                    totalCost += 10;
                }
                if (distance < 800) {
                    totalCost += 60;
                } else {
                    totalCost += 95;
                }
                break;
            case "Package":
                if (packageService) {
                    totalCost += 40;
                }
                int weightCost;
                if (weight > 10) {
                    weightCost = 160;
                } else if (weight > 2) {
                    weightCost = 120;
                } else {
                    weightCost = 75;
                }
                if (distance < 800) {
                    totalCost += weightCost;
                } else {
                    totalCost += weightCost * 2.5;
                }
                break;
            case "Cargo":
                if (packageService) {
                    totalCost += 100;
                }
                if (distance < 800) {
                    totalCost += 9 * weight;
                } else {
                    totalCost += 20 * weight;
                }
        }
        return totalCost;
    }

    private double getPrimaryWeight(ParcelDTO parcel) {
        double volumeWeight = parcel.getLength() * parcel.getWidth() * parcel.getHeight() / 4000;
        return Math.max(volumeWeight, parcel.getWeight());
    }
}
