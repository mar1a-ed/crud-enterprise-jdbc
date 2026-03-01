package model.dao;

import java.util.List;
import model.entities.Pedidos;

public interface PedidosDAO {
    
    void insert(Pedidos pe);
        
    void deleteById(Integer id);
    
    Pedidos findById(Integer id);
        
    List<Pedidos> findByIdProduto(Integer id);
    
    List<Pedidos> findAll();
}
