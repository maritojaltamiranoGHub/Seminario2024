
package main.java.sigep.model.entities;

/**
 *
 * @author Marito
 */
public class Roles {
    
    private int idRol;
    private String nombreRol;

   //Constructores

    public Roles(){
};
    public Roles(Integer idRol,String nombreRol){
        
       
        this.idRol      =   idRol; 
        this.nombreRol  =   nombreRol;
    
    };

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    @Override
    public String toString(){
        return String.format("%02d", idRol) + "-" + nombreRol;
    }
    
    
    
    
}
