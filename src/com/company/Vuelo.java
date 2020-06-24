package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Vuelo implements Serializable {

    private Date fecha_de_vuelo; //FECHA EN LA QUE SE REALIZA EL VUELO
    private Usuario usuario; //USUARIO QUE CONTRATO EL VUELO
    private Ciudad origen;
    private Ciudad destino;
    private int cantidad_de_pasajeros; //CANTIDAD DE PASAJEROS DE ESTE VUELO
    private Avion avion_asignado; //AVION ASIGNADO A ESTE VUELO

    public Vuelo (){}

    public Vuelo (Date fecha_de_vuelo, Usuario usuario, Ciudad origen, Ciudad destino, int cantidad_de_pasajeros, Avion avion_asignado){
        this.fecha_de_vuelo=fecha_de_vuelo;
        this.usuario=usuario;
        this.origen=origen;
        this.destino=destino;
        this.cantidad_de_pasajeros=cantidad_de_pasajeros;
        this.avion_asignado=avion_asignado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return cantidad_de_pasajeros == vuelo.cantidad_de_pasajeros &&
                Objects.equals(fecha_de_vuelo, vuelo.fecha_de_vuelo) &&
                Objects.equals(usuario, vuelo.usuario) &&
                origen == vuelo.origen &&
                destino == vuelo.destino &&
                Objects.equals(avion_asignado, vuelo.avion_asignado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha_de_vuelo, usuario, origen, destino, cantidad_de_pasajeros, avion_asignado);
    }

    public static void contratarUnVuelo(Usuario usuario_logueado) {
        //Recibe al usuario logueado y levanta el json de vuelos y aviones
        ArrayList<Vuelo> listaDeVuelos = Archivos.levantarVuelos();
        ArrayList<Avion> listaDeAviones = Archivos.levantarAviones();

        Scanner miScanner = new Scanner(System.in);
        Date fecha_convertida;
        final Date fecha_actual = new Date();
        LimpiarConsola.limpiar_consola();
        Vuelo nuevoVuelo = new Vuelo();
        nuevoVuelo.setUsuario(usuario_logueado);

        System.out.println("<<Contratando un Vuelo>>");
        try {

            do {
                System.out.println(" Ingrese la fecha deseada (formato dd/mm/aaaa) -> ");
                String fecha = miScanner.nextLine();
                fecha_convertida = convertirADate(fecha);
                if(fecha_convertida==null) {
                    System.out.println("La fecha ingresada no es valida,(" + fecha_convertida + ") \nReintente o presione 0 para salir.");
                    String salir = miScanner.nextLine();
                    if(salir.equals("0")) {
                        return;
                    }
                    else {
                        fecha_convertida = convertirADate(salir);
                    }
                } else if (fecha_convertida.compareTo(fecha_actual) < 0) {
                    System.out.println("La fecha ingresada no es valida, (" + fecha_convertida + ") \n Reintente");
                    Thread.sleep(2000);
                    return;
                }
            } while ((fecha_convertida == null)||(fecha_convertida.compareTo(fecha_actual)) < 0);
            nuevoVuelo.setFecha_de_vuelo(fecha_convertida);
            System.out.println(" Elija la ciudad de origen: ");
            nuevoVuelo.elegirCiudadOrigen();
            System.out.println(" Elija la ciudad de destino: ");
            nuevoVuelo.elegirCiudadDestino(nuevoVuelo.getOrigen());
            System.out.println(" Ingrese la cantidad de pasajeros");
            int cantidadPasajeros = miScanner.nextInt();
            nuevoVuelo.setCantidad_de_pasajeros(cantidadPasajeros);
            ArrayList<Avion> avionesDisponiles = nuevoVuelo.buscarAvionesDisponibles(listaDeAviones, listaDeVuelos);

            if(!avionesDisponiles.isEmpty()) {
                System.out.println(" Elija el avion que desea contratar: ");
                for (int i = 0; i < avionesDisponiles.size(); i++) {
                    System.out.println(i + ". " + avionesDisponiles.get(i).toString() + "\n");
                }
                int avionElegido = miScanner.nextInt();
                if(avionElegido<avionesDisponiles.size()) {
                    nuevoVuelo.setAvion_asignado(avionesDisponiles.get(avionElegido));
                    int respuesta;
                    do {
                        LimpiarConsola.limpiar_consola();
                        System.out.println(nuevoVuelo.toString());
                        nuevoVuelo.mostrar_costo();
                        System.out.println(" 1.Aceptar \t2.Cancelar");
                        respuesta = miScanner.nextInt();
                        switch (respuesta) {
                            case 1:
                                listaDeVuelos.add(nuevoVuelo);
                                Archivos.guardarVuelos(listaDeVuelos);
                                break;
                            case 2:
                                return;
                            default:
                                System.out.println("Ingreso un numero invalido.");
                                Thread.sleep(2000);
                                break;
                        }
                    }while (!(respuesta==1||respuesta==2));


                } else {
                    System.out.println("Ingreso una opcion invalida, reintente por favor.");
                    Thread.sleep(2000);
                    return;
                }
            }
            else {
                System.out.println(" No tenemos aviones disponibles para esa fecha. Disculpe, sera dirigido al menu anterior.");
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            if(e instanceof InputMismatchException) {
                try {
                    System.out.println("\nERROR: Ingreso un campo  invalido, reintente por favor.\n");
                    Thread.sleep(2000);
                } catch (InterruptedException errorDePausa) {
                    System.out.println("Error de pausa");
                    errorDePausa.printStackTrace();
                }
            }

        }

    }


    @Override
    public String toString() {
        return usuario.toString() + "\n->Fecha de vuelo: " + fecha_de_vuelo + "\n°Origen del vuelo: " + origen +
                "\n°Destino del vuelo: " + destino + "\nCantidad de pasajeros: " + cantidad_de_pasajeros + "\nAvion asignado: " + avion_asignado.toString();
    }

    public static void listarVuelosPorFecha() {
        ArrayList<Vuelo> vuelos = Archivos.levantarVuelos();
        Scanner miScanner = new Scanner(System.in);
        Date fecha_convertida;
        final Date fecha_actual = new Date();
        try {

            do {
                LimpiarConsola.limpiar_consola();
                System.out.println(" Ingrese la fecha deseada (formato dd/mm/aaaa) -> ");
                String fecha = miScanner.nextLine();
                fecha_convertida = convertirADate(fecha);
                if (fecha_convertida == null) {
                    System.out.println("La fecha ingresada no es valida,(" + fecha_convertida + ") \nReintente o presione 0 para salir.");
                    String salir = miScanner.nextLine();
                    if (salir.equals("0")) {
                        return;
                    } else {
                        fecha_convertida = convertirADate(salir);
                    }
                } else if (fecha_convertida.compareTo(fecha_actual) < 0) {
                    System.out.println("La fecha ingresada no es valida, (" + fecha_convertida + ") \n Reintente");
                    Thread.sleep(2000);
                    return;
                }
            } while ((fecha_convertida == null) || (fecha_convertida.compareTo(fecha_actual)) < 0);

            Vuelo.vuelos_por_fecha(fecha_convertida, vuelos);

        } catch (InterruptedException e) {
            System.out.println("Error de pausa.");
        }


    }

    //recibe los vuelos del archivo y los muestra si son de la fecha recibida por parametro
    public static void vuelos_por_fecha(Date d, List<Vuelo> vuelos) {
        int i = 0;
        int x = 0;
            if (!vuelos.isEmpty()) {
                while (i < vuelos.size()) {
                    if (d.compareTo(vuelos.get(i).fecha_de_vuelo) == 0) {
                        System.out.println("\nEl vuelo marcado como numero : " + i + vuelos.get(i).toString());
                        x++;
                    }
                    i++;
                }
                if (x == 0) {
                    System.out.println("\nNo hay vuelos para esta fecha");
                }
            } else {
                System.out.println("\nNo hay vuelos cargados en el achivo");
            }
            System.out.println("\n Presione para continuar...");
            Scanner miScanner = new Scanner(System.in);
            String contrinuar =  miScanner.nextLine();
    }





    public ArrayList<Avion> buscarAvionesDisponibles(ArrayList<Avion> aviones, ArrayList<Vuelo> vuelos) {
        //Recibe array de aviones y de vuelos totales del sistema
        //Crea un nuevo array papra retornar
        ArrayList<Avion> avionesDisponibles;
        //Si hay vuelos cargados hace el filtrado con las validaciones correspondientes
        if(!vuelos.isEmpty()) {
            avionesDisponibles = avionesConCapacidadSuficiente(aviones);
            if (!avionesDisponibles.isEmpty()) {
                avionesDisponibles = avionesDisponiblesEnEsaFecha(avionesDisponibles, vuelos);
            }
        }
        else {  //si no hay vuelos cargados, todos los aviones estan disponibles
            avionesDisponibles = aviones;
        }
        return avionesDisponibles;
    }

    //retorna array con aviones o array vacio
    public ArrayList<Avion> avionesConCapacidadSuficiente(ArrayList<Avion> aviones) {
        Scanner miScanner = new Scanner(System.in);
        ArrayList<Avion> avionesDisponibles = new ArrayList<>();
        int capacidad_Maxima = 0;
        //Busca cual es la capacidad maxima de los aviones en sistema
        for (Avion aux : aviones) {
            if (aux.capacidad_de_pasajeros > capacidad_Maxima) {
                capacidad_Maxima = aux.capacidad_de_pasajeros;
            }
        }
        //Si la cantidad buscada es menor, busca los aviones que son candidatos
        if (this.cantidad_de_pasajeros < capacidad_Maxima) {
            for (Avion aux : aviones) {
                if (this.cantidad_de_pasajeros < aux.capacidad_de_pasajeros) {
                    avionesDisponibles.add(aux);
                }
            }
        } else { //Sino >>

            try { //try para la excepcion de un dato mal ingresado de Scanner
                do {
                    //Ofrece reingresar o salir, si ingresa no retorna un arreglo vacio
                    System.out.println("No tenemos aviones disponibles con esa capacidad de pasajeros.\n");
                    System.out.println("Desea ingresar una cantidad menor? (Capacidad Maxima: " + capacidad_Maxima + "\n 1.Si\n Other.No");
                    int respuesta = miScanner.nextInt();
                    if (respuesta == 1) { //Si elije si reconfigura la capacidad de pasajeros solicitada
                        System.out.println("Nueva capacidad a buscar: ");
                        this.cantidad_de_pasajeros = miScanner.nextInt();
                        if(this.cantidad_de_pasajeros < capacidad_Maxima) { //Si esta vez es una cantidad valida, vuelve a buscar los aviones
                            for (Avion aux : aviones) {
                                if (this.cantidad_de_pasajeros < aux.capacidad_de_pasajeros) {
                                    avionesDisponibles.add(aux);
                                }
                            }
                        }
                    } else { //Si elije NO retorna un arreglo vacio que cerrara la busqueda
                        return new ArrayList<>();
                    }
                } while (this.cantidad_de_pasajeros > capacidad_Maxima);
            } catch(InputMismatchException e){
                System.out.println("\nERROR: Ingreso un campo  invalido, reintente por favor.\n");
                return new ArrayList<>();
            }
        }
        return avionesDisponibles;
    }

    //Recibe el array de aviones disponibles con la capacidad solicitada
    public ArrayList<Avion> avionesDisponiblesEnEsaFecha(ArrayList<Avion> aviones, ArrayList<Vuelo> vuelos) {

        ArrayList<Avion> avionesDisponibles = new ArrayList<>();
        ArrayList<Vuelo> vuelosDeEsaFecha = new ArrayList<>();

        for(Vuelo aux : vuelos) { //busca los vuelos de esa fecha
            if(aux.getFecha_de_vuelo().compareTo(this.getFecha_de_vuelo()) == 0) {
                vuelosDeEsaFecha.add(aux);
            }
        }


        if(!vuelosDeEsaFecha.isEmpty()) { //Controla que haya vuelos esa fecha para evitar NullPointerEx
            boolean flag;
            int i;
            for (Avion aux : aviones) { //por cada avion
                //resetea las variables de control de flujo
                flag = true;
                i = 0;
                while (i < vuelosDeEsaFecha.size() && flag == true) { //mientras haya vuelos y no haya encontrado el avion en el vuelo anterior

                    Vuelo aux_vuelo = vuelosDeEsaFecha.get(i); //compara en el siguiente vuelo

                    if (aux.equals(aux_vuelo.getAvion_asignado())) { //Si ese avion es el asignado a ese vuelo
                        flag = false; //Hay coincidencia flag = false, pasa al otro avion
                    }
                    else {
                        i++; //si no esta asignado pasa al siguiente vuelo
                    }
                }
                if(flag == true) { //si al recorrer todos los vuelos aun sigue sin haber coincidencia, lo añade a aviones disponiles
                    avionesDisponibles.add(aux);
                }

            }
        }
        else { //Si no hay vuelos esa fecha son todos los aviones los que estan disponibles
            avionesDisponibles = aviones;
        }

        return avionesDisponibles;
    }

    public void elegirCiudadOrigen() {
        int respuesta;
        Ciudad ciudadElegida = null;
        System.out.println("Seleccione una ciudad:\n 1.Buenos Aires\n 2.Cordoba\n 3.Santiago\n 4.Montevideo\n RTA: ");
        Scanner miScanner = new Scanner(System.in);
        respuesta = miScanner.nextInt();
        do {
            switch (respuesta) {

                case 1:
                    ciudadElegida = Ciudad.BUENOS_AIRES;
                    break;
                case 2:
                    ciudadElegida = Ciudad.CORDOBA;
                    break;
                case 3:
                    ciudadElegida = Ciudad.SANTIAGO;
                    break;
                case 4:
                    ciudadElegida = Ciudad.MONTEVIDEO;
                    break;
                default:
                    System.out.println("Opcion invalida, por favor reintente.");
                    break;

            }
        }while(respuesta < 1 && respuesta > 4);

        this.origen = ciudadElegida;

    }

    public void elegirCiudadDestino(Ciudad origen) {
        int respuesta;
        Ciudad ciudadElegida = null;
        System.out.println("Seleccione una ciudad:\n 1.Buenos Aires\n 2.Cordoba\n 3.Santiago\n 4.Montevideo\n RTA: ");
        Scanner miScanner = new Scanner(System.in);
        respuesta = miScanner.nextInt();
        do {
            switch (respuesta) {

                case 1:
                    ciudadElegida = Ciudad.BUENOS_AIRES;
                    break;
                case 2:
                    ciudadElegida = Ciudad.CORDOBA;
                    break;
                case 3:
                    ciudadElegida = Ciudad.SANTIAGO;
                    break;
                case 4:
                    ciudadElegida = Ciudad.MONTEVIDEO;
                    break;
                default:
                    System.out.println("Opcion invalida, por favor reintente.");
                    break;

            }
            if(origen == ciudadElegida) {
                System.out.println("No puede seleccionar la misma ciudad de destino que de origen, reintente por favor.");
            }
        }while(respuesta < 1 && respuesta > 4 && ciudadElegida != origen);

        this.destino = ciudadElegida;

    }

    public static Date convertirADate(String fecha_en_string) {
        //Recibe un string y lo convierte a objeto Date si no, retorna null
        Date retorno = null;
        SimpleDateFormat fecha_en_date_format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            retorno = fecha_en_date_format.parse(fecha_en_string);
        } catch (Exception e){ System.out.println("No se pudo convertir, error de fotmato");}
        return retorno;
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

    public int calcular_tarifa_de_avion (){
        int tarifa_tipo_de_avion = 0;
        if (avion_asignado.tipoDeAvion.equals(TipoDeAvion.GOLD)){
            tarifa_tipo_de_avion = 6000;
        }
        else if (avion_asignado.tipoDeAvion.equals(TipoDeAvion.SILVER)){
            tarifa_tipo_de_avion=4000;
        }
        else if (avion_asignado.equals(TipoDeAvion.BRONZE)){
            tarifa_tipo_de_avion= 3000;
        }
        else{
            System.out.println("este no es un avion valido");
        }
        return tarifa_tipo_de_avion;
    }
    public int calcular_tarifa_por_persona(){
        int tarifa_pasajero = 3500 * cantidad_de_pasajeros;
        return tarifa_pasajero;
    }
    public int calcular_kilometro_de_viaje (){
        int km_del_viaje=0;

        if ((origen == Ciudad.BUENOS_AIRES && destino==Ciudad.CORDOBA)||(destino==Ciudad.BUENOS_AIRES && origen==Ciudad.CORDOBA)){
            km_del_viaje=695;
        }
        else if ((origen==Ciudad.BUENOS_AIRES && destino==Ciudad.SANTIAGO) || (origen==Ciudad.BUENOS_AIRES && destino==Ciudad.SANTIAGO)){
            km_del_viaje=1400;
        }
        else if ((origen==Ciudad.BUENOS_AIRES && destino==Ciudad.MONTEVIDEO) || (origen==Ciudad.BUENOS_AIRES && destino==Ciudad.MONTEVIDEO)){
            km_del_viaje=950;
        }
        else if ((origen==Ciudad.CORDOBA && destino==Ciudad.MONTEVIDEO) || (origen==Ciudad.CORDOBA && destino==Ciudad.MONTEVIDEO)){
            km_del_viaje=1190;
        }
        else if ((origen==Ciudad.CORDOBA && destino==Ciudad.SANTIAGO) || (origen==Ciudad.CORDOBA && destino==Ciudad.SANTIAGO)){
            km_del_viaje=1050;
        }
        else if ((origen==Ciudad.MONTEVIDEO && destino==Ciudad.SANTIAGO) || (origen==Ciudad.MONTEVIDEO && destino==Ciudad.SANTIAGO)){
            km_del_viaje=2100;
        }
        return km_del_viaje;
    }
    public int calcular_tarifa_por_km (){
        int tarifa_km_costo=0;
        int km = calcular_kilometro_de_viaje();
        tarifa_km_costo= avion_asignado.costo_km * km;

        return tarifa_km_costo;
    }

    public int calcular_costo() {
        return calcular_tarifa_por_km()+calcular_tarifa_de_avion()+calcular_tarifa_por_persona();
    }
    public void mostrar_costo (){
        int total = calcular_costo();
        System.out.println("\nKM * costo KM: "+calcular_tarifa_por_km());
        System.out.println("\navion: "+calcular_tarifa_de_avion());
        System.out.println("\npor persona: "+calcular_tarifa_por_persona());
        System.out.println("\nEl costo total del vuelo es de: " + total);
    }


  /*  public TipoDeAvion comparadorTiposDeAvion(TipoDeAvion mejorTipoDeAvion) {

        if(mejorTipoDeAvion!=null) {
            if (mejorTipoDeAvion == TipoDeAvion.BRONZE) {
                return this.avion_asignado.tipoDeAvion;
            } else if (mejorTipoDeAvion == TipoDeAvion.SILVER) {
                if (this.avion_asignado.tipoDeAvion == TipoDeAvion.BRONZE) {
                    return mejorTipoDeAvion;
                } else {
                    return this.avion_asignado.tipoDeAvion;
                }
            } else if (mejorTipoDeAvion == TipoDeAvion.GOLD) {
                return mejorTipoDeAvion;
            } else {
                System.out.println("ROMPIMO
            }
        }
    }*/
}

