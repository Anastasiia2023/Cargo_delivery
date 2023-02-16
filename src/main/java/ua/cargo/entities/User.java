package ua.cargo.entities;

import ua.cargo.entities.enums.Role;

public class User {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private String date_birth;

    private Role role;

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, String surname, String password, String email, String phone, String date_birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.date_birth = date_birth;
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

    public User(String name, String surname, String password, String email, String phone, String date_birth) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.date_birth = date_birth;
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

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
