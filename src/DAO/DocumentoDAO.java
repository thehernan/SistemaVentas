/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ClasesGlobales.FormatoNumerico;
import Conexion.ConexionBD;
import Pojos.SucursalSingleton;
import Pojos.Ventas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HERNAN
 */
public class DocumentoDAO {
   
    SucursalSingleton sucursal = SucursalSingleton.getinstancia();
    FormatoNumerico fn= new FormatoNumerico();
    
    public List<Ventas> mostrar(long iddoc,String cadena,String op,Timestamp desde, Timestamp hasta,JTable tab)
    {
        ConexionBD Cbd = new ConexionBD();
        DefaultTableModel tabla=new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
           //      if (column == 5) return true;
        //else
        return false;
        }
        }; 
        String titulos[]={"Fecha","Documento","Razon Social","TM","Total","Estado"};
        tabla.setColumnIdentifiers(titulos);
        tab.setModel(tabla);
        List<Ventas> listvent=new ArrayList<>();
       DateFormat df = DateFormat.getDateInstance();
        try{

            System.out.println("SELECT * from sp_busquedasensitivadoc('"+cadena+"','"+sucursal.getId()+"','"+desde+"','"+hasta+"','"+op+"','"+iddoc+"')");
            String sql=("SELECT * from sp_busquedasensitivadoc(?,?,?,?,?,?)"); 
            PreparedStatement ps= Cbd.conectar().prepareStatement(sql);
            ps.setString(1, cadena);
            ps.setLong(2, sucursal.getId());
            ps.setTimestamp(3, desde);
            ps.setTimestamp(4, hasta);
            ps.setString(5, op);
            ps.setLong(6, iddoc);
            ResultSet rs= Cbd.RealizarConsulta(ps);
            Object datosR[] = new Object[6];
           
         
            while (rs.next()){
                Ventas vent= new Ventas();
                vent.setIdventa(rs.getLong("vidventa"));
                vent.setTotal(rs.getDouble("vtotal"));
                vent.setNumero(rs.getString("vnumero"));
                vent.setSerie(rs.getString("vserie"));
                vent.setDescuento(0.0);
                vent.setMotivodescuento("");
                vent.setIdcliente(rs.getLong("vidcliente"));
                
                 datosR[0] = rs.getObject("vfecha");

                 datosR[1] = vent.getSerie()+" - "+vent.getNumero()+" Ref. "+rs.getString("vcomprobateref");

                 datosR[2] = rs.getString("vrazons");

                 datosR[3] = rs.getObject("vabreviatura");

                 datosR[4] =fn.FormatoN(vent.getTotal());

                 datosR[5] =(rs.getObject("vestadosunat"));

               

                listvent.add(vent);
                tabla.addRow(datosR);

              
            }

            tab.setModel(tabla); 
            TableColumnModel columnModel = tab.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(1).setPreferredWidth(80);
            columnModel.getColumn(2).setPreferredWidth(400);
            columnModel.getColumn(3).setPreferredWidth(80);
            columnModel.getColumn(4).setPreferredWidth(80);
            columnModel.getColumn(5).setPreferredWidth(80);
            if(tabla.getRowCount()>0)
            {
                tab.setRowSelectionInterval(0, 0);
            }
            rs.close();
            ps.close();
//            if(cont == 0){
//                JOptionPane.showMessageDialog(null,"No se encontrarón documentos en las fechas "+df.format(desde)+" , "+df.format(hasta)
//                );
//            }
            } catch(Exception e)
                {
                JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
                   Cbd.desconectar();

                }    
    return listvent;
    }
    
    public void imprimir(long iddoc,String cadena,String op,Timestamp desde, Timestamp hasta)
  {

        ConexionBD Cbd = new ConexionBD();
        try
        {

            String  rutaInforme  = "src/Reportes/DocumentoRango.jasper";
            
            Map parametros = new HashMap();
           
            java.util.Locale locale = new Locale( "en", "US" );
            parametros.put( JRParameter.REPORT_LOCALE, locale );
            parametros.put("cadena",cadena);
            parametros.put("idsucursal",sucursal.getId());
            parametros.put("desde",desde);
            parametros.put("hasta",hasta);
            parametros.put("op",op);
            parametros.put("iddoc",iddoc);
            
            
           //se procesa el archivo jasper
           JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,Cbd.conectar());
           //impresion de reporte
           // TRUE: muestra la ventana de dialogo "preferencias de impresion"  /// no vista previa
//           JasperPrintManager.printReport(informe, true);     
           
           
           
            //vista previa ////
            JasperViewer jv = new JasperViewer(informe,false);  
        
             jv.setVisible(true);
             jv.setTitle(rutaInforme);
          //////////////////////////////////////
         }
         catch (JRException ex)
         {
           System.err.println( "Error iReport: " + ex.getMessage() );
         }finally{
            Cbd.desconectar();
        }

  }
    
    
}
