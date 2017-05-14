package usuario;

import java.util.Date;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: ClienteHabitual
 * date : 06-jun-2015
 * time : 6:56:56
 */
public class ClienteHabitual  extends Usuario implements Usuario_Registrado{
    private final String clave;
    private final String nombreUsuario;
    
    private final Date fechaNacimiento;
    private final String sexo;
    private final int puntosAcumulados;
    private final String foto;
    
    
    
    public ClienteHabitual(String nombreUsuario,String nombre, String correo, String clave,String telefono, Date fechNacimiento,String sexo) {
        super(nombre, correo, telefono);
        this.nombreUsuario= nombreUsuario;
        this.clave= clave;
        this.fechaNacimiento = fechNacimiento;
        this.sexo = sexo;
        this.puntosAcumulados = 0;
        this.foto = "deafult";
    }
  
    @Override
    public String getNombreUsuario() {
       return this.nombreUsuario;
    }

    @Override
    public String getClave() {
        return this.clave;
    }
    /**
     * devuelve los valores de los atributos de clase y superclase
     * @return String
     */
    @Override
    public String toString(){
        
        return  "\n\tNombre: "+this.getNombreUsuario()+"\n"
                +super.toString() +"\n"; 
    }

}
