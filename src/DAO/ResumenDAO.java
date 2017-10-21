/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Pojos.Resumen;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author info2017
 */
public class ResumenDAO {
     
      public Resumen mostrar(long id){
      
     Conexion conexion= new Conexion();

        Statement st=null;
        ResultSet rs=null;
        Resumen res = new Resumen();
          NumberFormat nf = NumberFormat.getInstance();
    try{
        String sql="SELECT * from sp_mostrarresumen(?)";
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setLong(1, id);
      
        rs=ps.executeQuery(); 
        
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
        
     
        rs.close();
        ps.close();
	
        } catch(SQLException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

return res;
    
      }
      
      public void imprimir(long id,String sucursal){
          Conexion conexion = new Conexion();
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
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,conexion.getConnection());
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          
        }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE", "ERROR",JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }finally{
    conexion.devolverConexionPool();

}
      }
}
