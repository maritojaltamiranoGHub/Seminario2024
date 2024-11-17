package main.java.sigep.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import main.java.sigep.model.Conexion;
import main.java.sigep.model.dao.UsuariosDAO;
import main.java.sigep.model.entities.Usuarios;

public class UsuariosDAOImpl implements UsuariosDAO {
   
    
    @Override
    public Usuarios validarUsuario(Integer user, String clave) {
        Connection conexion = Conexion.conectar();
        String sql = "SELECT u.id_usuario, u.nombre,"
                + " u.id_rol FROM usuarios u WHERE u.id_usuario = ? AND clave = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, user);
            pst.setString(2, clave);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setIdRol(rs.getInt("id_rol"));
                
                return usuario;
            }
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
        } finally {
            Conexion.desconectar();
        }
        return null;
    }

    @Override
    public Usuarios obtenerPorId(int id) {
        Connection conexion = Conexion.conectar();
        String sql = "SELECT id_usuario, nombre, id_rol FROM usuarios WHERE id_usuario = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setIdRol(rs.getInt("id_rol"));
                
                return usuario;
            }
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
        } finally {
            Conexion.desconectar();
        }
        return null;
    }

    @Override
    public List<Usuarios> obtenerTodos() {
        List<Usuarios> listaUsuarios = new ArrayList<>();
        Connection conexion = Conexion.conectar();
        String sql = "SELECT id_usuario, nombre, u.id_rol, r.descripcion rol_descripcion,clave FROM usuarios u "
                +" Left Join roles r on r.id_rol = u.id_rol ";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setIdRol(rs.getInt("id_rol"));
                usuario.setRolDescripcion(rs.getString("rol_descripcion"));
                usuario.setClave(rs.getString("clave"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
            
        } finally {
            Conexion.desconectar();
           
        }
        return listaUsuarios;
    }

    @Override
    public void ingresar(Usuarios usuario) {
        
        Connection conexion = Conexion.conectar();
        String sql = "INSERT INTO usuarios (nombre, clave, id_rol) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getClave());
            pst.setInt(3, usuario.getIdRol());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario Ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error Ingresando el nuevo usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.desconectar();
            
        }
        
    }

    @Override
    public void actualizar(Usuarios usuario) {
        
        Connection conexion = Conexion.conectar();
        String sql = "UPDATE usuarios SET nombre = ?, clave = ?, id_rol = ? WHERE id_usuario = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getClave());
            pst.setInt(3, usuario.getIdRol());
            pst.setInt(4, usuario.getIdUsuario());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.", "Éxito 1", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error Actualizando los datos del usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.desconectar();
            
        }
            
    }

    @Override
    public void eliminar(int id) {
      
        Connection conexion = Conexion.conectar();
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
            
        } finally {
            Conexion.desconectar();
            
        }
        
    }
}
