/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.VentasDAO;
import Pojos.Cliente;
import Pojos.Ventas;
import java.awt.Frame;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author info2017
 */
public class JIFVentaConsultar extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFVentaConsultar
     */
    VentasDAO daoventa = new VentasDAO();
    Cliente cliente = new Cliente();
    List<Ventas> listventcli;
    List<Ventas> listventemp;
    List<Ventas> listventanula;
    Ventas venta;
    public JIFVentaConsultar() {
        initComponents();
        jbtnimprimirporventa.setEnabled(false);
        jbtnimprimirtodo.setEnabled(false);
    }
    
    public void setcliente(Cliente cliente){
        this.cliente=cliente;
        jlblcliente.setText(cliente.getNombre_razons());
        jlblrut.setText(cliente.getRut());
        listventcli=daoventa.mostrarporcliente(jtablacliente,  cliente.getId_cliente(),jlblmsjcliente);
        jbtnimprimirtodo.setEnabled(true);
    }
    
    public void refrecar(){
     listventcli=daoventa.mostrarporcliente(jtablacliente,  cliente.getId_cliente(),jlblmsjcliente);
     jbtnimprimirtodo.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdpdesde = new org.jdesktop.swingx.JXDatePicker();
        jdphasta = new org.jdesktop.swingx.JXDatePicker();
        jbtnbuscar = new javax.swing.JButton();
        jlblmensaje = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablaempleado = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jlblcliente = new javax.swing.JLabel();
        jlblrut = new javax.swing.JLabel();
        jlblmsjcliente = new javax.swing.JLabel();
        jbtnbuscarcliente = new javax.swing.JButton();
        jbtnimprimirtodo = new javax.swing.JButton();
        jbtnimprimirporventa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtablacliente = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jbtnbuscarextornadas = new javax.swing.JButton();
        jdpextornadodesde = new org.jdesktop.swingx.JXDatePicker();
        jdpextornadohasta = new org.jdesktop.swingx.JXDatePicker();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlblmensajeextorno = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtablaanulada = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("DESDE:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("HASTA:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, 20));
        jPanel2.add(jdpdesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 133, -1));
        jPanel2.add(jdphasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 126, -1));

        jbtnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, -1, -1));

        jlblmensaje.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 102));
        jPanel2.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, 790, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("CONCRETADAS");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("LA BUSQUEDA  SE  REALIZA ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("DE ACUERDO A LAS VENTAS ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel12.setText("INGRESE FECHA:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        jScrollPane1.setViewportView(jtablaempleado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("POR EMPLEADO", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setText("CLIENTE:");

        jlblcliente.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblcliente.setText("* * *");

        jlblrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblrut.setText("* * *");

        jlblmsjcliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmsjcliente.setForeground(new java.awt.Color(255, 51, 51));

        jbtnbuscarcliente.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarcliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnbuscarcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarclienteActionPerformed(evt);
            }
        });

        jbtnimprimirtodo.setBackground(new java.awt.Color(255, 255, 255));
        jbtnimprimirtodo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnimprimirtodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnimprimirtodo.setText("TODO");
        jbtnimprimirtodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirtodoActionPerformed(evt);
            }
        });

        jbtnimprimirporventa.setBackground(new java.awt.Color(255, 255, 255));
        jbtnimprimirporventa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnimprimirporventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jbtnimprimirporventa.setText("POR VENTA");
        jbtnimprimirporventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimprimirporventaActionPerformed(evt);
            }
        });

        jtablacliente.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtablacliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtablacliente.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablacliente.getTableHeader().setReorderingAllowed(false);
        jtablacliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaclienteMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtablacliente);

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("DOBLE DOBLIC PARA ANULAR VENTA.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblmsjcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7)
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblrut, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(10, 10, 10)
                                .addComponent(jbtnbuscarcliente)
                                .addGap(30, 30, 30)
                                .addComponent(jbtnimprimirtodo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnimprimirporventa)))
                        .addGap(0, 261, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblrut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jlblmsjcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnbuscarcliente)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnimprimirporventa)
                        .addComponent(jbtnimprimirtodo)))
                .addGap(3, 3, 3)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        jTabbedPane1.addTab("POR CLIENTE", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jbtnbuscarextornadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Searchx32.png"))); // NOI18N
        jbtnbuscarextornadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarextornadasActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel9.setText("DESDE:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setText("HASTA:");

        jlblmensajeextorno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmensajeextorno.setForeground(new java.awt.Color(255, 51, 51));

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel13.setText("INGRESE FECHA:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdpextornadodesde, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdpextornadohasta, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlblmensajeextorno, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(jbtnbuscarextornadas)
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jdpextornadodesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdpextornadohasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jbtnbuscarextornadas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblmensajeextorno, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jtablaanulada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtablaanulada.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaanulada.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtablaanulada);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ANULADAS", jPanel5);

        jPanel7.setBackground(new java.awt.Color(238, 238, 238));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("CONSULTAR VENTAS CONCRETADAS");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarActionPerformed
        // TODO add your handling code here:
        if(jdpdesde.getDate()!=null && jdphasta.getDate()!=null){
            jlblmensaje.setText("");
            listventemp=daoventa.mostrar(jtablaempleado,new java.sql.Timestamp(jdpdesde.getDate().getTime()), 
                    new java.sql.Timestamp(jdphasta.getDate().getTime()), jlblmensaje);
            
        }else {
            jlblmensaje.setText("INGRESE FECHA");
        }
    }//GEN-LAST:event_jbtnbuscarActionPerformed

    private void jtablaempleadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaempleadoMouseReleased
        // TODO add your handling code here:
        int index = jtablaempleado.getSelectedRow();
        if(index>=0){
            
        if(evt.getClickCount()==2){
           venta=listventemp.get(index);
            JDVentasEmpleado ventasempleado= new JDVentasEmpleado(new java.awt.Frame(), isVisible(),venta,new java.sql.Timestamp(jdpdesde.getDate().getTime()),new java.sql.Timestamp(
            jdphasta.getDate().getTime()));
            ventasempleado.setVisible(true);
        }
        
        }
        
    }//GEN-LAST:event_jtablaempleadoMouseReleased

    private void jbtnbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarclienteActionPerformed
        // TODO add your handling code here:
        JDBuscarCliente buscarcliente = new JDBuscarCliente(new Frame(),isVisible(),this);
        buscarcliente.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarclienteActionPerformed

    private void jbtnimprimirtodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirtodoActionPerformed
        // TODO add your handling code here:
//        daoreparacion.imprimirreparaciontodo(cliente.getId_cliente());
        if(cliente.getId_cliente()!=0){
            daoventa.imprimirtodocliente(cliente.getId_cliente());
        
        }
        
    }//GEN-LAST:event_jbtnimprimirtodoActionPerformed

    private void jtablaclienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaclienteMouseReleased
        // TODO add your handling code here:
        int index= jtablacliente.getSelectedRow();
        
        if(index>=0){
            venta= listventcli.get(index);
          
            if(evt.getClickCount()==2){
            
             JDMotivoExtorno motextorno =new JDMotivoExtorno(new JFrame(),isVisible(),venta,this);
             motextorno.setVisible(true);
            }
            jbtnimprimirporventa.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_jtablaclienteMouseReleased

    private void jbtnimprimirporventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimprimirporventaActionPerformed
        // TODO add your handling code here:
        int index= jtablacliente.getSelectedRow();
        
        if(index>=0){
           venta= listventcli.get(index);
            daoventa.imprimirunacliente(cliente.getId_cliente(), venta.getIdventa());
        }
    }//GEN-LAST:event_jbtnimprimirporventaActionPerformed

    private void jbtnbuscarextornadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarextornadasActionPerformed
        // TODO add your handling code here:
        if(jdpextornadodesde.getDate()!=null && jdpextornadohasta.getDate()!=null){
            
            listventanula=daoventa.mostrarconcretadasanuladas(jtablaanulada,new java.sql.Date( jdpextornadodesde.getDate().getTime())
                    ,new java.sql.Date(jdpextornadohasta.getDate().getTime()), jlblmensajeextorno);
        }else {
            jlblmensajeextorno.setText("INGRESE FECHA");
        }
    }//GEN-LAST:event_jbtnbuscarextornadasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnbuscar;
    private javax.swing.JButton jbtnbuscarcliente;
    private javax.swing.JButton jbtnbuscarextornadas;
    private javax.swing.JButton jbtnimprimirporventa;
    private javax.swing.JButton jbtnimprimirtodo;
    private org.jdesktop.swingx.JXDatePicker jdpdesde;
    private org.jdesktop.swingx.JXDatePicker jdpextornadodesde;
    private org.jdesktop.swingx.JXDatePicker jdpextornadohasta;
    private org.jdesktop.swingx.JXDatePicker jdphasta;
    private javax.swing.JLabel jlblcliente;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmensajeextorno;
    private javax.swing.JLabel jlblmsjcliente;
    private javax.swing.JLabel jlblrut;
    private javax.swing.JTable jtablaanulada;
    private javax.swing.JTable jtablacliente;
    private javax.swing.JTable jtablaempleado;
    // End of variables declaration//GEN-END:variables
}
