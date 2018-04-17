package Conexion;

public class Conexion{
//	private DataSource dataSource=null;
//	private BasicDataSource basicDataSource=null;
//	private Connection connection=null;
////        private boolean valida=true;
//	public Conexion()
//	{
//           
//		try
//		{
//                    basicDataSource=new BasicDataSource();
//
//                    basicDataSource.setDriverClassName("org.postgresql.Driver");
//                    basicDataSource.setUsername("postgres");
//                    basicDataSource.setPassword("hernan$123");
//                    basicDataSource.setUrl("jdbc:postgresql://cnnpiura.dyndns.org:5432/BDVentas");
////                        basicDataSource.setUrl("jdbc:postgresql://seringco.dyndns.org:5432/BDVentas");
//                    basicDataSource.setMaxActive(50);
//                    basicDataSource.setMaxIdle(20);
//                    basicDataSource.setMaxOpenPreparedStatements(1000000);
//                    basicDataSource.setRemoveAbandoned(true);
//                    basicDataSource.setRemoveAbandonedTimeout(10);
//                    basicDataSource.setMaxWait(1000);
//                    basicDataSource.setDefaultAutoCommit(true);
//
//                    dataSource=basicDataSource;
//
//                    connection=dataSource.getConnection( );
//                        
//		}
//		catch(SQLException ex )
//		{
//                      connection=null;
//		}
//		catch(Exception ex)
//		{
//
////                      valida=false; 
//                       connection=null;
//		}
//             
//	}
//	
//	public void devolverConexionPool()
//                
//                
//	{
//		if(connection!=null)
//		{
//			try
//			{
//				connection.close();
//                               
//				
//			}
//			catch(Exception ex)
//			{
//				System.out.println("Error al cerrar la conexi�n "+ex.getMessage());
//			}
//		}
//	}
//	
//	public Connection getConnection() {
//
//		return connection;
//	}
//        
////        public boolean pruebaconexion(){
////       return valida;
////        
////        }
////        public boolean valida(){
////            
////        return valida ;
////        } 
}


//////////////////////////////////////////////////////////////////////////////////////////////////////











