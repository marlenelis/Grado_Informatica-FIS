package usuario;

import java.util.ArrayList;
import java.util.Iterator;
import restaurante.Restaurante;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: ResponsableRestaurante
 * date : 06-jun-2015
 * time : 6:58:25
 */
public class ResponsableRestaurante implements Usuario_Registrado{
    //atributos - variables
    private final String nombreUsuario;
    private final String clave;
    private final String correo;
    
    private final ArrayList<Restaurante> listaRestaurantes;
    //===========================================================
    //                      METODOS
    //===========================================================
    //----------------------Constuctor---------------------------
    /**
     * Constructor de la clase
     * @param nombre    String
     * @param clave     String
     * @param correo    String
     */
    public ResponsableRestaurante(String nombre, String clave, String correo) {
        this.nombreUsuario = nombre;
        this.clave = clave;
        this.correo= correo;
        this.listaRestaurantes = new ArrayList<>();
    }
    
    /**
     * Agrega un restaurante a la lista de restaurante
     * @param miRestaurante  Restaurante
     */
    public void incluirRestaurante(Restaurante miRestaurante){
        boolean siguiente =true;
        //no esta en el diagrama pero es necesario para no añadir
        //el mismo restaurante varias veces.
        
        // Obtenemos un Iterador y recorremos la lista.
        Iterator <Restaurante> iter = this.listaRestaurantes.iterator();
        while (iter.hasNext() && siguiente){
            Restaurante aux = iter.next();
             if(aux.equals(miRestaurante)){
                siguiente = false;//si es igual termina 
             }            
        }   
        if(siguiente) //una vez salido del bucle  siguiente = true no ha encontrado el restaurante
            this.listaRestaurantes.add(miRestaurante);// agrega el restaurante
    }
    
    /**
     * devuelve la lista de datos del los restaurantes
     * @return ArrayList
     */
    public ArrayList obtenerMisRestaurantes(){
        
        ArrayList  listaDatos = new ArrayList();
        for (Restaurante listaRestaurante : this.listaRestaurantes) {
            listaDatos.add(listaRestaurante.obtenerDatosRestaurante());
        }        
        return listaDatos;
    }
    /**
     * Devuelve el valor del atributo correo 
     * @return -String
     */
    public String getCorreo(){
        return this.correo;
    }
    /**
     * Implementa el metodo heredado de la interfase Usuario_Registrado
     * devuelve el valor del atributo de clase nombreUsuario.
     * @return String
     */
    @Override
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    /**
     * Implementa el metodo heredado de la interfase Usuario_Registrado
     * devuelve el valor del atributo de clase clave.
     * @return String
     */
    @Override
    public String getClave() {
        return this.clave;
    } 
    /**
     * devuelve los valores de los atributos de clase.
     * @return String
     */
    @Override
    public String toString(){
        
        String lista = "";
        for (Restaurante listaRestaurante : listaRestaurantes) {
            lista = lista +"\t\t"+ listaRestaurante.getName() + "\n";
        }
        return  "\n\tNombre: "+this.nombreUsuario+"\n"
                + "\tCorreo: "+this.correo+"\n"
                + "\tClave: "+this.clave+"\n"
                + "\tMis restaurantes a cargo: \n"+lista; 
    }
}
