
package main.java.sigep.model.entities;

/**
 *
 * @author Marito
 */
public class Usuarios {
    
    //Atributos
    private int idUsuario;
    private String nombre;
    private String clave;
    private int idRol;
    
  
//Constructores

    public Usuarios(){
};
    public Usuarios(Integer idUsuario,String nombre, Integer idRol ){
        
        this.idUsuario  =   idUsuario;
        this.nombre     =   nombre;
        this.idRol      =   idRol; 
    
    };

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    
    
    
}
