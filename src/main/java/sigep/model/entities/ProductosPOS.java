
package main.java.sigep.model.entities;

/**
 *
 * @author Marito
 */
public class ProductosPOS {
    
    
    private String codigoInterno;
    private String codigoBarra;
    private String descripcion;
    private double precioMinorista;
    private double existencia;
    
    //constructor Generico
    public ProductosPOS(){
    };
    
    //Constructor especifico
    public ProductosPOS(String codigoInterno, String codigoBarra,String descripcion, double precioMinorista,double existencia){
        
        
        this.codigoInterno = codigoInterno;
        this.codigoBarra = codigoBarra;
        this.descripcion = descripcion;
        this.precioMinorista = precioMinorista;
        this.existencia = existencia;
        
        
    };
    
    //Getters
    
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
        return precioMinorista;
    }
    public Double getExistencia() {
        return existencia;
    }
            
    //Setters
   
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioMinorista(double precioMinorista) {
        this.precioMinorista = precioMinorista;
        
    }

    public void setExistencia(double existencia) {
       this.existencia = existencia; 
    }
    
}
