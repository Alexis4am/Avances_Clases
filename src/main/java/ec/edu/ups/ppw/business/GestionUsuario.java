package ec.edu.ups.ppw.business;

import java.util.List;
import ec.edu.ups.ppw.dao.UsuarioDAO; // Asegúrate de usar UsuarioDAO, no PersonaDAO
import ec.edu.ups.ppw.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuario {

    @Inject
    private UsuarioDAO daoUsuario; // Usamos el DAO que creamos antes

    // CREATE
    public void createUsuario(Usuario usuario) {
        // Aquí podrías validar cosas (ej: que el email tenga @, que el password sea seguro)
        daoUsuario.insert(usuario);
    }

    // READ (Todos)
    public List<Usuario> getUsuarios() {
        return daoUsuario.getAll();
    }

    // READ (Uno)
    public Usuario getUsuarioPorId(int id) {
        return daoUsuario.read(id);
    }
    
    // Método extra para el Login (que usamos en el AuthService)
    public Usuario getUsuarioPorEmail(String email) {
        return daoUsuario.getUsuarioPorEmail(email);
    }

    // UPDATE
    public void updateUsuario(Usuario usuario) {
        daoUsuario.update(usuario);
    }

    // DELETE
    public void deleteUsuario(int id) {
        daoUsuario.delete(id);
    }
}