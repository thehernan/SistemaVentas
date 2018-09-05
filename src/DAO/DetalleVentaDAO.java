/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author info2017
 */
public class DetalleVentaDAO {
    
    
    
//    public void insertar(List<Producto> listprod,long idventa,JLabel mensa,JLabel anima){
//        ConexionBD Cbd = new ConexionBD();
//          Runnable miRunnable = new Runnable()
//      {
//         @Override
//         public void run()
//         {
//             VentasDAO daoventa = new VentasDAO();
//             //        List<Object> retur= new ArrayList<>();
//               
//                Statement st=null;
//                ResultSet rs=null;
//                long id=0;
//                anima.setVisible(true);
//             try{
//                 for(Producto prod :listprod ){
//        //          System.out.println("SELECT * from sp_insertardetalleventa("+detventa.getIdproducto()+","+detventa.getPrecio()+","+detventa.getCantidad()+","+detventa.getIdventa()+")");
//                String sql=("SELECT * from sp_insertardetalleventa(?,?,?,?)"); 
//                PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
//                ps.setLong(1, prod.getIdproducto());
//                ps.setBigDecimal(2, new BigDecimal(prod.getPrecio()));
//                ps.setBigDecimal(3, new BigDecimal(prod.getCantidad()));
//                ps.setLong(4, idventa);
//                
//                Cbd.actualizarDatos(ps);
//                mensa.setText("Vendiendo Cod.: "+prod.getCodigo());
//               
//                 }
//                 
//
//                 mensa.setText("Imprimiendo Ticket ...");
//                 daoventa.imprimirticketcaja(idventa);
//                 mensa.setText("");
//                 mensa.setVisible(false);
//                 anima.setVisible(false);
//                }
//             catch(Exception e)
//                    {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
//                    }finally{
//                    Cbd.desconectar();
//             
//             }           
//             
//         }
//         };
//        Thread hilo = new Thread (miRunnable);
//        hilo.start();
// 
//}
//    public void eliminar(Producto prod){
//    
//        ConexionBD Cbd = new ConexionBD();
//        Statement st=null;
//        ResultSet rs=null;
////        boolean valida=false;
//     try{
//        
//         
//        String sql=("SELECT * from sp_eliminardetventa(?,?)"); 
//        
//        PreparedStatement ps=Cbd.conectar().prepareStatement(sql);
//      
//        ps.setBigDecimal(1,new BigDecimal(prod.getCantidad()));
//        ps.setLong(2, prod.getIdproducto());
//        Cbd.actualizarDatos(ps);
//         
//     
//      
////        if  (rs.next()){
////            //JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
////
//// 
////        }
//
//        }
//     catch(Exception e)
//            {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            }finally{
//            Cbd.desconectar();
//     
//     }
//    
//    }

}
