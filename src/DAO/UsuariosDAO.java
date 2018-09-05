/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import Conexion.conectar;
import Conexion.ConexionBD;

import Pojos.UsuarioSingleton;
import Pojos.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
        ConexionBD Cbd = new ConexionBD();
    UsuarioSingleton user=UsuarioSingleton.getintancia();
    
    
     try{
         

          System.out.println("SELECT * from sp_validauser('"+userr+"','"+clave+"')");
          String sql=("SELECT * from sp_validauser(?,?,?)"); 
          PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
          ps.setString(1, userr);
          ps.setString(2, clave);
          ps.setString(3, "escritorio");
          ResultSet rs = Cbd.RealizarConsulta(ps);

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
             
             
             user.setWeb(rs.getBoolean("vweb"));
             user.setCotizacion(rs.getBoolean("vnuecotizacion"));
             user.setBuscar_cot(rs.getBoolean("vbuscotizacion"));
             user.setMant_prod(rs.getBoolean("vmatprod"));
             user.setKardex(rs.getBoolean("vkardex"));
             user.setAct_precios(rs.getBoolean("vactprecio"));
             user.setFacturas(rs.getBoolean("bfactura"));
             user.setBoletas(rs.getBoolean("bboletas"));
             user.setNcredito(rs.getBoolean("bnotacred"));
             user.setNdebito(rs.getBoolean("bnotadeb"));
//                    private boolean prodpendientes;
//    private boolean mermas;
//    private boolean consulmermas;
//    private boolean sucursalida;
//    private boolean sucurentrada;
//    private boolean consultasucur;
        }

      
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally {
            Cbd.desconectar();
     }
     return user;

    }
    public List<Usuarios> mostrar(JTable tabla){
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
      };      
     String titulos[]={"EMPLEADO","USUARIO"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
//     tabla.getColumnModel().getColumn(0).setMaxWidth(0);
//     tabla.getColumnModel().getColumn(0).setMinWidth(0);
//     tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
    
      List<Usuarios> listuser= new ArrayList<>();
     
    try{
	
      
        String sql=("SELECT * from sp_mostrarusuario()"); 
        PreparedStatement ps = Cbd.conectar().prepareStatement(sql);
        ResultSet rs = Cbd.RealizarConsulta(ps);
        Object datosR[] = new Object[3];
        while (rs.next()){
                    
             Usuarios user = new Usuarios();
             datosR[0] = rs.getObject("vid");

             datosR[1] = rs.getObject("vempleado");

             datosR[2] = rs.getObject("vusuario");
             listuser.add(user);
                    
            modelo.addRow(datosR);
		
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    

            return listuser;
    }
    public List<Usuarios> busquedasensitiva(JTable tabla,String cadena){
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
      //      if (column == 5) return true;
      //else
       return false;
      }
      };      
     String titulos[]={"EMPLEADO","USUARIO"};
     modelo.setColumnIdentifiers(titulos);
     tabla.setModel(modelo);
     

    
      List<Usuarios> listuser= new ArrayList<>();
     
    try{
	
      
        String sql=("SELECT * from sp_busquedasensitivausuario(?)"); 
        PreparedStatement ps =Cbd.conectar().prepareStatement(sql);
        ps.setString(1, cadena);
        ResultSet rs = Cbd.RealizarConsulta(ps);
        
        Object datosR[] = new Object[2];
        while (rs.next()){
            Usuarios user= new Usuarios();
             user.setId_usuario(rs.getLong("id"));
             user.setUsuario( rs.getString("vuser")) ;
             user.setClave(rs.getString("vclave"));
             user.setIdempleado(rs.getLong("vidempleado"));
             user.setNombreempleado(rs.getString("vnombre"));
             user.setRutempleado(rs.getString("vrut"));
             user.setCliente(rs.getBoolean("vcliente"));
             user.setProveedor(rs.getBoolean("vproveedor"));
             user.setApertura(rs.getBoolean("vapertura"));
             user.setConsultarrepara(rs.getBoolean("vconsultarrepara"));
             user.setConsultarventa(rs.getBoolean("vconsultavende"));
             user.setDebe(rs.getBoolean("vdebe"));
             user.setCkempleado(rs.getBoolean("vempleado"));
             user.setFamilia(rs.getBoolean("vfamilia"));
             user.setHaber(rs.getBoolean("vhaber"));
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
             
             user.setWeb(rs.getBoolean("vweb"));
             user.setCotizacion(rs.getBoolean("vnuecotizacion"));
             user.setBuscar_cot(rs.getBoolean("vbuscotizacion"));
             user.setMant_prod(rs.getBoolean("vmatprod"));
             user.setKardex(rs.getBoolean("vkardex"));
             user.setAct_precios(rs.getBoolean("vactprecio"));
             user.setFacturas(rs.getBoolean("bfactura"));
             user.setBoletas(rs.getBoolean("bboletas"));
             user.setNcredito(rs.getBoolean("bnotacred"));
             user.setNdebito(rs.getBoolean("bnotadeb"));
             
             
             
             
             datosR[0] = user.getNombreempleado();
             
             datosR[1] =user.getUsuario();
            
             listuser.add(user);
            modelo.addRow(datosR);
		
        }
        if(modelo.getRowCount()>0){
            tabla.setRowSelectionInterval(0, 0);
        
        }
	ps.close();
        rs.close();
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
            }    
        return listuser;
    
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
    ConexionBD Cbd = new ConexionBD();
   
   
     try{

         System.out.println("SELECT * from sp_busquedauser('"+tipoB+"',"+id+")");
      String sql=("SELECT * from sp_busquedauser(?,?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setString(1, tipoB);
      ps.setLong(2, id);
      ResultSet rs = Cbd.RealizarConsulta(ps);
   

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
                     
                    user.setWeb(rs.getBoolean("vweb"));
                    user.setCotizacion(rs.getBoolean("vnuecotizacion"));
                    user.setBuscar_cot(rs.getBoolean("vbuscotizacion"));
                    user.setMant_prod(rs.getBoolean("vmatprod"));
                    user.setKardex(rs.getBoolean("vkardex"));
                    user.setAct_precios(rs.getBoolean("vactprecio"));
                    user.setFacturas(rs.getBoolean("bfactura"));
                    user.setBoletas(rs.getBoolean("bboletas"));
                    user.setNcredito(rs.getBoolean("bnotacred"));
                    user.setNdebito(rs.getBoolean("bnotadeb"));
                     
                 

        }
      
        ps.close();
        rs.close();
//	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
                Cbd.desconectar();
     }
return user;
}
public void insertar(Usuarios user){
       
     ConexionBD Cbd = new ConexionBD();
     try{
       
            String insertImageSql = "SELECT * from sp_insertaruser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = Cbd.conectar().prepareStatement(insertImageSql);

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
            
            ps.setBoolean(28, user.isWeb());
            ps.setBoolean(29, user.isCotizacion());
            ps.setBoolean(30, user.isBuscar_cot());
            ps.setBoolean(31, user.isMant_prod());
            ps.setBoolean(32, user.isKardex());
            ps.setBoolean(33, user.isAct_precios());
            ps.setBoolean(34, user.isFacturas());
            ps.setBoolean(35, user.isBoletas());
            ps.setBoolean(36, user.isNcredito());
            ps.setBoolean(37, user.isNdebito());
   
//            
            Cbd.actualizarDatos(ps);
            ps.close();
            

        }
     catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
           Cbd.desconectar();
            
     }

}
public void editar(Usuarios user){

  
        
//        Usuarios usuario = user;
     
    ConexionBD Cbd = new ConexionBD();
     try{
	
     String sql=("SELECT * from sp_editarusuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");         
       PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
       
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
            ps.setBoolean(29, user.isWeb());
            ps.setBoolean(30, user.isCotizacion());
            ps.setBoolean(31, user.isBuscar_cot());
            ps.setBoolean(32, user.isMant_prod());
            ps.setBoolean(33, user.isKardex());
            ps.setBoolean(34, user.isAct_precios());
            ps.setBoolean(35, user.isFacturas());
            ps.setBoolean(36, user.isBoletas());
            ps.setBoolean(37, user.isNcredito());
            ps.setBoolean(38, user.isNdebito());
           Cbd.actualizarDatos(ps);
            JOptionPane.showMessageDialog(null,"Usuario editado con exito");
            ps.close();
     
           
        
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
           Cbd.desconectar();
            
     }      
            
           
        
 
 

}
public void updatetema(long id,String tema){

    ConexionBD Cbd = new ConexionBD();
    Statement st=null;
    ResultSet rs=null;
     try{
//	        JOptionPane.showMessageDialog(null,"id"+id);
//                JOptionPane.showMessageDialog(null,"tema"+tema);
      String sql=("SELECT * from sp_actualizartema(?,?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setLong(1, id);
      ps.setString(2, tema);
      Cbd.actualizarDatos(ps);
      
       
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
              Cbd.desconectar();
            }    
 
}
public void eliminar(long id){

   ConexionBD Cbd = new ConexionBD();
    Statement st=null;
    ResultSet rs=null;
     try{
	 
      String sql=("SELECT * from sp_eliminarusuario(?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setLong(1, id);
      Cbd.actualizarDatos(ps);
      
//       if  (rs.next()){
            JOptionPane.showMessageDialog(null,"Usuario eliminado con exito");
//        }
	
        } catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
              Cbd.desconectar();
            }    
 
    
 

}

public boolean duplicado(long idemple){

    ConexionBD Cbd = new ConexionBD();
    ResultSet rs=null;
    boolean valida=false;
     try{
//	st= (Statement)miconexion.createStatement();
      
      String sql=("SELECT * from sp_duplicadousuario(?)"); 
      PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
      ps.setLong(1, idemple);
    
      
      rs = Cbd.RealizarConsulta(ps);
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
            Cbd.desconectar();
     
     }
     return valida;

    }
    
}
