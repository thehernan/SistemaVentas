/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.Familia;
import Pojos.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class FamiliaDAO {
    
public void mostrarfamilia(JTable tabla){
        
       DefaultTableModel modelo= new DefaultTableModel(){
  public boolean isCellEditable(int row, int column) {
//      if (column == 5) return true;
//else
 return false;
}
  };      
 String titulos[]={"ID","DESCRIPCION"};
 modelo.setColumnIdentifiers(titulos);
 
 Conexion conexion = new Conexion();

    
     
    try{
	     
        String sql=("SELECT * from sp_mostrarfamilia()"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[3];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vdescripcion");
                     i++;
//                     datosR[i] = rs.getObject("vobservacion");
//                     i++;
                     
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
    
    public void busquedasensitivafamilia(String tipoB,String cadena,JTable tabla){
        
       DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
    };      
   String titulos[]={"ID","DESCRIPCION"};
   modelo.setColumnIdentifiers(titulos);

   Conexion conexion = new Conexion();
  
     
    try{
      
        String sql=("SELECT * from sp_busquedasensitivafamilia(?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[2];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vdescripcion");
                     i++;
//                     datosR[i] = rs.getObject("vobservacion");
//                     i++;
                    
                    
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

public Familia buscarfamilia(String tipoB,long id){

    Familia familia= new Familia();
    Conexion conexion = new Conexion();
     try{
         
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedafamilia('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedafamilia(?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ResultSet rs = ps.executeQuery();
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
                     familia.setIdfamilia(rs.getInt("id"));
                     familia.setDescripcion(rs.getString("vdescripcion"));
                     familia.setObservacion(rs.getString("vobservacion")) ;
                
        
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
     }
     return familia;

}
public void insertarfamilia(Familia familia){



//  byte[] FOTO= cliente.getImg();
  
//    System.out.println("fotoinsert"+FOTO);
        PreparedStatement preparedStatement = null;
        Conexion conexion= new Conexion();
       
     try{
            
          
            String insertImageSql = "SELECT * from sp_insertarfamilia(?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            preparedStatement = conexion.getConnection().prepareStatement(insertImageSql);
            
////            System.out.println("rutainsert"+ruta);
////            File file = new File(ruta);
////            FileInputStream fis = new FileInputStream(file);
//            InputStream fis = new ByteArrayInputStream(FOTO); 
//            System.out.println("fisinset"+fis);
            preparedStatement.setString(1,familia.getDescripcion());
            preparedStatement.setString(2,familia.getObservacion());
         
//            preparedStatement.setString(6,PRIORITARIO);
//            preparedStatement.setBinaryStream(7,fis);
            
            
            
            preparedStatement.execute();
            preparedStatement.close();

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
     
            conexion.devolverConexionPool();
     }

}
public void editarfamilia(Familia familia){

  
  Conexion conexion = new Conexion();

     try{
        
//	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_editarfamilia(?,?,?)");         
       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       
            ps.setLong(1,familia.getIdfamilia());
             ps.setString(2,familia.getDescripcion());
            ps.setString(3,familia.getObservacion());
          
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
            }   finally{
            conexion.devolverConexionPool();
     }     
            
           
        
    System.out.println("insertar fpac");
 

}
public void eliminarfamilia(long idfamilia){
 
    Conexion conexion = new Conexion();

     try{
	
        System.out.println("SELECT * from sp_eliminarfamilia("+idfamilia+")");
        String sql=("SELECT * from sp_eliminarfamilia(?)");
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idfamilia);
        ResultSet rs= ps.executeQuery();
       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
       rs.close();
       ps.close();
	
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
         System.out.println("ID"+id+"cadena"+cadena+"op"+tipoop);
      String sql=("SELECT * from sp_duplicadofamilia(?,?,?)"); 
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

 public List<Familia> llenarcombo(JComboBox combo){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        Conexion conexion = new Conexion();
        List<Familia> listfamilia= new ArrayList<>();
        Statement st=null;
        ResultSet rs=null;
        
     
    try{
	st= conexion.getConnection().createStatement();
      
        rs=st.executeQuery("SELECT * from sp_mostrarfamilia()"); 
//        Object datosR[] = new Object[1];
        modelo.addElement("Todo");
        listfamilia.add(new Familia());
        while (rs.next()){
            Familia familia = new Familia();
            familia.setIdfamilia(rs.getLong("id"));
            familia.setDescripcion(rs.getString("vdescripcion"));

            modelo.addElement(familia.getDescripcion());
            listfamilia.add(familia);
        }
        combo.setModel(modelo);
	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
    return listfamilia;
    }
    
}
