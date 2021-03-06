/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.DetalleCajaDAO;
import Pojos.Caja;
import Pojos.EmpleadoSingleton;

/**
 *
 * @author info2017
 */
public class JDDetalleMovimientoCaja extends javax.swing.JDialog {

    /**
     * Creates new form JDDetalleMovimientoCaja
     */
//    long idcaja;
    DetalleCajaDAO detcaja= new DetalleCajaDAO();
    EmpleadoSingleton empleadosingleton = EmpleadoSingleton.getinstancia();
    FormatoNumerico fn = new FormatoNumerico();
    public JDDetalleMovimientoCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
     public JDDetalleMovimientoCaja(java.awt.Frame parent, boolean modal,Caja caja) {
        super(parent, modal);
        initComponents();
        Caja c=detcaja.mostrar(jtabla, caja.getId_caja());
        jlbltotal.setText("Total: "+fn.FormatoN(c.getTotal()));
        jlblaperturo.setText("Dinero apertura: "+fn.FormatoN(c.getAperturadinero()));
        jlblcierra.setText("Dinero cierra: "+fn.FormatoN(c.getCierradinero()));
        jlblcajero.setText(c.getCajero());
               
//        jlblaperturo.setValue(caja.getAperturadinero());
//        jlblfecha.setText(caja.getFechahora_apertura().toString());
//        jlblcierra.setValue(caja.getCierradinero());
//        jlblcajero.setText(empleadosingleton.getNombre());
//        calculatotal();
        this.setLocationRelativeTo(null);
//        this.idcaja=idcaja;
        
    }
//     public void calculatotal(){
//        Double total=0.0;
//        boolean anula;
//        for (int i=0;i<=jtabla.getRowCount()-1 ;i++){
//         anula = Boolean.parseBoolean(jtabla.getValueAt(i, 6).toString());
//         if(anula==false){
//              total = total + Double.parseDouble(jtabla.getValueAt(i, 4).toString());
//         }
//        
//        }
//       
//               
//        jlbltotal.setValue(total);
//        
//       
//    }

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
        jLabel1 = new javax.swing.JLabel();
        jlblcajero = new javax.swing.JLabel();
        jlblaperturo = new javax.swing.JLabel();
        jlblcierra = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtabla);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("CAJERO(A):");

        jlblcajero.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jlblcajero.setText("jLabel5");

        jlblaperturo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlblaperturo.setText("jLabel5");

        jlblcierra.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlblcierra.setText("jLabel5");

        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlbltotal.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblcajero)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblaperturo, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addComponent(jlbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblcierra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlblcajero))
                .addGap(16, 16, 16)
                .addComponent(jlblaperturo)
                .addGap(6, 6, 6)
                .addComponent(jlbltotal)
                .addGap(4, 4, 4)
                .addComponent(jlblcierra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(JDDetalleMovimientoCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDDetalleMovimientoCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDDetalleMovimientoCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDDetalleMovimientoCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDDetalleMovimientoCaja dialog = new JDDetalleMovimientoCaja(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblaperturo;
    private javax.swing.JLabel jlblcajero;
    private javax.swing.JLabel jlblcierra;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JTable jtabla;
    // End of variables declaration//GEN-END:variables
}
