/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;

import Pojos.Caja;
import Pojos.SucursalSingleton;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author info2017
 */
public class CajaDAO {
    SucursalSingleton sucursal = SucursalSingleton.getinstancia();   
    
public Caja insertar(Caja caja){
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
         Conexion conexion = new Conexion();
         ResultSet rs=null;
         
         try{
             
             System.out.println("SELECT * from sp_insertarcaja("+cajan.getId_empleado()+","+cajan.getAperturadinero()+",'"+IP.getHostName()+" "+IP.getHostAddress()+"')");
             String sql="SELECT * from sp_insertarcaja(?,?,?,?)";
             PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
             ps.setLong(1, cajan.getId_empleado());
             ps.setBigDecimal(2, new BigDecimal(cajan.getAperturadinero()));
             ps.setString(3, IP.getHostName()+" "+IP.getHostAddress()+", "+ippublica);
             ps.setLong(4, cajan.getId_sucursal());
             rs = ps.executeQuery();
             
             
             if (rs.next()){
                 
                 cajan.setId_caja(rs.getLong("vidcaja"));
                 cajan.setFechahora_apertura(rs.getTimestamp("vfecha"));
             }
             
             ps.close();
             rs.close();
             
             
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             conexion.devolverConexionPool();
             
         }
       
     }
     catch(UnknownHostException ex)
            {
            Logger.getLogger(CajaDAO.class.getName()).log(Level.SEVERE,null, ex);
            }
      return caja;
}
public Caja validapertura(long idempledado){
    
    Caja caja= new Caja();
   
        
         Conexion conexion = new Conexion();
         ResultSet rs=null;
         
         try{
             
             System.out.println("SELECT * from sp_validaperturacaja("+idempledado+")");
             String sql=("SELECT * from sp_validaperturacaja(?,?)");
             PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
             ps.setLong(1, idempledado);
             ps.setLong(2, sucursal.getId());
             rs = ps.executeQuery();
             
             
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
             
             ps.close();
             rs.close();            
             
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally{
             conexion.devolverConexionPool();
             
         }
 return caja;    
}
public void cierre(Caja caja){
 
    
      Conexion conexion = new Conexion();

     try{
       
      // System.out.println("SELECT * from sp_editaralumno('"+RUT+"','"+NOMBRE+"','"+APELLIDO+"','"+CURSO+"','"+SECCION+"','"+PRIORITARIO+"','"+FOTO+"')");
     String sql=("SELECT * from sp_cierrecaja(?,?)");         
       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       
            ps.setLong(1,caja.getId_caja());
            ps.setBigDecimal(2,new BigDecimal(caja.getCierradinero()));
          
            ps.execute();
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
            ps.close();

           

	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }        
 
}
 public List<Caja> buscar(JTable tabla,Timestamp fdesde,Timestamp fhasta,JLabel jmensaje,long idsucur){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
    }
    };      
    String titulos[]={"CAJERO","FECHA APER.","FECHA CIE.","APER. CON","TOTAL COBRADO","CIERRA CON","ESTADO","MAQUINA"};
    modelo.setColumnIdentifiers(titulos);
    tabla.setModel(modelo);
//    tabla.getColumnModel().getColumn(0).setMaxWidth(0);
//    tabla.getColumnModel().getColumn(0).setMinWidth(0);
//    tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
    Conexion conexion = new Conexion();
     DateFormat df = DateFormat.getDateInstance();
     List<Caja> listcaja= new ArrayList<>();
     Double total=0.0;
     NumberFormat nf= NumberFormat.getInstance();
    try{
	
        System.out.println("SELECT * from sp_busquedacaja('"+fdesde+"','"+fhasta+"')");
        String sql=("SELECT * from sp_busquedacaja(?,?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setTimestamp(1,fdesde) ;
        ps.setTimestamp(2, fhasta);
        ps.setLong(3, idsucur);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[8];
       long  cont= 0;
        while (rs.next()){
                cont++;
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

                 datosR[3] = nf.format(caja.getAperturadinero());
                 datosR[4] = nf.format(rs.getDouble("vtotalcobra"));
                 datosR[5] = nf.format(caja.getCierradinero());

                 datosR[6] = rs.getObject("vestado");

                 datosR[7] = rs.getObject("vdescripcion");

                modelo.addRow(datosR);
                listcaja.add(caja);
		  
        }
        
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(400);
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(150);
        rs.close();
        ps.close();
        if(cont==0){
            jmensaje.setText("NO SE ENCONTRARON REGISTROS EN EL RANGO "+df.format(fdesde)+" AL "+df.format(fhasta));
        }else{
            jmensaje.setText("TOTAL: "+nf.format(total));
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
    return listcaja;
    }

}
