/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Conexion;
import Pojos.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.edisoncor.gui.textField.TextFieldRoundIcon;

/**
 *
 * @author info2017
 */
public class ClienteDAO {
    
    public void mostrarcliente(JTable tabla){
        
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
      };      
     String titulos[]={"ID","RUT","NOMBRES","CELULAR"};
     modelo.setColumnIdentifiers(titulos);


     Conexion conexion= new Conexion();

        Statement st=null;
        ResultSet rs=null;

     
    try{
	st= conexion.getConnection().createStatement();
      
        rs=st.executeQuery("SELECT * from sp_mostrarcliente()"); 
        Object datosR[] = new Object[4];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vcelular");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        rs.close();
        st.close();
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
    
    public void busquedasensitivacliente(String tipoB,String cadena,JTable tabla){
        
       DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
    }
    };      
    String titulos[]={"ID","RUT","NOMBRES","CELULAR"};
    modelo.setColumnIdentifiers(titulos);

     Conexion conexion= new Conexion();
       Statement st=null;
       ResultSet rs=null;
    
     
    try{
	st= conexion.getConnection().createStatement();
      
        rs=st.executeQuery("SELECT * from sp_busquedasensitivacliente('"+tipoB+"','"+cadena+"')"); 
        Object datosR[] = new Object[4];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("id");
                     i++;
                     datosR[i] = rs.getObject("vrut");
                     i++;
                     datosR[i] = rs.getObject("vnombre");
                     i++;
                     datosR[i] = rs.getObject("vcelular");
                     i++;
                    
                    modelo.addRow(datosR);
		}
                     
        }
        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
	rs.close();
        st.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
                                    
            }    
       
    }

public Cliente buscarcliente(String tipoB,long id,String cadena,JLabel msj){

    Cliente cliente= new Cliente();
    Conexion conexion = new Conexion();
    ResultSet rs=null;
     try{
//	st= (Statement)miconexion.createStatement();
         System.out.println("SELECT * from sp_busquedacliente('"+tipoB+"',"+id+",'"+cadena+"')");
      String sql=("SELECT * from sp_busquedacliente('"+tipoB+"',"+id+",'"+cadena+"')"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
                     cliente.setId_cliente(rs.getLong("vid"));
                     cliente.setNombre_razons(rs.getString("vnombre"));
                     cliente.setRut( rs.getString("vrut")) ;
                     
                     cliente.setDireccion(rs.getString("vdireccion"));
                     cliente.setCelular(rs.getString("vcelular"));
                     cliente.setEmail(rs.getString("vemail"));
 
        msj.setText("");
        }else {
        msj.setText("CLIENTE NO REGISTRADO");
        
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            conexion.devolverConexionPool();
     
     }
     return cliente;

    }

public boolean duplicado(long id,String cadena,String tipoop){

    Conexion conexion = new Conexion();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_duplicadocliente(?,?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, cadena);
      ps.setLong(2, id);
      ps.setString(3, tipoop);
      rs = ps.executeQuery();
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
        if (rs.next()){
            valida= rs.getBoolean("vvalida");
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            conexion.devolverConexionPool();
     
     }
     return valida;

    }

public long  buscarclientevent(String cadena,JLabel msj,TextFieldRoundIcon nombre,
        JFormattedTextField total,JFormattedTextField venta){
  
    Conexion conexion = new Conexion();
    ResultSet rs=null;
  
    long id=0;
     try{
//	st= (Statement)miconexion.createStatement();
        
      String sql=("SELECT * from sp_busquedaclienteventa(?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, cadena);
      rs = ps.executeQuery();
//      FileInputStream fis ;
//      byte[] vacio= new byte[0];
      total.setValue(0);
      venta.setValue(0);
      nombre.setText("");
      boolean val;
      
        if (rs.next()){
                   
                    id=(rs.getLong("idb"));
                    nombre.setText(rs.getString("nombre"));
                    total.setValue(rs.getDouble("total"));
                    venta.setValue(rs.getDouble("ventas"));
                   
        msj.setText("");
        }else {
        msj.setText("CLIENTE NO REGISTRADO, INGRESE SUS DATOS");
     
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            conexion.devolverConexionPool();
     
     }
     return id;

    }
public long insertarcliente(Cliente cliente){

       long id=0;
       Conexion conexion = new Conexion();
     try{
          
          
            String Sql = "SELECT * from sp_insertarcliente(?,?,?,?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            PreparedStatement ps = conexion.getConnection().prepareStatement(Sql);
            ps.setString(1,cliente.getNombre_razons());
            ps.setString(2,cliente.getRut());
            ps.setString(3,cliente.getDireccion());
            ps.setString(4,cliente.getCelular());
            ps.setString(5,cliente.getEmail());
           
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                id= rs.getLong("id");
            
            }
            
            rs.close();
            ps.close();

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
     }
return id;
}
public void editarcliente(Cliente cliente){
 
     
     Conexion conexion = new Conexion();

     try{

     String sql=("SELECT * from sp_editarcliente(?,?,?,?,?,?)");         
       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       
            ps.setLong(1,cliente.getId_cliente());
            ps.setString(2,cliente.getNombre_razons());
            ps.setString(3,cliente.getRut());
            ps.setString(4,cliente.getDireccion());
            ps.setString(5,cliente.getCelular());
            ps.setString(6,cliente.getEmail());

            ps.execute();
           
       if  (ps.getResultSet().next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	 ps.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }  finally{
            conexion.devolverConexionPool();
     }      
 
}
//public boolean validaduplicado(String rut){
// 
//     
//     Conexion conexion = new Conexion();
//     boolean val=false;
//     try{
//
//     String sql=("SELECT * from sp_duplicadocliente(?)");         
//       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
//       
//             ps.setString(1,rut);
//            ResultSet rs = ps.executeQuery();
//           
//       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"EL RUT "+rut+" YA SE ENCUENTRA REGISTRADO");
//            val=false;
//        }else {
//           val=true;
//       }
//	 ps.close();
//        } catch(Exception e)
//            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            }  finally{
//            conexion.devolverConexionPool();
//     }      
// return val;
//}
public void eliminarcliente(long idcliente){

    Conexion conexion = new Conexion();
            
  
     try{
	
        System.out.println("SELECT * from sp_eliminarcliente("+idcliente+")");
      String sql=("SELECT * from sp_eliminarcliente(?)");       
      PreparedStatement ps= conexion.getConnection().prepareStatement(sql);
      ps.setLong(1, idcliente);
      ResultSet rs =ps.executeQuery();
       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               conexion.devolverConexionPool();
            }    
 
    
 

    
}
}
