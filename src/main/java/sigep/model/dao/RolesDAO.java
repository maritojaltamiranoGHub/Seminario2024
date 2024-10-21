
package main.java.sigep.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.sigep.model.Conexion;
import main.java.sigep.model.entities.Roles;


/**
 *
 * @author Marito
 */
public class RolesDAO {
    
    public Roles ObtenerRolUsuario(int rol){
        
        Connection conexion = Conexion.conectar();
        String sql = null;
  
        sql =  "select   r.descripcion"
               + " from roles r"
               + " where"
               + " r.id_rol = ?";
     
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, rol);
         
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Roles roles = new Roles();
                roles.setNombreRol(rs.getString(1));
                Conexion.desconectar();
                return roles;
            } else {
                Conexion.desconectar();
                return null;
            }
        } catch (SQLException | NumberFormatException ex) {
            System.out.println("Error! " + ex.getMessage());
            Conexion.desconectar();
            return null;
        }  
    };
    
}
