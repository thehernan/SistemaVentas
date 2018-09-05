/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.GeneraCarnet;
import ClasesGlobales.Mayusculas;
import DAO.EmpleadoDAO;
import Pojos.Empleado;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author info2017
 */
public class JIFEmpleados extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFEmpleados
     */
    EmpleadoDAO daoempleado = new EmpleadoDAO();
   Boolean editar;
//   long id;
    String rutaimagen=null;
//     byte[]  fotoB=new  byte[0];
     Mayusculas mayus= new Mayusculas();
     List<Empleado> listempleado;
     Empleado empleado=new Empleado();
      int posx;
    int posy;
    MDIMenu menu;
    public JIFEmpleados() {
        
    }
    public JIFEmpleados(MDIMenu menu) {
        initComponents();
        jlblimagencarga.setVisible(false);
        jlblletracarga.setVisible(false);
        jlblcargaimagen.setVisible(false);
        
        bloquearjtf(false, false, false, false, false);
        bloquearjbtn(true, false, false, false, false, true,false,false);
        mostrar();
        this.menu=menu;
    }
    
    public void mostrar(){
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                jlblimagencarga.setVisible(true);
                jlblletracarga.setVisible(true);
                jtfbuscarnombre.setEnabled(false);
                jtfbuscarrut.setEnabled(false);
                listempleado=daoempleado.mostrarempleado(jtablaempleado);
                jlblimagencarga.setVisible(false);
                jlblletracarga.setVisible(false);
                jtfbuscarnombre.setEnabled(true);
                jtfbuscarrut.setEnabled(true);
            }
        };
        
        Thread T = new Thread(runnable);
        T.start();
    
    
    }
    

    public void bloquearjtf(boolean rut, boolean nombre,boolean email, boolean direccion, boolean celular){
    jtfrut.setEnabled(rut);
    jtfnombre.setEnabled(nombre);
    jtfemail.setEnabled(email);
    jtfdireccion.setEnabled(direccion);
    jtfcelular.setEnabled(celular);
     
    
    }
    public void bloquearjbtn(boolean nuevo,boolean editar,boolean guardar,boolean eliminar,boolean cancelar,
    boolean salir,boolean foto,boolean carne ){
    jbtnnew.setEnabled(nuevo);
    jbtneditar.setEnabled(editar);
    jbtnguardar.setEnabled(guardar);
    jbtneliminar.setEnabled(eliminar);
    jbtncancelar.setEnabled(cancelar);
//    jbtnsalir.setEnabled(salir);
    jbtnfoto.setEnabled(foto);
    jbtngeneracarnet.setEnabled(carne);
    
    }
     public void limpiarjtf(){
    jtfrut.setText("");
    jtfnombre.setText("");
    jtfemail.setText("");
    jtfdireccion.setText("");
    jtfcelular.setText("");
    jlblImageUser.setIcon(null);
//    fotoB= new  byte[0];
    empleado=new Empleado();
    }
    public void validaguardar(){
        String nombre= jtfnombre.getText().replaceAll("\\s","");
        String rut=jtfrut.getText().replaceAll("\\s","");
        String direc=jtfdireccion.getText().replaceAll("\\s","");
        String cel=jtfcelular.getText().replaceAll("\\s","");
        String email=jtfemail.getText().replaceAll("\\s","");
                
    if(nombre.length()>0 && rut.length()>0  && direc.length()>0 && cel.length()>0 && email.length()>0){
        bloquearjbtn(false, false, true,false ,true, true,true,false);
    }else {
        bloquearjbtn(true, false, false, false, true, true,false,false);
    }
    
    }
    public synchronized void ver(){
        jlblcargaimagen.setVisible(true);
        
       Empleado empleado=listempleado.get(jtablaempleado.getSelectedRow());
        
        this.empleado = daoempleado.buscarempleado("ID", empleado.getId_empleado());    
        jtfnombre.setText(this.empleado.getNombre());
        jtfrut.setText(this.empleado.getRut());
        jtfcelular.setText(this.empleado.getCelular());
        jtfdireccion.setText(this.empleado.getDireccion());
        jtfemail.setText(this.empleado.getEmail());
//        fotoB= empleado.getFoto();
        bloquearjbtn(true, true, false, true, false, true,false,true);
        bloquearjtf(false, false,false, false,false);
      

        //////////////  mostrar imagen ////////////////
        ImageIcon imageUser = new ImageIcon(this.empleado.getFoto());
        Image img = imageUser.getImage();
        Image newimg = img.getScaledInstance(jlblImageUser.getWidth(), jlblImageUser.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageUser = new ImageIcon(newimg);
        jlblImageUser.setIcon(imageUser);
        
        /////////////////////////////////7
        jlblcargaimagen.setVisible(false);
    }
    
    public synchronized void sensitiva(String op){
         jlblimagencarga.setVisible(true);
         jlblletracarga.setVisible(true);
        if(op.equals("nombre")){
        String nombre=jtfbuscarnombre.getText().trim().toUpperCase();
        listempleado=daoempleado.busquedasensitivaempleado(jtablaempleado,"NOMBRE", nombre);    
        
        }
        if(op.equals("rut")){
        String rut= jtfbuscarrut.getText().trim();
        listempleado=daoempleado.busquedasensitivaempleado(jtablaempleado,"RUT",rut);
        
        }
        if(jtablaempleado.getSelectedRow()>=0){
            ver();

        }else {
            limpiarjtf();

            bloquearjtf(false, false,false, false,false);
            bloquearjbtn(true, false, false, false, false, true,false,false);

        }
         jlblimagencarga.setVisible(false);
         jlblletracarga.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblletracarga = new javax.swing.JLabel();
        jlblimagencarga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablaempleado = new javax.swing.JTable();
        jbtncancelar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtnguardar = new javax.swing.JButton();
        jbtnnew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtfrut = new javax.swing.JTextField();
        jtfdireccion = new javax.swing.JTextField();
        jtfcelular = new javax.swing.JTextField();
        jtfemail = new javax.swing.JTextField();
        jtfnombre = new javax.swing.JTextField();
        jbtnfoto = new javax.swing.JButton();
        jbtngeneracarnet = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtfbuscarrut = new javax.swing.JTextField();
        jtfbuscarnombre = new javax.swing.JTextField();
        jbtneditar = new javax.swing.JButton();
        jlblcargaimagen = new javax.swing.JLabel();
        jlblImageUser = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1239, 533));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblletracarga.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblletracarga.setForeground(new java.awt.Color(0, 0, 0));
        jlblletracarga.setText("Cargando Registros ...");
        jPanel1.add(jlblletracarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, -1));

        jlblimagencarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblimagencarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel1.add(jlblimagencarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 310, 170));

        jtablaempleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtablaempleado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaempleado.getTableHeader().setReorderingAllowed(false);
        jtablaempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaempleadoMouseReleased(evt);
            }
        });
        jtablaempleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaempleadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtablaempleado);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 492, 357));

        jbtncancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncancelar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel20x20.png"))); // NOI18N
        jbtncancelar.setText("Cancelar");
        jbtncancelar.setToolTipText("Cancelar");
        jbtncancelar.setBorder(null);
        jbtncancelar.setBorderPainted(false);
        jbtncancelar.setContentAreaFilled(false);
        jbtncancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 90, 80, -1));

        jbtneliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneliminar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete20x20.png"))); // NOI18N
        jbtneliminar.setText("Eliminar");
        jbtneliminar.setToolTipText("Eliminar");
        jbtneliminar.setBorder(null);
        jbtneliminar.setBorderPainted(false);
        jbtneliminar.setContentAreaFilled(false);
        jbtneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, 70, -1));

        jbtnguardar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save20x20.png"))); // NOI18N
        jbtnguardar.setText("Guardar");
        jbtnguardar.setToolTipText("Guardar");
        jbtnguardar.setBorder(null);
        jbtnguardar.setBorderPainted(false);
        jbtnguardar.setContentAreaFilled(false);
        jbtnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, 70, -1));

        jbtnnew.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnew.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnnew.setText("Nuevo");
        jbtnnew.setToolTipText("Nuevo");
        jbtnnew.setBorder(null);
        jbtnnew.setBorderPainted(false);
        jbtnnew.setContentAreaFilled(false);
        jbtnnew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnewActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 70, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfrutFocusLost(evt);
            }
        });
        jtfrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrutKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfrutKeyTyped(evt);
            }
        });
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 139, -1));

        jtfdireccion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfdireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfdireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfdireccionFocusLost(evt);
            }
        });
        jtfdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfdireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfdireccionKeyTyped(evt);
            }
        });
        jPanel2.add(jtfdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 340, -1));

        jtfcelular.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfcelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcelularFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcelularFocusLost(evt);
            }
        });
        jtfcelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfcelularActionPerformed(evt);
            }
        });
        jtfcelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcelularKeyReleased(evt);
            }
        });
        jPanel2.add(jtfcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 140, -1));

        jtfemail.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfemailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfemailFocusLost(evt);
            }
        });
        jtfemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfemailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfemailKeyTyped(evt);
            }
        });
        jPanel2.add(jtfemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 340, -1));

        jtfnombre.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfnombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnombreFocusLost(evt);
            }
        });
        jtfnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfnombreKeyTyped(evt);
            }
        });
        jPanel2.add(jtfnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 340, -1));

        jbtnfoto.setBackground(new java.awt.Color(255, 255, 255));
        jbtnfoto.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FOTO.png"))); // NOI18N
        jbtnfoto.setText("Foto");
        jbtnfoto.setBorder(null);
        jbtnfoto.setBorderPainted(false);
        jbtnfoto.setContentAreaFilled(false);
        jbtnfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnfotoActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 40));

        jbtngeneracarnet.setBackground(new java.awt.Color(255, 255, 255));
        jbtngeneracarnet.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtngeneracarnet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carnet.png"))); // NOI18N
        jbtngeneracarnet.setText("Generar Carné");
        jbtngeneracarnet.setBorder(null);
        jbtngeneracarnet.setBorderPainted(false);
        jbtngeneracarnet.setContentAreaFilled(false);
        jbtngeneracarnet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtngeneracarnet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtngeneracarnetActionPerformed(evt);
            }
        });
        jPanel2.add(jbtngeneracarnet, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 40));

        jLabel3.setText("Nombre y Apellidos:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setText("N° Doc.:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel5.setText("Celular:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jLabel6.setText("Dirección:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel7.setText("E-mail:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, 373, 311));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Buscar por:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jtfbuscarrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscarrut.setText("Doc");
        jtfbuscarrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarrutFocusLost(evt);
            }
        });
        jtfbuscarrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfbuscarrutActionPerformed(evt);
            }
        });
        jtfbuscarrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarrutKeyReleased(evt);
            }
        });
        jPanel1.add(jtfbuscarrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, -1));

        jtfbuscarnombre.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscarnombre.setText("NOMBRE Y APELLIDOS");
        jtfbuscarnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarnombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarnombreFocusLost(evt);
            }
        });
        jtfbuscarnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfbuscarnombreActionPerformed(evt);
            }
        });
        jtfbuscarnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscarnombreKeyTyped(evt);
            }
        });
        jPanel1.add(jtfbuscarnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 560, -1));

        jbtneditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneditar.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit20x20.png"))); // NOI18N
        jbtneditar.setText("Editar");
        jbtneditar.setToolTipText("Editar");
        jbtneditar.setBorder(null);
        jbtneditar.setBorderPainted(false);
        jbtneditar.setContentAreaFilled(false);
        jbtneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, 70, -1));

        jlblcargaimagen.setFont(new java.awt.Font("Segoe Script", 0, 10)); // NOI18N
        jlblcargaimagen.setForeground(new java.awt.Color(238, 238, 238));
        jlblcargaimagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading4.gif"))); // NOI18N
        jlblcargaimagen.setText("Cargando vista previa ...");
        jPanel1.add(jlblcargaimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jlblImageUser.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jlblImageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 324, 357));
        jPanel1.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 170, 20));

        jPanel4.setBackground(new java.awt.Color(238, 238, 238));
        jPanel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel4MouseDragged(evt);
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setText("REGISTRO EMPLEADOS");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel8.setToolTipText("Cerrar");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 954, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-7, 0, 1240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
             int index = jtablaempleado.getSelectedRow();
        if(index>=0){
            ver();
        } else {
        limpiarjtf();
        }
         bloquearjtf(false, false,false, false,false);
        bloquearjbtn(true, false, false, false, false, true,false,false);
        
        
       
        //int index= jtablaalumno.getSelectedRow();

        //        limpiarjtext();
        //        habilitarjtext(false);
        //        jbtnguardar.setEnabled(false);
        //        jbtncancelar.setEnabled(false);
        //        jbtneditar.setEnabled(false);
        //
        //        guardar=true;
        //
        //        int fila=jtablaalumno.getSelectedRow();
        //        if(fila>=0){
            //            // Integer idP=Integer.parseInt(jtablaalumno.getValueAt(index, 0).toString());
            //            buscar();
            //        }
    }//GEN-LAST:event_jbtncancelarActionPerformed

    private void jbtneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneliminarActionPerformed
        // TODO add your handling code here:
        //        Integer index= jtablaalumno.getSelectedRow();
        //        Integer id = Integer.parseInt(jtablaalumno.getValueAt(index, 0).toString());
        //        if (index<0){
            //            JOptionPane.showMessageDialog(this, "SELECCIONE ALUMNO A ELIMINAR EN LA TABLA");
            //        }else {
            //            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR AL ALUMNO","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                //                eliminar(id);
                //                mostrar();
                //                limpiarjtext();
                //                habilitarjtext(false);
                //
                //            }
            //
            //        }
        int index= jtablaempleado.getSelectedRow();
        empleado=listempleado.get(index);
        if (index<0){
            JOptionPane.showMessageDialog(this, "SELECCIONE EMPLEADO A ELIMINAR EN LA TABLA");
        }else {
            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL EMPLEADO","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                daoempleado.eliminarempleado(empleado.getId_empleado());
                listempleado=daoempleado.mostrarempleado(jtablaempleado);
                bloquearjtf(false, false, false, false, false);
                bloquearjbtn(true, false, false, false, false, true,false,false);
                limpiarjtf();
            }

        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed

        // TODO add your handling code here:
        
        
//        Empleado empleado= new Empleado();
        String nombre=jtfnombre.getText().trim().toUpperCase();
        String rut=jtfrut.getText().trim().toUpperCase();
        String direccion=jtfdireccion.getText().trim().toUpperCase();
        String celular=jtfcelular.getText().trim().toUpperCase();
        String email=jtfemail.getText().trim().toUpperCase();
        
        
        empleado.setNombre(nombre);
        empleado.setRut(rut);
        empleado.setDireccion(direccion);
        empleado.setCelular(celular);
        empleado.setEmail(email);
//        empleado.setFoto(fotoB);
        
        if (nombre.length()>0 && rut.length()>0 && direccion.length()>0
                && celular.length()>0 && email.length()>0){
//            if(!nombre.equals("NOMBRES Y APELLIDOS")){
                if(editar==false){
                 boolean valida=daoempleado.duplicado(0, rut,"GUARDAR");
                if(valida==true){
                    daoempleado.insertarempleado(empleado);
                    menu.cargarresumen();
                } else {
                    JOptionPane.showMessageDialog(null, "El empleaado ya se encuentra registrado","",JOptionPane.INFORMATION_MESSAGE);
                }  
                 
                }
               
                else{
                    
//                    long id=Long.parseLong(jtablaempleado.getValueAt(jtablaempleado.getSelectedRow(), 0).toString());   
//                    empleado.setId_empleado(id);
//                    this.empleado= listempleado.get(jtablaempleado.getSelectedRow());
                    boolean Vvalida=daoempleado.duplicado(empleado.getId_empleado(), rut,"EDITAR");
                    if(Vvalida==true){
                      daoempleado.editarempleado(empleado);
                    }else {
                     JOptionPane.showMessageDialog(null, "EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                    }
                   
                        } 
                
                     mostrar();
                    bloquearjtf(false, false, false, false, false);
                    bloquearjbtn(true, false, false, false, false, true,false,false);
                    limpiarjtf();  
                    
                
                
//            }else {
//                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS","ERROR",JOptionPane.ERROR_MESSAGE);
//            } 
           
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE TODOS LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
        
        }
        
        
        
        //        String RUT = jtfrut.getText();
        //        Integer index= jtablaalumno.getSelectedRow();
        //
        //        if (validarrut(RUT)==true){
            //            if (guardar==true){ //GUARDO
                //                if (validarutduplicado("RUT", RUT,null)==true){ // BUSCA TODOS LOS RUT SI  NO ENCUENTRA CONCIDENCIA CON EL RUT INGRESADO (TRUE)
                    //                    // Limpiartabla();
                    //                    ///insert
                    //                    System.out.println("ruta"+rutaimagen);
                    //                    insertar();
                    //                    mostrar();
                    //                    jbtnguardar.setEnabled(false);
                    //                    jbtncancelar.setEnabled(false);
                    //
                    //                    habilitarjtext(false);
                    //                    limpiarjtext();
                    //                    //                jtfpuesto.setBackground(Color.WHITE);
                    //                    //            }
                //                //        else {
                    //                    //                JOptionPane.showMessageDialog(this,"EL PUESTO "+puesto+" YA SE ENCUENTRA REGISTRADO");
                    //                    //                jtfpuesto.requestFocus();
                    //                    //                jtfpuesto.setBackground(Color.RED);
                    //                    //            }
                //            } else {
                //                JOptionPane.showMessageDialog(this,"EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO");
                //            }
            //        }else {
            //            //EDITO
            //            //              System.out.println("editar");
            //            if (validarutduplicado("RUTDUPLICADO", RUT, id)==true){// RUC DUPLICADO: BUSCA LOS RUC EN BD MENOS EL RUC QUE QUEREMOS EDITAR
                //                //            actualizarrubrospuesto(id,"PUESTO");
                //                Integer idS = Integer.parseInt(jtablaalumno.getValueAt(index,0).toString());
                //                editar(idS,fotoB);
                //                //Limpiartabla();
                //                mostrar();
                //                jbtnguardar.setEnabled(false);
                //                jbtncancelar.setEnabled(false);
                //                habilitarjtext(false);
                //                limpiarjtext();
                //            }else {
                //                JOptionPane.showMessageDialog(this,"EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO");
                //            }
            //
            //        }
        //
        //        guardar=true;
        //        } else {
        //
        //            JOptionPane.showMessageDialog(this,"RUT INVALIDO");
        //            jtfrut.requestFocus();
        //        }
    }//GEN-LAST:event_jbtnguardarActionPerformed

    private void jbtnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnnewActionPerformed
        // TODO add your handling code here:
        bloquearjtf(true, true, true, true, true);
        bloquearjbtn(true, false, false, false, true, true,true,false);
        limpiarjtf();
        empleado.setFoto(new byte[0]);
        editar=false;
        //        jbtncancelar.setEnabled(true);
        //
        //        jbtneditar.setEnabled(false);
        //        guardar=false;
    }//GEN-LAST:event_jbtnnewActionPerformed

    private void jtfrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusGained
        // TODO add your handling code here:
//        if (jtfrut.getText().equals("R.U.T")){
//            jtfrut.setText("");
//        }
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
//        if (jtfrut.getText().equals("")){
//            jtfrut.setText("R.U.T");
//        }
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfdireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdireccionFocusGained
        // TODO add your handling code here:
//        if (jtfdireccion.getText().equals("DIRECCION")){
//            jtfdireccion.setText("");
//        }
    }//GEN-LAST:event_jtfdireccionFocusGained

    private void jtfdireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdireccionFocusLost
        // TODO add your handling code here:
//        if (jtfdireccion.getText().equals("")){
//            jtfdireccion.setText("DIRECCION");
//        }
    }//GEN-LAST:event_jtfdireccionFocusLost

    private void jtfcelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcelularFocusGained
        // TODO add your handling code here:
//        if (jtfcelular.getText().equals("CELULAR")){
//            jtfcelular.setText("");
//        }
    }//GEN-LAST:event_jtfcelularFocusGained

    private void jtfcelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcelularFocusLost
        // TODO add your handling code here:
//        if (jtfcelular.getText().equals("")){
//            jtfcelular.setText("CELULAR");
//        }
    }//GEN-LAST:event_jtfcelularFocusLost

    private void jtfemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfemailFocusGained
        // TODO add your handling code here:
//        if (jtfemail.getText().equals("EMAIL")){
//            jtfemail.setText("");
//        }
    }//GEN-LAST:event_jtfemailFocusGained

    private void jtfemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfemailFocusLost
        // TODO add your handling code here:
//        if (jtfemail.getText().equals("")){
//            jtfemail.setText("EMAIL");
//        }
    }//GEN-LAST:event_jtfemailFocusLost

    private void jtfnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusGained
        // TODO add your handling code here:
//        if (jtfnombre.getText().equals("NOMBRES Y APELLIDOS")){
//            jtfnombre.setText("");
//        }
    }//GEN-LAST:event_jtfnombreFocusGained

    private void jtfnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusLost
        // TODO add your handling code here:
//        if (jtfnombre.getText().equals("")){
//            jtfnombre.setText("NOMBRES Y APELLIDOS");
//        }
    }//GEN-LAST:event_jtfnombreFocusLost

    private void jtfbuscarrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarrutFocusGained
        // TODO add your handling code here:
        if (jtfbuscarrut.getText().equals("Doc")){
            jtfbuscarrut.setText("");
        }
    }//GEN-LAST:event_jtfbuscarrutFocusGained

    private void jtfbuscarrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarrutFocusLost
        // TODO add your handling code here:
        if (jtfbuscarrut.getText().equals("")){
            jtfbuscarrut.setText("Doc");
        }
    }//GEN-LAST:event_jtfbuscarrutFocusLost

    private void jtfbuscarnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarnombreFocusGained
        // TODO add your handling code here:
        if (jtfbuscarnombre.getText().equals("NOMBRE Y APELLIDOS")){
            jtfbuscarnombre.setText("");
        }
    }//GEN-LAST:event_jtfbuscarnombreFocusGained

    private void jtfbuscarnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarnombreFocusLost
        // TODO add your handling code here:
        if (jtfbuscarnombre.getText().equals("")){
            jtfbuscarnombre.setText("NOMBRE Y APELLIDOS");
        }
    }//GEN-LAST:event_jtfbuscarnombreFocusLost

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        // TODO add your handling code here:
        bloquearjtf(true, true, true,true, true);
        editar=true;
        bloquearjbtn(true, false, false, false, true, true,true,false);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jbtnfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnfotoActionPerformed
        // TODO add your handling code here:
//         falumno falum = new falumno();
//        Empleado empleado = new Empleado();
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
                    empleado.setFoto(fotobys);
                    
                    
                    Image icono=ImageIO.read(chooser.getSelectedFile()).getScaledInstance
                    (jlblImageUser.getWidth(),jlblImageUser.getHeight(),Image.SCALE_DEFAULT);
                    jlblImageUser.setIcon(new ImageIcon(icono));
                    jlblImageUser.updateUI();

                   validaguardar();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "imagen: "+ex);
                }
            }     
        
        
        
        
//        falumno falum = new falumno();
//        valumno valum = new valumno();
//        System.out.println("idfoto"+id);
//        valum.setIdalumno(id);
//        JFileChooser chooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//            "Archivos png & jpg", "png", "jpg");
//        chooser.setFileFilter(filter);
//        String userHome = System.getProperty( "user.home" );
//        chooser.setCurrentDirectory(new File(userHome));
//        chooser.setAcceptAllFileFilterUsed(false);
//        int retrival = chooser.showOpenDialog(null);
//        if (retrival == JFileChooser.APPROVE_OPTION) {
//            try {
//                rutaimagen = chooser.getSelectedFile().getAbsolutePath();
//                File file = new File(rutaimagen);
//                byte[]  fotobys= Files.readAllBytes(file.toPath());
//                fotoB=fotobys;
//
//                Image icono=ImageIO.read(chooser.getSelectedFile()).getScaledInstance
//                (jlblImageUser2.getWidth(),jlblImageUser2.getHeight(),Image.SCALE_DEFAULT);
//                jlblImageUser2.setIcon(new ImageIcon(icono));
//                jlblImageUser2.updateUI();
//
//                if (validarcamposobligatiorios()==true){
//                    jbtnguardar.setEnabled(true);
//                }else {
//                    jbtnguardar.setEnabled(false);
//                }
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(rootPane, "imagen: "+ex);
//            }
//        }

        
////////////////////////////////////////////////////////////////////////////////////////////////////////    
        
        
        
        //            try {
            ////                rutaimagen = chooser.getSelectedFile().getAbsolutePath();
            //
            //
            ////                falum.actualizarImagenUsuario(valum, rutaimagen);
            ////                falum.obtenerImagenUsuario(jlblImageUser2, valum);
            ////                falum.obtenerImagenUsuario(guiInterfaz.getJlblImage(), valum);
            //            } catch (Exception ex) {
            //                System.err.println("Error al abrir el archivo: " +ex.getMessage());
            //            }

    }//GEN-LAST:event_jbtnfotoActionPerformed

    private void jbtngeneracarnetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtngeneracarnetActionPerformed
        // TODO add your handling code here:
        GeneraCarnet carnet= new GeneraCarnet();

        String rutsinguion= carnet.formatoruccarne(jtfrut.getText());
        System.out.println("rutsinguion "+rutsinguion);
        String rutconguion = jtfrut.getText();
        String nombre= jtfnombre.getText();
        System.out.println("ruta"+System.getProperty("user.home"));
        carnet.generarcarnet("CARNÉ DE IDENTIFICACIÓN", nombre, "----", empleado.getFoto(), System.getProperty("user.home")+System.getProperty("file.separator")
                +"Pictures"+System.getProperty("file.separator")+nombre+"-"+rutsinguion+".pdf",rutsinguion );
            JOptionPane.showMessageDialog(this,"CARNÉ GENERADO EN: "+System.getProperty("user.home")+System.getProperty("file.separator")
                +"Pictures"+System.getProperty("file.separator")+nombre+"-"+rutsinguion+".pdf");
    }//GEN-LAST:event_jbtngeneracarnetActionPerformed

    private void jtfbuscarrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarrutKeyReleased
        // TODO add your handling code here:
      
        
        
        
        
    }//GEN-LAST:event_jtfbuscarrutKeyReleased

    private void jtfbuscarnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombreKeyReleased
        // TODO add your handling code here:
        
        
//         if(jtablaempleado.getSelectedRow()>=0){
//            ver();
//        
//        }else {
//            limpiarjtf();
//
//            bloquearjtf(false, false,false, false,false);
//            bloquearjbtn(true, false, false, false, false, true,false,false);
//        
//        }
    }//GEN-LAST:event_jtfbuscarnombreKeyReleased

    private void jtablaempleadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaempleadoMouseReleased
        // TODO add your handling code here:
         Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                jlblcargafoto.setVisible(true);
                ver();
//                jlblcargafoto.setVisible(false);
            }
        };
        Thread T= new Thread(runnable);
        T.start();
       
        
    }//GEN-LAST:event_jtablaempleadoMouseReleased

    private void jtfnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyReleased
        // TODO add your handling code here:
         validaguardar();
        
    }//GEN-LAST:event_jtfnombreKeyReleased

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jtfcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfcelularActionPerformed

    private void jtfcelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcelularKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfcelularKeyReleased

    private void jtfdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfdireccionKeyReleased

    private void jtfemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfemailKeyReleased

    private void jtablaempleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaempleadoKeyReleased
        // TODO add your handling code here:
         Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                jlblcargafoto.setVisible(true);
                ver();
//                jlblcargafoto.setVisible(false);
            }
        };
        Thread T= new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jtablaempleadoKeyReleased

    private void jtfnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfnombre);
    }//GEN-LAST:event_jtfnombreKeyTyped

    private void jtfrutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfrut);
    }//GEN-LAST:event_jtfrutKeyTyped

    private void jtfdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfdireccion);
    }//GEN-LAST:event_jtfdireccionKeyTyped

    private void jtfemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfemail);
    }//GEN-LAST:event_jtfemailKeyTyped

    private void jtfbuscarnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombreKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfbuscarnombre);
    }//GEN-LAST:event_jtfbuscarnombreKeyTyped

    private void jtfbuscarrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarrutActionPerformed
        // TODO add your handling code here:
          Runnable runnable= new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               sensitiva("rut");
                
            }
        };
        Thread FT = new Thread(runnable);
        FT.start();
    }//GEN-LAST:event_jtfbuscarrutActionPerformed

    private void jtfbuscarnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarnombreActionPerformed
        // TODO add your handling code here:
         Runnable runnable= new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               sensitiva("nombre");
                
            }
        };
        Thread FT = new Thread(runnable);
        FT.start();
    }//GEN-LAST:event_jtfbuscarnombreActionPerformed

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseReleased

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        // TODO add your handling code here:
          posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseDragged
        // TODO add your handling code here:
         int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel4MouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnfoto;
    private javax.swing.JButton jbtngeneracarnet;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnew;
    private javax.swing.JLabel jlblImageUser;
    private javax.swing.JLabel jlblcargaimagen;
    private javax.swing.JLabel jlblimagencarga;
    private javax.swing.JLabel jlblletracarga;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JTable jtablaempleado;
    private javax.swing.JTextField jtfbuscarnombre;
    private javax.swing.JTextField jtfbuscarrut;
    private javax.swing.JTextField jtfcelular;
    private javax.swing.JTextField jtfdireccion;
    private javax.swing.JTextField jtfemail;
    private javax.swing.JTextField jtfnombre;
    private javax.swing.JTextField jtfrut;
    // End of variables declaration//GEN-END:variables
}
