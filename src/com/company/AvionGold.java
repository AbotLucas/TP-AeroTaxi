package com.company;

public class AvionGold extends Avion  {

    protected boolean catering;
    protected boolean wifi;
    public AvionGold (int capacidad_de_combustible, int costo_km, int capacidad_de_pasajeros, double velocidad_maxima, Propulsion propulsion, boolean catering, boolean wifi){
        super( capacidad_de_combustible,  costo_km,  capacidad_de_pasajeros,  velocidad_maxima,  propulsion);
        catering=true;
        this.wifi = wifi;
    }
}
