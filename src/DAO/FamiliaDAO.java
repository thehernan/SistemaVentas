/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.ConexionBD;
import Pojos.Familia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    
    
public List<Familia> mostrarfamilia(JTable tabla){
         ConexionBD Cbd = new ConexionBD();
       DefaultTableModel modelo= new DefaultTableModel(){
      public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
      };      
     String titulos[]={"DESCRIPCION"};
     modelo.setColumnIdentifiers(titulos);

     
     List<Familia> listfamilia= new ArrayList<>();
    
     
    try{
	     
        String sql=("SELECT * from sp_mostrarfamilia()"); 
        PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[1];
        while (rs.next()){
                   
            Familia familia= new Familia();
            familia.setIdfamilia(rs.getLong("id"));
            familia.setDescripcion(rs.getString("vdescripcion"));
            familia.setObservacion(rs.getString("vobservacion"));
                    
           
             datosR[0] = familia.getDescripcion();
         
//                     datosR[i] = rs.getObject("vobservacion");
//                     i++;

            modelo.addRow(datosR);
            listfamilia.add(familia);
        }
        tabla.setModel(modelo);
       
      

                
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
            }    
    return listfamilia;
    }
    
public List<Familia> busquedasensitivafamilia(String tipoB,String cadena,JTable tabla){
         ConexionBD Cbd = new ConexionBD();
       DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
        };      
       String titulos[]={"DESCRIPCION"};
       modelo.setColumnIdentifiers(titulos);

      
       List<Familia> listfamilia= new ArrayList<>();
     
    try{
      
        String sql=("SELECT * from sp_busquedasensitivafamilia(?,?)"); 
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        ResultSet rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[2];
        while (rs.next()){
           

            Familia familia= new Familia();
            familia.setIdfamilia(rs.getLong("id"));
            familia.setDescripcion(rs.getString("vdescripcion"));
            familia.setObservacion(rs.getString("vobservacion"));
                    
           
             datosR[0] = familia.getDescripcion();
         
//                     datosR[i] = rs.getObject("vobservacion");
//                     i++;


            modelo.addRow(datosR);
            listfamilia.add(familia);
                     
        }
        tabla.setModel(modelo);
        if(modelo.getRowCount()>0){
            tabla.setRowSelectionInterval(0, 0);
        
        }
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

        return listfamilia;
    }

public Familia buscarfamilia(String tipoB,long id){
     ConexionBD Cbd = new ConexionBD();
    Familia familia= new Familia();
   
     try{
         
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedafamilia('"+tipoB+"',"+id+")");
        String sql=("SELECT * from sp_busquedafamilia(?,?)"); 
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setLong(2, id);
        ResultSet rs = Cbd.RealizarConsulta(ps);
  //      FileInputStream fis ;
  //      byte[] vacio= new byte[0];
          if (rs.next()){
               familia.setIdfamilia(rs.getInt("id"));
               familia.setDescripcion(rs.getString("vdescripcion"));
               familia.setObservacion(rs.getString("vobservacion")) ;


          }

      
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
     return familia;

}
public void insertarfamilia(Familia familia){



//  byte[] FOTO= cliente.getImg();
  
//    System.out.println("fotoinsert"+FOTO);
        PreparedStatement ps = null;
         ConexionBD Cbd = new ConexionBD();
       
     try{
            
          
            String insertImageSql = "SELECT * from sp_insertarfamilia(?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            ps= Cbd.conectar().prepareStatement(insertImageSql);
            

            ps.setString(1,familia.getDescripcion());
            ps.setString(2,familia.getObservacion());
         
//            preparedStatement.setString(6,PRIORITARIO);
//            preparedStatement.setBinaryStream(7,fis);
            
            
            
            Cbd.actualizarDatos(ps);
           

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
     
            Cbd.desconectar();
     }

}
public void editarfamilia(Familia familia){

     ConexionBD Cbd = new ConexionBD();
     try{
        
//	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_editarfamilia(?,?,?)");         
       PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
       
            ps.setLong(1,familia.getIdfamilia());
            ps.setString(2,familia.getDescripcion());
            ps.setString(3,familia.getObservacion());
          
//            ps.setString(7,PRIORITARIO);
//            ps.setBinaryStream(8, fis);
            Cbd.actualizarDatos(ps);
        //       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Familia editada exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }   finally{
            Cbd.desconectar();
     }     
            
           
        
    System.out.println("insertar fpac");
 

}
public void eliminarfamilia(long idfamilia){
 
     ConexionBD Cbd = new ConexionBD();
     try{
	
        System.out.println("SELECT * from sp_eliminarfamilia("+idfamilia+")");
        String sql=("SELECT * from sp_eliminarfamilia(?)");
        PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idfamilia);
        Cbd.actualizarDatos(ps);
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Familia eliminada exitosamente");
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
         System.out.println("ID"+id+"cadena"+cadena+"op"+tipoop);
      String sql=("SELECT * from sp_duplicadofamilia(?,?,?)"); 
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

 public List<Familia> llenarcombo(JComboBox combo){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
         ConexionBD Cbd = new ConexionBD();
        List<Familia> listfamilia= new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        
     
    try{
	
      
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrarfamilia()"); 
//        Object datosR[] = new Object[1];
        rs=Cbd.RealizarConsulta(ps);
        modelo.addElement("<<SELECCIONE>>");
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
                Cbd.desconectar();
            }    
    return listfamilia;
    }
    
}
