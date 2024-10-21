
package main.java.sigep.model.dao;

import java.util.List;
import main.java.sigep.model.entities.Usuarios;

/**
 *
 * @author Marito
 */
public interface UsuariosDAO {
    
    Usuarios validarUsuario(Integer user , String clave);
    Usuarios obtenerPorId(int id);
    List<Usuarios> obtenerTodos();
    void guardar(Usuarios usuario);
    void actualizar(Usuarios usuario);
    void eliminar(int id);
    
}
