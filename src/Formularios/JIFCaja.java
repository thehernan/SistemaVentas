/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.DetalleCajaDAO;
import DAO.ReparacionDAO;
import DAO.VentasDAO;
import Pojos.Caja;
import Pojos.DetalleCaja;
import Pojos.Reparacion;
import Pojos.Ventas;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class JIFCaja extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFCaja
     */
    List<String> codigo= new ArrayList<>();
    List<String> num= new ArrayList<>();
    boolean codfoco = false;
    boolean numfoco= false;
    VentasDAO daoventa= new VentasDAO();
    Ventas venta=  new Ventas();
    Caja caja= new Caja();
    DetalleCajaDAO daodetcaja= new DetalleCajaDAO();
   // DetalleCaja detcaja= new DetalleCaja();
    Reparacion repara= new Reparacion();
    ReparacionDAO daoreparacion= new ReparacionDAO();
    int posx;
    int posy;
    public JIFCaja() {
        initComponents();
    }
    public JIFCaja(Caja caja) {
        initComponents();
        System.out.println("idcaja"+caja.getId_caja());
        this.caja=caja;
    }
    public void imprimircant(){       
       
    if(codfoco==true){
         String s="";
    for (String lista : codigo){
         s= s + lista;
        
    }
      jtfcodigo.setText(s);
    }
    if(numfoco==true){
         String s1="";
    for (String lista : num){
         s1= s1+ lista;
        
    }
//     jtfnumero.setText(s1);
    }
     
   
   
    
    }
    public void enter(){
     try {
            String cod= (jtfcodigo.getText());
            if(!cod.equals("") && !cod.equals("CODIGO")){
                System.out.println("codsub"+cod.substring(0, 1));
                if(cod.substring(0, 1).equalsIgnoreCase("V")){
                venta = daoventa.buscarventa(jtabla, cod,jlblcliente,jlblrut,"debe");
                jlbltotal.setValue(venta.getTotal());
                jlbliva.setValue(venta.getIva());
                jlblsubtotal.setValue(venta.getSuvtotal());
                jtfdescuento.setValue(venta.getDescuento());
//                calculatotal();
                
                }else {if(cod.substring(0, 1).equalsIgnoreCase("R")){
                repara= daoreparacion.buscarcaja(jtabla, cod, jlblcliente, jlblrut,jlbltotal,
                        jlblsubtotal,jlbliva,jlblabono,jtfdescuento);
//                calculatotal();
                }else {
                
                    newcobro();
                }
                
                
                
                }
                
                
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un condigo valido","",JOptionPane.INFORMATION_MESSAGE);
        }
        
    
    
    
    }
//    public double calculatotal(){
//        Double importe=0.0,subtotal,iva,total=0.0;
//        double descuento =venta.getDescuento();
//        for (int i=0;i<=jtabla.getRowCount()-1 ;i++){
//         importe = importe + Double.parseDouble(jtabla.getValueAt(i, 4).toString());
//        }
//        total = importe-descuento;
//        subtotal= total/1.19;
//        iva = total-subtotal;
//       
//        System.out.println("total"+total);
////        BigDecimal totalB = new BigDecimal(total);
////        BigDecimal ivaB = new BigDecimal(iva);
////        BigDecimal subtotalB = new BigDecimal(subtotal);
////        
//        jlbltotal.setValue(total);
//        jlblsubtotal.setValue(subtotal);
//        jlbliva.setValue(iva);
//        jtfdescuento.setValue(descuento);
////        return totalB.setScale(2,BigDecimal.ROUND_HALF_EVEN);
//        return total;
//    }

    public void cobrar(DetalleCaja  detcaja,String estado , double abono,Reparacion repara){
        String tipo="";
        double aabono=abono;
        System.out.println("reparacobrar"+repara.getIdreparacion());
            detcaja.setIdreparacion(repara.getIdreparacion());
            detcaja.setIdventa(venta.getIdventa());
//            detcaja.setDocumento(jcbcomprobante.getSelectedItem().toString());
//            detcaja.setNumero(jtfnumero.getText());
            detcaja.setIdcaja(caja.getId_caja());
            if(jtfcodigo.getText().substring(0,1).equalsIgnoreCase("R")){
            tipo = "REPARACION";
            
            }else { if(jtfcodigo.getText().substring(0,1).equalsIgnoreCase("V")){
            tipo = "VENTA";
            aabono=Double.parseDouble(jlbltotal.getValue().toString());
            }
            
            }
            detcaja.setAbono(aabono);
            daodetcaja.insertar(detcaja,tipo,estado);
           
           
//            calculatotal();
//            jtfnumero.setText("");
            /////////////////////
    
    }
    public void newcobro(){
        //NUEVO OBJETO VENTA
        venta=new Ventas();
        repara = new Reparacion();
        codigo = new ArrayList<>();
        //NUEVO MODELO EN TABLA
        DefaultTableModel modelo= (DefaultTableModel) jtabla.getModel();
         for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        //LIMPIAR JLBL
        jlblcliente.setText("* * *");
        jlblrut.setText("* * *");
        jlbltotal.setValue(0);
        jlbliva.setValue(0);
        jlblsubtotal.setValue(0);
        jlblabono.setValue(0);
//        jtfnumero.setText("");
        jtfcodigo.setText("COD. VENTA");
//        jcbcomprobante.setSelectedIndex(0);
        jtfdescuento.setValue(0);
        
    }
    public void setventa(Ventas venta){
        jtfcodigo.setText(venta.getCodigo());
        enter();
    
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
        jtfcodigo = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlblcliente = new javax.swing.JLabel();
        jlblrut = new javax.swing.JLabel();
        jlblsubtotal = new javax.swing.JFormattedTextField();
        jlbliva = new javax.swing.JFormattedTextField();
        jlbltotal = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jlblabono = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jtfdescuento = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jbtnventas = new javax.swing.JButton();
        jbtncorte = new javax.swing.JButton();
        jbtncobrar = new javax.swing.JButton();
        jbtnegreso = new javax.swing.JButton();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtfcodigo.setBackground(new java.awt.Color(204, 204, 204));
        jtfcodigo.setForeground(new java.awt.Color(255, 255, 255));
        jtfcodigo.setText("CODIGO");
        jtfcodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfcodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusLost(evt);
            }
        });
        jtfcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfcodigoActionPerformed(evt);
            }
        });
        jtfcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyReleased(evt);
            }
        });

        panelNice1.setBackground(new java.awt.Color(238, 238, 238));
        panelNice1.setBorderColor(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("CLIENTE:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Doc.:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel5.setText("SUBTOTAL :");

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel6.setText("IGV 18%:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel7.setText("TOTAL PAGAR:");

        jlblcliente.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblcliente.setForeground(new java.awt.Color(0, 0, 0));
        jlblcliente.setText("* * *");

        jlblrut.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblrut.setForeground(new java.awt.Color(0, 0, 0));
        jlblrut.setText("* * *");

        jlblsubtotal.setEditable(false);
        jlblsubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlblsubtotal.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jlblsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlblsubtotalActionPerformed(evt);
            }
        });

        jlbliva.setEditable(false);
        jlbliva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlbliva.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jlbltotal.setEditable(false);
        jlbltotal.setForeground(new java.awt.Color(255, 51, 51));
        jlbltotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlbltotal.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel8.setText("ABONO:");

        jlblabono.setEditable(false);
        jlblabono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jlblabono.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel9.setText("DESCUENTO:");

        jtfdescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jtfdescuento.setEnabled(false);
        jtfdescuento.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N

        jtabla.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
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
        jScrollPane2.setViewportView(jtabla);

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelNice1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7))
                            .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelNice1Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(jlblabono, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbliva, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelNice1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jlbltotal))))
                        .addGroup(panelNice1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jLabel9)
                            .addGap(15, 15, 15)
                            .addComponent(jtfdescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jLabel5)
                            .addGap(9, 9, 9)
                            .addComponent(jlblsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlblrut, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                                    .addComponent(jlblcliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(267, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlblcliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlblrut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jtfdescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jlblsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jlblabono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlbliva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(238, 238, 238));
        jPanel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel7MouseDragged(evt);
            }
        });
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel7MousePressed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setText("CAJA");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel10.setToolTipText("Cerrar");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel10MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtnventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compra.png"))); // NOI18N
        jbtnventas.setText("Venta en cola");
        jbtnventas.setToolTipText("En cola");
        jbtnventas.setBorder(null);
        jbtnventas.setBorderPainted(false);
        jbtnventas.setContentAreaFilled(false);
        jbtnventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnventasActionPerformed(evt);
            }
        });

        jbtncorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cut20x20.png"))); // NOI18N
        jbtncorte.setText("Corte");
        jbtncorte.setToolTipText("Corte de Caja");
        jbtncorte.setBorderPainted(false);
        jbtncorte.setContentAreaFilled(false);
        jbtncorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncorteActionPerformed(evt);
            }
        });

        jbtncobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cobrar20X20.png"))); // NOI18N
        jbtncobrar.setText("Cobrar");
        jbtncobrar.setBorderPainted(false);
        jbtncobrar.setContentAreaFilled(false);
        jbtncobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncobrarActionPerformed(evt);
            }
        });

        jbtnegreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exitmoney20x20.png"))); // NOI18N
        jbtnegreso.setText("Egreso");
        jbtnegreso.setBorderPainted(false);
        jbtnegreso.setContentAreaFilled(false);
        jbtnegreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnegresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtfcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jbtncorte, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnegreso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtncobrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtncorte)
                        .addComponent(jbtnegreso)
                        .addComponent(jbtncobrar)
                        .addComponent(jbtnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelNice1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
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

    private void jtfcodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusGained
        // TODO add your handling code here:
        if(jtfcodigo.getText().equals("CODIGO")){
            jtfcodigo.setText("");
        }
        codfoco= true;
        numfoco=false;
        System.out.println("true");
    }//GEN-LAST:event_jtfcodigoFocusGained

    private void jtfcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusLost
        // TODO add your handling code here:
        if(jtfcodigo.getText().equals("")){
            jtfcodigo.setText("CODIGO");
        }
       
    }//GEN-LAST:event_jtfcodigoFocusLost

    private void jtfcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcodigoActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfcodigoActionPerformed

    private void jlblsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlblsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlblsubtotalActionPerformed

    private void jtfcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==113){
           jbtncobrar.doClick();
        
        }
    }//GEN-LAST:event_jtfcodigoKeyReleased

    private void jbtnventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnventasActionPerformed
        // TODO add your handling code here:
        JDVentaencola ventacola = new JDVentaencola(new Frame(), isVisible(),this);
        ventacola.setVisible(true);
        
    }//GEN-LAST:event_jbtnventasActionPerformed

    private void jPanel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MousePressed
        // TODO add your handling code here:
         posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel7MousePressed

    private void jPanel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseDragged
        // TODO add your handling code here:
         int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel7MouseDragged

    private void jLabel10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseReleased

    private void jtfcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            enter();
        
        }
    }//GEN-LAST:event_jtfcodigoKeyPressed

    private void jbtncorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncorteActionPerformed
       // TODO add your handling code here:
        JDCerrarCaja Ccaja= new JDCerrarCaja(new java.awt.Frame(),isVisible(),caja,this);
      Ccaja.setVisible(true);
    }//GEN-LAST:event_jbtncorteActionPerformed

    private void jbtncobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncobrarActionPerformed
        // TODO add your handling code here:
         System.out.println("idrepara"+repara.getIdreparacion());
        System.out.println("idventa"+venta.getIdventa());
        String tipopago="";
       
        if(repara.getIdreparacion()!=0 || venta.getIdventa()!=0 ){
          double total= Double.parseDouble(jlbltotal.getValue().toString());
                String cod= (jtfcodigo.getText());
                  if(venta.getIdventa()!=0){
                      tipopago="VENTA";
                }else{
                      tipopago="REPARACION";
                }
                JDCobrarCaja cobrarcaja= new JDCobrarCaja(new java.awt.Frame(),isVisible(),this,repara,total);
                cobrarcaja.setVisible(true);
                  
         
            
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE LOS DATOS NECESARIOS PARA REALIZAR EL COBRO E INTENTE"
                    + " NUEVAMENTE");
        
        }
        
    }//GEN-LAST:event_jbtncobrarActionPerformed

    private void jbtnegresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnegresoActionPerformed
        // TODO add your handling code here:
          JDEgresoCaja egreso = new JDEgresoCaja(new Frame(), isVisible(),caja.getId_caja());
        egreso.setVisible(true);
    }//GEN-LAST:event_jbtnegresoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtncobrar;
    private javax.swing.JButton jbtncorte;
    private javax.swing.JButton jbtnegreso;
    private javax.swing.JButton jbtnventas;
    private javax.swing.JFormattedTextField jlblabono;
    private javax.swing.JLabel jlblcliente;
    private javax.swing.JFormattedTextField jlbliva;
    private javax.swing.JLabel jlblrut;
    private javax.swing.JFormattedTextField jlblsubtotal;
    private javax.swing.JFormattedTextField jlbltotal;
    private javax.swing.JTable jtabla;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfcodigo;
    private javax.swing.JFormattedTextField jtfdescuento;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    // End of variables declaration//GEN-END:variables
}
