
package main.java.sigep.model.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import main.java.sigep.model.Conexion;
import main.java.sigep.model.dao.UsuariosDAO;
import main.java.sigep.model.entities.Usuarios;
/**
 *
 * @author Marito
 */
public class UsuariosDAOImpl implements UsuariosDAO {
    
    @Override
    public Usuarios validarUsuario(Integer user, String clave) {
       
        Connection conexion = Conexion.conectar();
        String sql = null;
  
        sql =  "select   u.id_usuario,u.nombre,u.id_rol"
               + " from usuarios u"
               + " where"
               + " u.id_usuario = ?  and clave = ?";
     
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, user);
            pst.setString(2, clave);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuarios usuario = new Usuarios();
                
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setIdRol(rs.getInt(3));
              
                Conexion.desconectar();
                return usuario;
            } else {
                Conexion.desconectar();
                return null;
            }
        } catch (SQLException | NumberFormatException ex) {
            System.out.println("Error! " + ex.getMessage());
            Conexion.desconectar();
            return null;
        }

    }

    @Override
    public Usuarios obtenerPorId(int id) {
        Connection conexion = Conexion.conectar();
        String sql = null;
  
        sql =  "select   u.id_usuario,u.nombre,u.id_rol"
               + " from usuarios u"
               + " where"
               + " u.id_usuario = ?";
     
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
         
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt(1));
                Conexion.desconectar();
                return usuario;
            } else {
                Conexion.desconectar();
                return null;
            }
        } catch (SQLException | NumberFormatException ex) {
            System.out.println("Error! " + ex.getMessage());
            Conexion.desconectar();
            return null;
        }
    }

    @Override
    public List<Usuarios> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(Usuarios usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Usuarios usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
