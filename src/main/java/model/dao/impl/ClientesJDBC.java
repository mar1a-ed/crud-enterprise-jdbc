package model.dao.impl;

import db.ConexaoDB;
import db.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.ClientesDAO;
import model.entities.Clientes;

public class ClientesJDBC implements ClientesDAO{
    private Connection conn;
    
    public ClientesJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Clientes c){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("insert into clientes (fname, lname, cpf, data_nasc, email, rua, numero, bairro)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, c.getFname());
            ps.setString(2, c.getLname());
            ps.setString(3, c.getCpf());
            ps.setDate(4, c.getData_nasc());
            ps.setString(5, c.getEmail());
            ps.setString(8, c.getRua());
            ps.setInt(9, c.getNumero());
            ps.setString(10, c.getBairro());
            
            ps.executeUpdate();
            
        }catch(SQLException e){
            try{
                throw new DBException(e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try{
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex) {
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Clientes c){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("update clientes set rua = ?, numero = ?, bairro = ?"
                    + "where id = ?");
            
            ps.setString(3, c.getRua());
            ps.setInt(4, c.getNumero());
            ps.setString(5, c.getBairro());
            
            ps.executeUpdate();
        }catch(SQLException e){
            
        }finally{
            try{
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteById(Integer id){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("delete from clientes where id = ?");
            
            ps.setInt(1, id);
            
            int qtd = ps.executeUpdate();
			
            if(qtd == 0){
		throw new DBException("Id Inexistente!");
            }
			
        }catch(SQLException e){
            try{
                throw new DBException(e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(DBException ex){
            Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                ConexaoDB.closeStatement(ps);
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Clientes findById(Integer id){
        PreparedStatement ps = null;
	ResultSet rs = null;
		
	try{
            ps = conn.prepareStatement("select * from clientes where id = ?");
			
            ps.setInt(1, id);
			
            rs = ps.executeQuery();
			
            if(rs.next()){
                Clientes cliente = initClient(rs);
                return cliente;
            }
			
            return null;
			
	}catch(SQLException e){
            try{
                throw new DBException(e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
	}finally{
            try{
                ConexaoDB.closeStatement(ps);
                ConexaoDB.closeResultSet(rs);
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        return null;
    }

    @Override
    public List<Clientes> findAll() {
        PreparedStatement ps = null;
	ResultSet rs = null;
		
	try{
            ps = conn.prepareStatement("select * from clientes");
			
            rs = ps.executeQuery();
			
            List<Clientes> list = new ArrayList<>();
            Map<Integer, Clientes> map = new HashMap<>();
			
            while(rs.next()){
                Clientes c = map.get(rs.getInt("id"));
				
                if(c==null){
                    c = initClient(rs);
                    map.put(rs.getInt("Id"), c);
                }
				
                list.add(c);
            }
			
            return list;
			
        }catch(SQLException e){
            try {
                throw new DBException(e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
	}finally{
            try{
                ConexaoDB.closeStatement(ps);
                ConexaoDB.closeResultSet(rs);
            }catch(DBException e){
                Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, e);
            }
	}
        
        return null;
    }

    private Clientes initClient(ResultSet rs){
        try {
            Clientes c = new Clientes();
            
            c.setRua(rs.getString("rua"));
            c.setNumero(rs.getInt("numero"));
            c.setBairro(rs.getString("bairro"));
            
            return c;
        }catch(SQLException e){
            Logger.getLogger(ClientesJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
}
