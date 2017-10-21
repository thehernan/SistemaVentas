/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import Conexion.conectar;
import Conexion.Conexion;

import Pojos.UsuarioSingleton;
import Pojos.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class UsuariosDAO {
    
    public UsuarioSingleton validauser(String userr,String clave){

    UsuarioSingleton user=UsuarioSingleton.getintancia();
    
    Conexion conexion = new Conexion();
     try{
         

          System.out.println("SELECT * from sp_validauser('"+userr+"','"+clave+"')");
          String sql=("SELECT * from sp_validauser(?,?)"); 
          PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
          ps.setString(1, userr);
          ps.setString(2, clave);
          ResultSet rs = ps.executeQuery();

        if (rs.next()){
                     user.setId_usuario(rs.getLong("viduser"));
                     user.setUsuario(rs.getString("vuser"));
                     user.setClave(rs.getString("vclave"));
                     user.setIdempleado(rs.getLong("vidmpleado"));
                     user.setCliente(rs.getBoolean("vcliente"));
                     user.setProveedor(rs.getBoolean("vproveedor"));
                     user.setApertura(rs.getBoolean("vapertura"));
                     user.setConsultarrepara(rs.getBoolean("vconsultarrepara"));
                     user.setConsultarventa(rs.getBoolean("vconsultavende"));
                     user.setDebe(rs.getBoolean("vdebe"));
                     user.setCkempleado(rs.getBoolean("vempleado"));
                     user.setFamilia(rs.getBoolean("vfamilia"));
                     user.setHaber(rs.getBoolean("vingreso"));
                     user.setIngreso(rs.getBoolean("vingreso"));
                     user.setInventario(rs.getBoolean("vinventario"));
                     user.setMovimientos(rs.getBoolean("vmovimientos"));
                     user.setNueva(rs.getBoolean("vnueva"));
                     user.setPendientes(rs.getBoolean("vpendientes"));
                     user.setProductos(rs.getBoolean("vproductos"));
                     user.setUseri(rs.getBoolean("vusuari"));
                     user.setVender(rs.getBoolean("vvender"));
                     user.setTema(rs.getString("vtema"));
                     user.setExtornar(rs.getBoolean("vextornar"));
                     
                     user.setProdpendientes(rs.getBoolean("vpendienteprod"));
                     user.setMermas(rs.getBoolean("vmermas"));
                     user.setConsulmermas(rs.getBoolean("vconsulmermas"));
                     user.setSucursalida(rs.getBoolean("vsucursalida"));
                     user.setSucurentrada(rs.getBoolean("vsucurentrada"));
                     user.setConsultasucur(rs.getBoolean("vsucurconsultar"));
//                    private boolean prodpendientes;
//    private boolean mermas;
//    private boolean consulmermas;
//    private boolean sucursalida;
//    private boolean sucurentrada;
//    private boolean consultasucur;
        }

        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally {
            conexion.devolverConexionPool();
     }
     return user;

    }
    public void mostrar(JTable tabla){
        
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
      };      
     String titulos[]={"ID","EMPLEADO","USUARIO"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
     tabla.getColumnModel().getColumn(0).setMaxWidth(0);
     tabla.getColumnModel().getColumn(0).setMinWidth(0);
     tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
      Conexion conexion = new Conexion();
          
     
    try{
	
      
        String sql=("SELECT * from sp_mostrarusuario()"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Object datosR[] = new Object[3];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vid");
                     i++;
                     datosR[i] = rs.getObject("vempleado");
                     i++;
                     datosR[i] = rs.getObject("vusuario");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
    public void busquedasensitiva(JTable tabla,String cadena){
        
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
      };      
     String titulos[]={"ID","EMPLEADO","USUARIO"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
     tabla.getColumnModel().getColumn(0).setMaxWidth(0);
     tabla.getColumnModel().getColumn(0).setMinWidth(0);
     tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
      Conexion conexion = new Conexion();
          
     
    try{
	
      
        String sql=("SELECT * from sp_busquedasensitivausuario(?)"); 
        PreparedStatement ps = conexion.getConnection().prepareStatement(sql);
        ps.setString(1, cadena);
        ResultSet rs = ps.executeQuery();
        
        Object datosR[] = new Object[3];
        while (rs.next()){
                     for(int i =0; i<=1; i++){
                         
                     datosR[i] = rs.getObject("vid");
                     i++;
                     datosR[i] = rs.getObject("vempleado");
                     i++;
                     datosR[i] = rs.getObject("vusuario");
                     i++;
                    
                    modelo.addRow(datosR);
		}
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                conexion.devolverConexionPool();
            }    

    
    }
    
//    public void insertar(Usuarios user){
//
////  byte[] FOTO= cliente.getImg();
//  
////    System.out.println("fotoinsert"+FOTO);
//        PreparedStatement preparedStatement = null;
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//     try{
//            connection = conectar.Connect();
//          
//            String insertImageSql = "SELECT * from sp_insertarcliente(?,?,?,?,?)";
////            String insertImageSql = "UPDATE alumno SET foto=? where id_alumno=?;";
//            preparedStatement = connection.prepareStatement(insertImageSql);
//            
//////            System.out.println("rutainsert"+ruta);
//////            File file = new File(ruta);
//////            FileInputStream fis = new FileInputStream(file);
////            InputStream fis = new ByteArrayInputStream(FOTO); 
////            System.out.println("fisinset"+fis);
//            preparedStatement.setString(1,NOMBRE);
//            preparedStatement.setString(2,RUT);
//            preparedStatement.setString(3,DIRECCION);
//            preparedStatement.setString(4,CELULAR);
//            preparedStatement.setString(5,EMAIL);
////            preparedStatement.setString(6,PRIORITARIO);
////            preparedStatement.setBinaryStream(7,fis);
//            
//            
//            
//            preparedStatement.execute();
//
//        }
//     catch(Exception e)
//            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            }
//
//}
      

public Usuarios buscar(String tipoB,long id,JTextField empleado, JTextField rut){  //busqueda por id de empleado

    Usuarios user= new Usuarios();
   
   
    Conexion conexion = new Conexion();
     try{

         System.out.println("SELECT * from sp_busquedauser('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedauser(?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ResultSet rs = ps.executeQuery();
   

        if (rs.next()){
                     user.setId_usuario(rs.getLong("id"));
                     user.setUsuario( rs.getString("vuser")) ;
                     user.setClave(rs.getString("vclave"));
                     user.setIdempleado(rs.getLong("vidempleado"));
                     empleado.setText(rs.getString("vnombre"));
                     rut.setText(rs.getString("vrut"));
                     user.setCliente(rs.getBoolean("vcliente"));
                     user.setProveedor(rs.getBoolean("vproveedor"));
                     user.setApertura(rs.getBoolean("vapertura"));
                     user.setConsultarrepara(rs.getBoolean("vconsultarrepara"));
                     user.setConsultarventa(rs.getBoolean("vconsultavende"));
                     user.setDebe(rs.getBoolean("vdebe"));
                     user.setCkempleado(rs.getBoolean("vempleado"));
                     user.setFamilia(rs.getBoolean("vfamilia"));
                     user.setHaber(rs.getBoolean("vingreso"));
                     user.setIngreso(rs.getBoolean("vingreso"));
                     user.setInventario(rs.getBoolean("vinventario"));
                     user.setMovimientos(rs.getBoolean("vmovimientos"));
                     user.setNueva(rs.getBoolean("vnueva"));
                     user.setPendientes(rs.getBoolean("vpendientes"));
                     user.setProductos(rs.getBoolean("vproductos"));
                     user.setUser(rs.getBoolean("vusuari"));
                     user.setVender(rs.getBoolean("vvender"));
                     user.setExtornar(rs.getBoolean("vextornar"));
                     
                     user.setProdpendientes(rs.getBoolean("vpendienteprod"));
                     user.setMermas(rs.getBoolean("vmermas"));
                     user.setConsulmermas(rs.getBoolean("vconsulmermas"));
                     user.setSucursalida(rs.getBoolean("vsucursalida"));
                     user.setSucurentrada(rs.getBoolean("vsucurentrada"));
                     user.setConsultasucur(rs.getBoolean("vsucurconsultar"));
                     
                 

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
return user;
}
public void insertar(Usuarios user){
       
        Conexion conexion = new Conexion();
     try{
       
            String insertImageSql = "SELECT * from sp_insertaruser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conexion.getConnection().prepareStatement(insertImageSql);

            ps.setString(1,user.getUsuario());
            ps.setString(2,user.getClave());
            ps.setLong(3, user.getIdempleado());
            ps.setBoolean(4,user.isCliente());
            ps.setBoolean(5,user.isProveedor());
            ps.setBoolean(6,user.isApertura());
            ps.setBoolean(7,user.isConsultarrepara());
            ps.setBoolean(8,user.isConsultarventa());
            ps.setBoolean(9,user.isDebe());
            ps.setBoolean(10,user.isCkempleado());
            ps.setBoolean(11,user.isFamilia());
            ps.setBoolean(12,user.isHaber());
            ps.setBoolean(13,user.isIngreso());
            ps.setBoolean(14,user.isInventario());
            ps.setBoolean(15,user.isMovimientos());
            ps.setBoolean(16,user.isNueva());
            ps.setBoolean(17,user.isPendientes());
            ps.setBoolean(18,user.isProductos());
            ps.setBoolean(19,user.isUser());
            ps.setBoolean(20,user.isVender());
            ps.setBoolean(21,user.isExtornar());
            
            ps.setBoolean(22, user.isProdpendientes());
            ps.setBoolean(23, user.isMermas());
            ps.setBoolean(24, user.isConsulmermas());
            ps.setBoolean(25, user.isSucursalida());
            ps.setBoolean(26, user.isSucurentrada());
            ps.setBoolean(27, user.isConsultasucur());

            
//            user.setProdpendientes(rs.getBoolean("vpendienteprod"));
//                     user.setMermas(rs.getBoolean("vmermas"));
//                     user.setConsulmermas(rs.getBoolean("vconsulmermas"));
//                     user.setSucursalida(rs.getBoolean("vsucursalida"));
//                     user.setSucurentrada(rs.getBoolean("vsucurentrada"));
//                     user.setConsultasucur(rs.getBoolean("vsucurconsultar"));
            
            ps.execute();
            ps.close();
            

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
            
     }

}
public void editar(Usuarios user){

  
        
//        Usuarios usuario = user;
        Conexion conexion = new Conexion();

     try{
	
     String sql=("SELECT * from sp_editarusuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");         
       PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
       
            ps.setLong(1,user.getId_usuario());
            ps.setString(2,user.getUsuario());
            ps.setString(3,user.getClave());
            ps.setLong(4, user.getIdempleado());
            ps.setBoolean(5,user.isCliente());
            ps.setBoolean(6,user.isProveedor());
            ps.setBoolean(7,user.isApertura());
            ps.setBoolean(8,user.isConsultarrepara());
            ps.setBoolean(9,user.isConsultarventa());
            ps.setBoolean(10,user.isDebe());
            ps.setBoolean(11,user.isCkempleado());
            ps.setBoolean(12,user.isFamilia());
            ps.setBoolean(13,user.isHaber());
            ps.setBoolean(14,user.isIngreso());
            ps.setBoolean(15,user.isInventario());
            ps.setBoolean(16,user.isMovimientos());
            ps.setBoolean(17,user.isNueva());
            ps.setBoolean(18,user.isPendientes());
            ps.setBoolean(19,user.isProductos());
            ps.setBoolean(20,user.isUser());
            ps.setBoolean(21,user.isVender());
            ps.setBoolean(22,user.isExtornar());
            
            ps.setBoolean(23, user.isProdpendientes());
            ps.setBoolean(24, user.isMermas());
            ps.setBoolean(25, user.isConsulmermas());
            ps.setBoolean(26, user.isSucursalida());
            ps.setBoolean(27, user.isSucurentrada());
            ps.setBoolean(28, user.isConsultasucur());
            ps.execute();
            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
            ps.close();
     
           
        
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            conexion.devolverConexionPool();
            
     }      
            
           
        
 
 

}
public void updatetema(long id,String tema){

    Conexion conexion= new Conexion();
    Statement st=null;
    ResultSet rs=null;
     try{
//	        JOptionPane.showMessageDialog(null,"id"+id);
//                JOptionPane.showMessageDialog(null,"tema"+tema);
      String sql=("SELECT * from sp_actualizartema(?,?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setLong(1, id);
      ps.setString(2, tema);
      rs = ps.executeQuery();
      
       if  (rs.next()){
//            JOptionPane.showMessageDialog(null,"OPERACIÓN EXITOSA");
        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
               conexion.devolverConexionPool();
            }    
 
}
public void eliminar(long id){

    Conexion conexion= new Conexion();
    Statement st=null;
    ResultSet rs=null;
     try{
	 
      String sql=("SELECT * from sp_eliminarusuario(?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setLong(1, id);
      rs = ps.executeQuery();
      
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

public boolean duplicado(long idemple){

    Conexion conexion = new Conexion();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_duplicadousuario(?)"); 
      PreparedStatement ps=conexion.getConnection().prepareStatement(sql);
      ps.setLong(1, idemple);
    
      
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
    
}
