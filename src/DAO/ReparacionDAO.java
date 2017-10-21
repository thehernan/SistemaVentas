/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.PrecioReparacion;
import Pojos.Reparacion;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class ReparacionDAO {
    
    public long insertar(Reparacion reparacion){
 
        PreparedStatement preparedStatement = null;
       
        Conexion conexion = new Conexion();
        String codigo= "";
        long idrepara=0;
        byte[] FOTO= reparacion.getFoto();
        InputStream fis = new ByteArrayInputStream(FOTO);
        
     try{
       
            String insertImageSql = "SELECT * from sp_insertareparacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            preparedStatement = conexion.getConnection().prepareStatement(insertImageSql);
            
            
            preparedStatement.setLong(1,reparacion.getIdcliente());
            preparedStatement.setLong(2,reparacion.getIdempleado());
            preparedStatement.setString(3, reparacion.getAtendido());
            preparedStatement.setString(4, reparacion.getMarca());
            preparedStatement.setString(5, reparacion.getModelo());
            preparedStatement.setString(6, reparacion.getFallas());
            preparedStatement.setString(7, reparacion.getCausas());
            preparedStatement.setString(8, reparacion.getObservacion());
            preparedStatement.setString(9, reparacion.getDiagnostico());
            preparedStatement.setBinaryStream(10,fis );  
           
            preparedStatement.setBigDecimal(11,new BigDecimal(reparacion.getPrecio()));
            preparedStatement.setTimestamp(12, reparacion.getFechaE());
            preparedStatement.setString(13, reparacion.getHora());
            preparedStatement.setLong(14,reparacion.getId_sucural());
            preparedStatement.setBigDecimal(15,new BigDecimal(reparacion.getPreciorevision()));
            preparedStatement.setBigDecimal(16,new BigDecimal(reparacion.getDescuento()));
            preparedStatement.execute();
            
            if(preparedStatement.getResultSet().next()){
                codigo=preparedStatement.getResultSet().getString("vcodigo");
                idrepara=preparedStatement.getResultSet().getLong("ridreparacion");
                System.out.println("codrep"+codigo);
                System.out.println("idrep"+idrepara);
                
            }
            preparedStatement.close();
        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
         
            conexion.devolverConexionPool();
            
     }
     return idrepara;
}
    public void editar(Reparacion reparacion){
 
        PreparedStatement preparedStatement = null;
       
        Conexion conexion = new Conexion();
      
        
     try{
       
            String insertImageSql = "SELECT * from sp_editarreparacion(?,?,?)";

            preparedStatement = conexion.getConnection().prepareStatement(insertImageSql);
            
            
            preparedStatement.setLong(1,reparacion.getIdreparacion());
            preparedStatement.setString(2,reparacion.getEstado());
            preparedStatement.setString(3, reparacion.getDiagnostico());
       
           
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
    public Reparacion buscar(JTable tab,String cod,JLabel cliente,JLabel rut,JFormattedTextField total,
        JFormattedTextField subtotal,JFormattedTextField iva,JFormattedTextField abono){
        Conexion conexion = new Conexion();
 
        Reparacion repara= new Reparacion();
        DefaultTableModel tabla=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
           //      if (column == 5) return true;
        //else
        return false;
    }
  }; 
    String titulos[]={"CODIGO","PRODUCTO","CANTIDAD","PRECIO","IMPORTE"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    try{
	
        System.out.println("SELECT * from sp_busquedareparacion('"+cod+"')");
        String sql=("SELECT * from sp_busquedareparacion(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setString(1, cod);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[5];
        cliente.setText("");
        rut.setText("");
        total.setValue(0);
        subtotal.setValue(0);
        iva.setValue(0);
        abono.setValue(0);
        int cont = 0;
        while (rs.next()){
            cont++;
           repara.setIdreparacion(rs.getLong("vidreparacion"));
           // System.out.println("NOMBRE: "+rs.getString("vnombre"));
           repara.setCodigo(rs.getString("vcodigo"));
           cliente.setText(rs.getString("vcliente"));
           rut.setText(rs.getString("vrut"));
           total.setValue(rs.getBigDecimal("vimporte"));
           subtotal.setValue(rs.getBigDecimal("vsubtotal"));
           iva.setValue(rs.getBigDecimal("viva"));
           abono.setValue(rs.getBigDecimal("vabono"));
            for(int i =0; i<=1; i++){
                     datosR[i] = repara.getCodigo();
                     i++;    
                     datosR[i] = rs.getObject("vproducto");
                     i++;
                     datosR[i] = rs.getObject("vcantidad");
                     i++;
                     datosR[i] = rs.getObject("vprecio");
                     i++;
                     datosR[i] = rs.getObject("vprecio");
                     i++;
//                     datosR[i] = rs.getObject("vcelular");
//                     i++;
                    
                    tabla.addRow(datosR);
		}
        }
        if (cont==0){
            JOptionPane.showMessageDialog(null,"LA REPARACION SE ENCUENTRA EN PROCESO");//SI NO ENCUENTRA LA REPARACION O ESTA EN PROCESO
        }
        rs.close();
        ps.close();
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    
return repara;
    
    }
    
    public void mostrar(JTable tab,Timestamp desde,Timestamp hasta,JLabel msj){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    }; 
    String titulos[]={"IDEMPLE","IDSUCUR","EMPLEADO","R.U.T","SUCURSAL","DIRECCION","TOTAL"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    
    tab.getColumnModel().getColumn(1).setMaxWidth(0);
    tab.getColumnModel().getColumn(1).setMinWidth(0);
    tab.getColumnModel().getColumn(1).setPreferredWidth(0);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarreparacion(?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setTimestamp(1, desde);
        ps.setTimestamp(2, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[7];
        msj.setText("");
        int cont=0;
        while (rs.next()){
           
            for(int i =0; i<=1; i++){
                     datosR[i] = rs.getObject("videmple");
                     i++;
                     datosR[i] = rs.getObject("vidsucur");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vsucursal");
                     i++;
                     datosR[i] = rs.getObject("vsucurdirec");
                     i++;
                     datosR[i] = rs.getObject("vtotal");
                     i++;
                    
                    tabla.addRow(datosR);
		}
            cont++;
        }
        rs.close();
        ps.close();
        if(cont == 0){
            msj.setText("NO SE ENCONTRARON REGISTROS EN EL RANGO DE "+desde+" AL "+hasta);
        }else {
            msj.setText("REGISTROS ENCONTRADOS "+cont);
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    
    }
     public void mostrarporempleado(JTable tab,long idemple,long idsucur,JLabel emple,JLabel sucur,
     Timestamp desde, Timestamp hasta){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"CODIGO","CLIENTE","ATENDIO","MARCA","MODELO","FALLAS","DIAGNOSTICO","ESTADO","FECHA REP.","FECHA ENTRE.","PRECIO"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarreparacionempleado(?,?,?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idemple);
        ps.setLong(2, idsucur);
        ps.setTimestamp(3, desde);
        ps.setTimestamp(4, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[11];
        
        
        while (rs.next()){
            emple.setText(rs.getString("vempleado"));
            sucur.setText(rs.getString("vsucursal"));
            for(int i =0; i<=1; i++){
                       
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vcliente");
                     i++;
                     datosR[i] = rs.getObject("vatentido");
                     i++;
                     datosR[i] = rs.getObject("vmarca");
                     i++;
                     datosR[i] = rs.getObject("vmodelo");
                     i++;
                     datosR[i] = rs.getObject("vfallas");
                     
                     i++;
                     datosR[i] = rs.getObject("vdiagnostico");
                     i++;
                     datosR[i] = rs.getObject("vestado");
                     i++;
                     datosR[i] = rs.getObject("vfecharecep");
                     i++;
                     datosR[i] = rs.getObject("vvfechaentre");
                     i++;
                     datosR[i] = rs.getObject("vprecio");
                     i++;
                                        
                    tabla.addRow(datosR);
		}
           
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
     
     public void mostrarporcliente(JTable tab,long id, JLabel msj){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"id","SUCURSAL","CODIGO","TECNICO","ATENDIO","MARCA","MODELO","DIAGNOSTICO","FECHA REP.","FECHA ENTRE.","ESTADO","PRECIO","PRECIO REVISION","DESCUENTO"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarreparacioncliente(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, id);
        
       
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[14];
        
        Integer cont=0;
        while (rs.next()){
           cont++;
             for(int i =0; i<=1; i++){
                     datosR[i] = rs.getObject("vidreparacion");
                     i++;
                      datosR[i] = rs.getObject("vsucursal");
                     i++;  
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vempleado");
                     i++;
                     datosR[i] = rs.getObject("vatentido");
                     i++;
                     datosR[i] = rs.getObject("vmarca");
                     i++;
                     datosR[i] = rs.getObject("vmodelo");
                     i++;
                     datosR[i] = rs.getObject("vdiagnostico");
                     i++;
                     datosR[i] = rs.getObject("vfecharecep");
                     
                     i++;
                     datosR[i] = rs.getObject("vvfechaentre");
                     i++;
                     datosR[i] = rs.getObject("vestado");
                     i++;
                     datosR[i] = rs.getObject("vprecio");
                     i++;
                     datosR[i] = rs.getObject("vpreciorevision");
                     i++;
                     datosR[i] = rs.getObject("vdescuento");
                     i++;
                                        
                    tabla.addRow(datosR);
		}
           
        }
        msj.setText("REGISTROS ENCONTRADOS "+cont.toString());
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    
    }
      public void mostrarpendientes(JTable tab,long idemple){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  }; 
    String titulos[]={"id","SUCURSAL","CODIGO","CLIENTE","ATENDIO","MARCA","MODELO","FALLAS","FECHA REP.","FECHA ENTRE."};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
    tab.getColumnModel().getColumn(0).setMaxWidth(0);
    tab.getColumnModel().getColumn(0).setMinWidth(0);
    tab.getColumnModel().getColumn(0).setPreferredWidth(0);
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarreparacionpendiente(?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, idemple);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[10];
        
        
        while (rs.next()){
            
            for(int i =0; i<=1; i++){
                    datosR[i] = rs.getObject("vidreparacion");
                     i++;
                    datosR[i] = rs.getObject("vsucursal");
                     i++;
                     datosR[i] = rs.getObject("vcodigo");
                     i++;
                     datosR[i] = rs.getObject("vcliente");
                     i++;
                     datosR[i] = rs.getObject("vatentido");
                     i++;
                     datosR[i] = rs.getObject("vmarca");
                     i++;
                     datosR[i] = rs.getObject("vmodelo");
                     i++;
                     datosR[i] = rs.getObject("vfallas");
                     
                     i++;
//                     datosR[i] = rs.getObject("vdiagnostico");
//                     i++;
                     datosR[i] = rs.getObject("vfecharecep");
                     i++;
                     datosR[i] = rs.getObject("vvfechaentre");
                     i++;
//                     datosR[i] = rs.getObject("vprecio");
//                     i++;
                                        
                    tabla.addRow(datosR);
		}
           
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
 public List<PrecioReparacion> llenarcombobox(JComboBox combo){
 
  Conexion conexion = new Conexion();
    
  List<PrecioReparacion> listprecio = new ArrayList<>();
  DefaultComboBoxModel modelo = new DefaultComboBoxModel();   
    try{
        
	PreparedStatement ps=conexion.getConnection().prepareStatement("SELECT * from sp_mostrarprecioporreparacion()");
       ResultSet rs=ps.executeQuery();
        modelo.addElement("<<SELECCIONE>>");
        listprecio.add(null);
        while (rs.next()){
            PrecioReparacion precio = new PrecioReparacion();
            precio.setDispositivo(rs.getString("vdispositivo"));
            precio.setPrecio(rs.getDouble("vprecio"));
            precio.setPreciorevision(rs.getDouble("vpreciovision"));
             
            modelo.addElement((rs.getObject("vdispositivo")));
            listprecio.add(precio);

        }
	combo.setModel(modelo);
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
    return listprecio;
    
    }

    public void imprimir(long id){
       Conexion conexion = new Conexion();  
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Reparacion.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("id",  id);
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
     public void imprimirticketcaja(long id){
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////////////    
           
           Conexion conexion = new Conexion();
            
                      
            String  rutaInforme  = "src/Reportes/ticketreparacion.jasper";
            
            Map parametros = new HashMap();
            System.out.println("idticket"+id);
            parametros.put("id",id);
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
     
      public void imprimirreparaciontodo(long id){
try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////////////    
           
           Conexion conexion = new Conexion();
            
                      
            String  rutaInforme  = "src/Reportes/ReparacionPorCliente.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("id",id);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (HeadlessException | JRException ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     

}
    
    
}
