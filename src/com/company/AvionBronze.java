package com.company;



import java.io.Serializable;


public class AvionBronze extends Avion implements Serializable {

    public AvionBronze(int capacidad_de_combustible, int costo_km, int capacidad_de_pasajeros,
                       double velocidad_maxima, Propulsion propulsion, boolean catering, boolean wifi) {
        super(capacidad_de_combustible, costo_km, capacidad_de_pasajeros, velocidad_maxima, propulsion,
                catering, wifi);
    }

    public AvionBronze() {

    }

    @Override
    public String toString() {
        return  "Bronze >> Capacidad de combustible: "+capacidad_de_combustible+"|| Capacidad de pasajeros: "+ capacidad_de_pasajeros+
                "|| Costo por kilometro: "+ costo_km+ "\n\t Velocidad maxima: "+velocidad_maxima+ "|| Tipo de propulsion: " + propulsion
                + "|| Catering: " + poseeCatering() + "|| Wifi: " + poseeWifi();
    }
}
