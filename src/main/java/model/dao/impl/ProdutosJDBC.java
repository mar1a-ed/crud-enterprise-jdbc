package model.dao.impl;

import db.ConexaoDB;
import db.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ProdutosDAO;
import model.entities.Categoria;
import model.entities.Produtos;

public class ProdutosJDBC implements ProdutosDAO{
    
    private Connection conn;
    
    public ProdutosJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Produtos pr) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into produtos (nome, categoria_id, preco_uni, qtd_estoque) "
                    + "values(?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pr.getNome());
            ps.setInt(2, pr.getCategoria().getId());
            ps.setDouble(3, pr.getPrecoUni());
            ps.setInt(4, pr.getQtdEstoque());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    pr.setId(rs.getInt(1));
                }
                ConexaoDB.closeResultSet(rs);
            }
        }catch(SQLException e){
            try {
                throw new DBException("Erro ao inserir produto: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(DBException ex){
            Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Produtos pr){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("update produtos set nome = ?, categoria_id = ?, preco_uni = ?, qtd_estoque = ? where id = ?");

            ps.setString(1, pr.getNome());
            ps.setInt(2, pr.getCategoria().getId());
            ps.setDouble(3, pr.getPrecoUni());
            ps.setInt(4, pr.getQtdEstoque());
            ps.setInt(5, pr.getId());

            ps.executeUpdate();
            
        }catch(SQLException e){
            try{
                throw new DBException("Erro ao atualizar produto: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }finally{
            try{
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteById(Integer id){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("delete from produtos where id = ?");
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
        }catch(SQLException e){
            try{
                throw new DBException("Erro ao deletar produto: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try{
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Produtos findById(Integer id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement("select produtos.*, categoria_prod.tipo as cat_nome " +
                "from produtos inner join categoria_prod on produtos.categoria_id = categoria_prod.id " +
                "where produtos.id = ?");
            
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                return initProduto(rs);
            }
            
            return null;
            
        }catch(SQLException e){
            try{
                throw new DBException("Erro ao buscar produto: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }finally{
            try{
                ConexaoDB.closeResultSet(rs);
                ConexaoDB.closeStatement(ps);
                
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    @Override
    public List<Produtos> findAll(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement("select produtos.*, categoria_prod.tipo as cat_nome " +
                "froom produtos inner join categoria_prod on produtos.categoria_id = categoria_prod.id " +
                "order by produtos.nome");
            
            rs = ps.executeQuery();

            List<Produtos> list = new ArrayList<>();
            
            while(rs.next()){
                list.add(initProduto(rs));
            }
            
            return list;
            
        }catch(SQLException e){           
            try{
                throw new DBException("Erro ao listar produtos: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }finally{           
            try{
                ConexaoDB.closeResultSet(rs);
                ConexaoDB.closeStatement(ps);               
            }catch(DBException ex){
                Logger.getLogger(ProdutosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    private Produtos initProduto(ResultSet rs) throws SQLException{
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("categoria_id"));
        cat.setTipo(rs.getString("cat_nome"));

        Produtos pr = new Produtos();
        pr.setId(rs.getInt("id"));
        pr.setNome(rs.getString("nome"));
        pr.setPrecoUni(rs.getDouble("preco_uni"));
        pr.setQtdEstoque(rs.getInt("qtd_estoque"));
        pr.setCategoria(cat); 
        
        return pr;    
    
    }
}
