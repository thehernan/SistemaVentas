/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.Merma;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
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
 * @author info2017
 */
public class MermaDAO {
    
    
    
     public long insertar(Merma merma){ 
       
        ConexionBD Cbd = new ConexionBD();
       long id=0;
     try{
         
            String sql=("SELECT * from sp_insertarmerma(?,?)");         
            PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
            ps.setString(1, merma.getMotivo());
            ps.setDate(2, merma.getFecha());
       
            ResultSet rs= Cbd.RealizarConsulta(ps);
       if  (rs.next()){
           id=(rs.getLong("id"));
           
           //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();

}
  return id;  

  }
     
public void eliminar(long  idmerma){
    
        ConexionBD Cbd = new ConexionBD();
        ResultSet rs=null;
//        boolean valida=false;
     try{
        
        String sql=("SELECT * from sp_eliminarmerma(?)"); 
        
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idmerma);
      
       Cbd.RealizarConsulta(ps);
      
//        if  (rs.next()){
//            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
//
// 
//        }

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
    
    }
      
public void mostrar(JTable tabla,Timestamp desde,Timestamp hasta){
    DefaultTableModel modelo= new DefaultTableModel(){
        
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
          };      
         String titulos[]={"CODIGO","DESCRIPCION","CANT.","MOTIVO","SUCURSAL"};
         modelo.setColumnIdentifiers(titulos);
//         tabla.setModel(modelo);
         ConexionBD Cbd = new ConexionBD();
         ResultSet rs=null;
         PreparedStatement ps=null;
//         List<Producto> listprod = new ArrayList<>();



        try{
            
            String sql=("SELECT * from sp_mostrarmermas(?,?)");
            ps= Cbd.conectar().prepareStatement(sql);
            ps.setTimestamp(1, desde);
            ps.setTimestamp(2, hasta);
            rs= Cbd.RealizarConsulta(ps);
            Object datosR[] = new Object[5];
            while (rs.next()){
//                Producto prod = new Producto();
//                prod.setIdproducto(rs.getLong("id"));
//                prod.setCodigo(rs.getString("vcodigo"));
//                prod.setDescripcion(rs.getString("vdescripcion"));
                
                 datosR[0] =rs.getObject("vcodigo") ;
                 datosR[1] =rs.getObject("vdescrip");
                 datosR[2]=rs.getObject("vcant");
                 datosR[3]=rs.getObject("vmotivo");
                 datosR[4]=rs.getObject("vsucursal");
             modelo.addRow(datosR);
//             listprod.add(prod);
                    
            }
            
            tabla.setModel(modelo);
            TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(1).setPreferredWidth(350);
            columnModel.getColumn(2).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(350);
            columnModel.getColumn(4).setPreferredWidth(200);
          
           

            } catch(Exception e )
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                     
                      Cbd.desconectar();
                      
 
                      
                } 
//        return listprod;
    


}


public void busquedasensitiva(JTable tabla,Timestamp desde,Timestamp hasta,long idsucur,String op,String cadena){
    DefaultTableModel modelo= new DefaultTableModel(){
       
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
          };      
         String titulos[]={"CODIGO","DESCRIPCION","CANT.","MOTIVO","SUCURSAL"};
         modelo.setColumnIdentifiers(titulos);
//         tabla.setModel(modelo);
          ConexionBD Cbd = new ConexionBD();
         ResultSet rs=null;
         PreparedStatement ps=null;
//         List<Producto> listprod = new ArrayList<>();



        try{
            
            String sql=("SELECT * from sp_busquedasensitivamermas(?,?,?,?,?)");
            ps= Cbd.conectar().prepareStatement(sql);
            ps.setTimestamp(1, desde);
            ps.setTimestamp(2, hasta);
            ps.setString(3, cadena);
            ps.setLong(4, idsucur);
            ps.setString(5, op);
            rs= Cbd.RealizarConsulta(ps);
            Object datosR[] = new Object[5];
            while (rs.next()){
//                Producto prod = new Producto();
//                prod.setIdproducto(rs.getLong("id"));
//                prod.setCodigo(rs.getString("vcodigo"));
//                prod.setDescripcion(rs.getString("vdescripcion"));
                
                 datosR[0] =rs.getObject("vcodigo") ;
                 datosR[1] =rs.getObject("vdescrip");
                 datosR[2]=rs.getObject("vcant");
                 datosR[3]=rs.getObject("vmotivo");
                 datosR[4]=rs.getObject("vsucursal");
             modelo.addRow(datosR);
//             listprod.add(prod);
                    
            }
            
            tabla.setModel(modelo);
            TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(1).setPreferredWidth(350);
            columnModel.getColumn(2).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(350);
            columnModel.getColumn(4).setPreferredWidth(200);
        
            } catch(Exception e )
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                     
                      Cbd.desconectar();
                      
 
                      
                } 
//        return listprod;
    


}
public void imprimir(Date desde, Date hasta){
       ConexionBD Cbd = new ConexionBD();
    try{
    ///////////////////////// formato fecha ////////////////////////////
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//           datepicker.setFormats(dateFormat);   
//           java.util.Date fecha =((datepicker.getDate())); 
           
    ////////////////////////////////////////////////////////////+33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333////////       
            //Connection miconexion = conectar.Connect();
           
                      
            String  rutaInforme  = "src/Reportes/Mermas.jasper";
            
            Map parametros = new HashMap();
           
            parametros.put("desde",  desde);
            parametros.put("hasta",  hasta);
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
