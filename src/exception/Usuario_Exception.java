package exception;

/**
 * Project:BuenProvecho
 * @author Marlene Vásquez
 * class: Usuario_Exception
 * date : 07-jun-2015
 * time : 7:51:43
 */
public class Usuario_Exception extends Exception{
    //atributo
    private final String mensaje;
    //-------------------CONSTRUCTOR--------------------------
    public Usuario_Exception(){
        this.mensaje = "Ya existe un usuario con ese nombre.";//mensaje
    }
    /**
     * Devuelve el valor del atributo mensaje
     * @return -String
     */
    @Override
    public String toString(){
        return this.mensaje+"\n";
    }

}
