/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.DetalleCajaDAO;
import DAO.ReparacionDAO;
import DAO.VentasDAO;
import Pojos.Caja;
import Pojos.Producto;
import Pojos.Reparacion;
import Pojos.Ventas;
import Pojos.GuiaTipo;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author info2017
 */
public class JIFCaja extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFCaja
     */
//    List<String> codigo= new ArrayList<>();
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
    List<Producto> listprod = new ArrayList<>();
    List<GuiaTipo> listguia = new ArrayList();
    FormatoNumerico fn= new FormatoNumerico();
    MDIMenu menu;
    public JIFCaja() {
        initComponents();
    }
    public JIFCaja(Caja caja,MDIMenu menu) {
        initComponents();
        System.out.println("idcaja"+caja.getId_caja());
        this.caja=caja;
        this.menu=menu;
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(450);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
    }
//    public void imprimircant(){       
//       
//    if(codfoco==true){
//         String s="";
//    for (String lista : codigo){
//         s= s + lista;
//        
//    }
//      jtfcodigo.setText(s);
//    }
//    if(numfoco==true){
//         String s1="";
//    for (String lista : num){
//         s1= s1+ lista;
//        
//    }
////     jtfnumero.setText(s1);
//    }
//     
//   
//   
//    
//    }
    public void enter(){
     try {
            String cod= (jtfcodigo.getText());
            if(!cod.equals("") && !cod.equals("CODIGO")){
                System.out.println("codsub"+cod.substring(0, 1));
//                if(cod.substring(0, 1).equalsIgnoreCase("V")){
                    
                    
                venta = daoventa.buscarventa(listprod,listguia, cod,"debe");
                if(venta.getIdventa()!=0){
                    jlblcliente.setText(venta.getClienteRS());
                    jlbldireccion.setText(venta.getClientedirec());
                    jlbldocumento.setText(venta.getClientenumdoc());
                    cargartabla();
                    jlbltotal.setText(fn.FormatoN(venta.getTotal()));
                    jlbligv.setText(fn.FormatoN(venta.getTotaligv()));
                    jlblsubtotal.setText(fn.FormatoN(venta.getSubtotal()));
                    jlbldescuento.setText(fn.FormatoN(venta.getDescuento()));
                    jlbltotalencabezado.setText(venta.getAbreviaturamoneda()+" "+fn.FormatoN(venta.getTotal()));
                
                }
               
//                calculatotal();
                
//                }
                
//                else {if(cod.substring(0, 1).equalsIgnoreCase("R")){
//                repara= daoreparacion.buscarcaja(jtabla, cod, jlblcliente, jlbldocumento);
//                jlbltotal.setText(fn.FormatoN(repara.getTotal()));
//                jlbligv.setText(fn.FormatoN(repara.getIgv()));
//                jlblsubtotal.setText(fn.FormatoN(repara.getSubtotal()));
//                jlbldescuento.setText(fn.FormatoN(repara.getDescuento()));
//                jlblanticipo.setText(fn.FormatoN(repara.getAbono()));
//                jlbltotalencabezado.setText("S/. "+fn.FormatoN(venta.getTotal()));
////                calculatotal();
//                }
//                else {
//                
//                    newcobro();
//                }
      
                }
             
            
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un condigo valido","",JOptionPane.INFORMATION_MESSAGE);
        }

    
    }
   public void cargartabla()
    {
        DefaultTableModel model = (DefaultTableModel)jtabla.getModel();
        Object datos[] = new Object[5];
        for(Producto p : listprod)
        {
            datos[0]=p.getCodigo();
            datos[1]=p.getDescripcion();
            datos[2]=p.getCantidad();
            datos[3]=fn.FormatoN(p.getPrecio());
            datos[4]=fn.FormatoN(p.getTotal());
            model.addRow(datos);
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

//    public void cobrar(DetalleCaja  detcaja, double abono){
//        String tipo="";
////        double aabono=abono;
////        System.out.println("reparacobrar"+repara.getIdreparacion());
////            detcaja.setIdreparacion(repara.getIdreparacion());
//            detcaja.setIdventa(venta.getIdventa());
////            detcaja.setDocumento(jcbcomprobante.getSelectedItem().toString());
////            detcaja.setNumero(jtfnumero.getText());
//            detcaja.setIdcaja(caja.getId_caja());
////            if(jtfcodigo.getText().substring(0,1).equalsIgnoreCase("R")){
////            tipo = "REPARACION";
////            
////            }else { if(jtfcodigo.getText().substring(0,1).equalsIgnoreCase("V")){
////            tipo = "VENTA";
////            aabono=Double.parseDouble(jlbltotal.getText());
////            }
////            
////            }
//            detcaja.setAbono(abono);
//            daodetcaja.insertar(detcaja,venta,listprod);
//           
//           
////            calculatotal();
////            jtfnumero.setText("");
//            /////////////////////
//    
//    }
    
    
    
    public void newcobro(){
        //NUEVO OBJETO VENTA
        venta=new Ventas();
        repara = new Reparacion();
        listprod = new ArrayList<>();
        //NUEVO MODELO EN TABLA
        DefaultTableModel modelo= (DefaultTableModel) jtabla.getModel();
         for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        //LIMPIAR JLBL
        jlbltotalencabezado.setText("* * *");
        jlblcliente.setText("* * *");
        jlbldocumento.setText("* * *");
        jlbldireccion.setText("* * *");
        jlbltotal.setText("* * *");
        jlbligv.setText("* * *");
        jlblsubtotal.setText("* * *");
        jlblanticipo.setText("* * *");
//        jtfnumero.setText("");
        jtfcodigo.setText("COD. VENTA");
//        jcbcomprobante.setSelectedIndex(0);
        jlbldescuento.setText("* * *");
        menu.cargarresumen();
    }
    public void setventa(Ventas venta){
        newcobro();
        jtfcodigo.setText("v"+venta.getIdventa());
        enter();
    
    }
    
    
//    public void generadocumentosunat()
//    {
//        ConsumingPost post=new ConsumingPost(venta,listprod);
//        post.apiConsume();
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
        jtfcodigo = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbtnventas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jbtncorte = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbtnegreso = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jbtncobrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jlblcliente = new javax.swing.JLabel();
        jlbldocumento = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jlblsubtotal = new javax.swing.JLabel();
        jlblanticipo = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jlbldescuento = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jlbligv = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jlbltotalencabezado = new javax.swing.JLabel();
        jlbldireccion = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(1216, 557));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtfcodigo.setBackground(new java.awt.Color(255, 255, 255));
        jtfcodigo.setForeground(new java.awt.Color(34, 75, 139));
        jtfcodigo.setText("CODIGO");
        jtfcodigo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
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

        jPanel7.setBackground(new java.awt.Color(34, 75, 139));
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
        jLabel11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("CAJA");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar.png"))); // NOI18N
        jLabel10.setToolTipText("Cerrar");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel10MouseReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/money40x4.png"))); // NOI18N

        jbtnventas.setBackground(new java.awt.Color(77, 161, 227));
        jbtnventas.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnventas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/compra.png"))); // NOI18N
        jbtnventas.setMnemonic('b');
        jbtnventas.setText("Venta en cola");
        jbtnventas.setToolTipText("alt + b");
        jbtnventas.setBorder(null);
        jbtnventas.setBorderPainted(false);
        jbtnventas.setContentAreaFilled(false);
        jbtnventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnventasActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Alt + b");

        jbtncorte.setBackground(new java.awt.Color(77, 161, 227));
        jbtncorte.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtncorte.setForeground(new java.awt.Color(255, 255, 255));
        jbtncorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cut20x20.png"))); // NOI18N
        jbtncorte.setMnemonic('x');
        jbtncorte.setText("Corte");
        jbtncorte.setToolTipText("alt + x");
        jbtncorte.setBorderPainted(false);
        jbtncorte.setContentAreaFilled(false);
        jbtncorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncorteActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Alt + x");

        jbtnegreso.setBackground(new java.awt.Color(77, 161, 227));
        jbtnegreso.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtnegreso.setForeground(new java.awt.Color(255, 255, 255));
        jbtnegreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exitmoney20x20.png"))); // NOI18N
        jbtnegreso.setMnemonic('e');
        jbtnegreso.setText("Egreso");
        jbtnegreso.setToolTipText("alt + e");
        jbtnegreso.setBorderPainted(false);
        jbtnegreso.setContentAreaFilled(false);
        jbtnegreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnegresoActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Alt + e");

        jbtncobrar.setBackground(new java.awt.Color(77, 161, 227));
        jbtncobrar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jbtncobrar.setForeground(new java.awt.Color(255, 255, 255));
        jbtncobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cobrar20X20.png"))); // NOI18N
        jbtncobrar.setMnemonic('c');
        jbtncobrar.setText("Cobrar");
        jbtncobrar.setToolTipText("alt + c");
        jbtncobrar.setBorderPainted(false);
        jbtncobrar.setContentAreaFilled(false);
        jbtncobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncobrarActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Alt + c");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(jbtnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jbtncorte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnegreso, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtncobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel10)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(124, 124, 124)
                        .addComponent(jLabel4)
                        .addGap(122, 122, 122)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtncorte, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnegreso)
                            .addComponent(jbtncobrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlblcliente.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlblcliente.setText("* * *");

        jlbldocumento.setText("* * *");

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(jtabla);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Subtotal Ventas:");

        jLabel13.setText("Anticipo:");

        jLabel14.setText("Descuento:");

        jLabel15.setText("Valor de Venta:");

        jLabel16.setText("ISC:");

        jLabel17.setText("IGV 18%:");

        jLabel18.setText("Otros Cargos:");

        jLabel19.setText("Otros Tributos:");

        jLabel20.setText("Importe Total:");

        jlblsubtotal.setText("* * *");

        jlblanticipo.setText("* * *");

        jLabel23.setText("* * *");

        jlbldescuento.setText("* * *");

        jLabel25.setText("* * *");

        jlbligv.setText("* * *");

        jLabel27.setText("* * *");

        jLabel28.setText("* * *");

        jlbltotal.setText("* * *");

        jlbltotalencabezado.setBackground(new java.awt.Color(34, 75, 139));
        jlbltotalencabezado.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jlbltotalencabezado.setForeground(new java.awt.Color(255, 255, 255));
        jlbltotalencabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sale250x50.png"))); // NOI18N
        jlbltotalencabezado.setText("* * *");
        jlbltotalencabezado.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblanticipo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jlbldescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbligv, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jlbltotalencabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jlbltotalencabezado)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jlblsubtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jlblanticipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jlbldescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jlbligv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jlbltotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlbldireccion.setText("* * *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbldocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbldireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtfcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 692, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jlblcliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbldireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbldocumento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
       
        if(venta.getIdventa()!=0 ){
//          double total= Double.parseDouble(jlbltotal.getText());
//          String cod= (jtfcodigo.getText());
//                  if(venta.getIdventa()!=0){
//                      tipopago="VENTA";
//                }else{
//                      tipopago="REPARACION";
//                }
                JDCobrarCaja cobrarcaja= new JDCobrarCaja(new java.awt.Frame(),isVisible(),venta,listprod,listguia,caja.getId_caja(),this);
                cobrarcaja.setVisible(true);
                  
         
            
        }else {
            JOptionPane.showMessageDialog(null, "Ingrese código e intente nuevamente");
            jtfcodigo.requestFocus();
            
        
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtncobrar;
    private javax.swing.JButton jbtncorte;
    private javax.swing.JButton jbtnegreso;
    private javax.swing.JButton jbtnventas;
    private javax.swing.JLabel jlblanticipo;
    private javax.swing.JLabel jlblcliente;
    private javax.swing.JLabel jlbldescuento;
    private javax.swing.JLabel jlbldireccion;
    private javax.swing.JLabel jlbldocumento;
    private javax.swing.JLabel jlbligv;
    private javax.swing.JLabel jlblsubtotal;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JLabel jlbltotalencabezado;
    private javax.swing.JTable jtabla;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfcodigo;
    // End of variables declaration//GEN-END:variables
}
