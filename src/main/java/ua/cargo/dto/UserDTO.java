package ua.cargo.dto;

import ua.cargo.entities.enums.Role;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private LocalDate dateBirth;
    private String address;
    private int role;

    public UserDTO(String name, String surname, String password, String email, String phone, LocalDate dateBirth) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
    }

    public UserDTO(long id, String name, String surname, String email, String phone, LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
    }

    public UserDTO(long id, String name, String surname, String email, String phone, LocalDate dateBirth, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.address = address;
    }

    public UserDTO(long id, String name, String surname, String password, String email, String phone, LocalDate dateBirth, String address, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.address = address;
        this.role = role.getValue();
    }

    public UserDTO(long id, String name, String surname, String password, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String LocalDate) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
