package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class Avion implements Serializable {
    protected int capacidad_de_combustible;
    protected int costo_km; //COSTO POR KILOMETRO
    protected int capacidad_de_pasajeros; //CAPACIDAD MAXIMA DE ESTE AVION
    protected double velocidad_maxima; //VELOCIDAD EN KM/H
    protected  Propulsion propulsion; //TIPO DE PROPULSION DEL AVION
    protected boolean catering;
    protected boolean wifi;
    protected TipoDeAvion tipoDeAvion;

    public Avion(){

    }

    public Avion (int capacidad_de_combustible, int costo_km, int capacidad_de_pasajeros, double velocidad_maxima, Propulsion propulsion, boolean catering, boolean wifi){
        this.capacidad_de_combustible=capacidad_de_combustible;
        this.capacidad_de_pasajeros=capacidad_de_pasajeros;
        this.costo_km=costo_km;
        this.velocidad_maxima=velocidad_maxima;
        this.propulsion=propulsion;
        this.catering = catering;
        this.wifi = wifi;
    }

    public static void cargarAvionNuevo() {

        //Levanta el archivo de Aviones

        ArrayList<Avion> listaDeAviones = Archivos.levantarAviones();
        Scanner miScanner = new Scanner(System.in);

        int respuestaInt;
        Avion nuevoAvion;
        do { //Selecciona el tipo de Avion y asigna los campos correspondientes a partir de ello
            LimpiarConsola.limpiar_consola();
            System.out.println("Ingrese el tipo de Avion nuevo:\n 1.Gold\n 2.Silver\n 3.Bronze\n 0.Salir\n Respuesta: ");
            respuestaInt = miScanner.nextInt();
            switch (respuestaInt) {
                case 1:
                    Avion.cargarAvionGold(listaDeAviones);
                    break;
                case 2:
                    Avion.cargarAvionSilver(listaDeAviones);
                    break;
                case 3:
                    Avion.cargarAvionBronze(listaDeAviones);
                case 0:
                    return;
                default:
                    System.out.println("Respuesta invalida, reintente por favor.");
            }
        }while (respuestaInt<0 || respuestaInt>3);

    }

    public static void cargarAvionGold(ArrayList<Avion> listaDeAviones) {

        Scanner miScanner = new Scanner(System.in);
        AvionGold nuevoAvion = new AvionGold();
        nuevoAvion.setTipoDeAvion(TipoDeAvion.GOLD);


        try {

            int respuestaInt;

            nuevoAvion.catering = true;
            System.out.println(" Este avion posee WiFi?\n 1.Si\n 2.No");
            respuestaInt = miScanner.nextInt();
            if(respuestaInt==1) {
                nuevoAvion.wifi = true;
            } else { nuevoAvion.wifi = false;}

            System.out.println(" Ingrese la capacidad de combustible del avion: (Lt)");
            respuestaInt = miScanner.nextInt();
            nuevoAvion.capacidad_de_combustible = respuestaInt;

            do {
                System.out.println(" Ingrese el costo por Km de este avion: (Entre 150 y 300)");
                respuestaInt = miScanner.nextInt();
                if (respuestaInt<150 || respuestaInt>300) {
                    System.out.println(" Ingrese un costo valido por favor.");
                }
                else {nuevoAvion.costo_km = respuestaInt;}
            }while (respuestaInt<150 || respuestaInt>300);
            nuevoAvion.costo_km = respuestaInt;

            System.out.println("Ingrese la capacidad maxima de pasajeros: ");
            nuevoAvion.capacidad_de_pasajeros = miScanner.nextInt();

            System.out.println(" Ingrese la velocidad maxima del avion: ");
            nuevoAvion.velocidad_maxima = miScanner.nextInt();

            do {
                System.out.println(" Elija el tipo de propulsion:\n 1.Reaccion\n 2.Helice\n 3.Pistones");
                respuestaInt = miScanner.nextInt();
                switch (respuestaInt) {
                    case 1:
                        nuevoAvion.propulsion = Propulsion.REACCION;
                        break;
                    case 2:
                        nuevoAvion.propulsion = Propulsion.ELICE;
                        break;
                    case 3:
                        nuevoAvion.propulsion = Propulsion.PISTONES;
                        break;
                    default:
                        System.out.println("Elija una opcion valida por favor.");
                        break;
                }

            } while (respuestaInt>3 || respuestaInt<1);

            listaDeAviones.add(nuevoAvion);
            Archivos.guardarAviones(listaDeAviones);



        } catch (InputMismatchException e) {
            try {
                System.out.println("Ingreso un campo invalido, reintente por favor.");
                Thread.sleep(2000);
            } catch (InterruptedException sleepFail) {
                System.out.println("Error de pausa/sleep.");
            }
        }

    }
    public static void cargarAvionSilver(ArrayList<Avion> listaDeAviones) {

        Scanner miScanner = new Scanner(System.in);
        AvionSilver nuevoAvion = new AvionSilver();
        nuevoAvion.setTipoDeAvion(TipoDeAvion.SILVER);


        try {

            int respuestaInt;

            nuevoAvion.catering = true;
            nuevoAvion.wifi = false;

            System.out.println(" Ingrese la capacidad de combustible del avion: (Lt)");
            respuestaInt = miScanner.nextInt();
            nuevoAvion.capacidad_de_combustible = respuestaInt;

            do {
                System.out.println(" Ingrese el costo por Km de este avion: (Entre 150 y 300)");
                respuestaInt = miScanner.nextInt();
                if (respuestaInt<150 || respuestaInt>300) {
                    System.out.println(" Ingrese un costo valido por favor.");
                }
                else {nuevoAvion.costo_km = respuestaInt;}
            }while (respuestaInt<150 || respuestaInt>300);
            nuevoAvion.costo_km = respuestaInt;

            System.out.println("Ingrese la capacidad maxima de pasajeros: ");
            nuevoAvion.capacidad_de_pasajeros = miScanner.nextInt();

            System.out.println(" Ingrese la velocidad maxima del avion: ");
            nuevoAvion.velocidad_maxima = miScanner.nextInt();

            do {
                System.out.println(" Elija el tipo de propulsion:\n 1.Reaccion\n 2.Helice\n 3.Pistones");
                respuestaInt = miScanner.nextInt();
                switch (respuestaInt) {
                    case 1:
                        nuevoAvion.propulsion = Propulsion.REACCION;
                        break;
                    case 2:
                        nuevoAvion.propulsion = Propulsion.ELICE;
                        break;
                    case 3:
                        nuevoAvion.propulsion = Propulsion.PISTONES;
                        break;
                    default:
                        System.out.println("Elija una opcion valida por favor.");
                        break;
                }

            } while (respuestaInt>3 || respuestaInt<1);

            listaDeAviones.add(nuevoAvion);
            Archivos.guardarAviones(listaDeAviones);

        } catch (InputMismatchException e) {
            try {
                System.out.println("Ingreso un campo invalido, reintente por favor.");
                Thread.sleep(2000);
            } catch (InterruptedException sleepFail) {
                System.out.println("Error de pausa/sleep.");
            }
        }

    }
    public static void cargarAvionBronze(ArrayList<Avion> listaDeAviones) {

        Scanner miScanner = new Scanner(System.in);
        AvionBronze nuevoAvion = new AvionBronze();
        nuevoAvion.setTipoDeAvion(TipoDeAvion.BRONZE);


        try {

            int respuestaInt;

            nuevoAvion.catering = false;
            nuevoAvion.wifi = false;

            System.out.println(" Ingrese la capacidad de combustible del avion: (Lt)");
            respuestaInt = miScanner.nextInt();
            nuevoAvion.capacidad_de_combustible = respuestaInt;

            do {
                System.out.println(" Ingrese el costo por Km de este avion: (Entre 150 y 300)");
                respuestaInt = miScanner.nextInt();
                if (respuestaInt<150 || respuestaInt>300) {
                    System.out.println(" Ingrese un costo valido por favor.");
                }
                else {nuevoAvion.costo_km = respuestaInt;}
            }while (respuestaInt<150 || respuestaInt>300);
            nuevoAvion.costo_km = respuestaInt;

            System.out.println("Ingrese la capacidad maxima de pasajeros: ");
            nuevoAvion.capacidad_de_pasajeros = miScanner.nextInt();

            System.out.println(" Ingrese la velocidad maxima del avion: ");
            nuevoAvion.velocidad_maxima = miScanner.nextInt();

            do {
                System.out.println(" Elija el tipo de propulsion:\n 1.Reaccion\n 2.Helice\n 3.Pistones");
                respuestaInt = miScanner.nextInt();
                switch (respuestaInt) {
                    case 1:
                        nuevoAvion.propulsion = Propulsion.REACCION;
                        break;
                    case 2:
                        nuevoAvion.propulsion = Propulsion.ELICE;
                        break;
                    case 3:
                        nuevoAvion.propulsion = Propulsion.PISTONES;
                        break;
                    default:
                        System.out.println("Elija una opcion valida por favor.");
                        break;
                }

            } while (respuestaInt>3 || respuestaInt<1);

            listaDeAviones.add(nuevoAvion);
            Archivos.guardarAviones(listaDeAviones);

        } catch (InputMismatchException e) {
            try {
                System.out.println("Ingreso un campo invalido, reintente por favor.");
                Thread.sleep(2000);
            } catch (InterruptedException sleepFail) {
                System.out.println("Error de pausa/sleep.");
            }
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return capacidad_de_combustible == avion.capacidad_de_combustible &&
                costo_km == avion.costo_km &&
                capacidad_de_pasajeros == avion.capacidad_de_pasajeros &&
                Double.compare(avion.velocidad_maxima, velocidad_maxima) == 0 &&
                catering == avion.catering &&
                wifi == avion.wifi &&
                propulsion == avion.propulsion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacidad_de_combustible, costo_km, capacidad_de_pasajeros, velocidad_maxima, propulsion, catering, wifi);
    }

    @Override
    public String toString() {
        return  tipoDeAvion + ">> Capacidad de combustible: "+capacidad_de_combustible+"|| Capacidad de pasajeros: "+ capacidad_de_pasajeros+
                "|| Costo por kilometro: "+ costo_km+ "\n\t Velocidad maxima: "+velocidad_maxima+ "|| Tipo de propulsion: " + propulsion
                + "|| Catering: " + poseeCatering() + "|| Wifi: " + poseeWifi();
    }

    public String poseeCatering(){
        if(catering){
            return ("Si");
        }
        else{
            return ("No");
        }
    }

    public String poseeWifi(){
        if(wifi){
            return ("Si");
        }
        else{
            return ("No");
        }
    }

    public int getCapacidad_de_combustible() {
        return capacidad_de_combustible;
    }

    public void setCapacidad_de_combustible(int capacidad_de_combustible) {
        this.capacidad_de_combustible = capacidad_de_combustible;
    }

    public int getCosto_km() {
        return costo_km;
    }

    public void setCosto_km(int costo_km) {
        this.costo_km = costo_km;
    }

    public int getCapacidad_de_pasajeros() {
        return capacidad_de_pasajeros;
    }

    public void setCapacidad_de_pasajeros(int capacidad_de_pasajeros) {
        this.capacidad_de_pasajeros = capacidad_de_pasajeros;
    }

    public double getVelocidad_maxima() {
        return velocidad_maxima;
    }

    public void setVelocidad_maxima(double velocidad_maxima) {
        this.velocidad_maxima = velocidad_maxima;
    }

    public Propulsion getPropulsion() {
        return propulsion;
    }

    public void setPropulsion(Propulsion propulsion) {
        this.propulsion = propulsion;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public TipoDeAvion getTipoDeAvion() {
        return tipoDeAvion;
    }

    public void setTipoDeAvion(TipoDeAvion tipoDeAvion) {
        this.tipoDeAvion = tipoDeAvion;
    }
}

