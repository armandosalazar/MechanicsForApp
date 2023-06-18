package me.armandosalazar.mechanicsforapp.models;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String password;
    private boolean registered;
    private String lastName;
    private String name;
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        this.email = "none";
        this.password = "none";
        this.registered = false;
    }

    public User(String  email, String password, boolean registered) {
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.name = name;
        this.lastName = lastName;
    }

    public User(String name, String lastName, String  email, String password, boolean registered) {
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.name = name;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
