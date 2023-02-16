package ua.cargo.entities;

public class City {
    private static final long serialVersionUID = 1L;
    private long id;
    private String city;

    public City(long id) {
        this.id = id;
    }

    public City(long id, String city) {
        this.id =id;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
