/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import ClasesGlobales.FormatoNumerico;
import Conexion.ConexionBD;
import Pojos.DetalleCompras;
import Pojos.Producto;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author info2017
 */
public class DetalleComprasDAO {
    
    FormatoNumerico fn = new FormatoNumerico();
    ProductoDAO proddao = new ProductoDAO();
     public void insertar(List<DetalleCompras> compras,long idcompra,JLabel mes){
         Runnable miRunnable = new Runnable()
      {
         @Override
         public void run()
         {  
              ConexionBD Cbd = new ConexionBD();
             int i = 1;
              System.out.println("com"+compras.size());
              Producto prod ;
            
             //Iterator<DetalleCompras> it= compras.iterator();
            try{

               for(DetalleCompras det: compras){
            
              if (det.getIddetallecompra()==0){
                  //////////////////// insertar detallee/////////////////
                String insertImageSql = "SELECT * from sp_insertardetallecompra(?,?,?,?,?,?)";
            
                PreparedStatement ps = Cbd.conectar().prepareStatement(insertImageSql);
                mes.setText("Insertando Item "+i);
                i++;
                ps.setLong(1,det.getIdproducto());
                System.out.print("idprodinsert"+det.getIdproducto());
                System.out.print("insertcantidad"+det.getCantidad());
                System.out.print("insertprecio"+det.getPrecio());
                
                ps.setBigDecimal(2,new BigDecimal(det.getCantidad()));
                ps.setBigDecimal(3,new BigDecimal(det.getPrecio()));
                ps.setLong(4,idcompra);
                ps.setBigDecimal(5,new  BigDecimal(det.getCantidadacord()));
                ps.setString(6, det.getUnidmed());
                Cbd.actualizarDatos(ps);
                //////////////////// editar precios /////////////////////////////////
                
                prod = new Producto();
                prod.setPrecio(det.getPrecio1());
                prod.setPrecio1(det.getPrecio2());
                prod.setPrecio2(det.getPrecio3());
                prod.setPrecio3(det.getPrecio4());
                prod.setPrecioc(det.getPrecio());
                prod.setIdproducto(det.getIdproducto());
                proddao.actualizaprecios(prod);
  
              }
            
            }

            }
            catch(Exception e)
                   {
                   JOptionPane.showMessageDialog(null, e.getMessage());
                   }finally{
                   Cbd.desconectar();
            }
             
             
             mes.setText(""); 
             
             
             
         }
    };
      Thread hilo = new Thread (miRunnable);
      hilo.start();
      
      
        

}
     
public boolean eliminar(long iddet,long idprod){
  
         ConexionBD Cbd = new ConexionBD();
        Statement st=null;
        ResultSet rs=null;
        boolean valida=true;
     try{
        
        String sql=("SELECT * from sp_eliminardetallecompra(?,?)"); 
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, idprod);
        ps.setLong(2, iddet);
        
        rs = Cbd.RealizarConsulta(ps);
      
        if  (rs.next()){
            
           valida=rs.getBoolean("valida");
            if(valida==false){  
             JOptionPane.showMessageDialog(null,"NO CUENTA CON STOCK, IMPOSIBLE DE ELIMINAR ITEM");
            }
 
        }

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
  return valida;
}
public void editar(DetalleCompras detcompra){
  
       ConexionBD Cbd = new ConexionBD();
        Statement st=null;
        ResultSet rs=null;
       
     try{
        
        String sql=("SELECT * from sp_editardetallecompra(?,?,?,?,?)"); 
        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
        ps.setLong(1, detcompra.getIddetallecompra());
        ps.setBigDecimal(2,new BigDecimal(detcompra.getCantidad()));
        ps.setBigDecimal(3,new  BigDecimal(detcompra.getPrecio()));
        ps.setBigDecimal(4,new  BigDecimal(detcompra.getCantidadacord()));
        ps.setLong(5, detcompra.getIdproducto());
        
        rs = Cbd.RealizarConsulta(ps);
      
        if  (rs.next()){
            
           
             JOptionPane.showMessageDialog(null,"Editado con exito","",JOptionPane.INFORMATION_MESSAGE);
           
 
        }

        }
     catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();
     }
  
}   
public void mostrar(JTable tabla,long idcompra){
         ConexionBD Cbd = new ConexionBD();
        DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
        //      if (column == 5) return true;
        //else
         return false;
        }
        };
        String titulos[]={"Codigo","Producto","Precio unid.","Cant. Llego","Cant. Acordada"};
        modelo.setColumnIdentifiers(titulos);
        tabla.setModel(modelo);
        Object datosR[] = new Object[5];
        
      
                
        PreparedStatement ps= null;
        ResultSet rs= null;
       
        try{
	
        ps = Cbd.conectar().prepareStatement("SELECT * from sp_mostrardetallecompras(?)");
        ps.setLong(1,idcompra);
     
        rs=Cbd.RealizarConsulta(ps);
      
        while (rs.next()){
            
            datosR[0]=rs.getString("vcodigo");
            datosR[1]=rs.getString("vdescripcion");
            datosR[2]=fn.FormatoN(rs.getDouble("vprecio"));
            datosR[3]=fn.FormatoN(rs.getDouble("vcantidad"));
            datosR[4]=fn.FormatoN(rs.getDouble("vcantidadacordada"));
          
            modelo.addRow(datosR);
           
        }
           TableColumnModel columnModel = tabla.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(450);
            columnModel.getColumn(2).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(50);
	
	
	
        } catch(SQLException | HeadlessException e)
            {
            JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            Cbd.desconectar();

            }
       
  

}
    
}
