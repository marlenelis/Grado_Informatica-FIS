package usuario;

import java.util.ArrayList;
import java.util.Iterator;
import reserva.Reserva;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: Usuario
 * date : 05-jun-2015
 * time : 22:43:43
 */
public abstract class  Usuario {
    //atributos -variables
    private final String nombre;
    private final String correo;
    private final String telefono;
    
    private final ArrayList<Reserva> listaReservas;    
    //===========================================================
    //                      METODOS
    //===========================================================
    //----------------------CONSTRUCTOR--------------------------
    /**
     * Constructor de la clase
     * @param nombre    String
     * @param correo    String
     * @param telefono  String
     */
    public Usuario(String nombre, String correo, String telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono; 
        
        this.listaReservas = new ArrayList<>();
    }
    /**
     * devuelve el valor del atributo nombre
     * @return String
     */   
    public String getNombre(){
        return this.nombre;
    }
    /**
     * devuelve el valor del atributo correo
     * @return String
     */   
    public String getCorreo(){
        return this.correo;
    }
    /**
     * devuelve el valor del atributo telefono
     * @return String
     */   
    public String getTelefono(){
        return this.telefono;
    }
    /**
     * Busca la reserva correspondiente con el codigo pasado como parametro
     * 
     * @param codReserva -String
     * @return  Reserva
     */
    private Reserva buscarReserva(String codReserva){
        Reserva aux = null;
        // Obtenemos un Iterador y recorremos la lista.
        Iterator <Reserva> iter = this.listaReservas.iterator();
        while (iter.hasNext()){
            aux = iter.next();
            if(aux.getCodigoReserva().equals(codReserva)){                
               return aux;
            }            
        }   
        return aux;//aux es null si no esta en la lista
    }
    /**
     * Comprueba si la lista no esta vacia.
     * @return boolean
     */
    public boolean tienesReserva(){
        return (!this.listaReservas.isEmpty());
    }
    /**
     * Elimina la reserva correspondiente con el codiigo reserva pasado como parametro.
     * @param codReserva - String
     */
    public void anularReserva(String codReserva){
        Reserva res = this.buscarReserva(codReserva);//usa el metodo privado
        res.eliminarDelRestaurante();
        
        this.listaReservas.remove(res);//2.3 eliminar(reserva)
    }
    /**
     * Agrega la reserva pasada como parametro a la lista de reservas
     * @param miReserva Reserva
     */
    public void incluirReserva(Reserva miReserva){
        this.listaReservas.add(miReserva);
    }
    /**
     * devuelve la lista con los datos de las Reservas
     * @return  ArrayList
     */
    public ArrayList consultarMisReservas(){
        
        ArrayList listaDatos = new ArrayList();
        
        for (Reserva listaReserva : this.listaReservas) {
            listaDatos.add(listaReserva.obtenerDatosReserva());            
        }
        return listaDatos;
    }
    /**
     * Elimina de la lista de reservas de la clase las reservas que sean
     * anteriores a la fecha actual
     */
    public void eliminarReservasPasadas(){
       
           for(int i= 0;i<this.listaReservas.size();i++){
                Reserva aux= this.listaReservas.get(i);
                if(aux.yaPasada()){
                    aux.eliminarDelRestaurante();
                    this.listaReservas.remove(aux);//elimina si yaPasada() = true
                }            
        }            
    }  
    /**
     * Devuelve los atributos de clase
     * @return String
     */
    @Override
    public String toString(){
        String lista = "";
        
        return  "\tNombre: "+this.nombre+"\n"
                +"\tCorreo:"+this.correo+"\n"
                +"\tTelefono: "+this.telefono+"\n";
    }
    
}