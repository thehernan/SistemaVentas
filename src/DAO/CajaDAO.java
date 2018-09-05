/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClasesGlobales.FormatoNumerico;
import Conexion.ConexionBD;

import Pojos.Caja;
import Pojos.DetalleCaja;
import Pojos.SucursalSingleton;
import Pojos.Ventas;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
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
public class CajaDAO {
    SucursalSingleton sucursal = SucursalSingleton.getinstancia();   
    
    FormatoNumerico fn = new FormatoNumerico();
public Caja insertar(Caja caja){
    ConexionBD Cbd = new ConexionBD();
     try{
         InetAddress IP;
         String ippublica=null;
         IP = InetAddress.getLocalHost();
         System.out.println("Nombre de Host:"+IP.getHostName());
         System.out.println( "Dirección IP:"+IP.getHostAddress());
          try {

            URL whatismyip = new URL("http://checkip.amazonaws.com");

            BufferedReader in = new BufferedReader(new InputStreamReader(

                    whatismyip.openStream()));     

            ippublica = in.readLine();     

            System.out.println("My Public ip is = "+ippublica);

            in.close();

        } catch (MalformedURLException ex) {

            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    
         Caja cajan= caja;
        
         ResultSet rs=null;
         PreparedStatement ps=null;
         try{
             
            
             String sql="SELECT * from sp_insertarcaja(?,?,?,?)";
             ps=Cbd.conectar().prepareStatement(sql);
             ps.setLong(1, cajan.getId_empleado());
             ps.setBigDecimal(2, new BigDecimal(cajan.getAperturadinero()));
             ps.setString(3, IP.getHostName()+" "+IP.getHostAddress()+", "+ippublica);
             ps.setLong(4, cajan.getId_sucursal());
             rs = Cbd.RealizarConsulta(ps);
             
             
             if (rs.next()){
                 
                 cajan.setId_caja(rs.getLong("vidcaja"));
                 cajan.setFechahora_apertura(rs.getTimestamp("vfecha"));
             }
             
          
         }
         catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             Cbd.desconectar();
             
         }
       
     }
     catch(UnknownHostException ex)
            {
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE,null, ex);
            }
      return caja;
}
public Caja validapertura(long idempledado){
        ConexionBD Cbd = new ConexionBD();
        Caja caja= new Caja();
   
        
      
         ResultSet rs=null;
         PreparedStatement ps=null;
         try{
           
             String sql=("SELECT * from sp_validaperturacaja(?,?)");
             ps=Cbd.conectar().prepareStatement(sql);
             ps.setLong(1, idempledado);
             ps.setLong(2, sucursal.getId());
             rs = Cbd.RealizarConsulta(ps);
             
             
             if (rs.next()){
                 
                 caja.setId_caja(rs.getLong("vidcaja"));
                 caja.setId_empleado(rs.getLong("vidempleado"));
                 caja.setFechahora_apertura(rs.getTimestamp("vfechaapertura"));
                 caja.setFechahora_cierre(rs.getTimestamp("vfechacierre"));
                 caja.setAperturadinero(rs.getDouble("vadinero"));
                 caja.setCierradinero(rs.getDouble("vcdinero"));
                 caja.setEstado(rs.getString("vestado"));
                 caja.setDescripcion("vdescripcion");
                 
             }
          
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             Cbd.desconectar();
             
         }
 return caja;    
}
public void cierre(Caja caja){  
    ConexionBD Cbd = new ConexionBD();
    PreparedStatement ps=null;
     try{
       
        String sql=("SELECT * from sp_cierrecaja(?,?)");         
        ps=Cbd.conectar().prepareStatement(sql);
       
        ps.setLong(1,caja.getId_caja());
        ps.setBigDecimal(2,new BigDecimal(caja.getCierradinero()));

        if(Cbd.actualizarDatos(ps)==true){
            JOptionPane.showMessageDialog(null,"Cierre de caja exitoso","",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }        
 
}
 public List<Caja> busquedasensitiva(JTable tabla,Timestamp fdesde,Timestamp fhasta,JLabel jmensaje,long idsucur,String cadena
 ,String op){
    ConexionBD Cbdd = new ConexionBD();
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
    }
    };      
    String titulos[]={"Cajero","Fecha apertura","Fecha cierre","Apertura con","Total cobrado","Cierra con","Estado","Dispositivo","Sucursal"};
    modelo.setColumnIdentifiers(titulos);
    tabla.setModel(modelo);

    
     DateFormat df = DateFormat.getDateInstance();
     List<Caja> listcaja= new ArrayList<>();
     Double total=0.0;
    
     PreparedStatement ps=null;
     ResultSet rs=null;
    try{
	
        String sql=("SELECT * from sp_busquedasensitivacajero(?,?,?,?,?)"); 
        ps= Cbdd.conectar().prepareStatement(sql);
        ps.setTimestamp(1,fdesde) ;
        ps.setTimestamp(2, fhasta);
        ps.setLong(3, idsucur);
        ps.setString(4, cadena);
        ps.setString(5, op);
        rs = Cbdd.RealizarConsulta(ps);
        Object datosR[] = new Object[9];
       
        while (rs.next()){
                
            Caja caja = new Caja();

             caja.setId_caja(rs.getLong("vidcaja"));
             caja.setAperturadinero(rs.getDouble("vaperturadinero"));
             caja.setCierradinero(rs.getDouble("vcierradinero"));
             total= total+ caja.getCierradinero();
             datosR[0] = rs.getObject("vnombre");

             datosR[1] = rs.getObject("vfechaaper");

          //   datosR[2] = rs.getObject("vhoraaper");

             datosR[2] = rs.getObject("vfechacierr");

          //   datosR[4] = rs.getObject("vhoracierr");

             datosR[3] = fn.FormatoN(caja.getAperturadinero());
             datosR[4] = fn.FormatoN(rs.getDouble("vtotalcobra"));
             datosR[5] = fn.FormatoN(caja.getCierradinero());

             datosR[6] = rs.getObject("vestado");

             datosR[7] = rs.getObject("vdescripcion");
             datosR[8] = rs.getObject("vsucursal");
            modelo.addRow(datosR);
            listcaja.add(caja);
		  
        }
        
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(300);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(7).setPreferredWidth(250);
        columnModel.getColumn(8).setPreferredWidth(300);
      
        
        jmensaje.setText("Total: "+fn.FormatoN(total));
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbdd.desconectar();
            }    
    return listcaja;
    }
 
  public List<DetalleCaja> movimiento(JTable tabla,JLabel total,
            Timestamp desde, Timestamp hasta, String cadena, long idsucur,String op){
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(
                new String[]{"DOC.","CAJERO","CLIENTE","TIPO OP.","IMPORTE","FECHA","SUCURSAL"}, 0) {
 
        Class[] types = new Class[]{
             java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,
            java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
        };
 
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
         public boolean isCellEditable(int row, int column) {
//        //      if (column == 5) return true;
//        //else
        return false;
        }            };
        tabla.setModel(modelo);
        
        
        
        List<DetalleCaja> listdet = new ArrayList<>();
        PreparedStatement ps=null;        
        ResultSet rs=null; 
         try{
             
//             System.out.println("SELECT * from sp_mostrardetallecaja("+idcaja+")");
             String sql=("SELECT * from sp_busquedasensitivamovimientocaja(?,?,?,?,?)");
             ps=Cbd.conectar().prepareStatement(sql);
             ps.setLong(1, idsucur);
             ps.setString(2, op);
             ps.setString(3, cadena);
             ps.setTimestamp(4, desde);
             ps.setTimestamp(5, hasta);
             
             rs = Cbd.RealizarConsulta(ps);
             
             Object datosR[]= new Object[7];
             double vtotal=0.0;
             while (rs.next()){
                 
                 DetalleCaja detcaja= new DetalleCaja();
                 detcaja.setIdreparacion(rs.getLong("vidrepara"));
                 detcaja.setIdventa(rs.getLong("vidventa"));
                 detcaja.setAbono(rs.getDouble("vimporte"));
                 vtotal = vtotal + detcaja.getAbono();

                 datosR[0] = rs.getObject("vdocumento");

                 datosR[1] = rs.getObject("vcajero");

                 datosR[2] = rs.getObject("vrazons");

                 datosR[4] = fn.FormatoN(detcaja.getAbono());

                 datosR[3] = rs.getObject("vtipo");



                 datosR[5] = rs.getObject("vfecha");
////                 datosR[6] = rs.getObject("vmotivo");
////
////                 datosR[7] = rs.getBoolean("vanulada");
                 datosR[6] = rs.getObject("vsucursal");
                modelo.addRow(datosR);
                listdet.add(detcaja);
                  
             }
             total.setText("Total: "+fn.FormatoN(vtotal));
         
             
             TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(1).setPreferredWidth(300);
            columnModel.getColumn(2).setPreferredWidth(300);
            columnModel.getColumn(3).setPreferredWidth(80);
            columnModel.getColumn(4).setPreferredWidth(80);
            columnModel.getColumn(5).setPreferredWidth(150);
//            columnModel.getColumn(6).setPreferredWidth(250);
//            columnModel.getColumn(7).setPreferredWidth(60);
            columnModel.getColumn(6).setPreferredWidth(300);
             
        
        
         }
         catch(SQLException e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
            Cbd.desconectar();
             
         }
         return listdet;
}
 
 
}
