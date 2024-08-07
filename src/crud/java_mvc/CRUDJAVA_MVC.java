/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.java_mvc;

import Config.ConexionLocal;
import Config.Miconexion;
import Models.Persona;
import Vistas.Personas;

/**
 *
 * @author Artur
 */
public class CRUDJAVA_MVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //"jdbc:mysql://localhost:3306/crud_java_mvc", "root", "root"
        /*ConexionLocal conexion = new ConexionLocal();
        conexion.conectar();
        conexion.testearConexion();
        //
         System.out.println("Crud java mvc");*/
        Personas formPersonas= new Personas();
        formPersonas.setLocationRelativeTo(null);
        formPersonas.setVisible(true);
    }
    
}
