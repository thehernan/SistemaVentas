/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.Compras;
import Pojos.DetalleCompras;
import java.awt.HeadlessException;
import java.math.BigDecimal; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class ComprasDAO {
    
    public long insertar(Compras compra){
        Conexion conexion = new Conexion();
        long id=0;
     try{
                  
            
            
            System.out.println("SELECT * from sp_insertarcompra('"+compra.getDocumento()+"','"+compra.getNumero()+"',"+compra.getId_proveedor()+",'"+compra.getFecha()+"')");
            
            String sql=("SELECT * from sp_insertarcompra(?,?,?,?,?,?,?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
            ps.setString(1, compra.getDocumento());
            ps.setString(2, compra.getNumero());
            ps.setLong(3, compra.getId_proveedor());
            ps.setDate(4, compra.getFecha());
            ps.setLong(5, compra.getId_sucursal());
            ps.setString(6, compra.getEstado());
            ps.setString(7, compra.getTipopago());
            ps.setBigDecimal(8,new BigDecimal(compra.getAbono()));
            ResultSet rs=ps.executeQuery();
       if  (rs.next()){
           id= rs.getLong("vidcompra");
           JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
             conexion.devolverConexionPool();

}
  return id;  
}
    public void editar(Compras compra){
        Conexion conexion = new Conexion();
       
     try{
           
            String sql=("SELECT * from sp_editarcompra(?,?,?,?,?,?,?,?)");         
            PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
            ps.setLong(1, compra.getId_compra());
            ps.setString(2, compra.getDocumento());
            ps.setString(3, compra.getNumero());
            ps.setLong(4, compra.getId_proveedor());
            ps.setDate(5, compra.getFecha());
            ps.setString(6, compra.getEstado());
            ps.setString(7, compra.getTipopago());
            ps.setBigDecimal(8,new BigDecimal(compra.getAbono()));
            ResultSet rs=ps.executeQuery();
       if  (rs.next()){
          
           JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
             conexion.devolverConexionPool();

}
   
}
     public List<Compras> mostrar(JTable tabla,long idsucur){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
 String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","FECHA","TIPO PAGO","ABONO"};
 modelo.setColumnIdentifiers(titulos);
 tabla.setModel(modelo);

  Conexion conexion = new Conexion();
    List<Compras> listcompra= new ArrayList<>();
     
    try{
	
      
        String sql=("SELECT * from sp_mostrarcompras(?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[6];
        while (rs.next()){
           Compras compra = new Compras();
           compra.setId_compra(rs.getLong("id"));
           
            
             datosR[0] = rs.getObject("vdocumento");

             datosR[1] = rs.getObject("vnumero");

             datosR[2] = rs.getObject("vproveedor");

             datosR[3] = rs.getObject("vfecha");

             datosR[4] = rs.getObject("vtipopago");

             datosR[5] = rs.getObject("vabono");
               
            listcompra.add(compra);
            modelo.addRow(datosR);
		
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    return listcompra;
    }
     
public void pendientes(JTable tabla,long idsucur){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
 String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","R.U.T","FECHA","CODIGO","PRODUCTO","CANTIDAD LLEGO","CANTIDAD ACORDADA"};
 modelo.setColumnIdentifiers(titulos);
 tabla.setModel(modelo);
  Conexion conexion = new Conexion();     
    try{
       String sql=("SELECT * from sp_mostrarcompraspendientes(?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[9];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vdocumento");
                     i++;
                     datosR[i] = rs.getObject("vnumero");
                     i++;
                     datosR[i] = rs.getObject("vproveedor");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vfecha");
                     i++;
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vdescripcion");
                     i++;
                      datosR[i] = rs.getObject("vcantidad");
                     i++;
                      datosR[i] = rs.getObject("vcantidadacor");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
public void pendientesporproveedor(JTable tabla,long idsucur,long idprove){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
 String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","R.U.T","FECHA","CODIGO","PRODUCTO","CANTIDAD LLEGO","CANTIDAD ACORDADA"};
 modelo.setColumnIdentifiers(titulos);
 tabla.setModel(modelo);
  Conexion conexion = new Conexion();     
    try{
       String sql=("SELECT * from sp_mostrarcompraspendientesporprov(?,?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ps.setLong(2, idprove);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[9];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vdocumento");
                     i++;
                     datosR[i] = rs.getObject("vnumero");
                     i++;
                     datosR[i] = rs.getObject("vproveedor");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vfecha");
                     i++;
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vdescripcion");
                     i++;
                      datosR[i] = rs.getObject("vcantidad");
                     i++;
                      datosR[i] = rs.getObject("vcantidadacor");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
     
     public void busquedasensitiva(JTable tabla,long idsucur,String numero){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
 String titulos[]={"ID","DOCUMENTO","NUMERO","PROVEEDOR","FECHA","TIPO PAGO","ABONO"};
 modelo.setColumnIdentifiers(titulos);
 tabla.setModel(modelo);
 tabla.getColumnModel().getColumn(0).setMaxWidth(0);
 tabla.getColumnModel().getColumn(0).setMinWidth(0);
 tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
  Conexion conexion = new Conexion();
          
     
    try{
	
      
        String sql=("SELECT * from sp_busquedasensitivacompra(?,?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ps.setString(2, numero);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[7];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vdocumento");
                     i++;
                     datosR[i] = rs.getObject("vnumero");
                     i++;
                     datosR[i] = rs.getObject("vproveedor");
                     i++;
                     datosR[i] = rs.getObject("vfecha");
                     i++;
                     datosR[i] = rs.getObject("vtipopago");
                     i++;
                     datosR[i] = rs.getObject("vabono");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
     
public DefaultTableModel  buscar(JTable tabla,long idcompra,JTextField proveedor,JTextField proveerut,
        Compras compra,List<DetalleCompras> listdet    ){//,JFormattedTextField total,JFormattedTextField subtotal,
//        JFormattedTextField iva){
        NumberFormat formatea = NumberFormat.getInstance();
        DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"CODIGO","DESCRIPCION","CANTIDAD LLEGO","CANTIDAD ACORDADA","PRECIO","IMPORTE"}, 0) {
   
             public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
            return false;
            }
            };
       
        Conexion conexion = new Conexion();
        long idprod,iddet;
        double cantidad,precio,cantidadacordad;
        
    try{
     
        String sql=("SELECT * from sp_busquedacompras(?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idcompra);
        ResultSet rs = ps.executeQuery();
//        ID","CODIGO","DESCRIPCION","CANTIDAD","PRECIO","IMPORTE
        Object datosR[] = new Object[6];
        
        while (rs.next()){
            compra.setId_compra(rs.getLong("id"));
            compra.setDocumento(rs.getString("vdocumento"));
            compra.setNumero(rs.getString("vnumero"));
            compra.setId_proveedor(rs.getLong("vidproveedor"));
            compra.setFecha(rs.getDate("vfecha"));
            compra.setTipopago(rs.getString("vtipopago"));
            proveedor.setText(rs.getString("vproveedor"));
            proveerut.setText(rs.getString("vrut"));
            compra.setAbono(rs.getDouble("vabono"));
//            total.setValue(rs.getObject("vtotal"));
//            subtotal.setValue(rs.getObject("vsubtotal"));   
//            iva.setValue(rs.getObject("iva"));
            
                    
                     DetalleCompras detcompra= new DetalleCompras();
                     iddet=rs.getLong("viddetalle");
                     if(iddet!=0){          
                                         
                     detcompra.setIddetallecompra(iddet);
                     detcompra.setIdproducto( rs.getLong("vidprod"));
                     detcompra.setCantidad(rs.getDouble("vcantidad"));
                     detcompra.setPrecio(rs.getDouble("vprecio"));
                     detcompra.setCantidadacord(rs.getDouble("vcantidadacordada"));
                     detcompra.setImporte(rs.getDouble("vimporte"));
                     listdet.add(detcompra);
               
                     datosR[0] = rs.getObject("vcodigo");
                    
                     datosR[1] = rs.getObject("vdescripcion");
                    
                     datosR[2] =formatea.format(detcompra.getCantidad());
                    
                     datosR[3] = formatea.format(detcompra.getCantidadacord());
                    
                     datosR[4] = formatea.format(detcompra.getPrecio());
                    
                     datosR[5] = formatea.format(detcompra.getImporte());
                     
                    
                    
                    modelo.addRow(datosR);
                    
		}
        }
        tabla.setModel(modelo);
      
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    return modelo;
    }

 public void imprimir(long idsucur){
       Conexion conexion = new Conexion();  
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/ProductoPendiente.jasper";
            
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
 public void imprimirporproveedor(long idsucur,long idprove){
       Conexion conexion = new Conexion();  
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/ProductoPendienteProve.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucur",  idsucur);
            parametros.put("idprove",  idprove);
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
}
