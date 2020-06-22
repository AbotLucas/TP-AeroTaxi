package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vuelo {

    private Date fecha_de_vuelo; //FECHA EN LA QUE SE REALIZA EL VUELO
    private Usuario usuario; //USUARIO QUE CONTRATO EL VUELO
    private Ciudad origen;
    private Ciudad destino;
    private int cantidad_de_pasajeros; //CANTIDAD DE PASAJEROS DE ESTE VUELO
    private Avion avion_asignado; //AVION ASIGNADO A ESTE VUELO

    public Vuelo() {
    }

    public Vuelo(Date fecha_de_vuelo, Usuario usuario, Ciudad origen, Ciudad destino, int cantidad_de_pasajeros, Avion avion_asignado) {
        this.fecha_de_vuelo = fecha_de_vuelo;
        this.usuario = usuario;
        this.origen = origen;
        this.destino = destino;
        this.cantidad_de_pasajeros = cantidad_de_pasajeros;
        this.avion_asignado = avion_asignado;
    }

    public Date getFecha_de_vuelo() {
        return fecha_de_vuelo;
    }

    public void setFecha_de_vuelo(Date fecha_de_vuelo) {
        this.fecha_de_vuelo = fecha_de_vuelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public int getCantidad_de_pasajeros() {
        return cantidad_de_pasajeros;
    }

    public void setCantidad_de_pasajeros(int cantidad_de_pasajeros) {
        this.cantidad_de_pasajeros = cantidad_de_pasajeros;
    }

    public Avion getAvion_asignado() {
        return avion_asignado;
    }

    public void setAvion_asignado(Avion avion_asignado) {
        this.avion_asignado = avion_asignado;
    }

    public int calcular_tarifa_de_avion() {
        int tarifa_tipo_de_avion = 0;
        if (avion_asignado instanceof AvionGold) {
            tarifa_tipo_de_avion = 6000;
        } else if (avion_asignado instanceof AvionSilver) {
            tarifa_tipo_de_avion = 4000;
        } else if (avion_asignado instanceof AvionBronze) {
            tarifa_tipo_de_avion = 3000;
        } else {
            System.out.println("este no es un avion valido");
        }
        return tarifa_tipo_de_avion;
    }

    public int calcular_tarifa_por_persona() {
        int tarifa_pasajero = 3500 * cantidad_de_pasajeros;
        return tarifa_pasajero;
    }

    public int calcular_kilometro_de_viaje() {
        int km_del_viaje = 0;

        if ((origen == Ciudad.BUENOS_AIRES && destino == Ciudad.CORDOBA) || (destino == Ciudad.BUENOS_AIRES && origen == Ciudad.CORDOBA)) {
            km_del_viaje = 695;
        } else if ((origen == Ciudad.BUENOS_AIRES && destino == Ciudad.SANTIAGO) || (origen == Ciudad.BUENOS_AIRES && destino == Ciudad.SANTIAGO)) {
            km_del_viaje = 1400;
        } else if ((origen == Ciudad.BUENOS_AIRES && destino == Ciudad.MONTEVIDEO) || (origen == Ciudad.BUENOS_AIRES && destino == Ciudad.MONTEVIDEO)) {
            km_del_viaje = 950;
        } else if ((origen == Ciudad.CORDOBA && destino == Ciudad.MONTEVIDEO) || (origen == Ciudad.CORDOBA && destino == Ciudad.MONTEVIDEO)) {
            km_del_viaje = 1190;
        } else if ((origen == Ciudad.CORDOBA && destino == Ciudad.SANTIAGO) || (origen == Ciudad.CORDOBA && destino == Ciudad.SANTIAGO)) {
            km_del_viaje = 1050;
        } else if ((origen == Ciudad.MONTEVIDEO && destino == Ciudad.SANTIAGO) || (origen == Ciudad.MONTEVIDEO && destino == Ciudad.SANTIAGO)) {
            km_del_viaje = 2100;
        }
        return km_del_viaje;
    }

    public int calcular_tarifa_por_km() {
        int tarifa_km_costo = 0;
        int km = calcular_kilometro_de_viaje();
        tarifa_km_costo = avion_asignado.costo_km * km;

        return tarifa_km_costo;
    }

    public void calcular_costo() {
        int total = calcular_tarifa_por_km() + calcular_tarifa_de_avion() + calcular_tarifa_por_persona();
        System.out.println("\nKM * costo KM: " + calcular_tarifa_por_km());
        System.out.println("\navion: " + calcular_tarifa_de_avion());
        System.out.println("\npor persona: " + calcular_tarifa_por_persona());
        System.out.println("\nEl costo total del vuelo es de: " + total);
    }

    //MUESTRA POR PANTALLA LA LISTA DE VUELOS DEL USUARIO.
    public void mis_vuelos(Usuario u, List<Vuelo> vuelos) {
        int i = 0;
        int x = 1;
        List<Vuelo> v = new ArrayList<Vuelo>();
        v = vuelos_de_usuario(u,vuelos);
        while (i < v.size()) {
                System.out.println("Vuelo numero " + x + v.get(i).toString() + "\n");
                x++;
            i++;
        }
    }
    //DEVUELVE UN ARRAYLIST DE VUELOS, SOLO CON LOS VUELOS DEL USUARIO PASADO POR PARAMETRO
    public List<Vuelo> vuelos_de_usuario (Usuario u, List<Vuelo> vuelos) {
        int i = 0;
        int x = 1;
        List<Vuelo> v = new ArrayList<Vuelo>();
        while (i < vuelos.size()) {
            if (u.equals(vuelos.get(i).usuario)) {
                v.add(vuelos.get(i));
                x++;
            }
            i++;
        }
        return v;
    }


    @Override
    public String toString() {
        return "\nUsuario : " + usuario.toString() + "\nFecha de vuelo: " + fecha_de_vuelo + "\nOrigen del vuelo: " + origen +
                "\nDestino del vuelo: " + destino + "\nCantidad de pasajeros: " + cantidad_de_pasajeros + "\nAvion asignado: " + avion_asignado.toString();
    }
    //
    public void vuelos_por_fecha(Usuario u, Date d, List<Vuelo> vuelos) {
        int i = 0;
        int x=0;
        List<Vuelo> v = new ArrayList<Vuelo>();
        v=vuelos_de_usuario(u,vuelos);
        while (i < v.size()) {
            if (d == v.get(i).fecha_de_vuelo) {
                System.out.println("\nEl vuelo marcado como numero : "+ i + v.get(i).toString());
                x++;
            }
            i++;
        }
        if (x==0){
            System.out.println("\nNo hay vuelos para esta fecha");
        }

    }



}

