/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import DAO.MonedaDAO;
import DAO.ProductoDAO;
import DAO.TipoNotaDAO;
import DAO.VentasDAO;
import Facturacion.ConsumingPost;
import Pojos.Cliente;
import Pojos.GuiaTipo;
import Pojos.Moneda;
import Pojos.Producto;
import Pojos.SerieNumeroRef;
import Pojos.TipoNota;
import Pojos.Tipo_Igv;
import Pojos.Ventas;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author HERNAN
 */
public class JDNotaCreditoDebito extends javax.swing.JDialog {

    /**
     * Creates new form JDNotaCreditoDebito
     */
    List<Moneda> listmoneda ;
    List<Tipo_Igv> listigv;
    List<TipoNota> listtiponota = new ArrayList<>();
    int op;
    Cliente cliente;
    Ventas ventaB= new Ventas();
    VentasDAO daoventa= new VentasDAO();
    List<Producto> listprod=new ArrayList<>();
    List<GuiaTipo> listguia = new ArrayList<>();
    FormatoNumerico fn = new FormatoNumerico();
    boolean validacredito=true;
    boolean validadebito=true;
    DefaultTableModel model ;
    String mov;
    ProductoDAO daoproducto = new ProductoDAO();
    boolean error=false;
    MDIMenu menu;
    public JDNotaCreditoDebito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }
    public JDNotaCreditoDebito(java.awt.Frame parent, boolean modal,String comprobante,String tipo,int op,Ventas v,MDIMenu menu) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        jlblnota.setText(tipo);
//        jdpfecha.setDate(new Date());
        this.op = op;
         mostramoneda();
         mostrartiponota();
//         mostrartipoigv();
//        if(op==3)  //////////////// nota de credito ///////////////////////////////
//        {
            ventaB=daoventa.buscarventa(listprod,listguia, "v"+v.getIdventa(), "todo");
            jlblcliente.setText(ventaB.getClienteRS());
            jlblclientedireccion.setText(ventaB.getClientedirec());
            jlbldocumento.setText(ventaB.getClientenumdoc());
            cargartabla();
//        }
        ////////////////////////////////////////////////////////////////////////
//        ventaB = v;
        jlblfactura.setText("Doc. referencia: "+ventaB.getSerie()+" - "+ventaB.getNumero());
        
         ////////////////////////////////////
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(600);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        //////////////////////////////     
        calcular();
        jbtnretirar.setMnemonic(KeyEvent.VK_DELETE);
        jbtnagregar.setMnemonic(KeyEvent.VK_PLUS);
        anadeListenerAlModelo(jtabla);
        jprogres.setVisible(false);
        this.menu=menu;
    }
    
    
        private void anadeListenerAlModelo(JTable tabla) {
        tabla.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent evento) {
                actualizaimporte(evento);
            }
        });
    }
        
        protected void actualizaimporte(TableModelEvent evento)
    {
          
              if(evento.getType() == TableModelEvent.UPDATE)
        {

                // Se obtiene el modelo de la tabla y la fila/columna que han cambiado.
            TableModel modelo = ((TableModel) (evento.getSource()));
            int fila = evento.getFirstRow();
            int columna = evento.getColumn();

            // Los cambios en la ultima fila y columna se ignoran.
            // Este return es necesario porque cuando nuestro codigo modifique
            // los valores de las sumas en esta fila y columna, saltara nuevamente
            // el evento, metiendonos en un bucle recursivo de llamadas a este
            // metodo.
            if (columna == 0 || columna == 1 || columna==4) {
                return;
            }
                
        try {
          
           System.out.println("index "+fila);
           Producto prod= listprod.get(fila);
           Double precio= Double.parseDouble(modelo.getValueAt(fila, 3).toString());
           Double cantidad= Double.parseDouble(modelo.getValueAt(fila, 2).toString());

   //        boolean vprecio=true, vcantidad=true;

               System.out.println("cantidad en tabla "+cantidad);
               System.out.println("Precio en tabla "+precio);
       
        
        
        
        
        
            if(prod.getPrecio3()<=precio){
                prod.setPrecio(precio);
                prod.setCantidad(cantidad);
                modelo.setValueAt(fn.FormatoN(precio*cantidad), fila,4);
                calcular();

            }else {
            JOptionPane.showMessageDialog(null,"Ingrese un precio valido","",JOptionPane.ERROR_MESSAGE);
                modelo.setValueAt((fn.FormatoN(prod.getPrecio())), fila, 3);
    //            vprecio = false;

            }
            if(op==4){
                System.out.println("unidm "+prod.getUnidadm());
                 if(prod.getUnidadm().equals("PRODUCTO"))
            {
                if(cantidad>0){
                    prod.setCantidad(cantidad);
                    prod.setPrecio(precio);
                    modelo.setValueAt(fn.FormatoN(precio*cantidad), fila,4);
                    calcular();
                }else {
                    JOptionPane.showMessageDialog(null,"No cuenta con el stock requerido","",JOptionPane.ERROR_MESSAGE);
                    modelo.setValueAt(fn.FormatoN(prod.getCantidad()), fila, 2);
                   
                    System.out.println("cantidadanterior "+prod.getCantidad());
        //            vcantidad=false;
                }

                System.out.print("cantidadenlista" +prod.getCantidad());
                
            }else {
                     if(cantidad>0){
                        modelo.setValueAt(fn.FormatoN(precio*cantidad), fila,4);
                        prod.setCantidad(cantidad);
                        prod.setPrecio(precio);
                        calcular();
                     
                     }else {
                         JOptionPane.showMessageDialog(null,"Ingrese cantidad","",JOptionPane.ERROR_MESSAGE);
                         modelo.setValueAt(fn.FormatoN(prod.getCantidad()), fila, 2);
                     
                     }
                 
                 
                 }
                
                
            
            }else {
                prod.setCantidad(cantidad);

                modelo.setValueAt(fn.FormatoN(prod.getPrecio()*prod.getCantidad()), fila,4);
            
            }
            

            calcular();

            error=false;

                System.out.print("entertabla");
    //        if(vcantidad==true && vprecio==true){
    //        jtabla.setValueAt(newimporte, index,4);
    //        
    //        }

            } catch (NumberFormatException | HeadlessException e) {
                
                jlbltotal.setText("Error");
                error=true;
            }
    
    }
    }    
    
    
    
    
    public void mostramoneda()
    {
        MonedaDAO  daomoneda = new MonedaDAO();
        listmoneda = daomoneda.mostrar();
        
        for(Moneda m : listmoneda)
        {
            jcbmoneda.addItem(m.getOp()+" - "+m.getMoneda());
            if(m.getMoneda().equals("SOLES"))
            {
                jcbmoneda.setSelectedItem(m.getOp()+" - "+m.getMoneda());
                jlbltipocambio.setText(String.valueOf(m.getTipo_cambio()));
            }
        }
        
    
    }
    public void calcular()
    {
        double total=0.0,subtotal=0.0,igv=0.0;
        for(Producto p: listprod)
        {
            total = total + (p.getPrecio()*p.getCantidad());
        }
        jlbltotal.setText(fn.FormatoN(total));
        subtotal=total/1.18;
        igv=total-subtotal;
        jlblsubtotal.setText(fn.FormatoN(subtotal));
        jlbligv.setText(fn.FormatoN(igv));
        ventaB.setTotal(total);
    
    
    }
//    public void mostrartipoigv()
//    {
//        TipoIgvDAO daoigv= new TipoIgvDAO();
//        listigv = daoigv.mostrar();
//        for(Tipo_Igv t: listigv)
//        {
//            jcbtipoigv.addItem(t.getOp()+" - "+t.getDescripcion());
//            
//        
//        }
//                
//    }
    public void mostrartiponota()
    {
        TipoNotaDAO tndao = new TipoNotaDAO();
        listtiponota.add(new TipoNota());
        if(op==3)
        {
            listtiponota = tndao.mostrar("credito");
            jbtnagregar.setEnabled(false);
            mov="Notas Credito";
        }
        if(op==4)
        {
            listtiponota = tndao.mostrar("debito");
//            jbtnretirar.setEnabled(false);
            mov="Notas Debito";
        }
        
        
        jcbop.addItem("<<Seleccione>>");
        for(TipoNota tn: listtiponota)
        {
            jcbop.addItem(tn.getOp()+" - "+tn.getDescripcion());
        }
    
    }
    public void setcliente(Cliente cliente)
    {
        this.cliente= cliente;
        jlblcliente.setText("Cliente: "+cliente.getNombre_razons());
        ventaB.setIdcliente(cliente.getId_cliente());
        ventaB.setClientetipodoc(cliente.getTipodoc());
        ventaB.setClientenumdoc(cliente.getRut());
        ventaB.setClienteRS(cliente.getNombre_razons());
        ventaB.setClientedirec(cliente.getDireccion());
        ventaB.setClientedirec(cliente.getEmail());
        
    
    }
    public void cargartabla()
    { 
        
        model =(DefaultTableModel)jtabla.getModel();
        for (int i = 0; i < jtabla.getRowCount(); i++) {
        model.removeRow(i);
        i-=1;
        }
        Object datos[] = new Object[5];
        System.out.println("tamañolist"+listprod);
        for(Producto p : listprod)
        {
            datos[0]=p.getCodigo();
            datos[1]=p.getDescripcion();
            datos[2]=fn.FormatoN(p.getCantidad());
            datos[3]=fn.FormatoN(p.getPrecio());
            datos[4]=fn.FormatoN(p.getCantidad()*p.getPrecio());
            model.addRow(datos);
        }   
    }
    public void addproducto(Producto prod)
    {
        listprod.add(prod);
        cargartabla();
        calcular();
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblfactura = new javax.swing.JLabel();
        jlblnota = new javax.swing.JLabel();
        jcbop = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlbltipocambio = new javax.swing.JLabel();
        jcbmoneda = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jlblcliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jlblsubtotal = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jlbligv = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jlbltotal = new javax.swing.JLabel();
        jbtnbuscarcliente = new javax.swing.JButton();
        jbtnagregar = new javax.swing.JButton();
        jbtnretirar = new javax.swing.JButton();
        jlblmens = new javax.swing.JLabel();
        jbtnguardar = new javax.swing.JButton();
        jlbldocumento = new javax.swing.JLabel();
        jlblclientedireccion = new javax.swing.JLabel();
        jprogres = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jlblfactura.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jlblfactura.setText("* * * ");

        jlblnota.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jlblnota.setText("* * *");

        jLabel1.setText("Moneda:");

        jLabel3.setText("T.C:");

        jlbltipocambio.setText("* * *");

        jcbmoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmonedaActionPerformed(evt);
            }
        });

        jLabel4.setText("OP.:");

        jlblcliente.setText("Cliente: ");

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Precio Unitario", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setText("Subtotal Ventas:");

        jLabel11.setText("Anticipo:");

        jLabel12.setText("Descuento:");

        jLabel13.setText("Valor de Venta:");

        jLabel14.setText("ISC:");

        jLabel15.setText("IGV 18%:");

        jLabel16.setText("Otros Cargos:");

        jLabel17.setText("Otros Tributos:");

        jLabel18.setText("Importe Total:");

        jlblsubtotal.setText("* * *");

        jLabel20.setText("* * *");

        jLabel21.setText("* * *");

        jLabel22.setText("* * *");

        jLabel23.setText("* * *");

        jlbligv.setText("* * *");

        jLabel25.setText("* * *");

        jLabel26.setText("* * *");

        jlbltotal.setText("* * *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbligv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlblsubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jlblsubtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jlbligv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jlbltotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtnbuscarcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search20x20.png"))); // NOI18N
        jbtnbuscarcliente.setToolTipText("Buscar Cliente");
        jbtnbuscarcliente.setBorderPainted(false);
        jbtnbuscarcliente.setContentAreaFilled(false);
        jbtnbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarclienteActionPerformed(evt);
            }
        });

        jbtnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnagregar.setToolTipText("Agregar");
        jbtnagregar.setBorderPainted(false);
        jbtnagregar.setContentAreaFilled(false);
        jbtnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagregarActionPerformed(evt);
            }
        });

        jbtnretirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnretirar.setToolTipText("Retirar (alt + supr)");
        jbtnretirar.setBorderPainted(false);
        jbtnretirar.setContentAreaFilled(false);
        jbtnretirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnretirarActionPerformed(evt);
            }
        });

        jlblmens.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N

        jbtnguardar.setBackground(new java.awt.Color(77, 161, 227));
        jbtnguardar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jbtnguardar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setMnemonic('s');
        jbtnguardar.setText("Guardar ");
        jbtnguardar.setToolTipText("(alt + s)");
        jbtnguardar.setBorderPainted(false);
        jbtnguardar.setContentAreaFilled(false);
        jbtnguardar.setOpaque(true);
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });

        jlbldocumento.setText("Doc.:");

        jlblclientedireccion.setText("Direccion:");

        jprogres.setBorderPainted(false);
        jprogres.setString("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblmens, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 295, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                                .addComponent(jbtnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbldocumento)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jcbmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlbltipocambio, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jcbop, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jlblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnbuscarcliente))
                            .addComponent(jlblfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblnota, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnagregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnretirar))
                            .addComponent(jlblclientedireccion))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jprogres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jprogres, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblnota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblfactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jlbltipocambio)
                    .addComponent(jcbmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblcliente))
                    .addComponent(jbtnbuscarcliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblclientedireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbldocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnagregar)
                    .addComponent(jbtnretirar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblmens, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarclienteActionPerformed
        // TODO add your handling code here:
        JDBuscarCliente bcliente = new JDBuscarCliente(new JFrame(), isVisible(),this);
        bcliente.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarclienteActionPerformed

    private void jcbmonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmonedaActionPerformed
        // TODO add your handling code here:
        Moneda m = listmoneda.get(jcbmoneda.getSelectedIndex());
        jlbltipocambio.setText(String.valueOf(m.getTipo_cambio()));
    }//GEN-LAST:event_jcbmonedaActionPerformed

    private void jtablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyReleased
        // TODO add your handling code here:
//        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
//        {
//            //////////////// nota de credito //////////////////
//            int index = jtabla.getSelectedRow();
//            if(index>=0)
//            {
//                double saldo=0.0;
//                try {
//                    
//                    Producto prod = listprod.get(index);
//                    double cantI = prod.getCantidad();
//                    double precioI = prod.getPrecio();
//                    
//                    double cantF = Double.parseDouble(jtabla.getValueAt(index, 2).toString());
//                    double precioF = Double.parseDouble(jtabla.getValueAt(index, 3).toString());
//                    double importe =0.0;
//                    if(op==3)
//                    {
//                        
//                         if(cantF<=cantI)
//                        {
//
//                            
//                            importe = prod.getCantidad()*prod.getPrecio();
//                            prod.setTotal(importe);
////                            saldo=cantF-cantI;
//                            System.out.println("saldo "+saldo);
//                            
//                            prod.setCantidad(cantF);
//                            jtabla.setValueAt(cantI, index, 4);
//                            
//                            calcular();
//                        }else{
//                            JOptionPane.showMessageDialog(null,"Nota Crédito no puede adicionar","",JOptionPane.ERROR_MESSAGE);
//                            jtabla.setValueAt(cantI, index, 2);
//                        }
//                        if(precioF<=precioI)
//                        {
//                            prod.setPrecio(precioF);
//                            importe = prod.getCantidad()*prod.getPrecio();
//                            prod.setTotal(importe);
//                            
//                            jtabla.setValueAt(cantI, index, 4);
//                            calcular();
//
//                        }else {
//                            JOptionPane.showMessageDialog(null,"Nota Crédito no puede adicionar","",JOptionPane.ERROR_MESSAGE);
//                            jtabla.setValueAt(precioI, index, 3);
//
//                        }
//                        
//                    
//                    }
        
        
        
        ////////////////////////////////////
//                    if(op==4)
//                    {
//                         if(cantI<=cantF)
//                        {
//
//                            prod.setCantidad(cantF);
//                            importe = prod.getCantidad()*prod.getPrecio();
//                            prod.setTotal(importe);
//                            saldo=cantF-cantI;
//                            prod.setSaldo(saldo);
//                            System.out.println("saldo "+saldo);
//                            jtabla.setValueAt(cantI, index, 4);
//                            calcular();
//                        }else{
//                            JOptionPane.showMessageDialog(null,"Nota Débito no puede disminuir","",JOptionPane.ERROR_MESSAGE);
//                            jtabla.setValueAt(cantI, index, 2);
//                        }
//                        if(precioI<=precioF)
//                        {
//                            prod.setPrecio(precioF);
//                            importe = prod.getCantidad()*prod.getPrecio();
//                            prod.setTotal(importe);
//                            jtabla.setValueAt(cantI, index, 4);
//                            calcular();
//
//                        }else {
//                            JOptionPane.showMessageDialog(null,"Nota Débito no puede disminuir","",JOptionPane.ERROR_MESSAGE);
//                            jtabla.setValueAt(precioI, index, 3);
//
//                        }
//                    
//                    }
//                    
                   
                    //////////////////////////77
//                    jlblmens.setText("");
//
//                } catch (Exception e) {
//                    jlblmens.setText("Error");
//                }
//                
//        
//        
//            }
            ////////////////////////////////////
            ////////////  nota debito //////////////////////////////////////
            
        
            
            
            
        
        
//        }
    }//GEN-LAST:event_jtablaKeyReleased

    private void jbtnretirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnretirarActionPerformed
        // TODO add your handling code here:
        int index= jtabla.getSelectedRow();
        if(index>=0)
        {
            if(JOptionPane.showConfirmDialog(null, "Seguro de retirar item")==JOptionPane.YES_OPTION)
            {
                listprod.remove(index);
                model.removeRow(index);
                calcular();
            }
        }else {
            JOptionPane.showMessageDialog(null,"Seleccione item","", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnretirarActionPerformed

    private void jbtnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagregarActionPerformed
        // TODO add your handling code here:
        JDBuscarProductoVenta bp = new JDBuscarProductoVenta(new Frame(), isVisible(),this,listprod);
        bp.setVisible(true);
        
        
    }//GEN-LAST:event_jbtnagregarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
        
         int index = jcbop.getSelectedIndex();
        if(index>0)
        {
         ////////////////////    ///////////////////////////  nuevo hilo ////////////////
        Runnable runnable=new Runnable() {

            @Override
            public void run() {
                ConsumingPost api;

            jprogres.setVisible(true);
            jprogres.setMinimum(0);
            jprogres.setMaximum(100);
            jprogres.setStringPainted(true);
            Ventas cab=null;
            String tipo="";
                int i = 0;
                long id=0;
            while(i<=100)
            {
                 jprogres.setValue(i);
                 if(i==0)
                 {
                     jcbmoneda.setEnabled(false);
                     jcbop.setEnabled(false);
                     jbtnretirar.setEnabled(false);
                     jbtnagregar.setEnabled(false);
                     jbtnbuscarcliente.setEnabled(false);
                     jbtnguardar.setEnabled(false);
                     JDNotaCreditoDebito.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                     jprogres.setVisible(true);
                     java.util.Date d = new java.util.Date();
                     java.sql.Date ds = new Date(d.getTime());
                     ventaB.setFecha(ds);
                 }
                 if(i==5){
                    TipoNota tn= listtiponota.get(index-1);
                    Moneda m = listmoneda.get(jcbmoneda.getSelectedIndex());
                    int comprobantemof;
                    String seriemof,numeromof;
                    comprobantemof=ventaB.getComprobante();

                    ventaB.setTipo_doc_modifica(String.valueOf(comprobantemof));
                    seriemof=ventaB.getSerie();
                    numeromof=ventaB.getNumero();


                    ventaB.setDoc_modifica_serie(seriemof);
                    ventaB.setDoc_modifica_numero(numeromof);
                    //////////////////////////////////////////////////
                    if(op==3){
                        ventaB.setTipo_nota_cred(String.valueOf(tn.getOp()));
                    }
                    if(op==4){
                        ventaB.setTipo_nota_deb(String.valueOf(tn.getOp()));
                    }


                    ventaB.setComprobante(op);
                    ventaB.setIdtiponota(tn.getId());
                    ventaB.setIdcomprobante(op);
                    ventaB.setIdmoneda(m.getId());
                    String ref = ventaB.getSerie()+" - "+ventaB.getNumero();
                    ventaB.setDocref(ref);

                    ventaB.setTipocambio(m.getTipo_cambio());

                    if(op==3)
                        tipo="credito";
                    else 
                        tipo="debito";


                 }


                if(i==10){
                    System.out.println("genera numero ref");
                          /////////////////// Genera serie y numero doc //////////////////
                        SerieNumeroRef sn;
                        sn = daoventa.generarserienumref(op);
                        ventaB.setNumero(sn.getNumero());
//                        ventaB.setSerie(sn.getSerie());
                        ////////////////////////////////////////////////////////////////
                }
                if(i==15){
                         System.out.println("Consumiendo api");
                  
                    api=new ConsumingPost(ventaB,listprod,listguia);

                     cab=api.apiConsume();
                  
                    
                     
                    
                    


                }
                if(i==25)
                {
                    if(cab!=null){
                        System.out.println("insertando cab");
                        id=daoventa.insertar(cab, listprod,listguia, true, tipo, mov);
                        JOptionPane.showMessageDialog(null, id);
                    }


                }


                if(i==50){
                    if(cab!=null){
                        if(id!=0){
                            JDNotaCreditoDebito.this.setVisible(false);
                            daoventa.imprimir(id);
                            menu.cargarresumen();
                        }

                    }

                }


                if(i==100)
                {
                        if(cab!=null)
                        {

                            JDNotaCreditoDebito.this.dispose();

                        }else {
                            jcbmoneda.setEnabled(true);
                            jcbop.setEnabled(true);
                            jbtnretirar.setEnabled(true);
                            jbtnagregar.setEnabled(true);
                            jbtnbuscarcliente.setEnabled(true);
                            jbtnguardar.setEnabled(true);
                            JDNotaCreditoDebito.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                        }





                }
            i++;
            }
           jprogres.setVisible(false);
            }
        };

        Thread t = new Thread(runnable);
        t.start();
        
        }else {
        
            JOptionPane.showMessageDialog(null, "Seleccione OP","",JOptionPane.ERROR_MESSAGE);
            jcbop.requestFocus();
        
        }
        
        
    }//GEN-LAST:event_jbtnguardarActionPerformed

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
            java.util.logging.Logger.getLogger(JDNotaCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDNotaCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDNotaCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDNotaCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDNotaCreditoDebito dialog = new JDNotaCreditoDebito(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnagregar;
    private javax.swing.JButton jbtnbuscarcliente;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnretirar;
    private javax.swing.JComboBox jcbmoneda;
    private javax.swing.JComboBox jcbop;
    private javax.swing.JLabel jlblcliente;
    private javax.swing.JLabel jlblclientedireccion;
    private javax.swing.JLabel jlbldocumento;
    private javax.swing.JLabel jlblfactura;
    private javax.swing.JLabel jlbligv;
    private javax.swing.JLabel jlblmens;
    private javax.swing.JLabel jlblnota;
    private javax.swing.JLabel jlblsubtotal;
    private javax.swing.JLabel jlbltipocambio;
    private javax.swing.JLabel jlbltotal;
    private javax.swing.JProgressBar jprogres;
    private javax.swing.JTable jtabla;
    // End of variables declaration//GEN-END:variables
}
