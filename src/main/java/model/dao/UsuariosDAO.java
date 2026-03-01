package model.dao;

import model.entities.Usuario;

public interface UsuariosDAO {
    
    Usuario validarLogin(String usuario, String senha);
    
    void insert(Usuario user);
    
    void update(Usuario user, Integer id);
    
    void deleteById(Integer id);
}
