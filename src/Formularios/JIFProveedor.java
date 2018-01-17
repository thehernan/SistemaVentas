/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ProveedorDAO;
import Pojos.Proveedor;
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
    public JIFProveedor() {
        initComponents();
         bloquearjtf(false, false, false, false, false);
        bloquearjbtn(true, false, false, false, false, true);
        daoproveedor.mostrarproveedor(jtablaproveedor);
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
    jtfrut.setText("R.U.T");
    jtfnombrerazon.setText("NOMBRE O RAZON SOCIAL");
    jtfemail.setText("EMAIL");
    jtfdireccion.setText("DIRECCION");
    jtfcelular.setText("CELULAR");
    }
    public void validaguardar(){
       String nombre= jtfnombrerazon.getText().replaceAll("\\s","");
        String rut= jtfrut.getText().replaceAll("\\s","");
        System.out.println(nombre);
    if(!jtfnombrerazon.getText().equals("NOMBRE O RAZON SOCIAL") && !nombre.equals("") && 
            !jtfrut.getText().equals("R.U.T") && rut.length()>0){
        bloquearjbtn(false, false, true,false ,true, true);
    }else {
        bloquearjbtn(true, false, false, false, true, true);
    }
    
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
        jLabel1 = new javax.swing.JLabel();
        jtfbuscarrut = new javax.swing.JTextField();
        jtfbuscarnombrerazon = new javax.swing.JTextField();
        jbtneditar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtablaproveedor.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtablaproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtablaproveedor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaproveedor.getTableHeader().setReorderingAllowed(false);
        jtablaproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaproveedorMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtablaproveedor);

        jbtncancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncancelar.setText("Cancelar");
        jbtncancelar.setToolTipText("Cancelar");
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });

        jbtneliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneliminar.setText("Eliminar");
        jbtneliminar.setToolTipText("Eliminar");
        jbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneliminarActionPerformed(evt);
            }
        });

        jbtnguardar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setText("Guardar");
        jbtnguardar.setToolTipText("Guardar");
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });

        jbtnnew.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnew.setText("Nuevo");
        jbtnnew.setToolTipText("Nuevo");
        jbtnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnewActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfrut.setText("R.U.T");
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
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 139, -1));

        jtfdireccion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfdireccion.setText("DIRECCION");
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
        jPanel2.add(jtfdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 420, -1));

        jtfcelular.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfcelular.setText("CELULAR");
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
        jPanel2.add(jtfcelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 140, -1));

        jtfemail.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfemail.setText("EMAIL");
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
        jPanel2.add(jtfemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 580, -1));

        jtfnombrerazon.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfnombrerazon.setText("NOMBRE O RAZON SOCIAL");
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
        jPanel2.add(jtfnombrerazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 420, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel1.setText("Buscar:");

        jtfbuscarrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscarrut.setText("R.U.T");
        jtfbuscarrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarrutFocusLost(evt);
            }
        });
        jtfbuscarrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarrutKeyReleased(evt);
            }
        });

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
        jtfbuscarnombrerazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarnombrerazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscarnombrerazonKeyTyped(evt);
            }
        });

        jbtneditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneditar.setText("Editar");
        jbtneditar.setToolTipText("Editar");
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jtfbuscarrut, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jtfbuscarnombrerazon, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jbtneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jbtnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jbtneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(5, 5, 5)
                                .addComponent(jtfbuscarrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jtfbuscarnombrerazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbtneliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnguardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtneditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnnew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtncancelar))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(238, 238, 238));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("REGISTRO PROVEEDORES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
          int index = jtablaproveedor.getSelectedRow();
        if(index>=0){
        jtablaproveedorMouseReleased(null);
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
        long id = Long.parseLong(jtablaproveedor.getValueAt(index, 0).toString());
        if (index<0){
            JOptionPane.showMessageDialog(this, "SELECCIONE PROVEEDOR A ELIMINAR EN LA TABLA");
        }else {
            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL PROVEEDOR","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                daoproveedor.eliminarproveedor(id);
                 bloquearjtf(false, false, false, false, false);
                bloquearjbtn(true, false, false, false, false, true);
                limpiarjtf();
                 daoproveedor.mostrarproveedor(jtablaproveedor);
            }

        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
          Proveedor proveedor= new Proveedor();
        
        String nombre=jtfnombrerazon.getText().trim();
        String rut=jtfrut.getText().trim();
        String direccion=jtfdireccion.getText().trim();
        String celular=jtfcelular.getText().trim();
        String email=jtfemail.getText().trim();
        
     
        proveedor.setNombrerazons(nombre);
        proveedor.setRut(rut);
        proveedor.setDireccion(direccion);
        proveedor.setCelular(celular);
        proveedor.setEmail(email);
        
        if (nombre.length()>0 && rut.length()>0 && direccion.length()>0
                && celular.length()>0 && email.length()>0){
            if(!nombre.equals("NOMBRE O RAZON SOCIAL")){
                if(editar==false){
                boolean valida=daoproveedor.duplicado(0, rut,"GUARDAR");
                if(valida==true){
                    daoproveedor.insertarproveedor(proveedor);
                }else {
                    JOptionPane.showMessageDialog(null, "EL PROVEEDOR YA SE ENCUENTRA REGISTRADO","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                }    
                
                }
                
                else{
                long id= Long.parseLong(jtablaproveedor.getValueAt(jtablaproveedor.getSelectedRow(), 0).toString());
                proveedor.setIdproveedor(id);
                boolean Vvalida=daoproveedor.duplicado(id, rut,"EDITAR");
                 if(Vvalida==true){
                      daoproveedor.editarproveedor(proveedor);
                 }else {
                     JOptionPane.showMessageDialog(null, "EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                 }
               
                        } 
                
                       
                daoproveedor.mostrarproveedor(jtablaproveedor); 
                
                
            }else {
                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS","ERROR",JOptionPane.ERROR_MESSAGE);
            } 
           
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE TODOS LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
        
        }
           daoproveedor.mostrarproveedor(jtablaproveedor);
        bloquearjtf(false, false, false, false, false);
        bloquearjbtn(true, false, false, false, false, true);
        limpiarjtf();
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
        if (jtfrut.getText().equals("R.U.T")){
            jtfrut.setText("");
        }
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
        if (jtfrut.getText().equals("")){
            jtfrut.setText("R.U.T");
        }
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfdireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdireccionFocusGained
        // TODO add your handling code here:
        if (jtfdireccion.getText().equals("DIRECCION")){
            jtfdireccion.setText("");
        }
    }//GEN-LAST:event_jtfdireccionFocusGained

    private void jtfdireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdireccionFocusLost
        // TODO add your handling code here:
        if (jtfdireccion.getText().equals("")){
            jtfdireccion.setText("DIRECCION");
        }
    }//GEN-LAST:event_jtfdireccionFocusLost

    private void jtfcelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcelularFocusGained
        // TODO add your handling code here:
        if (jtfcelular.getText().equals("CELULAR")){
            jtfcelular.setText("");
        }
    }//GEN-LAST:event_jtfcelularFocusGained

    private void jtfcelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcelularFocusLost
        // TODO add your handling code here:
        if (jtfcelular.getText().equals("")){
            jtfcelular.setText("CELULAR");
        }
    }//GEN-LAST:event_jtfcelularFocusLost

    private void jtfemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfemailFocusGained
        // TODO add your handling code here:
        if (jtfemail.getText().equals("EMAIL")){
            jtfemail.setText("");
        }
    }//GEN-LAST:event_jtfemailFocusGained

    private void jtfemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfemailFocusLost
        // TODO add your handling code here:
        if (jtfemail.getText().equals("")){
            jtfemail.setText("EMAIL");
        }
    }//GEN-LAST:event_jtfemailFocusLost

    private void jtfnombrerazonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombrerazonFocusGained
        // TODO add your handling code here:
        if (jtfnombrerazon.getText().equals("NOMBRE O RAZON SOCIAL")){
            jtfnombrerazon.setText("");
        }
    }//GEN-LAST:event_jtfnombrerazonFocusGained

    private void jtfnombrerazonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombrerazonFocusLost
        // TODO add your handling code here:
        if (jtfnombrerazon.getText().equals("")){
            jtfnombrerazon.setText("NOMBRE O RAZON SOCIAL");
        }
    }//GEN-LAST:event_jtfnombrerazonFocusLost

    private void jtfbuscarrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarrutFocusGained
        // TODO add your handling code here:
        if (jtfbuscarrut.getText().equals("R.U.T")){
            jtfbuscarrut.setText("");
        }
    }//GEN-LAST:event_jtfbuscarrutFocusGained

    private void jtfbuscarrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarrutFocusLost
        // TODO add your handling code here:
        if (jtfbuscarrut.getText().equals("")){
            jtfbuscarrut.setText("R.U.T");
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
        String rut= jtfbuscarrut.getText().trim();
        daoproveedor.busquedasensitivaproveedor("RUT",rut,jtablaproveedor);
    }//GEN-LAST:event_jtfbuscarrutKeyReleased

    private void jtfbuscarnombrerazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonKeyReleased
        // TODO add your handling code here:
        String nombre=jtfbuscarnombrerazon.getText().trim().toUpperCase();
        daoproveedor.busquedasensitivaproveedor("NOMBRE", nombre,jtablaproveedor);
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
         Proveedor proveedor = null;
        long id = Long.parseLong(jtablaproveedor.getValueAt(jtablaproveedor.getSelectedRow(), 0).toString());
        proveedor = daoproveedor.buscarproveedor("ID", id);    
        jtfnombrerazon.setText(proveedor.getNombrerazons());
        jtfrut.setText(proveedor.getRut());
        jtfcelular.setText(proveedor.getCelular());
        jtfdireccion.setText(proveedor.getDireccion());
        jtfemail.setText(proveedor.getEmail());
        bloquearjbtn(true, true, false, true, false, true);
        
    }//GEN-LAST:event_jtablaproveedorMouseReleased

    private void jtfnombrerazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombrerazonKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfnombrerazon);
    }//GEN-LAST:event_jtfnombrerazonKeyTyped

    private void jtfdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdireccionKeyTyped
        // TODO add your handling code here:
         mayus.convertirmayus(jtfdireccion);
    }//GEN-LAST:event_jtfdireccionKeyTyped

    private void jtfemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfemailKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfemail);
    }//GEN-LAST:event_jtfemailKeyTyped

    private void jtfbuscarnombrerazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombrerazonKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfbuscarnombrerazon);
    }//GEN-LAST:event_jtfbuscarnombrerazonKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnew;
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
