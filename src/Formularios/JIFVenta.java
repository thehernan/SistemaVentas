/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.Mayusculas;
import DAO.ClienteDAO;
import DAO.DetalleVentaDAO;
import DAO.ProductoDAO;
import DAO.VentasDAO;
import Pojos.Cliente;
import Pojos.DetalleVenta;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import Pojos.UsuarioSingleton;
import Pojos.Ventas;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author info2017
 */
public class JIFVenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFVenta
     */
    List<String> cantidad= new ArrayList<>();
    ProductoDAO daoproducto=new  ProductoDAO();
    Producto producto= new Producto();
    ClienteDAO daocliente = new ClienteDAO();
    Cliente cliente = new Cliente();
    byte[]  fotoB;
    Ventas venta= new Ventas();
    VentasDAO daoventa= new VentasDAO();
    DetalleVentaDAO daodetventa = new DetalleVentaDAO();
//    Empleado empleadologin;
//    Usuarios user;
    UsuarioSingleton user= UsuarioSingleton.getintancia();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    DefaultTableModel modelo= new DefaultTableModel(){
    public boolean isCellEditable(int row, int column) {
    //      if (column == 5) return true;
    //else
    return false;
}
  }; 
    long idcliente;
    Mayusculas mayus = new Mayusculas();
    List<Producto> listprod= new ArrayList<>();
    public JIFVenta() {
        initComponents();
        String titulos[]={"CODIGO","DESCRIPCION","CANTIDAD","PRECIO","IMPORTE"};
        modelo.setColumnIdentifiers(titulos);
        jtabla.setModel(modelo);
       jtfcantidad.grabFocus();
//        this.user= user;
       jtfdescuento.setValue(0);
       jtadescripprod.setLineWrap(true);
        jlblcargarventa.setVisible(false);
       
       ////// IMAGEN DEL PRODUCTO POR DEFAULT////////////////
//       ImageIcon imageIcon= new ImageIcon( getClass().getResource("/imagenes/product.png"));
//       ImageIcon imageUser = imageIcon;
//       Image img = imageUser.getImage();
//       Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
//       imageUser = new ImageIcon(newimg);
//       jlblimagen.setIcon(imageUser);
       
       //////////////////////////////////////////////////////

    }
    public void imprimircant(){
        String s="";
    for (String lista : cantidad){
         s= s + lista;
        
    }
    jtfcantidad.setText(s);
    }
    public void calculatotal(){
        Double total=0.0,importe=0.0;
        double descuento= Double.parseDouble(jtfdescuento.getValue().toString());
      for(Producto prod : listprod){
          importe = importe + (prod.getCantidad()*prod.getPrecio());
      
      }
      total = importe - descuento;
       
      jlbltotal.setValue(total);
//      jlblsubtotal.setValue(subtotal);
//      jlbliva.setValue(iva);
    }
    public void newventa(){
        //NUEVO MODELO
        for (int i = 0; i < jtabla.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
        }
        // NUEVOS OBJETOS 
        venta= new Ventas();
        producto= new Producto();
        cliente = new Cliente();
        listprod =new ArrayList<>();
        //LIMPIAR JLBL
        jlbltotal.setValue(0);
//        jlbliva.setText("");
//        jlblsubtotal.setText("");
        //TEXTO
        jtfcliente.setText("NOMBRE CLIENTE");
        jtfrut.setText("R.U.T CLIENTE");
        idcliente= 0;
        jtfrut.setEnabled(true);
        jtfcompras.setValue(0);
        jtftotalcompras.setValue(0);
        jtfdescuento.setValue(0);
    
    }
    public void setbuscarproducto(String codigo){
        System.out.println("codgo "+codigo);
        producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), jlblimagen, codigo);
      //  System.out.println("antes del error");
      
        jlblcodigo.setText(producto.getCodigo());
        jlblproducto.setText(producto.getDescripcion());
        jtadescripprod.setText(producto.getObservacion());
        producto.getFoto();
        jlblprecio.setValue(producto.getPrecio());
        jlblstock.setValue(producto.getCantidad());
    
        jtfcantidad.requestFocus();
        
    }
    public boolean validadescuento(){
        boolean valida;
    double descuento = Double.parseDouble(jtfdescuento.getValue().toString());
    double total= Double.parseDouble(jlbltotal.getValue().toString());
//    double descuentomax= Double.parseDouble(jlbltotal.getValue().toString())*0.1;
        if(descuento> total){
            jlblmsjdescuento.setText("El descuento debe ser menor del total vendido");
            valida = false;
        }else {
            valida=true;
        }
    return valida;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        windowsUtil1 = new org.edisoncor.gui.util.WindowsUtil();
        jPanel2 = new javax.swing.JPanel();
        jtfvender = new org.edisoncor.gui.button.ButtonColoredAction();
        jtfsalir = new org.edisoncor.gui.button.ButtonColoredAction();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jtfingresecodigo = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jLabel7 = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jlblcargarventa = new javax.swing.JLabel();
        panelNice2 = new org.edisoncor.gui.panel.PanelNice();
        jtfrut = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jtfcliente = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtadescripprod = new javax.swing.JTextArea();
        jtfdescuento = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jlblmensaje = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtfcompras = new javax.swing.JFormattedTextField();
        jtftotalcompras = new javax.swing.JFormattedTextField();
        jlblmsjdescuento = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtamotivodesc = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        panelNice3 = new org.edisoncor.gui.panel.PanelNice();
        jtbtn7 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn8 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn9 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtnborrar = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtnborrartodo = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn6 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn5 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn4 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn1 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn2 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn3 = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtnenter = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtncoma = new org.edisoncor.gui.button.ButtonColoredAction();
        jbtn0 = new org.edisoncor.gui.button.ButtonColoredAction();
        jtfcantidad = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        jlblprecio = new javax.swing.JFormattedTextField();
        jlblproducto = new javax.swing.JLabel();
        jlblcodigo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelRound3 = new org.edisoncor.gui.panel.PanelRound();
        jlblimagen = new javax.swing.JLabel();
        jlblstock = new javax.swing.JFormattedTextField();

        setTitle("VENTA");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(232, 231, 231));

        jtfvender.setBackground(new java.awt.Color(255, 255, 255));
        jtfvender.setText("Vender (F2)");
        jtfvender.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jtfvender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtfvenderMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtfvenderMousePressed(evt);
            }
        });
        jtfvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfvenderActionPerformed(evt);
            }
        });

        jtfsalir.setBackground(new java.awt.Color(255, 255, 255));
        jtfsalir.setText("X");
        jtfsalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jtfsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfsalirActionPerformed(evt);
            }
        });

        panelNice1.setBackground(new java.awt.Color(255, 255, 255));
        panelNice1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelNice1.setBorderColor(new java.awt.Color(173, 173, 173));
        panelNice1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfingresecodigo.setBackground(new java.awt.Color(204, 204, 204));
        jtfingresecodigo.setForeground(new java.awt.Color(255, 255, 255));
        jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)");
        jtfingresecodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfingresecodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfingresecodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfingresecodigoFocusLost(evt);
            }
        });
        jtfingresecodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfingresecodigoKeyReleased(evt);
            }
        });
        panelNice1.add(jtfingresecodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 500, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 102));
        jLabel7.setText("TOTAL:");
        panelNice1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        jlbltotal.setEditable(false);
        jlbltotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        panelNice1.add(jlbltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 250, -1));

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
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        panelNice1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 590, 380));

        jlblcargarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading_cart.gif"))); // NOI18N
        jlblcargarventa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelNice1.add(jlblcargarventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 250, 210));

        panelNice2.setBackground(new java.awt.Color(255, 255, 255));
        panelNice2.setBorderColor(new java.awt.Color(173, 173, 173));
        panelNice2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfrut.setBackground(new java.awt.Color(204, 204, 204));
        jtfrut.setForeground(new java.awt.Color(255, 255, 255));
        jtfrut.setText("R.U.T CLIENTE");
        jtfrut.setFont(new java.awt.Font("Segoe UI Light", 1, 10)); // NOI18N
        jtfrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfrutFocusLost(evt);
            }
        });
        jtfrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfrutActionPerformed(evt);
            }
        });
        jtfrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrutKeyReleased(evt);
            }
        });
        panelNice2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 30));

        jtfcliente.setBackground(new java.awt.Color(204, 204, 204));
        jtfcliente.setForeground(new java.awt.Color(255, 255, 255));
        jtfcliente.setText("NOMBRE CLIENTE");
        jtfcliente.setEnabled(false);
        jtfcliente.setFont(new java.awt.Font("Segoe UI Light", 1, 10)); // NOI18N
        jtfcliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfclienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfclienteFocusLost(evt);
            }
        });
        jtfcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfclienteKeyTyped(evt);
            }
        });
        panelNice2.add(jtfcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 220, 30));

        jtadescripprod.setEditable(false);
        jtadescripprod.setColumns(20);
        jtadescripprod.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jtadescripprod.setRows(5);
        jtadescripprod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtadescripprodFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtadescripprodFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jtadescripprod);

        panelNice2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 250, 90));

        jtfdescuento.setBackground(new java.awt.Color(204, 204, 204));
        jtfdescuento.setForeground(new java.awt.Color(255, 51, 51));
        jtfdescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jtfdescuento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfdescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfdescuentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfdescuentoKeyReleased(evt);
            }
        });
        panelNice2.add(jtfdescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 140, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Descuento:");
        panelNice2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));

        jlblmensaje.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlblmensaje.setForeground(new java.awt.Color(255, 51, 51));
        panelNice2.add(jlblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 80, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Compras:");
        panelNice2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Total de Compras:");
        panelNice2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jtfcompras.setEditable(false);
        panelNice2.add(jtfcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 110, -1));

        jtftotalcompras.setEditable(false);
        panelNice2.add(jtftotalcompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 150, -1));

        jlblmsjdescuento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblmsjdescuento.setForeground(new java.awt.Color(255, 51, 51));
        panelNice2.add(jlblmsjdescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 250, 20));

        jLabel5.setText("Descripción del Producto:");
        panelNice2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        jtamotivodesc.setColumns(20);
        jtamotivodesc.setRows(5);
        jScrollPane3.setViewportView(jtamotivodesc);

        panelNice2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 250, -1));

        jLabel6.setText("Motivo del descuento:");
        panelNice2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        panelNice3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtbtn7.setBackground(new java.awt.Color(255, 255, 255));
        jtbtn7.setText("7");
        jtbtn7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jtbtn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbtn7ActionPerformed(evt);
            }
        });
        panelNice3.add(jtbtn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        jbtn8.setBackground(new java.awt.Color(255, 255, 255));
        jbtn8.setText("8");
        jbtn8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn8ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 50, 40));

        jbtn9.setBackground(new java.awt.Color(255, 255, 255));
        jbtn9.setText("9");
        jbtn9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn9ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 50, 40));

        jbtnborrar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnborrar.setText("<");
        jbtnborrar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtnborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnborrarActionPerformed(evt);
            }
        });
        panelNice3.add(jbtnborrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 50, 40));

        jbtnborrartodo.setBackground(new java.awt.Color(255, 255, 255));
        jbtnborrartodo.setText("C");
        jbtnborrartodo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtnborrartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnborrartodoActionPerformed(evt);
            }
        });
        panelNice3.add(jbtnborrartodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 50, 40));

        jbtn6.setBackground(new java.awt.Color(255, 255, 255));
        jbtn6.setText("6");
        jbtn6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn6ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 50, 40));

        jbtn5.setBackground(new java.awt.Color(255, 255, 255));
        jbtn5.setText("5");
        jbtn5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn5ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 50, 40));

        jbtn4.setBackground(new java.awt.Color(255, 255, 255));
        jbtn4.setText("4");
        jbtn4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn4ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 40));

        jbtn1.setBackground(new java.awt.Color(255, 255, 255));
        jbtn1.setText("1");
        jbtn1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn1ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 40));

        jbtn2.setBackground(new java.awt.Color(255, 255, 255));
        jbtn2.setText("2");
        jbtn2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn2ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 50, 40));

        jbtn3.setBackground(new java.awt.Color(255, 255, 255));
        jbtn3.setText("3");
        jbtn3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn3ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 50, 40));

        jbtnenter.setBackground(new java.awt.Color(255, 255, 255));
        jbtnenter.setText("ENTER");
        jbtnenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnenterActionPerformed(evt);
            }
        });
        panelNice3.add(jbtnenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 50, 80));

        jbtncoma.setBackground(new java.awt.Color(255, 255, 255));
        jbtncoma.setText(".");
        jbtncoma.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtncoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncomaActionPerformed(evt);
            }
        });
        panelNice3.add(jbtncoma, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 50, 40));

        jbtn0.setBackground(new java.awt.Color(255, 255, 255));
        jbtn0.setText("0");
        jbtn0.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jbtn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn0ActionPerformed(evt);
            }
        });
        panelNice3.add(jbtn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, 40));

        jtfcantidad.setBackground(new java.awt.Color(204, 204, 204));
        jtfcantidad.setForeground(new java.awt.Color(255, 255, 255));
        jtfcantidad.setText("CANTIDAD");
        jtfcantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfcantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcantidadFocusLost(evt);
            }
        });
        jtfcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfcantidadActionPerformed(evt);
            }
        });
        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });

        jlblprecio.setEditable(false);
        jlblprecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jlblprecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jlblproducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblproducto.setForeground(new java.awt.Color(102, 102, 102));
        jlblproducto.setText("PRODUCTO");

        jlblcodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblcodigo.setForeground(new java.awt.Color(102, 102, 102));
        jlblcodigo.setText("CODIGO");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("STOCK");

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("x 1");

        panelRound3.setForeground(new java.awt.Color(255, 255, 255));
        panelRound3.setColorPrimario(new java.awt.Color(255, 255, 255));
        panelRound3.setColorSecundario(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlblstock.setEditable(false);
        jlblstock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jlblstock, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNice3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNice1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNice2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfvender, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jlblstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(jlblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jlblproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jlblprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jtfcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelNice3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jtfsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jtfvender, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panelNice2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbtn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtn7ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jtbtn7.getText());
        imprimircant();
    }//GEN-LAST:event_jtbtn7ActionPerformed

    private void jtfcantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcantidadFocusGained
        // TODO add your handling code here:
        if(jtfcantidad.getText().equals("CANTIDAD")){
            jtfcantidad.setText("");
        }
    }//GEN-LAST:event_jtfcantidadFocusGained

    private void jtfcantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcantidadFocusLost
        // TODO add your handling code here:
       if(jtfcantidad.getText().equals("")){
           jtfcantidad.setText("CANTIDAD");
       }
       
    }//GEN-LAST:event_jtfcantidadFocusLost

    private void jbtn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn0ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn0.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn0ActionPerformed

    private void jbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn1ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn1.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn1ActionPerformed

    private void jbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn2ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn2.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn2ActionPerformed

    private void jbtnborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnborrarActionPerformed
        // TODO add your handling code here:
        int digitos=cantidad.size()-1;
        System.out.println(digitos);
        if(digitos >= 0){
        cantidad.remove(digitos);
        imprimircant();
        
        }
        jtfcantidad.requestFocus();
        
        
    }//GEN-LAST:event_jbtnborrarActionPerformed

    private void jbtnborrartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnborrartodoActionPerformed
        // TODO add your handling code here:
       cantidad = new ArrayList<String>();
       imprimircant();
       jtfcantidad.requestFocus();
    }//GEN-LAST:event_jbtnborrartodoActionPerformed

    private void jbtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn3ActionPerformed
        // TODO add your handling code here:
      cantidad.add(jbtn3.getText());
        imprimircant();  
    }//GEN-LAST:event_jbtn3ActionPerformed

    private void jbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn4ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn4.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn4ActionPerformed

    private void jbtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn5ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn5.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn5ActionPerformed

    private void jbtn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn6ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn6.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn6ActionPerformed

    private void jbtn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn8ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn8.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn8ActionPerformed

    private void jbtn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn9ActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtn9.getText());
        imprimircant();
    }//GEN-LAST:event_jbtn9ActionPerformed

    private void jbtncomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncomaActionPerformed
        // TODO add your handling code here:
        cantidad.add(jbtncoma.getText());
        imprimircant();
    }//GEN-LAST:event_jbtncomaActionPerformed

    private void jtfingresecodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfingresecodigoFocusGained
        // TODO add your handling code here:
        if(jtfingresecodigo.getText().equals("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)")){
           jtfingresecodigo.setText("");
        }
    }//GEN-LAST:event_jtfingresecodigoFocusGained

    private void jtfingresecodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfingresecodigoFocusLost
        // TODO add your handling code here:
        if (jtfingresecodigo.getText().equals("")){
          jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)");
        }
    }//GEN-LAST:event_jtfingresecodigoFocusLost

    private void jtfingresecodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfingresecodigoKeyReleased
        // TODO add your handling code here:
        String codigo=jtfingresecodigo.getText().replace("\\s", "");
        if(evt.getKeyCode()==10){
            setbuscarproducto(codigo);
            System.out.println("enter");
        }
        if(evt.getKeyCode()==112){
            JDBuscarProductoVenta buscarproducto = new JDBuscarProductoVenta(new java.awt.Frame(),
                    isVisible(),this);
            buscarproducto.setVisible(true);
                    
        
        }
        
       
        
      
    }//GEN-LAST:event_jtfingresecodigoKeyReleased

    private void jtfrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusGained
        // TODO add your handling code here:
        if(jtfrut.getText().equals("R.U.T CLIENTE")){
            jtfrut.setText("");
        }
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfsalirActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "SEGURO DE CERRAR LA VENTANA VENTAS","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
           
//            if(modelo.getRowCount()>0){
             
              
                    for(Producto prod : listprod)
                    daodetventa.eliminar(prod);
//                    //modelo.removeRow(i);
//                    cont++;
//            }
       
//                if(venta.getIdventa()!=0){
//                daoventa.eliminar(venta.getIdventa());
//                }
//              
            
            this.dispose();
        
        }
        
    }//GEN-LAST:event_jtfsalirActionPerformed

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
        if(jtfrut.getText().equals("")){
            jtfrut.setText("R.U.T CLIENTE");
        }
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfclienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfclienteFocusGained
        // TODO add your handling code here:
        if(jtfcliente.getText().equals("NOMBRE CLIENTE")){
           jtfcliente.setText("");
        
        }
    }//GEN-LAST:event_jtfclienteFocusGained

    private void jtfclienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfclienteFocusLost
        // TODO add your handling code here:
          if(jtfcliente.getText().equals("")){
           jtfcliente.setText("NOMBRE CLIENTE");
        
        }
    }//GEN-LAST:event_jtfclienteFocusLost

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
     
        
           if(evt.getKeyCode()==113){
           jtfvender.doClick();
        
        }
     
               
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jbtnenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnenterActionPerformed
        // TODO add your handling code here:
      
       
        try {
           
        if(Double.parseDouble(jtfcantidad.getText())>0 && !jtfcantidad.getText().equals("") && !jtfcantidad.getText().equals("CANTIDAD")){ //VALIDO CANTIDAD INGRESADA
//           boolean valida;
              System.out.println("enter");
            if(producto.getIdproducto()!=0){ //VALIDO BUSQUEDA DE PRODUCTO
//                List<Object> valida= new ArrayList<>();
//                List<String> returninsertvent= new ArrayList<>();
//                if(idcliente!=0){
               if(daoproducto.validastockrequerido(Double.parseDouble(jtfcantidad.getText()), producto.getIdproducto())==true){ ////valida stock ventaS
  
                
                  
              
//                        System.out.println("valida"+valida.get(0));
//                if(String.valueOf(valida.get(0)).equals("true")){
//                    DecimalFormat formateador = new DecimalFormat("###");
                    NumberFormat nf = NumberFormat.getInstance();
                    Object[] miarray = new Object[5];
//                    miarray[0]=valida.get(1);
//                    miarray[1]=producto.getIdproducto();
                    Double cantidad = Double.parseDouble(jtfcantidad.getText());
                    miarray[0]=producto.getCodigo();
                    miarray[1]=producto.getDescripcion();
                    miarray[2]=nf.format(cantidad);
                    miarray[3]=nf.format(producto.getPrecio());
                    Double importe= producto.getPrecio()*cantidad;
                    
                    miarray[4]=nf.format(importe);
        
                    modelo.addRow(miarray);
                    producto.setCantidad(cantidad);
                   
                    listprod.add(producto);
                    calculatotal();
                    jtfcantidad.setText("0");
//                }
//                
//            }else {
//                JOptionPane.showMessageDialog(null, "INGRESE DATOS DEL CLIENTE");
//                
//                }
                jtfingresecodigo.setText("INGRESE CODIGO DEL PRODUCTO (F1 BUSQUEDA)");
//                jtfingresecodigoKeyReleased(null);
                setbuscarproducto("");
                            
               }else{
                JOptionPane.showMessageDialog(null, "No cuenta con stock para la venta del producto");
               }
                
            }else {
                JOptionPane.showMessageDialog(null, "INGRESE CODIGO DEL PRODUCTO");
            }
           
        }else{
            JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA");

        
        }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA");
        }
    
        
      
    }//GEN-LAST:event_jbtnenterActionPerformed

    private void jtfvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfvenderActionPerformed
        // TODO add your handling code here:
        if(listprod.size()>0 && validadescuento()==true){
         if (JOptionPane.showConfirmDialog(null, "SEGURO CONFIRMAR LA VENTA","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             
              if(idcliente ==0 ){ //VALIDO BUSQUEDA DE CLIENTE
                    cliente.setRut(jtfrut.getText());
                    cliente.setNombre_razons(jtfcliente.getText());
                    idcliente=daocliente.insertarcliente(cliente);
                }
              ////////////////////
                venta.setIdcliente(idcliente);
                venta.setIdempleado(user.getIdempleado());
                venta.setId_sucursal(sucursalsingleton.getId());
                venta.setDescuento(Double.parseDouble(jtfdescuento.getValue().toString()));
                venta.setMotivodescuento(jtamotivodesc.getText().toUpperCase());
                if(idcliente!=0){
                 venta.setIdventa(daoventa.insertar(venta));
                }else {
                 venta.setIdventa(daoventa.insertarnocliente(venta));
                }
             
                daodetventa.insertar(listprod,venta.getIdventa());
             
             
             System.out.println("idvnetqa"+venta.getIdventa());
            daoventa.imprimirticketcaja(venta.getIdventa());
            newventa();
            
            
        }
         jlblcargarventa.setVisible(false);
          
         
        
        }else{
            JOptionPane.showMessageDialog(null, "INGRESE DATOS VALIDOS");
            
        }
        
        
        ///
       
                   
        
        
        
       
        
        
    }//GEN-LAST:event_jtfvenderActionPerformed

    private void jtfcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcantidadActionPerformed
        // TODO add your handling code here:
        jbtnenter.doClick();
    }//GEN-LAST:event_jtfcantidadActionPerformed

    private void jtfdescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescuentoKeyReleased
        // TODO add your handling code here:
        validadescuento();
           if(evt.getKeyCode()==113){
             
               
           jtfvender.doClick();
           
        
        }
    }//GEN-LAST:event_jtfdescuentoKeyReleased

    private void jtfclienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfclienteKeyTyped
        // TODO add your handling code here:
        mayus.convertirmayus(jtfcliente);
    }//GEN-LAST:event_jtfclienteKeyTyped

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
          int index= jtabla.getSelectedRow();
        if(index >=0){
            if(evt.getClickCount()==2){

                if(index>=0){
                    if (JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE RETIRAR EL PRODUCTO DE LA VENTA","",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                       Producto proddelete= new Producto();
                       proddelete=listprod.get(index);
                       
                        daodetventa.eliminar(proddelete);
                        modelo.removeRow(index);
                        listprod.remove(index);
                        calculatotal();
//                        JOptionPane.showMessageDialog(null,"PRODUCTO RETIRADO DE LA VENTA CORRECTAMENTE");
                    }

                }

            }

        }
    }//GEN-LAST:event_jtablaMouseReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formKeyPressed

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        
         if(evt.getKeyCode()==113){
           jtfvender.doClick();
          
        
        }
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jtfrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfrutActionPerformed
        // TODO add your handling code here:
           String rut= jtfrut.getText().trim();
       
        idcliente=daocliente.buscarclientevent(rut,jlblmensaje,jtfcliente,jtftotalcompras,jtfcompras);
        if(idcliente==0){
        jtfcliente.setEnabled(true);
        
        }else {
        jtfcliente.setEnabled(false);
        }
    }//GEN-LAST:event_jtfrutActionPerformed

    private void jtadescripprodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtadescripprodFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtadescripprodFocusGained

    private void jtadescripprodFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtadescripprodFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jtadescripprodFocusLost

    private void jtfvenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMousePressed
        // TODO add your handling code here:
          if(listprod.size()>0 && validadescuento()==true){
               jlblcargarventa.setVisible(true);
          }
       
    }//GEN-LAST:event_jtfvenderMousePressed

    private void jtfvenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfvenderMouseExited
        // TODO add your handling code here:
        jlblcargarventa.setVisible(false);
    }//GEN-LAST:event_jtfvenderMouseExited

    private void jtfcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==113){
             if(listprod.size()>0 && validadescuento()==true){
               jlblcargarventa.setVisible(true);
          }   
               
               }
    }//GEN-LAST:event_jtfcantidadKeyPressed

    private void jtfdescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescuentoKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==113){
             if(listprod.size()>0 && validadescuento()==true){
               jlblcargarventa.setVisible(true);
          }   
               
               }
    }//GEN-LAST:event_jtfdescuentoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn0;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn1;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn2;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn3;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn4;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn5;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn6;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn8;
    private org.edisoncor.gui.button.ButtonColoredAction jbtn9;
    private org.edisoncor.gui.button.ButtonColoredAction jbtnborrar;
    private org.edisoncor.gui.button.ButtonColoredAction jbtnborrartodo;
    private org.edisoncor.gui.button.ButtonColoredAction jbtncoma;
    private org.edisoncor.gui.button.ButtonColoredAction jbtnenter;
    private javax.swing.JLabel jlblcargarventa;
    private javax.swing.JLabel jlblcodigo;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JLabel jlblmensaje;
    private javax.swing.JLabel jlblmsjdescuento;
    private javax.swing.JFormattedTextField jlblprecio;
    private javax.swing.JLabel jlblproducto;
    private javax.swing.JFormattedTextField jlblstock;
    private javax.swing.JFormattedTextField jlbltotal;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextArea jtadescripprod;
    private javax.swing.JTextArea jtamotivodesc;
    private org.edisoncor.gui.button.ButtonColoredAction jtbtn7;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfcantidad;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfcliente;
    private javax.swing.JFormattedTextField jtfcompras;
    private javax.swing.JFormattedTextField jtfdescuento;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfingresecodigo;
    private org.edisoncor.gui.textField.TextFieldRoundIcon jtfrut;
    private org.edisoncor.gui.button.ButtonColoredAction jtfsalir;
    private javax.swing.JFormattedTextField jtftotalcompras;
    private org.edisoncor.gui.button.ButtonColoredAction jtfvender;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private org.edisoncor.gui.panel.PanelNice panelNice2;
    private org.edisoncor.gui.panel.PanelNice panelNice3;
    private org.edisoncor.gui.panel.PanelRound panelRound3;
    private org.edisoncor.gui.util.WindowsUtil windowsUtil1;
    // End of variables declaration//GEN-END:variables
}
