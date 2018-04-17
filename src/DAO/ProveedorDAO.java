/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;



import Conexion.ConexionBD;
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
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
       public boolean isCellEditable(int row, int column) {
     //      if (column == 5) return true;
     //else
      return false;
     }
       };      
      String titulos[]={"RUT","NOMBRES","CELULAR"};
      modelo.setColumnIdentifiers(titulos);
      tabla.setModel(modelo);
    TableColumnModel columnModel = tabla.getColumnModel();

    columnModel.getColumn(0).setPreferredWidth(90);
    columnModel.getColumn(1).setPreferredWidth(400);
    columnModel.getColumn(2).setPreferredWidth(90);
       
         Statement st=null;
         List<Proveedor> listprov= new ArrayList<>();
    
     
    try{
	String sql=("SELECT * from sp_mostrarproveedor()");
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
       ResultSet rs=Cbd.RealizarConsulta(ps); 
        Object datosR[] = new Object[3];
        while (rs.next()){
                    
            Proveedor prove = new Proveedor();
            prove.setIdproveedor(rs.getLong("id"));
            prove.setRut(rs.getString("vrut"));
            prove.setNombrerazons( rs.getString("vnombre"));
            prove.setCelular( rs.getString("vcelular"));
            prove.setDireccion(rs.getString("vdireccion"));
            prove.setEmail(rs.getString("vemail"));
           
             datosR[0] =prove.getRut();
           
             datosR[1] = prove.getNombrerazons();
            
             datosR[2] = prove.getCelular();
            
             listprov.add(prove);
            modelo.addRow(datosR);
		
        }
        
        
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    return listprov;
    }
    
public  List<Proveedor> busquedasensitivaproveedor(String tipoB,String cadena,JTable tabla){
    ConexionBD Cbd = new ConexionBD();    
    DefaultTableModel modelo= new DefaultTableModel(){
      public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
    //else
     return false;
    }
      };      
     String titulos[]={"RUT","NOMBRES","CELULAR"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
     
      TableColumnModel columnModel = tabla.getColumnModel();
     
    columnModel.getColumn(0).setPreferredWidth(90);
    columnModel.getColumn(1).setPreferredWidth(400);
    columnModel.getColumn(2).setPreferredWidth(90);
     
     
     
    List<Proveedor> listprov= new ArrayList<>();
    Object datosR[] = new Object[3];

      
     
    try{
        
	String sql = ("SELECT * from sp_busquedasensitivaproveedor(?,?)");
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        System.out.println("tipo"+tipoB);
        System.out.println("cadena"+cadena);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        ResultSet rs=Cbd.RealizarConsulta(ps);
       
        while (rs.next()){
            Proveedor prove = new Proveedor();
            prove.setIdproveedor(rs.getLong("id"));
            prove.setRut(rs.getString("vrut"));
            prove.setNombrerazons( rs.getString("vnombre"));
            prove.setCelular( rs.getString("vcelular"));
            prove.setDireccion(rs.getString("vdireccion"));
            prove.setEmail(rs.getString("vemail"));
           
             datosR[0] =prove.getRut();
           
             datosR[1] = prove.getNombrerazons();
            
             datosR[2] = prove.getCelular();
            
             listprov.add(prove);
                    
            modelo.addRow(datosR);
		
        }
       
       
        
        if (tabla.getRowCount()>0){
            tabla.setRowSelectionInterval (0,0); 
               
        }
      
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
              Cbd.desconectar();
            }    
    return listprov;
    
    }

public Proveedor buscarproveedor(String tipoB,long id){

    Proveedor proveedor= new Proveedor();
    ConexionBD Cbd = new ConexionBD();
     try{
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedaproveedor('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedaproveedor(?,?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ResultSet rs =Cbd.RealizarConsulta(ps);
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
        
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
     return proveedor;

}
public void insertarproveedor(Proveedor proveedor){

//  byte[] FOTO= cliente.getImg();
  
//    System.out.println("fotoinsert"+FOTO);
        PreparedStatement preparedStatement = null;
       ConexionBD Cbd = new ConexionBD();
     try{
           
          
            String insertImageSql = "SELECT * from sp_insertarproveedor(?,?,?,?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            preparedStatement = Cbd.conectar().prepareStatement(insertImageSql);
            
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
            
            
            
           Cbd.actualizarDatos(preparedStatement);
            
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         Cbd.desconectar();
     
     }

}
public void editarproveedor(Proveedor proveedor){

    ConexionBD Cbd = new ConexionBD();
     try{
//	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_editarproveedor(?,?,?,?,?,?)");         
       PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
       
            ps.setLong(1,proveedor.getIdproveedor());
             ps.setString(2,proveedor.getNombrerazons());
            ps.setString(3,proveedor.getRut());
            ps.setString(4,proveedor.getDireccion());
            ps.setString(5,proveedor.getCelular());
            ps.setString(6,proveedor.getEmail());
//            ps.setString(7,PRIORITARIO);
//            ps.setBinaryStream(8, fis);
            Cbd.RealizarConsulta(ps);
            
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Proveedor editado exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }        
            
           
        
    System.out.println("insertar fpac");
 

}
public void eliminarproveedor(long idproveedor){


 
 
    ConexionBD Cbd = new ConexionBD();

     try{
	
        System.out.println("SELECT * from sp_eliminarproveedor("+idproveedor+")");
      String sql=("SELECT * from sp_eliminarproveedor(?)");       
      PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
      ps.setLong(1, idproveedor);
      ResultSet rs=Cbd.RealizarConsulta(ps);
      
       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    
 
    
 

    
}
public boolean duplicado(long id,String cadena,String tipoop){

    ConexionBD Cbd = new ConexionBD();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_duplicadoproveedor(?,?,?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, cadena);
      ps.setLong(2, id);
      ps.setString(3, tipoop);
      rs = Cbd.RealizarConsulta(ps);
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
            valida= rs.getBoolean("vvalida");
        }

    
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            Cbd.desconectar();
     
     }
     return valida;

    }
    
}
