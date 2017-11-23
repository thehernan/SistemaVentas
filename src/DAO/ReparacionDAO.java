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
import java.text.DateFormat;
import java.text.NumberFormat;
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
import javax.swing.table.TableColumnModel;


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
    NumberFormat nf=NumberFormat.getInstance();
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
            
                     datosR[0] = repara.getCodigo();
                     
                     datosR[1] = rs.getObject("vproducto");
                   
                     datosR[2] = rs.getObject("vcantidad");
                   
                     datosR[3] =nf.format(rs.getObject("vprecio"));
                  
                     datosR[4] =nf.format(rs.getObject("vprecio"));
                    
//                     datosR[i] = rs.getObject("vcelular");
//                     i++;
                    
                    tabla.addRow(datosR);
		
        }
        tab.setModel(tabla);
        TableColumnModel columnModel = tab.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
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
    
    public List<Reparacion> mostrar(JTable tab,Timestamp desde,Timestamp hasta,JLabel msj,JLabel jtotal){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
       //      if (column == 5) return true;
    //else
    return false;
    }
    }; 
    String titulos[]={"EMPLEADO","R.U.T","SUCURSAL","DIRECCION","IMPORTE","DESCUENTO","TOTAL"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);

        DateFormat df= DateFormat.getDateInstance();
        NumberFormat nf = NumberFormat.getInstance();
        List<Reparacion> listrepa= new ArrayList<>();
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
        double total=0.0;
        while (rs.next()){
           
            Reparacion repara= new Reparacion();
            repara.setIdempleado(rs.getLong("videmple"));
            repara.setId_sucural(rs.getLong("vidsucur"));
            repara.setTotal(rs.getDouble("vtotal"));
            total = total+repara.getTotal();
             
             datosR[0] = rs.getObject("vnombre");
            
             datosR[1] = rs.getObject("vrut");
             
             datosR[2] = rs.getObject("vsucursal");
            
             datosR[3] = rs.getObject("vsucurdirec");
             datosR[4] = nf.format(rs.getObject("vimporte"));
             datosR[5] = nf.format(rs.getObject("vdescuento"));
             datosR[6] =nf.format(repara.getTotal());             

            tabla.addRow(datosR);
            listrepa.add(repara);
            cont++;
        }
        jtotal.setText("Total: "+nf.format(total));
        rs.close();
        ps.close();
        if(cont == 0){
            msj.setText("NO SE ENCONTRARON REGISTROS EN EL RANGO DE "+df.format(desde)+" AL "+df.format(hasta));
        }else {
            msj.setText("REGISTROS ENCONTRADOS "+cont);
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    
    return listrepa;
    
    }
     public List<Reparacion> mostrarporempleado(JTable tab,Reparacion repair,JLabel emple,JLabel sucur,
     Timestamp desde, Timestamp hasta){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"CODIGO","CLIENTE","ATENDIO","MARCA","MODELO","FALLAS","DIAGNOSTICO","FECHA REP.","FECHA ENTRE.","PRECIO","DESC.","TOTAL","ESTADO","MOTIVO"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
     List<Reparacion> listrepa= new ArrayList<>();
         NumberFormat nf = NumberFormat.getInstance();
    try{
	
        //System.out.println("SELECT * from sp_mostrarreparacion('"+cod+"')");
        String sql=("SELECT * from sp_mostrarreparacionempleado(?,?,?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, repair.getIdempleado());
        ps.setLong(2,repair.getId_sucural());
        ps.setTimestamp(3, desde);
        ps.setTimestamp(4, hasta);
        ResultSet rs= ps.executeQuery();
        Object datosR[] = new Object[14];
       
        
        while (rs.next()){
            Reparacion repa = new Reparacion();
            emple.setText(rs.getString("vempleado"));
            sucur.setText(rs.getString("vsucursal"));
       
            repa.setIdreparacion(rs.getLong("vidrepa"));
            System.out.println("idreparalist"+repa.getIdreparacion());
             datosR[0] = rs.getObject("vcodigo");

             datosR[1] = rs.getObject("vcliente");

             datosR[2] = rs.getObject("vatentido");

             datosR[3] = rs.getObject("vmarca");

             datosR[4] = rs.getObject("vmodelo");

             datosR[5] = rs.getObject("vfallas");


             datosR[6] = rs.getObject("vdiagnostico");

             

             datosR[7] = rs.getObject("vfecharecep");

             datosR[8] = rs.getObject("vvfechaentre");

             datosR[9] =nf.format(rs.getObject("vprecio"));
             datosR[10] =nf.format(rs.getObject("vdescuento"));
             datosR[11] =nf.format(rs.getObject("vtotal"));
             
             datosR[12] = rs.getObject("vestado");
             datosR[13] = rs.getObject("vmotivo");


            tabla.addRow(datosR);
            listrepa.add(repa);
           
        }
        
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    return listrepa;
    }
     
     public List<Reparacion> mostrarporcliente(JTable tab,long id, JLabel msj,JLabel jtotal){
        
     
    Conexion conexion = new Conexion();
 
   
    DefaultTableModel tabla=new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
  };     
    
    String titulos[]={"SUCURSAL","CODIGO","TECNICO","ATENDIO","MARCA","MODELO","DIAGNOSTICO","FECHA REP.","FECHA ENTRE.","ESTADO","PRECIO","PRECIO REVISION","DESCUENTO","TOTAL"};
    tabla.setColumnIdentifiers(titulos);
    tab.setModel(tabla);
   List<Reparacion> listrep= new ArrayList<>();
   double total=0.0;
   NumberFormat nf =NumberFormat.getInstance();
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
           Reparacion repara = new Reparacion();
             repara.setIdreparacion(rs.getLong("vidreparacion"));
             repara.setTotal(rs.getDouble("vtotal"));
             total=total+repara.getTotal();
              datosR[0] = rs.getObject("vsucursal");
               
             datosR[1] = rs.getObject("vcodigo");
             
             datosR[2] = rs.getObject("vempleado");
            
             datosR[3] = rs.getObject("vatentido");
            
             datosR[4] = rs.getObject("vmarca");
            
             datosR[5] = rs.getObject("vmodelo");
             
             datosR[6] = rs.getObject("vdiagnostico");
             
             datosR[7] = rs.getObject("vfecharecep");

             
             datosR[8] = rs.getObject("vvfechaentre");
           
             datosR[9] = rs.getObject("vestado");
            
             datosR[10] = nf.format(rs.getObject("vprecio"));
             
             datosR[11] = nf.format(rs.getObject("vpreciorevision"));
            
             datosR[12] = nf.format(rs.getObject("vdescuento"));
             datosR[13] = nf.format(repara.getTotal());
            
                                        
            tabla.addRow(datosR);
            listrep.add(repara);
		
           
        }
        msj.setText("REGISTROS ENCONTRADOS "+cont.toString());
        jtotal.setText("Total: "+nf.format(total));
	rs.close();
        ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
                       
            }    

    return listrep;
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
            

            datosR[0] = rs.getObject("vidreparacion");
             
            datosR[1] = rs.getObject("vsucursal");
             
             datosR[2] = rs.getObject("vcodigo");
            
             datosR[3] = rs.getObject("vcliente");
            
             datosR[4] = rs.getObject("vatentido");
            
             datosR[5] = rs.getObject("vmarca");
             
             datosR[6] = rs.getObject("vmodelo");
            
             datosR[7] = rs.getObject("vfallas");

            
//                     datosR[i] = rs.getObject("vdiagnostico");
//                     i++;
             datosR[8] = rs.getObject("vfecharecep");
             
             datosR[9] = rs.getObject("vvfechaentre");
             
//                     datosR[i] = rs.getObject("vprecio");
//                     i++;

            tabla.addRow(datosR);
		
           
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
 
 public void extornar(Reparacion repara){
 
  Conexion conexion = new Conexion();
    
  
    try{
        
	PreparedStatement ps=conexion.getConnection().prepareStatement("SELECT * from sp_extornareparacion(?,?)");
        ps.setLong(1, repara.getIdreparacion());
        ps.setString(2, repara.getMotivo());
       ps.executeQuery();
       ps.close();
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
    
    
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
