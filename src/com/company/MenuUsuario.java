package com.company;

import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuUsuario {


    public static void ejecutarMenuUsuario(Usuario usuario_logueado, ArrayList<Usuario> usuarios){

        Scanner miScanner = new Scanner(System.in);
        int respuesta;


        do {
            LimpiarConsola.limpiar_consola();
            System.out.println("<<Menu Principal>>");
            System.out.println(" 1.Contratar un Vuelo\n 2.Mis Vuelos\n 3.Ver vuelos en una fecha\n 4.Ver todos los usuarios registrados\n 5.Cargar aviones al sistema\n 0.Salir\n Respuesta: ");
            respuesta = miScanner.nextInt();

            switch (respuesta) {

                case 1:
                    Vuelo.contratarUnVuelo(usuario_logueado);
                    break;
                case 2:
                    usuario_logueado.misVuelos();
                    break;
                case 3:
                    Vuelo.listarVuelosPorFecha();
                    break;
                case 4:
                    ArrayList<Usuario> usuarios_lista = Archivos.levantarUsuarios();
                    usuario_logueado.usuarios_registrados(usuarios_lista);
                    break;
                case 5:
                    Avion.cargarAvionNuevo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida, reintente.");
            }
        }while (respuesta != 0);



    }
}
