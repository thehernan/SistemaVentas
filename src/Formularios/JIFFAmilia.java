/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.FamiliaDAO;
import Pojos.Familia;
import javax.swing.JOptionPane;

/**
 *
 * @author info2017
 */
public class JIFFAmilia extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFFAmilia
     */
    FamiliaDAO daofamilia = new FamiliaDAO();
   Boolean editar;
   Mayusculas mayus= new Mayusculas();
    public JIFFAmilia() {
        initComponents();
         bloquearjtf(false, false);
        bloquearjbtn(true, false, false, false, false, true);
        daofamilia.mostrarfamilia(jtablafamilia);
    }
    public void bloquearjtf(boolean descripcion, boolean observacion){
    jtfdescripcion.setEnabled(descripcion);
    jtaobservacion.setEnabled(observacion);
    
     
    
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
    jtfdescripcion.setText("DESCRIPCION");
    jtaobservacion.setText("OBSERVACION");
   
    }
    public void validaguardar(){
        String desc= jtfdescripcion.getText().replaceAll("\\s","");
    if(!desc.equals("DESCRIPCION") && !desc.equals("")){
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
        jtablafamilia = new javax.swing.JTable();
        jbtncancelar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtnguardar = new javax.swing.JButton();
        jbtnnew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtfdescripcion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaobservacion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jtfbuscardescripcion = new javax.swing.JTextField();
        jbtneditar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtablafamilia.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtablafamilia.setModel(new javax.swing.table.DefaultTableModel(
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
        jtablafamilia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablafamilia.getTableHeader().setReorderingAllowed(false);
        jtablafamilia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablafamiliaMouseReleased(evt);
            }
        });
        jtablafamilia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablafamiliaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtablafamilia);

        jbtncancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtncancelar.setText("Cancelar");
        jbtncancelar.setToolTipText("Cancelar");
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });

        jbtneliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtneliminar.setText("Eliminar");
        jbtneliminar.setToolTipText("Eliminar");
        jbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneliminarActionPerformed(evt);
            }
        });

        jbtnguardar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnguardar.setText("Guardar");
        jbtnguardar.setToolTipText("Guardar");
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });

        jbtnnew.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnnew.setText("Nuevo");
        jbtnnew.setToolTipText("Nuevo");
        jbtnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnewActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtfdescripcion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfdescripcion.setText("DESCRIPCION");
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

        jtaobservacion.setColumns(20);
        jtaobservacion.setRows(5);
        jtaobservacion.setText("OBSERVACION");
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
        jScrollPane2.setViewportView(jtaobservacion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfdescripcion)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                .addGap(145, 145, 145))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jtfdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Buscar:");

        jtfbuscardescripcion.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscardescripcion.setText("DESCRIPCION");
        jtfbuscardescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscardescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscardescripcionFocusLost(evt);
            }
        });
        jtfbuscardescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscardescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscardescripcionKeyTyped(evt);
            }
        });

        jbtneditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jtfbuscardescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jbtneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jbtnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jbtncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(jtfbuscardescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jbtnnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtncancelar)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        jPanel3.setBackground(new java.awt.Color(238, 238, 238));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("REGISTRO DE FAMILIAS");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtablafamiliaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablafamiliaMouseReleased
        // TODO add your handling code here:
        Familia familia = null;
        long id = Long.parseLong(jtablafamilia.getValueAt(jtablafamilia.getSelectedRow(), 0).toString());
        familia = daofamilia.buscarfamilia("ID", id);
        jtfdescripcion.setText(familia.getDescripcion());
        jtaobservacion.setText(familia.getObservacion());
       
        bloquearjbtn(true, true, false, true, false, true);

    }//GEN-LAST:event_jtablafamiliaMouseReleased

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
        int index = jtablafamilia.getSelectedRow();
        if(index>=0){
            jtablafamiliaMouseReleased(null);
        } else {
            limpiarjtf();
        }
        bloquearjtf(false, false);
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
        int index= jtablafamilia.getSelectedRow();
        long id = Long.parseLong(jtablafamilia.getValueAt(index, 0).toString());
        if (index<0){
            JOptionPane.showMessageDialog(this, "SELECCIONE FAMILIA A ELIMINAR EN LA TABLA");
        }else {
            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL FAMILIA","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                daofamilia.eliminarfamilia(id);
                daofamilia.mostrarfamilia(jtablafamilia);
                bloquearjtf(false, false);
                bloquearjbtn(true, false, false, false, false, true);
                limpiarjtf();
            }

        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
        Familia familia= new Familia();

        String descrip=jtfdescripcion.getText().trim();
        String observacion=jtaobservacion.getText().trim();
       

        familia.setDescripcion(descrip);
        familia.setObservacion(observacion);
        
       

        if (descrip.length()>0 ){
            if(!descrip.equals("DESCRIPCION")){
                if(editar==false){
                    boolean valida=daofamilia.duplicado(0, descrip,"GUARDAR");
                    if(valida==true){
                         daofamilia.insertarfamilia(familia);
                    }else {
                    JOptionPane.showMessageDialog(null, "LA FAMILIA YA SE ENCUENTRA REGISTRADO","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                }
                   
                }

                else{
                    long id= Long.parseLong(jtablafamilia.getValueAt(jtablafamilia.getSelectedRow(), 0).toString());
                    familia.setIdfamilia(id);
                     boolean Vvalida=daofamilia.duplicado(id, descrip,"EDITAR");
                 if(Vvalida==true){
                     daofamilia.editarfamilia(familia);
                 }else {
                     JOptionPane.showMessageDialog(null, "LA DESCRIPCION INGRESADA YA SE ENCUENTRA REGISTRADA","SISTEMA",JOptionPane.INFORMATION_MESSAGE);
                 }
                    
                }

//                jtablafamilia.setModel(daofamilia.mostrarfamilia());

            }else {
                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS","ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }else {
            JOptionPane.showMessageDialog(null, "INGRESE TODOS LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);

        }
        daofamilia.mostrarfamilia(jtablafamilia);
        bloquearjtf(false, false);
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
        bloquearjtf(true, true);
        bloquearjbtn(true, false, false, false, true, true);
        limpiarjtf();editar=false;

        editar=false;
        //        jbtncancelar.setEnabled(true);
        //
        //        jbtneditar.setEnabled(false);
        //        guardar=false;
    }//GEN-LAST:event_jbtnnewActionPerformed

    private void jtfdescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusGained
        // TODO add your handling code here:
        if (jtfdescripcion.getText().equals("DESCRIPCION")){
            jtfdescripcion.setText("");
        }
    }//GEN-LAST:event_jtfdescripcionFocusGained

    private void jtfdescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusLost
        // TODO add your handling code here:
        if (jtfdescripcion.getText().equals("")){
            jtfdescripcion.setText("DESCRIPCION");
        }
    }//GEN-LAST:event_jtfdescripcionFocusLost

    private void jtfdescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfdescripcionKeyReleased

    private void jtfbuscardescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscardescripcionFocusGained
        // TODO add your handling code here:
        if (jtfbuscardescripcion.getText().equals("DESCRIPCION")){
            jtfbuscardescripcion.setText("");
        }
    }//GEN-LAST:event_jtfbuscardescripcionFocusGained

    private void jtfbuscardescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscardescripcionFocusLost
        // TODO add your handling code here:
        if (jtfbuscardescripcion.getText().equals("")){
            jtfbuscardescripcion.setText("DESCRIPCION");
        }
    }//GEN-LAST:event_jtfbuscardescripcionFocusLost

    private void jtfbuscardescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscardescripcionKeyReleased
        // TODO add your handling code here:
        String descrip=jtfbuscardescripcion.getText().trim().toUpperCase();
        daofamilia.busquedasensitivafamilia("DESCRIPCION", descrip,jtablafamilia);
    }//GEN-LAST:event_jtfbuscardescripcionKeyReleased

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        // TODO add your handling code here:
        bloquearjtf(true, true);
        editar=true;
        bloquearjbtn(true, false, false, false, true, true);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jtaobservacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaobservacionFocusGained
        // TODO add your handling code here:
          if (jtaobservacion.getText().equals("OBSERVACION")){
            jtaobservacion.setText("");
        }
    }//GEN-LAST:event_jtaobservacionFocusGained

    private void jtaobservacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaobservacionFocusLost
        // TODO add your handling code here:
        if (jtaobservacion.getText().equals("")){
            jtaobservacion.setText("OBSERVACION");
        }
    }//GEN-LAST:event_jtaobservacionFocusLost

    private void jtfdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfdescripcion);
    }//GEN-LAST:event_jtfdescripcionKeyTyped

    private void jtaobservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaobservacionKeyTyped
        // TODO add your handling code here:
         mayus.convertirmayusTA(jtaobservacion);
    }//GEN-LAST:event_jtaobservacionKeyTyped

    private void jtfbuscardescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscardescripcionKeyTyped
        // TODO add your handling code here:
         mayus.convertirmayus(jtfbuscardescripcion);
    }//GEN-LAST:event_jtfbuscardescripcionKeyTyped

    private void jtaobservacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaobservacionKeyReleased
        // TODO add your handling code here:
         validaguardar();
    }//GEN-LAST:event_jtaobservacionKeyReleased

    private void jtablafamiliaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablafamiliaKeyReleased
        // TODO add your handling code here:
        Familia familia = null;
        long id = Long.parseLong(jtablafamilia.getValueAt(jtablafamilia.getSelectedRow(), 0).toString());
        familia = daofamilia.buscarfamilia("ID", id);
        jtfdescripcion.setText(familia.getDescripcion());
        jtaobservacion.setText(familia.getObservacion());
       
        bloquearjbtn(true, true, false, true, false, true);
    }//GEN-LAST:event_jtablafamiliaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnew;
    private javax.swing.JTable jtablafamilia;
    private javax.swing.JTextArea jtaobservacion;
    private javax.swing.JTextField jtfbuscardescripcion;
    private javax.swing.JTextField jtfdescripcion;
    // End of variables declaration//GEN-END:variables
}