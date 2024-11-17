
package main.java.sigep.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.sigep.model.Conexion;
import main.java.sigep.model.entities.Novedades;



/**
 *
 * @author Marito
 */
public class NovedadesDAO {

    public static Novedades getArticuloNovedad(String codigo,String fechaNovedad) {
        Connection conexion = Conexion.conectar();
        String sql = null;
        if (codigo.length() <= 6) {          
            sql = "select    n.id_producto,         n.codigo_barras,        a.descripcion, "
                         + " n.precio_novedad,      n.fecha_novedad"
                         + "n.descripcion_novedad,  n.operador_novedad" 
                         + " from novedades n where"
                         + " n.id_producto = ?  and n.fecha_novedad = ?";
        } else {
            
             sql = "select   n.id_producto,         n.codigo_barras,        a.descripcion, "
                         + "n.precio_novedad,      n.fecha_novedad, "
                         + "n.descripcion_novedad,  n.operador_novedad  " 
                         + "from novedades n where"
                         + "n.codigo_barras = ?  and n.fecha_novedad = ?    ";
        }
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            if (codigo.length() <= 6){
                 codigo = String.format("%06d", Integer.parseInt(codigo));
            }  
            pst.setString(1, codigo);
            pst.setString(2,fechaNovedad);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Novedades novedad = new Novedades();
                
                novedad.setCodigoInterno(rs.getString(1));
                novedad.setCodigoBarra(rs.getString(2));
                novedad.setDescripcion(rs.getString(3));
                novedad.setPrecioNovedad(rs.getDouble(4));
                novedad.setFechaNovedad(rs.getDate(5));
                novedad.setMotivoNovedad(rs.getString(6));
                novedad.setOperadorNovedad(rs.getString(7));
             
                Conexion.desconectar();
                return novedad;
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

    public static List<Novedades> getNovedades(String fecha) {
        Connection conexion = Conexion.conectar();
        String  sql = "select  n.id_producto,         n.codigo_barras,"
        +"a.descripcion, n.precio_novedad,"
        +"n.descripcion_novedad,n.fecha_novedad,"
        +"n.operador_novedad "
        +"from novedadessgp n  left join productospos a "
        +"on n.id_producto = a.id_producto ";
       

        // Validar la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = sdf.parse(fecha);
        } catch (ParseException e) {
            // Si ocurre una excepción, la fecha es inválida
            System.err.println("Fecha inválida: " + fecha);
        }

        // Agregar la cláusula WHERE si la fecha es válida
        if (fechaDate != null || !fecha.isEmpty()) {
            sql += " WHERE date(n.fecha_novedad) = ?";
        }

        sql += " ORDER BY n.fecha_novedad DESC";
                   
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            // Agregar la cláusula WHERE si la fecha es válida
            if (fechaDate != null || !fecha.isEmpty()) {
                pst.setString(1,fecha);
            }
 
            ResultSet rs = pst.executeQuery();
            
            List<Novedades> lnovedades = new ArrayList<>();
            while (rs.next()) {
                Novedades novedad = new Novedades();
                
                novedad.setCodigoInterno(rs.getString(1));
                novedad.setCodigoBarra(rs.getString(2));
                novedad.setDescripcion(rs.getString(3));
                novedad.setPrecioNovedad(rs.getDouble(4));
                novedad.setMotivoNovedad(rs.getString(5));
                novedad.setFechaNovedad(rs.getDate(6));
                novedad.setOperadorNovedad(rs.getString(7));
     
                lnovedades.add(novedad);
            }
            Conexion.desconectar();
            return lnovedades;
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
            Conexion.desconectar();
            return null;
        }
          
    }
    
}
