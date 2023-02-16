package ua.cargo.utils;

import ua.cargo.dto.*;
import ua.cargo.entities.*;
import ua.cargo.entities.enums.OrderStatus;

public final class ConvertorUtil {

    private ConvertorUtil() {}

    public static Route convertDTOToRoute(RouteDTO routeDTO) {
        return new Route(routeDTO.getId(), new City(routeDTO.getCityFromId()), new City(routeDTO.getCityToId()), routeDTO.getDistance(), routeDTO.getTerms());
    }

    public static RouteDTO convertRouteToDTO(Route route) {
        City cityFrom  = route.getCityFrom();
        City cityTo = route.getCityTo();
        return new RouteDTO(route.getId(), cityFrom.getId(), cityFrom.getCity(), cityTo.getId(), cityTo.getCity(), route.getDistance(), route.getTerms());
    }

    public static CityDTO convertCityToDTO(City city) {
        return new CityDTO(city.getId(), city.getCity());
    }

    public static City convertDTOToCity(CityDTO cityDTO) {
        return new City(cityDTO.getId(), cityDTO.getName());
    }

    public static UserDTO convertUserToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getPassword(), user.getEmail(), user.getPhone(), user.getDate_birth(), user.getRole());
    }

    public static User convertDTOToUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getSurname(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getPhone(), userDTO.getDate_birth());
    }

    public static Order convertDTOToOrder(OrderDTO orderDTO) {
        return new Order(OrderStatus.getOrderStatus(orderDTO.getStatus()),
                orderDTO.getEstimatedDeliveryDate(),
                orderDTO.getCreationDate(),
                orderDTO.getWeight(),
                orderDTO.getVolume(),
                new Route(orderDTO.getRouteId()),
                orderDTO.getPrice(),
                new User(orderDTO.getUserId()));
    }

    public static OrderDTO convertOrderToDTO(Order order) {
        User user = order.getUser();
        Route route = order.getRoute();
        return new OrderDTO(order.getId(),
                order.getStatus().getValue(),
                order.getEstimatedDeliveryDate(),
                order.getCreationDate(),
                order.getWeight(),
                order.getVolume(),
                route.getId(),
                route.getCityFrom().getCity(),
                route.getCityTo().getCity(),
                order.getPrice(),
                user.getId(),
                order.isInvoiced(),
                order.isWaitingPayment());
    }


    public static InvoiceDTO convertInvoiceToDTO(Invoice invoice) {
        return new InvoiceDTO(invoice.getId(),
                invoice.getDate(),
                invoice.getPath(),
                invoice.getOrder().getId(),
                invoice.getUser().getId());
    }

    public static Invoice convertDTOToInvoice(InvoiceDTO invoiceDTO) {
        return new Invoice(invoiceDTO.getId(), invoiceDTO.getDate(), invoiceDTO.getPath(), new User(invoiceDTO.getUserId()), new Order(invoiceDTO.getOrderId()));
    }
}