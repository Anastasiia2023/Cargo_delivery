package ua.cargo.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;

    public CityDTO(String name) {
        this.name = name;
    }

    public CityDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
