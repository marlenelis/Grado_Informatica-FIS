package reserva;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import restaurante.Restaurante;
import usuario.Usuario;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: Reserva
 * date : 05-jun-2015
 * time : 22:43:15
 */
public class Reserva {
    //atributos - variables
    private final GregorianCalendar fecha;
    private final int numeroDeComensales;
    private boolean confirmada;
    
    private final String codReserva ;    //<-----ALEATORIO---------->    
    Usuario usuario;
    Restaurante restaurante;
    //===========================================================
    //                      METODOS
    //===========================================================
    //----------------------CONSTRUCTOR--------------------------
    public Reserva(Usuario unUsuario, Restaurante unRestaurante, GregorianCalendar diaHora, int numComensales){
        this.usuario = unUsuario;
        this.restaurante = unRestaurante;
        this.fecha = diaHora;
        this.numeroDeComensales = numComensales;
        this.confirmada = false;
        this.codReserva = this.asignarCodigo();
    }
    /**
     * Asigna un codigo de reserva aleatorio
     * @return - String
     */
    private String asignarCodigo(){
        String cadena ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        Random rnd = new Random(); 
        StringBuilder sb = new StringBuilder( 7 ); for( int i = 0; i < 7; i++ ) 
        sb.append( cadena.charAt( rnd.nextInt(cadena.length()) ) ); 
                
        return sb.toString(); 

    }
    /**
     * Devuelve el valor del atributo codReserva
     * @return String
     */
    public String getCodigoReserva(){
        return this.codReserva;
    }
    /**
     * quita el enlace que hay entre restaurante y reserva
     */
    public void eliminarDelRestaurante(){
        this.restaurante.eliminarReserva(this);
    }
    /**
     * Devuelve los datos de la reserva 
     * @return ArrayList
     */
    public ArrayList obtenerDatosReserva(){
        ArrayList datosReserva =  new ArrayList();
        
        /*
        datosReserva.add(this.fecha);
        datosReserva.add(this.hora);
        datosReserva.add(this.numeroDeComensales);
        datosReserva.add(this.confirmada);
        
        datosReserva.add(this.restaurante.obtenerDatosRestaurante());
        /**/
        
        datosReserva.add(this.toString());
        return datosReserva;
    }
    /**
     * Devuelve si la fecha de la reserva es menor a la actual
     * @return boolean
     */
    public boolean yaPasada(){
        GregorianCalendar fechaActual = new GregorianCalendar();
        System.out.print(this.fecha.before(fechaActual));
        return this.fecha.before(fechaActual);//compara si es menor
    }
    /**
     * Cambia el valor del atributo de false a true
     */
    public void confirmar(){
        this.confirmada = true;
    }
    /**
     * devuelve la informacion de los atributos de la clase 
     * @return String
     */
    @Override
    public String toString(){
        
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
        
        String conf = (this.confirmada)?"SI":"NO";
        return    "\n\tCodReserva: "+this.codReserva+"\n"
                + "\tUsuario: "+this.usuario.getNombre()+"\n"
                + "\tRestaurante: "+this.restaurante.getName()+"\n"
                + "\tFecha: "+formatoFecha.format(fecha.getTime())+"\n"
                + "\tHora: "+formatoHora.format(fecha.getTime())+"\n"
                + "\tNº comensales: "+ this.numeroDeComensales+"\n"
                + "\tConfirmada: "+conf+"\n"  ;
    }    
}
