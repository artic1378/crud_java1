/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlles;
import Config.ConexionLocal;
import Models.Roles;
import Interface.IGestorDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur
 */
public class RolesController  implements IGestorDatos< Roles >{
    private Connection cnn;
    private final ConexionLocal connNewAdmin = new ConexionLocal();
    
    @Override
    public void creacion(Roles objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Roles lectura(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Roles objeto, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Roles> lista() {
         List<Roles> listaRoles = new ArrayList<>();
         try {
            connNewAdmin.conectar();

            // Consulta para obtener los roles
            String consulta = "SELECT id, nombre FROM roles";
           
            PreparedStatement st = connNewAdmin.getConexion().prepareStatement(consulta);
             ResultSet resultSet = st.executeQuery(consulta);
           
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                listaRoles.add(new Roles(id, nombre));
            }

            // Ahora tienes la lista de roles en 'listaRoles'
            // Puedes usarla según tus necesidades

            // Cierra los recursos
            resultSet.close();
            st.close();
            connNewAdmin.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return listaRoles;
    }
    
}
