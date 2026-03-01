package model.dao.impl;

import db.ConexaoDB;
import db.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.UsuariosDAO;
import model.entities.Usuario;

public class UsuariosJDBC implements UsuariosDAO{

    private Connection conn;
    
    public UsuariosJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public Usuario validarLogin(String usuario, String senha){
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement("select * from usuarios where usuario = ? and senha = ?");
        
            st.setString(1, usuario);
            st.setString(2, senha);
        
            rs = st.executeQuery();
        
            if(rs.next()){
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setSenha(rs.getString("senha"));

                return user;
            }
            
        }catch(SQLException e){
            try{
                throw new DBException("Erro ao validar login: " + e.getMessage());
            }catch(DBException ex){
                Logger.getLogger(UsuariosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            try{
                ConexaoDB.closeResultSet(rs);
                ConexaoDB.closeStatement(st);
            }catch(DBException ex){
                Logger.getLogger(UsuariosJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    @Override
    public void insert(Usuario user){
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("insert into usuarios (usuario, senha, perfil, cnpj)"
                    + "values (?, ?, ?, ?)");
            
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getSenha());
            ps.setString(3, user.getPerfil());
            ps.setString(4, user.getCnpj());
            
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
    public void update(Usuario user, Integer id) {
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("update usuarios set perfil = ? where id = ?");
            
            ps.setString(1, user.getPerfil());
            ps.setInt(2, id);
            
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
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("delete from usuarios where id = ?");
            
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
    
}
