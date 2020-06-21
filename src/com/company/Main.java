package com.company;

import javafx.scene.shape.Arc;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Usuario u1 = new Usuario("Lucas", "Abot", 40138127, 23);
        Usuario u2 = new Usuario("Tomas", "Abot", 41138127, 18);

        Scanner miScanner = new Scanner(System.in);


        int respuesta;

        do {
            LimpiarConsola.limpiar_consola();
            System.out.println("<<Menu Principal>>");
            System.out.println(" 1.Ingresar\n 2.Registrarse\n 0.Salir\n Respuesta: ");
            respuesta = miScanner.nextInt();

            switch (respuesta) {

                case 1:
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.ingresar();
                    break;
                case 2:
                    MenuPrincipal menu2 = new MenuPrincipal();
                    ArrayList<Usuario> usuarios = Archivos.levantarUsuarios();
                    menu2.registrarse(usuarios);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida, reintente.");
                }
        }while (respuesta != 0);

    }


}
