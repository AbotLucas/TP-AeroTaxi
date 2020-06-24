package com.company;

import java.util.*;

public class MenuPrincipal {

    Scanner miScanner;


    public MenuPrincipal() {
        this.miScanner = new Scanner(System.in);
    }


    public void registrarse(ArrayList<Usuario> usuarios) {
        LimpiarConsola.limpiar_consola();
        Usuario nuevoUsuario = new Usuario();
        boolean verificacion_dni;
        int dni;
        try {
            try {
                System.out.println("<<BIENVENIDO USUARIO>>\n<<REGISTRANDOSE>>");
                System.out.println("  Ingrese su Nombre-> ");
                String nombre = miScanner.nextLine();
                System.out.println("  Ingrese su Apellido-> ");
                String apellido = miScanner.nextLine();
                do {

                    System.out.println("  Ingrese su DNI-> ");
                    dni = miScanner.nextInt();
                    verificacion_dni = Archivos.existeDNI(dni, usuarios);
                    if(verificacion_dni) {
                        System.out.println("El DNI ingresado ya fue registrado, reintente o presione 0 (cero) para SALIR.");
                        int salir = miScanner.nextInt();
                        if(salir==0) {
                            return;
                        }
                    }
                }while (verificacion_dni);
                System.out.println("  Ingrese su Edad-> ");
                int edad = miScanner.nextInt();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setDni(dni);
                nuevoUsuario.setEdad(edad);
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Ingreso un campo  invalido, reintente por favor.\n");
                Thread.sleep(2000);
            }
            usuarios.add(nuevoUsuario);
            Archivos.guardarUsuario(usuarios);
        } catch (ClassCastException | InterruptedException e) {
            System.out.println("Algo anda mal: ClassCastException");
            e.printStackTrace();
        }
        }

    public void ingresar(ArrayList<Usuario> usuarios) {

        int dni_ingresado;
        boolean verificacion_dni;
        try {
            do {
                LimpiarConsola.limpiar_consola();
                System.out.println("<<BIENVENIDO USUARIO>>\n<<INGRESAR>>");
                System.out.println("   Ingrese su DNI-> ");
                dni_ingresado = miScanner.nextInt();
                verificacion_dni = Archivos.existeDNI(dni_ingresado, usuarios);
                if(!verificacion_dni) {
                    System.out.println("No existe un usuario registrado con ese DNI, reintente o vuelva al menu principal con 0");
                    int salir = miScanner.nextInt();
                    if(salir == 0) {
                        return;
                    }
                }
            } while (!verificacion_dni);
            Usuario usuario_logueado = Archivos.encuentraUsuario(usuarios, dni_ingresado);
            MenuUsuario.ejecutarMenuUsuario(usuario_logueado, usuarios);

        } catch (InputMismatchException e) {
            System.out.println("\nERROR: Ingreso un campo  invalido, reintente por favor.\n");

        }
    }


}
