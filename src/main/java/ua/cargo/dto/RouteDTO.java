package ua.cargo.dto;

import java.io.Serializable;

public class RouteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private long cityFromId;
    private String cityFrom;
    private long cityToId;
    private String cityTo;
    private int distance;
    private int terms;

    public RouteDTO(long cityFromId, long cityToId, int distance, int terms) {
        this.cityFromId = cityFromId;
        this.cityToId = cityToId;
        this.distance = distance;
        this.terms = terms;
    }

    public RouteDTO(long cityFromId, String cityFrom, long cityToId, String cityTo, int distance, int terms) {
        this.cityFromId = cityFromId;
        this.cityFrom = cityFrom;
        this.cityToId = cityToId;
        this.cityTo = cityTo;
        this.distance = distance;
        this.terms = terms;
    }

    public RouteDTO(long id, long cityFromId, String cityFrom, long cityToId, String cityTo, int distance, int terms) {
        this.id = id;
        this.cityFromId = cityFromId;
        this.cityFrom = cityFrom;
        this.cityToId = cityToId;
        this.cityTo = cityTo;
        this.distance = distance;
        this.terms = terms;
    }

    public RouteDTO(long id, long cityFromId, long cityToId, int distance, int terms) {
        this.id = id;
        this.cityFromId = cityFromId;
        this.cityToId = cityToId;
        this.distance = distance;
        this.terms = terms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityFromId() {
        return cityFromId;
    }

    public void setCityFromId(long cityFromId) {
        this.cityFromId = cityFromId;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public long getCityToId() {
        return cityToId;
    }

    public void setCityToId(long cityToId) {
        this.cityToId = cityToId;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTerms() {
        return terms;
    }

    public void setTerms(int terms) {
        this.terms = terms;
    }
}
