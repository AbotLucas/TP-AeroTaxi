package com.company;


import javafx.scene.shape.Arc;

import java.awt.image.TileObserver;
import java.io.Serializable;
import java.lang.management.ThreadInfo;
import java.lang.reflect.Array;
import java.util.*;

public class Usuario implements Serializable {
    protected String nombre;
    protected String apellido;
    protected int dni;
    protected int edad;

    public Usuario() {}

    public Usuario(String nombre, String apellido, int dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;

    }


    public void misVuelos() {
        int respuestaInt;
        Scanner miScanner = new Scanner(System.in);
        try {
            do {
                LimpiarConsola.limpiar_consola();
                System.out.println(" Que lo trae hasta aqui joven Padawan?\n 1.Ver mis vuelos\n 2.Cancelar un vuelo :c\n 0.Salir\n Respuesta-> ");
                respuestaInt = miScanner.nextInt();
                switch (respuestaInt) {
                    case 1:
                        mostrar_mis_vuelos();
                        break;
                    case 2:
                        cancelar_un_vuelo();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Si continuar quiere, ingresar algo valido debe. ");
                        break;
                }
            }while (respuestaInt<0 || respuestaInt>2);
        } catch (InputMismatchException e) {

            System.out.println(" Pidiendo un int estaba te...");

        }

    }

    public void cancelar_un_vuelo() {

        ArrayList<Vuelo> vuelos = Archivos.levantarVuelos();
        List<Vuelo> misVuelos = vuelos_de_usuario(vuelos);
        List<Vuelo> vuelos_futuros = new ArrayList<>();
        Date fecha_acutal = new Date();
        Scanner miScanner = new Scanner(System.in);
        int respuesta;

        for(Vuelo aux : misVuelos) {
            if(aux.getFecha_de_vuelo().compareTo(fecha_acutal) > 0) {
                vuelos_futuros.add(aux);
            }
        }

        try {

            do {
                LimpiarConsola.limpiar_consola();
                System.out.println(" Que vuelo desea cancelar? ");
                for (int i = 0; i < vuelos_futuros.size(); i++) {
                    System.out.println(" " + (i+1) + ".>> EL VUELO DEL DIA: " + vuelos.get(i).getFecha_de_vuelo().toString());
                }
                respuesta = miScanner.nextInt();
                if(respuesta == 0) {
                    return;
                }
                else if(respuesta <= vuelos_futuros.size() && respuesta > 0) {
                    vuelos.remove(vuelos_futuros.get(respuesta-1));
                    Archivos.guardarVuelos(vuelos);
                    System.out.println("Vuelo Cancelado con exito! ");
                    Thread.sleep(2000);
                }
                else {
                    System.out.println("Ingreso un valor inexsitente. sera redirigido al menu anterior");
                    return;
                }
            } while (respuesta > vuelos_futuros.size() || respuesta < 0);
        } catch (Exception e) {
            if(e instanceof InputMismatchException) {
                System.out.println("Ingreso un valor erroneo.");
            }
            else {
                System.out.println("error de pausa!");
            }
        }

    }

    //MUESTRA POR PANTALLA LA LISTA DE VUELOS DEL USUARIO.
    public void mostrar_mis_vuelos() {
        //levanta el archivo de vuelos
        ArrayList<Vuelo> vuelos = Archivos.levantarVuelos();
        int i = 0;
        int x = 1;
        //los filtra para ver cuales son los del usuario
        List<Vuelo> v = vuelos_de_usuario(vuelos);
        LimpiarConsola.limpiar_consola();
        while (i < v.size()) {
            System.out.println("|>>Vuelo numero " + x + v.get(i).toString() + "\n");
            x++;
            i++;
        }
        System.out.println(" Presione para continuar");
        Scanner miScanenr = new Scanner(System.in);
        String continuar = miScanenr.nextLine();
    }
    //DEVUELVE UN ARRAYLIST DE VUELOS, SOLO CON LOS VUELOS DEL USUARIO LOGUEADO
    public List<Vuelo> vuelos_de_usuario (List<Vuelo> vuelos) {
        int i = 0;
        List<Vuelo> v = new ArrayList<>();
        while (i < vuelos.size()) {
            if (this.dni == (vuelos.get(i).getUsuario().getDni())) {
                v.add(vuelos.get(i));
            }
            i++;
        }
        return v;
    }

    //RECORRE EL ARREGLO DE USUARIOS Y LOS MUESTRA
    public void usuarios_registrados (List<Usuario> u){
        LimpiarConsola.limpiar_consola();

        try {

            int i = 0;
            while (i < u.size()) {
                System.out.println(u.get(i).toString());
                i++;
            }
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*
    public void verUsuariosRegistrados() {
        int totalGastadoPorUsuario = 0;
        TipoDeAvion mejorAvionContratado = null;

        ArrayList<Usuario> usuarios = Archivos.levantarUsuarios();
        ArrayList<Vuelo> vuelos = Archivos.levantarVuelos();


        //por cada usuario del archivo:
        for(Usuario aux : usuarios) {
            //Busca los vuelos de ese usuario
            List<Vuelo> vuelosDelUsuario = aux.vuelos_de_usuario(vuelos);
            if(!vuelosDelUsuario.isEmpty()) {
                for(Vuelo aux_vuelo : vuelosDelUsuario) {
                    totalGastadoPorUsuario += aux_vuelo.calcular_costo();
                    mejorAvionContratado = aux_vuelo.comparadorTiposDeAvion(mejorAvionContratado);
                }
            }


        }



    }*/



    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", edad=" + edad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dni == usuario.dni &&
                edad == usuario.edad &&
                Objects.equals(nombre, usuario.nombre) &&
                Objects.equals(apellido, usuario.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, dni, edad);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
