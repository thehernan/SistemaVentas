/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class EstadoCuentaDAO {
    
     
     
    public void mostrardebe(JTable tabla,long idsucur){
    ConexionBD Cbd = new ConexionBD();    
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
  //      if (column == 5) return true;
  //else
        return false;
       }
       };      
      String titulos[]={"ID","NOMBRE RAZON S.","RUT","TOTAL","ABONO","SALDO"};
      modelo.setColumnIdentifiers(titulos);
      tabla.setModel(modelo);
      tabla.getColumnModel().getColumn(0).setMaxWidth(0);
      tabla.getColumnModel().getColumn(0).setMinWidth(0);
      tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
  
          
     
    try{
	
      
        String sql=("SELECT * from sp_estadocuentadebe(?)"); 
        PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ResultSet rs =Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[6];
        while (rs.next()){
                    
                         
             datosR[0] = rs.getObject("vidproveedor");

             datosR[1] = rs.getObject("vnombre");

             datosR[2] = rs.getObject("vrut");

             datosR[3] = rs.getObject("vtotal");

             datosR[4] = rs.getObject("vabono");

             datosR[5] = rs.getObject("vsaldo");
                     
                    
            modelo.addRow(datosR);
		
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    
    }
    public void imprimirdebe(long idprove,long idsucur){
        ConexionBD Cbd = new ConexionBD();
    try{
        ///////////////////////// formato fecha ////////////////////////////
    //            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //           datepicker.setFormats(dateFormat);   
    //           java.util.Date fecha =((datepicker.getDate())); 

        ////////////////////////////////////////////////////////////////////       
                //Connection miconexion = conectar.Connect();

                      
            String  rutaInforme  = "src/Reportes/EstadoCuentaDebe.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucursal",  idsucur);
            parametros.put("idproveedor",  idprove);
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
    public void imprimirhaber(long idsucur,long idcliente ){
        ConexionBD Cbd = new ConexionBD();
    try{
        ///////////////////////// formato fecha ////////////////////////////
    //            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //           datepicker.setFormats(dateFormat);   
    //           java.util.Date fecha =((datepicker.getDate())); 

        ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
                //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/EstadoCuentaHaber.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucursal",  idsucur);
            parametros.put("idcliente",  idcliente);
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
    public void mostrarhaber(JTable tabla,long idsucur){
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
      };      
     String titulos[]={"ID","NOMBRE RAZON S.","RUT","CELULAR","IMPORTE","ABONO","SALDO"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
     tabla.getColumnModel().getColumn(0).setMaxWidth(0);
     tabla.getColumnModel().getColumn(0).setMinWidth(0);
     tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
 
          
     
    try{
	
      
        String sql=("SELECT * from sp_estadocuentahaber(?)"); 
        PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idsucur);
        ResultSet rs = Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[7];
        while (rs.next()){
                   
                         
             datosR[0] = rs.getObject("vidcliente");
           
             datosR[1] = rs.getObject("vnombre");
            
             datosR[2] = rs.getObject("vrut");
             
             datosR[3] = rs.getObject("vcelular");
           
             datosR[4] = rs.getObject("vimporte");
            
             datosR[5] = rs.getObject("vabono");
            
             datosR[6] = rs.getObject("vsaldo");
            

            modelo.addRow(datosR);

        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

    
    }
    
}
