/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.FamiliaDAO;
import Pojos.Familia;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 *
 * @author info2017
 */
public class JDBuscarFamilia extends javax.swing.JDialog {

    /**
     * Creates new form JDBuscarFamilia
     */
    FamiliaDAO daofamilia= new FamiliaDAO();
    Familia familia ;
    JIProductos productos;
    Mayusculas mayus = new Mayusculas ();
    public JDBuscarFamilia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        daofamilia.mostrarfamilia(jtablafamilia);
//         setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent evt) {
//                close();
//            }
//        });
    }
     public JDBuscarFamilia(java.awt.Frame parent, boolean modal,Familia familia,JIProductos productos) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        daofamilia.mostrarfamilia(jtablafamilia);
        this.familia=familia;
        this.productos=productos;
//         setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent evt) {
//                close();
//            }
//        });
    }
   
    public void close(){
        jbtnaceptar.doClick();
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
        jtfdescripcion = new javax.swing.JTextField();
        jbtnaceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablafamilia = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR FAMILIA");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jtfdescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        jbtnaceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Checked.png"))); // NOI18N
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });

        jtablafamilia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        jScrollPane1.setViewportView(jtablafamilia);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtfdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jbtnaceptar)))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jtfdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnaceptar))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addGap(28, 28, 28))
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
        String descrip=jtfdescripcion.getText().trim().toUpperCase();
       daofamilia.busquedasensitivafamilia("DESCRIPCION",descrip,jtablafamilia);
        
    }//GEN-LAST:event_jtfdescripcionKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        int index= jtablafamilia.getSelectedRow();
        if (index >=0 ){
        long id = Long.parseLong(jtablafamilia.getValueAt(index, 0).toString());
        String descripcion= jtablafamilia.getValueAt(index, 1).toString();
        familia.setIdfamilia(id);
        familia.setDescripcion(descripcion);
        productos.setfamilia(familia);
        productos.validaguardar();
        this.dispose();
        }
        
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfdescripcion);
    }//GEN-LAST:event_jtfdescripcionKeyTyped

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
            java.util.logging.Logger.getLogger(JDBuscarFamilia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBuscarFamilia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBuscarFamilia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBuscarFamilia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBuscarFamilia dialog = new JDBuscarFamilia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JTable jtablafamilia;
    private javax.swing.JTextField jtfdescripcion;
    // End of variables declaration//GEN-END:variables
}