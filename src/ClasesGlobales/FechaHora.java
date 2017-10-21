/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

import Conexion.Conexion;
import Pojos.Usuarios;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class FechaHora {
    private String fecha;
    private String hora;
    
public FechaHora extraer(){ 
    FechaHora fecha = new FechaHora();
    ResultSet rs=null;
    Conexion conexion = new Conexion();
     try{

         System.out.println("SELECT * from sp_fechasistema()");
      String sql=("SELECT * from sp_fechasistema()"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
      FileInputStream fis ;

        if (rs.next()){
                     fecha.setFecha(rs.getString("fecha"));
                     fecha.setHora( rs.getString("hora")) ;
        }
      
        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
     }
     
     
return fecha;
}    

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
   
    

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
    
    
    
}
