package com.company;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jdk.nashorn.internal.runtime.ECMAException;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Archivos<T>{

    //EN PRINCIPIO HABIA CREADO UNA SOLA FUCNION DE LECTURA Y UNA SOLA DE ESCRITURA,...
    //... PERO AL PARECER JACKSON TIENE PROBLEMAS AL CASTEAR OBJETOS GENERICOS...
    //...ASI QUE CREE UNA PARA CADA TIPO DE DATO.

    public static  ArrayList<Usuario> levantarUsuarios() {
        String path = "usuarios.json";
        ArrayList<Usuario> lista = new ArrayList<>();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe, por lo tanto no hay datos, procedo a crearlo.");
                Thread.sleep(1000);
                archivo.createNewFile();
                //NO SOLO BASTA CON CREARLO, DEBO DECIRLE QUE CONTENDRA UN ARRAYLIST PARA EVITAR EXCEPCIONES EN LA PROXIMA LECTURA
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(archivo, lista);
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
                //NO SOLO BASTA CON CREARLO, DEBO DECIRLE QUE CONTENDRA UN ARRAYLIST PARA EVITAR EXCEPCIONES EN LA PROXIMA LECTURA
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(archivo, lista);
                System.out.println("Archivo creado correctamente!");
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();
                lista = mapper.readValue(archivo, new TypeReference<ArrayList<Vuelo>>(){});
                System.out.println("JACKSON: Archivo leido correctamente");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("No se pudo leer el archivo, error de Lectura");
            e.printStackTrace();
        }

        return lista;
    }

    public static  ArrayList<Avion> levantarAviones() {
        String path = "aviones.json";
        ArrayList<Avion> lista = new ArrayList<>();
        File archivo = new File(path);
        try{
            if(!archivo.exists()) {
                System.out.println("El archivo no existe, por lo tanto no hay datos, procedo a crearlo.");
                Thread.sleep(1000);
                archivo.createNewFile();
                //NO SOLO BASTA CON CREARLO, DEBO DECIRLE QUE CONTENDRA UN ARRAYLIST PARA EVITAR EXCEPCIONES EN LA PROXIMA LECTURA
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(archivo, lista);
                System.out.println("Archivo creado correctamente!");
            }
            else
            {
                ObjectMapper mapper = new ObjectMapper();
                lista = mapper.readValue(archivo, new TypeReference<ArrayList<Avion>>(){});
                System.out.println("JACKSON: Archivo leido correctamente!");

            }

        } catch (IOException |  InterruptedException e) {
            System.out.println("No se pudo leer el archivo, error de lectura.");
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
                mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                //COMO NO TENGO GETTERS Y SETTERS EN SUBLCASES AVION, CON ESTA SENTENCIA LE DIJO A JACKSON QUE IGUAL ACCEDA A SERIALIZARLO
                mapper.writeValue(archivo, lista);
                System.out.println("Se guardo el avion con éxito!");
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo para guardar este usuario.");
            e.printStackTrace();
        }
    }

    public static boolean existeDNI(int dni, ArrayList<Usuario> listaUsuarios) {
        //SI ENCUENTRA EL DNI EN EL ARRAYLIST DEVUELVE TRUE
        for(Usuario user : listaUsuarios) {
            System.out.println(user.toString());
            if(user.getDni() == dni) {
                return true;
            }
        }
        return false;
    }

    public static Usuario encuentraUsuario(ArrayList<Usuario> usuarios, int dniABuscar) {
        //SI ENCUENTRA AL USUARIO EN EL ARRAYLIST LO DEVUELVE, SI NO DEVUELVE NULL
        Usuario encontrado = new Usuario();
        for(Usuario aux : usuarios) {
            if(aux.getDni() == dniABuscar) {
                encontrado = aux;
            }
            else {
                encontrado = null;
            }
        }
        return encontrado;
    }


}

