package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Usuario u1 = new Usuario("Lucas", "Abot", 40138127, 23);
        Usuario u2 = new Usuario("Tomas", "Abot", 40138127, 18);
        Usuario u3 = new Usuario("Lucas", "Abot", 40138127, 23);
        HashMap<Integer, Usuario> lista = new HashMap<Integer, Usuario>(2);
        lista.put(40138127, u1);
        lista.put(4013812, u2);
        System.out.println(lista.get(40138127).toString());
        Avion a = new Avion(123,456,789,123456,Propulsion.REACCION,true,true);
        System.out.println(a.toString());
        Date m = new Date(10, 10,10);

        AvionSilver as = new AvionSilver(123,456,789,123456,Propulsion.ELICE,true,true);
        AvionGold ag = new AvionGold(123,456,789,123456,Propulsion.PISTONES,true,true);
        Avion j = new Avion();
        Date d = new Date(12,12,12);
        Vuelo v = new Vuelo(d,u1,Ciudad.MONTEVIDEO,Ciudad.SANTIAGO,10,as);
        Vuelo v2 = new Vuelo(d,u2,Ciudad.MONTEVIDEO,Ciudad.SANTIAGO,10,ag);
        Vuelo v3 = new Vuelo(m,u1,Ciudad.MONTEVIDEO,Ciudad.SANTIAGO,10,a);
        Vuelo v4 = new Vuelo(d,u1,Ciudad.MONTEVIDEO,Ciudad.SANTIAGO,10,ag);
        Vuelo v5 = new Vuelo(d,u1,Ciudad.MONTEVIDEO,Ciudad.SANTIAGO,10,a);
        v.calcular_costo();

        Vuelo h = new Vuelo();

        List<Avion> aviones = new ArrayList<Avion>();
        List<Vuelo> vuelos = new ArrayList<Vuelo>();

        aviones.add(as);
        aviones.add(ag);
        aviones.add(a);

        vuelos.add(v);
        vuelos.add(v2);
        vuelos.add(v3);
        vuelos.add(v4);
        vuelos.add(v5);
       // vuelos.get(1).toString();
       // h.mis_vuelos(u1,vuelos);
        h.vuelos_por_fecha(u1, d,vuelos);


    }
}
