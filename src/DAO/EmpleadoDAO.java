/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.Empleado;
import Pojos.EmpleadoSingleton;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class EmpleadoDAO {
    
    public void mostrarempleado(JTable tabla){
        
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
           return false;
      }
      };      
     String titulos[]={"ID","RUT","NOMBRES","CELULAR"};
     modelo.setColumnIdentifiers(titulos);

      Conexion conexion = new Conexion();
          
     
    try{
	
      
        String sql=("SELECT * from sp_mostrarempleado()"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[4];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vcelular");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
    
    public void busquedasensitivaempleado(JTable tabla,String tipoB,String cadena){
        
       DefaultTableModel modelo= new DefaultTableModel(){
  public boolean isCellEditable(int row, int column) {
//      if (column == 5) return true;
//else
 return false;
}
  };      
 String titulos[]={"ID","RUT","NOMBRES","CELULAR"};
 modelo.setColumnIdentifiers(titulos);
 
  Conexion conexion = new Conexion();
     
    try{
	
        System.out.println("SELECT * from sp_busquedasensitivaempleado('"+tipoB+"','"+cadena+"')");
        String sql=("SELECT * from sp_busquedasensitivaempleado(?,?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
       
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[4];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vcelular");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        rs.close();
        ps.close();
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               conexion.devolverConexionPool();
            }    

    
    }

public Empleado buscarempleado(String tipoB,long id,JLabel jlbl){
//Connection miconexion = conectar.Connect();
//    Statement st=null;
    Empleado empleado= new Empleado();
     ImageIcon imageIcon =null;
//    ImageIcon imageIcon = new ImageIcon(getClass().getResource("../imagenes/user-not-image.png"));
    Conexion conexion = new Conexion();
     try{
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedaempleado('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedaempleado(?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      
      ResultSet rs= ps.executeQuery();
      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
                     empleado.setId_empleado(rs.getLong("id"));
                     empleado.setRut( rs.getString("vrut")) ;
                     empleado.setNombre(rs.getString("vnombre"));
                    
                     empleado.setCelular(rs.getString("vcelular"));
                     empleado.setDireccion(rs.getString("vdireccion"));
                     empleado.setEmail(rs.getString("vemail"));
                     empleado.setCargo(rs.getString("vcargo"));
                     

                     
                      byte[] imgBytes = rs.getBytes("vfoto");
                   
//                      System.out.println("byte"+rs.getBytes("vfoto"));
                      
                      empleado.setFoto(imgBytes);
                      System.out.println("imgbytes"+imgBytes);

                    imageIcon = new ImageIcon(imgBytes);

        }
        ImageIcon imageUser = imageIcon;
        Image img = imageUser.getImage();
        Image newimg = img.getScaledInstance(jlbl.getWidth(), jlbl.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageUser = new ImageIcon(newimg);
        jlbl.setIcon(imageUser);
        ps.close();
	rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
return empleado;
}
public EmpleadoSingleton cargarempleadosinfoto(String tipoB,long id){

    EmpleadoSingleton empleado= EmpleadoSingleton.getinstancia();

    Conexion conexion = new Conexion();
     try{

         System.out.println("SELECT * from sp_busquedaempleado('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedaempleado('"+tipoB+"',"+id+")"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
      FileInputStream fis ;

        if (rs.next()){
                     empleado.setId_empleado(rs.getLong("id"));
                     empleado.setRut( rs.getString("vrut")) ;
                     empleado.setNombre(rs.getString("vnombre"));
                    
                     empleado.setCelular(rs.getString("vcelular"));
                     empleado.setDireccion(rs.getString("vdireccion"));
                     empleado.setEmail(rs.getString("vemail"));
                     empleado.setCargo(rs.getString("vcargo"));
 
        }
      
        ps.close();
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }
return empleado;
}

public void insertarempleado(Empleado empleado){

 
  byte[] FOTO= empleado.getFoto();
  
    System.out.println("fotoinsert"+FOTO);
     Conexion conexion = new Conexion();
     try{
           
          
            String insertImageSql = "SELECT * from sp_insertarempleado(?,?,?,?,?,?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            PreparedStatement ps = conexion.getConnection().prepareStatement(insertImageSql);
           

            InputStream fis = new ByteArrayInputStream(FOTO); 
            System.out.println("fisinset"+fis);
            ps.setString(1,empleado.getNombre());
            ps.setString(2,empleado.getRut());
          
            ps.setString(3,empleado.getCelular());
            ps.setString(4,empleado.getDireccion());
            ps.setString(5,empleado.getEmail());
            ps.setString(6,empleado.getCargo());
            ps.setBinaryStream(7,fis);
            
            
            
          ps.executeQuery();
          ps.close();
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }

}
public void editarempleado(Empleado empleado){

  byte[] FOTO= empleado.getFoto();
  
    Conexion conexion = new Conexion();

     try{
	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_editarempleado(?,?,?,?,?,?,?,?)");         
       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       
            ps.setLong(1,empleado.getId_empleado());
            ps.setString(2,empleado.getNombre());
           
            ps.setString(3,empleado.getRut());
            ps.setString(4,empleado.getCelular());
            ps.setString(5,empleado.getDireccion());
            ps.setString(6,empleado.getEmail());
            ps.setString(7,empleado.getCargo());      
            ps.setBinaryStream(8, fis);
            
            ps.executeQuery();
            ps.close();
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }        
            
           
        
 
 

}
public void eliminarempleado(long idempleado){

 Conexion conexion = new Conexion();
    Statement st=null;
    ResultSet rs=null;
     try{
	st= conexion.getConnection().createStatement();
        System.out.println("SELECT * from sp_eliminarempleado("+idempleado+")");
        
        String sql=("SELECT * from sp_eliminarempleado(?)");       
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idempleado);
        rs=ps.executeQuery();
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
      
      String sql=("SELECT * from sp_duplicadoempleado(?,?,?)"); 
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
