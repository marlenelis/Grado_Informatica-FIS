package restaurante;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import reserva.Reserva;
import usuario.Usuario;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: Restaurante
 * date : 05-jun-2015
 * time : 22:43:26
 */
public class Restaurante {
    
    private final String nombre;
    private final String provincia;
    private final String localidad;
    private final String direccion;
    private final String telefono;
    private String tipoCocina;
    private float precioMedio;
    private String descripcion;
    private String horario;
    
    enum Estado{INACTIVO,PENDIENTE_ALTA,ACTIVO, PENDIENTE_BAJA}    
    private Estado estado ;
    
    //----------------------------------------------------------
    private final ArrayList <Reserva> listaReservas = new ArrayList<>();
    
    //===========================================================
    //                      METODOS
    //===========================================================
    //----------------------Constructor--------------------------s
    public Restaurante( String nombre, String provincia, String localidad, String direccion, String telefono){
        this.nombre = nombre;
        this.provincia = provincia;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
        this.estado = Estado.PENDIENTE_ALTA;
    }
    /**
     * devuelve el estado 
     * @return boolean
     */
    public boolean pendienteAlta(){
        return (estado == Estado.PENDIENTE_ALTA);
    }
    /**
     * Devuelve todos los valores de los atributos de la clase
     * @return ArrayList
     */
    public ArrayList obtenerDatosRestaurante(){
        
        
        ArrayList datosRestaurante = new ArrayList();        
       /*
        datosRestaurante.add(this.nombre);        
        datosRestaurante.add(this.localidad);
        datosRestaurante.add(this.provincia);
        datosRestaurante.add(this.telefono);        
       /**/ 
        
        // con toString devuelvo lo siguiente y comento lo anterior
        datosRestaurante.add(this);
        
        return datosRestaurante;
    }
    /**
     * cambia el valor del atributo estado a activo
     */
    public void activarRestaurante(){        
        estado = Estado.ACTIVO;
    }
    /**
     * elimina la reserva pasada como argumento de la lista de reservas
     * de la clase 
     * @param reserva - Reserva: esta reserva se elimina de la lista 
     */
    public void eliminarReserva(Reserva reserva){
        listaReservas.remove(reserva);        
    }
    /**
     * Crea una reserva con los parametros pasados al metodo 
     * y agrega a la lista de reservas
     * 
     * @param unUsuario - Usuario
     * @param diaHora  GregorianCalendar
     * @param numComensales - int 
     */                                                            
    public void registrarReserva(Usuario unUsuario, GregorianCalendar diaHora, int numComensales){  
        Reserva nueva = new Reserva(unUsuario, this, diaHora, numComensales);
        unUsuario.incluirReserva(nueva);
        listaReservas.add(nueva);       
    }
    /**
     * Actualiza los valores que faltan de rellenar de los atributos de clase
     * @param tipoCocina - String
     * @param precioMedio - float
     * @param descripcion - String
     * @param horario - String
     */
    public void actualizarRestaurante(String tipoCocina, float precioMedio,String descripcion, String horario){
        this.tipoCocina = tipoCocina;
        this.precioMedio = precioMedio;
        this.descripcion = descripcion;
        this.horario = horario;
    }
    /**
     * Busca la reserva correspondiente con el 
     * codigo de reserva pasada como parametro al metodo de la lista de reservas
     * y cambia el valor del atributo estado de la reserva.
     * @param codReserva - String
     */
    public void confirmarReserva(String codReserva){
        // Obtenemos un Iterador y recorremos la lista.
        Reserva reserva = this.buscarReserva(codReserva);
        reserva.confirmar();
    }
    /**
     * Busca la reserva correspondiente con el codigo de reserva en la lista de reservas 
     * @param codReserva - String
     * @return Reserva
     */
    public Reserva buscarReserva(String codReserva){
        Reserva aux = null;
        // Obtenemos un Iterador y recorremos la lista.
        Iterator <Reserva> iter = this.listaReservas.iterator();
        while (iter.hasNext()){
            aux = iter.next();
            if(aux.getCodigoReserva().equals(codReserva)){                
               return aux;
             }            
        }    
        return aux;
    } 
    /**
     * Devuelve el valor del atributo nombre de la clase
     * @return  - String
     */       
    public String getName(){
        return this.nombre;
    }
    /**
     * Devuelve el valor del atributo telefono de la clase
     * @return  - String
     */       
    public String getTelefono(){
        return this.telefono;
    }
    /**
     * devuelve la informacion de los atributos de la clase 
     * @return String
     */
    @Override 
    public String toString(){
        
        return    "\tNombre: "+this.nombre+"\n"
                + "\tTelefono: "+this.telefono+"\n"
                + "\tEstado: "+this.estado+"\n"
                + "\tProvincia: "+this.provincia+"\n"
                + "\tLocalidad: "+this.provincia+"\n"
                + "\tDireccion: "+this.direccion+"\n"
                + "\tDescripcion: "+this.descripcion+"\n"
                + "\tTipo cocina: "+this.tipoCocina+"\n"
                + "\tPrecio medio: "+this.precioMedio+"\n";
                
    }
}

