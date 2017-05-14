/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buenprovecho;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marlene Vásquez
 */
public class TestBuenProvecho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){ 
     
        // Obtener la única instancia de la clase BuenProvecho (patrón sigleton)
        BuenProvecho aComer = BuenProvecho.getInstance(); 
        // Definir la variable que nos permite leer String desde teclado
        final Scanner in = new Scanner(System.in);
        int opcion = 0; 
        ArrayList list;
        String nombre, telefono,descripcion,correo,clave,nombreUsuario;
        do{
            try{ // tratamiento de las excepciones. Bloque try en el que se puede producir una excepción y la capturamos
		 
                 //Terminar de diseñar el menú (usando System.out.println(...)) con las opciones que faltan
		 // Podéis hacer vuestros propios diseños de interfaz, esta es la interfaz mínima que tenéis que entregar
                System.out.println("\n\n*********************************** MENU ***********************************\n" +
                                       "GESTIÓN DE USUARIOS   \n" +
                                     "\t10. Nuevo Responsable Restaurante \n" +
                                     "\t11. Ver todos Responsables Restaurantes \n");	
                                 
                System.out.println("GESTIÓN DE RESTAURANTES   \n" +                             
                                    "\t20. Solicitar registro restaurante \n" +
                                    "\t21. Ver restaurantes pendientes de registro \n" +
                                    "\t22. Confirmar registros de restaurantes \n" + 
                                    "\t23. Consultar los restaurantes de un responsable \n" +
                                    "\t24. Actualizar datos de un restaurante \n");
                
                System.out.println("GESTIÓN DE RESERVAS   \n" +
                                    "\t30. Solicitar  reserva \n" +
                                    "\t31. Confirmar reserva \n" +
                                    "\t32. Ver las reservas de un usuario \n" +
                                    "\t33. Anular una reserva \n" +
                                    "\t34. Anular reservas pasadas \n");
                
		System.out.println("TERMINAR ");          
                                  System.out.println("\t0. Terminar");
		System.out.println("\n**********************************************************************");
                 
                // Lectura de un int, para darle valor a opcion.
                opcion =Integer.parseInt(in.nextLine()); 
                
                // Estructura switch con todas las opciones de menú. Algunos de ellos ya lo tenéis hecho
                // Tenéis que terminar las opciones que están incompletas y las que no están hechas
                switch(opcion){
                    case 10:// incluir un nuevo responsable de restaurante en el sistema 
                                            
                        System.out.print("Nombre de responsable: ");
                        nombreUsuario =in.nextLine();
                                       
                        System.out.print("Clave de responsable: ");
                        String claveUsuario= in.nextLine();
                        
                        System.out.print("Correo de responsable: ");
                        String correoUsuario= in.nextLine();
                        
                        aComer.altaRegistro(nombreUsuario, claveUsuario, correoUsuario,"Responsable");                                             
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                    break;         
                                     
                    case 11:/*Consultar todos los responsables de restaurantes que hay */
                        
                        List salida = aComer.verResponsables(); // la salida la tenéis que adaptar a la implementación que le deis a vuestras salidas
                        System.out.println("Responsables: ");
                        for ( Object s : salida) {
                            System.out.println("\t" + s);
                        }                                            
                    break;
                  
                  case 20: /*Soliciatar registro de restaurante */                         
                        System.out.print("nombre del responsable: ");                  
                        String nombre_Usuario =in.nextLine();
                        System.out.print("Nombre del Restaurante: ");
                        nombre =in.nextLine();
                        System.out.print("Provincia donde esta ubicado el Restaurante: ");
                        String provincia=in.nextLine();
                        System.out.print("localidad del Restaurante: ");
                        String localidad =in.nextLine();
                        System.out.print("Dirección del Restaurante: ");
                        String direccion =in.nextLine();
                        System.out.print("Telefono del Restaurante: ");
                        telefono =in.nextLine();
                        //  llamada a la funcion
                        aComer.solicitarRegistroRestaurante(nombre_Usuario, nombre, provincia, localidad, direccion, telefono);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                  break;
                  
                  case 21: /* Ver restaurantes pendientes de registro  */
                       List lista= aComer.obtenerRestaurantesPendientesDeAlta();
                       System.out.println("Restaurantes pendientes de alta: ");
                        for ( Object s:lista) {
                            System.out.println("\t" + s);
                        } 
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                  break;             
                  
                  case 22: /* Cofirmar registro de restaurante  */
                        System.out.print("Telefono del Restaurante: ");
                        telefono =in.nextLine();
                        list = new ArrayList();
                        list.add(telefono);
                        aComer.confirmarRegistroDeRestaurante(list);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                  break;
 
                  case 23: /* Consultar los restaurantes de un responsable  */
                        System.out.print("Nombre del responsable: ");
                        nombre =in.nextLine();
                        
                        lista = aComer.obtenerMisRestaurantes(nombre);
                     
                        for ( Object s:lista) {
                               System.out.println("\t" + s);
                           } 
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                                         
                  break;
                        
                  case 24: /*Actualizar datos de un restaurante */
                        System.out.print("Telefono del Restaurante: ");
                        telefono =in.nextLine();
                        
                        System.out.print("nombre del Restaurante: ");
                        nombre =in.nextLine();
                        
                        System.out.print("Nuevo télefono del Restaurante: ");
                        String telefNuevo =in.nextLine();
                        
                        System.out.print("Tipo cocina: ");
                        String tipo =in.nextLine();
                        
                        System.out.print("Precio medio: ");
                        float precio = Float.parseFloat(in.nextLine()); //---------------->
                        
                        System.out.print("Descripción del Restaurante: ");
                        descripcion =in.nextLine();
                        
                        System.out.print("Horario del Restaurante: ");
                        String horario =in.nextLine();
                        
                        aComer.actualizarDatosRestaurante(telefono, nombre, telefNuevo, tipo, precio,descripcion, horario);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                        
                  break;
                        
                  case 30: /* Solicitar Reserva */
                        System.out.print("Nombre de usuario: ");
                        nombreUsuario =in.nextLine();
                        
                        System.out.print("Correo de usuario: ");
                        correo = in.nextLine();
                        
                        System.out.print("telefono de Usuario: ");
                        telefono = in.nextLine();
                        
                        System.out.print("telefono del restaurante: ");
                        String telefonoR = in.nextLine();                        
                                                
                        System.out.print("dia : ");
                        int dia = Integer.parseInt(in.nextLine());
                        if (dia<1 ||dia >=31){
                            System.out.println(" El día que ha ingresado no esta dentro del rango correcto[1,31]");
                            System.out.print("día : ");
                            dia = Integer.parseInt(in.nextLine());
                        }
                        System.out.print("mes : ");
                        int mes = Integer.parseInt(in.nextLine());
                        if (mes<1 ||mes >12){
                            System.out.println(" El mes que ha ingresado no esta dentro del rango correcto[1,12]");
                            System.out.print("mes : ");
                            mes = Integer.parseInt(in.nextLine());
                        }
                        System.out.print("hora : ");
                        int hora = Integer.parseInt(in.nextLine());
                        System.out.print("minutos : ");
                        int minutos = Integer.parseInt(in.nextLine());
                       
                        System.out.print("Numero de comensales: ");
                        int num = Integer.parseInt(in.nextLine());
                        GregorianCalendar diaHora= new GregorianCalendar(2015,mes-1,dia,hora,minutos);
                        
                        aComer.solicitarReservaRestaurante(nombreUsuario, correo, telefono, telefonoR,diaHora, num);
                        System.out.print("++++++  Operación realizada con éxito ++++++");
                  break;
                        
                  case 31: /* Confirmar Reserva */
                      System.out.print("idReserva: ");
                      String id = in.nextLine();
                      
                      System.out.print("Teléfono restaurante: ");
                      telefono  = in.nextLine();
                      aComer.confirmarReserva(id, telefono);
                      
                      System.out.print("++++++  Operación realizada con éxito ++++++");                   
                  break;

                  case 32: /*  Ver las reservas de un usuario */
                        System.out.print("Teléfono del Usuario: ");
                        telefono = in.nextLine();  
                        ArrayList reservas;
                        reservas = aComer.consultarMisReservas(telefono);
                        if(reservas!=null)
                        for ( Object s: reservas) {
                               System.out.println("\t" + s);
                           } 
                       
                       System.out.print("++++++  Operación realizada con éxito ++++++");            
                  break;

                  case 33: /* Anular una reserva   */
                       System.out.print("idReserva: ");
                        String id_ = in.nextLine();
                      
                        System.out.print("Telefono del usuario :");
                        nombre = in.nextLine();
                        
                        aComer.anularReserva(id_, nombre);//----------->
                       System.out.print("++++++  Operación realizada con éxito ++++++");
                  break;

                  case 34: /* Anular reservas pasadas */ 
                      //TEMPORIZADOR
                      aComer.eliminarReservasPasadasClientesNoHabituales();
                      
                       System.out.print("++++++  Operación realizada con éxito ++++++");                  
                  break;

                    case 0: /* terminar */
                       System.exit(0);
                    break;                        
                                    
                    default:
                        System.out.println("opcion no válida");
                    break;
                }
//               
            }catch(Exception ex){ // captura de la excepción
                System.err.println("se ha producido la siguiente excepcion: "+ ex);
            } 
        }while(opcion !=0); 
        System.exit(0);
    }  

}
