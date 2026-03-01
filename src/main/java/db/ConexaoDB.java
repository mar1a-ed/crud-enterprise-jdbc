package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexaoDB {
    private static Connection conn = null;
    
    private static Properties carregarPropriedades() throws DBException {
	try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
	}catch(IOException e){
            throw new DBException(e.getMessage());                   
	}
        
    }
    
    
	
    public static Connection getConnection() throws DBException{
	if(conn==null){
            try{
		Properties props = carregarPropriedades();
		String url = props.getProperty("dburl");
		conn = DriverManager.getConnection(url, props);
            }catch(SQLException e){
		throw new DBException(e.getMessage());
            }
	}
	
        return conn;
    }
    
    public static void closeConnection() throws DBException{
	if(conn!=null){
            try{
		conn.close();
            }catch(SQLException e){
                throw new DBException(e.getMessage());
            }
	}
    }
	
    public static void closeStatement(Statement st) throws DBException{
	if(st!=null){
            try{
		st.close();
            }catch(SQLException e){
		throw new DBException(e.getMessage());
            }
	}
    }
	
    public static void closeResultSet(ResultSet rs) throws DBException{
	if(rs!=null){
            try{
		rs.close();
            }catch(SQLException e){
		throw new DBException(e.getMessage());
            }
	}
    }
    
}
