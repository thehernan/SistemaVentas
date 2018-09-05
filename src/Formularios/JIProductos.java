/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import ClasesGlobales.Mayusculas;
import DAO.FamiliaDAO;
import DAO.ProductoDAO;
import DAO.UnidadMedidaDAO;
import Pojos.Familia;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import Pojos.Unidad_Medida;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author info2017
 */
public class JIProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIProductos
     */
   ProductoDAO daoproducto = new ProductoDAO();
   Boolean editar;
 
   String rutaimagen=null;
   byte[]  fotoB=new byte[0];
   
   Familia familia =new Familia();
   SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
   Mayusculas mayus = new Mayusculas();
   List<Producto> listprod= new ArrayList<>();
    FamiliaDAO daofamilia = new FamiliaDAO();
    List<Familia> listfamilia= new ArrayList<>();
    Producto producto = new Producto();
    int posx;
    int posy;
    List<Unidad_Medida> listunidad;
    FormatoNumerico fn = new FormatoNumerico();
    MDIMenu menu;
    public JIProductos() {
        
    }
    public JIProductos(MDIMenu menu) {
        initComponents();
        jlblcargando.setVisible(false);
        jlblcargafoto.setVisible(false);
        bloquearjtf( false, false, false,false,false,false,false,false);
        bloquearjbtn(true, false, false, false, false, true,false,false,false);
        jbtngenerarcodigo.setEnabled(false);      
        jtaobservacion.setLineWrap(true);
        
        mostrar();    
        mostrarunidadm();
        this.menu=menu;
    }
    public void mostrar() {
        Runnable runnable = new Runnable() {
            public void run() {
                    jlblcargando.setVisible(true);  
                    jlblcargandoletra.setVisible(true);
                    jcbfamilia.setEnabled(false);
                    jtfbuscarproducto.setEnabled(false);
                    long tiempoInicio = System.currentTimeMillis();
                       
                        
                    listfamilia=daofamilia.llenarcombo(jcbfamilia);
                    listprod= daoproducto.mostrarproducto(jtablaproducto,sucursalsingleton.getId());
                    
                    long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                    jlbltiempoproceso.setText("Proceso de carga "+totalTiempo + " miliseg");
                    
                    jcbfamilia.setEnabled(true);
                    jtfbuscarproducto.setEnabled(true);
                    jlblcargando.setVisible(false);
                    jlblcargandoletra.setVisible(false);
             
            }
        };
        Thread M = new Thread(runnable);
        M.start();
        

}
    public void bloquearjtf(boolean codigo, boolean descripcion,boolean observacion,boolean precio,boolean precio1
    ,boolean precio2,boolean precio3,boolean moneda){
        jtfcodigo.setEnabled(codigo);
        jtfdescripcion.setEnabled(descripcion);
        jtaobservacion.setEnabled(observacion);
        jtfprecio.setEnabled(precio);
        jtfprecio1.setEnabled(precio1);
        jtfprecio2.setEnabled(precio2);
        jtfprecio3.setEnabled(precio3);
        jcbunidmedida.setEnabled(moneda);
     
    
    }
    public void bloquearjbtn(boolean nuevo,boolean editar,boolean guardar,boolean eliminar,boolean cancelar,
    boolean salir,boolean foto,boolean codbarra,boolean familia)
    {///,boolean codigo ){
    jbtnnuevo.setEnabled(nuevo);
    jbtneditar.setEnabled(editar);
    jbtnguardar.setEnabled(guardar);
    jbtneliminar.setEnabled(eliminar);
    jbtncancelar.setEnabled(cancelar);
    
    jbtnfoto.setEnabled(foto);
    jbtngeneracodigobarras.setEnabled(codbarra);
    jbtnbuscarfamilia.setEnabled(familia);
   // jbtngenerarcodigo.setEnabled(codigo);
    
    }
     public void limpiarjtf(){
    jtfcodigo.setText("CODIGO");
    jtfdescripcion.setText("DESCRIPCION");
    jtaobservacion.setText("OBSERVACION");
    jtffamilia.setText("FAMILIA");
    jlblImageProducto.setIcon(null);
    jtfprecio.setText("");
    jtfprecio1.setText("");
    jtfprecio2.setText("");
    jtfprecio3.setText("");
    familia = new Familia();
    }
    public void validaguardar(){
        try {
            String cod= jtfcodigo.getText().replaceAll("\\s","");
            String jfamilia= jtffamilia.getText().replaceAll("\\s","");
            String desc = jtfdescripcion.getText().replaceAll("\\s","");
            if(!cod.equals("CODIGO") && cod.length() > 4 && !jfamilia.equals("FAMILIA") && !jfamilia.equals("") &&
                    Double.parseDouble(jtfprecio.getText()) >=0 && Double.parseDouble(jtfprecio1.getText()) >=0 &&
                    Double.parseDouble(jtfprecio2.getText()) >=0 &&Double.parseDouble(jtfprecio3.getText()) >=0 && desc.length() > 0
                    && !jtfdescripcion.getText().equals("DESCRIPCION")){
                bloquearjbtn(false, false, true,false ,true, true,true,false,true);
            }else {
                 bloquearjbtn(true, false, false, false, true, true,true,false,true);

            }
            
            
            
            
            
        } catch (Exception e) {
            bloquearjbtn(true, false, false, false, true, true,true,false,true);
        }
       
    
    }
    public void setfamilia(Familia familia){
    this.familia= familia;
    jtffamilia.setText(familia.getDescripcion());
    
    }
    public  void buscar(){
//        try {
        if(jtablaproducto.getSelectedRow()>=0){
        Producto prodb;
        jlblcargafoto.setVisible(true);
            System.out.println("tamañoL"+listprod.size());
            System.out.println("tamañoT"+jtablaproducto.getRowCount());
        if(listprod.size()==jtablaproducto.getRowCount()){
        prodb = listprod.get(jtablaproducto.getSelectedRow());
//        id = Long.parseLong(jtablaproducto.getValueAt(jtablaproducto.getSelectedRow(), 0).toString());
            System.out.println("idprod: "+prodb.getIdproducto());
        producto = daoproducto.buscarproducto("ID", prodb.getIdproducto(),sucursalsingleton.getId(),"");
        
        /// mostrar imagen ///
        ImageIcon imageIcon = new ImageIcon(this.producto.getFoto());
        //ImageIcon imageUser = imageIcon;
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(jlblImageProducto.getWidth(), jlblImageProducto.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(newimg);
        jlblImageProducto.setIcon(imageIcon);   
        //////////////////////////////////////////////
        jtfcodigo.setText(producto.getCodigo());
        jtfdescripcion.setText(producto.getDescripcion());
        jtaobservacion.setText(producto.getObservacion());
        //jtffamilia.setText(producto.getDireccion());
        jtffamilia.setText(producto.getDescripfamilia());
        familia.setIdfamilia(producto.getIdfamilia());
        fotoB= producto.getFoto();
        jtfprecio.setText(fn.FormatoN(producto.getPrecio()));
        jtfprecio1.setText(fn.FormatoN(producto.getPrecio1()));
        jtfprecio2.setText(fn.FormatoN(producto.getPrecio2()));
        jtfprecio3.setText(fn.FormatoN(producto.getPrecio3()));
        jcbunidmedida.setSelectedItem(producto.getUnidadm()+" - "+producto.getUnidabrev());
//        jcbmoneda.setSelectedItem(producto.getMoneda());
//        jspmargen.setValue(producto.getMargenG());
        bloquearjbtn(true, true, false, true, false, true,false,true,false);
        jbtngenerarcodigo.setEnabled(false);
        }
        }else {
                limpiarjtf();

                 bloquearjtf( false, false, false,false,false,false,false,false);
                 bloquearjbtn(true, false, false, false, false, true,false,false,false);
                 jbtngenerarcodigo.setEnabled(false);

            }
         
//        } catch (Exception e) {
//            System.out.println(" b"+e);
//        }
    jlblcargafoto.setVisible(false);    
   
    }
    
    public  void sensitiva(){
        
        Runnable runnable = new Runnable() {

              @Override
              public void run() {
   //               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   long tiempoInicio = System.currentTimeMillis();

                   jlblcargando.setVisible(true);  
                   jlblcargandoletra.setVisible(true);
                   ////////////////////////////////////////
                   
                     System.out.println("listfamia"+listfamilia.size());
                     String descrip= jtfbuscarproducto.getText().toUpperCase();
                     if(listfamilia.size()>0){
                         Familia fam;
                         fam=listfamilia.get(jcbfamilia.getSelectedIndex());
                         System.out.println("idfamilia"+fam.getIdfamilia());


                         if(fam.getIdfamilia()==0){
                          listprod=daoproducto.busquedasensitivaproducto(jtablaproducto,"TODO", descrip,
                          sucursalsingleton.getId(),fam.getIdfamilia());


                         }else {
                         listprod=daoproducto.busquedasensitivaproducto(jtablaproducto,"", descrip,
                             sucursalsingleton.getId(),fam.getIdfamilia());
                         }
                      buscar();

                     }
                  ////////////////////////////////////////
                  
                   jlblcargando.setVisible(false);
                   jlblcargandoletra.setVisible(false);

                   long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                   jlbltiempoproceso.setText("Proceso de busqueda "+totalTiempo + " miliseg");
                   bloquearjtf(false, false, false, false,false, false, false, false);
                }
            };
    
            Thread M = new Thread(runnable);
            M.start();
  
    }
    
    public void mostrarunidadm()
    {
        UnidadMedidaDAO  unddao = new UnidadMedidaDAO();
        listunidad = unddao.mostrar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Unidad_Medida unid: listunidad)
        {
            model.addElement(unid.getMedida()+" - "+unid.getAbreviatura());
        
        }
        jcbunidmedida.setModel(model);
        
        
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfbuscarproducto = new javax.swing.JTextField();
        jlblcargandoletra = new javax.swing.JLabel();
        jlblcargando = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtablaproducto = new javax.swing.JTable();
        jbtnnuevo = new javax.swing.JButton();
        jbtneditar = new javax.swing.JButton();
        jbtnguardar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtncancelar = new javax.swing.JButton();
        jtfcodigo = new javax.swing.JTextField();
        jtfdescripcion = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtaobservacion = new javax.swing.JTextArea();
        jbtngenerarcodigo = new javax.swing.JButton();
        jtffamilia = new javax.swing.JTextField();
        jbtnbuscarfamilia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcbfamilia = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlbltiempoproceso = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcbunidmedida = new javax.swing.JComboBox();
        jlblImageProducto = new javax.swing.JLabel();
        jbtnfoto = new javax.swing.JButton();
        jbtngeneracodigobarras = new javax.swing.JButton();
        jlblcargafoto = new javax.swing.JLabel();
        jtfprecio = new javax.swing.JTextField();
        jtfprecio1 = new javax.swing.JTextField();
        jtfprecio2 = new javax.swing.JTextField();
        jtfprecio3 = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Buscar:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 74, -1, -1));

        jtfbuscarproducto.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscarproducto.setToolTipText("");
        jtfbuscarproducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarproductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarproductoFocusLost(evt);
            }
        });
        jtfbuscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfbuscarproductoActionPerformed(evt);
            }
        });
        jtfbuscarproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfbuscarproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarproductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscarproductoKeyTyped(evt);
            }
        });
        jPanel4.add(jtfbuscarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 74, 320, -1));

        jlblcargandoletra.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblcargandoletra.setForeground(new java.awt.Color(0, 0, 0));
        jlblcargandoletra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblcargandoletra.setText("Cargando Productos ...");
        jPanel4.add(jlblcargandoletra, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 220, -1));

        jlblcargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblcargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel4.add(jlblcargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 210, 210));

        jtablaproducto.setBackground(new java.awt.Color(255, 255, 255));
        jtablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtablaproducto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaproducto.getTableHeader().setReorderingAllowed(false);
        jtablaproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaproductoMouseReleased(evt);
            }
        });
        jtablaproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaproductoKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jtablaproducto);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 104, 740, 480));

        jbtnnuevo.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnuevo.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnnuevo.setText("Nuevo");
        jbtnnuevo.setToolTipText("Nuevo");
        jbtnnuevo.setBorder(null);
        jbtnnuevo.setBorderPainted(false);
        jbtnnuevo.setContentAreaFilled(false);
        jbtnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnuevoActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 60, -1));

        jbtneditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneditar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit20x20.png"))); // NOI18N
        jbtneditar.setText("Editar");
        jbtneditar.setToolTipText("Editar");
        jbtneditar.setBorder(null);
        jbtneditar.setBorderPainted(false);
        jbtneditar.setContentAreaFilled(false);
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 60, -1));

        jbtnguardar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save20x20.png"))); // NOI18N
        jbtnguardar.setText("Guardar");
        jbtnguardar.setToolTipText("Guardar");
        jbtnguardar.setBorder(null);
        jbtnguardar.setBorderPainted(false);
        jbtnguardar.setContentAreaFilled(false);
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 80, -1));

        jbtneliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneliminar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete20x20.png"))); // NOI18N
        jbtneliminar.setText("Eliminar");
        jbtneliminar.setToolTipText("Eliminar");
        jbtneliminar.setBorder(null);
        jbtneliminar.setBorderPainted(false);
        jbtneliminar.setContentAreaFilled(false);
        jbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneliminarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, 80, -1));

        jbtncancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncancelar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel20x20.png"))); // NOI18N
        jbtncancelar.setText("Cancelar");
        jbtncancelar.setToolTipText("Cancelar");
        jbtncancelar.setBorder(null);
        jbtncancelar.setBorderPainted(false);
        jbtncancelar.setContentAreaFilled(false);
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, 90, -1));

        jtfcodigo.setText("CODIGO");
        jtfcodigo.setToolTipText("Codigo");
        jtfcodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusLost(evt);
            }
        });
        jtfcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyTyped(evt);
            }
        });
        jPanel4.add(jtfcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 310, 210, -1));

        jtfdescripcion.setText("DESCRIPCION");
        jtfdescripcion.setToolTipText("Descripcion");
        jtfdescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfdescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfdescripcionFocusLost(evt);
            }
        });
        jtfdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyTyped(evt);
            }
        });
        jPanel4.add(jtfdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, 460, -1));

        jtaobservacion.setColumns(20);
        jtaobservacion.setRows(5);
        jtaobservacion.setText("OBSERVACIONES");
        jtaobservacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtaobservacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtaobservacionFocusLost(evt);
            }
        });
        jtaobservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtaobservacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtaobservacionKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(jtaobservacion);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, 460, 110));

        jbtngenerarcodigo.setBackground(new java.awt.Color(255, 255, 255));
        jbtngenerarcodigo.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtngenerarcodigo.setText("GENERAR CODIGO");
        jbtngenerarcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtngenerarcodigoActionPerformed(evt);
            }
        });
        jPanel4.add(jbtngenerarcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 310, -1, 20));

        jtffamilia.setText("FAMILIA");
        jtffamilia.setEnabled(false);
        jPanel4.add(jtffamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, 300, -1));

        jbtnbuscarfamilia.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarfamilia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnbuscarfamilia.setText("...");
        jbtnbuscarfamilia.setPreferredSize(new java.awt.Dimension(45, 18));
        jbtnbuscarfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarfamiliaActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnbuscarfamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 440, 50, 20));

        jLabel1.setText("Precio 3:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 400, -1, 20));

        jcbfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbfamiliaActionPerformed(evt);
            }
        });
        jPanel4.add(jcbfamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 74, 350, -1));

        jLabel4.setText("Precio:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 370, -1, 20));

        jLabel5.setText("Precio 1:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 370, -1, 20));

        jLabel6.setText("Precio 2:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 400, -1, 20));

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setText("MANTENIMIENTO DE PRODUCTOS Y SERVICIOS");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel15.setToolTipText("Cerrar");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel15MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 710, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 50));

        jlbltiempoproceso.setBackground(new java.awt.Color(0, 0, 0));
        jlbltiempoproceso.setFont(new java.awt.Font("Segoe UI Light", 0, 8)); // NOI18N
        jlbltiempoproceso.setForeground(new java.awt.Color(0, 0, 0));
        jlbltiempoproceso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbltiempoproceso.setText("* * *");
        jPanel4.add(jlbltiempoproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 280, 20));

        jLabel8.setText("Unid. Medida:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, -1, 20));

        jcbunidmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbunidmedidaActionPerformed(evt);
            }
        });
        jPanel4.add(jcbunidmedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 270, -1));

        jlblImageProducto.setBackground(new java.awt.Color(255, 255, 255));
        jlblImageProducto.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(jlblImageProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 260, 180));

        jbtnfoto.setBackground(new java.awt.Color(255, 255, 255));
        jbtnfoto.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FOTO.png"))); // NOI18N
        jbtnfoto.setText("Foto Referencial");
        jbtnfoto.setBorderPainted(false);
        jbtnfoto.setContentAreaFilled(false);
        jbtnfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnfotoActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 120, 160, 40));

        jbtngeneracodigobarras.setBackground(new java.awt.Color(255, 255, 255));
        jbtngeneracodigobarras.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtngeneracodigobarras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/codprod.png"))); // NOI18N
        jbtngeneracodigobarras.setText("Generar Cod. Barras");
        jbtngeneracodigobarras.setBorderPainted(false);
        jbtngeneracodigobarras.setContentAreaFilled(false);
        jbtngeneracodigobarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtngeneracodigobarrasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtngeneracodigobarrasMousePressed(evt);
            }
        });
        jbtngeneracodigobarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtngeneracodigobarrasActionPerformed(evt);
            }
        });
        jPanel4.add(jbtngeneracodigobarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 170, -1, 50));

        jlblcargafoto.setFont(new java.awt.Font("Segoe Script", 0, 10)); // NOI18N
        jlblcargafoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading4.gif"))); // NOI18N
        jlblcargafoto.setText("Cargando vista previa");
        jPanel4.add(jlblcargafoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 200, -1));

        jtfprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecioKeyReleased(evt);
            }
        });
        jPanel4.add(jtfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 130, -1));

        jtfprecio1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecio1KeyReleased(evt);
            }
        });
        jPanel4.add(jtfprecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 370, 130, -1));

        jtfprecio2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecio2KeyReleased(evt);
            }
        });
        jPanel4.add(jtfprecio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 400, 130, -1));

        jtfprecio3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfprecio3KeyReleased(evt);
            }
        });
        jPanel4.add(jtfprecio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 400, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarproductoActionPerformed
        // TODO add your handling code here:
//           try {
        
    }//GEN-LAST:event_jtfbuscarproductoActionPerformed

    private void jtfbuscarproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarproductoKeyReleased
        
        
     
       
    }//GEN-LAST:event_jtfbuscarproductoKeyReleased

    private void jtfbuscarproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarproductoKeyTyped
        // TODO add your handling code here:
//       mayus.convertirmayus(jtfbuscarproducto);
    }//GEN-LAST:event_jtfbuscarproductoKeyTyped

    private void jtablaproductoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaproductoMouseReleased
        // TODO add your handling code here:
   
        Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                jlblcargafoto.setVisible(true);
                long tiempoInicio = System.currentTimeMillis();
                buscar();
                
                long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                jlbltiempoproceso.setText("Vista previa "+totalTiempo + " miliseg");
//                jlblcargafoto.setVisible(false);
                bloquearjtf(false, false, false, false, false, false, false, false);
            }
        };
        Thread T= new Thread(runnable);
        T.start();
       
       
    }//GEN-LAST:event_jtablaproductoMouseReleased

    private void jbtnfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnfotoActionPerformed
        // TODO add your handling code here:
//          Empleado empleado = new Empleado();
//        System.out.println("idfoto"+id);
//        empleado.setId_empleado(id);
    JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos png & jpg", "png", "jpg");
        chooser.setFileFilter(filter);
        String userHome = System.getProperty( "user.home" );
        chooser.setCurrentDirectory(new File(userHome));
        chooser.setAcceptAllFileFilterUsed(false);
        int retrival = chooser.showOpenDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {                        
                try {
                    rutaimagen = chooser.getSelectedFile().getAbsolutePath();
                    File file = new File(rutaimagen);
                    byte[]  fotobys= Files.readAllBytes(file.toPath());
                    fotoB=fotobys;
                    
                    
                    Image icono=ImageIO.read(chooser.getSelectedFile()).getScaledInstance
                    (jlblImageProducto.getWidth(),jlblImageProducto.getHeight(),Image.SCALE_DEFAULT);
                    jlblImageProducto.setIcon(new ImageIcon(icono));
                    jlblImageProducto.updateUI();

                   validaguardar();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "imagen: "+ex);
                }
            }     
    }//GEN-LAST:event_jbtnfotoActionPerformed

    private void jbtngeneracodigobarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtngeneracodigobarrasActionPerformed
        // TODO add your handling code here:
//            GenerarCodigoBarras codbarra= new GenerarCodigoBarras();
//
//        String codigo= jtfcodigo.getText().replaceAll("\\s","");
//        codbarra.generarcodigobarras( "E:\\"+codigo+".pdf",codigo );
//        JOptionPane.showMessageDialog(this,"CODIGO GENERADO EXITOSAMENTE");
        Producto prod;
        prod= listprod.get(jtablaproducto.getSelectedRow());
        daoproducto.imprimircodigo(prod.getIdproducto());
//         jlblmensajeimpresion.setText("");
    }//GEN-LAST:event_jbtngeneracodigobarrasActionPerformed

    private void jbtnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnnuevoActionPerformed
        // TODO add your handling code here:
         bloquearjtf(true, true, true,true,true,true,true,true);
        bloquearjbtn(true, false, false, false, true, true,true,false,true);
        jbtngenerarcodigo.setEnabled(true);
        limpiarjtf();
        producto = new Producto();
        editar=false;

    }//GEN-LAST:event_jbtnnuevoActionPerformed

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        // TODO add your handling code here:
         bloquearjtf(true, true, true,true,true,true,true,true);
        editar=true;
        bloquearjbtn(true, false, false, false, true, true,true,false,true);
        jbtngenerarcodigo.setEnabled(true);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
//        Producto producto= new Producto();
       Unidad_Medida und = listunidad.get(jcbunidmedida.getSelectedIndex());
        boolean refrescar = false;
        String codigo=jtfcodigo.getText().replaceAll("\\s","");
        String descripcion=jtfdescripcion.getText().trim().toUpperCase();
        String observacion=jtaobservacion.getText().trim().toUpperCase();
        producto.setIdfamilia(familia.getIdfamilia());
        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setObservacion(observacion);
        producto.setPrecio(Double.parseDouble(jtfprecio.getText()));
        producto.setPrecio1(Double.parseDouble(jtfprecio1.getText()));
        producto.setPrecio2(Double.parseDouble(jtfprecio2.getText()));
        producto.setPrecio3(Double.parseDouble(jtfprecio3.getText()));
       producto.setIdunidm(und.getId());
//        try {
//             producto.setMoneda(jcbmoneda.getSelectedItem().toString());
//        } catch (Exception e) {
//            producto.setMoneda("");
//        }
       
        producto.setFoto(fotoB);
        producto.setId_sucursal(sucursalsingleton.getId());
        
        if (codigo.length()>0 && descripcion.length()>0 && observacion.length()>0
                ){
            if(!codigo.equals("CODIGO")){
                if(editar==false){
                  boolean valida=daoproducto.duplicado(0, codigo,"GUARDAR");
                   if(valida==true){
                       producto.setCantidad(0.0);
                       daoproducto.insertarproducto(producto);
                       menu.cargarresumen();
                       refrescar=true;
                        bloquearjtf(false, false, false,false,false,false,false,false);   
                   }else{
                    JOptionPane.showMessageDialog(null, "El producto ya se encuentra registrado","",JOptionPane.ERROR_MESSAGE);
                }    
                 
                }
               
                else{
                    
                   
                    boolean Vvalida=daoproducto.duplicado(producto.getIdproducto(), codigo,"EDITAR");
                 if(Vvalida==true){
                      daoproducto.editarproducto(producto);
                      refrescar=true;
                       bloquearjtf(false, false, false,false,false,false,false,false);   
                 }else {
                     JOptionPane.showMessageDialog(null, "El producto ya se encuentra registrado","",JOptionPane.INFORMATION_MESSAGE);
                 }
                   
                   
                        } 
                
                       
                    
             
                
            }else {
                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS","ERROR",JOptionPane.ERROR_MESSAGE);
            } 
           
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE TODOS LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
        
        }

        
        if(refrescar==true){
            Runnable runnable = new Runnable() {

              @Override
              public void run() {
   //               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                   jlblcargando.setVisible(true);  
                   jlblcargandoletra.setVisible(true);
                   sensitiva();
                   jlblcargando.setVisible(false);
                   jlblcargandoletra.setVisible(false);

              }
          };

          Thread M = new Thread(runnable);
          M.start();

        
        }
        
     
    }//GEN-LAST:event_jbtnguardarActionPerformed

    private void jbtneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneliminarActionPerformed
        // TODO add your handling code here:
        int index= jtablaproducto.getSelectedRow();
        Producto prod = listprod.get(index);
        
        if (index<0){
            JOptionPane.showMessageDialog(this, "SELECCIONE PRODUCTO A ELIMINAR EN LA TABLA");
        }else {
            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL PRODUCTO","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                daoproducto.eliminarproducto(prod.getIdproducto());
//                bloquearjtf( false, false, false,false,false,false,false,false);
//                bloquearjbtn(true, false, false, false, false, true,false,false,false);
//                jbtngenerarcodigo.setEnabled(false);
//                limpiarjtf();
//                daoproducto.mostrarproducto(jtablaproducto,sucursalsingleton.getId());
         Runnable runnable = new Runnable() {

           @Override
           public void run() {
//               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               
                jlblcargando.setVisible(true);  
                jlblcargandoletra.setVisible(true);
                sensitiva();
                jlblcargando.setVisible(false);
                jlblcargandoletra.setVisible(false);
           }
       };
       Thread M = new Thread(runnable);
       M.start();
               
            }

        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
        int index = jtablaproducto.getSelectedRow();
        if(index>=0){
            buscar();
        } else {
        limpiarjtf();
        producto= new Producto();
        }
        
        bloquearjtf( false, false, false,false,false,false,false,false);
        bloquearjbtn(true, false, false, false, false, true,false,false,false);
        jbtngenerarcodigo.setEnabled(false);
        
    }//GEN-LAST:event_jbtncancelarActionPerformed

    private void jtfbuscarproductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarproductoFocusGained
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfbuscarproductoFocusGained

    private void jtfbuscarproductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarproductoFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtfbuscarproductoFocusLost

    private void jtfcodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusGained
        // TODO add your handling code here:
         if(jtfcodigo.getText().equals("CODIGO")){
            jtfcodigo.setText("");
        
        }
    }//GEN-LAST:event_jtfcodigoFocusGained

    private void jtfcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusLost
        // TODO add your handling code here:
         if(jtfcodigo.getText().equals("")){
            jtfcodigo.setText("CODIGO");
        
        }
    }//GEN-LAST:event_jtfcodigoFocusLost

    private void jtfdescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusGained
        // TODO add your handling code here:
         if(jtfdescripcion.getText().equals("DESCRIPCION")){
            jtfdescripcion.setText("");
        
        }
    }//GEN-LAST:event_jtfdescripcionFocusGained

    private void jtfdescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusLost
        // TODO add your handling code here:
         if(jtfdescripcion.getText().equals("")){
            jtfdescripcion.setText("DESCRIPCION");
        
        }
    }//GEN-LAST:event_jtfdescripcionFocusLost

    private void jtaobservacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaobservacionFocusGained
        // TODO add your handling code here:
         if(jtaobservacion.getText().equals("OBSERVACION")){
            jtaobservacion.setText("");
        
        }
    }//GEN-LAST:event_jtaobservacionFocusGained

    private void jtaobservacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaobservacionFocusLost
        // TODO add your handling code here:
         if(jtaobservacion.getText().equals("")){
            jtaobservacion.setText("OBSERVACION");
        
        }
    }//GEN-LAST:event_jtaobservacionFocusLost

    private void jbtnbuscarfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarfamiliaActionPerformed
        // TODO add your handling code here:
        
        JDBuscarFamilia bfamilia= new JDBuscarFamilia(new java.awt.Frame(), isVisible(),this);
        
        bfamilia.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarfamiliaActionPerformed

    private void jtfdescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfdescripcionKeyReleased

    private void jtaobservacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaobservacionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtaobservacionKeyReleased

    private void jtablaproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaproductoKeyReleased
        // TODO add your handling code here
        
       Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                jlblcargafoto.setVisible(true);
                buscar();
//                jlblcargafoto.setVisible(false);
                bloquearjtf(false, false, false, false, false, false, false, false);
            }
        };
        Thread T= new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jtablaproductoKeyReleased

    private void jtfcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfcodigoKeyReleased

    private void jbtngenerarcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtngenerarcodigoActionPerformed
        // TODO add your handling code here:
        daoproducto.generarcodigo(jtfcodigo);
        validaguardar();
        jbtngenerarcodigo.setEnabled(false);
    }//GEN-LAST:event_jbtngenerarcodigoActionPerformed

    private void jtfdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfdescripcion);
        validaguardar();
    }//GEN-LAST:event_jtfdescripcionKeyTyped

    private void jtaobservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaobservacionKeyTyped
        // TODO add your handling code here:
//         mayus.convertirmayusTA(jtaobservacion);
    }//GEN-LAST:event_jtaobservacionKeyTyped

    private void jtfcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyTyped
        // TODO add your handling code here:
//         char c = evt.getKeyChar();
//        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
//                ) {
//                evt.consume();
//            }
//            if (c == '.' && jtfcodigo.getText().contains(".")) {
//                evt.consume();
//                } 
        
    }//GEN-LAST:event_jtfcodigoKeyTyped

    private void jcbfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbfamiliaActionPerformed
        // TODO add your handling code here:
         Runnable runnable = new Runnable() {

           @Override
           public void run() {
//               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                long tiempoInicio = System.currentTimeMillis();
                
                jlblcargando.setVisible(true);  
                jlblcargandoletra.setVisible(true);
                sensitiva();
                jlblcargando.setVisible(false);
                jlblcargandoletra.setVisible(false);
                long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                jlbltiempoproceso.setText("Busqueda por familias "+totalTiempo + " miliseg");
           }
       };
       
       Thread M = new Thread(runnable);
       M.start();
      
        
    }//GEN-LAST:event_jcbfamiliaActionPerformed

    private void jbtngeneracodigobarrasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtngeneracodigobarrasMousePressed
        // TODO add your handling code here:
//        jlblmensajeimpresion.setText("Generando ...");
    }//GEN-LAST:event_jbtngeneracodigobarrasMousePressed

    private void jbtngeneracodigobarrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtngeneracodigobarrasMouseExited
        // TODO add your handling code here:
//        jlblmensajeimpresion.setText("");
    }//GEN-LAST:event_jbtngeneracodigobarrasMouseExited

    private void jLabel15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseReleased

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
          posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jtfbuscarproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarproductoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
           sensitiva();
           System.out.println("enter");
         }
    }//GEN-LAST:event_jtfbuscarproductoKeyPressed

    private void jcbunidmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbunidmedidaActionPerformed
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jcbunidmedidaActionPerformed

    private void jtfprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecioKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfprecioKeyReleased

    private void jtfprecio1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecio1KeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfprecio1KeyReleased

    private void jtfprecio2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecio2KeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfprecio2KeyReleased

    private void jtfprecio3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfprecio3KeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfprecio3KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jbtnbuscarfamilia;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnfoto;
    private javax.swing.JButton jbtngeneracodigobarras;
    private javax.swing.JButton jbtngenerarcodigo;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnuevo;
    private javax.swing.JComboBox jcbfamilia;
    private javax.swing.JComboBox jcbunidmedida;
    private javax.swing.JLabel jlblImageProducto;
    private javax.swing.JLabel jlblcargafoto;
    private javax.swing.JLabel jlblcargando;
    private javax.swing.JLabel jlblcargandoletra;
    private javax.swing.JLabel jlbltiempoproceso;
    private javax.swing.JTable jtablaproducto;
    private javax.swing.JTextArea jtaobservacion;
    private javax.swing.JTextField jtfbuscarproducto;
    private javax.swing.JTextField jtfcodigo;
    private javax.swing.JTextField jtfdescripcion;
    private javax.swing.JTextField jtffamilia;
    private javax.swing.JTextField jtfprecio;
    private javax.swing.JTextField jtfprecio1;
    private javax.swing.JTextField jtfprecio2;
    private javax.swing.JTextField jtfprecio3;
    // End of variables declaration//GEN-END:variables
}
