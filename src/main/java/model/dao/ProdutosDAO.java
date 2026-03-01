package model.dao;

import java.util.List;
import model.entities.Produtos;

public interface ProdutosDAO {
    
    void insert(Produtos pr);
    
    void update(Produtos pr);
    
    void deleteById(Integer id);
    
    Produtos findById(Integer id);
    
    List<Produtos> findAll();
}
