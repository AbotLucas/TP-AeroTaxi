package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Archivos<T>{




    public static  ArrayList<Usuario> levantarUsuarios() {
        String path = "usuarios.json";
        ArrayList<Usuario> lista = new ArrayList<>();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe, por lo tanto no hay datos, procedo a crearlo.");
                Thread.sleep(1000);
                archivo.createNewFile();
                System.out.println("Archivo creado correctamente!");
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();
                lista = mapper.readValue(archivo, new TypeReference<ArrayList<Usuario>>(){});
                System.out.println("JACKSON: Archivo leido correctamente.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("No se pudo leer el archivo.");
            e.printStackTrace();

        }
        return lista;
    }

    public static  ArrayList<Vuelo> levantarVuelos() {
        String path = "vuelos.json";
        ArrayList<Vuelo> lista = new ArrayList<>();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe, por lo tanto no hay datos, procedo a crearlo.");
                Thread.sleep(1000);
                archivo.createNewFile();
                System.out.println("Archivo creado correctamente!");
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();
                lista = mapper.readValue(archivo, new TypeReference<ArrayList<Vuelo>>(){});
                System.out.println("JACKSON: Archivo leido correctamente");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("No se pudo leer el archivo.");
            e.printStackTrace();

        }
        return lista;
    }

    public static  ArrayList<Avion> levantarAviones() {
        String path = "vuelos.json";
        ArrayList<Avion> lista = new ArrayList<>();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe, por lo tanto no hay datos, procedo a crearlo.");
                Thread.sleep(1000);
                archivo.createNewFile();
                System.out.println("Archivo creado correctamente!");
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();
                lista = mapper.readValue(archivo, new TypeReference<ArrayList<Avion>>(){});
                System.out.println("JACKSON: Archivo leido correctamente!");

            }

        } catch (IOException |  InterruptedException e) {
            System.out.println("No se pudo leer el archivo.");
            e.printStackTrace();

        }
        return lista;
    }

    public static void guardarUsuario(ArrayList<Usuario> lista) {
        String path = "usuarios.json";
        ObjectMapper mapper = new ObjectMapper();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe. Creando uno...");
                Thread.sleep(1000);
                archivo.createNewFile();
                System.out.println("Archivo " + archivo.getName() + " creado con exito!\n");
                mapper.writeValue(archivo, lista);
            }
            else {
                mapper.writeValue(archivo, lista);
                System.out.println("Se guardo el Usuario con éxito!");
                Thread.sleep(1500);

            }
        } catch (IOException | InterruptedException e) {
            System.out.println("No se pudo leer el archivo para guardar este usuario.");
            e.printStackTrace();
        }
    }
    public static void guardarVuelos(ArrayList<Vuelo> lista) {
        String path = "vuelos.json";
        ObjectMapper mapper = new ObjectMapper();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe. Creando uno...");
                Thread.sleep(1000);
                archivo.createNewFile();
                System.out.println("Archivo " + archivo.getName() + " creado con exito!\n");
                mapper.writeValue(archivo, lista);
            }
            else {
                mapper.writeValue(archivo, lista);
                System.out.println("Se guardo el Vuelo con éxito!");
                Thread.sleep(1500);

            }
        } catch (IOException | InterruptedException e) {
            System.out.println("No se pudo leer el archivo para guardar este usuario.");
            e.printStackTrace();
        }
    }
    public static void guardarAviones(ArrayList<Avion> lista) {
        String path = "aviones.json";
        ObjectMapper mapper = new ObjectMapper();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe. Creando uno...");
                archivo.createNewFile();
                System.out.println("Archivo " + archivo.getName() + " creado con exito!\n");
                mapper.writeValue(archivo, lista);
            }
            else {
                mapper.writeValue(archivo, lista);
                System.out.println("Se guardo el avion con éxito!");
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo para guardar este usuario.");
            e.printStackTrace();
        }
    }

    public static boolean existeDNI(int dni, ArrayList<Usuario> listaUsuarios) {
        //SI ENCUENTRA EL DNI EN EL ARCHIVO DEVUELVE TRUE
        for(Usuario user : listaUsuarios) {
            System.out.println(user.toString());
            if(user.getDni() == dni) {
                return true;
            }
        }
        return false;
    }


}

