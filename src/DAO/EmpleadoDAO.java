/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.ConexionBD;
import Pojos.Empleado;
import Pojos.EmpleadoSingleton;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
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
public class EmpleadoDAO {
    
    
    public List<Empleado> mostrarempleado(JTable tabla){
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

     
          
     List<Empleado> listempleado= new ArrayList<>();
    try{
	
      
        String sql=("SELECT * from sp_mostrarempleado()"); 
        PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
        ResultSet rs = Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[3];
        while (rs.next()){
                    
             Empleado empleado = new Empleado();
             empleado.setId_empleado(rs.getLong("id"));
             empleado.setNombre(rs.getString("vnombre"));
             empleado.setRut(rs.getString("vrut"));
             empleado.setDireccion(rs.getString("vdireccion"));
             empleado.setCelular(rs.getString("vcelular"));
             datosR[0] = empleado.getRut();

             datosR[1] = empleado.getNombre();

             datosR[2] = empleado.getCelular();
                    
                    
            modelo.addRow(datosR);
            listempleado.add(empleado);
        }
        tabla.setModel(modelo);
       
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(90);
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    return listempleado;
    }
    
    public List<Empleado> busquedasensitivaempleado(JTable tabla,String tipoB,String cadena){
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

     
     List<Empleado> listempleado= new ArrayList<>();
    try{
	
//        System.out.println("SELECT * from sp_busquedasensitivaempleado('"+tipoB+"','"+cadena+"')");
        String sql=("SELECT * from sp_busquedasensitivaempleado(?,?)"); 
        PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
       
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[3];
         
        while (rs.next()){
                     
                         
            Empleado empleado = new Empleado();
            
             empleado.setId_empleado(rs.getLong("id"));
             empleado.setNombre(rs.getString("vnombre"));
             empleado.setRut(rs.getString("vrut"));
             empleado.setDireccion(rs.getString("vdireccion"));
             empleado.setCelular(rs.getString("vcelular"));
             datosR[0] = empleado.getRut();

             datosR[1] =empleado.getNombre();

             datosR[2] = empleado.getCelular();
                    
                    
            modelo.addRow(datosR);
            listempleado.add(empleado);
		
        }
        tabla.setModel(modelo);
  
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(90);
        
        if(modelo.getRowCount()>0){
            tabla.setRowSelectionInterval(0, 0);
        
        }
      
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
              Cbd.desconectar();
            }    

    return listempleado;
    }

public Empleado buscarempleado(String tipoB,long id){
//Connection miconexion = conectar.Connect();
//    Statement st=null;
    ConexionBD Cbd = new ConexionBD();
    Empleado empleado= new Empleado();
    
    
//    Runnable miRunnable = new Runnable()
//      {
//         @Override
//         public void run()
//         {
             
    
//     ImageIcon imageIcon =null;
//    ImageIcon imageIcon = new ImageIcon(getClass().getResource("../imagenes/user-not-image.png"));
   
     try{
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedaempleado('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedaempleado(?,?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      
      ResultSet rs= Cbd.RealizarConsulta(ps);
      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
//         msj.setText("Cargando Imagen... ");
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

//                    imageIcon = new ImageIcon(imgBytes);
                      
        }
//        msj.setText("");
//        ImageIcon imageUser = imageIcon;
//        Image img = imageUser.getImage();
//        Image newimg = img.getScaledInstance(jlbl.getWidth(), jlbl.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
//        imageUser = new ImageIcon(newimg);
//        jlbl.setIcon(imageUser);
       
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }


      return empleado;
}


public EmpleadoSingleton cargarempleadosinfoto(String tipoB,long id){
    ConexionBD Cbd = new ConexionBD();
    EmpleadoSingleton empleado= EmpleadoSingleton.getinstancia();

   
     try{

         System.out.println("SELECT * from sp_busquedaempleado('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedaempleado('"+tipoB+"',"+id+")"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
       ResultSet rs = Cbd.RealizarConsulta(ps);
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
      
      
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
return empleado;
}

public void insertarempleado(Empleado empleado){

 ConexionBD Cbd = new ConexionBD();
  byte[] FOTO= empleado.getFoto();
  
    System.out.println("fotoinsert"+FOTO);
   
     try{
           
          
            String insertImageSql = "SELECT * from sp_insertarempleado(?,?,?,?,?,?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            PreparedStatement ps =Cbd.conectar().prepareStatement(insertImageSql);
           

            InputStream fis = new ByteArrayInputStream(FOTO); 
            System.out.println("fisinset"+fis);
            ps.setString(1,empleado.getNombre());
            ps.setString(2,empleado.getRut());
          
            ps.setString(3,empleado.getCelular());
            ps.setString(4,empleado.getDireccion());
            ps.setString(5,empleado.getEmail());
            ps.setString(6,empleado.getCargo());
            ps.setBinaryStream(7,fis);
            
          Cbd.actualizarDatos(ps);
         
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         Cbd.desconectar();
     }

}
public void editarempleado(Empleado empleado){

  byte[] FOTO= empleado.getFoto();
  
   ConexionBD Cbd = new ConexionBD();

     try{
	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_editarempleado(?,?,?,?,?,?,?,?)");         
       PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
       
            ps.setLong(1,empleado.getId_empleado());
            ps.setString(2,empleado.getNombre());
           
            ps.setString(3,empleado.getRut());
            ps.setString(4,empleado.getCelular());
            ps.setString(5,empleado.getDireccion());
            ps.setString(6,empleado.getEmail());
            ps.setString(7,empleado.getCargo());      
            ps.setBinaryStream(8, fis);
            
            Cbd.actualizarDatos(ps);
        
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Cliente editado exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }        
}
public void eliminarempleado(long idempleado){
    ConexionBD Cbd = new ConexionBD();
     Statement st=null;
    ResultSet rs=null;
     try{
	
        System.out.println("SELECT * from sp_eliminarempleado("+idempleado+")");
        
        String sql=("SELECT * from sp_eliminarempleado(?)");       
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idempleado);
        Cbd.actualizarDatos(ps);
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Cliente eliminado exitosamente");
//        }
	
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
      
      String sql=("SELECT * from sp_duplicadoempleado(?,?,?)"); 
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
