/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;
import ClasesGlobales.CifradoMD5;
import ClasesGlobales.Mayusculas;
import DAO.UsuariosDAO;

import Pojos.Empleado;
import Pojos.Usuarios;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author info2017
 */
public class JIFUsuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFUsuarios
     */
   Usuarios usuario= new Usuarios();
   UsuariosDAO daousuario= new UsuariosDAO();
   Empleado empleado = new Empleado();
   
   boolean editar= false;
   List<Usuarios> listuser;
   ////     PRIVILEGIOS //////////////////////
//   boolean cliente=false,proveedor=false,apertura=false,consultarrepara=false,consultarventa=false,
//           debe=false,ckempleado=false,familia=false,haber=false,ingreso=false,inventario=false,
//           movimientos=false,nueva=false,pendientes=false,productos=false,user=false,vender=false;

   //////////////////////////////////////////
   
   Mayusculas mayus= new Mayusculas();
   int posx;
    int posy;
    public JIFUsuarios() {
        initComponents();
       
        mostrar("");
      
        bloquearjbtn(true, false, false, false, false, true,false,false);
        bloquearjtf(false, false, false, false);
        bloquearckec(false);
    }
    
    

    
    
    
    
    
    public void setuser(Usuarios user){
    this.usuario= user;
    
    }
    public void setempleado (Empleado empleado){
    this.empleado=empleado;
    jtfnombre.setText(empleado.getNombre());
    jtfrut.setText(empleado.getRut());
    jtfusuario.setText(empleado.getRut());
    }
    public void bloquearjtf(boolean user, boolean clave,boolean nombre,boolean rut){
    jtfusuario.setEnabled(user);
    jtfclave.setEnabled(clave);
    jtfnombre.setEnabled(nombre);
    jtfrut.setEnabled(rut);
    }
    public void bloquearjbtn(boolean nuevo,boolean editar,boolean guardar,boolean eliminar,boolean cancelar,
    boolean salir,boolean empleado,boolean clave ){
    jbtnnew.setEnabled(nuevo);
    jbtneditar.setEnabled(editar);
    jbtnguardar.setEnabled(guardar);
    jbtneliminar.setEnabled(eliminar);
    jbtncancelar.setEnabled(cancelar);
//    jbtnsalir.setEnabled(salir);
    
    jbtnbuscarempleado.setEnabled(empleado);
    jbtncambiarclave.setEnabled(clave);
    
    }
    public void bloquearckec(boolean ban){
        jcktodos.setEnabled(ban);
        jckcliente.setEnabled(ban);
         jckproveedor.setEnabled(ban);
         jckapertura.setEnabled(ban);
         jckconsultarrepara.setEnabled(ban);
         jckconsultarventa.setEnabled(ban);
         jckdebe.setEnabled(ban);
         jckempleado.setEnabled(ban);
         jckfamilia.setEnabled(ban);
         jckhaber.setEnabled(ban);
         jckingreso.setEnabled(ban);
         jckinventario.setEnabled(ban);
         jckmovimientos.setEnabled(ban);
         jcknueva.setEnabled(ban);
         jckpendientes.setEnabled(ban);
         jckproductos.setEnabled(ban);
         jckuser.setEnabled(ban);
         jckvender.setEnabled(ban);
         jckenproceso.setEnabled(ban);
         
         jckprodpendientes.setEnabled(ban);
         jckmermas.setEnabled(ban);
         jckconsulmerma.setEnabled(ban);
         jcksucursalida.setEnabled(ban);
         jcksucurentrada.setEnabled(ban);
         jcksucurconsultar.setEnabled(ban);
         jckweb.setEnabled(ban);
    }
     public void limpiarjtf(){
    jtfrut.setText("R.U.T");
    jtfnombre.setText("NOMBRE Y APELLIDOS");
    jtfusuario.setText("USUARIO");
    jtfclave.setText("");
 
    }
     public void validaguardar(){
     
         if (jtfusuario.getText().trim().length() != 0 && usuario.getIdempleado() != 0 && 
                new String( jtfclave.getPassword()).length() != 0){
          jbtnguardar.setEnabled(true);
         }
        
     
     }
     public void seleccionartodo(boolean ban){
         jcktodos.setSelected(ban);
         jckcliente.setSelected(ban);
         jckproveedor.setSelected(ban);
         jckapertura.setSelected(ban);
         jckconsultarrepara.setSelected(ban);
         jckconsultarventa.setSelected(ban);
         jckdebe.setSelected(ban);
         jckempleado.setSelected(ban);
         jckfamilia.setSelected(ban);
         jckhaber.setSelected(ban);
         jckingreso.setSelected(ban);
         jckinventario.setSelected(ban);
         jckmovimientos.setSelected(ban);
         jcknueva.setSelected(ban);
         jckpendientes.setSelected(ban);
         jckproductos.setSelected(ban);
         jckuser.setSelected(ban);
         jckvender.setSelected(ban);
         jckenproceso.setSelected(ban);
         
         jckprodpendientes.setSelected(ban);
         jckmermas.setSelected(ban);
         jckconsulmerma.setSelected(ban);
         jcksucursalida.setSelected(ban);
         jcksucurentrada.setSelected(ban);
         jcksucurconsultar.setSelected(ban);
         jckweb.setSelected(ban);
     
     }
     public void ver(){
//         long id = Long.parseLong(jtabla.getValueAt(jtabla.getSelectedRow(), 0).toString());
//        String nombre = jtabla.getValueAt(jtabla.getSelectedRow(), 2).toString();
//        String rut =jtabla.getValueAt(jtabla.getSelectedRow(), 1).toString();
         
//        usuario = daousuario.buscar("ID", id,jtfnombre,jtfrut);
        usuario = listuser.get(jtabla.getSelectedRow());
        jtfnombre.setText(usuario.getNombreempleado());
        jtfrut.setText(usuario.getRutempleado());
        
        jtfusuario.setText(usuario.getUsuario());
        jtfclave.setText(usuario.getClave());
//        System.out.println("clave"+CifradoMD5.Cifrado(usuario.getClave()));
        jckcliente.setSelected(usuario.isCliente());
         jckproveedor.setSelected(usuario.isProveedor());
         jckapertura.setSelected(usuario.isApertura());
         jckconsultarrepara.setSelected(usuario.isConsultarrepara());
         jckconsultarventa.setSelected(usuario.isConsultarventa());
         jckdebe.setSelected(usuario.isDebe());
         jckempleado.setSelected(usuario.isCkempleado());
         jckfamilia.setSelected(usuario.isFamilia());
         jckhaber.setSelected(usuario.isHaber());
         jckingreso.setSelected(usuario.isIngreso());
         jckinventario.setSelected(usuario.isInventario());
         jckmovimientos.setSelected(usuario.isMovimientos());
         jcknueva.setSelected(usuario.isNueva());
         jckpendientes.setSelected(usuario.isPendientes());
         jckproductos.setSelected(usuario.isProductos());
         jckuser.setSelected(usuario.isUser());
         jckvender.setSelected(usuario.isVender());
         jckenproceso.setSelected(usuario.isExtornar());
        
         jckprodpendientes.setSelected(usuario.isProdpendientes());
         jckmermas.setSelected(usuario.isMermas());
         jckconsulmerma.setSelected(usuario.isConsulmermas());
         jcksucursalida.setSelected(usuario.isSucursalida());
         jcksucurentrada.setSelected(usuario.isSucurentrada());
         jcksucurconsultar.setSelected(usuario.isConsultasucur());
         jckweb.setSelected(usuario.isWeb());
        bloquearjbtn(true, true, false, true, false, true,false,false);
        bloquearckec(false);
        bloquearjtf(false, false, false,false);
     
     
     
     }
     public synchronized void sensitiva(String cadena){
        jlblimagencarga.setVisible(true);
        jlblletracarga.setVisible(true);
        
            
         
        listuser=daousuario.busquedasensitiva(jtabla, cadena);
        
           
      if(jtabla.getSelectedRow()>=0){
            ver();
        }else{ 
             limpiarjtf();
             bloquearjtf(false, false,false, false);
             bloquearjbtn(true, false, false, false, false, true,false,false);
             seleccionartodo(false);
        
        }
      jlblimagencarga.setVisible(false);
      jlblletracarga.setVisible(false);
    
    }
     
    public void mostrar(String cadena){
        
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            sensitiva(cadena);
          
        }
        };

        Thread T = new Thread(runnable);
        T.start();
    
    
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
        jlblimagencarga = new javax.swing.JLabel();
        jlblletracarga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla = new javax.swing.JTable();
        jbtncancelar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtnguardar = new javax.swing.JButton();
        jbtnnew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtfrut = new javax.swing.JTextField();
        jtfnombre = new javax.swing.JTextField();
        jtfusuario = new javax.swing.JTextField();
        jtfclave = new javax.swing.JPasswordField();
        jbtncambiarclave = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jcktodos = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jckcliente = new javax.swing.JCheckBox();
        jckproveedor = new javax.swing.JCheckBox();
        jckempleado = new javax.swing.JCheckBox();
        jckuser = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jckingreso = new javax.swing.JCheckBox();
        jckproductos = new javax.swing.JCheckBox();
        jckinventario = new javax.swing.JCheckBox();
        jckfamilia = new javax.swing.JCheckBox();
        jckprodpendientes = new javax.swing.JCheckBox();
        jckmermas = new javax.swing.JCheckBox();
        jckconsulmerma = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jckvender = new javax.swing.JCheckBox();
        jckconsultarventa = new javax.swing.JCheckBox();
        jckenproceso = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jckapertura = new javax.swing.JCheckBox();
        jckmovimientos = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jcknueva = new javax.swing.JCheckBox();
        jckconsultarrepara = new javax.swing.JCheckBox();
        jckpendientes = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jckdebe = new javax.swing.JCheckBox();
        jckhaber = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jcksucursalida = new javax.swing.JCheckBox();
        jcksucurentrada = new javax.swing.JCheckBox();
        jcksucurconsultar = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jckweb = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jbtnbuscarempleado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtfbuscarnombre = new javax.swing.JTextField();
        jbtneditar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblimagencarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblimagencarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel1.add(jlblimagencarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 250, 190));

        jlblletracarga.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblletracarga.setForeground(new java.awt.Color(0, 0, 0));
        jlblletracarga.setText("Cargando Registros ...");
        jPanel1.add(jlblletracarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

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
        jtabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtabla.getTableHeader().setReorderingAllowed(false);
        jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaMouseReleased(evt);
            }
        });
        jtabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 111, 438, 500));

        jbtncancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel20x20.png"))); // NOI18N
        jbtncancelar.setText("Cancelar");
        jbtncancelar.setBorder(null);
        jbtncancelar.setBorderPainted(false);
        jbtncancelar.setContentAreaFilled(false);
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 70, 20));

        jbtneliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete20x20.png"))); // NOI18N
        jbtneliminar.setText("Eliminar");
        jbtneliminar.setBorder(null);
        jbtneliminar.setBorderPainted(false);
        jbtneliminar.setContentAreaFilled(false);
        jbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 60, 20));

        jbtnguardar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save20x20.png"))); // NOI18N
        jbtnguardar.setText("Guardar");
        jbtnguardar.setBorder(null);
        jbtnguardar.setBorderPainted(false);
        jbtnguardar.setContentAreaFilled(false);
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 70, 20));

        jbtnnew.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnnew.setText("Nuevo");
        jbtnnew.setBorder(null);
        jbtnnew.setBorderPainted(false);
        jbtnnew.setContentAreaFilled(false);
        jbtnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnewActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 60, 20));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfrut.setText("R.U.T");
        jtfrut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfrutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfrutFocusLost(evt);
            }
        });
        jtfrut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfrutKeyReleased(evt);
            }
        });
        jPanel2.add(jtfrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 139, -1));

        jtfnombre.setText("NOMBRE Y APELLIDOS");
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
        });
        jPanel2.add(jtfnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 490, -1));

        jtfusuario.setText("USUARIO");
        jtfusuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfusuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfusuarioFocusLost(evt);
            }
        });
        jtfusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfusuarioKeyReleased(evt);
            }
        });
        jPanel2.add(jtfusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 190, -1));

        jtfclave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfclaveKeyReleased(evt);
            }
        });
        jPanel2.add(jtfclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 240, -1));

        jbtncambiarclave.setBackground(new java.awt.Color(255, 255, 255));
        jbtncambiarclave.setText("CAMBIAR CLAVE");
        jbtncambiarclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncambiarclaveActionPerformed(evt);
            }
        });
        jPanel2.add(jbtncambiarclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 120, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcktodos.setBackground(new java.awt.Color(255, 255, 255));
        jcktodos.setText("SELECCIONAR TODOS");
        jcktodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcktodosActionPerformed(evt);
            }
        });
        jPanel3.add(jcktodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 9, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento registro"));

        jckcliente.setBackground(new java.awt.Color(255, 255, 255));
        jckcliente.setText("Cliente");
        jckcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckclienteActionPerformed(evt);
            }
        });

        jckproveedor.setBackground(new java.awt.Color(255, 255, 255));
        jckproveedor.setText("Proveedor");
        jckproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckproveedorActionPerformed(evt);
            }
        });

        jckempleado.setBackground(new java.awt.Color(255, 255, 255));
        jckempleado.setText("Empleado");
        jckempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckempleadoActionPerformed(evt);
            }
        });

        jckuser.setBackground(new java.awt.Color(255, 255, 255));
        jckuser.setText("Usuario");
        jckuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckuserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jckcliente)
                .addGap(10, 10, 10)
                .addComponent(jckproveedor)
                .addGap(10, 10, 10)
                .addComponent(jckempleado)
                .addGap(18, 18, 18)
                .addComponent(jckuser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckcliente)
                    .addComponent(jckproveedor)
                    .addComponent(jckempleado)
                    .addComponent(jckuser))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 370, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jckingreso.setBackground(new java.awt.Color(255, 255, 255));
        jckingreso.setText("Ingreso");
        jckingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckingresoActionPerformed(evt);
            }
        });

        jckproductos.setBackground(new java.awt.Color(255, 255, 255));
        jckproductos.setText("Productos");
        jckproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckproductosActionPerformed(evt);
            }
        });

        jckinventario.setBackground(new java.awt.Color(255, 255, 255));
        jckinventario.setText("Inventario");
        jckinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckinventarioActionPerformed(evt);
            }
        });

        jckfamilia.setBackground(new java.awt.Color(255, 255, 255));
        jckfamilia.setText("Familias");
        jckfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckfamiliaActionPerformed(evt);
            }
        });

        jckprodpendientes.setBackground(new java.awt.Color(255, 255, 255));
        jckprodpendientes.setText("Pendientes");
        jckprodpendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckprodpendientesActionPerformed(evt);
            }
        });

        jckmermas.setBackground(new java.awt.Color(255, 255, 255));
        jckmermas.setText("Mermas");
        jckmermas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckmermasActionPerformed(evt);
            }
        });

        jckconsulmerma.setBackground(new java.awt.Color(255, 255, 255));
        jckconsulmerma.setText("Consul. Mermas");
        jckconsulmerma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckconsulmermaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jckingreso)
                        .addGap(18, 18, 18)
                        .addComponent(jckproductos))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jckprodpendientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jckmermas)))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jckinventario)
                        .addGap(18, 18, 18)
                        .addComponent(jckfamilia))
                    .addComponent(jckconsulmerma))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckingreso)
                    .addComponent(jckproductos)
                    .addComponent(jckinventario)
                    .addComponent(jckfamilia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckprodpendientes)
                    .addComponent(jckmermas)
                    .addComponent(jckconsulmerma))
                .addContainerGap())
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 340, 90));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta"));

        jckvender.setBackground(new java.awt.Color(255, 255, 255));
        jckvender.setText("Vender");
        jckvender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckvenderActionPerformed(evt);
            }
        });

        jckconsultarventa.setBackground(new java.awt.Color(255, 255, 255));
        jckconsultarventa.setText("Consultar");
        jckconsultarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckconsultarventaActionPerformed(evt);
            }
        });

        jckenproceso.setBackground(new java.awt.Color(255, 255, 255));
        jckenproceso.setText("En Proceso");
        jckenproceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckenprocesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jckvender)
                        .addGap(10, 10, 10)
                        .addComponent(jckconsultarventa))
                    .addComponent(jckenproceso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckvender)
                    .addComponent(jckconsultarventa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckenproceso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 180, 80));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Caja"));

        jckapertura.setBackground(new java.awt.Color(255, 255, 255));
        jckapertura.setText("Apertura");
        jckapertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckaperturaActionPerformed(evt);
            }
        });

        jckmovimientos.setBackground(new java.awt.Color(255, 255, 255));
        jckmovimientos.setText("Movimientos");
        jckmovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckmovimientosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jckapertura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jckmovimientos)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckapertura)
                    .addComponent(jckmovimientos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 190, 60));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Reparación"));

        jcknueva.setBackground(new java.awt.Color(255, 255, 255));
        jcknueva.setText("Nueva");
        jcknueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcknuevaActionPerformed(evt);
            }
        });

        jckconsultarrepara.setBackground(new java.awt.Color(255, 255, 255));
        jckconsultarrepara.setText("Consultar");
        jckconsultarrepara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckconsultarreparaActionPerformed(evt);
            }
        });

        jckpendientes.setBackground(new java.awt.Color(255, 255, 255));
        jckpendientes.setText("Pendientes");
        jckpendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckpendientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcknueva)
                .addGap(35, 35, 35)
                .addComponent(jckconsultarrepara)
                .addGap(18, 18, 18)
                .addComponent(jckpendientes)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcknueva)
                    .addComponent(jckconsultarrepara)
                    .addComponent(jckpendientes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 340, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Cotización"));

        jckdebe.setBackground(new java.awt.Color(255, 255, 255));
        jckdebe.setText("Nueva Cotización");
        jckdebe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckdebeActionPerformed(evt);
            }
        });

        jckhaber.setBackground(new java.awt.Color(255, 255, 255));
        jckhaber.setText("Cotizaciones");
        jckhaber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckhaberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jckdebe)
                    .addComponent(jckhaber))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jckdebe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jckhaber)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 190, 70));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sucursal"));

        jcksucursalida.setBackground(new java.awt.Color(255, 255, 255));
        jcksucursalida.setText("Salida");
        jcksucursalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcksucursalidaActionPerformed(evt);
            }
        });

        jcksucurentrada.setBackground(new java.awt.Color(255, 255, 255));
        jcksucurentrada.setText("Entrada");
        jcksucurentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcksucurentradaActionPerformed(evt);
            }
        });

        jcksucurconsultar.setBackground(new java.awt.Color(255, 255, 255));
        jcksucurconsultar.setText("Consultar");
        jcksucurconsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcksucurconsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jcksucursalida)
                .addGap(18, 18, 18)
                .addComponent(jcksucurentrada)
                .addGap(10, 10, 10)
                .addComponent(jcksucurconsultar)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcksucursalida)
                    .addComponent(jcksucurentrada)
                    .addComponent(jcksucurconsultar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 340, 70));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Acceso web"));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        jckweb.setBackground(new java.awt.Color(255, 255, 255));
        jckweb.setText("Web");
        jckweb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckwebActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jckweb)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jckweb)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 180, 50));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 740, 280));

        jLabel2.setText("PRIVILEGIOS ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jbtnbuscarempleado.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarempleado.setText("...");
        jbtnbuscarempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarempleadoActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnbuscarempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        jLabel3.setText("USUARIO:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 60, 20));

        jLabel4.setText("CLAVE:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 60, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 111, 760, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel1.setText("Buscar:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, -1, -1));

        jtfbuscarnombre.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfbuscarnombre.setText("NOMBRES Y APELLIDOS");
        jtfbuscarnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarnombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarnombreFocusLost(evt);
            }
        });
        jtfbuscarnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfbuscarnombreActionPerformed(evt);
            }
        });
        jtfbuscarnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscarnombreKeyTyped(evt);
            }
        });
        jPanel1.add(jtfbuscarnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 83, 480, -1));

        jbtneditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit20x20.png"))); // NOI18N
        jbtneditar.setText("Editar");
        jbtneditar.setBorder(null);
        jbtneditar.setBorderPainted(false);
        jbtneditar.setContentAreaFilled(false);
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 60, 20));

        jPanel11.setBackground(new java.awt.Color(220, 151, 96));
        jPanel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel11MouseDragged(evt);
            }
        });
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel11MousePressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("REGISTRO DE USUARIOS");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel15.setToolTipText("Cerrar");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel15MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 930, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(73, 73, 73))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1228, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaMouseReleased
        // TODO add your handling code here:
     
     
//        long id = Long.parseLong(jtabla.getValueAt(jtabla.getSelectedRow(), 0).toString());
//        String nombre = jtabla.getValueAt(jtabla.getSelectedRow(), 2).toString();
//        String rut =jtabla.getValueAt(jtabla.getSelectedRow(), 1).toString();
//        usuario = daousuario.buscar("ID", id,jtfnombre,jtfrut);
//       
//        jtfusuario.setText(usuario.getUsuario());
//        jtfclave.setText(usuario.getClave());
////        System.out.println("clave"+CifradoMD5.Cifrado(usuario.getClave()));
//        jckcliente.setSelected(usuario.isCliente());
//         jckproveedor.setSelected(usuario.isProveedor());
//         jckapertura.setSelected(usuario.isApertura());
//         jckconsultarrepara.setSelected(usuario.isConsultarrepara());
//         jckconsultarventa.setSelected(usuario.isConsultarventa());
//         jckdebe.setSelected(usuario.isDebe());
//         jckempleado.setSelected(usuario.isCkempleado());
//         jckfamilia.setSelected(usuario.isFamilia());
//         jckhaber.setSelected(usuario.isHaber());
//         jckingreso.setSelected(usuario.isIngreso());
//         jckinventario.setSelected(usuario.isInventario());
//         jckmovimientos.setSelected(usuario.isMovimientos());
//         jcknueva.setSelected(usuario.isNueva());
//         jckpendientes.setSelected(usuario.isPendientes());
//         jckproductos.setSelected(usuario.isProductos());
//         jckuser.setSelected(usuario.isUser());
//         jckvender.setSelected(usuario.isVender());
//         jckenproceso.setSelected(usuario.isExtornar());
//        
//         jckprodpendientes.setSelected(usuario.isProdpendientes());
//         jckmermas.setSelected(usuario.isMermas());
//         jckconsulmerma.setSelected(usuario.isConsulmermas());
//         jcksucursalida.setSelected(usuario.isSucursalida());
//         jcksucurentrada.setSelected(usuario.isSucurentrada());
//         jcksucurconsultar.setSelected(usuario.isConsultasucur());
//        bloquearjbtn(true, true, false, true, false, true,false,false);
//        bloquearckec(false);
//        bloquearjtf(false, false, false,false);
        ver();
    }//GEN-LAST:event_jtablaMouseReleased

    private void jtablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaKeyReleased
        // TODO add your handling code here:
        ver();
    }//GEN-LAST:event_jtablaKeyReleased

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
        int index = jtabla.getSelectedRow();
        if(index>=0){
            jtablaMouseReleased(null);
        } else {
            limpiarjtf();
        }
       bloquearjbtn(true, false, false, false, false, true,false,false);
        bloquearjtf(false, false, false, false);
        bloquearckec(false);
        //int index= jtablaalumno.getSelectedRow();

        //        limpiarjtext();
        //        habilitarjtext(false);
        //        jbtnguardar.setEnabled(false);
        //        jbtncancelar.setEnabled(false);
        //        jbtneditar.setEnabled(false);
        //
        //        guardar=true;
        //
        //        int fila=jtablaalumno.getSelectedRow();
        //        if(fila>=0){
            //            // Integer idP=Integer.parseInt(jtablaalumno.getValueAt(index, 0).toString());
            //            buscar();
            //        }
    }//GEN-LAST:event_jbtncancelarActionPerformed

    private void jbtneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneliminarActionPerformed
        // TODO add your handling code here:
        //        Integer index= jtablaalumno.getSelectedRow();
        //        Integer id = Integer.parseInt(jtablaalumno.getValueAt(index, 0).toString());
        //        if (index<0){
            //            JOptionPane.showMessageDialog(this, "SELECCIONE ALUMNO A ELIMINAR EN LA TABLA");
            //        }else {
            //            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR AL ALUMNO","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                //                eliminar(id);
                //                mostrar();
                //                limpiarjtext();
                //                habilitarjtext(false);
                //
                //            }
            //
            //        }
        int index= jtabla.getSelectedRow();
        long id = Long.parseLong(jtabla.getValueAt(index, 0).toString());
        if (index<0){
            JOptionPane.showMessageDialog(this, "SELECCIONE USUARIO A ELIMINAR EN LA TABLA");
        }else {
            if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL USUARIO","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                daousuario.eliminar(id);
                daousuario.mostrar(jtabla);
      
                bloquearjbtn(true, false, false, false, false, true,false,false);
               
            }

        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
        
          
        
        if (usuario.getIdempleado()!= 0){
//            usuario.setClave(CifradoMD5.Cifrado(new String(jtfclave.getPassword())));
                    usuario.setUsuario(jtfusuario.getText());
//                    usuario.setClave(new String(jtfclave.getPassword()));
                    
                    usuario.setCliente(jckcliente.isSelected());
                    System.out.println("cliente" +jckcliente.isSelected());
                    usuario.setProveedor(jckproveedor.isSelected());
                    usuario.setApertura(jckapertura.isSelected());
                    usuario.setConsultarrepara(jckconsultarrepara.isSelected());
                    usuario.setConsultarventa(jckconsultarventa.isSelected());
                    usuario.setDebe(jckdebe.isSelected());
                    usuario.setCkempleado(jckempleado.isSelected());
                    usuario.setFamilia(jckfamilia.isSelected());
                    usuario.setHaber(jckhaber.isSelected());
                    usuario.setIngreso(jckingreso.isSelected());
                    usuario.setInventario(jckinventario.isSelected());
                    usuario.setMovimientos(jckmovimientos.isSelected());
                    usuario.setNueva(jcknueva.isSelected());
                    usuario.setPendientes(jckpendientes.isSelected());
                    usuario.setProductos(jckproductos.isSelected());
                    usuario.setUser(jckuser.isSelected());
                    usuario.setVender(jckvender.isSelected());
                    usuario.setExtornar(jckenproceso.isSelected());
                    
                    usuario.setProdpendientes(jckprodpendientes.isSelected());
                    usuario.setMermas(jckmermas.isSelected());
                    usuario.setConsulmermas(jckconsulmerma.isSelected());
                    usuario.setSucursalida(jcksucursalida.isSelected());
                    usuario.setSucurentrada(jcksucurentrada.isSelected());
                    usuario.setConsultasucur(jcksucurconsultar.isSelected());
                    usuario.setWeb(jckweb.isSelected());

                if(editar==false){
                    boolean validaduplicado=daousuario.duplicado(usuario.getIdempleado());
                    if(validaduplicado==true){
                    usuario.setClave(CifradoMD5.encriptaEnMD5(new String(jtfclave.getPassword())));
                    System.out.println("idempleado2"+usuario.getIdempleado());
                    daousuario.insertar(usuario);
                    }else {
                    JOptionPane.showMessageDialog(null, "EL EMPLEADO "+jtfnombre.getText()+" YA TIENE UNA CUENTA DE INGRESO AL SISTEMA","SISTEMA",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }

                else{
//                    usuario.setUsuario(jtfusuario.getText());
                    daousuario.editar(usuario);
                }

            }else {
                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS","ERROR",JOptionPane.ERROR_MESSAGE);
            }

       if(jtfbuscarnombre.getText().equals("NOMBRES Y APELLIDOS"))
            mostrar("");
       else 
           mostrar(jtfbuscarnombre.getText().trim().toUpperCase());
//        bloquearjbtn(true, false, false, false, false, true,false,false);
//        bloquearjtf(false, false, false, false);
//        limpiarjtf();
//        bloquearckec(false);
//        seleccionartodo(false);

        //        String RUT = jtfrut.getText();
        //        Integer index= jtablaalumno.getSelectedRow();
        //
        //        if (validarrut(RUT)==true){
            //            if (guardar==true){ //GUARDO
                //                if (validarutduplicado("RUT", RUT,null)==true){ // BUSCA TODOS LOS RUT SI  NO ENCUENTRA CONCIDENCIA CON EL RUT INGRESADO (TRUE)
                    //                    // Limpiartabla();
                    //                    ///insert
                    //                    System.out.println("ruta"+rutaimagen);
                    //                    insertar();
                    //                    mostrar();
                    //                    jbtnguardar.setEnabled(false);
                    //                    jbtncancelar.setEnabled(false);
                    //
                    //                    habilitarjtext(false);
                    //                    limpiarjtext();
                    //                    //                jtfpuesto.setBackground(Color.WHITE);
                    //                    //            }
                //                //        else {
                    //                    //                JOptionPane.showMessageDialog(this,"EL PUESTO "+puesto+" YA SE ENCUENTRA REGISTRADO");
                    //                    //                jtfpuesto.requestFocus();
                    //                    //                jtfpuesto.setBackground(Color.RED);
                    //                    //            }
                //            } else {
                //                JOptionPane.showMessageDialog(this,"EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO");
                //            }
            //        }else {
            //            //EDITO
            //            //              System.out.println("editar");
            //            if (validarutduplicado("RUTDUPLICADO", RUT, id)==true){// RUC DUPLICADO: BUSCA LOS RUC EN BD MENOS EL RUC QUE QUEREMOS EDITAR
                //                //            actualizarrubrospuesto(id,"PUESTO");
                //                Integer idS = Integer.parseInt(jtablaalumno.getValueAt(index,0).toString());
                //                editar(idS,fotoB);
                //                //Limpiartabla();
                //                mostrar();
                //                jbtnguardar.setEnabled(false);
                //                jbtncancelar.setEnabled(false);
                //                habilitarjtext(false);
                //                limpiarjtext();
                //            }else {
                //                JOptionPane.showMessageDialog(this,"EL RUT INGRESADO YA SE ENCUENTRA REGISTRADO");
                //            }
            //
            //        }
        //
        //        guardar=true;
        //        } else {
        //
        //            JOptionPane.showMessageDialog(this,"RUT INVALIDO");
        //            jtfrut.requestFocus();
        //        }
    }//GEN-LAST:event_jbtnguardarActionPerformed

    private void jbtnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnnewActionPerformed
        // TODO add your handling code here:
        bloquearjtf(false, true,false,false);
        bloquearjbtn(true, false, false, false, true, true,true,false);
        bloquearckec(true);
        limpiarjtf();
        seleccionartodo(false);
        editar=false;
        //        jbtncancelar.setEnabled(true);
        //
        //        jbtneditar.setEnabled(false);
        //        guardar=false;
    }//GEN-LAST:event_jbtnnewActionPerformed

    private void jtfrutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusGained
        // TODO add your handling code here:
        if (jtfrut.getText().equals("R.U.T")){
            jtfrut.setText("");
        }
    }//GEN-LAST:event_jtfrutFocusGained

    private void jtfrutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfrutFocusLost
        // TODO add your handling code here:
        if (jtfrut.getText().equals("")){
            jtfrut.setText("R.U.T");
        }
    }//GEN-LAST:event_jtfrutFocusLost

    private void jtfrutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfrutKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfrutKeyReleased

    private void jtfnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusGained
        // TODO add your handling code here:
        if (jtfnombre.getText().equals("NOMBRES Y APELLIDOS")){
            jtfnombre.setText("");
        }
    }//GEN-LAST:event_jtfnombreFocusGained

    private void jtfnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfnombreFocusLost
        // TODO add your handling code here:
        if (jtfnombre.getText().equals("")){
            jtfnombre.setText("NOMBRES Y APELLIDOS");
        }
    }//GEN-LAST:event_jtfnombreFocusLost

    private void jtfnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfnombreKeyReleased
        // TODO add your handling code here:
        validaguardar();

    }//GEN-LAST:event_jtfnombreKeyReleased

    private void jtfbuscarnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarnombreFocusGained
        // TODO add your handling code here:
        if (jtfbuscarnombre.getText().equals("NOMBRES Y APELLIDOS")){
            jtfbuscarnombre.setText("");
        }
    }//GEN-LAST:event_jtfbuscarnombreFocusGained

    private void jtfbuscarnombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarnombreFocusLost
        // TODO add your handling code here:
        if (jtfbuscarnombre.getText().equals("")){
            jtfbuscarnombre.setText("NOMBRES Y APELLIDOS");
        }
    }//GEN-LAST:event_jtfbuscarnombreFocusLost

    private void jtfbuscarnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombreKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfbuscarnombreKeyReleased

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        // TODO add your handling code here:
        bloquearjtf(false, false,false,false);
        bloquearckec(true);
        editar=true;
        bloquearjbtn(true, false, false, false, true, true,false,true);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jckclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckclienteActionPerformed
        // TODO add your handling code here:
        if(jckcliente.isSelected()==false){
            jcktodos.setSelected(false);
            
        }
        validaguardar();
    }//GEN-LAST:event_jckclienteActionPerformed

    private void jcktodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcktodosActionPerformed
        // TODO add your handling code here:
        if(jcktodos.isSelected()==true){
            seleccionartodo(true);
            
        }else {
            seleccionartodo(false);
        
        }
        validaguardar();
    }//GEN-LAST:event_jcktodosActionPerformed

    private void jbtncambiarclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncambiarclaveActionPerformed
        // TODO add your handling code here:
        JDCambiarClave cambiarc= new JDCambiarClave(new java.awt.Frame(),isVisible(),usuario,this);
        cambiarc.setVisible(true);
    }//GEN-LAST:event_jbtncambiarclaveActionPerformed

    private void jbtnbuscarempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarempleadoActionPerformed
        // TODO add your handling code here:
        JDBuscarEmpleado bempleado= new JDBuscarEmpleado(new java.awt.Frame(),isVisible(),usuario,this);
        bempleado.setVisible(true);
    }//GEN-LAST:event_jbtnbuscarempleadoActionPerformed

    private void jtfusuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfusuarioFocusGained
        // TODO add your handling code here:
        if(jtfusuario.getText().equals("USUARIO")){
            jtfusuario.setText("");
        }
    }//GEN-LAST:event_jtfusuarioFocusGained

    private void jtfusuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfusuarioFocusLost
        // TODO add your handling code here:
         if(jtfusuario.getText().equals("")){
            jtfusuario.setText("USUARIO");
        }
    }//GEN-LAST:event_jtfusuarioFocusLost

    private void jtfusuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfusuarioKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfusuarioKeyReleased

    private void jtfclaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfclaveKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtfclaveKeyReleased

    private void jckproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckproveedorActionPerformed
        // TODO add your handling code here:
        if(jckproveedor.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckproveedorActionPerformed

    private void jckempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckempleadoActionPerformed
        // TODO add your handling code here:
        if(jckempleado.isSelected()==false){
            jcktodos.setSelected(false);
            
        }
        validaguardar();
    }//GEN-LAST:event_jckempleadoActionPerformed

    private void jckuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckuserActionPerformed
        // TODO add your handling code here:
        if(jckuser.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckuserActionPerformed

    private void jckingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckingresoActionPerformed
        // TODO add your handling code here:
        if(jckingreso.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckingresoActionPerformed

    private void jckproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckproductosActionPerformed
        // TODO add your handling code here:
       if(jckproductos.isSelected()==false){
            jcktodos.setSelected(false);
          
        }
       validaguardar();
    }//GEN-LAST:event_jckproductosActionPerformed

    private void jckinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckinventarioActionPerformed
        // TODO add your handling code here:
         if(jckinventario.isSelected()==false){
            jcktodos.setSelected(false);
          
        }
         validaguardar();
    }//GEN-LAST:event_jckinventarioActionPerformed

    private void jckfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckfamiliaActionPerformed
        // TODO add your handling code here:
        if(jckfamilia.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckfamiliaActionPerformed

    private void jckvenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckvenderActionPerformed
        // TODO add your handling code here:
        if(jckvender.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckvenderActionPerformed

    private void jckconsultarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckconsultarventaActionPerformed
        // TODO add your handling code here:
        if(jckconsultarventa.isSelected()==false){
            jcktodos.setSelected(false);
            
        }
        validaguardar();
    }//GEN-LAST:event_jckconsultarventaActionPerformed

    private void jckaperturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckaperturaActionPerformed
        // TODO add your handling code here:
        if(jckapertura.isSelected()==false){
            jcktodos.setSelected(false);
          
        }
        validaguardar();
    }//GEN-LAST:event_jckaperturaActionPerformed

    private void jckmovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckmovimientosActionPerformed
        // TODO add your handling code here:
        if(jckmovimientos.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckmovimientosActionPerformed

    private void jcknuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcknuevaActionPerformed
        // TODO add your handling code here:
        if(jcknueva.isSelected()==false){
            jcktodos.setSelected(false);
          
        }
        validaguardar();
    }//GEN-LAST:event_jcknuevaActionPerformed

    private void jckconsultarreparaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckconsultarreparaActionPerformed
        // TODO add your handling code here:
        if(jckconsultarrepara.isSelected()==false){
            jcktodos.setSelected(false);
            
        }
        validaguardar();
    }//GEN-LAST:event_jckconsultarreparaActionPerformed

    private void jckpendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckpendientesActionPerformed
        // TODO add your handling code here:
        if(jckpendientes.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckpendientesActionPerformed

    private void jckdebeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckdebeActionPerformed
        // TODO add your handling code here:
         if(jckdebe.isSelected()==false){
            jcktodos.setSelected(false);
            
        }
         validaguardar();
    }//GEN-LAST:event_jckdebeActionPerformed

    private void jckhaberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckhaberActionPerformed
        // TODO add your handling code here:
        if(jckhaber.isSelected()==false){
            jcktodos.setSelected(false);
          
           
        }
        validaguardar();
    }//GEN-LAST:event_jckhaberActionPerformed

    private void jckenprocesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckenprocesoActionPerformed
        // TODO add your handling code here:
        if(jckenproceso.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckenprocesoActionPerformed

    private void jtfbuscarnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarnombreKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfbuscarnombre);
    }//GEN-LAST:event_jtfbuscarnombreKeyTyped

    private void jckprodpendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckprodpendientesActionPerformed
        // TODO add your handling code here:
         if(jckprodpendientes.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckprodpendientesActionPerformed

    private void jckmermasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckmermasActionPerformed
        // TODO add your handling code here:
        if(jckmermas.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckmermasActionPerformed

    private void jckconsulmermaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckconsulmermaActionPerformed
        // TODO add your handling code here:
         if(jckconsulmerma.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jckconsulmermaActionPerformed

    private void jcksucursalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcksucursalidaActionPerformed
        // TODO add your handling code here:
        if(jcksucursalida.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jcksucursalidaActionPerformed

    private void jcksucurentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcksucurentradaActionPerformed
        // TODO add your handling code here:
         if(jcksucurentrada.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jcksucurentradaActionPerformed

    private void jcksucurconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcksucurconsultarActionPerformed
        // TODO add your handling code here:
        if(jcksucurconsultar.isSelected()==false){
            jcktodos.setSelected(false);
           
        }
        validaguardar();
    }//GEN-LAST:event_jcksucurconsultarActionPerformed

    private void jtfbuscarnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarnombreActionPerformed
        // TODO add your handling code here:
//        Runnable runnable = new Runnable() {
//
//            @Override
//            public void run() {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 String nombre=jtfbuscarnombre.getText().trim().toUpperCase();
//                sensitiva(nombre);
//            }
//        };
//        Thread T = new Thread(runnable);
//        T.start();
        mostrar(nombre);
        
    }//GEN-LAST:event_jtfbuscarnombreActionPerformed

    private void jLabel15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseReleased

    private void jPanel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MousePressed
        // TODO add your handling code here:
          posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel11MousePressed

    private void jPanel11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseDragged
        // TODO add your handling code here:
         int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel11MouseDragged

    private void jckwebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckwebActionPerformed
        // TODO add your handling code here:
           if(jckweb.isSelected()==false){
            jcktodos.setSelected(false);
          
           
        }
        validaguardar();
    }//GEN-LAST:event_jckwebActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnbuscarempleado;
    private javax.swing.JButton jbtncambiarclave;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnew;
    private javax.swing.JCheckBox jckapertura;
    private javax.swing.JCheckBox jckcliente;
    private javax.swing.JCheckBox jckconsulmerma;
    private javax.swing.JCheckBox jckconsultarrepara;
    private javax.swing.JCheckBox jckconsultarventa;
    private javax.swing.JCheckBox jckdebe;
    private javax.swing.JCheckBox jckempleado;
    private javax.swing.JCheckBox jckenproceso;
    private javax.swing.JCheckBox jckfamilia;
    private javax.swing.JCheckBox jckhaber;
    private javax.swing.JCheckBox jckingreso;
    private javax.swing.JCheckBox jckinventario;
    private javax.swing.JCheckBox jckmermas;
    private javax.swing.JCheckBox jckmovimientos;
    private javax.swing.JCheckBox jcknueva;
    private javax.swing.JCheckBox jckpendientes;
    private javax.swing.JCheckBox jckprodpendientes;
    private javax.swing.JCheckBox jckproductos;
    private javax.swing.JCheckBox jckproveedor;
    private javax.swing.JCheckBox jcksucurconsultar;
    private javax.swing.JCheckBox jcksucurentrada;
    private javax.swing.JCheckBox jcksucursalida;
    private javax.swing.JCheckBox jcktodos;
    private javax.swing.JCheckBox jckuser;
    private javax.swing.JCheckBox jckvender;
    private javax.swing.JCheckBox jckweb;
    private javax.swing.JLabel jlblimagencarga;
    private javax.swing.JLabel jlblletracarga;
    private javax.swing.JTable jtabla;
    private javax.swing.JTextField jtfbuscarnombre;
    private javax.swing.JPasswordField jtfclave;
    private javax.swing.JTextField jtfnombre;
    private javax.swing.JTextField jtfrut;
    private javax.swing.JTextField jtfusuario;
    // End of variables declaration//GEN-END:variables
}
