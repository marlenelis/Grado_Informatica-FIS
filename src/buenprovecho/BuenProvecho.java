package buenprovecho;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import restaurante.Restaurante;
import usuario.ResponsableRestaurante;
import usuario.Usuario;
import exception.Usuario_Exception;
import usuario.Usuario_Registrado;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: BuenProvecho
 * date : 05-jun-2015
 * time : 22:35:55
 */
public class BuenProvecho {
    //==========================================================
    //                      INSTANCE 
    //==========================================================
    private static final BuenProvecho instance=new BuenProvecho();    
    public static BuenProvecho getInstance(){
        return instance; 
    }    
    //===========================================================
    //                      VARIABLES
    //===========================================================
        ArrayList <Usuario> listaUsuarios = new ArrayList<>();
       // ArrayList <ResponsableRestaurante> listaResponsablesRestaurantes;
        ArrayList <Restaurante> listaRestaurantes = new ArrayList<>();;
        ArrayList <Usuario_Registrado> listaUsuariosRegistrados = new ArrayList<>();;    
    //===========================================================
    //                      METODOS
    //===========================================================
    /**
     * agrega un nuevo restaurante que previamente se crea con los parametros dados 
     * a la lista de restaurantes de la clase
     * @param nombreUsuario - String
     * @param nombre - String
     * @param provincia - String
     * @param localidad - String
     * @param direccion - String
     * @param telefono  - String
     * @throws java.lang.Exception
     */
    public void solicitarRegistroRestaurante(String nombreUsuario, String nombre,String provincia,
                                  String localidad , String direccion, String telefono ) throws Exception 
    {        
        //=======================No esta en el diagrama de comunicación=============
        Restaurante nuevo = this.buscarRestaurante(telefono);//para no registrar el mismo restaurante
        if(nuevo == null){
       //===========================================================================        
                nuevo = new Restaurante(nombre, provincia, localidad, direccion, telefono);               
                
                if(this.existeResponsableRestaurante(nombreUsuario)){
                    ResponsableRestaurante aux = this.buscarResponsable(nombreUsuario);
                    aux.incluirRestaurante(nuevo);       
                    this.listaRestaurantes.add(nuevo);  
                }else{
                    throw(new Exception("No hay coincidencia en nuestra lista de responsables"));
                }
                    
        }else  
            throw(new Exception("El restaurante  con ese número de teléfono ya se encuentra registrado"));       
    }
    /**
     * Devuelve una lista con los restaurantes pendientes de alta
     * @return ArrayLis
     */
    public List obtenerRestaurantesPendientesDeAlta(){
        
        List lista = new ArrayList();
        
        for (Restaurante listaRestaurante : this.listaRestaurantes) {
            if (listaRestaurante.pendienteAlta()) {
                lista.add(listaRestaurante.obtenerDatosRestaurante());
            }
        }
               return lista;
    }
    /**
     * Confirma el registro de los restaurantes
     * @param listaTelefonosRestaurantes  String
     */
    public void confirmarRegistroDeRestaurante(ArrayList<String> listaTelefonosRestaurantes){
        
        ArrayList<Restaurante> pendientes = this.seleccionarRestaurantes(listaTelefonosRestaurantes);
        
        if(pendientes!=null){            
            for (Restaurante pendiente : pendientes) {
                pendiente.activarRestaurante();
            }           
        }
    }
    /**
     * Devuelve los datos de los restaurantes relacionados con el responsable
     * @param nombreUsuario String
     * @return ArrayLis
     */
    public ArrayList obtenerMisRestaurantes(String nombreUsuario){
        ArrayList lista = new ArrayList();
        if(this.existeResponsableRestaurante(nombreUsuario)){
            ResponsableRestaurante responsable = this.buscarResponsable(nombreUsuario);            
            lista = responsable.obtenerMisRestaurantes();
        }
        return lista;
    }
    /**
     * Anula la reserva del usuario corespondiente con el telefono
     * si no tiene reservas elimina a este usuario de la lista de usuarios
     * @param codReserva - String
     * @param telefonoUsuario - String
     */
    public void anularReserva(String codReserva, String telefonoUsuario){
        
        Usuario unUsuario = this.buscarUsuario(telefonoUsuario);
        
        if(unUsuario != null){//
            unUsuario.anularReserva(codReserva);

            if(!unUsuario.tienesReserva())//no tiene reservas
                this.listaUsuarios.remove(unUsuario); //elimina usuario                
        }
    }
    /**
     * solicita la reserva con los datos pasados como parametros,
     * si el usuario existe se añade a su lista la nueva reserva creada por el restaurante.
     * caso contrario se crea el nuevo usuario se realiza lo anterio y se añade 
     * a la lista de usuarios del sistema.
     * @param nombreUsuario - String
     * @param correo - String
     * @param telefono - String
     * @param telefonoRestaurante - String
     * @param diaHora GregorianCalendar
     * @param numeroDeComensales - String   
     */
    //====================================================================================>>>
    public void solicitarReservaRestaurante(String nombreUsuario, String correo, 
                                            String telefono, String telefonoRestaurante,
                                            GregorianCalendar diaHora, int numeroDeComensales )
    {        
        boolean existe = this.existeUsuario(telefono);        
        Usuario nuevo;
        Restaurante restaurante;
        
        if(!existe){//comprueba si no existe
            nuevo = new Usuario(nombreUsuario, correo, telefono) {};//crea el nuevos usuario
            this.listaUsuarios.add(nuevo );//agrega el usuario creado a la lista de usuarios           
        }
        else{
            nuevo = this.buscarUsuario(telefono);   //4        
        }                     
        
        restaurante = this.buscarRestaurante(telefonoRestaurante);// unRestaurante = buscarRestaurante();
        
        if(restaurante!= null){
           restaurante.registrarReserva(nuevo, diaHora, numeroDeComensales);             
        }
    }
    /**
     * Confirma la reserva correspondiente con el codReserva pasada como parametro en el restaurante correspondiente
     * con el telefonoRestaurante pasado como parametro
     * @param codReserva String
     * @param telefonoRestaurante  - String
     */
    public void confirmarReserva(String codReserva, String telefonoRestaurante){
        Restaurante restaurante = this.buscarRestaurante(telefonoRestaurante);
        restaurante.confirmarReserva(codReserva);
    }
    /**
     * Devuelve la lista de reservas relacionadas con el identificador que se pasa como parametro
     * @param telefonoUsuario String
     * @return ArrayList
     * @throws java.lang.Exception
     */
    public ArrayList consultarMisReservas(String telefonoUsuario) throws Exception{
        ArrayList misReservas = new ArrayList();
        
        Usuario usuario = this.buscarUsuario(telefonoUsuario);        
         if(usuario!=null){
            misReservas = usuario.consultarMisReservas();
         }else
            throw(new Exception("No hay coincidencia en nuestra lista de usuarios"));
         
        return misReservas;
    }
    /**
     *                  TEMPORIZADOR
     * Elimina las reservas anteriores a la fecha actual de la lista de usuario
     */
    public void eliminarReservasPasadasClientesNoHabituales(){
        for(int i=0; i<this.listaUsuarios.size();i++){
            Usuario aux= this.listaUsuarios.get(i);
            aux.eliminarReservasPasadas();
            if(!aux.tienesReserva())
                this.listaUsuarios.remove(aux);
        }
    }
    /**
     * Crea un usuario_registrado dependiendo del tipo (Administrador, ClienteHabitual,ResponsableRestaurante)
     * @param nombreUsuario -String
     * @param clave -String
     * @param correo -String
     * @param tipoUsuario -String
     */
    public void altaRegistro(String nombreUsuario, String clave, String correo, String tipoUsuario){
        
        boolean existe = this.existeResponsableRestaurante(nombreUsuario);
        try{
                if(!existe){
                        switch(tipoUsuario){

                            case "Responsable"://implementada
                                    ResponsableRestaurante nuevo;
                                    nuevo = new ResponsableRestaurante(nombreUsuario,clave, correo);
                                    this.listaUsuariosRegistrados.add(nuevo);
                                break;
                            case "Administrador"://falta implementar
                                break;

                            case "Habitual"://falta implementar
                                break;
                        }
                }
                else 
                    throw( new Usuario_Exception()) ;//genera la Excepcion
        }catch(Usuario_Exception e){
            if(existe)
            System.err.print(e);//------->
           
        }
        
    }
    /**
     * Agrega los valores de los atributos de la clase Restaurante que faltan , tras su creacion. 
     * @param telefono -String
     * @param nombre -String
     * @param nuevoTelefono -String (este no se puede cambiar ya que es el identificador)
     * @param tipoCocina -String
     * @param precioMedio precioMedio - float
     * @param descripcion -String
     * @param horario  -String
     * @throws java.lang.Exception
     */
    public void actualizarDatosRestaurante(String telefono, String nombre,String nuevoTelefono, 
            String tipoCocina, float precioMedio,String descripcion,String horario ) throws Exception
    {        
        Restaurante restaurante = this.buscarRestaurante(telefono);
        if(restaurante != null)
            restaurante.actualizarRestaurante(tipoCocina, precioMedio, descripcion, horario);
        else{
            throw(new Exception("No hay coincidencia en nuestra lista de restaurantes."));
        }
    }
    /**
     * Realiza una busqueda en la lista de responsables.
     * Devuelve un ResponsableRestaurante correspondiente con el nombreUsuario pasado como parametro.
     * @param nombreUsuario - String
     * @return ResponsableRestaurante
     */
    private ResponsableRestaurante buscarResponsable(String nombreUsuario){
       ResponsableRestaurante responsable= null;
        for (Usuario_Registrado listaUsuariosRegistrado : this.listaUsuariosRegistrados) {            
            responsable = ((ResponsableRestaurante) listaUsuariosRegistrado);
            if(responsable.getNombreUsuario().equals(nombreUsuario))
                return responsable;
        }
        return responsable;
    }
    /**
     * Devuelve el usuario con telefono igual al pasado como parametro
     * @param telefono -String
     * @return String
     */
    private  Usuario buscarUsuario(String telefono){
        Usuario usuario= null;
        
        if(this.existeUsuario(telefono)){
            for (Usuario lista_Usuarios: this.listaUsuarios) {
                usuario =  lista_Usuarios;
                if(usuario.getTelefono().equals(telefono))
                    return usuario;
            }
        }
        return usuario;        
    }
    /**
     * Selecciona los restauarantes correspondientes con la lista de telefonos pasada como parametro
     * @param listaTelefonosRestaurantes
     * @return 
     */
    private ArrayList seleccionarRestaurantes(ArrayList<String> listaTelefonosRestaurantes){
        
        ArrayList lista = new ArrayList();
        for (Restaurante aux : this.listaRestaurantes) {
            for (String listaTelefonosRestaurante : listaTelefonosRestaurantes) {
                if (aux.getTelefono().equals(listaTelefonosRestaurante)) {
                    lista.add(aux);
                }
            }
        }     
        return lista;
    }    
    /**
     * Devuelve si el usuario con telefono pasado como parametros existe
     * @param telefonoUsuario String
     * @return boolean
     */
    private boolean existeUsuario(String telefonoUsuario){
        
        for (Usuario lista_Usuarios: this.listaUsuarios) {
            Usuario usuario;
            usuario =  lista_Usuarios;
            if(usuario.getTelefono().equals(telefonoUsuario))
                return true;
        }        
        return false;
    }
    /**
     * Devuelve si el responsable  con nombreUsuario pasado como parametros existe
     * @param nombreUsuario String
     * @return boolean
     */
    private boolean existeResponsableRestaurante(String nombreUsuario){
        
        for (Usuario_Registrado listaUsuariosRegistrado : this.listaUsuariosRegistrados) {            
            ResponsableRestaurante responsable = (ResponsableRestaurante) listaUsuariosRegistrado;
            if(responsable.getNombreUsuario().equals(nombreUsuario))
                return true;
        }
        return false;
    }
   /**
    * Devuelve el Restaurante buscado en la lista de resturantes que corresponde con el idRestaurante(telefono)
    * @param idRestaurante String
    * @return Restaurante
    */ 
    private Restaurante buscarRestaurante(String idRestaurante){
        
        Restaurante restaurante = null;
        
        for (Restaurante listaRestaurante : this.listaRestaurantes) {
            if (listaRestaurante.getTelefono().equals(idRestaurante)) {
                restaurante = listaRestaurante;
                return restaurante;
            }            
        }
        return restaurante;
    }
    
    /**
     * Devuelve una lista con los nombres de los responsables. 
     * @return ArrayList
     */
    public ArrayList verResponsables() {
        
//        ArrayList lista = new ArrayList();
//        for (Usuario_Registrado listaUsuariosRegistrado : this.listaUsuariosRegistrados) {
//            lista.add(listaUsuariosRegistrado.toString()); //todos los datos relacionados con el responsable
//                                                          // si solo es necesario el nombre se reemplaza por getNombre
//        }
       // return lista;
        return this.listaUsuariosRegistrados;//por ahora solo registra responsables 
    }   
}



