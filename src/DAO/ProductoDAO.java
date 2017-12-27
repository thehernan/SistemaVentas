/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClasesGlobales.ColorRowTabla;
import Conexion.Conexion;
import Formularios.JIFOrdenSalida;
import Pojos.DetalleCaja;
import Pojos.DetalleOrdeSalidaEntrada;
import Pojos.Producto;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class ProductoDAO {
    
    public List<Producto> mostrarproducto(JTable tabla,long idsucursal) {
        
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
          };      
         String titulos[]={"CODIGO","DESCRIPCION","FAMILIA"};
         modelo.setColumnIdentifiers(titulos);
//         tabla.setModel(modelo);
         Conexion conexion = new Conexion();
         ResultSet rs=null;
         PreparedStatement ps=null;
         List<Producto> listprod = new ArrayList<>();



        try{

            String sql=("SELECT * from sp_mostrarproducto(?)");
            ps= conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, idsucursal);
            rs= ps.executeQuery();
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
            
            tabla.setModel(modelo);
            
             rs.close();
             ps.close();

            } catch(Exception e )
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                     
                      conexion.devolverConexionPool();
                      
 
                      
                } 
        return listprod;

    }
    
    public List<Producto> busquedasensitivaproducto(JTable tabla,String tipoB,String cadena,long idsucur,long idfamilia){
        
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
        };      
    String titulos[]={"CODIGO","DESCRIPCION","FAMILIA"};
    modelo.setColumnIdentifiers(titulos);
    tabla.setModel(modelo);
  
    Conexion conexion = new Conexion();
    ResultSet rs=null;
    PreparedStatement ps=null; 
     List<Producto> listprod = new ArrayList<>();
    try{
	
        String sql=("SELECT * from sp_busquedasensitivaproducto(?,?,?,?)"); 
        ps= conexion.getConnection().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        ps.setLong(3, idsucur);
        ps.setLong(4, idfamilia);
        rs= ps.executeQuery();
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
        tabla.setModel(modelo);

        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                 if(rs!=null){
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      }
                      if(ps!=null){
                        try {
                            ps.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }   
            }    
    return listprod;
    }
 public void llenarcombobox(JComboBox combo,long idsucur){
 
  Conexion conexion = new Conexion();
  ResultSet rs=null;  
  PreparedStatement ps=null;  
     
    try{
        
	ps=conexion.getConnection().prepareStatement("SELECT * from sp_mostrarproducto(?)");
        ps.setLong(1, idsucur);
         rs=ps.executeQuery();
        
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
                conexion.devolverConexionPool();
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      }
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }   
            }    

    
    }

public Producto buscarproducto(String tipoB,long id,long idsucur,JLabel jlbl,String cadena){
//Connection miconexion = conectar.Connect();
//    Statement st=null;
     ImageIcon imageIcon= new ImageIcon( getClass().getResource("/imagenes/product.png"));
    Producto producto= new Producto();
    Conexion conexion = new Conexion();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
     try{
//	st= (Statement)miconexion.createStatement();
        System.out.println("SELECT * from sp_busquedaproducto('"+tipoB+"',"+id+",'"+cadena+"')");
      String sql=("SELECT * from sp_busquedaproducto(?,?,?,?)"); 
      ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ps.setLong(3, idsucur);
      ps.setString(4, cadena);
    
      rs = ps.executeQuery();
      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
           
                     producto.setIdproducto(rs.getLong("id"));
                     producto.setCodigo(rs.getString("vcodigo")) ;
                     producto.setDescripcion(rs.getString("vdescripcion"));
                    
                     producto.setObservacion(rs.getString("vobservacion"));
                     producto.setIdfamilia(rs.getLong("vidfamilia"));
                     producto.setPrecio(rs.getDouble("vprecio"));
                     producto.setCantidad(rs.getDouble("vcantidad"));                 
                      byte[] imgBytes = rs.getBytes("vfoto");
                     producto.setDescripfamilia(rs.getString("vfamilia"));
//                      System.out.println("byte"+rs.getBytes("vfoto"));
                      producto.setMargenG(rs.getDouble("vmargen"));
                      producto.setFoto(imgBytes);
                      System.out.println("imgbytes"+imgBytes);
                      imageIcon = new ImageIcon(imgBytes);
        }        
                    ImageIcon imageUser = imageIcon;
                    Image img = imageUser.getImage();
                    Image newimg = img.getScaledInstance(jlbl.getWidth(), jlbl.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
                    imageUser = new ImageIcon(newimg);
                    jlbl.setIcon(imageUser);       
//	
        } catch(Exception e )
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                if(rs!=null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  }
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }   
            
     }
return producto;
}
public void insertarproducto(Producto producto){
 
 
    byte[] FOTO= producto.getFoto();  
   
    Conexion conexion= new Conexion();
    PreparedStatement  ps=null;       
     try{
            
          
            String Sql = "SELECT * from sp_insertarproducto(?,?,?,?,?,?,?,?,?)";

            ps = conexion.getConnection().prepareStatement(Sql);
            

            InputStream fis = new ByteArrayInputStream(FOTO); 
            System.out.println("fisinset"+fis);
            ps.setString(1,producto.getCodigo());
            ps.setString(2,producto.getDescripcion());
          
            ps.setString(3,producto.getObservacion());
            ps.setLong(4,producto.getIdfamilia());
            ps.setBigDecimal(5,new BigDecimal(producto.getPrecio()));
            ps.setBigDecimal(6,new BigDecimal(producto.getCantidad()));
            ps.setBinaryStream(7,fis);
            ps.setBigDecimal(8, new BigDecimal(producto.getMargenG()));
            ps.setLong(9, producto.getId_sucursal());
            
            
            
            ps.execute();
           

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally {
                conexion.devolverConexionPool();
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                           
     }

}
public void editarproducto(Producto producto){

// precio=new BigDecimal(producto.getPrecio());
// cantidad=new BigDecimal(producto.getCantidad());
 byte[] FOTO= producto.getFoto();  
  
       
        Conexion conexion = new Conexion();
 

     try{
	InputStream fis = new ByteArrayInputStream(FOTO); 
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
        String sql=("SELECT * from sp_editarproducto(?,?,?,?,?,?,?,?)");         
        PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
        
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
            ps.execute();
            ps.close();
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
     
            conexion.devolverConexionPool();
     }       
            

}
public void eliminarproducto(long idproducto){

    Conexion conexion = new Conexion();
     try{
	String sql=("SELECT * from sp_eliminarproducto(?)");
      
         PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
         ps.setLong(1, idproducto);
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
public List<Producto> inventario(JTable tabla,long idsucursal,double stockmin,String tipob){
         DefaultTableModel modelo= new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
            //      if (column == 5) return true;
            //else
            return false;
            }
            };      
            String titulos[]={"CODIGO","DESCRIPCION PROD.","PRECIO VENTA","INVENTARIO","FAMILIA","SUCURSAL"};
            modelo.setColumnIdentifiers(titulos);
            tabla.setModel(modelo);
        
        
      
        ColorRowTabla colorrow= new ColorRowTabla(3, stockmin);
        tabla.setDefaultRenderer (Object.class, colorrow );
        tabla.setDefaultRenderer (Object.class, colorrow );
//        tabla.requestFocus();
        List<Producto> listprod= new ArrayList<>();
         Conexion conexion = new Conexion();
         ResultSet rs=null;
         NumberFormat nf =NumberFormat.getInstance();
         try{
                         
             String sql=("SELECT * from sp_mostrarinventario(?,?)");
             PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
             ps.setLong(1, idsucursal);
             ps.setString(2, tipob);
             rs = ps.executeQuery();
             Object datosR[]= new Object[6];
             
             while (rs.next()){
                 
                    Producto prod = new Producto();
                    prod.setIdproducto(rs.getLong("vid"));
                    prod.setCodigo(rs.getString("vcodigo"));
                    prod.setDescripcion( rs.getString("vdescripcion"));
                    prod.setPrecio(rs.getDouble("vprecio"));
                    prod.setCantidad(rs.getDouble("vcantidad"));
                   
                     datosR[0] = prod.getCodigo();
                                
                     datosR[1] = prod.getDescripcion();
                   
                     datosR[2] =nf.format( prod.getPrecio());
                  
                     datosR[3] = nf.format(prod.getCantidad());
                     datosR[4] = rs.getObject("vfamilia");
                     datosR[5] = rs.getObject("vsucusal");
                 
                    modelo.addRow(datosR);
		listprod.add(prod);
             }

           //  tabla.changeSelection(0,0,false,true);
            if (tabla.getRowCount()>0){
                tabla.setRowSelectionInterval (0,0); 
                tabla.setRowSelectionInterval(0, 0);
            }
             
             //tabla.requestFocus();
       
             
             ps.close();
             rs.close(); 
             conexion.devolverConexionPool();
             
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{

         }
         return listprod;
 
}
 
public List<Producto> busquedasensitivainventario(JTable tabla,String tipob,String cadena,long idsucur,long idfamilia){
    
       DefaultTableModel    modelo= new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
            //      if (column == 5) return true;
            //else
            return false;
            }
            };      
            String titulos[]={"CODIGO","DESCRIPCION PROD.","PRECIO VENTA","INVENTARIO","FAMILIA","SUCURSAL"};
            modelo.setColumnIdentifiers(titulos);
            tabla.setModel(modelo);
        
        
        List<Producto> listprod = new ArrayList<>();
         Conexion conexion = new Conexion();
        NumberFormat nf = NumberFormat.getInstance();
         
         try{
             
             System.out.println("SELECT * from sp_busquedasensitivainventario('"+tipob+"','"+cadena+"')");
             String sql=("SELECT * from sp_busquedasensitivainventario(?,?,?,?)");
             PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
             ps.setString(1, tipob);
             ps.setString(2, cadena);
             ps.setLong(3, idsucur);
             ps.setLong(4, idfamilia);
             ResultSet rs = ps.executeQuery();
             Object datosR[]= new Object[6];
             
             while (rs.next()){
                 
                 Producto prod = new Producto();
                    prod.setIdproducto(rs.getLong("vid"));
                    prod.setCodigo(rs.getString("vcodigo"));
                    prod.setDescripcion( rs.getString("vdescripcion"));
                    prod.setPrecio(rs.getDouble("vprecio"));
                    prod.setCantidad(rs.getDouble("vcantidad"));
                   
                     datosR[0] = prod.getCodigo();
                                
                     datosR[1] = prod.getDescripcion();
                   
                     datosR[2] = nf.format(prod.getPrecio());
                  
                     datosR[3] = nf.format(prod.getCantidad());
                     datosR[4] = rs.getObject("vfamilia");
                     datosR[5] = rs.getObject("vsucursal");
                 
                    modelo.addRow(datosR);
                    listprod.add(prod);
             }
             if(tabla.getRowCount()>0){
              tabla.setRowSelectionInterval (0,0); 
             tabla.setRowSelectionInterval(0, 0);
             }
             
            // tabla.requestFocus();
             
             ps.close();
             rs.close(); 
             conexion.devolverConexionPool();
             
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             
             
         }
         return listprod;
 
}
public void generarcodigo(JTextField codigo){
 
 
    Conexion conexion = new Conexion();
   
     try{
	
        System.out.println("SELECT * from sp_generarcodigoprod()");
        String sql=("SELECT * from sp_generarcodigoprod()");       
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ResultSet rs= ps.executeQuery();
       if  (rs.next()){
            codigo.setText(rs.getString("codigo"));
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               conexion.devolverConexionPool();
            }    
 
    
 

}
public void imprimircodigo(long id){
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
            Conexion conexion = new Conexion();
                      
            String  rutaInforme  = "src/Reportes/GenerarCodigoProducto.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("id",  id);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperPrintManager.printReport(informe, true);    
//            JasperViewer jv = new JasperViewer(informe,false);  
//        
//             jv.setVisible(true);
//             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     

}
public void imprimirinventario(long idsucur){
     Conexion conexion = new Conexion();
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Inventario.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucur",  idsucur);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
    conexion.devolverConexionPool();
}
}
public void imprimirinventariostockmin(long idsucur,double stockmin){
     Conexion conexion = new Conexion();
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
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
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
      
      String sql=("SELECT * from sp_duplicadoproducto(?,?,?)"); 
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
public boolean validastockrequerido(Double cant, Long idprod){
     Conexion conexion = new Conexion();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_validastockrequerido(?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setLong(1, idprod);
      ps.setBigDecimal(2,new BigDecimal(cant));
      rs = ps.executeQuery();
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
            conexion.devolverConexionPool();
     
     }
     return valida;
    


}
public void devolverstockrequerido(List<DetalleOrdeSalidaEntrada> listdet,JTextArea mens,JIFOrdenSalida jifsalida){
     Conexion conexion = new Conexion();
    ResultSet rs=null;
    PreparedStatement ps=null;  
     
     
      Runnable miRunnable = new Runnable()
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
               mens.append("Iniciando proceso ...");
               mens.append(System.getProperty("line.separator"));
               
                if(listdet.size()>0){
                
                for(DetalleOrdeSalidaEntrada det: listdet){
                    mens.append("Retornado del producto "+i);
                    mens.append(System.getProperty("line.separator"));
                    String sql=("SELECT * from sp_devolverstockrequerido(?,?)"); 
                    ps=conexion.getConnection().prepareStatement(sql);
                    ps.setBigDecimal(1,new BigDecimal(det.getCantidad()));
                    ps.setLong(2, det.getIdproducto());

                    ps.executeQuery();
                    mens.setCaretPosition(mens.getDocument().getLength());
                    i++;
                    }
             
                
                    ps.close();
                }
                
                   
                   
                   
                    mens.append("Extornado con exito ");
                    mens.append(System.getProperty("line.separator"));
                     mens.append("Saliendo.... ");
                    mens.append(System.getProperty("line.separator"));
                    Thread.sleep(500);
                    jifsalida.dispose();
                    mens.setCaretPosition(mens.getDocument().getLength());
               
               
               
            }
            catch (SQLException e)
            {
            } catch (InterruptedException ex) {
                 Logger.getLogger(DetalleOrdenSalidaDAO.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
            conexion.devolverConexionPool();
     }
         }
      };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
    
  
}
    
    
}
