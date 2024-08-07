/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artur
 */
public class Miconexion {

    private Connection cnn;
    private String cadenaConexion, usuarioDb, claveDb;
    

    public Miconexion(String cadenaConexion, String usuarioDb, String claveDb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.cadenaConexion = cadenaConexion;
            this.usuarioDb = usuarioDb;
            this.claveDb = claveDb;
            cnn = DriverManager.getConnection(this.cadenaConexion, this.usuarioDb, this.claveDb);
            System.out.println("estas conectado");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Miconexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public boolean testearConxion(){
        try {
            return !cnn.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(Miconexion.class.getName ()).log(Level.SEVERE,null,ex);
            return false;
        }
    }
    
    public void abrirConexion(){
        try {
           cnn = DriverManager.getConnection(cadenaConexion,"root","root");
        } catch (SQLException ex) {
            Logger.getLogger(Miconexion.class.getName ()).log(Level.SEVERE,null,ex);
        }
    }
    public void cerrarConexion(){
        try {
             cnn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Miconexion.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
}
