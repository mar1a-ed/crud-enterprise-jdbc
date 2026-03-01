package model.dao;

import java.util.List;
import model.entities.Clientes;

public interface ClientesDAO {
        
    void insert(Clientes c);
    
    void update(Clientes c);
    
    void deleteById(Integer id);
    
    Clientes findById(Integer id);
    
    List<Clientes> findAll();
}
