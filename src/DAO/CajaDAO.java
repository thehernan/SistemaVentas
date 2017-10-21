/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;

import Pojos.Caja;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class CajaDAO {
    
    
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
             String sql=("SELECT * from sp_validaperturacaja(?)");
             PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
             ps.setLong(1, idempledado);
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
 public void buscar(JTable tabla,Timestamp fdesde,Timestamp fhasta,JLabel jmensaje,long idsucur){
        
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
    }
    };      
    String titulos[]={"ID","CAJERO","FECHA APER.","HORA APER.","FECHA CIE.","HORA CIE.","APER. CON","CIERRA CON","ESTADO","MAQUINA"};
    modelo.setColumnIdentifiers(titulos);
    tabla.setModel(modelo);
    tabla.getColumnModel().getColumn(0).setMaxWidth(0);
    tabla.getColumnModel().getColumn(0).setMinWidth(0);
    tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
    Conexion conexion = new Conexion();
    
     
    try{
	
        System.out.println("SELECT * from sp_busquedacaja('"+fdesde+"','"+fhasta+"')");
        String sql=("SELECT * from sp_busquedacaja(?,?,?)"); 
        PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
        ps.setTimestamp(1,fdesde) ;
        ps.setTimestamp(2, fhasta);
        ps.setLong(3, idsucur);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[10];
       long  cont= 0;
        while (rs.next()){
                cont++;
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vidcaja");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vfechaaper");
                     i++;
                     datosR[i] = rs.getObject("vhoraaper");
                     i++;
                     datosR[i] = rs.getObject("vfechacierr");
                     i++;
                     datosR[i] = rs.getObject("vhoracierr");
                     i++;
                     datosR[i] = rs.getObject("vaperturadinero");
                     i++;
                     datosR[i] = rs.getObject("vcierradinero");
                     i++;
                     datosR[i] = rs.getObject("vestado");
                     i++;
                      datosR[i] = rs.getObject("vdescripcion");
                     i++;
                    modelo.addRow(datosR);
		}
                     
        }
        rs.close();
        ps.close();
        if(cont==0){
            jmensaje.setText("NO SE ENCONTRARON REGISTROS EN EL RANGO "+fdesde+" AL "+fhasta);
        }else{
            jmensaje.setText("");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    
    
    }

}
