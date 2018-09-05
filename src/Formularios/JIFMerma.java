/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import ClasesGlobales.Mayusculas;
import DAO.DetalleMermaDAO;
import DAO.MermaDAO;
import DAO.ProductoDAO;
import Pojos.DetalleMerma;
import Pojos.Merma;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import com.sun.org.apache.bcel.internal.Constants;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author info2017
 */
public class JIFMerma extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFMerma
     */
    Producto producto = new Producto();
    ProductoDAO daoproducto= new ProductoDAO();
    SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
    Merma merma= new Merma();
    DetalleMerma detallemerma=new DetalleMerma();
    MermaDAO daomerma= new MermaDAO();
    DetalleMermaDAO daodetallemerma= new DetalleMermaDAO();
    Mayusculas mayus = new Mayusculas();
    List<Producto> listdet= new ArrayList<>();
//     DefaultTableModel modelo= new DefaultTableModel(){
//    public boolean isCellEditable(int row, int column) {
//          if (column == 2 || column==3) return true;
//    else
//    return false;
//    }
//      }; 
      int posx;
    int posy;
    boolean validadoc=true;
    DefaultTableModel modelo;
    FormatoNumerico fn = new FormatoNumerico();
    public JIFMerma() {
        initComponents();
//
//        String titulos[]={"Codigo","Descripcion","Cantidad","Motivo"};
//        modelo.setColumnIdentifiers(titulos);
//        jtabla.setModel(modelo);
        ////////////////////////////////////////////
        modelo= (DefaultTableModel)jtabla.getModel();
        ///////////////////////
        TableColumnModel columnModel = jtabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(550);      
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(500);
        jdpfecha.setDate(new Date());
        jlblmensajecarga.setVisible(false);
        jtfcodigo.requestFocus();
        
        anadeListenerAlModelo(jtabla);


    }
    
     /**
     * Se añade el listener al modelo y se llama a actualizaSumas(), que es el método 
     * encargado de actualizar las sumas de las celdas no editables.
     */
    private void anadeListenerAlModelo(JTable tabla) {
        tabla.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent evento) {
                actualizaimporte(evento);
            }
        });
    }

    /**
     * Cuando cambie el valor de alguna celda, el JTable avisará a los listener y
     * nuestro listener llamará a este método.
     */
    
    /**
     * Cuando cambie el valor de alguna celda, el JTable avisará a los listener y
 nuestro listener llamará a este método.
     * @param evento
     */
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
                if (columna == 0 || columna == 1) {
                    return;
                }


//            int index = jtabla.getSelectedRow();
//            if(index>=0)
//            {
                Producto prod = listdet.get(fila);
                
                double cantI= prod.getCantidad();
                String motI = prod.getMotivo();
                System.err.println("canti"+cantI);
                try {
                    System.err.println("fila "+fila);
                    System.err.println("valor "+modelo.getValueAt(fila, 2));
                    double cant= Double.parseDouble(modelo.getValueAt(fila, 2).toString());
                    String mov = modelo.getValueAt(fila, 3).toString();
                    System.err.println("motivo "+mov);
                    if(daoproducto.validastockrequerido(prod.getIdproducto(),cant)==true){
                    if(cant>0)
                    {
                        prod.setCantidad(cant);
                       
                        
//                        modelo.setValueAt(mov, fila, 3);
//                        modelo.setValueAt(cant, fila, 2);
                        validadoc=true;
                    
                    }else {
                        JOptionPane.showMessageDialog(null,"Ingrese datos validos", "",JOptionPane.ERROR_MESSAGE);
                        modelo.setValueAt(fn.FormatoN(cantI), fila, 2);
                        
                       validadoc=false;
                    }
                    if(mov.trim().length()>0)
                    {
                         prod.setMotivo(mov);
                         validadoc=true;
                    
                    }else {
                        JOptionPane.showMessageDialog(null,"Motivo invalido", "",JOptionPane.ERROR_MESSAGE);
                        modelo.setValueAt((motI), fila, 3);
                        
                       validadoc=false;
                    }
                   
                    
                    }else {
                        JOptionPane.showMessageDialog(null,"No cuenta con stock","", JOptionPane.ERROR_MESSAGE);
                        modelo.setValueAt(fn.FormatoN(cantI), fila, 2);
                        validadoc=false;
                }
                   

                    
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(null,"Ingrese datos validos", "",JOptionPane.ERROR_MESSAGE);
                    modelo.setValueAt(fn.FormatoN(cantI), fila, 2);
                    validadoc=false;
                    
                }       
    	}
//        }
        
     }
    
    
    
    
    
    
    public void validaagregar(){
        
        try {
            if(producto.getIdproducto()!=0 && Double.parseDouble(jtfcantidad.getText())>0 
                   && !jtamotivo.getText().trim().equals("")){
            jbtnagregar.setEnabled(true);
            }else {
            jbtnagregar.setEnabled(false);
            }
        } catch (Exception e) {
             jbtnagregar.setEnabled(false);
        }
    
    
    }
    public void nuevoprod(){
    producto= new Producto();
    jtfcantidad.setText("");
    jtfproducto.setText("");
    jtfcodigo.setText("");
    jlblimagen.setIcon(null);
    jtamotivo.setText("");
    }
    public void validaaceptar(){
     
    if(jdpfecha.getDate()!=null  &&
            listdet.size()> 0 && validadoc==true ){
    jbtnaceptar.setEnabled(true);
    
    }else {
    jbtnaceptar.setEnabled(false);
    
    }
  
    }
    public void nuevamerma(){
    merma = new Merma();
    listdet = new ArrayList<>();
//    producto= new Producto();
    
    for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.removeRow(i);
        i-=1;
     }
    jdpfecha.setDate(new Date());
    jtamotivo.setText("");
    jbtnaceptar.setEnabled(false);
     nuevoprod();
    }
    
    public void setproducto(Producto prod){
//            producto=prod;
            
            producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), prod.getCodigo());
           // jtfcodigo.setText(producto.getCodigo());
//            jtfstock.setValue((producto.getCantidad()));
            jtfproducto.setText(producto.getDescripcion());
            jtfcodigo.setText(producto.getCodigo());
            
            /// mostrar imagen ///
            ImageIcon imageIcon = new ImageIcon(producto.getFoto());
            //ImageIcon imageUser = imageIcon;
            Image img = imageIcon.getImage();
            Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
            imageIcon = new ImageIcon(newimg);
            jlblimagen.setIcon(imageIcon);   
        //////////////////////////////////////////////
            jtfcantidad.requestFocus();
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
        jdpfecha = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jtfcodigo = new javax.swing.JTextField();
        jbtnagregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfcantidad = new javax.swing.JTextField();
        jbtnbuscarprod = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtamotivo = new javax.swing.JTextArea();
        jlblimagen = new javax.swing.JLabel();
        jtfproducto = new javax.swing.JLabel();
        jbtnaceptar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jlblmensajecarga = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jbtnsalir = new javax.swing.JButton();
        jbtnretirar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdpfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdpfechaActionPerformed(evt);
            }
        });
        jPanel1.add(jdpfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 150, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Fecha:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 20));

        jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtablaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtablaFocusLost(evt);
            }
        });
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtablaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 890, 200));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfcodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfcodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfcodigoFocusLost(evt);
            }
        });
        jtfcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyReleased(evt);
            }
        });
        jPanel2.add(jtfcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 160, -1));

        jbtnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addproduct20x20.png"))); // NOI18N
        jbtnagregar.setText("Agregar");
        jbtnagregar.setBorder(null);
        jbtnagregar.setBorderPainted(false);
        jbtnagregar.setContentAreaFilled(false);
        jbtnagregar.setEnabled(false);
        jbtnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnagregarActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 130, 40));

        jLabel3.setText("Cantidad:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        jLabel4.setText("Codigo:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jtfcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcantidadKeyReleased(evt);
            }
        });
        jPanel2.add(jtfcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 80, -1));

        jbtnbuscarprod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search20x20.png"))); // NOI18N
        jbtnbuscarprod.setMnemonic('b');
        jbtnbuscarprod.setToolTipText("Alt + b");
        jbtnbuscarprod.setBorderPainted(false);
        jbtnbuscarprod.setContentAreaFilled(false);
        jbtnbuscarprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarprodActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnbuscarprod, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        jLabel7.setText("Motivo:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jtamotivo.setColumns(20);
        jtamotivo.setRows(5);
        jtamotivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtamotivoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtamotivoFocusLost(evt);
            }
        });
        jtamotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtamotivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtamotivoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jtamotivo);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 620, 60));
        jPanel2.add(jlblimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 140, 90));

        jtfproducto.setText("* * *");
        jPanel2.add(jtfproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 550, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 880, 180));

        jbtnaceptar.setBackground(new java.awt.Color(77, 161, 227));
        jbtnaceptar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jbtnaceptar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnaceptar.setText("Guardar");
        jbtnaceptar.setBorder(null);
        jbtnaceptar.setBorderPainted(false);
        jbtnaceptar.setContentAreaFilled(false);
        jbtnaceptar.setEnabled(false);
        jbtnaceptar.setOpaque(true);
        jbtnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnaceptarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 190, 40));

        jLabel8.setText("Productos:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jlblmensajecarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading4.gif"))); // NOI18N
        jlblmensajecarga.setText("* * *");
        jPanel1.add(jlblmensajecarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 540, 340, 60));

        jPanel4.setBackground(new java.awt.Color(238, 238, 238));
        jPanel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel4MouseDragged(evt);
            }
        });
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel6.setText("INGRESO DE MERMAS");

        jbtnsalir.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jbtnsalir.setBorder(null);
        jbtnsalir.setBorderPainted(false);
        jbtnsalir.setContentAreaFilled(false);
        jbtnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 640, Short.MAX_VALUE)
                .addComponent(jbtnsalir)
                .addGap(56, 56, 56))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jbtnsalir))
                .addGap(15, 15, 15))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        jbtnretirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minus20x20.png"))); // NOI18N
        jbtnretirar.setText("Retirar");
        jbtnretirar.setBorderPainted(false);
        jbtnretirar.setContentAreaFilled(false);
        jbtnretirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnretirarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnretirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyReleased
        // TODO add your handling code here:
           String codigo= jtfcodigo.getText().replaceAll("\\s", "");
        if(evt.getKeyCode()==10){
            producto=daoproducto.buscarproducto("CODIGO", 0,sucursalsingleton.getId(), codigo);
           // jtfcodigo.setText(producto.getCodigo());
//            jtfstock.setValue((producto.getCantidad()));
            jtfproducto.setText(producto.getDescripcion());
            
            /// mostrar imagen ///
        ImageIcon imageIcon = new ImageIcon(producto.getFoto());
        //ImageIcon imageUser = imageIcon;
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(jlblimagen.getWidth(), jlblimagen.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(newimg);
        jlblimagen.setIcon(imageIcon);   
        //////////////////////////////////////////////
        
        
        }
        validaagregar();
      
//        if(evt.getKeyCode()==112){
//           
//        }
        
    }//GEN-LAST:event_jtfcodigoKeyReleased

    private void jbtnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnagregarActionPerformed
        // TODO add your handling code here:
       
//        long iddet=0;
//        boolean valida=false;
       
          /// INSERT MERMA
//            merma.setFecha(new java.sql.Date(jdpfecha.getDate().getTime()));
//            merma.setMotivo(jtamotivo.getText());
//            merma.setId_merma(daomerma.insertar(merma));//insert
           ////// INSERT DETALLE
            
//            daodetallemerma.insertar(listdet,jlblmensajecarga,merma.getId_merma());
            
       
//        System.out.println("valida"+valida);
       double cantidad= Double.parseDouble(jtfcantidad.getText());
        if(daoproducto.validastockrequerido(producto.getIdproducto(),cantidad)==true){
            Object[] miarray = new Object[4];
             producto.setCantidad(Double.parseDouble(jtfcantidad.getText()));
             producto.setMotivo(jtamotivo.getText().toUpperCase());
             
            miarray[0]=producto.getCodigo();
            miarray[1]=producto.getDescripcion();
            miarray[2]=producto.getCantidad();
            miarray[3]=jtamotivo.getText().toUpperCase();
            
            
            
//            detallemerma.setIdproducto(producto.getIdproducto());        
            
            listdet.add(producto);
            modelo.addRow(miarray);
            
            nuevoprod();
            jbtnagregar.setEnabled(true);     
        }else {
            JOptionPane.showMessageDialog(null, "No cuenta con stock suficiente");
            jtfcantidad.requestFocus();
            jtfcantidad.selectAll();
        }
        
     
        validaaceptar();
    }//GEN-LAST:event_jbtnagregarActionPerformed

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
            
    }//GEN-LAST:event_jtablaMouseReleased

    private void jbtnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsalirActionPerformed
        // TODO add your handling code here:
         if(JOptionPane.showConfirmDialog(null, "¿Seguro de cerrar?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
           
//            if(modelo.getRowCount()>0){
             
//              DetalleMerma detmerma= new DetalleMerma();
//              int cont=0;
//             System.out.println("modelorow"+modelo.getRowCount()); 
//                for(int i=0;i<=modelo.getRowCount()-1;i++){
//                    cont++;
//                    detmerma.setIddetallemerma(Long.parseLong(modelo.getValueAt(i, 0).toString()));
//                    detmerma.setIdproducto(Long.parseLong(modelo.getValueAt(i, 1).toString()));
//                    detmerma.setCantidad(Double.parseDouble(modelo.getValueAt(i, 4).toString()));
//                    daodetallemerma.eliminar(detmerma);
////                    //modelo.removeRow(i);
////                    cont++;
////            }           
//            }
//                if(merma.getId_merma()!=0){
//                    daomerma.eliminar(merma.getId_merma());
//                
//                }
//              
//            daoproducto.devolverstockrequerido(listdet, jlblmensajecarga, this);
             this.dispose();
        }
    }//GEN-LAST:event_jbtnsalirActionPerformed

    private void jbtnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnaceptarActionPerformed
        // TODO add your handling code here:
        merma.setFecha(new java.sql.Date(jdpfecha.getDate().getTime()));
//        merma.setMotivo(jtamotivo.getText().trim().toUpperCase());
        
        if(daomerma.insertar(merma,listdet)==true){
            JOptionPane.showMessageDialog(null,"Documento guardado con exito","",JOptionPane.INFORMATION_MESSAGE);
            nuevamerma();
        }//insert
        
//        daodetallemerma.insertar(listdet, jlblmensajecarga, merma.getId_merma());
        
        
    }//GEN-LAST:event_jbtnaceptarActionPerformed

    private void jtfcodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusGained
        // TODO add your handling code here:
//        if(jtfcodigo.getText().equals("CODIGO")){
//        jtfcodigo.setText("");
//        }
    }//GEN-LAST:event_jtfcodigoFocusGained

    private void jtfcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusLost
        // TODO add your handling code here:
//        if(jtfcodigo.getText().equals("")){
//         jtfcodigo.setText("CODIGO");
//        
//        }
    }//GEN-LAST:event_jtfcodigoFocusLost

    private void jtamotivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtamotivoFocusGained
        // TODO add your handling code here:
//        if(jtamotivo.getText().equals("MOTIVO")){
//        jtamotivo.setText("");
//        
//        }
    }//GEN-LAST:event_jtamotivoFocusGained

    private void jtamotivoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtamotivoFocusLost
        // TODO add your handling code here:
//        if(jtamotivo.getText().equals("")){
//        jtamotivo.setText("MOTIVO");
//               
//        }
    }//GEN-LAST:event_jtamotivoFocusLost

    private void jdpfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdpfechaActionPerformed
        // TODO add your handling code here:
        validaaceptar();
   
    }//GEN-LAST:event_jdpfechaActionPerformed

    private void jtamotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtamotivoKeyReleased
        // TODO add your handling code here:
         validaagregar();
        
    }//GEN-LAST:event_jtamotivoKeyReleased

    private void jtamotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtamotivoKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayusTA(jtamotivo);
    }//GEN-LAST:event_jtamotivoKeyTyped

    private void jtfcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcantidadKeyReleased
        // TODO add your handling code here:
        validaagregar();
    }//GEN-LAST:event_jtfcantidadKeyReleased

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        // TODO add your handling code here:
          posy=evt.getY();
        posx=evt.getX();
 
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel4MouseDragged

    private void jbtnbuscarprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarprodActionPerformed
        // TODO add your handling code here:
         JDBuscarProductoVenta buscarproducto = new JDBuscarProductoVenta(new java.awt.Frame(),
                    isVisible(),this);
            buscarproducto.setVisible(true);     
    }//GEN-LAST:event_jbtnbuscarprodActionPerformed

    private void jbtnretirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnretirarActionPerformed
        // TODO add your handling code here:
           int index= jtabla.getSelectedRow();
        if(index >=0){
           
            if (JOptionPane.showConfirmDialog(null,"¿Seguro de retirar el producto?","",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
//                        DetalleMerma detmerma= new DetalleMerma();
//
//                        detmerma.setIddetallemerma(Long.parseLong(modelo.getValueAt(index, 0).toString()));
//                        detmerma.setIdproducto(Long.parseLong(modelo.getValueAt(index, 1).toString()));
//                        detmerma.setCantidad(Double.parseDouble(modelo.getValueAt(index, 4).toString()));
//                       Producto prod;
//                       prod=listdet.get(index);
//                       daoproducto.devolver(prod, jlblmensajecarga);

               listdet.remove(index);
               modelo.removeRow(index);

            }
            validaaceptar();
        }    
    }//GEN-LAST:event_jbtnretirarActionPerformed

    private void jtablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtablaKeyPressed

    private void jtablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtablaKeyReleased

    private void jtablaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtablaFocusLost
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(null, "perdio foco");
    }//GEN-LAST:event_jtablaFocusLost

    private void jtablaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtablaFocusGained
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(null, "gano foco");
    }//GEN-LAST:event_jtablaFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnaceptar;
    private javax.swing.JButton jbtnagregar;
    private javax.swing.JButton jbtnbuscarprod;
    private javax.swing.JButton jbtnretirar;
    private javax.swing.JButton jbtnsalir;
    private org.jdesktop.swingx.JXDatePicker jdpfecha;
    private javax.swing.JLabel jlblimagen;
    private javax.swing.JLabel jlblmensajecarga;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextArea jtamotivo;
    private javax.swing.JTextField jtfcantidad;
    private javax.swing.JTextField jtfcodigo;
    private javax.swing.JLabel jtfproducto;
    // End of variables declaration//GEN-END:variables
}
