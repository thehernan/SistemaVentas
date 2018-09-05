/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.ConexionBD;
import Pojos.EmpleadoSingleton;
import Pojos.Merma;
import Pojos.Producto;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    EmpleadoSingleton empleado = EmpleadoSingleton.getinstancia();
   public boolean insertar(Merma merma,List<Producto> listdet){ 
       
        ConexionBD Cbd = new ConexionBD();
        Connection cnn=Cbd.getConexion();
         try {
             cnn.setAutoCommit(false);
         } catch (SQLException ex) {
             Logger.getLogger(MermaDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       long id=0;
       boolean validacabecera=true,validadetalle=true,estado=false;
       PreparedStatement cabecera=null,detalle=null;
       ResultSet rscabecera=null, rsdetalle=null;
       String msj="";
       int cont=0;
     try{
         
            String sql=("SELECT * from sp_insertarmerma(?,?)");         
            cabecera= cnn.prepareStatement(sql);
//            cabecera.setString(1, merma.getMotivo());
            cabecera.setDate(1, merma.getFecha());
            cabecera.setLong(2,empleado.getId_empleado());
       
            rscabecera= Cbd.RealizarConsulta(cabecera);
       if  (rscabecera.next()){
           id=(rscabecera.getLong("id"));
           
           //JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
       if(id!=0)
       {
            for(Producto det: listdet){
         
            String sqldet="SELECT * from sp_insertardetallemerma(?,?,?,?)"; 
            detalle=cnn.prepareStatement(sqldet);
            detalle.setLong(1, det.getIdproducto());
            detalle.setBigDecimal(2,new BigDecimal(det.getCantidad()));
            detalle.setLong(3, id);
            detalle.setString(4, det.getMotivo());
        
            rsdetalle=Cbd.RealizarConsulta(detalle);
            msj=("Insertando Item "+cont);
            cont++;
         }
            while(rsdetalle.next())
            {
                if(rsdetalle.getBoolean("vvalida")==false)
                {
                    JOptionPane.showMessageDialog(null,"No cuenta con stock: "+rsdetalle.getString("mens")+" retire ó edite",""
                    ,JOptionPane.ERROR_MESSAGE);
//                    cnn.rollback();
                    validadetalle=false;
//                    break;
                    
                }        
            
            }
    
       }else {
           validadetalle=false;
       }
        if(validacabecera==true && validadetalle==true)
           {
               cnn.commit();
               estado=true;
              
           }else {
               cnn.rollback();
               System.err.println("rollback");
               estado=false;
           }
            
	
        } catch(SQLException | HeadlessException e)
            {
                estado=false;
                JOptionPane.showMessageDialog(null, e.getMessage());
                try {
                    cnn.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(MermaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }finally{
                Cbd.desconectar();
             try {
                 rscabecera.close();
                 rsdetalle.close();
                 cabecera.close();
                 detalle.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(MermaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                       

}
  return estado;  

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
