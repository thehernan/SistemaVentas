/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClasesGlobales.ColorRowTabla;
import ClasesGlobales.FormatoNumerico;

import Conexion.ConexionBD;
import Pojos.Producto;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class ProductoDAO {
   
    FormatoNumerico fn= new FormatoNumerico();
    public List<Producto> mostrarproducto(JTable tabla,long idsucursal) {
         ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
          };      
         String titulos[]={"Codigo","Descripción","Familia"};
         modelo.setColumnIdentifiers(titulos);
//         tabla.setModel(modelo);
          tabla.setModel(modelo);
          
          TableColumnModel columnModel = tabla.getColumnModel();
          System.out.println("councolumn"+columnModel.getColumnCount());
//         if (columnModel.getColumnCount()>0){
       
        columnModel.getColumn(0).setPreferredWidth(120);
        columnModel.getColumn(1).setPreferredWidth(550);
        columnModel.getColumn(2).setPreferredWidth(300);
    
//        }
        
//         Conexion conexion = new Conexion();
      
         ResultSet rs=null;
         PreparedStatement ps=null;
         List<Producto> listprod = new ArrayList<>();



        try{

            
            ps= Cbd.conectar().prepareStatement("SELECT * from sp_mostrarproducto(?)");
            ps.setLong(1, idsucursal);
            rs= Cbd.RealizarConsulta(ps);
            Object datosR[] = new Object[3];
            while (rs.next()){
                Producto prod = new Producto();
                prod.setIdproducto(rs.getLong("id"));
                prod.setCodigo(rs.getString("vcodigo"));
                prod.setDescripcion(rs.getString("vdescripcion"));
                prod.setCantidad(rs.getDouble("vcantidad"));
                 datosR[0] =prod.getCodigo() ;
                 datosR[1] = prod.getDescripcion();
                 datosR[2]=rs.getString("vfamilia");
                modelo.addRow(datosR);
                listprod.add(prod);
                    
            }
            
           
          
             

            } catch(Exception e )
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                     
                      Cbd.desconectar();
                      
 
                      
                } 
        return listprod;

    }
    
    public List<Producto> busquedasensitivaproducto(JTable tabla,String tipoB,String cadena,long idsucur,long idfamilia){
         ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
        };      
    String titulos[]={"Codigo","Descripción","Familia"};
    modelo.setColumnIdentifiers(titulos);
    tabla.setModel(modelo);
    
    TableColumnModel columnModel = tabla.getColumnModel();
//     if (columnModel.getColumnCount()>0){
    columnModel.getColumn(0).setPreferredWidth(120);
    columnModel.getColumn(1).setPreferredWidth(550);
    columnModel.getColumn(2).setPreferredWidth(300);
//     }
   
    ResultSet rs=null;
    PreparedStatement ps=null; 
     List<Producto> listprod = new ArrayList<>();
    try{
	
        String sql=("SELECT * from sp_busquedasensitivaproducto(?,?,?,?)"); 
        ps= Cbd.conectar().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        ps.setLong(3, idsucur);
        ps.setLong(4, idfamilia);
        rs= Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[3];
        while (rs.next()){
             Producto prod = new Producto();
            prod.setIdproducto(rs.getLong("id"));
            prod.setCodigo(rs.getString("vcodigo"));
            prod.setDescripcion(rs.getString("vdescripcion"));
            datosR[0] =prod.getCodigo() ;
            datosR[1] = prod.getDescripcion();
            datosR[2]=rs.getString("vfamilia");
                    
            modelo.addRow(datosR);
            listprod.add(prod);
        }
        
        if(modelo.getRowCount()>0){
            tabla.setRowSelectionInterval(0, 0);
        
        }
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
                
            }    
    return listprod;
    }
 public void llenarcombobox(JComboBox combo,long idsucur){
 
  ConexionBD Cbd = new ConexionBD();
  ResultSet rs=null;  
  PreparedStatement ps=null;  
     
    try{
        
	ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrarproducto(?)");
        ps.setLong(1, idsucur);
         rs=Cbd.RealizarConsulta(ps);
        
       // Object datosR[] = new Object[3];
        while (rs.next()){
                   //  for(int i =0; i<=1; i++){
                         
                   //  listprod.add(rs.getObject("id"));
                 //    listprod.add(rs.getObject("vcodigo"));
                     combo.addItem((rs.getObject("vdescripcion")));
//                     i++;
////                     datosR[i] = rs.getObject("vcelular");
////                     i++;
//                    
//                    modelo.addRow(datosR);
		//}
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
                
            }    

    
    }

public Producto buscarproducto(String tipoB,long id,long idsucur,String cadena) {
//Connection miconexion = conectar.Connect();
//    Statement st=null;
     ConexionBD Cbd = new ConexionBD();
//    ImageIcon imageIcon= new ImageIcon( getClass().getResource("/imagenes/product.png"));
    Producto producto= new Producto();
  
    PreparedStatement ps=null;
    ResultSet rs=null;
    
     try{
//	st= (Statement)miconexion.createStatement();
      System.out.println("SELECT * from sp_busquedaproducto('"+tipoB+"',"+id+",'"+cadena+"')");
      String sql=("SELECT * from sp_busquedaproducto(?,?,?,?)"); 
      ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ps.setLong(3, idsucur);
      ps.setString(4, cadena);
    
      rs = Cbd.RealizarConsulta(ps);
      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
           
         producto.setIdproducto(rs.getLong("id"));
         producto.setCodigo(rs.getString("vcodigo")) ;
         producto.setDescripcion(rs.getString("vdescripcion"));

         producto.setObservacion(rs.getString("vobservacion"));
         producto.setIdfamilia(rs.getLong("vidfamilia"));
         producto.setPrecio(rs.getDouble("vprecio"));
         producto.setPrecio1(rs.getDouble("vprecio1"));
         producto.setPrecio2(rs.getDouble("vprecio2"));
         producto.setPrecio3(rs.getDouble("vprecio3"));
         producto.setMoneda(rs.getString("vmoneda"));
         producto.setCantidad(rs.getDouble("vcantidad"));                 
          byte[] imgBytes = rs.getBytes("vfoto");
         producto.setDescripfamilia(rs.getString("vfamilia"));
//                      System.out.println("byte"+rs.getBytes("vfoto"));
          producto.setMargenG(rs.getDouble("vmargen"));
          producto.setFoto(imgBytes);
          producto.setIdunidm(rs.getLong("vidunid"));
          producto.setUnidadm(rs.getString("vmedida"));
          producto.setUnidabrev(rs.getString("vabreviatura"));
          System.out.println("imgbytes"+imgBytes);
          
          ///////////////
          producto.setUnidmedv(rs.getString("vunidmv"));
          producto.setUnidmedc(rs.getString("vunidmc"));
          producto.setStockmin(rs.getDouble("vstockmin"));
          producto.setStockmax(rs.getDouble("vstockmax"));
          producto.setPrecioc(rs.getDouble("vprecioc"));
          producto.setLocalizacion(rs.getString("vlocalizacion"));
          producto.setFactor(rs.getDouble("vfactor"));
          /////////
          producto.setIdcodsunat(rs.getLong("vidcodsunat"));
          producto.setCodigosunat(rs.getString("vcodsunat"));
                  
          
                      
                      
                      
//                      imageIcon = new ImageIcon(imgBytes);
        }        
//                    ImageIcon imageUser = imageIcon;
//                    Image img = imageUser.getImage();
//                    Image newimg = img.getScaledInstance(jlbl.getWidth(), jlbl.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
//                    imageUser = new ImageIcon(newimg);
//                    jlbl.setIcon(imageUser);       
//	
        } catch(Exception e )
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
//                if(rs!=null){
//                    try {
//                        rs.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                  }
//                if(ps!=null){
//                    try {
//                        ps.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                   }   
            
     }
return producto;
}
public void insertarproducto(Producto producto){
 
     ConexionBD Cbd = new ConexionBD();
    byte[] FOTO= producto.getFoto();  
   
   
    PreparedStatement  ps=null;       
     try{
            
          
            String Sql = "SELECT * from sp_insertarproducto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            ps = Cbd.conectar().prepareStatement(Sql);
            

            InputStream fis = new ByteArrayInputStream(FOTO); 
            System.out.println("fisinset"+fis);
            ps.setString(1,producto.getCodigo());
            ps.setString(2,producto.getDescripcion());
          
            ps.setString(3,producto.getObservacion());
            ps.setLong(4,producto.getIdfamilia());
            ps.setBigDecimal(5,new BigDecimal(producto.getPrecio()));
            ps.setBigDecimal(6,new BigDecimal(producto.getPrecio1()));
            ps.setBigDecimal(7,new BigDecimal(producto.getPrecio2()));
            ps.setBigDecimal(8,new BigDecimal(producto.getPrecio3()));
            ps.setString(9,(producto.getMoneda()));
            ps.setBigDecimal(10,new BigDecimal(producto.getCantidad()));
            ps.setBinaryStream(11,fis);
            ps.setBigDecimal(12, new BigDecimal(producto.getMargenG()));
            ps.setLong(13, producto.getId_sucursal());
            ps.setLong(14, producto.getIdunidm());
            ///// new 
            ps.setString(15,producto.getUnidmedv());
            ps.setString(16,producto.getUnidmedc());
            ps.setBigDecimal(17,new BigDecimal(producto.getStockmin()));
            ps.setBigDecimal(18,new BigDecimal(producto.getStockmax()));
            ps.setBigDecimal(19,new BigDecimal(producto.getPrecioc()));
            ps.setString(20,producto.getLocalizacion());
            ps.setBigDecimal(21,new BigDecimal(producto.getFactor()));
            ps.setLong(22, producto.getIdcodsunat());
                    
           Cbd.actualizarDatos(ps);
           

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally {
               Cbd.desconectar();
                  
//                if(ps!=null){
//                    try {
//                        ps.close();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        }
                           
     }

}
public void editarproducto(Producto producto){

// precio=new BigDecimal(producto.getPrecio());
// cantidad=new BigDecimal(producto.getCantidad());
     ConexionBD Cbd = new ConexionBD();
    byte[] FOTO= producto.getFoto();  
  
       
      

     try{
	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
        String sql=("SELECT * from sp_editarproducto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");         
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        
            ps.setLong(1,producto.getIdproducto());
            ps.setString(2,producto.getCodigo());
           
            ps.setString(3,producto.getDescripcion());
            ps.setString(4,producto.getObservacion());
            ps.setLong(5,producto.getIdfamilia());
//            ps.setBigDecimal(6,precio);
//            ps.setBigDecimal(7,cantidad);      
            ps.setBinaryStream(6, fis);
            ps.setBigDecimal(7, new BigDecimal(producto.getMargenG()));
            ps.setBigDecimal(8, new BigDecimal(producto.getPrecio()));
            ps.setBigDecimal(9, new BigDecimal(producto.getPrecio1()));
            ps.setBigDecimal(10, new BigDecimal(producto.getPrecio2()));
            ps.setBigDecimal(11, new BigDecimal(producto.getPrecio3()));
             ps.setString(12,producto.getMoneda());
             ps.setLong(13, producto.getIdunidm());
             
             //////////////////////////////////////
             
            ps.setString(14,producto.getUnidmedv());
            ps.setString(15,producto.getUnidmedc());
            ps.setBigDecimal(16,new BigDecimal(producto.getStockmin()));
            ps.setBigDecimal(17,new BigDecimal(producto.getStockmax()));
            ps.setBigDecimal(18,new BigDecimal(producto.getPrecioc()));
            ps.setString(19,producto.getLocalizacion());
            ps.setBigDecimal(20,new BigDecimal(producto.getFactor()));
            ps.setLong(21, producto.getIdcodsunat());
            Cbd.actualizarDatos(ps);
           
//       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"Producto editado con exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
     
           Cbd.desconectar();
     }       
            

}
public void eliminarproducto(long idproducto){

     ConexionBD Cbd = new ConexionBD();
     try{
	String sql=("SELECT * from sp_eliminarproducto(?)");
      
         PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
         ps.setLong(1, idproducto);
         Cbd.actualizarDatos(ps);
      
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Producto eliminado exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    
 
    
 

}
public List<Producto> inventario(JTable tabla,long idsucursal,double stockmin,String tipob){
    
    DefaultTableModel modelo= (DefaultTableModel)tabla.getModel();
    
     for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
    }
//         DefaultTableModel modelo= new DefaultTableModel(){
//             
//            public boolean isCellEditable(int row, int column) {
//            //      if (column == 5) return true;
//            //else
//            return false;
//            }
//            };      
//            String titulos[]={"Codigo","Desc. Producto","Precio","Precio1","Precio2","Precio3","Stock","Familia","Sucursal"};
//            modelo.setColumnIdentifiers(titulos);
            
        
        
      
      
////        tabla.setDefaultRenderer (Object.class, colorrow );
//        tabla.requestFocus();
//          tabla.setModel(modelo);
        
         TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(450);
//        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
        columnModel.getColumn(9).setPreferredWidth(50);
        columnModel.getColumn(10).setPreferredWidth(80);
        columnModel.getColumn(11).setPreferredWidth(50);
        columnModel.getColumn(12).setPreferredWidth(100);
        columnModel.getColumn(13).setPreferredWidth(300);
        columnModel.getColumn(14).setPreferredWidth(350);
        List<Producto> listprod= new ArrayList<>();
          ConexionBD Cbd = new ConexionBD();
         ResultSet rs=null;
         
        
         try{
                         
             String sql=("SELECT * from sp_mostrarinventario(?,?)");
             PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
             ps.setLong(1, idsucursal);
             ps.setString(2, tipob);
             rs = Cbd.RealizarConsulta(ps);
             Object datosR[]= new Object[15];
             
             while (rs.next()){
                 
                    Producto prod = new Producto();
                    prod.setIdproducto(rs.getLong("vid"));
                    prod.setCodigo(rs.getString("vcodigo"));
                    prod.setDescripcion( rs.getString("vdescripcion"));
                    prod.setPrecio(rs.getDouble("vprecio"));
                    prod.setPrecio1(rs.getDouble("vprecio1"));
                    prod.setPrecio2(rs.getDouble("vprecio2"));
                    prod.setPrecio3(rs.getDouble("vprecio3"));
                    prod.setMoneda(rs.getString("vmoneda"));
                    prod.setCantidad(rs.getDouble("vcantidad"));
                    prod.setUnidadm(rs.getString("vmedida"));
                    prod.setUnidabrev(rs.getString("vabrev"));
                    prod.setUnidmedv(rs.getString("vunidmv"));
                    prod.setUnidmedc(rs.getString("vunidmc"));
                    prod.setStockmin(rs.getDouble("vstockmin"));
                    prod.setStockmax(rs.getDouble("vstockmax"));
                    prod.setPrecioc(rs.getDouble("vprecioc"));
                    prod.setLocalizacion(rs.getString("vlocalizacion"));
                    prod.setFactor(rs.getDouble("vfactor"));
                    
                     datosR[0] = prod.getCodigo();
                                
                     datosR[1] = prod.getDescripcion();
//                     datosR[2] =prod.getMoneda();
                     datosR[2] =fn.FormatoN(prod.getPrecio());
                     datosR[3] =fn.FormatoN(prod.getPrecio1());
                     datosR[4] =fn.FormatoN(prod.getPrecio2());
                     datosR[5] =fn.FormatoN(prod.getPrecio3());
                     datosR[6] =fn.FormatoN(prod.getStockmin());
                     datosR[7] =fn.FormatoN(prod.getStockmax());
                     datosR[8] =fn.FormatoN(prod.getCantidad());
                     datosR[9] =prod.getUnidmedc();
                     datosR[10] =fn.FormatoN(rs.getDouble("vcantidadv"));
                     datosR[11] =prod.getUnidmedv();
                     datosR[12] =prod.getLocalizacion();
                     datosR[13] = rs.getObject("vfamilia");
                     datosR[14] = rs.getObject("vsucusal");
                    
                    
                    modelo.addRow(datosR);
                    ColorRowTabla colorrow= new ColorRowTabla(8, 6,7);
                    tabla.setDefaultRenderer (Object.class, colorrow );
                    
                    listprod.add(prod);
             }
           
            

           //  tabla.changeSelection(0,0,false,true);
            if (tabla.getRowCount()>0){
                tabla.setRowSelectionInterval (0,0); 
               
            }
             
             //tabla.requestFocus();
       
             
            
            
             
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             Cbd.desconectar();
         }
         return listprod;
 
}
 
public List<Producto> busquedasensitivainventario(JTable tabla,String tipob,String cadena,long idsucur,long idfamilia,double stockmin){
         ConexionBD Cbd = new ConexionBD();
         
    DefaultTableModel modelo= (DefaultTableModel)tabla.getModel();
    
     for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
    }
         
//        DefaultTableModel    modelo= new DefaultTableModel(){
//        public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
//        return false;
//        }
//        };      
//        String titulos[]={"Codigo","Desc. Producto","Precio","Precio1","Precio2","Precio3","Stock","Familia","Sucursal"};
//        modelo.setColumnIdentifiers(titulos);
//        tabla.setModel(modelo);
        
        
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(450);
//        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(80);
        columnModel.getColumn(8).setPreferredWidth(80);
        columnModel.getColumn(9).setPreferredWidth(50);
        columnModel.getColumn(10).setPreferredWidth(80);
        columnModel.getColumn(11).setPreferredWidth(50);
        columnModel.getColumn(12).setPreferredWidth(100);
        columnModel.getColumn(13).setPreferredWidth(300);
        columnModel.getColumn(14).setPreferredWidth(350);
            System.out.print(stockmin);
        
//        tabla.setDefaultRenderer (Object.class, colorrow );
        
        
        List<Producto> listprod = new ArrayList<>();
        
        
         
         try{
             
             System.out.println("SELECT * from sp_busquedasensitivainventario('"+tipob+"','"+cadena+"')");
             String sql=("SELECT * from sp_busquedasensitivainventario(?,?,?,?)");
             PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
             ps.setString(1, tipob);
             ps.setString(2, cadena);
             ps.setLong(3, idsucur);
             ps.setLong(4, idfamilia);
             ResultSet rs = Cbd.RealizarConsulta(ps);
             Object datosR[]= new Object[15];
             
             while (rs.next()){
                 
                 Producto prod = new Producto();
                    prod.setIdproducto(rs.getLong("vid"));
                    prod.setCodigo(rs.getString("vcodigo"));
                    prod.setDescripcion( rs.getString("vdescripcion"));
                    prod.setPrecio(rs.getDouble("vprecio"));
                    prod.setPrecio1(rs.getDouble("vprecio1"));
                    prod.setPrecio2(rs.getDouble("vprecio2"));
                    prod.setPrecio3(rs.getDouble("vprecio3"));
                    prod.setMoneda(rs.getString("vmoneda"));
                    prod.setCantidad(rs.getDouble("vcantidad"));
                    prod.setUnidadm(rs.getString("vmedida"));
                    prod.setUnidabrev(rs.getString("vabrev"));
                    prod.setUnidmedv(rs.getString("vunidmv"));
                    prod.setUnidmedc(rs.getString("vunidmc"));
                    prod.setStockmin(rs.getDouble("vstockmin"));
                    prod.setStockmax(rs.getDouble("vstockmax"));
                    prod.setPrecioc(rs.getDouble("vprecioc"));
                    prod.setLocalizacion(rs.getString("vlocalizacion"));
                    prod.setFactor(rs.getDouble("vfactor"));
                    
                     datosR[0] = prod.getCodigo();
                                
                     datosR[1] = prod.getDescripcion();
//                     datosR[2] =prod.getMoneda();
                     datosR[2] =fn.FormatoN(prod.getPrecio());
                     datosR[3] =fn.FormatoN(prod.getPrecio1());
                     datosR[4] =fn.FormatoN(prod.getPrecio2());
                     datosR[5] =fn.FormatoN(prod.getPrecio3());
                     datosR[6] =fn.FormatoN(prod.getStockmin());
                     datosR[7] =fn.FormatoN(prod.getStockmax());
                     datosR[8] =fn.FormatoN(prod.getCantidad());
                     datosR[9] =prod.getUnidmedc();
                     datosR[10] =fn.FormatoN(rs.getDouble("vcantidadv"));
                     datosR[11] =prod.getUnidmedv();
                     datosR[12] =prod.getLocalizacion();
                     datosR[13] = rs.getObject("vfamilia");
                     datosR[14] = rs.getObject("vsucursal");
                    
                     
                    modelo.addRow(datosR);
                     ColorRowTabla colorrow= new ColorRowTabla(8, 6,7);
                    tabla.setDefaultRenderer (Object.class, colorrow );
                    
                    listprod.add(prod);
             }
             
//            ColorRowTabla colorrow= new ColorRowTabla(6, stockmin);
//            tabla.setDefaultRenderer (Object.class, colorrow );
             
             if(tabla.getRowCount()>0){
              tabla.setRowSelectionInterval (0,0); 
            
             }
             
            // tabla.requestFocus();
             
          
             
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             Cbd.desconectar();
             
         }
         return listprod;
 
}
public void generarcodigo(JTextField codigo){
 
     ConexionBD Cbd = new ConexionBD();
   
     try{
	
        System.out.println("SELECT * from sp_generarcodigoprod()");
        String sql=("SELECT * from sp_generarcodigoprod()");       
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ResultSet rs= Cbd.RealizarConsulta(ps);
       if  (rs.next()){
            codigo.setText(rs.getString("codigo"));
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
            }    
 
    
 

}
public void imprimircodigo(long id){
     ConexionBD Cbd = new ConexionBD();
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
          
                      
            String  rutaInforme  = "src/Reportes/GenerarCodigoProducto.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("id",  id);

            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            
            // no vista previa 
//            JasperPrintManager.printReport(informe, true);    
            
            ///////////////////
            
            //vista previa ////
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          //////////////////////////////////////
             
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
        Cbd.desconectar();
}



     

}
public void imprimirinventario(long idsucur){
   ConexionBD Cbd = new ConexionBD();

 try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Inventario.jasper";
            
            Map parametros = new HashMap();
            java.util.Locale locale = new Locale( "en", "US" );
            parametros.put( JRParameter.REPORT_LOCALE, locale );
           
             parametros.put("idsucur",  idsucur);
             
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
        Cbd.desconectar();
           
}





}
public void imprimirinventariostockmin(long idsucur,double stockmin){
     ConexionBD Cbd = new ConexionBD();
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/InventarioStockmin.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucur",  idsucur);
            parametros.put("stockmin",  stockmin);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
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
      
      String sql=("SELECT * from sp_duplicadoproducto(?,?,?)"); 
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
public boolean validastockrequerido(long idprod,double cantidad){
      ConexionBD Cbd = new ConexionBD();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_validastockrequerido(?,?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setLong(1,idprod);
      ps.setBigDecimal(2,new BigDecimal(cantidad));
      rs = Cbd.RealizarConsulta(ps);
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
            valida= rs.getBoolean(1);
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            Cbd.desconectar();
     
     }
     return valida;
    


}
public void devolverstockrequerido(List<Producto> listdet,JLabel mens,JInternalFrame frame){
    
//    ResultSet rs=null;
       
      ConexionBD Cbd = new ConexionBD();
     
      Runnable miRunnable;
        miRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                
                PreparedStatement ps = null;
                try
                {
//               System.out.println("Me han pulsado");
//               Thread.sleep(10000); //Tarea que consume diez segundos.
//               System.out.println("Terminé");
                    
                    int i=1;
                    mens.setVisible(true);
                    mens.setText("Iniciando proceso ...");
//               mens.append(System.getProperty("line.separator"));
                
//                if(listdet.size()>0){
                    System.out.println("listdetthread"+listdet.size());
                    for(Producto det: listdet){
                        mens.setText("Retornado Producto Cod. "+det.getCodigo());
//                    mens.setText(System.getProperty("line.separator"));
                        String sql=("SELECT * from sp_devolverstockrequerido(?,?)");
                        ps=Cbd.conectar().prepareStatement(sql);
                        ps.setBigDecimal(1,new BigDecimal(det.getCantidad()));
                        ps.setLong(2, det.getIdproducto());
                        
                        Cbd.actualizarDatos(ps);
//                    mens.setCaretPosition(mens.getDocument().getLength());
//                        i++;
                    }
                    
                    
                    
                    
//                }
                    
                    
                   
                    mens.setText("Extornado con exito ");
//                    mens.append(System.getProperty("line.separator"));
                    mens.setText("Saliendo.... ");
//                    mens.append(System.getProperty("line.separator"));
                    Thread.sleep(500);
                    frame.dispose();
//                    mens.setCaretPosition(mens.getDocument().getLength());
                   
                    
                    
                }
                catch (SQLException e)
                {
                } catch (InterruptedException ex) {
                    Logger.getLogger(DetalleOrdenSalidaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    Cbd.desconectar();
                           
                }
            }
        };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
    
  
}

public void devolver(Producto prod,JLabel mens){
   
//    ResultSet rs=null;
       
     
      ConexionBD Cbd = new ConexionBD();
      Runnable miRunnable;
        miRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                
                PreparedStatement ps = null;
                try
                {
//               System.out.println("Me han pulsado");
//               Thread.sleep(10000); //Tarea que consume diez segundos.
//               System.out.println("Terminé");
                    
                    int i=1;
                    mens.setVisible(true);
                    mens.setText("Iniciando proceso ...");
//               mens.append(System.getProperty("line.separator"));
                
//                if(listdet.size()>0){
                
                  
                        mens.setText("Retornado Producto Cod. "+prod.getCodigo());
//                    mens.setText(System.getProperty("line.separator"));
                        System.out.println("idprod"+prod.getIdproducto());
                        System.err.println("cant"+prod.getCantidad());
                        String sql=("SELECT * from sp_devolverstockrequerido(?,?)");
                        ps=Cbd.conectar().prepareStatement(sql);
                        ps.setBigDecimal(1,new BigDecimal(prod.getCantidad()));
                        ps.setLong(2, prod.getIdproducto());
                        
                        Cbd.actualizarDatos(ps);
                                
//                    mens.setCaretPosition(mens.getDocument().getLength());
//                        i++;
                    
                    
                    
                    
                    
//                }
                    
                    
//                    if(ps!=null){
//                      ps.close();
//                    }
                   
                    mens.setText("Extornado con exito ");
//                    mens.append(System.getProperty("line.separator"));
//                    mens.setText("Saliendo.... ");
//                    mens.append(System.getProperty("line.separator"));
                    Thread.sleep(500);
                    mens.setVisible(false);
//                    mens.setCaretPosition(mens.getDocument().getLength());
                   
                    
                    
                }
                catch (SQLException e)
                {
                } catch (InterruptedException ex) {
                    Logger.getLogger(DetalleOrdenSalidaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    Cbd.desconectar();
                }
            }
        };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
    
  
}



public void actulizarprecioporfamilia(double precio,double precio1,double precio2,double precio3,long idfamilia){

// precio=new BigDecimal(producto.getPrecio());
// cantidad=new BigDecimal(producto.getCantidad());
     ConexionBD Cbd = new ConexionBD();
     int filas=0;
     try{
	
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
        String sql=("SELECT * from sp_actulizarprecioporfamilia(?,?,?,?,?)");         
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        
            ps.setLong(1,idfamilia);
                   
            ps.setBigDecimal(2, new BigDecimal(precio));
            ps.setBigDecimal(3, new BigDecimal(precio1));
            ps.setBigDecimal(4, new BigDecimal(precio2));
            ps.setBigDecimal(5, new BigDecimal(precio3));
     
            if(Cbd.actualizarDatos(ps)==true)
            {
                JOptionPane.showMessageDialog(null,"Actualizado con exito","",JOptionPane.INFORMATION_MESSAGE);
            }
           
//       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"Producto editado con exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
     
           Cbd.desconectar();
     }       
            
   
}

public boolean ajustarexistencia(long id, double cantidad){
     ConexionBD Cbd = new ConexionBD();
     boolean val=false;
     try{
	
     
        String sql=("UPDATE producto SET cantidad=? WHERE id_producto=?;");         
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
         
        ps.setDouble(1, (cantidad));
        ps.setLong(2,id);
      
        val =Cbd.update(ps);
       
//            if(Cbd.actualizarDatos(ps)==true)
//            {
//                 JOptionPane.showMessageDialog(null,"Exisistencias ajustadas con éxito","",JOptionPane.INFORMATION_MESSAGE);
//            }
           
//       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"Producto editado con exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
     
           Cbd.desconectar();
     }       





return val;

}


public boolean actualizaprecios(Producto prod){
     ConexionBD Cbd = new ConexionBD();
     boolean val=false;
     try{
	
     
        String sql=("UPDATE producto SET precio=?,precio1=?,precio2=?,precio3=?,preciocompra=? WHERE id_producto=?;");         
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
         
        ps.setDouble(1, prod.getPrecio());
        ps.setDouble(1, prod.getPrecio1());
        ps.setDouble(1, prod.getPrecio2());
        ps.setDouble(1, prod.getPrecio3());
        ps.setDouble(1, prod.getPrecioc());
        ps.setLong(2,prod.getIdproducto());
      
        val =Cbd.update(ps);
       
//            if(Cbd.actualizarDatos(ps)==true)
//            {
//                 JOptionPane.showMessageDialog(null,"Exisistencias ajustadas con éxito","",JOptionPane.INFORMATION_MESSAGE);
//            }
           
//       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"Producto editado con exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
     
           Cbd.desconectar();
     }       





return val;

}


public boolean actualizapreciocompra(double precioc , long id){
     ConexionBD Cbd = new ConexionBD();
     boolean val=false;
     try{
	
     
        String sql=("UPDATE producto SET preciocompra=? WHERE id_producto=?;");         
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
         
        ps.setDouble(1, precioc);
 
        ps.setLong(2,id);
      
        val =Cbd.update(ps);
       
//            if(Cbd.actualizarDatos(ps)==true)
//            {
//                 JOptionPane.showMessageDialog(null,"Exisistencias ajustadas con éxito","",JOptionPane.INFORMATION_MESSAGE);
//            }
           
//       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"Producto editado con exitosamente");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
     
           Cbd.desconectar();
     }       





return val;

}
    


    
    
}
