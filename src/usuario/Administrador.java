package usuario;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: Administrador
 * date : 06-jun-2015
 * time : 11:17:21
 */
public class Administrador implements Usuario_Registrado {
    // atributos - variables
    private final String nombreUsuario;
    private final String nombre;
    private final String clave;
    private final String correo;
    //===========================================================
    //                      METODOS
    //===========================================================
    //----------------------CONSTRUCTOR--------------------------  
    /**
     * Constructor de la clase Administrador
     * @param nombreUsuario String
     * @param nombre    String
     * @param clave     String
     * @param correo    String
     */
    public Administrador(String nombreUsuario,String nombre, String clave, String correo){
        this.nombre = nombre;
        this.clave  = clave;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Devuelve el valor del atributo nombreUsuario de la clase
     * @return  - String
     */       
    @Override
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    /**
     * Devuelve el valor del atributo clave de la clase
     * @return  - String
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
        
        return  "\n\tNombre de Usuario: "+this.nombreUsuario+"\n"
                + "\tNombre: "+this.nombre+"\n"
                + "\tCorreo: "+this.correo+"\n"
                + "\tClave: "+this.clave+"\n"; 
    }

}
