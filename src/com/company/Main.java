package com.company;


import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.shape.Arc;

import java.io.File;
import java.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        // write your code here

        Scanner miScanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = Archivos.levantarUsuarios();
        int respuesta;

        do {
            LimpiarConsola.limpiar_consola();
            System.out.println("<<Menu Principal>>");
            System.out.println(" 1.Ingresar\n 2.Registrarse\n 0.Salir\n Respuesta: ");
            respuesta = miScanner.nextInt();

            switch (respuesta) {

                case 1:
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.ingresar(usuarios);
                    break;
                case 2:
                    MenuPrincipal menu2 = new MenuPrincipal();
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
