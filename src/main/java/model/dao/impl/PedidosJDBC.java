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
import model.dao.PedidosDAO;
import model.entities.Clientes;
import model.entities.ItemPedido;
import model.entities.Pedidos;

public class PedidosJDBC implements PedidosDAO{

    private Connection conn;
    
    public PedidosJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insert(Pedidos pe) {
        PreparedStatement ps = null;
        PreparedStatement psItem = null;
        
        try{
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement("insert into pedidos (cliente_id, preco_total) values"
                    + "(?, ?)",Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, pe.getCliente().getId());
            ps.setDouble(2, pe.getPrecoTotal());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int pedidoId = 0;
            if (rs.next()) pedidoId = rs.getInt(1);

            
            psItem = conn.prepareStatement("insert into item_pedido (pedido_id, produto_id, qtd, preco_uni) values (?, ?, ?, ?)");
        
            for (ItemPedido item : pe.getItensPedido()) {
                psItem.setInt(1, pedidoId);
                psItem.setInt(2, item.getProduto().getId());
                psItem.setInt(3, item.getQuantidade());
                psItem.setDouble(4, item.getPrecoUnitario());
                psItem.executeUpdate();
            }

            conn.commit();
            
        }catch(SQLException e){
            try { 
                conn.rollback();          
                try {
                    throw new DBException("Erro no pedido: " + e.getMessage());
                }catch(DBException ex){
                    Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }catch(SQLException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ConexaoDB.closeStatement(ps);
                }catch(DBException ex){
                    Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("delete from item_pedido where pedido_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            ps = conn.prepareStatement("delete from pedidos where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        }catch(SQLException e){
            try{
                throw new DBException("Erro ao deletar pedido: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try{
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex) {
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Pedidos findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement("select pedidos.*, clientes.fname, clientes.lname " +
            "from pedidos inner join clientes on pedidos.cliente_id = clientes.id where pedidos.id = ?");
            
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                Clientes cli = new Clientes();
                cli.setId(rs.getInt("cliente_id"));
                cli.setFname(rs.getString("fname"));
                cli.setLname(rs.getString("lname"));

                Pedidos ped = new Pedidos();
                ped.setId(rs.getInt("id"));
                ped.setDataPedido(rs.getDate("data_pedido"));
                ped.setPrecoTotal(rs.getDouble("preco_total"));
                ped.setCliente(cli);

                return ped;
            }
            
            return null;
        }catch(SQLException e){
            try{
                throw new DBException("Erro ao buscar pedido por ID: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }finally{
            try{
                ConexaoDB.closeResultSet(rs);
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    @Override
    public List<Pedidos> findByIdProduto(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement("select distinct pedidos.*, clientes.fname, clientes.lname from pedidos " +
                "inner join clientes ON pedidos.cliente_id = clientes.id inner join item_pedido ON pedidos.id = item_pedido.pedido_id"
                +"where item_pedido.produto_id = ? order by pedidos.data_pedido desc");
        
            ps.setInt(1, rs.getInt("produto_id"));
            rs = ps.executeQuery();

            List<Pedidos> list = new ArrayList<>();
            
            while(rs.next()){
                Clientes cli = new Clientes();
                cli.setId(rs.getInt("cliente_id"));
                cli.setFname(rs.getString("fname"));
                cli.setLname(rs.getString("lname"));

                Pedidos ped = new Pedidos();
                ped.setId(rs.getInt("id"));
                ped.setDataPedido(rs.getDate("data_pedido"));
                ped.setPrecoTotal(rs.getDouble("preco_total"));
                ped.setCliente(cli);

                list.add(ped);
            }
        
            return list;
            
        }catch(SQLException e){
             try {
                throw new DBException("Erro ao buscar pedidos por produto: " + e.getMessage());
            } catch (DBException ex) {
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }finally{
            try{
                ConexaoDB.closeResultSet(rs);
                 ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    @Override
    public List<Pedidos> findAll(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement("select pedidos.*, clientes.fname, clientes.lname from pedidos inner join clientes on "
                    + "pedidos.cliente_id = clientes.id order by pedidos.data_pedido desc");

            rs = ps.executeQuery();
            List<Pedidos> list = new ArrayList<>();

            while(rs.next()){
                Clientes cli = new Clientes();
                cli.setId(rs.getInt("cliente_id"));
                cli.setFname(rs.getString("fname"));
                cli.setLname(rs.getString("lname"));

                Pedidos ped = new Pedidos();
                ped.setId(rs.getInt("id"));
                ped.setDataPedido(rs.getDate("data_pedido"));
                ped.setPrecoTotal(rs.getDouble("preco_total"));
                ped.setCliente(cli);

                list.add(ped);
            }
            return list;
            
        }catch(SQLException e){
            try{
                throw new DBException("Erro ao listar pedidos: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try{
                ConexaoDB.closeResultSet(rs);
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(PedidosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }
    
    
}

    