
package main.java.sigep.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.sigep.model.Conexion;
import main.java.sigep.model.entities.ProductosPOS;

/**
 *
 * @author Marito
 */
public class ProductosPOSDAO {

    public static ProductosPOS getArticulo(String codigo) {
        Connection conexion = Conexion.conectar();
        String sql = null;
        if (codigo.length() <= 6) {          
            sql = "select    a.id_producto,     a.codigo_barras,        a.descripcion, "
                         + " a.precio_venta,    a.existencia"
                    + " from productospos a where"
                    + " a.id_producto = ? ";
        } else {
            
             sql = "select   a.id_producto,           a.descripcion,                       a.codigo_barras, "
                         + " a.precio_venta,           a.existencia"
                    + " from productospos a where"
                    + " a.codigo_barras = ? ";
        }
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            if (codigo.length() <= 6){
                 codigo = String.format("%06d", Integer.parseInt(codigo));
            }  
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ProductosPOS producto = new ProductosPOS();
                
                producto.setCodigoInterno(rs.getString(1));
                producto.setCodigoBarra(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setPrecioMinorista(rs.getDouble(4));
                producto.setExistencia(rs.getDouble(5));
             
                Conexion.desconectar();
                return producto;
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

    public static List<ProductosPOS> getArticulos(String descripcion, boolean avanzada) {
        Connection conexion = Conexion.conectar();
        String  sql = "select    a.id_producto,     a.codigo_barras,        a.descripcion, "
                         + " a.precio_venta,    a.existencia"
                    + " from productospos a where";
                    
        if (avanzada) {
            descripcion = "%"+descripcion +"%";
            sql += " a.descripcion like ? order by a.descripcion";
        } else {
            descripcion += "%";
            sql += " a.descripcion like ? order by a.descripcion";
        }
        
        try {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, descripcion);
            ResultSet rs = pst.executeQuery();
            List<ProductosPOS> articulos = new ArrayList<>();
            while (rs.next()) {
                ProductosPOS producto = new ProductosPOS();
                
                producto.setCodigoInterno(rs.getString(1));
                producto.setCodigoBarra(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setPrecioMinorista(rs.getDouble(4));
                producto.setExistencia(rs.getDouble(5));
                
                articulos.add(producto);
            }
            Conexion.desconectar();
            return articulos;
        } catch (SQLException ex) {
            System.out.println("Error! " + ex.getMessage());
            Conexion.desconectar();
            return null;
        }
          
    }
    
}
