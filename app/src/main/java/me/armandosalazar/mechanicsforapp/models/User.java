package me.armandosalazar.mechanicsforapp.models;

public class User {
    private String email;
    private String password;
    private boolean registered;

    public User() {
        this.email = "none";
        this.password = "none";
        this.registered = false;
    }

    public User(String email, String password, boolean registered) {
        this.email = email;
        this.password = password;
        this.registered = registered;
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
