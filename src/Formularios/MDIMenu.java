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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    
     public MDIMenu() {
        initComponents();

      
        
        try {
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/codprod.png")).getImage());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no se encontro icono");
        }
//        this.user= user;
        ///ADAPTAR AL TAMAÑO DE PANTALLA
        dim=super.getToolkit().getScreenSize();
        super.setSize(dim);
       
//        super.setUndecorated(true);
//        super.setVisible(true);
        //////////////////////////////////////
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setExtendedState(EXIT_ON_CLOSE);
        
        jlbluser.setText(empleadosingleton.getNombre());
        jlblrut.setText(empleadosingleton.getRut());
        jlblsucursal.setText(sucursalsingleton.getNombre()+" - "+sucursalsingleton.getDireccion());
       
        
        
        
        // xxxxxxxxxxxxxxxxxx
//        this.empleadologin=empleadologin;
        //////////  INTEFARZAS SEGUN PRIVILEGIOS ////////////////
//        cargarinterfazsegunprivilegios();
        //////////////////////////////////////////////////////////
//        jPanel6.setVisible(false);
        
        //xxxxxxxxxxxxxxxxxxxxx
        reloj();
        
        
        
         
        list=daosucur.llenarcombo(jcbsucursal);
        verresumen();
//        jpanelresumen.setOpaque(false);
        pruebaconexion();
//        jpanelresumen.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
 
       
       
      
    }
     
      
     
     public void cargarresumen(){
        
        sucur=list.get(jcbsucursal.getSelectedIndex());
        Resumen res= daores.mostrar(sucur.getId());
        jlblprodreg.setText(res.getProdreg());
        jlblprodbajo.setText(res.getProdbajo());
        jlblclireg.setText(res.getClireg());
        jlblprovreg.setText(res.getProvreg());
        jlblemplereg.setText(res.getEmplereg());
        jlblventaanio.setText(res.getVentanio());
        jlblventames.setText(res.getVentames());
        jlblventadia.setText(res.getVentadia());
        jlblventproc.setText(res.getVentproc());
        jlblempleadomes.setText(res.getEmpleadomes());
//        
//        if(sucur.getId()!=0){
//        jbtnimprimir.setEnabled(true);
//        }else {
//        jbtnimprimir.setEnabled(false);
//        }
     }
//     
     public void verresumen(){
         boolean ver=false;
     if(user.isCliente()==true && user.isProveedor()==true && user.isCkempleado()==true && user.isUseri()==true && 
             user.isIngreso()==true && user.isProductos()==true &&
                  user.isInventario()==true &&  user.isFamilia()==true && user.isVender()
                     && user.isConsultarventa()==true && user.isApertura() ==true &&
                     user.isMovimientos()==true && user.isNueva()==true && user.isConsultarrepara()
                     && user.isPendientes()==true && user.isDebe() && user.isHaber()==true 
                     && user.isExtornar()==true && user.isProdpendientes()==true && 
                    user.isMermas()==true && user.isConsulmermas()==true && 
                     user.isSucursalida()==true && user.isSucurentrada()==true && user.isConsultasucur()==true){
     ver=true;
     
     }else {
     ver=false;
     }
     jlbltitulo.setVisible(ver);
     jcbsucursal.setVisible(ver);
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
     
     
     }
     
    public void menuusuario(){              
         JLabel titulo = new JLabel("USUARIO");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
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
         usuari.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         usuari.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
           JButton cerrar= new JButton("CERRAR SESION");
         cerrar.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   if(JOptionPane.showConfirmDialog(null, "SEGURO QUE DESEA CERRAR SESIÓN","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            int componentes=jDesktop.getComponentCount();
            System.out.println("ventanas "+componentes);
            if(componentes==6){
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
         cerrar.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         cerrar.setBackground(new java.awt.Color(255, 204, 51));
         
        
     
     }
    
     public void menumantenimiento(){
         JLabel titulo = new JLabel("MANT. REGISTRO");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
         ////////////////////////////////////////////////////////
         if(user.isCliente()==true){
         JButton client= new JButton("CLIENTES");
         client.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIFCliente cliente = new JIFCliente();
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
                   JIFProveedor proveedor = new JIFProveedor();
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
                JIFEmpleados empleado= new JIFEmpleados();
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
         emplead.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         emplead.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         /////////////////
         
     
     }
     public void menuproducto(){
         JLabel titulo = new JLabel("PRODUCTOS");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
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
         ingreso.setBounds(20, 70, 200, 50);
         ingreso.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         ingreso.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isProductos()==true){
        JButton produc= new JButton("PRODUCTOS");
         produc.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIProductos producto= new JIProductos();
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
         produc.setBounds(20, 140, 200, 50);
         produc.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         produc.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
         if(user.isInventario()==true){
             JButton invent= new JButton("INVENTARIO");
         invent.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               JIFrmInventario inventario= new JIFrmInventario();
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
          
         
         
         jpanelcontenedor.add(invent);
         invent.setBounds(20, 210, 200, 50);
         invent.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         invent.setBackground(new java.awt.Color(255, 204, 51));
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
         famili.setBounds(20, 280, 200, 50);
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
         pendient.setBounds(20, 350, 200, 50);
         pendient.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         pendient.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
         if(user.isMermas()==true){
            JButton merm= new JButton("MERMA");
         merm.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    JIFMerma merma= new JIFMerma();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = merma.getSize();
                 merma.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                  ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    merma.getUI()).setNorthPane(null);
                 jDesktop.add(merma);
                 merma.show();
                  panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
          
         
         jpanelcontenedor.add(merm);
         merm.setBounds(20, 420, 200, 50);
         merm.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         merm.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////
         if(user.isConsulmermas()==true){
          JButton conmerma= new JButton("CONSULT. MERMA");
         conmerma.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  JIFConsultarMerma consulmerma= new JIFConsultarMerma();
                 Dimension desktopSize = jDesktop.getSize();
                 Dimension FrameSize = consulmerma.getSize();
                 consulmerma.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 ((javax.swing.plaf.basic.BasicInternalFrameUI) 
                    consulmerma.getUI()).setNorthPane(null);
                 jDesktop.add(consulmerma);
                 consulmerma.show();
                  panelconmin();
//                  jlblmensajeinfo.setText("");
             }
         });
         
         
         jpanelcontenedor.add(conmerma);
         conmerma.setBounds(20, 490, 200, 50);
         conmerma.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         conmerma.setBackground(new java.awt.Color(255, 204, 51));
         ///////////////////////////////////////////////////////////
         }
     
     }
     
     
     
     public void menuventa(){
         JLabel titulo = new JLabel("VENTA");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
         ////////////////////////////////////////////////////////
         if(user.isVender()==true){
         JButton newventa= new JButton("NUEV. VENTA");
         newventa.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 JIFVenta Nventa = new JIFVenta();
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
         
         ////////////////////////////////////////////////////////
         if(user.isApertura()==true){
         JButton apertur= new JButton("APERTURA CAJA");
         apertur.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   CajaDAO daocaja= new CajaDAO();
                    Caja caja = new Caja();
                    caja=daocaja.validapertura(user.getIdempleado());
                    if(caja.getId_caja()==0){
                        JDAperturaCaja aperturaC = new JDAperturaCaja(new java.awt.Frame(), isVisible(),MDIMenu.this);
                        aperturaC.setVisible(true);
                    }else{
                        JIFCaja ncaja= new JIFCaja(caja);
                        
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
         movimi.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         movimi.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////////
     
         
     
     }
     
     //////////////////////////
     
        public void menureparacion(){
         JLabel titulo = new JLabel("REPARACION");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
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
         rpendient.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         rpendient.setBackground(new java.awt.Color(255, 204, 51));
         }
         ////////////////////////////////////////////////////////
         /////////////////
         
     
     }
      
       
      public void menuotros(){
         JLabel titulo = new JLabel("OTROS");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
         ////////////////////////////////////////////////////////
         if(user.isDebe()==true){
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
         debe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         debe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isHaber()==true){
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
      
    public void cargarImagen(){
    try{
//        JOptionPane.showMessageDialog(null,System.getProperty("java.home"));
//        jlblcerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
        InputStream fondo=getClass().getResourceAsStream("/imagenes/fondo-de-pantalla-paisaje.jpg");
        BufferedImage image=ImageIO.read(fondo);
        
        InputStream fondoresumen=getClass().getResourceAsStream("/imagenes/fondoresumen.png");
        BufferedImage imageresumen=ImageIO.read(fondoresumen);
        
        InputStream fondoiconos=getClass().getResourceAsStream("/imagenes/fondoiconos.png");
        BufferedImage imageiconos=ImageIO.read(fondoiconos);
        
//        jDesktop.setBorder(new javax.swing.border.Border(getClass().getResource("/imagenes/cerrar.png")));
        
        jDesktop.setBorder(new ImagenFondo(image));
        jpanelresumen.setBorder(new ImagenFondo(imageresumen));
        jPanel6.setBorder(new ImagenFondo(imageiconos));
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
    
     public void MaximizarJIFrame(javax.swing.JInternalFrame frame){
    try
        {
        frame.setSelected(true);
        frame.setMaximum(true);
    }
    catch (java.beans.PropertyVetoException e) {}
          frame.show();
           
    }
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
        jlblhora.setText("<html><center><p style=\"color:#000000\";>" + hour + "<br>" + date); 
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
                   
                    
                   jlblrpruebaconexion.setText("<html><p style=\"color:#333333\";>"+"Estado: En Línea");
                   jlblrpruebaconexion.setIcon(null);
                 
                   
               } else{
                    
                   
                   jlblrpruebaconexion.setText("<html><p style=\"color:#FF0000 \";>"+"No se obtuvo conexión con el servidor pongase en contacto con el administrador del sistema");
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
        jlblmenusucursal = new javax.swing.JLabel();
        jlblmenuregistro = new javax.swing.JLabel();
        jpanelcontenedor = new javax.swing.JPanel();
        jpanelresumen = new javax.swing.JPanel();
        jlblempleadomes = new javax.swing.JLabel();
        jlbluser = new javax.swing.JLabel();
        jlblemplereg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlblrut = new javax.swing.JLabel();
        jlblventadia = new javax.swing.JLabel();
        jlblprodreg = new javax.swing.JLabel();
        jlblresize = new javax.swing.JLabel();
        jlbltitulo = new javax.swing.JLabel();
        jlblclireg = new javax.swing.JLabel();
        jlblprodbajo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlblventames = new javax.swing.JLabel();
        jbtnactualizar = new javax.swing.JButton();
        jcbsucursal = new javax.swing.JComboBox();
        jlblventproc = new javax.swing.JLabel();
        jlblventaanio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblprovreg = new javax.swing.JLabel();
        jlblsucursal = new javax.swing.JLabel();
        jlblrpruebaconexion = new javax.swing.JLabel();
        jlblminice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu Principal");
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        setPreferredSize(super.getToolkit().getScreenSize());

        jDesktop.setBackground(new java.awt.Color(255, 255, 255));
        jDesktop.setForeground(new java.awt.Color(255, 255, 255));
        jDesktop.setEnabled(false);
        jDesktop.setOpaque(false);
        jDesktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDesktopMouseReleased(evt);
            }
        });

        jlblhora.setBackground(new java.awt.Color(0, 0, 0));
        jlblhora.setFont(new java.awt.Font("Segoe UI Light", 1, 48)); // NOI18N
        jlblhora.setForeground(new java.awt.Color(255, 255, 255));
        jlblhora.setText("* * *");

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.setOpaque(false);
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel6MouseReleased(evt);
            }
        });

        jlblmenuproducto.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenuproducto.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuproducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Product2.png"))); // NOI18N
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

        jlblmenuuser.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenuuser.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Key.png"))); // NOI18N
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

        jlblmenuventa.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenuventa.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuventa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventaaa.png"))); // NOI18N
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

        jlblmenucaja.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenucaja.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenucaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenucaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ATM.png"))); // NOI18N
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

        jlblmenurepara.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenurepara.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenurepara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenurepara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/repair.png"))); // NOI18N
        jlblmenurepara.setToolTipText("Reparaciones");
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

        jlblmenuestado.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenuestado.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuestado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuestado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/otros.png"))); // NOI18N
        jlblmenuestado.setToolTipText("Otros (Cotizacion, Orden Compra)");
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

        jlblmenusucursal.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenusucursal.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenusucursal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenusucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Office.png"))); // NOI18N
        jlblmenusucursal.setToolTipText("Sucursal (Envio de productos)");
        jlblmenusucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenusucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenusucursalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlblmenusucursalMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenusucursalMouseReleased(evt);
            }
        });

        jlblmenuregistro.setBackground(new java.awt.Color(220, 151, 96));
        jlblmenuregistro.setForeground(new java.awt.Color(220, 151, 96));
        jlblmenuregistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblmenuregistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user+.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblmenuregistro, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jlblmenuuser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuproducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenucaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenurepara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenusucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(jlblmenusucursal)
                .addContainerGap(227, Short.MAX_VALUE))
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
        jlblempleadomes.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblempleadomes.setForeground(new java.awt.Color(255, 255, 255));
        jlblempleadomes.setText("* * *");

        jlbluser.setBackground(new java.awt.Color(255, 255, 255));
        jlbluser.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlbluser.setForeground(new java.awt.Color(255, 255, 255));
        jlbluser.setText("---");

        jlblemplereg.setBackground(new java.awt.Color(255, 255, 255));
        jlblemplereg.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblemplereg.setForeground(new java.awt.Color(255, 255, 255));
        jlblemplereg.setText("* * *");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Bienvenido");

        jlblrut.setBackground(new java.awt.Color(255, 255, 255));
        jlblrut.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblrut.setForeground(new java.awt.Color(255, 255, 255));
        jlblrut.setText("---");

        jlblventadia.setBackground(new java.awt.Color(255, 255, 255));
        jlblventadia.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblventadia.setForeground(new java.awt.Color(255, 255, 255));
        jlblventadia.setText("* * *");

        jlblprodreg.setBackground(new java.awt.Color(255, 255, 255));
        jlblprodreg.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblprodreg.setForeground(new java.awt.Color(255, 255, 255));
        jlblprodreg.setText("* * *");

        jlblresize.setBackground(new java.awt.Color(255, 255, 255));
        jlblresize.setFont(new java.awt.Font("Segoe Script", 0, 12)); // NOI18N
        jlblresize.setForeground(new java.awt.Color(255, 255, 255));
        jlblresize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/arriba.png"))); // NOI18N
        jlblresize.setToolTipText("Clic para expandir o reducir");
        jlblresize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblresize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblresizeMouseReleased(evt);
            }
        });

        jlbltitulo.setBackground(new java.awt.Color(255, 255, 255));
        jlbltitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlbltitulo.setForeground(new java.awt.Color(255, 255, 255));
        jlbltitulo.setText("Resumen:");

        jlblclireg.setBackground(new java.awt.Color(255, 255, 255));
        jlblclireg.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblclireg.setForeground(new java.awt.Color(255, 255, 255));
        jlblclireg.setText("* * *");

        jlblprodbajo.setBackground(new java.awt.Color(255, 255, 255));
        jlblprodbajo.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblprodbajo.setForeground(new java.awt.Color(255, 255, 255));
        jlblprodbajo.setText("* * *");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("R.U.T.:");

        jlblventames.setBackground(new java.awt.Color(255, 255, 255));
        jlblventames.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblventames.setForeground(new java.awt.Color(255, 255, 255));
        jlblventames.setText("* * *");

        jbtnactualizar.setFont(new java.awt.Font("Segoe Script", 0, 12)); // NOI18N
        jbtnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Refresh_25px.png"))); // NOI18N
        jbtnactualizar.setPreferredSize(new java.awt.Dimension(55, 41));
        jbtnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnactualizarActionPerformed(evt);
            }
        });

        jcbsucursal.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jcbsucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbsucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbsucursalActionPerformed(evt);
            }
        });

        jlblventproc.setBackground(new java.awt.Color(255, 255, 255));
        jlblventproc.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblventproc.setForeground(new java.awt.Color(255, 255, 255));
        jlblventproc.setText("* * *");

        jlblventaanio.setBackground(new java.awt.Color(255, 255, 255));
        jlblventaanio.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblventaanio.setForeground(new java.awt.Color(255, 255, 255));
        jlblventaanio.setText("* * *");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sucursal:");

        jlblprovreg.setBackground(new java.awt.Color(255, 255, 255));
        jlblprovreg.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jlblprovreg.setForeground(new java.awt.Color(255, 255, 255));
        jlblprovreg.setText("* * *");

        jlblsucursal.setBackground(new java.awt.Color(255, 255, 255));
        jlblsucursal.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
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
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jlbluser, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jlblrut))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(jlblsucursal))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlbltitulo)
                        .addGap(12, 12, 12)
                        .addComponent(jcbsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblventaanio, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblventames, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblventadia, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblventproc, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblempleadomes, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblprodreg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblprodbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblclireg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblprovreg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jlblemplereg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jbtnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanelresumenLayout.setVerticalGroup(
            jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelresumenLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblresize)
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jlbluser))))
                .addGap(6, 6, 6)
                .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addComponent(jlblrut))
                .addGap(12, 12, 12)
                .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jlblsucursal))
                .addGap(12, 12, 12)
                .addGroup(jpanelresumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelresumenLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jcbsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
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

        jlblrpruebaconexion.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jlblrpruebaconexion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlblrpruebaconexion.setText("* * *");

        jlblminice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimize3.png"))); // NOI18N
        jlblminice.setToolTipText("Minimizar");
        jlblminice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblminice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblminiceMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelcontenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopLayout.createSequentialGroup()
                        .addComponent(jlblrpruebaconexion, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlblminice)
                        .addContainerGap())
                    .addGroup(jDesktopLayout.createSequentialGroup()
                        .addComponent(jlblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                        .addComponent(jpanelresumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199))))
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanelcontenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopLayout.createSequentialGroup()
                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblminice)
                    .addGroup(jDesktopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblrpruebaconexion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanelresumen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblhora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jDesktop.setLayer(jlblhora, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jPanel6, javax.swing.JLayeredPane.MODAL_LAYER);
        jDesktop.setLayer(jpanelcontenedor, javax.swing.JLayeredPane.MODAL_LAYER);
        jDesktop.setLayer(jpanelresumen, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jlblrpruebaconexion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jlblminice, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktop)
                .addGap(0, 0, 0))
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

    private void jlblmenusucursalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenusucursalMouseReleased
        // TODO add your handling code here:
         System.out.println(jpanelcontenedor.getComponentCount());
      if(jpanelcontenedor.getComponentCount()==0){
          menusucursal();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
        menusucursal();
      }
       panelconmax();
    }//GEN-LAST:event_jlblmenusucursalMouseReleased

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

    private void jcbsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbsucursalActionPerformed
        // TODO add your handling code here:
        if(list.size()>0){
            cargarresumen();
        }
    }//GEN-LAST:event_jcbsucursalActionPerformed

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
                dimm= new Dimension(376, 493);
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

    private void jlblmenusucursalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenusucursalMouseEntered
        // TODO add your handling code here:
       jlblmenusucursal.setOpaque(true);
          jlblmenusucursal.repaint();
          jlblmenusucursal.revalidate();
    }//GEN-LAST:event_jlblmenusucursalMouseEntered

    private void jlblmenusucursalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenusucursalMouseExited
        // TODO add your handling code here:
           jlblmenusucursal.setOpaque(false);
          jlblmenusucursal.repaint();
          jlblmenusucursal.revalidate();
    }//GEN-LAST:event_jlblmenusucursalMouseExited

    private void jlblmenucajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblmenucajaMouseExited
        // TODO add your handling code here:
        jlblmenucaja.setOpaque(false);
          jlblmenucaja.repaint();
          jlblmenucaja.revalidate();
    }//GEN-LAST:event_jlblmenucajaMouseExited

    private void jlblminiceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblminiceMouseReleased
        // TODO add your handling code here:
          setExtendedState(JFrame.CROSSHAIR_CURSOR);
    }//GEN-LAST:event_jlblminiceMouseReleased

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
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton jbtnactualizar;
    private javax.swing.JComboBox jcbsucursal;
    private javax.swing.JLabel jlblclireg;
    private javax.swing.JLabel jlblempleadomes;
    private javax.swing.JLabel jlblemplereg;
    private javax.swing.JLabel jlblhora;
    private javax.swing.JLabel jlblmenucaja;
    private javax.swing.JLabel jlblmenuestado;
    private javax.swing.JLabel jlblmenuproducto;
    private javax.swing.JLabel jlblmenuregistro;
    private javax.swing.JLabel jlblmenurepara;
    private javax.swing.JLabel jlblmenusucursal;
    private javax.swing.JLabel jlblmenuuser;
    private javax.swing.JLabel jlblmenuventa;
    private javax.swing.JLabel jlblminice;
    private javax.swing.JLabel jlblprodbajo;
    private javax.swing.JLabel jlblprodreg;
    private javax.swing.JLabel jlblprovreg;
    private javax.swing.JLabel jlblresize;
    private javax.swing.JLabel jlblrpruebaconexion;
    private javax.swing.JLabel jlblrut;
    private javax.swing.JLabel jlblsucursal;
    private javax.swing.JLabel jlbltitulo;
    private javax.swing.JLabel jlbluser;
    private javax.swing.JLabel jlblventaanio;
    private javax.swing.JLabel jlblventadia;
    private javax.swing.JLabel jlblventames;
    private javax.swing.JLabel jlblventproc;
    private javax.swing.JPanel jpanelcontenedor;
    private javax.swing.JPanel jpanelresumen;
    // End of variables declaration//GEN-END:variables
}
