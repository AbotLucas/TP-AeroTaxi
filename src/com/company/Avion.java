package com.company;

public class Avion {
    protected int capacidad_de_combustible;
    protected int costo_km; //COSTO POR KILOMETRO
    protected int capacidad_de_pasajeros; //CAPACIDAD MAXIMA DE ESTE AVION
    protected double velocidad_maxima; //VELOCIDAD EN KM/H
    protected  Propulsion propulsion; //TIPO DE PROPULSION DEL AVION

    public Avion (int capacidad_de_combustible, int costo_km, int capacidad_de_pasajeros, double velocidad_maxima, Propulsion propulsion){
        this.capacidad_de_combustible=capacidad_de_combustible;
        this.capacidad_de_pasajeros=capacidad_de_pasajeros;
        this.costo_km=costo_km;
        this.velocidad_maxima=velocidad_maxima;
        this.propulsion=propulsion;
    }

    @Override
    public String toString() {
        return  "Capacidad de combustible: "+capacidad_de_combustible+"Capacidad de pasajeros: "+ capacidad_de_pasajeros+
                "Costo por kilometro: "+ costo_km+ "Velocidad maxima: "+velocidad_maxima+ "Tipo de propulsion: " + propulsion;
    }

}
