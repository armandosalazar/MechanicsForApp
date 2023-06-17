package me.armandosalazar.mechanicsforapp.models;

import java.io.Serializable;

public class Mechanic extends User implements Serializable {

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
