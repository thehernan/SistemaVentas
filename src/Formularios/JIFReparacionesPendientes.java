/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ReparacionDAO;
import Pojos.Reparacion;
import Pojos.UsuarioSingleton;

/**
 *
 * @author info2017
 */
public final class JIFReparacionesPendientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFReparacionesPendientes
     */
    ReparacionDAO daoreparacion= new ReparacionDAO();
    UsuarioSingleton usuariosingleton =  UsuarioSingleton.getintancia();
    Reparacion reparacion= new Reparacion();
    public JIFReparacionesPendientes() {
        initComponents();
        jbtnimprimir.setEnabled(false);
        mostrar(usuariosingleton.getIdempleado()); 
    }
    public void mostrar(long idemple){
         daoreparacion.mostrarpendientes(jtabla,idemple);
         System.out.println(" mostrar");
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
        jtabla = new javax.swing.JTable();
        jbtnimprimir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();
        jbtnrefrescar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtabla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jbtnimprimir.setBackground(new java.awt.Color(255, 255, 255));
        jbtnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print_46933.png"))); // NOI18N
        jbtnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("DOBLE CLIC SOBRE SOBRE LA REPARACION ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("PARA DAR POR REPARADO O NO EL DISPOSITIVO");

        jlblmensaje.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));

        jbtnrefrescar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnrefrescar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnrefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh.png"))); // NOI18N
        jbtnrefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnrefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(jlblmensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnrefrescar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jbtnimprimir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1)))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnrefrescar)
                            .addComponent(jbtnimprimir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblmensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirActionPerformed
        // TODO add your handling code here:
        int index = jtabla.getSelectedRow();
        if (index>=0){
            long id = Long.parseLong(jtabla.getValueAt(index,0).toString());
            daoreparacion.imprimir(id);
            jlblmensaje.setText("");
        }else {
            jlblmensaje.setText("SELECCIONE UN REPARACION DE LA TABLA");
            }
    }//GEN-LAST:event_jbtnimprimirActionPerformed

    private void jtablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMousePressed
        // TODO add your handling code here:
        jbtnimprimir.setEnabled(true);
        int index = jtabla.getSelectedRow();
        if(index >= 0){
            if (evt.getClickCount()==2) {
                long id= Long.parseLong(jtabla.getValueAt(index, 0).toString());
                String marca = jtabla.getValueAt(index,5).toString();
                String modelo = jtabla.getValueAt(index, 6).toString();
                reparacion.setIdreparacion(id);
                reparacion.setModelo(modelo);
                reparacion.setMarca(marca);
                JDDiagnosticoReparacion dreparacion= new JDDiagnosticoReparacion(new java.awt.Frame(), isVisible(),
                reparacion,this);
                dreparacion.setVisible(true);
            }
        
        }
        
        
    }//GEN-LAST:event_jtablaMousePressed

    private void jbtnrefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnrefrescarActionPerformed
        // TODO add your handling code here:
        mostrar(usuariosingleton.getIdempleado()); 
    }//GEN-LAST:event_jbtnrefrescarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnimprimir;
    private javax.swing.JButton jbtnrefrescar;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JTable jtabla;
    // End of variables declaration//GEN-END:variables
}
