package com.company;

public class AvionBronze extends Avion{

    protected boolean catering;
    public AvionBronze (int capacidad_de_combustible, int costo_km, int capacidad_de_pasajeros, double velocidad_maxima, Propulsion propulsion, boolean catering){
        super( capacidad_de_combustible,  costo_km,  capacidad_de_pasajeros,  velocidad_maxima,  propulsion);
        catering=false;
    }

}
