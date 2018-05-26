/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.ConexionBD;
import Pojos.Cliente;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.edisoncor.gui.textField.TextFieldRoundIcon;

/**
 *
 * @author info2017
 */
public class ClienteDAO {
    
    
    public List<Cliente> mostrarcliente(JTable tabla){
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
     return false;
    }
      };      
     String titulos[]={"R.U.T.","NOMBRES","CELULAR"};
     modelo.setColumnIdentifiers(titulos);
      tabla.setModel(modelo);

//     Conexion conexion= new Conexion();

        PreparedStatement ps=null;
        ResultSet rs=null;

        List<Cliente> listcliente= new ArrayList<>();
        
    try{

        
        ps=Cbd.conectar().prepareStatement("SELECT * from sp_mostrarcliente()");
        rs=Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[3];
        while (rs.next()){
                   
             Cliente cliente = new Cliente();  
             cliente.setId_cliente(rs.getLong("id"));
             cliente.setRut(rs.getString("vrut"));
             cliente.setNombre_razons(rs.getString("vnombre"));
             cliente.setCelular(rs.getString("vcelular"));
             cliente.setDireccion(rs.getString("vdireccion"));
             cliente.setEmail(rs.getString("vemail"));
             cliente.setIdtipodoc(rs.getLong("iddocumento"));
             cliente.setDocumento(rs.getString("vdocumento"));
             datosR[0] = cliente.getRut();

             datosR[1] = cliente.getNombre_razons();

             datosR[2] = cliente.getCelular();


            modelo.addRow(datosR);
            listcliente.add(cliente);
		
        }
       
             
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(90);
       
     
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{

                Cbd.desconectar();
            }    
      return listcliente;
    
    }
    
    public List<Cliente> busquedasensitivacliente(String tipoB,String cadena,JTable tabla){
        ConexionBD Cbd = new ConexionBD();
       DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
    }
    };      
    String titulos[]={"R.U.T.","NOMBRES","CELULAR"};
    modelo.setColumnIdentifiers(titulos);

     
       PreparedStatement ps=null;
       ResultSet rs=null;
       List<Cliente> listcliente= new ArrayList<>();
     
    try{
	ps= Cbd.conectar().prepareStatement("SELECT * from sp_busquedasensitivacliente(?,?)");
        ps.setString(1, tipoB);
        ps.setString(2, cadena);
        rs=Cbd.RealizarConsulta(ps); 
        Object datosR[] = new Object[3];
        while (rs.next()){
                    
             Cliente cliente = new Cliente();  
             cliente.setId_cliente(rs.getLong("id"));
             cliente.setRut(rs.getString("vrut"));
             cliente.setNombre_razons(rs.getString("vnombre"));
             cliente.setCelular(rs.getString("vcelular"));
             cliente.setDireccion(rs.getString("vdireccion"));
             cliente.setEmail(rs.getString("vemail"));
             datosR[0] = cliente.getRut();

             datosR[1] = cliente.getNombre_razons();

             datosR[2] = cliente.getCelular();


            modelo.addRow(datosR);
            listcliente.add(cliente);
                     
        }
        tabla.setModel(modelo);
        TableColumnModel columnModel = tabla.getColumnModel();
     
        columnModel.getColumn(0).setPreferredWidth(90);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(90);
        if (modelo.getRowCount()>0){
            tabla.setRowSelectionInterval (0,0); 
               
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
                                    
            }    
       return listcliente;
    }

public Cliente buscarcliente(String tipoB,long id,String cadena,JLabel msj){
    ConexionBD Cbd = new ConexionBD();
    Cliente cliente= new Cliente();
  
    ResultSet rs=null;
    PreparedStatement ps=null;
     try{

         System.out.println("SELECT * from sp_busquedacliente('"+tipoB+"',"+id+",'"+cadena+"')");
        String sql=("SELECT * from sp_busquedacliente(?,?,?)"); 
        ps=Cbd.conectar().prepareStatement(sql);
        ps.setString(1, tipoB);
        ps.setLong(2, id);
        ps.setString(3, cadena);
        rs = Cbd.RealizarConsulta(ps);

        if (rs.next()){
             cliente.setId_cliente(rs.getLong("vid"));
             cliente.setNombre_razons(rs.getString("vnombre"));
             cliente.setRut( rs.getString("vrut")) ;

             cliente.setDireccion(rs.getString("vdireccion"));
             cliente.setCelular(rs.getString("vcelular"));
             cliente.setEmail(rs.getString("vemail"));
 
        msj.setText("");
        }else {
        msj.setText("Cliente no registrado");
        
        }

     
//	
        } catch(Exception e)
            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            Cbd.desconectar();
     
     }
     return cliente;

    }

public boolean duplicado(long id,String cadena,String tipoop){

    ConexionBD Cbd = new ConexionBD();
    ResultSet rs=null;
    PreparedStatement ps;
    boolean valida=false;
     try{

      
      String sql=("SELECT * from sp_duplicadocliente(?,?,?)"); 
      ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, cadena);
      ps.setLong(2, id);
      ps.setString(3, tipoop);
      rs = Cbd.RealizarConsulta(ps);

        if (rs.next()){
            valida= rs.getBoolean("vvalida");
        }

       
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
           Cbd.desconectar();
     
     }
     return valida;

    }

public Cliente  buscarclientevent(String cadena,JLabel msj,
        JFormattedTextField total,JFormattedTextField venta){
  
    ConexionBD Cbd = new ConexionBD();
    ResultSet rs=null;
    PreparedStatement ps=null;
    long id=0;
    Cliente cliente =null;
     try{
//	st= (Statement)miconexion.createStatement();
        
      String sql=("SELECT * from sp_busquedaclienteventa(?)"); 
      ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, cadena);
      rs = Cbd.RealizarConsulta(ps);

      total.setValue(0);
      venta.setValue(0);
     
      boolean val;
      
        if (rs.next()){
            cliente = new Cliente();
            cliente.setId_cliente(rs.getLong("idb"));
            cliente.setNombre_razons(rs.getString("nombre"));
            cliente.setDireccion(rs.getString("vdireccion"));
            cliente.setEmail(rs.getString("vemail"));
            total.setValue(rs.getDouble("total"));
            venta.setValue(rs.getDouble("ventas"));
                   
        msj.setText("");
        }else {
        msj.setText("VARIOS - VENTAS MENORES A S/.700.00 Y OTROS");
     
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            } finally{
            Cbd.desconectar();
     
     }
     return cliente;

    }
public long insertarcliente(Cliente cliente){
        ConexionBD Cbd = new ConexionBD();
       long id=0;
       PreparedStatement ps=null;
       ResultSet rs=null;
     try{
          
          
            String Sql = "SELECT * from sp_insertarcliente(?,?,?,?,?,?)";
//            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
            ps = Cbd.conectar().prepareStatement(Sql);
            ps.setString(1,cliente.getNombre_razons());
            ps.setString(2,cliente.getRut());
            ps.setString(3,cliente.getDireccion());
            ps.setString(4,cliente.getCelular());
            ps.setString(5,cliente.getEmail());
            ps.setLong(6, cliente.getIdtipodoc());
            rs= Cbd.RealizarConsulta(ps);
            
            if(rs.next()){
                id= rs.getLong("id");
            
            }
            
          

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
return id;
}
public void editarcliente(Cliente cliente){
 
     ConexionBD Cbd = new ConexionBD();
     
     ResultSet rs=null;
     PreparedStatement ps=null;
     try{
          
        String sql=("SELECT * from sp_editarcliente(?,?,?,?,?,?,?)");         
        ps=Cbd.conectar().prepareStatement(sql);
       
        ps.setLong(1,cliente.getId_cliente());
        ps.setString(2,cliente.getNombre_razons());
        ps.setString(3,cliente.getRut());
        ps.setString(4,cliente.getDireccion());
        ps.setString(5,cliente.getCelular());
        ps.setString(6,cliente.getEmail());
        ps.setLong(7, cliente.getIdtipodoc());
   
       if  (Cbd.actualizarDatos(ps)==true){
            JOptionPane.showMessageDialog(null,"Cliente Editado con exito","",JOptionPane.INFORMATION_MESSAGE);
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }  finally{
            Cbd.desconectar();
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

   ConexionBD Cbd = new ConexionBD();
    PreparedStatement ps=null;        
  
     try{
	
        
      String sql=("SELECT * from sp_eliminarcliente(?)");       
      ps= Cbd.conectar().prepareStatement(sql);
      ps.setLong(1, idcliente);
      
       if  (Cbd.actualizarDatos(ps)==true){
            JOptionPane.showMessageDialog(null,"Cliente eliminado con exito","",JOptionPane.INFORMATION_MESSAGE);
        }
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               Cbd.desconectar();
            }    
 
    
 

    
}
}
