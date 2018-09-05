/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.VentasDAO;
import ClasesGlobales.ImagenFondo;
import ClasesGlobales.LookAndFeel;
import Conexion.TestConexionServer;
import DAO.CajaDAO;
import DAO.ResumenDAO;
import DAO.SucursalDAO;
import DAO.UsuariosDAO;
import Pojos.Caja;
import Pojos.EmpleadoSingleton;
import Pojos.Resumen;
import Pojos.Sucursal;
import Pojos.SucursalSingleton;
import Pojos.UsuarioSingleton;
import Pojos.Ventas;
import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
 

final class MDIMenu extends javax.swing.JFrame {

    /**
     * Creates new form MDIMenu
     */
    VentasDAO daoventa = new VentasDAO();
    Ventas venta = new Ventas();
//    Usuarios user;
    UsuarioSingleton user= UsuarioSingleton.getintancia();
    EmpleadoSingleton empleadosingleton= EmpleadoSingleton.getinstancia();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    UsuariosDAO daousuario=new UsuariosDAO();
    int agrandarresumen=0;
//    boolean disminuirresumen = false;
//    Empleado empleadologin;
    
//    public MDIMenu() {
//        initComponents();
//        this.setExtendedState(MAXIMIZED_BOTH);
//       
//    }
     private Dimension dim;
     LookAndFeel look = new LookAndFeel();
     ///////////////////////
       java.util.Calendar calendario; 
    int dia, mes, año, hora, minutos, segundos; 
    //////////////////////
    ResumenDAO daores = new ResumenDAO();
     SucursalDAO daosucur= new SucursalDAO();
     Sucursal sucur = new Sucursal();
     List<Sucursal> list;
    int posx;
    int posy; 
     public MDIMenu() {
        initComponents();

      
        
        try {
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/codprod.png")).getImage());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no se encontro icono");
        }

        ///ADAPTAR AL TAMAÑO DE PANTALLA
//        dim=super.getToolkit().getScreenSize();
//        super.setSize(dim);
       
//        super.setUndecorated(true);
//        super.setVisible(true);
        //////////////////////////////////////
//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setExtendedState(EXIT_ON_CLOSE);
        ///////////////////////////////////////////
        jlbluser.setText(empleadosingleton.getNombre());
        jlblrut.setText(empleadosingleton.getRut());
        jlblsucursal.setText(sucursalsingleton.getNombre()+" - "+sucursalsingleton.getDireccion());
        jlblusercabecera.setText(empleadosingleton.getNombre());
        
        
        
        // xxxxxxxxxxxxxxxxxx
//        this.empleadologin=empleadologin;
        //////////  INTEFARZAS SEGUN PRIVILEGIOS ////////////////
//        cargarinterfazsegunprivilegios();
        //////////////////////////////////////////////////////////
//        jPanel6.setVisible(false);
        
        //xxxxxxxxxxxxxxxxxxxxx
        reloj();
        
        
        
         
//        list=daosucur.llenarcombo(jcbsucursal);
        verresumen();
//        cargarresumendos();
        cargarresumen();
//        jpanelresumen.setOpaque(false);
        pruebaconexion();
//        jpanelresumen.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        
//        jpanelbanner.setBackground(new Color(255,255,255,80));
      
    }
     
      
     
     public void cargarresumen(){

        Resumen res= daores.mostrar(sucursalsingleton.getId());
        
        jpanelproductos.setText(String.valueOf(res.getProdreg()));
        
        jpanelcliente.setText(String.valueOf(res.getClireg()));
        jpanelproveedor.setText(String.valueOf(res.getProvreg()));
        jpanelempleados.setText(String.valueOf(res.getEmplereg()));
        
        
        jlblprodreg.setText("Productos Registrados: "+res.getProdreg());
        jlblprodbajo.setText("Productos con bajo stock: "+res.getProdbajo());
        jlblclireg.setText("Clientes Registrados: "+res.getClireg());
        jlblprovreg.setText("Proveedores Registrados: "+res.getProvreg());
        jlblemplereg.setText("Empleados Registrados: "+res.getEmplereg());
        jlblventaanio.setText("Año: "+res.getFactnio()+" - "+res.getBolnio());
        jlblventames.setText("Mes: "+res.getFactmes()+" - "+res.getBolmes());
        jlblventadia.setText("Dia: "+res.getFactdia()+" - "+res.getBoldia());
        jlblventproc.setText(res.getVentproc());
        jlblempleadomes.setText(res.getEmpleadomes());
        
         DefaultCategoryDataset dataset= new DefaultCategoryDataset();
//         dataset.setValue(2, "", "Enero");
//         dataset.setValue(3, "", "Febrero");
//         dataset.setValue(4, "", "Marzo");
//         dataset.setValue(5, "", "Abril");
//         dataset.setValue(6, "", "Mayo");
//         dataset.setValue(7, "", "Junio");
//         dataset.setValue(9, "", "Julio");
//         dataset.setValue(10, "", "Agosto");
//         dataset.setValue(11, "", "Septiembre");
//         dataset.setValue(12, "", "Octubre");
//         dataset.setValue(13, "", "Noviembre");
//         dataset.setValue(14, "", "Diciembre");
         
//         System.out.println("enero"+res.getVentaenero());
//         System.out.println("enero"+res.getVentafebrero());
//         System.out.println("enero"+res.getVentamarzo());
//         System.out.println("enero"+res.getVentamayo());
//         System.out.println("enero"+res.getVentajunio());
//         System.out.println("enero"+res.getVentajulio());
//         System.out.println("enero"+res.getVentaagosto());
//         System.out.println("enero"+res.getVentasetiembre());
//         System.out.println("enero"+res.getVentaoctubre());
//         System.out.println("enero"+res.getVentanoviembre());
         
         
         
         // FACTURA
         
         dataset.setValue(res.getFactenero(), "Factura", "Enero");
         dataset.setValue(res.getFactfebrero(), "Factura", "Febrero");
         dataset.setValue(res.getFactmarzo(), "Factura", "Marzo");
         dataset.setValue(res.getFactabril(), "Factura", "Abril");
         dataset.setValue(res.getFactmayo(), "Factura", "Mayo");
         dataset.setValue(res.getFactjunio(), "Factura", "Junio");
         dataset.setValue(res.getFactjulio(), "Factura", "Julio");
         dataset.setValue(res.getFactagosto(), "Factura", "Agosto");
         dataset.setValue(res.getFactsetiembre(), "Factura", "Septiembre");
         dataset.setValue(res.getFactoctubre(), "Factura", "Octubre");
         dataset.setValue(res.getFactnoviembre(), "Factura", "Noviembre");
         dataset.setValue(res.getFactdiciembre(), "Factura", "Diciembre");
         // BOLETA
         
         dataset.setValue(res.getBolenero(), "Boleta", "Enero");
         dataset.setValue(res.getBolfebrero(), "Boleta", "Febrero");
         dataset.setValue(res.getBolmarzo(), "Boleta", "Marzo");
         dataset.setValue(res.getBolabril(), "Boleta", "Abril");
         dataset.setValue(res.getBolmayo(), "Boleta", "Mayo");
         dataset.setValue(res.getBoljunio(), "Boleta", "Junio");
         dataset.setValue(res.getBoljulio(), "Boleta", "Julio");
         dataset.setValue(res.getBolagosto(), "Boleta", "Agosto");
         dataset.setValue(res.getBolsetiembre(), "Boleta", "Septiembre");
         dataset.setValue(res.getBoloctubre(), "Boleta", "Octubre");
         dataset.setValue(res.getBolnoviembre(), "Boleta", "Noviembre");
         dataset.setValue(res.getBoldiciembre(), "Boleta", "Diciembre");
         //NOTA CREDITO
         dataset.setValue(res.getNotacenero(), "Nota de Crédito", "Enero");
         dataset.setValue(res.getNotacfebrero(), "Nota de Crédito", "Febrero");
         dataset.setValue(res.getNotacmarzo(), "Nota de Crédito", "Marzo");
         dataset.setValue(res.getNotacabril(), "Nota de Crédito", "Abril");
         dataset.setValue(res.getNotacmayo(), "Nota de Crédito", "Mayo");
         dataset.setValue(res.getNotacjunio(), "Nota de Crédito", "Junio");
         dataset.setValue(res.getNotacjulio(), "Nota de Crédito", "Julio");
         dataset.setValue(res.getNotacagosto(), "Nota de Crédito", "Agosto");
         dataset.setValue(res.getNotacsetiembre(), "Nota de Crédito", "Septiembre");
         dataset.setValue(res.getNotacoctubre(), "Nota de Crédito", "Octubre");
         dataset.setValue(res.getNotacnoviembre(), "Nota de Crédito", "Noviembre");
         dataset.setValue(res.getNotacdiciembre(), "Nota de Crédito", "Diciembre");
         
         //NOTA DEBITO
         dataset.setValue(res.getNotadenero(), "Nota de Débito", "Enero");
         dataset.setValue(res.getNotadfebrero(), "Nota de Débito", "Febrero");
         dataset.setValue(res.getNotadmarzo(), "Nota de Débito", "Marzo");
         dataset.setValue(res.getNotadabril(), "Nota de Débito", "Abril");
         dataset.setValue(res.getNotadmayo(), "Nota de Débito", "Mayo");
         dataset.setValue(res.getNotadjunio(), "Nota de Débito", "Junio");
         dataset.setValue(res.getNotadjulio(), "Nota de Débito", "Julio");
         dataset.setValue(res.getNotadagosto(), "Nota de Débito", "Agosto");
         dataset.setValue(res.getNotadsetiembre(), "Nota de Débito", "Septiembre");
         dataset.setValue(res.getNotadoctubre(), "Nota de Débito", "Octubre");
         dataset.setValue(res.getNotadnoviembre(), "Nota de Débito", "Noviembre");
         dataset.setValue(res.getNotaddiciembre(), "Nota de Débito", "Diciembre");
         ////
         
         
         JFreeChart chart = ChartFactory.createBarChart("", "", "", dataset,PlotOrientation.VERTICAL,true,true,false);
         CategoryPlot catplot= chart.getCategoryPlot();
         catplot.setRangeGridlinePaint(Color.BLACK);
         ChartPanel chatpanel= new ChartPanel(chart);
         chatpanel.setBackground(Color.BLUE);
         chatpanel.setToolTipText("Facturas");
        
         jpanelgrafico.removeAll();
       
         jpanelgrafico.add(chatpanel,BorderLayout.CENTER);
       
         jpanelgrafico.validate();
           
//        
//        if(sucur.getId()!=0){
//        jbtnimprimir.setEnabled(true);
//        }else {
//        jbtnimprimir.setEnabled(false);
//        }
     }
//     public void cargarresumendos(){
//        
//        Resumen res= daores.mostrar(sucursalsingleton.getId());
//        jpanelproductos.setText(String.valueOf(res.getProdreg()));
//        
//        jpanelcliente.setText(String.valueOf(res.getClireg()));
//        jpanelproveedor.setText(String.valueOf(res.getProvreg()));
//        jpanelempleados.setText(String.valueOf(res.getEmplereg()));
//        
//      
//
//     }
     
//     
     public void verresumen(){
         boolean ver=false;
     if(user.isCliente()==true && user.isProveedor()==true && user.isCkempleado()==true && user.isUseri()==true && 
             user.isIngreso()==true && user.isProductos()==true &&
                  user.isInventario()==true &&  user.isFamilia()==true && user.isVender()
                     && user.isConsultarventa()==true && user.isApertura() ==true &&
                     user.isMovimientos()==true && user.isNueva()==true && user.isConsultarrepara()
                     && user.isPendientes()==true 
                     && user.isExtornar()==true && user.isProdpendientes()==true && user.isMant_prod()==true &&
             user.isKardex()==true && user.isAct_precios()==true && user.isFacturas()==true && user.isBoletas() &&
             user.isNcredito()==true && user.isNdebito()==true && user.isCotizacion()==true && user.isBuscar_cot()==true){
     ver=true;
     
     }else {
     ver=false;
     }
//     jlbltitulo.setVisible(ver);
//     jcbsucursal.setVisible(ver);
     jlblprodreg.setVisible(ver);
     jlblprodbajo.setVisible(ver);
     jlblclireg.setVisible(ver);
     jlblprovreg.setVisible(ver);
     jlblemplereg.setVisible(ver);
     jlblventaanio.setVisible(ver);
     jlblventames.setVisible(ver);
     jlblventadia.setVisible(ver);
     jlblventproc.setVisible(ver);
     jlblempleadomes.setVisible(ver);
//     jbtnimprimir.setVisible(ver);
     jbtnactualizar.setVisible(ver);
     jpanelgrafico.setVisible(ver);
     
     
     }
     
    public void menuusuario(){              
         JLabel titulo = new JLabel("USUARIO");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
          titulo.setForeground(new Color(255,255,255));
         ////////////////////////////////////////////////////////
         if(user.isUseri()==true){
         JButton usuari= new JButton("USUARIOS");
         usuari.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  panelconmin();
                   JIFUsuarios user = new JIFUsuarios();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = user.getSize();
                int x=(jDesktop.getWidth()/2)-user.getWidth()/2;
                int y=(jDesktop.getHeight()/2)-user.getHeight()/2;
                user.setLocation(x,y);
                user.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    user.getUI()).setNorthPane(null);
                jDesktop.add(user);
                user.show();
                
//                 jlblmensajeinfo.setText("");
             }
         });
//         usuari.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         
         
         jpanelcontenedor.add(usuari);
         usuari.setBounds(20, 70, 200, 50);
         
         usuari.setBorderPainted(false);
         usuari.setContentAreaFilled(false);
         usuari.setOpaque(true);
         usuari.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         usuari.setBackground(new java.awt.Color(255,255,255));
         usuari.setForeground(new java.awt.Color(34,75,139));
//         usuari.setForeground(Color.WHITE);
         
         }
         /////////////////////////////////////////////////////////
           JButton cerrar= new JButton("CERRAR SESION");
         cerrar.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(JOptionPane.showConfirmDialog(null, "¿Seguro de cerrar sesión?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            int componentes=jDesktop.getComponentCount();
            System.out.println("ventanas "+componentes);
            if(componentes==12){
                user.getdestruir();
                empleadosingleton.getdestruir();
                sucursalsingleton.getdestruir();
                MDIMenu.this.dispose();
                Login nuevaSesion = new Login();
                AWTUtilities.setWindowOpaque(nuevaSesion, false);
                nuevaSesion.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null, "Imposible cerrar sesión, cierre todas las ventanas abiertas e intente nuevamente","",JOptionPane.INFORMATION_MESSAGE);
            }

        }
                 
             }
         });
         
         
         jpanelcontenedor.add(cerrar);
         cerrar.setBounds(20, 140, 200, 50);
         cerrar.setBorderPainted(false);
         cerrar.setContentAreaFilled(false);
         cerrar.setOpaque(true);
         cerrar.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         cerrar.setBackground(new java.awt.Color(255, 204, 51));
         
        
     
     }
    
     public void menumantenimiento(){
         JLabel titulo = new JLabel("MANT. REGISTRO");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
          titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isCliente()==true){
         JButton client= new JButton("CLIENTES");
         client.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JIFCliente cliente = new JIFCliente(MDIMenu.this);
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = cliente.getSize();
                cliente.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    cliente.getUI()).setNorthPane(null);
                jDesktop.add(cliente);
                cliente.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
//          client.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         
         jpanelcontenedor.add(client);
         client.setBounds(20, 70, 200, 50);
       
         client.setBorderPainted(false);
         client.setContentAreaFilled(false);
         client.setOpaque(true);
         client.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         client.setBackground(new java.awt.Color(255, 204, 51));
         
         
         }
         /////////////////////////////////////////////////////////
         if(user.isProveedor()==true){
        JButton proveed= new JButton("PROVEEDORES");
         proveed.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JIFProveedor proveedor = new JIFProveedor(MDIMenu.this);
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = proveedor.getSize();
                proveedor.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    proveedor.getUI()).setNorthPane(null);
                jDesktop.add(proveedor);
                proveedor.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
//         proveed.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         
         jpanelcontenedor.add(proveed);
         proveed.setBounds(20, 140, 200, 50);
         proveed.setBorderPainted(false);
         proveed.setContentAreaFilled(false);
         proveed.setOpaque(true);
         proveed.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         proveed.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
         if(user.isCkempleado()==true){
             JButton emplead= new JButton("EMPLEADOS");
         emplead.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JIFEmpleados empleado= new JIFEmpleados(MDIMenu.this);
              Dimension desktopSize = jDesktop.getSize();
              Dimension FrameSize = empleado.getSize();
              empleado.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    empleado.getUI()).setNorthPane(null);
              jDesktop.add(empleado);
              empleado.show();
               panelconmin();
//                jlblmensajeinfo.setText("");   
             }
         });
        
         
         jpanelcontenedor.add(emplead);
         emplead.setBounds(20, 210, 200, 50);
         
          
         emplead.setBorderPainted(false);
         emplead.setContentAreaFilled(false);
         emplead.setOpaque(true);
         emplead.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         emplead.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         /////////////////
         
     
     }
     public void menuproducto(){
         JLabel titulo = new JLabel("PRODUCTO");
         jpanelcontenedor.add(titulo);
         titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isIngreso()==true){
         JButton ingreso= new JButton("INGRESO");
         ingreso.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   JIFIngresoProducto Ingreprod= new JIFIngresoProducto();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = Ingreprod.getSize();
                Ingreprod.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    Ingreprod.getUI()).setNorthPane(null);
                jDesktop.add(Ingreprod);
                Ingreprod.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
           
         
         jpanelcontenedor.add(ingreso);
         ingreso.setBounds(20, 70, 200, 40);
          ingreso.setBorderPainted(false);
         ingreso.setContentAreaFilled(false);
         ingreso.setOpaque(true);
         ingreso.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         ingreso.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isMant_prod()==true){
        JButton produc= new JButton("MANT. PRODUCTOS");
         produc.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIProductos producto= new JIProductos(MDIMenu.this);
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = producto.getSize();
                producto.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    producto.getUI()).setNorthPane(null);
                jDesktop.add(producto);
                producto.show();
                 panelconmin();
//                 jlblmensajeinfo.setText("");
             }
         });
          
         
         jpanelcontenedor.add(produc);
         produc.setBounds(20, 120, 200, 40);
         
          produc.setBorderPainted(false);
         produc.setContentAreaFilled(false);
         produc.setOpaque(true);
         produc.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         produc.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
         if(user.isProductos()==true){
             JButton prod= new JButton("PRODUCTOS");
         prod.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               JIFrmBuscarProductos inventario= new JIFrmBuscarProductos();
              Dimension desktopSize = jDesktop.getSize();
              Dimension FrameSize = inventario.getSize();
              inventario.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    inventario.getUI()).setNorthPane(null);
              jDesktop.add(inventario);
              inventario.show();
              panelconmin();
                 
             }
         });
          
         
         
         jpanelcontenedor.add(prod);
         prod.setBounds(20, 170, 200, 40);
         
          prod.setBorderPainted(false);
         prod.setContentAreaFilled(false);
         prod.setOpaque(true);
         prod.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         prod.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         if(user.isFamilia()==true){
           JButton famili= new JButton("FAMILIAS");
         famili.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   JIFFAmilia familia= new JIFFAmilia();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = familia.getSize();
                 familia.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    familia.getUI()).setNorthPane(null);
                 jDesktop.add(familia);
                 familia.show();
                  panelconmin();
//                   jlblmensajeinfo.setText("");
             }
         });
        
         
         jpanelcontenedor.add(famili);
         famili.setBounds(20, 220, 200, 40);
          famili.setBorderPainted(false);
         famili.setContentAreaFilled(false);
         famili.setOpaque(true);
         famili.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         famili.setBackground(new java.awt.Color(255, 204, 51));
         }
         //////////////////////////////////////////////////////////
         if(user.isProdpendientes()==true){
         JButton pendient= new JButton("PENDIENTES");
         pendient.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   JIFIngresoProdPendiente ingresopend= new JIFIngresoProdPendiente();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = ingresopend.getSize();
                 ingresopend.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                  ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    ingresopend.getUI()).setNorthPane(null);
                 jDesktop.add(ingresopend);
                 ingresopend.show();
                  panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
          
         
         jpanelcontenedor.add(pendient);
         pendient.setBounds(20, 270, 200, 40);
         
          pendient.setBorderPainted(false);
         pendient.setContentAreaFilled(false);
         pendient.setOpaque(true);
         pendient.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         pendient.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
//         if(user.isMermas()==true){
//            JButton merm= new JButton("MERMA");
//         merm.addActionListener(new ActionListener() {
//
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    JIFMerma merma= new JIFMerma();
//                 Dimension desktopSize = jDesktop.getSize();
//                 Dimension FrameSize = merma.getSize();
//                 merma.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
//                  ((javax.swing.plaf.basic.BasicInternalFrameUI) 
//                    merma.getUI()).setNorthPane(null);
//                 jDesktop.add(merma);
//                 merma.show();
//                  panelconmin();
////                  jlblmensajeinfo.setText("");
//             }
//         });
//          
//         
//         jpanelcontenedor.add(merm);
//         merm.setBounds(20, 320, 200, 40);
//         
//          merm.setBorderPainted(false);
//         merm.setContentAreaFilled(false);
//         merm.setOpaque(true);
//         merm.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
//         merm.setBackground(new java.awt.Color(255, 204, 51));
//         }
         /////////////////////////////////////////////
//         if(user.isConsulmermas()==true){
//          JButton conmerma= new JButton("CONSULT. MERMA");
//         conmerma.addActionListener(new ActionListener() {
//
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                  JIFConsultarMerma consulmerma= new JIFConsultarMerma();
//                 Dimension desktopSize = jDesktop.getSize();
//                 Dimension FrameSize = consulmerma.getSize();
//                 consulmerma.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
//                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
//                    consulmerma.getUI()).setNorthPane(null);
//                 jDesktop.add(consulmerma);
//                 consulmerma.show();
//                  panelconmin();
////                  jlblmensajeinfo.setText("");
//             }
//         });
//         
//         
//         jpanelcontenedor.add(conmerma);
//         conmerma.setBounds(20, 370, 200, 40);
//         
//          conmerma.setBorderPainted(false);
//         conmerma.setContentAreaFilled(false);
//         conmerma.setOpaque(true);
//         conmerma.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
//         conmerma.setBackground(new java.awt.Color(255, 204, 51));
//         ///////////////////////////////////////////////////////////
//         }
         if(user.isKardex()==true){
          JButton conmerma= new JButton("KARDEX");
         conmerma.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIFKardexProducto kardex= new JIFKardexProducto();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = kardex.getSize();
                 kardex.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    kardex.getUI()).setNorthPane(null);
                 jDesktop.add(kardex);
                 kardex.show();
                  panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         
         jpanelcontenedor.add(conmerma);
         conmerma.setBounds(20, 320, 200, 40);
         
         conmerma.setBorderPainted(false);
         conmerma.setContentAreaFilled(false);
         conmerma.setOpaque(true);
         conmerma.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         conmerma.setBackground(new java.awt.Color(255, 204, 51));
         ///////////////////////////////////////////////////////////
         }
         if(user.isInventario()==true){
          JButton jbtninven= new JButton("INVENTARIO");
         jbtninven.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIFInventario invent= new JIFInventario();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = invent.getSize();
                 invent.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    invent.getUI()).setNorthPane(null);
                 jDesktop.add(invent);
                 invent.show();
                  panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         
         jpanelcontenedor.add(jbtninven);
         jbtninven.setBounds(20, 370, 200, 40);
         jbtninven.setBorderPainted(false);
         jbtninven.setContentAreaFilled(false);
         jbtninven.setOpaque(true);
         jbtninven.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         jbtninven.setBackground(new java.awt.Color(255, 204, 51));
         ///////////////////////////////////////////////////////////
         }
         if(user.isAct_precios()==true){
          JButton jbtnactprecio= new JButton("ACT. PRECIOS");
         jbtnactprecio.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIFActualizarPrecio actprecio= new JIFActualizarPrecio();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = actprecio.getSize();
                 actprecio.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    actprecio.getUI()).setNorthPane(null);
                 jDesktop.add(actprecio);
                 actprecio.show();
                  panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         
         jpanelcontenedor.add(jbtnactprecio);
         jbtnactprecio.setBounds(20, 420, 200, 40);
         jbtnactprecio.setBorderPainted(false);
         jbtnactprecio.setContentAreaFilled(false);
         jbtnactprecio.setOpaque(true);
         jbtnactprecio.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         jbtnactprecio.setBackground(new java.awt.Color(255, 204, 51));
         ///////////////////////////////////////////////////////////
         }
     
     }
     
     
     
     public void menuventa(){
         JLabel titulo = new JLabel("VENTA");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
          titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isVender()==true){
         JButton newventa= new JButton("NUEV. VENTA");
         newventa.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates./
                JIFVenta Nventa = new JIFVenta(MDIMenu.this);
            Dimension desktopSize = jDesktop.getSize();
            Dimension FrameSize = Nventa.getSize();
            Nventa.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                Nventa.getUI()).setNorthPane(null);
            jDesktop.add(Nventa);
            Nventa.show();
            panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
          
         
         jpanelcontenedor.add(newventa);
         newventa.setBounds(20, 70, 200, 50);
         
         newventa.setBorderPainted(false);
         newventa.setContentAreaFilled(false);
         newventa.setOpaque(true);
         newventa.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         newventa.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isExtornar()==true){
        JButton proceso= new JButton("EN PROCESO");
         proceso.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   JIFEnProceso enproceso = new JIFEnProceso();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = enproceso.getSize();
                enproceso.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    enproceso.getUI()).setNorthPane(null);
                jDesktop.add(enproceso);
                enproceso.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         jpanelcontenedor.add(proceso);
         proceso.setBounds(20, 140, 200, 50);
         
         proceso.setBorderPainted(false);
         proceso.setContentAreaFilled(false);
         proceso.setOpaque(true);
         proceso.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         proceso.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
         if(user.isConsultarventa()==true){
             JButton consventa= new JButton("CONS. VENTA");
         consventa.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JIFVentaConsultar ventaconsultar= new JIFVentaConsultar();
              Dimension desktopSize = jDesktop.getSize();
              Dimension FrameSize = ventaconsultar.getSize();
              ventaconsultar.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    ventaconsultar.getUI()).setNorthPane(null);
              jDesktop.add(ventaconsultar);
              ventaconsultar.show();
               panelconmin();
//                jlblmensajeinfo.setText("");  
             }
         });
          
         
         jpanelcontenedor.add(consventa);
         consventa.setBounds(20, 210, 200, 50);
          consventa.setBorderPainted(false);
         consventa.setContentAreaFilled(false);
         consventa.setOpaque(true);
         consventa.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         consventa.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         /////////////////
         
     
     }
    
     //////////////////
     
      public void menucaja(){
         JLabel titulo = new JLabel("CAJA");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isApertura()==true){
         JButton apertur= new JButton("APERTURA CAJA");
         apertur.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   CajaDAO daocaja= new CajaDAO();
                    Caja caja;
                    caja=daocaja.validapertura(user.getIdempleado());
                    if(caja.getId_caja()==0){
                        JDAperturaCaja aperturaC = new JDAperturaCaja(new java.awt.Frame(), isVisible(),MDIMenu.this);
                        aperturaC.setVisible(true);
                    }else{
                        JIFCaja ncaja= new JIFCaja(caja,MDIMenu.this);
                        
                        Dimension desktopSize = jDesktop.getSize();
                        Dimension FrameSize = ncaja.getSize();
                        ncaja.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                        ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        ncaja.getUI()).setNorthPane(null);
                        jDesktop.add(ncaja);
                        ncaja.show();
                    }
                 
                 
                
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
          
         
         
         jpanelcontenedor.add(apertur);
         apertur.setBounds(20, 70, 200, 50);
         apertur.setBorderPainted(false);
         apertur.setContentAreaFilled(false);
         apertur.setOpaque(true);
         apertur.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         apertur.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isMovimientos()==true){
        JButton movimi= new JButton("MOVIMIENTOS");
         movimi.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JIFMovimientoCaja movimientoc = new JIFMovimientoCaja();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = movimientoc.getSize();
                movimientoc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(movimientoc);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        movimientoc.getUI()).setNorthPane(null);
                movimientoc.show();
                 panelconmin();
                 
             }
         });
          
         
         jpanelcontenedor.add(movimi);
         movimi.setBounds(20, 140, 200, 50);
         
          movimi.setBorderPainted(false);
         movimi.setContentAreaFilled(false);
         movimi.setOpaque(true);
         movimi.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         movimi.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
     
         
     
     }
     
     //////////////////////////
     
        public void menureparacion(){
         JLabel titulo = new JLabel("SOPORTE TÉCNICO");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
          if(user.isNueva()==true){
         JButton nuevar= new JButton("NUEVA");
         nuevar.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 JIFReparaciones reparacion= new JIFReparaciones();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = reparacion.getSize();
                reparacion.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        reparacion.getUI()).setNorthPane(null);
                jDesktop.add(reparacion);
                
                reparacion.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
     
         
         jpanelcontenedor.add(nuevar);
         nuevar.setBounds(20, 70, 200, 50);
         
           nuevar.setBorderPainted(false);
         nuevar.setContentAreaFilled(false);
         nuevar.setOpaque(true);
         nuevar.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         nuevar.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
          if(user.isConsultarrepara()==true){
        JButton consultr= new JButton("CONSULTAR");
         consultr.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                      JIFrmReparacionConsultar consultar= new JIFrmReparacionConsultar();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = consultar.getSize();
                consultar.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        consultar.getUI()).setNorthPane(null);
                jDesktop.add(consultar);
                
                consultar.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         
         jpanelcontenedor.add(consultr);
         consultr.setBounds(20, 140, 200, 50);
           consultr.setBorderPainted(false);
         consultr.setContentAreaFilled(false);
         consultr.setOpaque(true);
         consultr.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         consultr.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
          if(user.isPendientes()==true){
             JButton rpendient= new JButton("PENDIENTES");
         rpendient.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 JIFReparacionesPendientes reparacionPendiente= new JIFReparacionesPendientes();
              Dimension desktopSize = jDesktop.getSize();
              Dimension FrameSize = reparacionPendiente.getSize();
              reparacionPendiente.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
               ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        reparacionPendiente.getUI()).setNorthPane(null);
              jDesktop.add(reparacionPendiente);
             
              reparacionPendiente.show();
               panelconmin();
//                jlblmensajeinfo.setText(""); 
             }
         });
          
         
         jpanelcontenedor.add(rpendient);
         rpendient.setBounds(20, 210, 200, 50);
         
         rpendient.setBorderPainted(false);
         rpendient.setContentAreaFilled(false);
         rpendient.setOpaque(true);
         rpendient.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         rpendient.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         /////////////////
         
     
     }
      
       
      public void menuotros(){
         JLabel titulo = new JLabel("COTIZACIÓN");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isCotizacion()==true){
         JButton debe= new JButton("NUEVA COTIZACION");
         debe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
                          JIFCotizacion cotixa = new JIFCotizacion();
                        
                        Dimension desktopSize = jDesktop.getSize();
                        Dimension FrameSize = cotixa.getSize();
                        cotixa.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                         ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        cotixa.getUI()).setNorthPane(null);
                        jDesktop.add(cotixa);
                       
                        cotixa.show();
                  
                 
                
                 panelconmin();
//                  jlblmensajeinfo.setText("");
                 
             }
         });
//           debe.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         
         jpanelcontenedor.add(debe);
         debe.setBounds(20, 70, 200, 50);
         
          debe.setBorderPainted(false);
         debe.setContentAreaFilled(false);
         debe.setOpaque(true);
         debe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         debe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isBuscar_cot()==true){
        JButton habe= new JButton("COTIZACIONES");
         habe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                 JIFEstadocuentaHaber estadohaber = new JIFEstadocuentaHaber();
                 JIFBuscarCotizacion buscarcoti= new JIFBuscarCotizacion();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = buscarcoti.getSize();
                buscarcoti.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
               ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        buscarcoti.getUI()).setNorthPane(null);
                jDesktop.add(buscarcoti);
                buscarcoti.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
//           habe.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         jpanelcontenedor.add(habe);
         habe.setBounds(20, 140, 200, 50);
         
         habe.setBorderPainted(false);
         habe.setContentAreaFilled(false);
         habe.setOpaque(true);
         habe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         habe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
     
         
     
     }
      ///////////////////
      
      
         public void menusucursal(){
         JLabel titulo = new JLabel("SALIDA");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isSucursalida()==true){
         JButton salida= new JButton("SALIDA");
         salida.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIFOrdenSalida ordensalida = new JIFOrdenSalida();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = ordensalida.getSize();
                ordensalida.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        ordensalida.getUI()).setNorthPane(null);
                jDesktop.add(ordensalida);
                
                ordensalida.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
          
         
         jpanelcontenedor.add(salida);
         salida.setBounds(20, 70, 200, 50);
         salida.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         salida.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isSucurentrada()==true){
        JButton entrad= new JButton("ENTRADA");
         entrad.addActionListener(new ActionListener() {
             
             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 JIFOrdenEntrada ordenentrada = new JIFOrdenEntrada();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = ordenentrada.getSize();
                ordenentrada.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        ordenentrada.getUI()).setNorthPane(null);
                jDesktop.add(ordenentrada);
                ordenentrada.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         jpanelcontenedor.add(entrad);
         entrad.setBounds(20, 140, 200, 50);
         entrad.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         entrad.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
         if(user.isConsultasucur()==true){
             JButton conssucur= new JButton("CONSULTAR");
         conssucur.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JIFConsultarMovimientosSucursal movsucursal = new JIFConsultarMovimientosSucursal();
              Dimension desktopSize = jDesktop.getSize();
              Dimension FrameSize = movsucursal.getSize();
              movsucursal.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        movsucursal.getUI()).setNorthPane(null);
              jDesktop.add(movsucursal);
              movsucursal.show();
               panelconmin();
//               jlblmensajeinfo.setText("");   
             }
         });
         
         
         jpanelcontenedor.add(conssucur);
         conssucur.setBounds(20, 210, 200, 50);
         conssucur.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         conssucur.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         /////////////////
         
     
     }
    public void menufacturacion(){
         JLabel titulo = new JLabel("FACTURACIÓN");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         titulo.setForeground(new Color(255, 204, 51));
         ////////////////////////////////////////////////////////
         if(user.isFacturas()==true){
         JButton debe= new JButton("FACTURAS");
         debe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
                         JIFFacturas factura = new JIFFacturas(MDIMenu.this);
                        
                        Dimension desktopSize = jDesktop.getSize();
                        Dimension FrameSize = factura.getSize();
                        factura.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                         ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        factura.getUI()).setNorthPane(null);
                        jDesktop.add(factura);
                       
                        factura.show();
                  
                 
                
                 panelconmin();
//                  jlblmensajeinfo.setText("");
                 
             }
         });
//           debe.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         
         jpanelcontenedor.add(debe);
         debe.setBounds(20, 70, 200, 50);
         
          debe.setBorderPainted(false);
         debe.setContentAreaFilled(false);
         debe.setOpaque(true);
         debe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         debe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isBoletas()==true){
        JButton habe= new JButton("BOLETAS");
         habe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                 JIFEstadocuentaHaber estadohaber = new JIFEstadocuentaHaber();
                 JIFBoletas boletas= new JIFBoletas(MDIMenu.this);
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = boletas.getSize();
                boletas.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
               ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        boletas.getUI()).setNorthPane(null);
                jDesktop.add(boletas);
                boletas.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
//           habe.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         jpanelcontenedor.add(habe);
         habe.setBounds(20, 140, 200, 50);
           habe.setBorderPainted(false);
         habe.setContentAreaFilled(false);
         habe.setOpaque(true);
         habe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         habe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
       if(user.isNcredito()==true){
        JButton habe= new JButton("NOTA DE CRÉDITO");
         habe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                 JIFEstadocuentaHaber estadohaber = new JIFEstadocuentaHaber();
                 JIFNotaCredito nc = new JIFNotaCredito();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = nc.getSize();
                nc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
               ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        nc.getUI()).setNorthPane(null);
                jDesktop.add(nc);
                nc.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
//           habe.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         jpanelcontenedor.add(habe);
         habe.setBounds(20, 210, 200, 50);
          habe.setBorderPainted(false);
         habe.setContentAreaFilled(false);
         habe.setOpaque(true);
         habe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         habe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isNdebito()==true){
        JButton habe= new JButton("NOTA DE DÉBITO");
         habe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                 JIFEstadocuentaHaber estadohaber = new JIFEstadocuentaHaber();
                JIFNotaDebito nd= new JIFNotaDebito();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = nd.getSize();
                nd.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
               ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        nd.getUI()).setNorthPane(null);
                jDesktop.add(nd);
                nd.show();
                 panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
//           habe.addMouseListener(new MouseAdapter() {
//              public void mousePressed(MouseEvent evt) {
//              jlblmensajeinfo.setText("Cargando datos .....");
//                }
//               public void mouseExited(MouseEvent evt) {
//                 jlblmensajeinfo.setText("");
//               }
//             
//        });
         
         jpanelcontenedor.add(habe);
         habe.setBounds(20, 280, 200, 50);
          habe.setBorderPainted(false);
         habe.setContentAreaFilled(false);
         habe.setOpaque(true);
         habe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         habe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         
     
     }
      
    public void cargarImagen(){
    try{

////        InputStream fondo=getClass().getResourceAsStream("/imagenes/degradado.jpg");
////        BufferedImage image=ImageIO.read(fondo);
//        
        InputStream fondoresumen=getClass().getResourceAsStream("/imagenes/fondoresumen.png");
        BufferedImage imageresumen=ImageIO.read(fondoresumen);
        
//        InputStream fondoiconos=getClass().getResourceAsStream("/imagenes/fondoiconosblack.jpg");
//        BufferedImage imageiconos=ImageIO.read(fondoiconos);
        
//        jDesktop.setBorder(new javax.swing.border.Border(getClass().getResource("/imagenes/cerrar.png")));
        
////        jDesktop.setBorder(new ImagenFondo(image));
        jpanelresumen.setBorder(new ImagenFondo(imageresumen));
//        jPanel6.setBorder(new ImagenFondo(imageiconos));
    }
    catch (Exception e){
            JOptionPane.showMessageDialog(null,"no se encontro fondo");
                    
        }

    }
//    public void cargarinterfazsegunprivilegios(){
//        jbtnmenucliente.setEnabled(user.isCliente());
//        jbtnmenuproveedor.setEnabled(user.isProveedor());
//        jbtnmenuempleado.setEnabled(user.isCkempleado());
//        jbtnmenuusuario.setEnabled(user.isUseri());
//        jbtnmenuingreso.setEnabled(user.isIngreso());
//        jbtnmenuproductos.setEnabled(user.isProductos());
//        jbtnmenuinventario.setEnabled(user.isInventario());
//        jbtnmenufamilia.setEnabled(user.isFamilia());
//        jbtnmenuventa.setEnabled(user.isVender());
//        jbtnmenuconsultarventa.setEnabled(user.isConsultarventa());
//        jbtnapertura.setEnabled(user.isApertura());
//        jbtnmovimientos.setEnabled(user.isMovimientos());
//        jbtnnuevarepacion.setEnabled(user.isNueva());
//        jbtnconsultarreparacion.setEnabled(user.isConsultarrepara());
//        jbtnpendientes.setEnabled(user.isPendientes());
//        jbtnestadocuentadebe.setEnabled(user.isDebe());
//        jbtnhaber.setEnabled(user.isHaber());
//        jbtnenproceso.setEnabled(user.isExtornar());
//        
//        
//        jbtningresopendientes.setEnabled(user.isProdpendientes());
//        jbtnmenumermas.setEnabled(user.isMermas());
//        jbtnmenuconsultarmermas.setEnabled(user.isConsulmermas());
//        jbtnordensalida.setEnabled(user.isSucursalida());
//        jbtnordenentrada.setEnabled(user.isSucurentrada());
//        jbtnconsultarsucursal.setEnabled(user.isConsultasucur());
//    
//    }
    
    
    
    
//     public void MaximizarJIFrame(javax.swing.JInternalFrame frame){
//    try
//        {
//        frame.setSelected(true);
//        frame.setMaximum(true);
//    }
//    catch (java.beans.PropertyVetoException e) {}
//          frame.show();
//           
//    }
    
    
    
     public void setcaja(JIFCaja caja){
      ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                        caja.getUI()).setNorthPane(null);
     jDesktop.add(caja);
     caja.show();
     }
     
   public void panelconmin(){
      Dimension dimm= new Dimension(0, 0);
      jpanelcontenedor.setPreferredSize(dimm);
      jpanelcontenedor.revalidate();
//      jDesktop.revalidate();
      /////////////// panel resumen disminiur///////////////
        agrandarresumen=0;
        System.out.println("disminuir");
        ImageIcon imageIcon= new ImageIcon( getClass().getResource("/imagenes/arriba.png"));
        jlblresize.setIcon(imageIcon);
        dimm= new Dimension(376, 110);
        jpanelresumen.setPreferredSize(dimm);
    //                jpanelresumen.repaint();
        jpanelresumen.revalidate();
      
      }
      public void panelconmax(){
      Dimension dimm= new Dimension(237, 0);
      jpanelcontenedor.setPreferredSize(dimm);
      jpanelcontenedor.revalidate();
//       jDesktop.revalidate();
      
      }
      
      
       private void reloj() { 
        calendario = new java.util.GregorianCalendar(); 
        segundos = calendario.get(Calendar.SECOND); 
        javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() { 
        @ Override 
        public void actionPerformed(java.awt.event.ActionEvent ae) { 
        java.util.Date actual = new java.util.Date(); 
        calendario.setTime(actual); 
        dia = calendario.get(Calendar.DAY_OF_MONTH); 
        mes = (calendario.get(Calendar.MONTH) + 1); 
        año = calendario.get(Calendar.YEAR); 
        hora = calendario.get(Calendar.HOUR_OF_DAY); 
        minutos = calendario.get(Calendar.MINUTE); 
        segundos = calendario.get(Calendar.SECOND); 
        String hour = String.format("%02d : %02d : %02d", hora, minutos, segundos); 
        String date = String.format("%02d / %02d / %02d", dia, mes, año); 
        jlblhora.setText("<html><center><p style=\"color:##5B5B5B\";>" + hour + "<br>" + date); 
           } 
        }); 
        timer.start(); 
}
       
   public void pruebaconexion(){
     
     Runnable runnable = new Runnable() {

         @Override
         public void run() {
             int i=0;
//             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             TestConexionServer test= new TestConexionServer();
             while(true){
                
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  
                 System.out.println("probando conexion...menu principal");
                
                if(test.ping()==true){
                   
                    
                   jlblrpruebaconexion.setText("<html><p style=\"color:#FFFFFF\";>"+"Cloud Habilitado");
                 
                 
                   
               } else{
                    
                   
                   jlblrpruebaconexion.setText("<html><p style=\"color:#FF0000 \";>"+"Cloud Inhabilitado");
                   JDReconexion reconexion = new JDReconexion(new Frame(),isVisible());
                   AWTUtilities.setWindowOpaque(reconexion, false);
                   reconexion.setVisible(true);
                 
//                    try {
//                     Thread.sleep(3000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//             
//                    jlblrpruebaconexion.setText("<html><p style=\"color:#FF0000 \";>"+"Reconectando ...");
                  
               }
             
              
             }
             
         }
     };
     Thread T = new Thread(runnable);
     T.start();
     
    }
     
     /////////////// CODIGO PARA CAMBIAR TEMAS /////////////////////////
//     look.setLook( "com.jtattoo.plaf.noire.NoireLookAndFeel",this);
//        daousuario.updatetema(user.getId_usuario(), "com.jtattoo.plaf.noire.NoireLookAndFeel");
    //////////////////////////////////////////////////////////

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktop = new javax.swing.JDesktopPane();
        jlblhora = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jlblmenuproducto = new javax.swing.JLabel();
        jlblmenuuser = new javax.swing.JLabel();
        jlblmenuventa = new javax.swing.JLabel();
        jlblmenucaja = new javax.swing.JLabel();
        jlblmenurepara = new javax.swing.JLabel();
        jlblmenuestado = new javax.swing.JLabel();
        jlblmenuregistro = new javax.swing.JLabel();
        jlblmenufacturacion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpanelcontenedor = new javax.swing.JPanel();
        jpanelresumen = new javax.swing.JPanel();
        jlblempleadomes = new javax.swing.JLabel();
        jlbluser = new javax.swing.JLabel();
        jlblemplereg = new javax.swing.JLabel();
        jlblrut = new javax.swing.JLabel();
        jlblventadia = new javax.swing.JLabel();
        jlblprodreg = new javax.swing.JLabel();
        jlblresize = new javax.swing.JLabel();
        jlblclireg = new javax.swing.JLabel();
        jlblprodbajo = new javax.swing.JLabel();
        jlblventames = new javax.swing.JLabel();
        jbtnactualizar = new javax.swing.JButton();
        jlblventproc = new javax.swing.JLabel();
        jlblventaanio = new javax.swing.JLabel();
        jlblprovreg = new javax.swing.JLabel();
        jlblsucursal = new javax.swing.JLabel();
        jlblrpruebaconexion = new javax.swing.JLabel();
        jpaneliconoclientes = new javax.swing.JPanel();
        jpanelcliente = new javax.swing.JLabel();
        jlblclienteaccesodir = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlblminice = new javax.swing.JLabel();
        jlblusercabecera = new javax.swing.JLabel();
        jlblcerrar = new javax.swing.JLabel();
        jlblmax = new javax.swing.JLabel();
        jpaneliconoproveedores = new javax.swing.JPanel();
        jpanelproveedor = new javax.swing.JLabel();
        jlblproveedoraccesodir = new javax.swing.JLabel();
        jpaneliconoproductos = new javax.swing.JPanel();
        jpanelproductos = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jpaneliconoventa = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jpanelventa = new javax.swing.JLabel();
        jpaneliconoempleados = new javax.swing.JPanel();
        jpanelempleados = new javax.swing.JLabel();
        jlblempleadoaccesodir1 = new javax.swing.JLabel();
        jpanelbanner = new javax.swing.JPanel();
        jpanelgrafico = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu Principal");
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);

        jDesktop.setBackground(new java.awt.Color(255, 255, 255));
        jDesktop.setForeground(new java.awt.Color(51, 51, 51));
        jDesktop.setEnabled(false);
        jDesktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDesktopMouseReleased(evt);
            }
        });

        jlblhora.setBackground(new java.awt.Color(0, 0, 0));
        jlblhora.setFont(new java.awt.Font("Segoe UI Light", 1, 45)); // NOI18N
        jlblhora.setForeground(new java.awt.Color(0, 0, 0));
        jlblhora.setText("* * *");

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setForeground(new java.awt.Color(77, 161, 227));
        jPanel6.setPreferredSize(new java.awt.Dimension(46, 791));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel6MouseReleased(evt);
            }
        });

        jlblmenuproducto.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenuproducto.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuproducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/barcode40x40.png"))); // NOI18N
        jlblmenuproducto.setToolTipText("Productos");
        jlblmenuproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenuproductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenuproductoMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuproductoMouseReleased(evt);
            }
        });

        jlblmenuuser.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenuuser.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user40x40b.png"))); // NOI18N
        jlblmenuuser.setToolTipText("Usuario");
        jlblmenuuser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuuser.setRequestFocusEnabled(false);
        jlblmenuuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenuuserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenuuserMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuuserMouseReleased(evt);
            }
        });

        jlblmenuventa.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenuventa.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuventa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buy40x40b.png"))); // NOI18N
        jlblmenuventa.setToolTipText("Venta");
        jlblmenuventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenuventaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenuventaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuventaMouseReleased(evt);
            }
        });

        jlblmenucaja.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenucaja.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenucaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenucaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/money40x40b.png"))); // NOI18N
        jlblmenucaja.setToolTipText("Caja");
        jlblmenucaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenucaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenucajaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenucajaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenucajaMouseReleased(evt);
            }
        });

        jlblmenurepara.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenurepara.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenurepara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenurepara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/repair40x40.png"))); // NOI18N
        jlblmenurepara.setToolTipText("Soporte Técnico");
        jlblmenurepara.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenurepara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenureparaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenureparaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenureparaMouseReleased(evt);
            }
        });

        jlblmenuestado.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenuestado.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuestado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuestado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/docs40x40b.png"))); // NOI18N
        jlblmenuestado.setToolTipText("Cotizaciones");
        jlblmenuestado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuestado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenuestadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenuestadoMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuestadoMouseReleased(evt);
            }
        });

        jlblmenuregistro.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenuregistro.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuregistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuregistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group40x40b.png"))); // NOI18N
        jlblmenuregistro.setToolTipText("Mantenimiento Registro");
        jlblmenuregistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenuregistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenuregistroMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuregistroMouseReleased(evt);
            }
        });

        jlblmenufacturacion.setBackground(new java.awt.Color(238, 238, 238));
        jlblmenufacturacion.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenufacturacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenufacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factu40x40b.png"))); // NOI18N
        jlblmenufacturacion.setToolTipText("Facturación");
        jlblmenufacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenufacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenufacturacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenufacturacionMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenufacturacionMouseReleased(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu40x40b.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuregistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jlblmenuuser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuproducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuventa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenucaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenurepara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuestado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenufacturacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addComponent(jlblmenuuser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblmenuregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblmenuproducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblmenuventa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblmenucaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblmenurepara)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblmenuestado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblmenufacturacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpanelcontenedor.setBackground(new java.awt.Color(102, 102, 102));
        jpanelcontenedor.setForeground(new java.awt.Color(102, 102, 102));
        jpanelcontenedor.setInheritsPopupMenu(true);
        jpanelcontenedor.setOpaque(false);
        jpanelcontenedor.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout jpanelcontenedorLayout = new javax.swing.GroupLayout(jpanelcontenedor);
        jpanelcontenedor.setLayout(jpanelcontenedorLayout);
        jpanelcontenedorLayout.setHorizontalGroup(
            jpanelcontenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpanelcontenedorLayout.setVerticalGroup(
            jpanelcontenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpanelresumen.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jpanelresumen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpanelresumen.setOpaque(false);
        jpanelresumen.setPreferredSize(new java.awt.Dimension(376, 110));

        jlblempleadomes.setBackground(new java.awt.Color(255, 255, 255));
        jlblempleadomes.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblempleadomes.setForeground(new java.awt.Color(255, 255, 255));
        jlblempleadomes.setText("* * *");

        jlbluser.setBackground(new java.awt.Color(255, 255, 255));
        jlbluser.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jlbluser.setForeground(new java.awt.Color(255, 255, 255));
        jlbluser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/userblanco40x40.png"))); // NOI18N
        jlbluser.setText("---");

        jlblemplereg.setBackground(new java.awt.Color(255, 255, 255));
        jlblemplereg.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblemplereg.setForeground(new java.awt.Color(255, 255, 255));
        jlblemplereg.setText("* * *");

        jlblrut.setBackground(new java.awt.Color(255, 255, 255));
        jlblrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblrut.setForeground(new java.awt.Color(255, 255, 255));
        jlblrut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlblrut.setText("---");

        jlblventadia.setBackground(new java.awt.Color(255, 255, 255));
        jlblventadia.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblventadia.setForeground(new java.awt.Color(255, 255, 255));
        jlblventadia.setText("* * *");

        jlblprodreg.setBackground(new java.awt.Color(255, 255, 255));
        jlblprodreg.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblprodreg.setForeground(new java.awt.Color(255, 255, 255));
        jlblprodreg.setText("* * *");

        jlblresize.setBackground(new java.awt.Color(255, 255, 255));
        jlblresize.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblresize.setForeground(new java.awt.Color(255, 255, 255));
        jlblresize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arriba.png"))); // NOI18N
        jlblresize.setToolTipText("Clic para expandir o reducir");
        jlblresize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblresize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblresizeMouseReleased(evt);
            }
        });

        jlblclireg.setBackground(new java.awt.Color(255, 255, 255));
        jlblclireg.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblclireg.setForeground(new java.awt.Color(255, 255, 255));
        jlblclireg.setText("* * *");

        jlblprodbajo.setBackground(new java.awt.Color(255, 255, 255));
        jlblprodbajo.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblprodbajo.setForeground(new java.awt.Color(255, 255, 255));
        jlblprodbajo.setText("* * *");

        jlblventames.setBackground(new java.awt.Color(255, 255, 255));
        jlblventames.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblventames.setForeground(new java.awt.Color(255, 255, 255));
        jlblventames.setText("* * *");

        jbtnactualizar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Refresh_25px.png"))); // NOI18N
        jbtnactualizar.setPreferredSize(new java.awt.Dimension(55, 41));
        jbtnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnactualizarActionPerformed(evt);
            }
        });

        jlblventproc.setBackground(new java.awt.Color(255, 255, 255));
        jlblventproc.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblventproc.setForeground(new java.awt.Color(255, 255, 255));
        jlblventproc.setText("* * *");

        jlblventaanio.setBackground(new java.awt.Color(255, 255, 255));
        jlblventaanio.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblventaanio.setForeground(new java.awt.Color(255, 255, 255));
        jlblventaanio.setText("* * *");

        jlblprovreg.setBackground(new java.awt.Color(255, 255, 255));
        jlblprovreg.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblprovreg.setForeground(new java.awt.Color(255, 255, 255));
        jlblprovreg.setText("* * *");

        jlblsucursal.setBackground(new java.awt.Color(255, 255, 255));
        jlblsucursal.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jlblsucursal.setForeground(new java.awt.Color(255, 255, 255));
        jlblsucursal.setText("---");

        javax.swing.GroupLayout jpanelresumenLayout = new javax.swing.GroupLayout(jpanelresumen);
        jpanelresumen.setLayout(jpanelresumenLayout);
        jpanelresumenLayout.setHorizontalGroup(
            jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelresumenLayout.createSequentialGroup()
                .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jlblresize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlbluser, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblrut, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblsucursal)))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblventaanio, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblventames, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblventadia, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblventproc, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblempleadomes, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblprodreg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblprodbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblclireg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblprovreg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblemplereg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelresumenLayout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addComponent(jbtnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpanelresumenLayout.setVerticalGroup(
            jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelresumenLayout.createSequentialGroup()
                .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jlblresize))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jlbluser)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblrut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblsucursal)
                .addGap(18, 18, 18)
                .addComponent(jlblventaanio)
                .addGap(12, 12, 12)
                .addComponent(jlblventames)
                .addGap(10, 10, 10)
                .addComponent(jlblventadia)
                .addGap(10, 10, 10)
                .addComponent(jlblventproc)
                .addGap(10, 10, 10)
                .addComponent(jlblempleadomes)
                .addGap(6, 6, 6)
                .addComponent(jlblprodreg)
                .addGap(10, 10, 10)
                .addComponent(jlblprodbajo)
                .addGap(10, 10, 10)
                .addComponent(jlblclireg)
                .addGap(10, 10, 10)
                .addComponent(jlblprovreg)
                .addGap(12, 12, 12)
                .addComponent(jlblemplereg)
                .addGap(6, 6, 6)
                .addComponent(jbtnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlblrpruebaconexion.setBackground(new java.awt.Color(77, 161, 227));
        jlblrpruebaconexion.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblrpruebaconexion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblrpruebaconexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cloud_20x20.png"))); // NOI18N
        jlblrpruebaconexion.setText("* * *");
        jlblrpruebaconexion.setOpaque(true);

        jpaneliconoclientes.setBackground(new java.awt.Color(255, 255, 255));
        jpaneliconoclientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jpaneliconoclientes.setForeground(new java.awt.Color(255, 255, 255));
        jpaneliconoclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpaneliconoclientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpaneliconoclientesMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpaneliconoclientesMouseReleased(evt);
            }
        });

        jpanelcliente.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jpanelcliente.setForeground(new java.awt.Color(91, 91, 91));
        jpanelcliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpanelcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        jpanelcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelclienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpanelclienteMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpanelclienteMouseReleased(evt);
            }
        });

        jlblclienteaccesodir.setFont(new java.awt.Font("Segoe UI Light", 1, 15)); // NOI18N
        jlblclienteaccesodir.setForeground(new java.awt.Color(51, 51, 51));
        jlblclienteaccesodir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblclienteaccesodir.setText("CLIENTES");
        jlblclienteaccesodir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblclienteaccesodir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblclienteaccesodirMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jpaneliconoclientesLayout = new javax.swing.GroupLayout(jpaneliconoclientes);
        jpaneliconoclientes.setLayout(jpaneliconoclientesLayout);
        jpaneliconoclientesLayout.setHorizontalGroup(
            jpaneliconoclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanelcliente, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(jlblclienteaccesodir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpaneliconoclientesLayout.setVerticalGroup(
            jpaneliconoclientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneliconoclientesLayout.createSequentialGroup()
                .addComponent(jpanelcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jlblclienteaccesodir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(34, 75, 139));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jlblminice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Minimizeblanco40x40.png"))); // NOI18N
        jlblminice.setToolTipText("Minimizar");
        jlblminice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblminice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblminiceMouseReleased(evt);
            }
        });

        jlblusercabecera.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblusercabecera.setForeground(new java.awt.Color(255, 255, 255));
        jlblusercabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/userblanco40x40.png"))); // NOI18N
        jlblusercabecera.setText("jLabel1");

        jlblcerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shutdownblanco40x40.png"))); // NOI18N
        jlblcerrar.setToolTipText("Cerrar Sesión");
        jlblcerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblcerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblcerrarMouseReleased(evt);
            }
        });

        jlblmax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/resize40x40.png"))); // NOI18N
        jlblmax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmaxMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jlblusercabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblminice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblmax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblcerrar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblcerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlblminice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlblusercabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlblmax))
        );

        jpaneliconoproveedores.setBackground(new java.awt.Color(255, 255, 255));
        jpaneliconoproveedores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jpaneliconoproveedores.setForeground(new java.awt.Color(255, 255, 255));
        jpaneliconoproveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpaneliconoproveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpaneliconoproveedoresMouseExited(evt);
            }
        });

        jpanelproveedor.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jpanelproveedor.setForeground(new java.awt.Color(91, 91, 91));
        jpanelproveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpanelproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/provee.png"))); // NOI18N
        jpanelproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelproveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpanelproveedorMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpanelproveedorMouseReleased(evt);
            }
        });

        jlblproveedoraccesodir.setFont(new java.awt.Font("Segoe UI Light", 1, 15)); // NOI18N
        jlblproveedoraccesodir.setForeground(new java.awt.Color(51, 51, 51));
        jlblproveedoraccesodir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblproveedoraccesodir.setText("PROVEEDORES");
        jlblproveedoraccesodir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblproveedoraccesodir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblproveedoraccesodirMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jpaneliconoproveedoresLayout = new javax.swing.GroupLayout(jpaneliconoproveedores);
        jpaneliconoproveedores.setLayout(jpaneliconoproveedoresLayout);
        jpaneliconoproveedoresLayout.setHorizontalGroup(
            jpaneliconoproveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblproveedoraccesodir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(jpanelproveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpaneliconoproveedoresLayout.setVerticalGroup(
            jpaneliconoproveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneliconoproveedoresLayout.createSequentialGroup()
                .addComponent(jpanelproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblproveedoraccesodir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpaneliconoproductos.setBackground(new java.awt.Color(255, 255, 255));
        jpaneliconoproductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jpaneliconoproductos.setForeground(new java.awt.Color(255, 255, 255));
        jpaneliconoproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpaneliconoproductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpaneliconoproductosMouseExited(evt);
            }
        });

        jpanelproductos.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jpanelproductos.setForeground(new java.awt.Color(91, 91, 91));
        jpanelproductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpanelproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); // NOI18N
        jpanelproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelproductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpanelproductosMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpanelproductosMouseReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setMnemonic('p');
        jButton2.setText("PRODUCTOS");
        jButton2.setToolTipText("alt + p");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpaneliconoproductosLayout = new javax.swing.GroupLayout(jpaneliconoproductos);
        jpaneliconoproductos.setLayout(jpaneliconoproductosLayout);
        jpaneliconoproductosLayout.setHorizontalGroup(
            jpaneliconoproductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
            .addComponent(jpanelproductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpaneliconoproductosLayout.setVerticalGroup(
            jpaneliconoproductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneliconoproductosLayout.createSequentialGroup()
                .addComponent(jpanelproductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpaneliconoventa.setBackground(new java.awt.Color(255, 255, 255));
        jpaneliconoventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jpaneliconoventa.setForeground(new java.awt.Color(255, 255, 255));
        jpaneliconoventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpaneliconoventaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpaneliconoventaMouseExited(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(77, 161, 227));
        jButton1.setFont(new java.awt.Font("Segoe UI Light", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setMnemonic('v');
        jButton1.setText("VENTA");
        jButton1.setToolTipText("Alt + v");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jpanelventa.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jpanelventa.setForeground(new java.awt.Color(255, 255, 255));
        jpanelventa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpanelventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clien.png"))); // NOI18N
        jpanelventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelventaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpanelventaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpanelventaMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jpaneliconoventaLayout = new javax.swing.GroupLayout(jpaneliconoventa);
        jpaneliconoventa.setLayout(jpaneliconoventaLayout);
        jpaneliconoventaLayout.setHorizontalGroup(
            jpaneliconoventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(jpanelventa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpaneliconoventaLayout.setVerticalGroup(
            jpaneliconoventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneliconoventaLayout.createSequentialGroup()
                .addComponent(jpanelventa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpaneliconoempleados.setBackground(new java.awt.Color(255, 255, 255));
        jpaneliconoempleados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jpaneliconoempleados.setForeground(new java.awt.Color(255, 255, 255));
        jpaneliconoempleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpaneliconoempleadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpaneliconoempleadosMouseExited(evt);
            }
        });

        jpanelempleados.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jpanelempleados.setForeground(new java.awt.Color(91, 91, 91));
        jpanelempleados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpanelempleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/empleados.png"))); // NOI18N
        jpanelempleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpanelempleadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpanelempleadosMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jpanelempleadosMouseReleased(evt);
            }
        });

        jlblempleadoaccesodir1.setFont(new java.awt.Font("Segoe UI Light", 1, 15)); // NOI18N
        jlblempleadoaccesodir1.setForeground(new java.awt.Color(51, 51, 51));
        jlblempleadoaccesodir1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblempleadoaccesodir1.setText("EMPLEADOS");
        jlblempleadoaccesodir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblempleadoaccesodir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblempleadoaccesodir1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jpaneliconoempleadosLayout = new javax.swing.GroupLayout(jpaneliconoempleados);
        jpaneliconoempleados.setLayout(jpaneliconoempleadosLayout);
        jpaneliconoempleadosLayout.setHorizontalGroup(
            jpaneliconoempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblempleadoaccesodir1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(jpanelempleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpaneliconoempleadosLayout.setVerticalGroup(
            jpaneliconoempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaneliconoempleadosLayout.createSequentialGroup()
                .addComponent(jpanelempleados, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblempleadoaccesodir1))
        );

        jpanelbanner.setBackground(new java.awt.Color(255, 255, 255));
        jpanelbanner.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpanelbannerMouseDragged(evt);
            }
        });
        jpanelbanner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpanelbannerMousePressed(evt);
            }
        });

        jpanelgrafico.setBackground(new java.awt.Color(0, 0, 0));
        jpanelgrafico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jpanelgrafico.setForeground(new java.awt.Color(0, 0, 0));
        jpanelgrafico.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jpanelbannerLayout = new javax.swing.GroupLayout(jpanelbanner);
        jpanelbanner.setLayout(jpanelbannerLayout);
        jpanelbannerLayout.setHorizontalGroup(
            jpanelbannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanelgrafico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanelbannerLayout.setVerticalGroup(
            jpanelbannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelbannerLayout.createSequentialGroup()
                .addComponent(jpanelgrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelcontenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanelbanner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDesktopLayout.createSequentialGroup()
                        .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopLayout.createSequentialGroup()
                                .addComponent(jlblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                                .addComponent(jpanelresumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopLayout.createSequentialGroup()
                                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblrpruebaconexion, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDesktopLayout.createSequentialGroup()
                                        .addComponent(jpaneliconoclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jpaneliconoempleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jpaneliconoproductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jpaneliconoproveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jpaneliconoventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
            .addGroup(jDesktopLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanelcontenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                    .addGroup(jDesktopLayout.createSequentialGroup()
                        .addComponent(jpanelbanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopLayout.createSequentialGroup()
                                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jpaneliconoproductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpaneliconoempleados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpaneliconoproveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpaneliconoventa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpaneliconoclientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblrpruebaconexion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                                .addComponent(jlblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jpanelresumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jDesktop.setLayer(jlblhora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jPanel6, javax.swing.JLayeredPane.MODAL_LAYER);
        jDesktop.setLayer(jpanelcontenedor, javax.swing.JLayeredPane.MODAL_LAYER);
        jDesktop.setLayer(jpanelresumen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jlblrpruebaconexion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jpaneliconoclientes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jpaneliconoproveedores, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jpaneliconoproductos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jpaneliconoventa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jpaneliconoempleados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jpanelbanner, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblmenuuserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuuserMouseReleased
        // TODO add your handling code here:
          System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menuusuario();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
         menuusuario();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenuuserMouseReleased

    private void jlblmenuregistroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuregistroMouseReleased
        // TODO add your handling code here:
          System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menumantenimiento();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
         menumantenimiento();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenuregistroMouseReleased

    private void jlblmenuproductoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuproductoMouseReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
          System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menuproducto();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
         menuproducto();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenuproductoMouseReleased

    private void jlblmenuventaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuventaMouseReleased
        // TODO add your handling code here:
         System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menuventa();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
          menuventa();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenuventaMouseReleased

    private void jlblmenucajaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenucajaMouseReleased
        // TODO add your handling code here:
        System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menucaja();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
           menucaja();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenucajaMouseReleased

    private void jlblmenureparaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenureparaMouseReleased
        // TODO add your handling code here:
         System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menureparacion();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
         menureparacion();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenureparaMouseReleased

    private void jlblmenuestadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuestadoMouseReleased
        // TODO add your handling code here:
         System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menuotros();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
          menuotros();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenuestadoMouseReleased

    private void jlblmenuuserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuuserMouseEntered
        // TODO add your handling code here:
        jlblmenuuser.setOpaque(true);
//        jlblmenuuser.setBackground(new Color(220, 151, 96));
        jlblmenuuser.repaint();
        jlblmenuuser.revalidate();
       
       
    }//GEN-LAST:event_jlblmenuuserMouseEntered

    private void jDesktopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopMouseReleased
        // TODO add your handling code here:
        panelconmin();
    }//GEN-LAST:event_jDesktopMouseReleased

    private void jPanel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseReleased
        // TODO add your handling code here:
         panelconmin();
    }//GEN-LAST:event_jPanel6MouseReleased

    private void jbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnactualizarActionPerformed
        // TODO add your handling code here:
        cargarresumen();
    }//GEN-LAST:event_jbtnactualizarActionPerformed

    private void jlblresizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblresizeMouseReleased
        // TODO add your handling code here:
        ImageIcon imageIcon;
        Dimension dimm;
        switch(agrandarresumen){
            case 0:
                agrandarresumen=1;
                 System.out.println("agrandar");
                imageIcon= new ImageIcon( getClass().getResource("/imagenes/abajo.png"));
                jlblresize.setIcon(imageIcon);
                dimm= new Dimension(376, 416);
                jpanelresumen.setPreferredSize(dimm);
//                jpanelresumen.repaint();
                jpanelresumen.revalidate();
//                jpanelresumen.revalidate();
//                 jpanelresumen.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                break;
            case 1:
                agrandarresumen=0;
                System.out.println("disminuir");
                imageIcon= new ImageIcon( getClass().getResource("/imagenes/arriba.png"));
                jlblresize.setIcon(imageIcon);
                dimm= new Dimension(376, 110);
                jpanelresumen.setPreferredSize(dimm);
//                jpanelresumen.repaint();
                jpanelresumen.revalidate();
//                jpanelresumen.revalidate();
//                 jpanelresumen.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
                break;  
        }
       
    
        
    }//GEN-LAST:event_jlblresizeMouseReleased

    private void jlblmenuuserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuuserMouseExited
        // TODO add your handling code here:
          jlblmenuuser.setOpaque(false);
          jlblmenuuser.repaint();
          jlblmenuuser.revalidate();
//        jlblmenuuser.setBackground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jlblmenuuserMouseExited

    private void jlblmenuregistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuregistroMouseEntered
        // TODO add your handling code here:
          jlblmenuregistro.setOpaque(true);
          jlblmenuregistro.repaint();
          jlblmenuregistro.revalidate();
    }//GEN-LAST:event_jlblmenuregistroMouseEntered

    private void jlblmenuregistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuregistroMouseExited
        // TODO add your handling code here:
          jlblmenuregistro.setOpaque(false);
          jlblmenuregistro.repaint();
          jlblmenuregistro.revalidate();
    }//GEN-LAST:event_jlblmenuregistroMouseExited

    private void jlblmenuproductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuproductoMouseEntered
        // TODO add your handling code here:
               jlblmenuproducto.setOpaque(true);
          jlblmenuproducto.repaint();
          jlblmenuproducto.revalidate();
    }//GEN-LAST:event_jlblmenuproductoMouseEntered

    private void jlblmenuventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuventaMouseEntered
        // TODO add your handling code here:
         jlblmenuventa.setOpaque(true);
          jlblmenuventa.repaint();
          jlblmenuventa.revalidate();
    }//GEN-LAST:event_jlblmenuventaMouseEntered

    private void jlblmenuproductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuproductoMouseExited
        // TODO add your handling code here:
        jlblmenuproducto.setOpaque(false);
          jlblmenuproducto.repaint();
          jlblmenuproducto.revalidate();
    }//GEN-LAST:event_jlblmenuproductoMouseExited

    private void jlblmenuventaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuventaMouseExited
        // TODO add your handling code here:
        jlblmenuventa.setOpaque(false);
          jlblmenuventa.repaint();
          jlblmenuventa.revalidate();
    }//GEN-LAST:event_jlblmenuventaMouseExited

    private void jlblmenucajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenucajaMouseEntered
        // TODO add your handling code here:
        jlblmenucaja.setOpaque(true);
          jlblmenucaja.repaint();
          jlblmenucaja.revalidate();
    }//GEN-LAST:event_jlblmenucajaMouseEntered

    private void jlblmenureparaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenureparaMouseEntered
        // TODO add your handling code here:
       jlblmenurepara.setOpaque(true);
          jlblmenurepara.repaint();
          jlblmenurepara.revalidate();
    }//GEN-LAST:event_jlblmenureparaMouseEntered

    private void jlblmenureparaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenureparaMouseExited
        // TODO add your handling code here:
        jlblmenurepara.setOpaque(false);
          jlblmenurepara.repaint();
          jlblmenurepara.revalidate();
    }//GEN-LAST:event_jlblmenureparaMouseExited

    private void jlblmenuestadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuestadoMouseEntered
        // TODO add your handling code here:
        jlblmenuestado.setOpaque(true);
          jlblmenuestado.repaint();
          jlblmenuestado.revalidate();
    }//GEN-LAST:event_jlblmenuestadoMouseEntered

    private void jlblmenuestadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenuestadoMouseExited
        // TODO add your handling code here:
         jlblmenuestado.setOpaque(false);
          jlblmenuestado.repaint();
          jlblmenuestado.revalidate();
    }//GEN-LAST:event_jlblmenuestadoMouseExited

    private void jlblmenucajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenucajaMouseExited
        // TODO add your handling code here:
        jlblmenucaja.setOpaque(false);
          jlblmenucaja.repaint();
          jlblmenucaja.revalidate();
    }//GEN-LAST:event_jlblmenucajaMouseExited

    private void jlblminiceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblminiceMouseReleased
        // TODO add your handling code here:
          setExtendedState(JFrame.CROSSHAIR_CURSOR);
          
                 ///ADAPTAR AL TAMAÑO DE PANTALLA
//        dim=super.getToolkit().getScreenSize();
//        super.setSize(dim);
       
      
       
    }//GEN-LAST:event_jlblminiceMouseReleased

    private void jlblclienteaccesodirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblclienteaccesodirMouseReleased
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jlblclienteaccesodirMouseReleased

    private void jlblproveedoraccesodirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblproveedoraccesodirMouseReleased
        // TODO add your handling code here:
        JIFProveedor proveedor = new JIFProveedor(MDIMenu.this);
        Dimension desktopSize = jDesktop.getSize();
        Dimension FrameSize = proveedor.getSize();
        proveedor.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) 
            proveedor.getUI()).setNorthPane(null);
        jDesktop.add(proveedor);
        proveedor.show();
    }//GEN-LAST:event_jlblproveedoraccesodirMouseReleased

    private void jlblmenufacturacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenufacturacionMouseEntered
        // TODO add your handling code here:
          jlblmenufacturacion.setOpaque(true);
          jlblmenufacturacion.repaint();
          jlblmenufacturacion.revalidate();
    }//GEN-LAST:event_jlblmenufacturacionMouseEntered

    private void jlblmenufacturacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenufacturacionMouseExited
        // TODO add your handling code here:
        jlblmenufacturacion.setOpaque(false);
          jlblmenufacturacion.repaint();
          jlblmenufacturacion.revalidate();
    }//GEN-LAST:event_jlblmenufacturacionMouseExited

    private void jlblmenufacturacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenufacturacionMouseReleased
        // TODO add your handling code here:
           System.out.println(jpanelcontenedor.getComponentCount());
        if(jpanelcontenedor.getComponentCount()==0){
            menufacturacion();
        }else {
            jpanelcontenedor.removeAll();
            jpanelcontenedor.repaint();
          menufacturacion();
        }
         panelconmax();
    }//GEN-LAST:event_jlblmenufacturacionMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(user.isVender()==true)
        {
            JIFVenta Nventa = new JIFVenta(MDIMenu.this);
            Dimension desktopSize = jDesktop.getSize();
            Dimension FrameSize = Nventa.getSize();
            Nventa.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                Nventa.getUI()).setNorthPane(null);
            jDesktop.add(Nventa);
            Nventa.show();
        
        }else {
             JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a venta","",JOptionPane.INFORMATION_MESSAGE);
        
        }
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(user.isProductos()==true){
            JIFrmBuscarProductos inventario= new JIFrmBuscarProductos();
          Dimension desktopSize = jDesktop.getSize();
          Dimension FrameSize = inventario.getSize();
          inventario.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
          ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                inventario.getUI()).setNorthPane(null);
          jDesktop.add(inventario);
          inventario.show();
        }else {
         JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a productos","",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
          
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jlblempleadoaccesodir1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblempleadoaccesodir1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblempleadoaccesodir1MouseReleased

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        // TODO add your handling code here:
        
         posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        // TODO add your handling code here:
         int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jpaneliconoclientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoclientesMouseEntered
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jpaneliconoclientesMouseEntered

    private void jpaneliconoclientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoclientesMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jpaneliconoclientesMouseExited

    private void jpaneliconoclientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoclientesMouseReleased
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jpaneliconoclientesMouseReleased

    private void jpaneliconoempleadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoempleadosMouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jpaneliconoempleadosMouseEntered

    private void jpaneliconoempleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoempleadosMouseExited
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jpaneliconoempleadosMouseExited

    private void jpaneliconoventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoventaMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jpaneliconoventaMouseEntered

    private void jpaneliconoventaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoventaMouseExited
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jpaneliconoventaMouseExited

    private void jpaneliconoproveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoproveedoresMouseEntered
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jpaneliconoproveedoresMouseEntered

    private void jpaneliconoproveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoproveedoresMouseExited
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jpaneliconoproveedoresMouseExited

    private void jpaneliconoproductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoproductosMouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jpaneliconoproductosMouseEntered

    private void jpaneliconoproductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpaneliconoproductosMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jpaneliconoproductosMouseExited

    private void jpanelempleadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelempleadosMouseReleased
        // TODO add your handling code here:
        if(user.isCkempleado()==true)
        {
            JIFEmpleados empleado= new JIFEmpleados(MDIMenu.this);
              Dimension desktopSize = jDesktop.getSize();
              Dimension FrameSize = empleado.getSize();
              empleado.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    empleado.getUI()).setNorthPane(null);
              jDesktop.add(empleado);
              empleado.show();
               panelconmin();
        
        
        }else{
             JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a empleados","",JOptionPane.INFORMATION_MESSAGE);
        
        }
         
    }//GEN-LAST:event_jpanelempleadosMouseReleased

    private void jpanelventaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelventaMouseReleased
        // TODO add your handling code here:
        if(user.isVender()==true)
        {
            JIFVenta Nventa = new JIFVenta(MDIMenu.this);
            Dimension desktopSize = jDesktop.getSize();
            Dimension FrameSize = Nventa.getSize();
            Nventa.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                Nventa.getUI()).setNorthPane(null);
            jDesktop.add(Nventa);
            Nventa.show();
        
        
        }else {
        
            JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a venta","",JOptionPane.INFORMATION_MESSAGE);
        }
          
    }//GEN-LAST:event_jpanelventaMouseReleased

    private void jpanelproveedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelproveedorMouseReleased
        // TODO add your handling code here:
        if(user.isProveedor()==true)
        {
             JIFProveedor proveedor = new JIFProveedor(MDIMenu.this);
            Dimension desktopSize = jDesktop.getSize();
            Dimension FrameSize = proveedor.getSize();
            proveedor.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                proveedor.getUI()).setNorthPane(null);
            jDesktop.add(proveedor);
            proveedor.show();
        
        }else {
            
             JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a proveedores","",JOptionPane.INFORMATION_MESSAGE);
        
        }
          
    }//GEN-LAST:event_jpanelproveedorMouseReleased

    private void jpanelproductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelproductosMouseReleased
        // TODO add your handling code here:
        if(user.isProductos()==true)
        {
            JIFrmBuscarProductos inventario= new JIFrmBuscarProductos();
          Dimension desktopSize = jDesktop.getSize();
          Dimension FrameSize = inventario.getSize();
          inventario.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
          ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                inventario.getUI()).setNorthPane(null);
          jDesktop.add(inventario);
          inventario.show();
        
        }else {
             JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a productos","",JOptionPane.INFORMATION_MESSAGE);
        
        }
      
    }//GEN-LAST:event_jpanelproductosMouseReleased

    private void jpanelclienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelclienteMouseEntered
        // TODO add your handling code here:
          jpaneliconoclientes.setBackground(new java.awt.Color(77,161,227));
    }//GEN-LAST:event_jpanelclienteMouseEntered

    private void jpanelclienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelclienteMouseExited
        // TODO add your handling code here:
          jpaneliconoclientes.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jpanelclienteMouseExited

    private void jpanelclienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelclienteMouseReleased
        // TODO add your handling code here:
        if(user.isCliente()==true)
        {
            JIFCliente cliente = new JIFCliente(MDIMenu.this);
            Dimension desktopSize = jDesktop.getSize();
            Dimension FrameSize = cliente.getSize();
            cliente.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
            ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                cliente.getUI()).setNorthPane(null);
            jDesktop.add(cliente);
            cliente.show();
        }else {
             JOptionPane.showMessageDialog(null, "No tiene permiso para obtener acceso a clientes","",JOptionPane.INFORMATION_MESSAGE);
        
        }
        
    }//GEN-LAST:event_jpanelclienteMouseReleased

    private void jpanelempleadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelempleadosMouseEntered
        // TODO add your handling code here:
          jpaneliconoempleados.setBackground(new java.awt.Color(77,161,227));
        
    }//GEN-LAST:event_jpanelempleadosMouseEntered

    private void jpanelempleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelempleadosMouseExited
        // TODO add your handling code here:
          jpaneliconoempleados.setBackground(new java.awt.Color(255,255,255));
       
    }//GEN-LAST:event_jpanelempleadosMouseExited

    private void jpanelventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelventaMouseEntered
        // TODO add your handling code here:
         jpaneliconoventa.setBackground(new java.awt.Color(77,161,227));
    }//GEN-LAST:event_jpanelventaMouseEntered

    private void jpanelventaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelventaMouseExited
        // TODO add your handling code here:
         jpaneliconoventa.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jpanelventaMouseExited

    private void jpanelproveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelproveedorMouseEntered
        // TODO add your handling code here:
        jpaneliconoproveedores.setBackground(new java.awt.Color(77,161,227));
    }//GEN-LAST:event_jpanelproveedorMouseEntered

    private void jpanelproveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelproveedorMouseExited
        // TODO add your handling code here:
        jpaneliconoproveedores.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jpanelproveedorMouseExited

    private void jpanelproductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelproductosMouseEntered
        // TODO add your handling code here:
          jpaneliconoproductos.setBackground(new java.awt.Color(77,161,227));
    }//GEN-LAST:event_jpanelproductosMouseEntered

    private void jpanelproductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelproductosMouseExited
        // TODO add your handling code here:
          jpaneliconoproductos.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_jpanelproductosMouseExited

    private void jlblcerrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblcerrarMouseReleased
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "¿Seguro de cerrar sesión?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            int componentes=jDesktop.getComponentCount();
            System.out.println("ventanas "+componentes);
            if(componentes==12){
                user.getdestruir();
                empleadosingleton.getdestruir();
                sucursalsingleton.getdestruir();
                MDIMenu.this.dispose();
                Login nuevaSesion = new Login();
                AWTUtilities.setWindowOpaque(nuevaSesion, false);
                nuevaSesion.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null, "Imposible cerrar sesión, cierre todas las ventanas abiertas e intente nuevamente","",JOptionPane.INFORMATION_MESSAGE);
            }

        }
        
    }//GEN-LAST:event_jlblcerrarMouseReleased

    private void jpanelbannerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelbannerMouseDragged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jpanelbannerMouseDragged

    private void jpanelbannerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpanelbannerMousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jpanelbannerMousePressed

    private void jlblmaxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmaxMouseReleased
        // TODO add your handling code here:
          this.setExtendedState(MAXIMIZED_BOTH);
        
        
    }//GEN-LAST:event_jlblmaxMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MDIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDIMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MDIMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton jbtnactualizar;
    private javax.swing.JLabel jlblcerrar;
    private javax.swing.JLabel jlblclienteaccesodir;
    private javax.swing.JLabel jlblclireg;
    private javax.swing.JLabel jlblempleadoaccesodir1;
    private javax.swing.JLabel jlblempleadomes;
    private javax.swing.JLabel jlblemplereg;
    private javax.swing.JLabel jlblhora;
    private javax.swing.JLabel jlblmax;
    private javax.swing.JLabel jlblmenucaja;
    private javax.swing.JLabel jlblmenuestado;
    private javax.swing.JLabel jlblmenufacturacion;
    private javax.swing.JLabel jlblmenuproducto;
    private javax.swing.JLabel jlblmenuregistro;
    private javax.swing.JLabel jlblmenurepara;
    private javax.swing.JLabel jlblmenuuser;
    private javax.swing.JLabel jlblmenuventa;
    private javax.swing.JLabel jlblminice;
    private javax.swing.JLabel jlblprodbajo;
    private javax.swing.JLabel jlblprodreg;
    private javax.swing.JLabel jlblproveedoraccesodir;
    private javax.swing.JLabel jlblprovreg;
    private javax.swing.JLabel jlblresize;
    private javax.swing.JLabel jlblrpruebaconexion;
    private javax.swing.JLabel jlblrut;
    private javax.swing.JLabel jlblsucursal;
    private javax.swing.JLabel jlbluser;
    private javax.swing.JLabel jlblusercabecera;
    private javax.swing.JLabel jlblventaanio;
    private javax.swing.JLabel jlblventadia;
    private javax.swing.JLabel jlblventames;
    private javax.swing.JLabel jlblventproc;
    private javax.swing.JPanel jpanelbanner;
    private javax.swing.JLabel jpanelcliente;
    private javax.swing.JPanel jpanelcontenedor;
    private javax.swing.JLabel jpanelempleados;
    private javax.swing.JPanel jpanelgrafico;
    private javax.swing.JPanel jpaneliconoclientes;
    private javax.swing.JPanel jpaneliconoempleados;
    private javax.swing.JPanel jpaneliconoproductos;
    private javax.swing.JPanel jpaneliconoproveedores;
    private javax.swing.JPanel jpaneliconoventa;
    private javax.swing.JLabel jpanelproductos;
    private javax.swing.JLabel jpanelproveedor;
    private javax.swing.JPanel jpanelresumen;
    private javax.swing.JLabel jpanelventa;
    // End of variables declaration//GEN-END:variables
}
