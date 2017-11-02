/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.EmpleadoDAO;
import Pojos.Empleado;
import Pojos.Usuarios;

/**
 *
 * @author info2017
 */
public class JDBuscarEmpleado extends javax.swing.JDialog {

    /**
     * Creates new form JDBuscarEmpleado
     */
    EmpleadoDAO daoempleado = new EmpleadoDAO();
    Empleado empleado = new Empleado();
    Usuarios user=new Usuarios();
    JIFUsuarios fromuser;
    JIFReparaciones frmreparacion;
    String tipoB;
    Mayusculas mayus = new Mayusculas();
    public JDBuscarEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        daoempleado.mostrarempleado(jtabla);
        this.setLocationRelativeTo(null);
    }
     public JDBuscarEmpleado(java.awt.Frame parent, boolean modal,Usuarios user, JIFUsuarios frmuser) {//CONTRUCTOR USUARIO
        super(parent, modal);
        initComponents();
        daoempleado.mostrarempleado(jtabla);
        this.user=user;
        this.fromuser=frmuser;
        this.setLocationRelativeTo(null);
        tipoB= "USUARIO";
    }
     public JDBuscarEmpleado(java.awt.Frame parent, boolean modal,JIFReparaciones frmreparacion) {//CONTRUCTOR REPARACION
        super(parent, modal);
        initComponents();
        daoempleado.mostrarempleado(jtabla);
        this.setLocationRelativeTo(null);
        this.frmreparacion= frmreparacion;
        tipoB="REPARACION";
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
        jbtnaceptar = new javax.swing.JButton();
        jtfnombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

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
        jScrollPane1.setViewportView(jtabla);

        jbtnaceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Checked.png"))); // NOI18N
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });

        jtfnombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfnombre.setText("NOMBRE");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtfnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jbtnaceptar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(3, 3, 3)))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jtfnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnaceptar))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyReleased
        // TODO add your handling code here:
        
        daoempleado.busquedasensitivaempleado(jtabla,"NOMBRE", jtfnombre.getText().trim().toUpperCase());
    }//GEN-LAST:event_jtfnombreKeyReleased

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        int index= jtabla.getSelectedRow();
        if(index>=0){
            
            long idempleado= Long.parseLong(jtabla.getValueAt(index, 0).toString());
            String rut=jtabla.getValueAt(index, 1).toString();
            String nombre= jtabla.getValueAt(index, 2).toString();
            System.out.println("idemplebuscar"+idempleado);
            
            empleado.setId_empleado(idempleado);
            empleado.setNombre(nombre);
            empleado.setRut(rut);
            
            if(tipoB.equals("USUARIO")){ //SI LLAMA EL CONTRUCTOR USUARIO
                user.setIdempleado(idempleado);
                fromuser.setuser(user);
                fromuser.setempleado(empleado);
                fromuser.validaguardar();
            }
            if(tipoB.equals("REPARACION")){ //SI LLAMA EL CONTRUCTOR REPARACION
                frmreparacion.setempleado(empleado);
                frmreparacion.validaaceptar();
            
            }
            
            
            
            
            
            this.dispose();
        }
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusGained
        // TODO add your handling code here:
        if (jtfnombre.getText().equals("NOMBRE")){
            jtfnombre.setText("");
        }
    }//GEN-LAST:event_jtfnombreFocusGained

    private void jtfnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusLost
        // TODO add your handling code here:
          if (jtfnombre.getText().equals("")){
            jtfnombre.setText("NOMBRE");
        }
    }//GEN-LAST:event_jtfnombreFocusLost

    private void jtfnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfnombre);
    }//GEN-LAST:event_jtfnombreKeyTyped

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
            java.util.logging.Logger.getLogger(JDBuscarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBuscarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBuscarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBuscarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBuscarEmpleado dialog = new JDBuscarEmpleado(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfnombre;
    // End of variables declaration//GEN-END:variables
}