package ua.cargo.services;

import ua.cargo.dto.OrderDTO;
import ua.cargo.dto.ParcelDTO;
import ua.cargo.exceptions.ServiceException;

public interface CalculateService {
    double calculateParcel(ParcelDTO parcel) throws ServiceException;

    double calculateOrder(OrderDTO order, boolean packageService) throws ServiceException;
}