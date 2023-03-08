package ua.cargo.entities;

import ua.cargo.entities.enums.Role;

import java.time.LocalDate;

public class User {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private LocalDate dateBirth;
    private String address;
    private Role role;

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, String surname, String password, String email, String phone, LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
    }
    public User(long id, String name, String surname, String password, String email, String phone, LocalDate dateBirth, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.address = address;
    }
    public User(long id, String name, String surname, String password, String email, String phone, LocalDate dateBirth, String address, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.address = address;
        this.role = role;
    }
    public User(long id, String name, String surname, String password, String email, String phone, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public User(String name, String surname, String password, String email, String phone, LocalDate dateBirth) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
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

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
