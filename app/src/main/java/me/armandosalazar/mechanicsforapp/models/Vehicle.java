package me.armandosalazar.mechanicsforapp.models;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String brand;
    private String model;
    private String year;
    private String licensePlates;
    private String color;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, String year, String licensePlates, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlates = licensePlates;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
