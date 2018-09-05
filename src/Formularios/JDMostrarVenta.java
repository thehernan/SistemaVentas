/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.VentasDAO;
import Pojos.Producto;
import Pojos.Ventas;
import java.awt.Graphics;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author HERNAN
 */
public class JDMostrarVenta extends javax.swing.JDialog {

    /**
     * Creates new form JDMostrarVenta
     */
    VentasDAO daoventa = new VentasDAO();
    Ventas ventaB;
    FormatoNumerico fn = new FormatoNumerico();
    List<Producto> listprod=new ArrayList<>();
    public JDMostrarVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public JDMostrarVenta(java.awt.Frame parent, boolean modal,long idventa) {
        super(parent, modal);
        initComponents();
       
        ventaB=daoventa.buscarventa(listprod, "v"+idventa,"todo");
        jdpfecha.setDate(ventaB.getFecha());
        jlbldescuento.setText("Descuento: "+fn.FormatoN(ventaB.getDescuento()));
        jlbltotal.setText("Total: "+fn.FormatoN(ventaB.getTotal()));
        jlblcliente.setText("Señor(es): "+ventaB.getClienteRS());
        jlblrut.setText("Documento: "+ventaB.getClientenumdoc());
        jlbldireccion.setText("Dirección: "+ventaB.getClientedirec());
        jlbltitulo.setText("Comprobante: "+ventaB.getSerie()+" - "+ventaB.getNumero());
        
        ////////////////////////////////////
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(600);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        //////////////////////////////
        cargartabla();
//        mostrarQR();
        this.setLocationRelativeTo(null);
        addEscapeListener(this);
    }
        public static void addEscapeListener(final JDialog dialog) {
    ActionListener escListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.setVisible(false);
        }
    };

    dialog.getRootPane().registerKeyboardAction(escListener,
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);

}
    public void cargartabla()
    {
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        Object datos[] = new Object[5];
        for(Producto p : listprod)
        {
            datos[0]=p.getCodigo();
            datos[1]=p.getDescripcion();
            datos[2]=fn.FormatoN(p.getCantidad());
            datos[3]=fn.FormatoN(p.getPrecio());
            datos[4]=fn.FormatoN(p.getTotal());
            model.addRow(datos);
        }
       
        
    
    
    }
    
    
//    public void mostrarQR()
//    {
//        FileOutputStream fout;
//        ByteArrayOutputStream out;
//        
//        //VERIFICAR QUE TENGA LETRAS ESCRITAS
//        System.out.println("QR "+ventaB.getQr());
//        if(ventaB.getQr().length()==0)
//            return;
//        
//        //OBTENER EL TEXTO ESCRITO
//        String t = ventaB.getQr();
//        //RECIBIR STREAM DEL QR GENERADO
//        //POR LA LIBRERIA QRCode
//        out = QRCode.from(t).withSize(250, 250).to(ImageType.PNG).stream();
//        try
//        {
//            //CREAR EL ARCHIVO
//            fout = new FileOutputStream(new File("temp.png"));
//            //ESCRIBIR EL CONTENIDO DEL ARCHIVO
//            fout.write(out.toByteArray());
//            //LIBERAR MEMORIA
//            fout.flush();
//            fout.close();
//            
//            //MOSTRAR LA IMAGEN GENERADA
//            //LEER LA IMAGEN
//            BufferedImage miQr = ImageIO.read(new File("temp.png"));
//            //DIBUJARLA EN UN CONTROL
//            JLabel label = new JLabel(new ImageIcon(miQr));
//            //OBTENER EL LIENZO DEL JPANEL
//            //PARA PODER DIBUJAR LA IMAGEN
//            //SOBRE ÉL
//            Graphics g = jpanelQR.getGraphics();
//            //DIBUJAR LA IMAGEN
//            g.drawImage(miQr, WIDTH, WIDTH, label);
//        }
//        catch(Exception ex)
//        {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    
//    
//    
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jlbltitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jlblcliente = new javax.swing.JLabel();
        jlblrut = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jdpfecha = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jlbldescuento = new javax.swing.JLabel();
        jlbldireccion = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jlbltitulo.setBackground(new java.awt.Color(0, 0, 0));
        jlbltitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlbltitulo.setText("DETALLE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbltitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbltitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "Precio", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jlblcliente.setText("jLabel1");

        jlblrut.setText("jLabel1");

        jlbltotal.setBackground(new java.awt.Color(255, 51, 51));
        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbltotal.setText("* * *");

        jdpfecha.setEnabled(false);

        jLabel1.setText("Fecha de OP.:");

        jlbldescuento.setBackground(new java.awt.Color(255, 51, 51));
        jlbldescuento.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlbldescuento.setText("* * *");

        jlbldireccion.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jlblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jdpfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlbldireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlblrut, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlbldescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jlblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addComponent(jdpfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jlbldireccion)
                .addGap(15, 15, 15)
                .addComponent(jlblrut)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbltotal)
                    .addComponent(jlbldescuento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDMostrarVenta dialog = new JDMostrarVenta(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jdpfecha;
    private javax.swing.JLabel jlblcliente;
    private javax.swing.JLabel jlbldescuento;
    private javax.swing.JLabel jlbldireccion;
    private javax.swing.JLabel jlblrut;
    private javax.swing.JLabel jlbltitulo;
    private javax.swing.JLabel jlbltotal;
    // End of variables declaration//GEN-END:variables
}
