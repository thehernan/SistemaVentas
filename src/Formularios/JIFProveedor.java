/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ProveedorDAO;
import Pojos.Proveedor;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author info2017
 */
public class JIFProveedor extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFProveedor
     */
   ProveedorDAO daoproveedor = new ProveedorDAO();
   Boolean editar;
   Mayusculas mayus = new Mayusculas();
   Proveedor proveedor= new Proveedor();
    List<Proveedor> listprove;
     int posx;
    int posy;
    public JIFProveedor() {
        initComponents();
        bloquearjtf(false, false, false, false, false);
        bloquearjbtn(true, false, false, false, false, true);
        mostrar();
    }
    
    public void mostrar(){
    
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            jlblimagencarga.setVisible(true);
            jlblletracarga.setVisible(true);
            jtfbuscarnombrerazon.setEnabled(false);
            jtfbuscarrut.setEnabled(false);
             listprove=daoproveedor.mostrarproveedor(jtablaproveedor);
             jlblimagencarga.setVisible(false);
            jlblletracarga.setVisible(false);
             jtfbuscarnombrerazon.setEnabled(true);
            jtfbuscarrut.setEnabled(true);
        }
    };
    
    Thread T = new Thread(runnable);
    T.start();
    
    
    }
   
    
    
    
    
    public void bloquearjtf(boolean rut, boolean nombre,boolean email, boolean direccion, boolean celular){
    jtfrut.setEnabled(rut);
    jtfnombrerazon.setEnabled(nombre);
    jtfemail.setEnabled(email);
    jtfdireccion.setEnabled(direccion);
    jtfcelular.setEnabled(celular);
     
    
    }
    public void bloquearjbtn(boolean nuevo,boolean editar,boolean guardar,boolean eliminar,boolean cancelar,
    boolean salir ){
    jbtnnew.setEnabled(nuevo);
    jbtneditar.setEnabled(editar);
    jbtnguardar.setEnabled(guardar);
    jbtneliminar.setEnabled(eliminar);
    jbtncancelar.setEnabled(cancelar);
//    jbtnsalir.setEnabled(salir);
    
    }
     public void limpiarjtf(){
    jtfrut.setText("");
    jtfnombrerazon.setText("");
    jtfemail.setText("");
    jtfdireccion.setText("");
    jtfcelular.setText("");
    }
    public void validaguardar(){
       String nombre= jtfnombrerazon.getText().replaceAll("\\s","");
        String rut= jtfrut.getText().replaceAll("\\s","");
        String direc= jtfrut.getText().replaceAll("\\s","");
        String cel= jtfrut.getText().replaceAll("\\s","");
        String email= jtfrut.getText().replaceAll("\\s","");
        
        System.out.println(nombre);
    if(nombre.length()>0 && rut.length()>0 && direc.length()>0 && cel.length()>0 && email.length()>0){
        bloquearjbtn(false, false, true,false ,true, true);
    }else {
        bloquearjbtn(true, false, false, false, true, true);
    }
    
    }
    public void ver(){
        System.out.println("getselecttabla"+jtablaproveedor.getSelectedRow());
        try {
            if(jtablaproveedor.getSelectedRow()>=0){
                System.out.println("listprove"+listprove.size());
            proveedor = listprove.get(jtablaproveedor.getSelectedRow());
            jtfnombrerazon.setText(proveedor.getNombrerazons());
            jtfrut.setText(proveedor.getRut());
            jtfcelular.setText(proveedor.getCelular());
            jtfdireccion.setText(proveedor.getDireccion());
            jtfemail.setText(proveedor.getEmail());
            bloquearjbtn(true, true, false, true, false, true);
             }else{ 
                 limpiarjtf();
                 bloquearjtf(false, false,false, false,false);
                bloquearjbtn(true, false, false, false, false, true);

            }
            
            
            
        } catch (Exception e) {
            System.out.println("Exception"+e);
        }
        
    
    }
    
    public  void sensitiva(String op){
        jlblimagencarga.setVisible(true);
        jlblletracarga.setVisible(true);
        if(op.equals("rut")){
        String rut= jtfbuscarrut.getText().trim();
        listprove=daoproveedor.busquedasensitivaproveedor("RUT",rut,jtablaproveedor);
        }
        if(op.equals("nombre")){
            
        String nombre= jtfbuscarnombrerazon.getText().trim();
        listprove=daoproveedor.busquedasensitivaproveedor("NOMBRE",nombre,jtablaproveedor);
        
        }    
     
        ver();
       
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
        jtablaproveedor = new javax.swing.JTable();
        jbtncancelar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtnguardar = new javax.swing.JButton();
        jbtnnew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtfrut = new javax.swing.JTextField();
        jtfdireccion = new javax.swing.JTextField();
        jtfcelular = new javax.swing.JTextField();
        jtfemail = new javax.swing.JTextField();
        jtfnombrerazon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtfbuscarrut = new javax.swing.JTextField();
        jtfbuscarnombrerazon = new javax.swing.JTextField();
        jbtneditar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1129, 418));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1123, 380));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblletracarga.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblletracarga.setForeground(new java.awt.Color(0, 0, 0));
        jlblletracarga.setText("Cargando Registro ...");
        jPanel1.add(jlblletracarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        jlblimagencarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblimagencarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel1.add(jlblimagencarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 490, 220));

        jtablaproveedor.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtablaproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtablaproveedor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaproveedor.getTableHeader().setReorderingAllowed(false);
        jtablaproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaproveedorMouseReleased(evt);
            }
        });
        jtablaproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaproveedorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtablaproveedor);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 507, 280));

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
        jPanel1.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, 80, -1));

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
        jPanel1.add(jbtneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 70, -1));

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
        jPanel1.add(jbtnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 70, -1));

        jbtnnew.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnew.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jbtnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnnew.setText("Nuevo");
        jbtnnew.setToolTipText("Nuevo");
        jbtnnew.setBorder(null);
        jbtnnew.setBorderPainted(false);
        jbtnnew.setContentAreaFilled(false);
        jbtnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnewActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 70, 20));

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
        });
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 139, -1));

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
        jPanel2.add(jtfdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 420, -1));

        jtfcelular.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfcelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcelularFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcelularFocusLost(evt);
            }
        });
        jtfcelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcelularKeyReleased(evt);
            }
        });
        jPanel2.add(jtfcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 140, -1));

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
        jPanel2.add(jtfemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 580, -1));

        jtfnombrerazon.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfnombrerazon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfnombrerazonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfnombrerazonFocusLost(evt);
            }
        });
        jtfnombrerazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfnombrerazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfnombrerazonKeyTyped(evt);
            }
        });
        jPanel2.add(jtfnombrerazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 420, -1));

        jLabel3.setText("Sr(es):");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel4.setText("N° Doc.:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel5.setText("Dirección:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel6.setText("Celular:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, -1));

        jLabel7.setText("E-mail:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 600, 160));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel1.setText("Buscar:");
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

        jtfbuscarnombrerazon.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscarnombrerazon.setText("NOMBRE O RAZON SOCIAL");
        jtfbuscarnombrerazon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarnombrerazonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarnombrerazonFocusLost(evt);
            }
        });
        jtfbuscarnombrerazon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfbuscarnombrerazonActionPerformed(evt);
            }
        });
        jtfbuscarnombrerazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarnombrerazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscarnombrerazonKeyTyped(evt);
            }
        });
        jPanel1.add(jtfbuscarnombrerazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 320, -1));

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
        jPanel1.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 70, -1));

        jPanel3.setBackground(new java.awt.Color(238, 238, 238));
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

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setText("REGISTRO PROVEEDORES");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel9.setToolTipText("Cerrar");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel9MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 812, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1123, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
          int index = jtablaproveedor.getSelectedRow();
        if(index>=0){
            ver();
        } else {
        limpiarjtf();
        }
        bloquearjtf(false, false, false, false, false);
        bloquearjbtn(true, false, false, false, false, true);
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
        int index= jtablaproveedor.getSelectedRow();
       proveedor = listprove.get(index);
        if (index<0){
            JOptionPane.showMessageDialog(this, "SELECCIONE PROVEEDOR A ELIMINAR EN LA TABLA");
        }else {
            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL PROVEEDOR","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                daoproveedor.eliminarproveedor(proveedor.getIdproveedor());
                 bloquearjtf(false, false, false, false, false);
                bloquearjbtn(true, false, false, false, false, true);
                limpiarjtf();
                listprove= daoproveedor.mostrarproveedor(jtablaproveedor);
            }

        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
//          Proveedor proveedor= new Proveedor();
        
        String nombre=jtfnombrerazon.getText().trim().toUpperCase();
        String rut=jtfrut.getText().trim().toUpperCase();
        String direccion=jtfdireccion.getText().trim().toUpperCase();
        String celular=jtfcelular.getText().trim().toUpperCase();
        String email=jtfemail.getText().trim().toUpperCase();
        
     
        proveedor.setNombrerazons(nombre);
        proveedor.setRut(rut);
        proveedor.setDireccion(direccion);
        proveedor.setCelular(celular);
        proveedor.setEmail(email);
        
        if (nombre.length()>0 && rut.length()>0 && direccion.length()>0
                && celular.length()>0 && email.length()>0){
         
                if(editar==false){
                boolean valida=daoproveedor.duplicado(0, rut,"GUARDAR");
                if(valida==true){
                    daoproveedor.insertarproveedor(proveedor);
                }else {
                    JOptionPane.showMessageDialog(null, "EL PROVEEDOR YA SE ENCUENTRA REGISTRADO","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                }    
                
                }
                
                else{
//                long id= Long.parseLong(jtablaproveedor.getValueAt(jtablaproveedor.getSelectedRow(), 0).toString());
//                proveedor.setIdproveedor(id);
                boolean Vvalida=daoproveedor.duplicado(proveedor.getIdproveedor(), rut,"EDITAR");
                 if(Vvalida==true){
                      daoproveedor.editarproveedor(proveedor);
                 }else {
                     JOptionPane.showMessageDialog(null, "EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                 }
               
                        } 
                
                       
                listprove=daoproveedor.mostrarproveedor(jtablaproveedor); 
                
               listprove= daoproveedor.mostrarproveedor(jtablaproveedor);
                bloquearjtf(false, false, false, false, false);
                bloquearjbtn(true, false, false, false, false, true);
                limpiarjtf(); 
           
           
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
        bloquearjbtn(true, false, false, false, true, true);
        limpiarjtf();
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

    private void jtfnombrerazonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombrerazonFocusGained
        // TODO add your handling code here:
//        if (jtfnombrerazon.getText().equals("NOMBRE O RAZON SOCIAL")){
//            jtfnombrerazon.setText("");
//        }
    }//GEN-LAST:event_jtfnombrerazonFocusGained

    private void jtfnombrerazonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombrerazonFocusLost
        // TODO add your handling code here:
//        if (jtfnombrerazon.getText().equals("")){
//            jtfnombrerazon.setText("NOMBRE O RAZON SOCIAL");
//        }
    }//GEN-LAST:event_jtfnombrerazonFocusLost

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

    private void jtfbuscarnombrerazonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonFocusGained
        // TODO add your handling code here:
        if (jtfbuscarnombrerazon.getText().equals("NOMBRE O RAZON SOCIAL")){
            jtfbuscarnombrerazon.setText("");
        }
    }//GEN-LAST:event_jtfbuscarnombrerazonFocusGained

    private void jtfbuscarnombrerazonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonFocusLost
        // TODO add your handling code here:
        if (jtfbuscarnombrerazon.getText().equals("")){
            jtfbuscarnombrerazon.setText("NOMBRE O RAZON SOCIAL");
        }
    }//GEN-LAST:event_jtfbuscarnombrerazonFocusLost

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        // TODO add your handling code here:
          bloquearjtf(true, true, true,true, true);
        editar=true;
        bloquearjbtn(true, false, false, false, true, true);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jtfbuscarrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarrutKeyReleased
        // TODO add your handling code here:
//        String rut= jtfbuscarrut.getText().trim();
//        listprove=daoproveedor.busquedasensitivaproveedor("RUT",rut,jtablaproveedor);
//        if(jtablaproveedor.getSelectedRow()>=0){
//            ver();
//        
//        
//        }else {
//        limpiarjtf();
//         bloquearjtf(false, false, false, false, false);
//        bloquearjbtn(true, false, false, false, false, true);
//        }
    }//GEN-LAST:event_jtfbuscarrutKeyReleased

    private void jtfbuscarnombrerazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonKeyReleased
        // TODO add your handling code here:
//        String nombre=jtfbuscarnombrerazon.getText().trim().toUpperCase();
//        listprove=daoproveedor.busquedasensitivaproveedor("NOMBRE", nombre,jtablaproveedor);
//       
//        if(jtablaproveedor.getSelectedRow()>=0){
//            ver();    
//        }else {
//        limpiarjtf();
//         bloquearjtf(false, false, false, false, false);
//        bloquearjbtn(true, false, false, false, false, true);
//        }
    }//GEN-LAST:event_jtfbuscarnombrerazonKeyReleased

    private void jtfnombrerazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombrerazonKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfnombrerazonKeyReleased

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jtfdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfdireccionKeyReleased

    private void jtfcelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcelularKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfcelularKeyReleased

    private void jtfemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfemailKeyReleased

    private void jtablaproveedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaproveedorMouseReleased
        // TODO add your handling code here:
        
        ver();
    }//GEN-LAST:event_jtablaproveedorMouseReleased

    private void jtfnombrerazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombrerazonKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfnombrerazon);
    }//GEN-LAST:event_jtfnombrerazonKeyTyped

    private void jtfdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyTyped
        // TODO add your handling code here:
//         mayus.convertirmayus(jtfdireccion);
    }//GEN-LAST:event_jtfdireccionKeyTyped

    private void jtfemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfemail);
    }//GEN-LAST:event_jtfemailKeyTyped

    private void jtfbuscarnombrerazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfbuscarnombrerazon);
    }//GEN-LAST:event_jtfbuscarnombrerazonKeyTyped

    private void jtablaproveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaproveedorKeyReleased
        // TODO add your handling code here:
         ver();
    }//GEN-LAST:event_jtablaproveedorKeyReleased

    private void jtfbuscarnombrerazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonActionPerformed
        // TODO add your handling code here:
         Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                sensitiva("nombre");
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jtfbuscarnombrerazonActionPerformed

    private void jtfbuscarrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarrutActionPerformed
        // TODO add your handling code here:
         Runnable runnable = new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                sensitiva("rut");
            }
        };
        Thread T = new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jtfbuscarrutActionPerformed

    private void jLabel9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseReleased

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnew;
    private javax.swing.JLabel jlblimagencarga;
    private javax.swing.JLabel jlblletracarga;
    private javax.swing.JTable jtablaproveedor;
    private javax.swing.JTextField jtfbuscarnombrerazon;
    private javax.swing.JTextField jtfbuscarrut;
    private javax.swing.JTextField jtfcelular;
    private javax.swing.JTextField jtfdireccion;
    private javax.swing.JTextField jtfemail;
    private javax.swing.JTextField jtfnombrerazon;
    private javax.swing.JTextField jtfrut;
    // End of variables declaration//GEN-END:variables
}
