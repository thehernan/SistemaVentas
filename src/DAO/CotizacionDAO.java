/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Cotizacion;
import Pojos.SucursalSingleton;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HERNAN
 */
public class CotizacionDAO {
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
     
     
   public long insert(Cotizacion cotizacion) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
        ConexionBD Cbd = new ConexionBD();        
        PreparedStatement ps= null;
        ResultSet rs= null;
        long id= 0;
        try{
	
        ps = Cbd.conectar().prepareStatement("SELECT * from sp_insertcotizacion(?,?,?,?)");
        ps.setLong(1,cotizacion.getIdcliente());
        ps.setString(2, cotizacion.getEmitida());
        ps.setString(3, cotizacion.getCondicionpago());
        
        ps.setLong(4,sucursalsingleton.getId());
        
       
       
    
        
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
           id= rs.getLong("id");
           JOptionPane.showMessageDialog(null,"Cotizacion guardado con exito");	
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();

            }
             
        return id;
    }
   
   public List<Cotizacion> mostrar(JTable tabla){
       ConexionBD Cbd = new ConexionBD();
       DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
        };
        String titulos[]={"Codigo","Cliente","Fecha"};
        modelo.setColumnIdentifiers(titulos);
        tabla.setModel(modelo);
        Object datosR[] = new Object[3];
        
          
        PreparedStatement ps= null;
        ResultSet rs= null;
        List<Cotizacion> listcotizacion = new ArrayList<>();
        try{
	
        ps = Cbd.conectar().prepareStatement("SELECT * from sp_mostrarcotizacion(?)");
        ps.setLong(1,sucursalsingleton.getId());
     
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setId(rs.getLong("vid"));
            datosR[0]=rs.getString("vcodigo");
            datosR[1]=rs.getString("vcliente");
            datosR[2]=rs.getString("vfecha");
            listcotizacion.add(cotizacion);
            modelo.addRow(datosR);
           
        }
           TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(450);
            columnModel.getColumn(2).setPreferredWidth(50);
	
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();

            }
       
   return listcotizacion;
   
   }
   
   public List<Cotizacion> busquedasensitiva(JTable tabla,String cadena,String op,long idsucur,
            Timestamp desde,Timestamp hasta){
       ConexionBD Cbd = new ConexionBD();
       DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
        };
        String titulos[]={"Codigo","Cliente","Fecha","Sucursal"};
        modelo.setColumnIdentifiers(titulos);
        tabla.setModel(modelo);
        Object datosR[] = new Object[4];
        
      
        PreparedStatement ps= null;
        ResultSet rs= null;
        List<Cotizacion> listcotizacion = new ArrayList<>();
        try{
	
        ps = Cbd.conectar().prepareStatement("SELECT * from sp_busquedasensitivacotizacion(?,?,?,?,?)");
        ps.setLong(1,idsucur);
        ps.setString(2, cadena);
        ps.setString(3, op);
        ps.setTimestamp(4, desde);
        ps.setTimestamp(5, hasta);
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setId(rs.getLong("vid"));
            datosR[0]=rs.getString("vcodigo");
            datosR[1]=rs.getString("vcliente");
            datosR[2]=rs.getString("vfecha");
            datosR[3]=rs.getString("vsucursal");
            listcotizacion.add(cotizacion);
            modelo.addRow(datosR);
           
        }
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(100);    
        if(modelo.getRowCount()>0){
            tabla.setRowSelectionInterval(0, 0);
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();

            }
       
   return listcotizacion;
   
   }
   public void imprimir(long idcotizacion){
   ConexionBD Cbd = new ConexionBD();
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Cotizacion.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idcotizacion",  idcotizacion);
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
