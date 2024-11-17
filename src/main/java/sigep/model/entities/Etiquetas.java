package main.java.sigep.model.entities;

import main.java.sigep.model.entities.ProductosPOS;



/**
 *
 * @author Marito
 */
public class Etiquetas extends ProductosPOS{

    private Double precio;
    private Double fraccion;
    private Double total;

    public Etiquetas(ProductosPOS productos) {
        super(productos.getCodigoInterno(),productos.getCodigoBarra(),productos.getDescripcion(),
                productos.getExistencia(),productos.getPrecioMinorista());
    }
   

    public Double getFraccion() {
        return fraccion;
    }

    public void setFraccion(Double fraccion) {
        this.fraccion = fraccion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double importe) {
        this.total = importe;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}

