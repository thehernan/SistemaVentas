/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.ConexionBD;
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
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class ComprasDAO {
    
    DecimalFormat formatea = new DecimalFormat("#.00");
    
    public long insertar(Compras compra){
    ConexionBD Cbd= new ConexionBD();    
    long id=0;
    PreparedStatement  ps=null;
    ResultSet rs=null;
     try{
                    
        String sql=("SELECT * from sp_insertarcompra(?,?,?,?,?,?,?,?)");         
        ps= Cbd.conectar().prepareStatement(sql);
        ps.setString(1, compra.getDocumento());
        ps.setString(2, compra.getNumero());
        ps.setLong(3, compra.getId_proveedor());
        ps.setDate(4, compra.getFecha());
        ps.setLong(5, compra.getId_sucursal());
        ps.setString(6, compra.getEstado());
        ps.setString(7, compra.getTipopago());
        ps.setBigDecimal(8,new BigDecimal(compra.getAbono()));
        rs=Cbd.RealizarConsulta(ps);
       if  (rs.next()){
           id= rs.getLong("vidcompra");

        }
	
    } catch(SQLException | HeadlessException e)
        {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
         Cbd.desconectar();

}
  return id;  
}
    
public void editar(Compras compra){
    ConexionBD Cbd= new ConexionBD();   
    PreparedStatement ps=null;
    
     try{
           
        String sql=("SELECT * from sp_editarcompra(?,?,?,?,?,?,?,?)");         
        ps= Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, compra.getId_compra());
        ps.setString(2, compra.getDocumento());
        ps.setString(3, compra.getNumero());
        ps.setLong(4, compra.getId_proveedor());
        ps.setDate(5, compra.getFecha());
        ps.setString(6, compra.getEstado());
        ps.setString(7, compra.getTipopago());
        ps.setBigDecimal(8,new BigDecimal(compra.getAbono()));
       
       if  (Cbd.actualizarDatos(ps)==true){
          
           JOptionPane.showMessageDialog(null,"Editado con exito","",JOptionPane.INFORMATION_MESSAGE);
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();

}
   
}
 public List<Compras> mostrar(JTable tabla,long idsucur){
    ConexionBD Cbd= new ConexionBD();    
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
    TableColumnModel columnModel = tabla.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(50);
    columnModel.getColumn(2).setPreferredWidth(450);
    columnModel.getColumn(3).setPreferredWidth(50);
    columnModel.getColumn(4).setPreferredWidth(50);
    columnModel.getColumn(5).setPreferredWidth(50);
  
    List<Compras> listcompra= new ArrayList<>();
    PreparedStatement ps=null; 
    ResultSet rs=null;
    Object datosR[] = new Object[6];
    try{
	
      
        String sql=("SELECT * from sp_mostrarcompras(?)"); 
        ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        rs = Cbd.RealizarConsulta(ps);
        
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
       
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    return listcompra;
    }
     
public List<Compras> pendientes(JTable tabla,long idsucur){
    ConexionBD Cbd= new ConexionBD();    
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
      }
      };      
    
     String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","R.U.T","FECHA"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
    TableColumnModel columnModel = tabla.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(50);
    columnModel.getColumn(2).setPreferredWidth(450);
    columnModel.getColumn(3).setPreferredWidth(50);
    columnModel.getColumn(4).setPreferredWidth(50);
  
    List<Compras> listcompras = new ArrayList<>();
    PreparedStatement ps=null;
    ResultSet rs=null;
    Object datosR[] = new Object[5];
    try{
       String sql=("SELECT * from sp_mostrarcompraspendientes(?)"); 
        ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        rs = Cbd.RealizarConsulta(ps);
        
        while (rs.next()){
                     
            Compras compras = new Compras();
            compras.setId_compra(rs.getLong("vid"));
            compras.setDocumento(rs.getString("vdocumento"));
            compras.setNumero(rs.getString("vnumero"));
             datosR[0] = compras.getDocumento();

             datosR[1] = compras.getNumero();

             datosR[2] = rs.getObject("vproveedor");

             datosR[3] = rs.getObject("vrut");

             datosR[4] = rs.getObject("vfecha");
        
                     
            listcompras.add(compras);
            modelo.addRow(datosR);
		
        }
        
      
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    
    return listcompras;
    
    }
public List<Compras> busquedasensitivapendientes(JTable tabla,long idsucur,String cadena){
    ConexionBD Cbd= new ConexionBD();    
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
   return false;
  }
  };      
// String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","R.U.T","FECHA","CODIGO","PRODUCTO","CANTIDAD LLEGO","CANTIDAD ACORDADA"};
    String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","R.U.T","FECHA"};
    modelo.setColumnIdentifiers(titulos);
    tabla.setModel(modelo);
   TableColumnModel columnModel = tabla.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(50);
    columnModel.getColumn(2).setPreferredWidth(450);
    columnModel.getColumn(3).setPreferredWidth(50);
    columnModel.getColumn(4).setPreferredWidth(50);
   
    List<Compras> listcompras = new ArrayList<>();
    PreparedStatement ps=null;
    ResultSet rs=null;
    Object datosR[] = new Object[5];
    try{
       String sql=("SELECT * from sp_busquedasensitivacompraspendientes(?,?)"); 
        ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ps.setString(2, cadena);
        rs = Cbd.RealizarConsulta(ps);
       
        while (rs.next()){
                     
            Compras compras = new Compras();
            compras.setId_compra(rs.getLong("vid"));
            compras.setDocumento(rs.getString("vdocumento"));
            compras.setNumero(rs.getString("vnumero"));
             datosR[0] = compras.getDocumento();

             datosR[1] = compras.getNumero();

             datosR[2] = rs.getObject("vproveedor");

             datosR[3] = rs.getObject("vrut");

             datosR[4] = rs.getObject("vfecha");
                     
            listcompras.add(compras);
            modelo.addRow(datosR);
		
        }
      
      
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
            }    
    return listcompras;
    
    }
//public void pendientesporproveedor(JTable tabla,long idsucur,long idprove){
//        
//    DefaultTableModel modelo= new DefaultTableModel(){
//    public boolean isCellEditable(int row, int column) {
//  //      if (column == 5) return true;
//  //else
//   return false;
//  }
//  };      
// String titulos[]={"DOCUMENTO","NUMERO","PROVEEDOR","R.U.T","FECHA","CODIGO","PRODUCTO","CANTIDAD LLEGO","CANTIDAD ACORDADA"};
// modelo.setColumnIdentifiers(titulos);
// tabla.setModel(modelo);
//  Conexion conexion = new Conexion();     
//    try{
//       String sql=("SELECT * from sp_mostrarcompraspendientesporprov(?,?)"); 
//        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
//        ps.setLong(1, idsucur);
//        ps.setLong(2, idprove);
//        ResultSet rs = ps.executeQuery();
//        Object datosR[] = new Object[9];
//        while (rs.next()){
//                     
//                         
//                     datosR[0] = rs.getObject("vdocumento");
//                     
//                     datosR[1] = rs.getObject("vnumero");
//                    
//                     datosR[2] = rs.getObject("vproveedor");
//                    
//                     datosR[3] = rs.getObject("vrut");
//                    
//                     datosR[4] = rs.getObject("vfecha");
//                    
//                     datosR[5] = rs.getObject("vcodigo");
//                   
//                     datosR[6] = rs.getObject("vdescripcion");
//                    
//                      datosR[7] = rs.getObject("vcantidad");
//                     
//                      datosR[8] = rs.getObject("vcantidadacor");
//                   
//                    
//                    modelo.addRow(datosR);
//		
//        }
//	ps.close();
//        rs.close();
//        } catch(Exception e)
//            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            }finally{
//                conexion.devolverConexionPool();
//            }    
//
//    
//    }
     
 public void busquedasensitiva(JTable tabla,long idsucur,String numero){
    ConexionBD Cbd= new ConexionBD();   
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
    PreparedStatement ps=null;
    ResultSet rs=null;      
     Object datosR[] = new Object[7];
    try{
	
      
        String sql=("SELECT * from sp_busquedasensitivacompra(?,?)"); 
        ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ps.setString(2, numero);
        rs =Cbd.RealizarConsulta(ps);
        
        while (rs.next()){
                   
                         
             datosR[0] = rs.getObject("id");

             datosR[1] = rs.getObject("vdocumento");

             datosR[2] = rs.getObject("vnumero");

             datosR[3] = rs.getObject("vproveedor");

             datosR[4] = rs.getObject("vfecha");

             datosR[5] = rs.getObject("vtipopago");

             datosR[6] = rs.getObject("vabono");


            modelo.addRow(datosR);
        }
        
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    
    }
     
public DefaultTableModel  buscar(JTable tabla,long idcompra,JTextField proveedor,JTextField proveerut,
        Compras compra,List<DetalleCompras> listdet){//,JFormattedTextField total,JFormattedTextField subtotal,
//        JFormattedTextField iva){
    ConexionBD Cbd= new ConexionBD();
        
        DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"CODIGO","DESCRIPCION","CANT. LLEGO","CANT. ACORDADA","PRECIO","IMPORTE"}, 0) {
   
             public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
            return false;
            }
            };
       
       
        long idprod,iddet;
        double cantidad,precio,cantidadacordad;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Object datosR[] = new Object[6];
    try{
     
        String sql=("SELECT * from sp_busquedacompras(?)"); 
        ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idcompra);
        rs = Cbd.RealizarConsulta(ps);
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
          TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(450);
            columnModel.getColumn(2).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(50);
            columnModel.getColumn(5).setPreferredWidth(50);
      
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    return modelo;
    }

 public void imprimir(long idsucur){
    ConexionBD Cbd= new ConexionBD();  
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
 public void imprimirporproveedor(long idsucur,long idprove){
   ConexionBD Cbd= new ConexionBD();   
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
}
