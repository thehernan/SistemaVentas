/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClasesGlobales.FormatoNumerico;
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
    
    
      FormatoNumerico fn = new FormatoNumerico();
     
      
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
            res.setProdreg(rs.getInt("prodreg"));
            res.setProdbajo(rs.getInt("prodbajo"));
            res.setClireg(rs.getInt("clireg"));
            res.setProvreg(+rs.getInt("provreg"));
            res.setEmplereg(rs.getInt("emplereg"));
      
            //FACTURA
            res.setFactnio("Facturas: "+fn.FormatoN(rs.getDouble("factanio")));
            res.setFactmes("Facturas: "+fn.FormatoN(rs.getDouble("factmes")));
            res.setFactdia("Facturas: "+fn.FormatoN(rs.getDouble("factdia")));
            res.setFactenero(rs.getDouble("factmesenero"));
            res.setFactfebrero(rs.getDouble("factmesfebrero"));
            res.setFactmarzo(rs.getDouble("factmesmarzo"));
            res.setFactabril(rs.getDouble("factmesabril"));
            res.setFactmayo(rs.getDouble("factmesmayo"));
            res.setFactjunio(rs.getDouble("factmesjunio"));
            res.setFactjulio(rs.getDouble("factmesjulio"));
            res.setFactagosto(rs.getDouble("factmesagosto"));
            res.setFactsetiembre(rs.getDouble("factmessetiembre"));
            res.setFactoctubre(rs.getDouble("factmesactubre"));
            res.setFactnoviembre(rs.getDouble("factmesnov"));
            res.setFactdiciembre(rs.getDouble("factmesdic"));
            //BOLETA
            res.setBolnio("Boletas: "+fn.FormatoN(rs.getDouble("bolanio")));
            res.setBolmes("Boletas: "+fn.FormatoN(rs.getDouble("bolmes")));
            res.setBoldia("Boletas: "+fn.FormatoN(rs.getDouble("boldia")));
            res.setBolenero(rs.getDouble("bolmesenero"));
            res.setBolfebrero(rs.getDouble("bolmesfebrero"));
            res.setBolmarzo(rs.getDouble("bolmesmarzo"));
            res.setBolabril(rs.getDouble("bolmesabril"));
            res.setBolmayo(rs.getDouble("bolmesmayo"));
            res.setBoljunio(rs.getDouble("bolmesjunio"));
            res.setBoljulio(rs.getDouble("bolmesjulio"));
            res.setBolagosto(rs.getDouble("bolmesagosto"));
            res.setBolsetiembre(rs.getDouble("bolmessetiembre"));
            res.setBoloctubre(rs.getDouble("bolmesactubre"));
            res.setBolnoviembre(rs.getDouble("bolmesnov"));
            res.setBoldiciembre(rs.getDouble("bolmesdic"));
            //NOTA CRED
            res.setNotacnio("Total Notas Crédito año: "+fn.FormatoN(rs.getDouble("notacanio")));
            res.setNotacmes("Total Notas Crédito mes: "+fn.FormatoN(rs.getDouble("notacmes")));
            res.setNotacdia("Total Notas Crédito dia: "+fn.FormatoN(rs.getDouble("notacdia")));
            res.setNotacenero(rs.getDouble("notacmesenero"));
            res.setNotacfebrero(rs.getDouble("notacmesfebrero"));
            res.setNotacmarzo(rs.getDouble("notacmesmarzo"));
            res.setNotacabril(rs.getDouble("notacmesabril"));
            res.setNotacmayo(rs.getDouble("notacmesmayo"));
            res.setNotacjunio(rs.getDouble("notacmesjunio"));
            res.setNotacjulio(rs.getDouble("notacmesjulio"));
            res.setNotacagosto(rs.getDouble("notacmesagosto"));
            res.setNotacsetiembre(rs.getDouble("notacmessetiembre"));
            res.setNotacoctubre(rs.getDouble("notacmesactubre"));
            res.setNotacnoviembre(rs.getDouble("notacmesnov"));
            res.setNotacdiciembre(rs.getDouble("notacmesdic"));
            //NOTA DEB
            res.setNotadnio("Total Notas Débito año: "+fn.FormatoN(rs.getDouble("notadanio")));
            res.setNotadmes("Total Notas Débito mes: "+fn.FormatoN(rs.getDouble("notadmes")));
            res.setNotaddia("Total Notas Débito dia: "+fn.FormatoN(rs.getDouble("notaddia")));
            res.setNotadenero(rs.getDouble("notadmesenero"));
            res.setNotadfebrero(rs.getDouble("notadmesfebrero"));
            res.setNotadmarzo(rs.getDouble("notadmesmarzo"));
            res.setNotadabril(rs.getDouble("notadmesabril"));
            res.setNotadmayo(rs.getDouble("notadmesmayo"));
            res.setNotadjunio(rs.getDouble("notadmesjunio"));
            res.setNotadjulio(rs.getDouble("notadmesjulio"));
            res.setNotadagosto(rs.getDouble("notadmesagosto"));
            res.setNotadsetiembre(rs.getDouble("notadmessetiembre"));
            res.setNotadoctubre(rs.getDouble("notadmesactubre"));
            res.setNotadnoviembre(rs.getDouble("notadmesnov"));
            res.setNotaddiciembre(rs.getDouble("notadmesdic"));
            
            
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
