
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
    private String rolDescripcion;
    
  
//Constructores

    public Usuarios(){
};
    public Usuarios(Integer idUsuario,String nombre,String clave, Integer idRol ){
        
        this.idUsuario  =   idUsuario;
        this.nombre     =   nombre;
        this.idRol      =   idRol; 
        this.clave      =   clave;
    
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
    
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }
    
    public Object[] toObject() {
        return new Object[] { idUsuario, nombre, String.format("%02d", idRol)+"-"+rolDescripcion,clave};
    }
    
}
