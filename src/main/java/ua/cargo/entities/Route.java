package ua.cargo.entities;

public class Route {
    private static final long serialVersionUID = 1L;
    private long id;
    private City cityFrom;
    private City cityTo;
    private int distance;
    private int terms;

    public Route(long id) {
        this.id = id;
    }

    public Route(long id, City cityFrom, City cityTo, int distance, int terms) {
        this.id = id;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
        this.terms = terms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
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
