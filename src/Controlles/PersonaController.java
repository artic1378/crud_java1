/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlles;

import Config.ConexionLocal;
import Interface.IGestorDatos;
import Models.Persona;

    import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Artur
 */
public class PersonaController implements IGestorDatos<Persona> {
    private Connection cnn;
    private final ConexionLocal connNewAdmin = new ConexionLocal();
    
    @Override
    public void creacion(Persona objeto) {
        try {
             connNewAdmin.conectar();
             String sql = "INSERT INTO Persona (nombre,apellido,correo,fecha_nacimiento,pais,profesion,roles_id )"
                     + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = connNewAdmin.getConexion().prepareStatement(sql);
            st.setString(1,objeto.getNombre() );
            st.setString(2,objeto.getApellido() );
            st.setString(3,objeto.getCorreo() );
            //formatert fecha 
            java.sql.Date fechaNacimiento = new java.sql.Date(objeto.getFecha_nacimiento().getTime());
            //pasar fecha
            st.setDate(4, fechaNacimiento);
            st.setString(5,objeto.getPais());
            st.setString(6,objeto.getProfesion());
            st.setInt(7, objeto.getRoles_id());
            
        st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se ha realizado un nuevo registro");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Porfavor comprueva los datos "+e);
        }
    }

    @Override
    public Persona lectura(int id) {
        String sql = "SELECT nombre,apellido,correo,fecha_nacimiento,pais,profesion,roles_id FROM persona WHERE id = '" + id +"' "; 
    
        Persona personaTraida = new Persona();
        
        try {
            connNewAdmin.conectar();
            PreparedStatement realizaConsula = connNewAdmin.getConexion().prepareStatement(sql);
            ResultSet resultado = realizaConsula.executeQuery();
            
           if(resultado.next()){
               personaTraida.setNombre(resultado.getString("nombre"));
               personaTraida.setApellido( resultado.getString("apellido"));
               personaTraida.setCorreo(resultado.getString("correo"));
               
               personaTraida.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
               
               personaTraida.setPais(resultado.getString("pais"));
               personaTraida.setProfesion(resultado.getString("profesion"));
               personaTraida.setRoles_id(resultado.getInt("roles_id"));
               
           }else{
               personaTraida = new Persona();
               JOptionPane.showMessageDialog(null,"No se encontraron datos.");
           
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se encontraron registros", "Error al Recuperar", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error  de tipo"+e);
            System.out.println("Error en la clase " + this.getClass().getName());
        }
        return personaTraida;
    }

    @Override
    public void actualizar(Persona objetoActualizar, int id) {
       
        String sqlActualizar = "Update persona set nombre=?,apellido=?,correo=?,fecha_nacimiento=?,pais=?,profesion=?,roles_id=? WHERE id = '" + id +"'";
        try {
             connNewAdmin.conectar();
             PreparedStatement preparaConsultaEditar = connNewAdmin.getConexion().prepareStatement(sqlActualizar);
             preparaConsultaEditar.setString(1,objetoActualizar.getNombre());
             preparaConsultaEditar.setString(2,objetoActualizar.getApellido());
             preparaConsultaEditar.setString(3,objetoActualizar.getCorreo());
             
             java.sql.Date fecha_nacimiento = new java.sql.Date(objetoActualizar.getFecha_nacimiento().getTime());
             preparaConsultaEditar.setDate(4,fecha_nacimiento);
             
             preparaConsultaEditar.setString(5,objetoActualizar.getPais());
             preparaConsultaEditar.setString(6,objetoActualizar.getProfesion());
             preparaConsultaEditar.setInt(7,objetoActualizar.getRoles_id());
             
             preparaConsultaEditar.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se pudo actualizar el registro de la persona"+e,"ERROR ACTUALIZAR",JOptionPane.ERROR_MESSAGE);
        }finally{
            connNewAdmin.desconectar();
        }
    }

    @Override
    public void eliminar(int id) {
        String eliminar = "DELETE from persona where id ='"+id+"'";
        try {
            connNewAdmin.conectar();
            PreparedStatement eliminacion =connNewAdmin.getConexion().prepareStatement(eliminar);
            int filasAfectadas = eliminacion.executeUpdate();
                 
            
            if (filasAfectadas > 0){
                JOptionPane.showMessageDialog(null,"Datos Eliminados ");
            }else{
                JOptionPane.showMessageDialog(null,"No se encontraron datos a eliminar");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar"+e);
        }finally{
            connNewAdmin.desconectar();
        }
    
    }

    @Override
    public List<Persona> lista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
