package Conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.apache.commons.dbcp.BasicDataSource;

public class Conexion {
	private DataSource dataSource=null;
	private BasicDataSource basicDataSource=null;
	private Connection connection=null;
        private boolean valida=true;
	public Conexion()
	{
           
		try
		{
			basicDataSource=new BasicDataSource();
			
			basicDataSource.setDriverClassName("org.postgresql.Driver");
			basicDataSource.setUsername("postgres");
			basicDataSource.setPassword("hernan$123");
			basicDataSource.setUrl("jdbc:postgresql://192.168.0.109:5432/BDVentas");
			basicDataSource.setMaxActive(50);
			basicDataSource.setMaxIdle(20);
                        basicDataSource.setMaxOpenPreparedStatements(1000);
			basicDataSource.setRemoveAbandoned(true);
			basicDataSource.setRemoveAbandonedTimeout(300);
			basicDataSource.setMaxWait(1000);
			basicDataSource.setDefaultAutoCommit(true);
                        
			dataSource=basicDataSource;
			
			connection=dataSource.getConnection();
                        
		}
		catch(SQLException ex )
		{
//			JOptionPane.showMessageDialog(null,"SqlException "+ex.getMessage());
//                        JOptionPane.showMessageDialog(null,"No se obtuvo conexion con la base de datos","",JOptionPane.ERROR_MESSAGE);
                     valida=false;
		}
		catch(Exception ex)
		{
			///System.out.println("Exception "+ex.getMessage());
//                        JOptionPane.showMessageDialog(null,"Exception "+ex.getMessage());
                      valida=false; 
		}
             
	}
	
	public void devolverConexionPool()
                
                
	{
		if(connection!=null)
		{
			try
			{
				connection.close();
				
			}
			catch(Exception ex)
			{
				System.out.println("Error al cerrar la conexi�n "+ex.getMessage());
			}
		}
	}
	
	public Connection getConnection() {
//                if(connection==null){
//                valida=false;
//                }else {valida=true;}
		return connection;
	}
        
        public boolean pruebaconexion(){
       return valida;
        
        }
//        public boolean valida(){
//            
//        return valida ;
//        } 
}
