/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author info2017
 */
public class ProveedorDAO {
      public List<Proveedor> mostrarproveedor(JTable tabla){
        
            DefaultTableModel modelo= new DefaultTableModel(){
       public boolean isCellEditable(int row, int column) {
     //      if (column == 5) return true;
     //else
      return false;
     }
       };      
      String titulos[]={"RUT","NOMBRES","CELULAR"};
      modelo.setColumnIdentifiers(titulos);

       Conexion conexion = new Conexion();
         Statement st=null;
         List<Proveedor> listprov= new ArrayList<>();
    
     
    try{
	String sql=("SELECT * from sp_mostrarproveedor()");
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
       ResultSet rs=ps.executeQuery(); 
        Object datosR[] = new Object[3];
        while (rs.next()){
                    
            Proveedor prove = new Proveedor();
            prove.setIdproveedor(rs.getLong("id"));
            prove.setRut(rs.getString("vrut"));
            prove.setNombrerazons( rs.getString("vnombre"));
            prove.setCelular( rs.getString("vcelular"));
           
             datosR[0] =prove.getRut();
           
             datosR[1] = prove.getNombrerazons();
            
             datosR[2] = prove.getCelular();
            
             listprov.add(prove);
            modelo.addRow(datosR);
		
        }
        tabla.setModel(modelo);
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(90);
        rs.close();
        ps.close();
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    return listprov;
    }
    
public  List<Proveedor> busquedasensitivaproveedor(String tipoB,String cadena,JTable tabla){
        
       DefaultTableModel modelo= new DefaultTableModel(){
  public boolean isCellEditable(int row, int column) {
//      if (column == 5) return true;
//else
 return false;
}
  };      
 String titulos[]={"RUT","NOMBRES","CELULAR"};
 modelo.setColumnIdentifiers(titulos);
 
 Conexion conexion= new Conexion();
   List<Proveedor> listprov= new ArrayList<>();
     
    try{
	String sql = ("SELECT * from sp_busquedasensitivaproveedor(?,?)");
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        System.out.println("tipo"+tipoB);
        System.out.println("cadena"+cadena);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        ResultSet rs=ps.executeQuery();
        Object datosR[] = new Object[3];
        while (rs.next()){
            Proveedor prove = new Proveedor();
            prove.setIdproveedor(rs.getLong("id"));
            prove.setRut(rs.getString("vrut"));
            prove.setNombrerazons( rs.getString("vnombre"));
            prove.setCelular( rs.getString("vcelular"));
           
             datosR[0] =prove.getRut();
           
             datosR[1] = prove.getNombrerazons();
            
             datosR[2] = prove.getCelular();
            
             listprov.add(prove);
                    
                    modelo.addRow(datosR);
		
        }
        tabla.setModel(modelo);
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(400);
        columnModel.getColumn(3).setPreferredWidth(90);
      
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
              conexion.devolverConexionPool();
            }    
    return listprov;
    
    }

public Proveedor buscarproveedor(String tipoB,long id){

    Proveedor proveedor= new Proveedor();
    Conexion conexion = new Conexion();
     try{
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedaproveedor('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedaproveedor(?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ResultSet rs = ps.executeQuery();
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
                     proveedor.setIdproveedor(rs.getInt("id"));
                     proveedor.setNombrerazons(rs.getString("vnombre"));
                     proveedor.setRut( rs.getString("vrut")) ;
                     
                     proveedor.setDireccion(rs.getString("vdireccion"));
                     proveedor.setCelular(rs.getString("vcelular"));
                     proveedor.setEmail(rs.getString("vemail"));
 
        
        }
        rs.close();
        ps.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            }
     return proveedor;

}
public void insertarproveedor(Proveedor proveedor){

//  byte[] FOTO= cliente.getImg();
  
//    System.out.println("fotoinsert"+FOTO);
        PreparedStatement preparedStatement = null;
       Conexion conexion= new Conexion();
     try{
           
          
            String insertImageSql = "SELECT * from sp_insertarproveedor(?,?,?,?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            preparedStatement = conexion.getConnection().prepareStatement(insertImageSql);
            
////            System.out.println("rutainsert"+ruta);
////            File file = new File(ruta);
////            FileInputStream fis = new FileInputStream(file);
//            InputStream fis = new ByteArrayInputStream(FOTO); 
//            System.out.println("fisinset"+fis);
            preparedStatement.setString(1,proveedor.getNombrerazons());
            preparedStatement.setString(2,proveedor.getRut());
            preparedStatement.setString(3,proveedor.getDireccion());
            preparedStatement.setString(4,proveedor.getCelular());
            preparedStatement.setString(5,proveedor.getEmail());
//            preparedStatement.setString(6,PRIORITARIO);
//            preparedStatement.setBinaryStream(7,fis);
            
            
            
            preparedStatement.execute();
            preparedStatement.close();
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }

}
public void editarproveedor(Proveedor proveedor){

    Conexion conexion = new Conexion();

     try{
//	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_editarproveedor(?,?,?,?,?,?)");         
       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       
            ps.setLong(1,proveedor.getIdproveedor());
             ps.setString(2,proveedor.getNombrerazons());
            ps.setString(3,proveedor.getRut());
            ps.setString(4,proveedor.getDireccion());
            ps.setString(5,proveedor.getCelular());
            ps.setString(6,proveedor.getEmail());
//            ps.setString(7,PRIORITARIO);
//            ps.setBinaryStream(8, fis);
            ps.execute();
            ps.close();
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
     }        
            
           
        
    System.out.println("insertar fpac");
 

}
public void eliminarproveedor(long idproveedor){


 
 
    Conexion conexion = new Conexion();

     try{
	
        System.out.println("SELECT * from sp_eliminarproveedor("+idproveedor+")");
      String sql=("SELECT * from sp_eliminarproveedor(?)");       
      PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
      ps.setLong(1, idproveedor);
      ResultSet rs= ps.executeQuery();
      
       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
 
    
 

    
}
public boolean duplicado(long id,String cadena,String tipoop){

    Conexion conexion = new Conexion();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_duplicadoproveedor(?,?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, cadena);
      ps.setLong(2, id);
      ps.setString(3, tipoop);
      rs = ps.executeQuery();
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
            valida= rs.getBoolean("vvalida");
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            conexion.devolverConexionPool();
     
     }
     return valida;

    }
    
}
