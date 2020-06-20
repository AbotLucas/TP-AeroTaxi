package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Usuario u1 = new Usuario("Lucas", "Abot", 40138127, 23);
        Usuario u2 = new Usuario("Tomas", "Abot", 40138127, 18);
        HashMap<Integer, Usuario> lista = new HashMap<Integer, Usuario>(2);
        lista.put(40138127, u1);
        lista.put(4013812, u2);
        System.out.println(lista.get(40138127).toString());
        Avion a = new Avion(123,456,789,123456,Propulsion.REACCION,true,true);
        System.out.println(a.toString());


    }
}
