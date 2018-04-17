/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Resumen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class ResumenDAO {
    
    
      DecimalFormat nf = new DecimalFormat("#.00");
     
      
    public Resumen mostrar(long id){
      
        ConexionBD Cbd = new ConexionBD();

        Statement st=null;
        ResultSet rs=null;
        Resumen res = new Resumen();
         
    try{
        String sql="SELECT * from sp_mostrarresumen(?)";
        PreparedStatement ps =Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, id);
      
        rs=Cbd.RealizarConsulta(ps); 
        
        if (rs.next()){
            res.setProdreg(("Productos Registrados: "+rs.getLong("prodreg")));
            res.setProdbajo("Productos con bajo stock: "+rs.getLong("prodbajo"));
            res.setClireg("Clientes registrados: "+rs.getLong("clireg"));
            res.setProvreg("Proveedores registrados: "+rs.getLong("provreg"));
            res.setEmplereg("Empleados registrados: "+rs.getLong("emplereg"));
            res.setVentanio("Ventas del año: "+nf.format(rs.getDouble("ventanio")));
            res.setVentames("Ventas del mes: "+nf.format(rs.getDouble("ventmes")));
            res.setVentadia("Ventas del dia: "+nf.format(rs.getDouble("ventdia")));
            res.setVentproc("Ventas en proceso: "+rs.getLong("ventproc")); 
            res.setEmpleadomes("Vendedor del mes: "+rs.getString("empleames"));
        }
        
     
     
	
        } catch(SQLException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
            }    

return res;
    
      }
      
      public void imprimir(long id,String sucursal){
          ConexionBD Cbd = new ConexionBD();
        try{
            ///////////////////////// formato fecha ////////////////////////////
        //            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //           datepicker.setFormats(dateFormat);   
        //           java.util.Date fecha =((datepicker.getDate())); 

            ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
                    //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Resumen.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("idsucursal",  id);
             parametros.put("sucursal",  sucursal);
//            parametros.put("fecha",fecha);
//            parametros.put("motivodet", motdet);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
    Cbd.desconectar();

}
      }
}
