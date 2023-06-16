package me.armandosalazar.mechanicsforapp.models;

public class Mechanic extends User{

    private String rfc;
    private String typeOfMechanic;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTypeOfMechanic() {
        return typeOfMechanic;
    }

    public void setTypeOfMechanic(String typeOfMechanic) {
        this.typeOfMechanic = typeOfMechanic;
    }
}
