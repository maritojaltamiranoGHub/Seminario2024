
package main.java.sigep.model.entities;
import java.text.NumberFormat;
import java.util.Date;

/**
 *
 * @author Marito
 */
public class Novedades {
    
    
    private String codigoInterno;
    private String codigoBarra;
    private String descripcion;
    private double precioNovedad;
    private String motivoNovedad;
    private Date   fechaNovedad;
    private String operadorNovedad;
    
    
    
    //constructor Generico
    public Novedades(){
    };
    
    //Constructor especifico
    public Novedades(String codigoInterno, String codigoBarra,String descripcion,
           double precioNovedad,String motivoNovedad, Date fechaNovedad,String operadorNovedad){
        
        
        this.codigoInterno      = codigoInterno;
        this.codigoBarra        = codigoBarra;
        this.descripcion        = descripcion;
        this.precioNovedad      = precioNovedad;
        this.fechaNovedad       = fechaNovedad;
        this.motivoNovedad      = motivoNovedad;
        this.operadorNovedad    = operadorNovedad;
        
    }
    
     public String getCodigoInterno() {
            return codigoInterno;
    } 
    public String getCodigoBarra() {
        return codigoBarra; 
    } 

     public String getDescripcion() {
        return descripcion;  
    }
    public Double getPrecioMinorista() {
        return precioNovedad;
    }

    public double getPrecioNovedad() {
        return precioNovedad;
    }

    public String getMotivoNovedad() {
        return motivoNovedad;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public String getOperadorNovedad() {
        return operadorNovedad;
    }
   
    

    //Setters
    public void setOperadorNovedad(String operadorNovedad) {
        this.operadorNovedad = operadorNovedad;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioNovedad(double precioNovedad) {
        this.precioNovedad = precioNovedad;
        
    }
    

    public void setMotivoNovedad(String motivoNovedad) {
        this.motivoNovedad = motivoNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

     public Object[] toObject() {
        return new Object[] { codigoInterno, codigoBarra, descripcion, precioNovedad,motivoNovedad, fechaNovedad, operadorNovedad };
    }
     
    public static String format(Double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }
    
}
