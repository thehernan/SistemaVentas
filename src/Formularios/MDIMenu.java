/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.VentasDAO;
import ClasesGlobales.ImagenFondo;
import ClasesGlobales.LookAndFeel;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoicono.png")).getImage());
            
        } catch (Exception e) {
        }
//        this.user= user;
        ///ADAPTAR AL TAMAÑO DE PANTALLA
        dim=super.getToolkit().getScreenSize();
        super.setSize(dim);
       
//        super.setUndecorated(true);
//        super.setVisible(true);
        //////////////////////////////////////
       // this.setExtendedState(MAXIMIZED_BOTH);
        jlbluser.setText(empleadosingleton.getNombre());
        jlblrut.setText(empleadosingleton.getRut());
        jlblsucursal.setText(sucursalsingleton.getNombre());
        jlblsucursaldireccion.setText(sucursalsingleton.getDireccion());
//        this.empleadologin=empleadologin;
        //////////  INTEFARZAS SEGUN PRIVILEGIOS ////////////////
//        cargarinterfazsegunprivilegios();
        //////////////////////////////////////////////////////////
//        jPanel6.setVisible(false);
        reloj();
        
        list=daosucur.llenarcombo(jcbsucursal);
        verresumen();
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
        if(sucur.getId()!=0){
        jbtnimprimir.setEnabled(true);
        }else {
        jbtnimprimir.setEnabled(false);
        }
     }
     
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
     jbtnimprimir.setVisible(ver);
     jbtnactualizar.setVisible(ver);
     
     
     }
     
    public void menuusuario(){              
         JLabel titulo = new JLabel("USUARIO");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
         ////////////////////////////////////////////////////////
         JButton usuari= new JButton("USUARIOS");
         usuari.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  panelconmin();
                   JIFUsuarios user = new JIFUsuarios();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = user.getSize();
//                int x=(jDesktop.getWidth()/2)-user.getWidth()/2;
//                int y=(jDesktop.getHeight()/2)-user.getHeight()/2;
//                user.setLocation(x,y);
//                user.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(user);
                user.show();
                
                 jlblmensajeinfo.setText("");
             }
         });
         usuari.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
               }
             
        });
         
         
         
         jpanelcontenedor.add(usuari);
         usuari.setBounds(20, 70, 200, 50);
         usuari.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         usuari.setBackground(new java.awt.Color(255, 204, 51));
         
         /////////////////////////////////////////////////////////
           JButton cerrar= new JButton("CERRAR SESION");
         cerrar.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   if(JOptionPane.showConfirmDialog(null, "SEGURO QUE DESEA CERRAR SESIÓN","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            int componentes=jDesktop.getComponentCount();
            System.out.println("ventanas "+componentes);
            if(componentes==3){
                user.getdestruir();
                empleadosingleton.getdestruir();
                sucursalsingleton.getdestruir();
                MDIMenu.this.dispose();
                Login nuevaSesion = new Login();
                AWTUtilities.setWindowOpaque(nuevaSesion, false);
                nuevaSesion.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null, "IMPOSIBLE CERRAR SESION, CIERRE LAS VENTANAS ABIERTAS E INTENTE NUEVAMENTE","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
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
//                cliente.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(cliente);
                cliente.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          client.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
               }
             
        });
         
         
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
//                proveedor.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(proveedor);
                proveedor.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
         proveed.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
               }
             
        });
         
         
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
//              empleado.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              jDesktop.add(empleado);
              empleado.show();
               panelconmin();
                jlblmensajeinfo.setText("");   
             }
         });
          emplead.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                Ingreprod.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(Ingreprod);
                Ingreprod.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
           ingreso.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                producto.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(producto);
                producto.show();
                 panelconmin();
                 jlblmensajeinfo.setText("");
             }
         });
          produc.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//              inventario.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              jDesktop.add(inventario);
              inventario.show();
               panelconmin();
                 
             }
         });
          invent.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                 familia.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 jDesktop.add(familia);
                 familia.show();
                  panelconmin();
                   jlblmensajeinfo.setText("");
             }
         });
         famili.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                 ingresopend.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 jDesktop.add(ingresopend);
                 ingresopend.show();
                  panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          pendient.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                 merma.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 jDesktop.add(merma);
                 merma.show();
                  panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          merm.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                 consulmerma.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                 jDesktop.add(consulmerma);
                 consulmerma.show();
                  panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          conmerma.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                Nventa.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(Nventa);
                Nventa.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          newventa.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                enproceso.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(enproceso);
                enproceso.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
         proceso.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//              ventaconsultar.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              jDesktop.add(ventaconsultar);
              ventaconsultar.show();
               panelconmin();
                jlblmensajeinfo.setText("");  
             }
         });
          consventa.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                        ncaja.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                        jDesktop.add(ncaja);
                        ncaja.show();
                    }
                 
                 
                
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          apertur.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                movimientoc.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(movimientoc);
                movimientoc.show();
                 panelconmin();
                 
             }
         });
          movimi.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                reparacion.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(reparacion);
                reparacion.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
         
         nuevar.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//                consultar.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(consultar);
                consultar.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
         consultr.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
//              reparacionPendiente.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
              jDesktop.add(reparacionPendiente);
              reparacionPendiente.show();
               panelconmin();
                jlblmensajeinfo.setText(""); 
             }
         });
          rpendient.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
      
       
      public void menuestado(){
         JLabel titulo = new JLabel("EST. CUENTA");
         jpanelcontenedor.add(titulo);
          titulo.setBounds(60, 10, 200, 50);
         titulo.setFont(new java.awt.Font("Segoe UI Light", 1, 20));
         
         ////////////////////////////////////////////////////////
         if(user.isDebe()==true){
         JButton debe= new JButton("DEBE");
         debe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
                          JIFEstadoCuentaDebe estadodebe = new JIFEstadoCuentaDebe();
                        
                        Dimension desktopSize = jDesktop.getSize();
                        Dimension FrameSize = estadodebe.getSize();
//                        estadodebe.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                        jDesktop.add(estadodebe);
                        estadodebe.show();
                  
                 
                
                 panelconmin();
                  jlblmensajeinfo.setText("");
                 
             }
         });
           debe.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
               }
             
        });
         
         
         jpanelcontenedor.add(debe);
         debe.setBounds(20, 70, 200, 50);
         debe.setFont(new java.awt.Font("Segoe UI Light", 1, 14));
         debe.setBackground(new java.awt.Color(255, 204, 51));
         }
         /////////////////////////////////////////////////////////
         if(user.isHaber()==true){
        JButton habe= new JButton("HABER");
         habe.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 JIFEstadocuentaHaber estadohaber = new JIFEstadocuentaHaber();
                Dimension desktopSize = jDesktop.getSize();
                Dimension FrameSize = estadohaber.getSize();
//                estadohaber.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
                jDesktop.add(estadohaber);
                estadohaber.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
           habe.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
               }
             
        });
         
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
                jDesktop.add(ordensalida);
                ordensalida.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
          salida.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
                jDesktop.add(ordenentrada);
                ordenentrada.show();
                 panelconmin();
                  jlblmensajeinfo.setText("");
             }
         });
         entrad.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
              jDesktop.add(movsucursal);
              movsucursal.show();
               panelconmin();
               jlblmensajeinfo.setText("");   
             }
         });
         conssucur.addMouseListener(new MouseAdapter() {
              public void mousePressed(MouseEvent evt) {
              jlblmensajeinfo.setText("Cargando datos .....");
                }
               public void mouseExited(MouseEvent evt) {
                 jlblmensajeinfo.setText("");
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
        InputStream fondo=this.getClass().getResourceAsStream("/imagenes/fondopsdgsmstore.jpg");
        BufferedImage image=ImageIO.read(fondo);
        jDesktop.setBorder(new ImagenFondo(image));}
    catch (Exception e){
            JOptionPane.showMessageDialog(null,"no se encontro");
                    
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
     jDesktop.add(caja);
     caja.show();
     }
     
       public void panelconmin(){
      Dimension dimm= new Dimension(0, 0);
      jpanelcontenedor.setPreferredSize(dimm);
      jpanelcontenedor.revalidate();
//      jDesktop.revalidate();
      
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
        jlblhora.setText("<html><center>" + hour + "<br>" + date); 
           } 
        }); 
        timer.start(); 
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

        jPanel6 = new javax.swing.JPanel();
        jlblmenuuser = new javax.swing.JLabel();
        jlblmenuproducto = new javax.swing.JLabel();
        jlblmenuventa = new javax.swing.JLabel();
        jlblmenucaja = new javax.swing.JLabel();
        jlblmenurepara = new javax.swing.JLabel();
        jlblmenuestado = new javax.swing.JLabel();
        jlblmenusucursal = new javax.swing.JLabel();
        jlblmenuregistro = new javax.swing.JLabel();
        jlblhora = new javax.swing.JLabel();
        jDesktop = new javax.swing.JDesktopPane();
        jpanelcontenedor = new javax.swing.JPanel();
        jlblmensajeinfo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlbluser = new javax.swing.JLabel();
        jlblrut = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblsucursal = new javax.swing.JLabel();
        jlblsucursaldireccion = new javax.swing.JLabel();
        jcbsucursal = new javax.swing.JComboBox();
        jlblprodreg = new javax.swing.JLabel();
        jlblprodbajo = new javax.swing.JLabel();
        jlblventaanio = new javax.swing.JLabel();
        jlblprovreg = new javax.swing.JLabel();
        jlblventadia = new javax.swing.JLabel();
        jlblclireg = new javax.swing.JLabel();
        jlblempleadomes = new javax.swing.JLabel();
        jlblventames = new javax.swing.JLabel();
        jlblventproc = new javax.swing.JLabel();
        jlbltitulo = new javax.swing.JLabel();
        jlblemplereg = new javax.swing.JLabel();
        jbtnactualizar = new javax.swing.JButton();
        jbtnimprimir = new javax.swing.JButton();
        jlblmensajeprint = new javax.swing.JLabel();
        jlblfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SISTEMA DE VENTAS");
        setUndecorated(true);
        setPreferredSize(super.getToolkit().getScreenSize());
        setResizable(false);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel6MouseReleased(evt);
            }
        });

        jlblmenuuser.setBackground(new java.awt.Color(255, 255, 255));
        jlblmenuuser.setForeground(new java.awt.Color(255, 255, 255));
        jlblmenuuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Key.png"))); // NOI18N
        jlblmenuuser.setToolTipText("Usuario");
        jlblmenuuser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlblmenuuserMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuuserMouseReleased(evt);
            }
        });

        jlblmenuproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Product2.png"))); // NOI18N
        jlblmenuproducto.setToolTipText("Productos");
        jlblmenuproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuproductoMouseReleased(evt);
            }
        });

        jlblmenuventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventaaa.png"))); // NOI18N
        jlblmenuventa.setToolTipText("Venta");
        jlblmenuventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuventaMouseReleased(evt);
            }
        });

        jlblmenucaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ATM.png"))); // NOI18N
        jlblmenucaja.setToolTipText("Caja");
        jlblmenucaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenucaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenucajaMouseReleased(evt);
            }
        });

        jlblmenurepara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/repair.png"))); // NOI18N
        jlblmenurepara.setToolTipText("Reparaciones");
        jlblmenurepara.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenurepara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenureparaMouseReleased(evt);
            }
        });

        jlblmenuestado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/money.png"))); // NOI18N
        jlblmenuestado.setToolTipText("Estado Cuenta");
        jlblmenuestado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuestado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuestadoMouseReleased(evt);
            }
        });

        jlblmenusucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Office.png"))); // NOI18N
        jlblmenusucursal.setToolTipText("Sucursal");
        jlblmenusucursal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenusucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenusucursalMouseReleased(evt);
            }
        });

        jlblmenuregistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user+.png"))); // NOI18N
        jlblmenuregistro.setToolTipText("Mantenimiento Registro");
        jlblmenuregistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblmenuregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblmenuregistroMouseReleased(evt);
            }
        });

        jlblhora.setBackground(new java.awt.Color(255, 255, 255));
        jlblhora.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jlblhora.setForeground(new java.awt.Color(255, 255, 255));
        jlblhora.setText("* * *");

        jDesktop.setBackground(new java.awt.Color(204, 255, 255));
        jDesktop.setForeground(new java.awt.Color(204, 255, 255));
        jDesktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDesktopMouseReleased(evt);
            }
        });

        jpanelcontenedor.setBackground(new java.awt.Color(52, 152, 219));
        jpanelcontenedor.setForeground(new java.awt.Color(52, 152, 219));
        jpanelcontenedor.setInheritsPopupMenu(true);
        jpanelcontenedor.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout jpanelcontenedorLayout = new javax.swing.GroupLayout(jpanelcontenedor);
        jpanelcontenedor.setLayout(jpanelcontenedorLayout);
        jpanelcontenedorLayout.setHorizontalGroup(
            jpanelcontenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpanelcontenedorLayout.setVerticalGroup(
            jpanelcontenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        jlblmensajeinfo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblmensajeinfo.setForeground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbluser.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlbluser.setForeground(new java.awt.Color(0, 0, 0));
        jlbluser.setText("---");
        jPanel1.add(jlbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jlblrut.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblrut.setForeground(new java.awt.Color(0, 0, 0));
        jlblrut.setText("---");
        jPanel1.add(jlblrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Sucursal:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jlblsucursal.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblsucursal.setForeground(new java.awt.Color(0, 0, 0));
        jlblsucursal.setText("---");
        jPanel1.add(jlblsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jlblsucursaldireccion.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblsucursaldireccion.setForeground(new java.awt.Color(0, 0, 0));
        jlblsucursaldireccion.setText("---");
        jPanel1.add(jlblsucursaldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jcbsucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbsucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbsucursalActionPerformed(evt);
            }
        });
        jPanel1.add(jcbsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 200, -1));

        jlblprodreg.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblprodreg.setForeground(new java.awt.Color(0, 0, 0));
        jlblprodreg.setText("* * *");
        jPanel1.add(jlblprodreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 390, -1));

        jlblprodbajo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblprodbajo.setForeground(new java.awt.Color(0, 0, 0));
        jlblprodbajo.setText("* * *");
        jPanel1.add(jlblprodbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 380, -1));

        jlblventaanio.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblventaanio.setForeground(new java.awt.Color(0, 0, 0));
        jlblventaanio.setText("* * *");
        jPanel1.add(jlblventaanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 360, -1));

        jlblprovreg.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblprovreg.setForeground(new java.awt.Color(0, 0, 0));
        jlblprovreg.setText("* * *");
        jPanel1.add(jlblprovreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 300, -1));

        jlblventadia.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblventadia.setForeground(new java.awt.Color(0, 0, 0));
        jlblventadia.setText("* * *");
        jPanel1.add(jlblventadia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 320, -1));

        jlblclireg.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblclireg.setForeground(new java.awt.Color(0, 0, 0));
        jlblclireg.setText("* * *");
        jPanel1.add(jlblclireg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 300, -1));

        jlblempleadomes.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblempleadomes.setForeground(new java.awt.Color(0, 0, 0));
        jlblempleadomes.setText("* * *");
        jPanel1.add(jlblempleadomes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 320, -1));

        jlblventames.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblventames.setForeground(new java.awt.Color(0, 0, 0));
        jlblventames.setText("* * *");
        jPanel1.add(jlblventames, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 370, -1));

        jlblventproc.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblventproc.setForeground(new java.awt.Color(0, 0, 0));
        jlblventproc.setText("* * *");
        jPanel1.add(jlblventproc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 330, -1));

        jlbltitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlbltitulo.setForeground(new java.awt.Color(0, 0, 0));
        jlbltitulo.setText("Resumen:");
        jPanel1.add(jlbltitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

        jlblemplereg.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jlblemplereg.setForeground(new java.awt.Color(0, 0, 0));
        jlblemplereg.setText("* * *");
        jPanel1.add(jlblemplereg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 360, -1));

        jbtnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update.png"))); // NOI18N
        jbtnactualizar.setPreferredSize(new java.awt.Dimension(55, 41));
        jbtnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnactualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 410, 60, -1));

        jbtnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnimprimir.setToolTipText("");
        jbtnimprimir.setEnabled(false);
        jbtnimprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnimprimirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnimprimirMousePressed(evt);
            }
        });
        jbtnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnimprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, -1, -1));

        jlblmensajeprint.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblmensajeprint.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlblmensajeprint, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 170, 20));

        jlblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/panel.png"))); // NOI18N
        jlblfondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jlblfondoMouseReleased(evt);
            }
        });
        jPanel1.add(jlblfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 460, 480));

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopLayout.createSequentialGroup()
                .addComponent(jpanelcontenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblmensajeinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(2636, Short.MAX_VALUE))
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jpanelcontenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDesktopLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblmensajeinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jDesktop.setLayer(jpanelcontenedor, javax.swing.JLayeredPane.MODAL_LAYER);
        jDesktop.setLayer(jlblmensajeinfo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktop.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jlblhora, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205)
                .addComponent(jlblmenuuser)
                .addGap(52, 52, 52)
                .addComponent(jlblmenuregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jlblmenuproducto)
                .addGap(44, 44, 44)
                .addComponent(jlblmenuventa)
                .addGap(61, 61, 61)
                .addComponent(jlblmenucaja)
                .addGap(35, 35, 35)
                .addComponent(jlblmenurepara)
                .addGap(36, 36, 36)
                .addComponent(jlblmenuestado)
                .addGap(43, 43, 43)
                .addComponent(jlblmenusucursal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jDesktop)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jDesktop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblmenuuser, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jlblmenuproducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenucaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenurepara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenusucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblmenuregistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblhora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
          menuestado();
      }else {
          jpanelcontenedor.removeAll();
          jpanelcontenedor.repaint();
         menuestado();
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
      
       
    }//GEN-LAST:event_jlblmenuuserMouseEntered

    private void jDesktopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopMouseReleased
        // TODO add your handling code here:
        panelconmin();
    }//GEN-LAST:event_jDesktopMouseReleased

    private void jlblfondoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblfondoMouseReleased
        // TODO add your handling code here:
         panelconmin();
    }//GEN-LAST:event_jlblfondoMouseReleased

    private void jPanel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseReleased
        // TODO add your handling code here:
         panelconmin();
    }//GEN-LAST:event_jPanel6MouseReleased

    private void jcbsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbsucursalActionPerformed
        // TODO add your handling code here:
        if(list.size()>0){
          cargarresumen();
        }
       
    }//GEN-LAST:event_jcbsucursalActionPerformed

    private void jbtnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnactualizarActionPerformed
        // TODO add your handling code here:
        cargarresumen();
    }//GEN-LAST:event_jbtnactualizarActionPerformed

    private void jbtnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirActionPerformed
        // TODO add your handling code here:
        daores.imprimir(sucur.getId(), sucur.getNombre());
        jlblmensajeprint.setText("");
    }//GEN-LAST:event_jbtnimprimirActionPerformed

    private void jbtnimprimirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnimprimirMousePressed
        // TODO add your handling code here:
        if(sucur.getId()!=0){
         jlblmensajeprint.setText("Generando reporte ...");
        }
       
    }//GEN-LAST:event_jbtnimprimirMousePressed

    private void jbtnimprimirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnimprimirMouseExited
        // TODO add your handling code here:
         jlblmensajeprint.setText("");
    }//GEN-LAST:event_jbtnimprimirMouseExited

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton jbtnactualizar;
    private javax.swing.JButton jbtnimprimir;
    private javax.swing.JComboBox jcbsucursal;
    private javax.swing.JLabel jlblclireg;
    private javax.swing.JLabel jlblempleadomes;
    private javax.swing.JLabel jlblemplereg;
    private javax.swing.JLabel jlblfondo;
    private javax.swing.JLabel jlblhora;
    private javax.swing.JLabel jlblmensajeinfo;
    private javax.swing.JLabel jlblmensajeprint;
    private javax.swing.JLabel jlblmenucaja;
    private javax.swing.JLabel jlblmenuestado;
    private javax.swing.JLabel jlblmenuproducto;
    private javax.swing.JLabel jlblmenuregistro;
    private javax.swing.JLabel jlblmenurepara;
    private javax.swing.JLabel jlblmenusucursal;
    private javax.swing.JLabel jlblmenuuser;
    private javax.swing.JLabel jlblmenuventa;
    private javax.swing.JLabel jlblprodbajo;
    private javax.swing.JLabel jlblprodreg;
    private javax.swing.JLabel jlblprovreg;
    private javax.swing.JLabel jlblrut;
    private javax.swing.JLabel jlblsucursal;
    private javax.swing.JLabel jlblsucursaldireccion;
    private javax.swing.JLabel jlbltitulo;
    private javax.swing.JLabel jlbluser;
    private javax.swing.JLabel jlblventaanio;
    private javax.swing.JLabel jlblventadia;
    private javax.swing.JLabel jlblventames;
    private javax.swing.JLabel jlblventproc;
    private javax.swing.JPanel jpanelcontenedor;
    // End of variables declaration//GEN-END:variables
}
