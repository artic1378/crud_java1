/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;
import Interface.IGestorConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artur
 */
public class ConexionLocal implements  IGestorConexion{
     private String url = "jdbc:mysql://localhost:3306/crud_java_mvc";
     private String usuario = "root";
     private String claves = "root"; 
     //Instancia de la clave Connection del sistema
     private Connection conexion;

    public ConexionLocal() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClaves() {
        return claves;
    }

    public void setClaves(String claves) {
        this.claves = claves;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
     
     
    @Override
    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            conexion = DriverManager.getConnection(this.url, this.usuario, this.claves);
            System.out.println("estas conectado a la base de datos local");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionLocal.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }

    @Override
    public void desconectar() {
          try {
             conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionLocal.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    @Override
    public boolean testearConexion() {
        try {
           if (conexion != null && !conexion.isClosed()){
               return true;
           }else{
               return false;
           }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionLocal.class.getName ()).log(Level.SEVERE,null,ex);
            return false;
        }
    }
    
}
