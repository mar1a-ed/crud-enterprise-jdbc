package model.dao;

import db.ConexaoDB;
import db.DBException;
import model.dao.impl.ClientesJDBC;
import model.dao.impl.PedidosJDBC;
import model.dao.impl.ProdutosJDBC;
import model.dao.impl.UsuariosJDBC;

public class DAOFactory {
    
    public static ClientesDAO createClienteDao() throws DBException{
        return new ClientesJDBC(ConexaoDB.getConnection());
    }
    
    public static PedidosDAO createPedidosDao() throws DBException{
        return new PedidosJDBC(ConexaoDB.getConnection());
    }
    
    public static ProdutosDAO createProdutosDao() throws DBException{
        return new ProdutosJDBC(ConexaoDB.getConnection());
    }
    
    public static UsuariosDAO createUsuarioDao() throws DBException{
        return new UsuariosJDBC(ConexaoDB.getConnection());
    }
}
