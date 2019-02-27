/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import ClasesGlobales.FormatoNumerico;
import ClasesGlobales.Mayusculas;
import DAO.FamiliaDAO;
import DAO.ProductoDAO;
import DAO.UnidadMedidaDAO;
import DAO.UnidadMedidaFisicaDAO;
import Pojos.Codigo_sunat;
import Pojos.Familia;
import Pojos.Producto;
import Pojos.SucursalSingleton;
import Pojos.Unidad_Medida;
import Pojos.Unidad_Medida_Fisica;
import java.awt.Color;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author info2017
 */
public class JIProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIProductos
     */
   ProductoDAO daoproducto = new ProductoDAO();
   Boolean editar;
 
   String rutaimagen=null;
   byte[]  fotoB=new byte[0];
   
   Familia familia =new Familia();
   SucursalSingleton sucursalsingleton = SucursalSingleton.getinstancia();
   Mayusculas mayus = new Mayusculas();
   List<Producto> listprod= new ArrayList<>();
    FamiliaDAO daofamilia = new FamiliaDAO();
    List<Familia> listfamilia= new ArrayList<>();
    Producto producto = new Producto();
    int posx;
    int posy;
    List<Unidad_Medida> listunidad;
    List<Unidad_Medida_Fisica> listunidadfis;
    FormatoNumerico fn = new FormatoNumerico();
    MDIMenu menu;
    Codigo_sunat  cods;
  
    public JIProductos() {
        
    }
    public JIProductos(MDIMenu menu) {
        initComponents();
        jlblcargando.setVisible(false);
        jlblcargafoto.setVisible(false);
        bloquearjtf( false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
        bloquearjbtn(true, false, false, false, false, true,false,false,false,false);
        jbtngenerarcodigo.setEnabled(false);      
        jtaobservacion.setLineWrap(true);
        
        mostrar();    
        mostrarunidadm();
        this.menu=menu;
    }
    public void mostrar() {
        Runnable runnable = new Runnable() {
            public void run() {
                    jlblcargando.setVisible(true);  
                    jlblcargandoletra.setVisible(true);
                    jcbfamilia.setEnabled(false);
                    jtfbuscarproducto.setEnabled(false);
                    long tiempoInicio = System.currentTimeMillis();
                       
                        
                    listfamilia=daofamilia.llenarcombo(jcbfamilia);
                    listprod= daoproducto.mostrarproducto(jtablaproducto,sucursalsingleton.getId());
                    
                    long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                    jlbltiempoproceso.setText("Proceso de carga "+totalTiempo + " miliseg");
                    
                    jcbfamilia.setEnabled(true);
                    jtfbuscarproducto.setEnabled(true);
                    jlblcargando.setVisible(false);
                    jlblcargandoletra.setVisible(false);
             
            }
        };
        Thread M = new Thread(runnable);
        M.start();
        

}
    public void bloquearjtf(boolean codigo, boolean descripcion,boolean observacion,boolean precio,boolean precio1
    ,boolean precio2,boolean precio3,boolean moneda,boolean cbunidventa, boolean cbunidcompra, boolean factor,boolean invmax,
    boolean  invmin, boolean loc, boolean precioc,boolean preciocunic,boolean preciocuniv,boolean precioigv1,boolean precioigv2,
    boolean precioigv3,boolean precioigv4){
        jtfcodigo.setEnabled(codigo);
        jtfdescripcion.setEnabled(descripcion);
        jtaobservacion.setEnabled(observacion);
        jtfutilidad.setEnabled(precio);
        jtfutilidad1.setEnabled(precio1);
        jtfutilidad2.setEnabled(precio2);
        jtfutilidad3.setEnabled(precio3);
        jcbunidmedida.setEnabled(moneda);
        jcbunidadventa.setEnabled(cbunidventa);
        jcbunidcompra.setEnabled(cbunidcompra);
        jtffactor.setEnabled(factor);
        jtfinventariomax.setEnabled(invmax);
        jtfinventariomin.setEnabled(invmin);
        jtflocalizacion.setEnabled(loc);
        jtfprecioc.setEnabled(precioc);
//        jtfpreciocunic.setEnabled(preciocunic);
//        jtfpreciocuniv.setEnabled(preciocuniv);
        jtfpreciof.setEnabled(precioigv1);
        jtfpreciof1.setEnabled(precioigv2);
        jtfpreciof2.setEnabled(precioigv3);
        jtfpreciof3.setEnabled(precioigv4);
        
                
        
     
    
    }
    public void bloquearjbtn(boolean nuevo,boolean editar,boolean guardar,boolean eliminar,boolean cancelar,
    boolean salir,boolean foto,boolean codbarra,boolean familia,boolean codsunat)
    {///,boolean codigo ){
    jbtnnuevo.setEnabled(nuevo);
    jbtneditar.setEnabled(editar);
    jbtnguardar.setEnabled(guardar);
    jbtneliminar.setEnabled(eliminar);
    jbtncancelar.setEnabled(cancelar);
    
    jbtnfoto.setEnabled(foto);
    jbtngeneracodigobarras.setEnabled(codbarra);
    jbtnbuscarfamilia.setEnabled(familia);
   // jbtngenerarcodigo.setEnabled(codigo);
    jbtncodigosunat.setEnabled(codsunat);
    
    }
     public void limpiarjtf(){
    jtfcodigo.setText("");
    jtfdescripcion.setText("");
    jtaobservacion.setText("");
    jtffamilia.setText("");
    jlblImageProducto.setIcon(null);
    jtfutilidad.setText("");
    jtfutilidad1.setText("");
    jtfutilidad2.setText("");
    jtfutilidad3.setText("");
    familia = new Familia();
    jcbunidadventa.setSelectedIndex(0);
    jcbunidcompra.setSelectedIndex(0);
    jtfinventariomax.setText("");
    jtfinventariomin.setText("");
    jtflocalizacion.setText("");
    jtfprecioc.setText("");
    jtfpreciocuniv.setText("");
    jlblunidcompra.setText("X "+jcbunidcompra.getSelectedItem());
    jlblunidmedv.setText("X "+jcbunidadventa.getSelectedItem());
    jtfpreciof.setText("");
    jtfpreciof1.setText("");
    jtfpreciof2.setText("");
    jtfpreciof3.setText("");
    jtfprecio.setText("");
    jtfprecio1.setText("");
    jtfprecio2.setText("");
    jtfprecio3.setText("");
    jtffactor.setText("");
    jtfcodigosunat.setText("");
    cods = null;
    }
     
     
     public void colortext(){
         String cod= jtfcodigo.getText().replaceAll("\\s","");
//            String jfamilia= jtffamilia.getText().replaceAll("\\s","");
         String desc = jtfdescripcion.getText().replaceAll("\\s","");
         String codsunat = jtfcodigosunat.getText().replaceAll("\\s","");
         ////////////////////////////////////////////////////////////////////////
        if(cod.length() == 0){
                    jtfcodigo.setBackground(new Color(248, 193, 186));
                }else {
                 jtfcodigo.setBackground(Color.WHITE);
             }
        if(familia.getIdfamilia()==0){
             jtffamilia.setBackground(new Color(248, 193, 186));
        }else {
         jtffamilia.setBackground(Color.WHITE);
        }
            try {
                if(Double.parseDouble(jtfpreciof.getText()) ==0 || jtfpreciof.getText().equals("")){
                jtfpreciof.setBackground(new Color(248, 193, 186));
                }else {
                    jtfpreciof.setBackground(Color.WHITE);
                }

        } catch (Exception e) {
             jtfpreciof.setBackground(new Color(248, 193, 186));
        }
                try {
                    if(Double.parseDouble(jtfpreciof1.getText()) ==0 || jtfpreciof1.getText().equals("")){
                    jtfpreciof1.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfpreciof1.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfpreciof1.setBackground(new Color(248, 193, 186));
            }
                    
                try {
                    if(Double.parseDouble(jtfpreciof2.getText()) ==0 || jtfpreciof.getText().equals("")){
                    jtfpreciof2.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfpreciof2.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfpreciof2.setBackground(new Color(248, 193, 186));
            }
                    
                try {
                    if(Double.parseDouble(jtfpreciof3.getText()) ==0 || jtfpreciof3.getText().equals("")){
                    jtfpreciof3.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfpreciof3.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfpreciof3.setBackground(new Color(248, 193, 186));
            }
                
                 try {
                    if(Double.parseDouble(jtfprecioc.getText()) ==0 || jtfprecioc.getText().equals("")){
                    jtfprecioc.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfprecioc.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfprecioc.setBackground(new Color(248, 193, 186));
            }
                 
                try {
                if(Double.parseDouble(jtffactor.getText()) ==0 || jtffactor.getText().equals("")){
                    jtffactor.setBackground(new Color(248, 193, 186));
                }else {
                    jtffactor.setBackground(Color.WHITE);
                }
            } catch (Exception e) {
                jtffactor.setBackground(new Color(248, 193, 186));
            }
                
                
                try {
                    if(Double.parseDouble(jtfinventariomax.getText()) ==0 || jtfinventariomax.getText().equals("")){
                    jtfinventariomax.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfinventariomax.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                 jtfinventariomax.setBackground(new Color(248, 193, 186));
            }
                    
                try {
                    if(Double.parseDouble(jtfinventariomin.getText()) ==0 || jtfinventariomin.getText().equals("")){
                    jtfinventariomin.setBackground(new Color(248, 193, 186));
                    }else {
                        jtfinventariomin.setBackground(Color.WHITE);
                    }
                
            } catch (Exception e) {
                jtfinventariomin.setBackground(new Color(248, 193, 186));
            }
                
                
                if( desc.length() == 0){
                    jtfdescripcion.setBackground(new Color(248, 193, 186));
                }else {
                    jtfdescripcion.setBackground(Color.WHITE);
                }
                
                    if(codsunat.length() == 0){
                    jtfcodigosunat.setBackground(new Color(248, 193, 186));
                }else {
                 jtfcodigosunat.setBackground(Color.WHITE);
             }
       
     
     }
     
     
    public void validaguardar(){
            String cod= jtfcodigo.getText().replaceAll("\\s","");
//            String jfamilia= jtffamilia.getText().replaceAll("\\s","");
            String desc = jtfdescripcion.getText().replaceAll("\\s","");
            String codsunat = jtfcodigosunat.getText().replaceAll("\\s","");
        
        try {
            
            if(cod.length() > 0 && familia.getIdfamilia()!=0 &&
                    Double.parseDouble(jtfpreciof.getText()) >0 && Double.parseDouble(jtfpreciof1.getText()) >0 &&
                    Double.parseDouble(jtfpreciof2.getText()) >0 && Double.parseDouble(jtfpreciof3.getText()) >0 && desc.length() > 0
                 && Double.parseDouble(jtffactor.getText()) >0 && Double.parseDouble(jtfinventariomin.getText()) >0
                    && Double.parseDouble(jtfinventariomax.getText()) >0 && Double.parseDouble(jtfprecioc.getText()) >0 && codsunat.length() >0){
                bloquearjbtn(false, false, true,false ,true, true,true,false,true,true);
                
               
                
                
            }else {
                 bloquearjbtn(true, false, false, false, true, true,true,false,true,true);

            }
   
        } catch (Exception e) {
            bloquearjbtn(true, false, false, false, true, true,true,false,true,true);
        }
        
       colortext();
       
    
    }
    public void setfamilia(Familia familia){
    this.familia= familia;
    jtffamilia.setText(familia.getDescripcion());
    jcbunidcompra.requestFocus();
    validaguardar();
    }
    public  void buscar(){
//        try {
        if(jtablaproducto.getSelectedRow()>=0){
        Producto prodb;
        jlblcargafoto.setVisible(true);
            System.out.println("tamañoL"+listprod.size());
            System.out.println("tamañoT"+jtablaproducto.getRowCount());
        if(listprod.size()==jtablaproducto.getRowCount()){
        prodb = listprod.get(jtablaproducto.getSelectedRow());
//        id = Long.parseLong(jtablaproducto.getValueAt(jtablaproducto.getSelectedRow(), 0).toString());
            System.out.println("idprod: "+prodb.getIdproducto());
        producto = daoproducto.buscarproducto("ID", prodb.getIdproducto(),sucursalsingleton.getId(),"");
        
        /// mostrar imagen ///
        ImageIcon imageIcon = new ImageIcon(this.producto.getFoto());
        //ImageIcon imageUser = imageIcon;
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(jlblImageProducto.getWidth(), jlblImageProducto.getHeight(), java.awt.Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(newimg);
        jlblImageProducto.setIcon(imageIcon);   
        //////////////////////////////////////////////
        jtfcodigo.setText(producto.getCodigo());
        jtfdescripcion.setText(producto.getDescripcion());
        jtaobservacion.setText(producto.getObservacion());
        //jtffamilia.setText(producto.getDireccion());
        jtffamilia.setText(producto.getDescripfamilia());
        familia.setIdfamilia(producto.getIdfamilia());
        fotoB= producto.getFoto();
        
        
        jtfpreciof.setText(fn.FormatoN(producto.getPrecio()));
        jtfpreciof1.setText(fn.FormatoN(producto.getPrecio1()));
        jtfpreciof2.setText(fn.FormatoN(producto.getPrecio2()));
        jtfpreciof3.setText(fn.FormatoN(producto.getPrecio3()));
        jcbunidmedida.setSelectedItem(producto.getUnidadm()+" - "+producto.getUnidabrev());
//        jcbmoneda.setSelectedItem(producto.getMoneda());
//        jspmargen.setValue(producto.getMargenG());
        /////////////////////////////////////////////////
        jcbunidadventa.setSelectedItem(producto.getUnidmedv());
        jcbunidcompra.setSelectedItem(producto.getUnidmedc());
        jtfprecioc.setText(fn.FormatoN(producto.getPrecioc()));
        jtffactor.setText(fn.FormatoN(producto.getFactor()));
        jtfinventariomin.setText(fn.FormatoN(producto.getStockmin()));
        jtfinventariomax.setText(fn.FormatoN(producto.getStockmax()));
        jtflocalizacion.setText(producto.getLocalizacion());
        
        jtfcodigosunat.setText(producto.getCodigosunat());
        
          try {
                
           
        ///////////////////// calcular utilidad //////////////////
        double preciocuniv = producto.getPrecioc()/producto.getFactor();
        jtfpreciocuniv.setText(fn.FormatoN(preciocuniv));
        /////////// precio 1 
            double sinigv =producto.getPrecio()-(producto.getPrecio()*0.18);
            Double utilidad = ((producto.getPrecio()-preciocuniv)/preciocuniv)*100;
            jtfutilidad.setText(fn.FormatoN(utilidad));
            jtfprecio.setText(fn.FormatoN(sinigv));
            
            /////////// precio 2 
            double sinigv1 =producto.getPrecio1()-(producto.getPrecio1()*0.18);
            Double utilidad1 = ((producto.getPrecio1()-preciocuniv)/preciocuniv)*100;
            jtfutilidad1.setText(fn.FormatoN(utilidad1));
            jtfprecio1.setText(fn.FormatoN(sinigv1));
                /////////// precio 3 
            double sinigv2 =producto.getPrecio2()-(producto.getPrecio2()*0.18);
            Double utilidad2 = ((producto.getPrecio2()-preciocuniv)/preciocuniv)*100;
            jtfutilidad2.setText(fn.FormatoN(utilidad2));
            jtfprecio2.setText(fn.FormatoN(sinigv2));
                /////////// precio 4 
            double sinigv3 =producto.getPrecio3()-(producto.getPrecio3()*0.18);
            Double utilidad3 = ((producto.getPrecio3()-preciocuniv)/preciocuniv)*100;
            jtfutilidad3.setText(fn.FormatoN(utilidad3));
            jtfprecio3.setText(fn.FormatoN(sinigv3));
        //////////////////////////////////////////////////////
         } catch (Exception e) {
            }
        
        
        bloquearjbtn(true, true, false, true, false, true,false,true,false,false);
        jbtngenerarcodigo.setEnabled(false);
        }
        }else {
                limpiarjtf();

                 bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
                 bloquearjbtn(true, false, false, false, false, true,false,false,false,false);
                 jbtngenerarcodigo.setEnabled(false);

            }
         
//        } catch (Exception e) {
//            System.out.println(" b"+e);
//        }
    jlblcargafoto.setVisible(false);    
   
    }
    
    public  void sensitiva(){
        
        Runnable runnable = new Runnable() {

              @Override
              public void run() {
   //               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   long tiempoInicio = System.currentTimeMillis();

                   jlblcargando.setVisible(true);  
                   jlblcargandoletra.setVisible(true);
                   ////////////////////////////////////////
                   
                     System.out.println("listfamia"+listfamilia.size());
                     String descrip= jtfbuscarproducto.getText().toUpperCase();
                     if(listfamilia.size()>0){
                         Familia fam;
                         fam=listfamilia.get(jcbfamilia.getSelectedIndex());
                         System.out.println("idfamilia"+fam.getIdfamilia());


                         if(fam.getIdfamilia()==0){
                          listprod=daoproducto.busquedasensitivaproducto(jtablaproducto,"TODO", descrip,
                          sucursalsingleton.getId(),fam.getIdfamilia());


                         }else {
                         listprod=daoproducto.busquedasensitivaproducto(jtablaproducto,"", descrip,
                             sucursalsingleton.getId(),fam.getIdfamilia());
                         }
                      buscar();

                     }
                  ////////////////////////////////////////
                  
                   jlblcargando.setVisible(false);
                   jlblcargandoletra.setVisible(false);

                   long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                   jlbltiempoproceso.setText("Proceso de busqueda "+totalTiempo + " miliseg");
                   bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
                }
            };
    
            Thread M = new Thread(runnable);
            M.start();
  
    }
    
    public void mostrarunidadm()
    {
        /////////////// unid medida prod servicio ///////////
        UnidadMedidaDAO  unddao = new UnidadMedidaDAO();
        listunidad = unddao.mostrar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Unidad_Medida unid: listunidad)
        {
            model.addElement(unid.getMedida()+" - "+unid.getAbreviatura());
        
        }
        jcbunidmedida.setModel(model);
        
        ///////////// Unidad medida fisica //////////////
        UnidadMedidaFisicaDAO  unidfisicadao = new UnidadMedidaFisicaDAO();
        listunidadfis = unidfisicadao.select();
        DefaultComboBoxModel model1 = new DefaultComboBoxModel();
        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        for(Unidad_Medida_Fisica unidfis: listunidadfis)
        {
            model1.addElement(unidfis.getDescrip());
            model2.addElement(unidfis.getDescrip());
        }
        jcbunidcompra.setModel(model1);
        jcbunidadventa.setModel(model2);
        
        Unidad_Medida_Fisica unidf = listunidadfis.get(0);
//        jlblunidadcomsinigv.setText("X "+unidf.getDescrip());
        jlblunidmedv.setText("X "+unidf.getDescrip());
        jlblunidcompra.setText("X "+unidf.getDescrip());
    
    }
    
     public void setcodigosunat(Codigo_sunat cods){
        this.cods=cods;
        jtfcodigosunat.setText(cods.getCodigo()+"-"+cods.getDescripcion());
        colortext();
        validaguardar();
        
        
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfbuscarproducto = new javax.swing.JTextField();
        jlblcargandoletra = new javax.swing.JLabel();
        jlblcargando = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtablaproducto = new javax.swing.JTable();
        jbtnnuevo = new javax.swing.JButton();
        jbtneditar = new javax.swing.JButton();
        jbtnguardar = new javax.swing.JButton();
        jbtneliminar = new javax.swing.JButton();
        jbtncancelar = new javax.swing.JButton();
        jcbfamilia = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlbltiempoproceso = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jtfcodigo = new javax.swing.JTextField();
        jbtngenerarcodigo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jtfdescripcion = new javax.swing.JTextField();
        jcbunidmedida = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtffamilia = new javax.swing.JTextField();
        jbtnbuscarfamilia = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jtfutilidad = new javax.swing.JTextField();
        jtfutilidad1 = new javax.swing.JTextField();
        jtfutilidad3 = new javax.swing.JTextField();
        jtfutilidad2 = new javax.swing.JTextField();
        jtfprecio3 = new javax.swing.JLabel();
        jtfprecio2 = new javax.swing.JLabel();
        jtfprecio1 = new javax.swing.JLabel();
        jtfprecio = new javax.swing.JLabel();
        jtfinventariomax = new javax.swing.JTextField();
        jtfinventariomin = new javax.swing.JTextField();
        jtflocalizacion = new javax.swing.JTextField();
        jtffactor = new javax.swing.JTextField();
        jcbunidcompra = new javax.swing.JComboBox();
        jcbunidadventa = new javax.swing.JComboBox();
        jtfpreciof3 = new javax.swing.JTextField();
        jtfpreciof2 = new javax.swing.JTextField();
        jtfpreciof1 = new javax.swing.JTextField();
        jtfpreciof = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jtfprecioc = new javax.swing.JTextField();
        jlblunidcompra = new javax.swing.JLabel();
        jlblunidmedv = new javax.swing.JLabel();
        jtfpreciocuniv = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfcodigosunat = new javax.swing.JTextField();
        jbtncodigosunat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jlblImageProducto = new javax.swing.JLabel();
        jbtnfoto = new javax.swing.JButton();
        jbtngeneracodigobarras = new javax.swing.JButton();
        jlblcargafoto = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtaobservacion = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setClosable(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel2.setText("Buscar:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, -1));

        jtfbuscarproducto.setToolTipText("");
        jtfbuscarproducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfbuscarproductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfbuscarproductoFocusLost(evt);
            }
        });
        jtfbuscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfbuscarproductoActionPerformed(evt);
            }
        });
        jtfbuscarproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfbuscarproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfbuscarproductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfbuscarproductoKeyTyped(evt);
            }
        });
        jPanel4.add(jtfbuscarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 610, -1));

        jlblcargandoletra.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        jlblcargandoletra.setForeground(new java.awt.Color(0, 0, 0));
        jlblcargandoletra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblcargandoletra.setText("Cargando Productos ...");
        jPanel4.add(jlblcargandoletra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 220, -1));

        jlblcargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblcargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ring.gif"))); // NOI18N
        jPanel4.add(jlblcargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 210, 210));

        jtablaproducto.setBackground(new java.awt.Color(255, 255, 255));
        jtablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtablaproducto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtablaproducto.getTableHeader().setReorderingAllowed(false);
        jtablaproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtablaproductoMouseReleased(evt);
            }
        });
        jtablaproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtablaproductoKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jtablaproducto);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 670, 490));

        jbtnnuevo.setBackground(new java.awt.Color(255, 255, 255));
        jbtnnuevo.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jbtnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add20X20.png"))); // NOI18N
        jbtnnuevo.setText("Nuevo");
        jbtnnuevo.setToolTipText("Nuevo");
        jbtnnuevo.setBorder(null);
        jbtnnuevo.setBorderPainted(false);
        jbtnnuevo.setContentAreaFilled(false);
        jbtnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnnuevoActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 60, -1));

        jbtneditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneditar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jbtneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit20x20.png"))); // NOI18N
        jbtneditar.setText("Editar");
        jbtneditar.setToolTipText("Editar");
        jbtneditar.setBorder(null);
        jbtneditar.setBorderPainted(false);
        jbtneditar.setContentAreaFilled(false);
        jbtneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneditarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 60, -1));

        jbtnguardar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnguardar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jbtnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save20x20.png"))); // NOI18N
        jbtnguardar.setText("Guardar");
        jbtnguardar.setToolTipText("Guardar");
        jbtnguardar.setBorder(null);
        jbtnguardar.setBorderPainted(false);
        jbtnguardar.setContentAreaFilled(false);
        jbtnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnguardarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 80, -1));

        jbtneliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbtneliminar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jbtneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete20x20.png"))); // NOI18N
        jbtneliminar.setText("Eliminar");
        jbtneliminar.setToolTipText("Eliminar");
        jbtneliminar.setBorder(null);
        jbtneliminar.setBorderPainted(false);
        jbtneliminar.setContentAreaFilled(false);
        jbtneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtneliminarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 80, -1));

        jbtncancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbtncancelar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jbtncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel20x20.png"))); // NOI18N
        jbtncancelar.setText("Cancelar");
        jbtncancelar.setToolTipText("Cancelar");
        jbtncancelar.setBorder(null);
        jbtncancelar.setBorderPainted(false);
        jbtncancelar.setContentAreaFilled(false);
        jbtncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncancelarActionPerformed(evt);
            }
        });
        jPanel4.add(jbtncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 90, -1));

        jcbfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbfamiliaActionPerformed(evt);
            }
        });
        jPanel4.add(jcbfamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 340, -1));

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setText("MANTENIMIENTO DE PRODUCTOS Y SERVICIOS");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrarblanco.png"))); // NOI18N
        jLabel15.setToolTipText("Cerrar");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel15MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 710, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 50));

        jlbltiempoproceso.setBackground(new java.awt.Color(0, 0, 0));
        jlbltiempoproceso.setFont(new java.awt.Font("Segoe UI Light", 0, 8)); // NOI18N
        jlbltiempoproceso.setForeground(new java.awt.Color(0, 0, 0));
        jlbltiempoproceso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbltiempoproceso.setText("* * *");
        jPanel4.add(jlbltiempoproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 670, 280, 20));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 533, 10));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 533, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 533, 10));

        jLabel10.setText("Código:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jtfcodigo.setToolTipText("Codigo");
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfcodigoKeyTyped(evt);
            }
        });
        jPanel2.add(jtfcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, -1));

        jbtngenerarcodigo.setBackground(new java.awt.Color(255, 255, 255));
        jbtngenerarcodigo.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtngenerarcodigo.setText("Generar ");
        jbtngenerarcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtngenerarcodigoActionPerformed(evt);
            }
        });
        jPanel2.add(jbtngenerarcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 19));

        jLabel9.setText("Descripción:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jtfdescripcion.setToolTipText("Descripcion");
        jtfdescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfdescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfdescripcionFocusLost(evt);
            }
        });
        jtfdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfdescripcionKeyTyped(evt);
            }
        });
        jPanel2.add(jtfdescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 521, -1));

        jcbunidmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbunidmedidaActionPerformed(evt);
            }
        });
        jcbunidmedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbunidmedidaKeyReleased(evt);
            }
        });
        jPanel2.add(jcbunidmedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 160, -1));

        jLabel8.setText("Tipo:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));

        jLabel11.setText("Familia:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, 20));

        jtffamilia.setEnabled(false);
        jtffamilia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtffamiliaFocusGained(evt);
            }
        });
        jPanel2.add(jtffamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 191, -1));

        jbtnbuscarfamilia.setBackground(new java.awt.Color(255, 255, 255));
        jbtnbuscarfamilia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnbuscarfamilia.setText("...");
        jbtnbuscarfamilia.setPreferredSize(new java.awt.Dimension(45, 18));
        jbtnbuscarfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnbuscarfamiliaActionPerformed(evt);
            }
        });
        jPanel2.add(jbtnbuscarfamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 50, 20));

        jLabel12.setText("Unidad de Compra:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel14.setText("Factor:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        jLabel16.setText("Inventario Min.:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        jLabel13.setText("Unidad de Venta");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel17.setText("Inventario Max.:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel18.setText("Localización");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel19.setText("Precio de Compra:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel20.setForeground(new java.awt.Color(0, 153, 0));
        jLabel20.setText("Precios de Venta");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, -1, -1));

        jLabel21.setText("Márgenes de Utilidad %:");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jLabel22.setText("Precio de Venta neto:");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel23.setText("Precio de Venta sin igv:");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jtfutilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidadActionPerformed(evt);
            }
        });
        jtfutilidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidadKeyReleased(evt);
            }
        });
        jPanel2.add(jtfutilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 82, -1));

        jtfutilidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad1KeyReleased(evt);
            }
        });
        jPanel2.add(jtfutilidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 79, -1));

        jtfutilidad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidad3ActionPerformed(evt);
            }
        });
        jtfutilidad3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad3KeyReleased(evt);
            }
        });
        jPanel2.add(jtfutilidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, 83, -1));

        jtfutilidad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfutilidad2ActionPerformed(evt);
            }
        });
        jtfutilidad2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfutilidad2KeyReleased(evt);
            }
        });
        jPanel2.add(jtfutilidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 82, -1));

        jtfprecio3.setText("0");
        jPanel2.add(jtfprecio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, -1, 20));

        jtfprecio2.setText("0");
        jPanel2.add(jtfprecio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, 20));

        jtfprecio1.setText("0");
        jPanel2.add(jtfprecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, -1, 20));

        jtfprecio.setText("0");
        jPanel2.add(jtfprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, -1, 20));

        jtfinventariomax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfinventariomaxKeyReleased(evt);
            }
        });
        jPanel2.add(jtfinventariomax, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 80, -1));

        jtfinventariomin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfinventariominActionPerformed(evt);
            }
        });
        jtfinventariomin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfinventariominKeyReleased(evt);
            }
        });
        jPanel2.add(jtfinventariomin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 80, 20));

        jtflocalizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtflocalizacionKeyReleased(evt);
            }
        });
        jPanel2.add(jtflocalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 400, -1));

        jtffactor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtffactorKeyReleased(evt);
            }
        });
        jPanel2.add(jtffactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 110, -1));

        jcbunidcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbunidcompraActionPerformed(evt);
            }
        });
        jcbunidcompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbunidcompraKeyReleased(evt);
            }
        });
        jPanel2.add(jcbunidcompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 170, -1));

        jcbunidadventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbunidadventaActionPerformed(evt);
            }
        });
        jcbunidadventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbunidadventaKeyReleased(evt);
            }
        });
        jPanel2.add(jcbunidadventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 170, -1));

        jtfpreciof3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfpreciof3ActionPerformed(evt);
            }
        });
        jtfpreciof3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciof3KeyReleased(evt);
            }
        });
        jPanel2.add(jtfpreciof3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 470, 80, -1));

        jtfpreciof2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciof2KeyReleased(evt);
            }
        });
        jPanel2.add(jtfpreciof2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 80, -1));

        jtfpreciof1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciof1KeyReleased(evt);
            }
        });
        jPanel2.add(jtfpreciof1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 80, -1));

        jtfpreciof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciofKeyReleased(evt);
            }
        });
        jPanel2.add(jtfpreciof, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 80, -1));

        jLabel24.setBackground(new java.awt.Color(255, 51, 51));
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("Otros");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, -1, -1));

        jLabel25.setBackground(new java.awt.Color(255, 51, 51));
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("Mayorista");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, -1, -1));

        jLabel26.setBackground(new java.awt.Color(255, 51, 51));
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setText("Min. Publico");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, -1, -1));

        jLabel27.setBackground(new java.awt.Color(255, 51, 51));
        jLabel27.setForeground(new java.awt.Color(255, 51, 51));
        jLabel27.setText("Público");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, -1));

        jtfprecioc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciocKeyReleased(evt);
            }
        });
        jPanel2.add(jtfprecioc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 90, -1));

        jlblunidcompra.setText("X ...");
        jPanel2.add(jlblunidcompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, -1, -1));

        jlblunidmedv.setText("X ...");
        jPanel2.add(jlblunidmedv, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, -1, -1));

        jtfpreciocuniv.setEnabled(false);
        jtfpreciocuniv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfpreciocunivActionPerformed(evt);
            }
        });
        jtfpreciocuniv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfpreciocunivKeyReleased(evt);
            }
        });
        jPanel2.add(jtfpreciocuniv, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 130, -1));

        jLabel4.setText("Código Producto Sunat:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jtfcodigosunat.setEnabled(false);
        jPanel2.add(jtfcodigosunat, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 150, -1));

        jbtncodigosunat.setText("...");
        jbtncodigosunat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncodigosunatActionPerformed(evt);
            }
        });
        jPanel2.add(jbtncodigosunat, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, 20));

        jTabbedPane1.addTab("Datos", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblImageProducto.setBackground(new java.awt.Color(255, 255, 255));
        jlblImageProducto.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jlblImageProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 270, 190));

        jbtnfoto.setBackground(new java.awt.Color(255, 255, 255));
        jbtnfoto.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtnfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FOTO.png"))); // NOI18N
        jbtnfoto.setText("Foto Referencial");
        jbtnfoto.setBorderPainted(false);
        jbtnfoto.setContentAreaFilled(false);
        jbtnfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnfotoActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 160, 40));

        jbtngeneracodigobarras.setBackground(new java.awt.Color(255, 255, 255));
        jbtngeneracodigobarras.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jbtngeneracodigobarras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/codprod.png"))); // NOI18N
        jbtngeneracodigobarras.setText("Generar Cod. Barras");
        jbtngeneracodigobarras.setBorderPainted(false);
        jbtngeneracodigobarras.setContentAreaFilled(false);
        jbtngeneracodigobarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtngeneracodigobarrasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtngeneracodigobarrasMousePressed(evt);
            }
        });
        jbtngeneracodigobarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtngeneracodigobarrasActionPerformed(evt);
            }
        });
        jPanel3.add(jbtngeneracodigobarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, 50));

        jlblcargafoto.setFont(new java.awt.Font("Segoe Script", 0, 10)); // NOI18N
        jlblcargafoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loading4.gif"))); // NOI18N
        jlblcargafoto.setText("Cargando vista previa");
        jPanel3.add(jlblcargafoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 200, -1));

        jtaobservacion.setColumns(20);
        jtaobservacion.setRows(5);
        jtaobservacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtaobservacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtaobservacionFocusLost(evt);
            }
        });
        jtaobservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtaobservacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtaobservacionKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(jtaobservacion);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 500, 310));

        jLabel7.setText("Especificación:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jTabbedPane1.addTab("Caracteristicas", jPanel3);

        jPanel4.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 550, 590));

        jLabel1.setText("Familia:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfbuscarproductoActionPerformed
        // TODO add your handling code here:
//           try {
        
    }//GEN-LAST:event_jtfbuscarproductoActionPerformed

    private void jtfbuscarproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarproductoKeyReleased
        
        
     
       
    }//GEN-LAST:event_jtfbuscarproductoKeyReleased

    private void jtfbuscarproductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarproductoKeyTyped
        // TODO add your handling code here:
//       mayus.convertirmayus(jtfbuscarproducto);
    }//GEN-LAST:event_jtfbuscarproductoKeyTyped

    private void jtablaproductoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablaproductoMouseReleased
        // TODO add your handling code here:
   
        Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                jlblcargafoto.setVisible(true);
                long tiempoInicio = System.currentTimeMillis();
                buscar();
                colortext();
                long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                jlbltiempoproceso.setText("Vista previa "+totalTiempo + " miliseg");
//                jlblcargafoto.setVisible(false);
                bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
            }
        };
        Thread T= new Thread(runnable);
        T.start();
       
       
    }//GEN-LAST:event_jtablaproductoMouseReleased

    private void jbtnfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnfotoActionPerformed
        // TODO add your handling code here:
//          Empleado empleado = new Empleado();
//        System.out.println("idfoto"+id);
//        empleado.setId_empleado(id);
    JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Archivos png & jpg", "png", "jpg");
        chooser.setFileFilter(filter);
        String userHome = System.getProperty( "user.home" );
        chooser.setCurrentDirectory(new File(userHome));
        chooser.setAcceptAllFileFilterUsed(false);
        int retrival = chooser.showOpenDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {                        
                try {
                    rutaimagen = chooser.getSelectedFile().getAbsolutePath();
                    File file = new File(rutaimagen);
                    byte[]  fotobys= Files.readAllBytes(file.toPath());
                    fotoB=fotobys;
                    
                    
                    Image icono=ImageIO.read(chooser.getSelectedFile()).getScaledInstance
                    (jlblImageProducto.getWidth(),jlblImageProducto.getHeight(),Image.SCALE_DEFAULT);
                    jlblImageProducto.setIcon(new ImageIcon(icono));
                    jlblImageProducto.updateUI();

                   validaguardar();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "imagen: "+ex);
                }
            }     
    }//GEN-LAST:event_jbtnfotoActionPerformed

    private void jbtngeneracodigobarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtngeneracodigobarrasActionPerformed
        // TODO add your handling code here:
//            GenerarCodigoBarras codbarra= new GenerarCodigoBarras();
//
//        String codigo= jtfcodigo.getText().replaceAll("\\s","");
//        codbarra.generarcodigobarras( "E:\\"+codigo+".pdf",codigo );
//        JOptionPane.showMessageDialog(this,"CODIGO GENERADO EXITOSAMENTE");
        Producto prod;
        prod= listprod.get(jtablaproducto.getSelectedRow());
        daoproducto.imprimircodigo(prod.getIdproducto());
//         jlblmensajeimpresion.setText("");
    }//GEN-LAST:event_jbtngeneracodigobarrasActionPerformed

    private void jbtnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnnuevoActionPerformed
        // TODO add your handling code here:
         bloquearjtf(true, true, true,true,true,true,true,true,true, true, true,true,true,true,true,true,true, true, true,true,true);
        bloquearjbtn(true, false, false, false, true, true,true,false,true,true);
        jbtngenerarcodigo.setEnabled(true);
        limpiarjtf();
        producto = new Producto();
        editar=false;
        jtfcodigo.requestFocus();

    }//GEN-LAST:event_jbtnnuevoActionPerformed

    private void jbtneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneditarActionPerformed
        // TODO add your handling code here:
         bloquearjtf(true, true, true,true,true,true,true,true,true, true, true,true,true,true,true,true,true, true, true,true,true);
        editar=true;
        bloquearjbtn(true, false, false, false, true, true,true,false,true,true);
        jbtngenerarcodigo.setEnabled(true);
    }//GEN-LAST:event_jbtneditarActionPerformed

    private void jbtnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnguardarActionPerformed
        // TODO add your handling code here:
//        Producto producto= new Producto();
       Unidad_Medida und = listunidad.get(jcbunidmedida.getSelectedIndex());
        boolean refrescar = false;
        String codigo=jtfcodigo.getText().replaceAll("\\s","");
        String descripcion=jtfdescripcion.getText().trim().toUpperCase();
        String observacion=jtaobservacion.getText().trim().toUpperCase();
        producto.setIdfamilia(familia.getIdfamilia());
        producto.setCodigo(codigo);
        producto.setDescripcion(descripcion);
        producto.setObservacion(observacion);
        producto.setPrecio(Double.parseDouble(jtfpreciof.getText()));
        producto.setPrecio1(Double.parseDouble(jtfpreciof1.getText()));
        producto.setPrecio2(Double.parseDouble(jtfpreciof2.getText()));
        producto.setPrecio3(Double.parseDouble(jtfpreciof3.getText()));
        producto.setIdunidm(und.getId());
        if(producto.getIdcodsunat()==0){
            producto.setIdcodsunat(cods.getId());
        }
        
//        try {
//             producto.setMoneda(jcbmoneda.getSelectedItem().toString());
//        } catch (Exception e) {
//            producto.setMoneda("");
//        }
       
        producto.setFoto(fotoB);
        producto.setId_sucursal(sucursalsingleton.getId());
        /// unid med venta /////////////////////////
        int iunidmedfc = jcbunidcompra.getSelectedIndex();
        int iunidmedfv = jcbunidadventa.getSelectedIndex();
        Unidad_Medida_Fisica unidmedfc = listunidadfis.get(iunidmedfc);
        Unidad_Medida_Fisica unidmedfv = listunidadfis.get(iunidmedfv);
        
        producto.setUnidmedv(unidmedfv.getDescrip());
        producto.setUnidmedc(unidmedfc.getDescrip());
        
        producto.setStockmax(Double.parseDouble(jtfinventariomax.getText()));
        producto.setStockmin(Double.parseDouble(jtfinventariomin.getText()));
        
        producto.setPrecioc(Double.parseDouble(jtfprecioc.getText()));
        producto.setLocalizacion(jtflocalizacion.getText().toUpperCase());
        producto.setFactor(Double.parseDouble(jtffactor.getText()));
        
        
        if (codigo.length()>0 && descripcion.length()>0 
                ){
            if(!codigo.equals("")){
                if(editar==false){
                  boolean valida=daoproducto.duplicado(0, codigo,"GUARDAR");
                   if(valida==true){
                       producto.setCantidad(0.0);
                       daoproducto.insertarproducto(producto);
                       menu.cargarresumen();
                       refrescar=true;
                        bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);   
                   }else{
                    JOptionPane.showMessageDialog(null, "El producto ya se encuentra registrado","",JOptionPane.ERROR_MESSAGE);
                }    
                 
                }
               
                else{
                    
                   
                    boolean Vvalida=daoproducto.duplicado(producto.getIdproducto(), codigo,"EDITAR");
                 if(Vvalida==true){
                      daoproducto.editarproducto(producto);
                      refrescar=true;
                       bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);   
                 }else {
                     JOptionPane.showMessageDialog(null, "El producto ya se encuentra registrado","",JOptionPane.INFORMATION_MESSAGE);
                 }
                   
                   
                        } 
                
                       
                    
             
                
            }else {
                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS","ERROR",JOptionPane.ERROR_MESSAGE);
            } 
           
        }else {
            JOptionPane.showMessageDialog(null, "INGRESE TODOS LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
        
        }

        
        if(refrescar==true){
            Runnable runnable = new Runnable() {

              @Override
              public void run() {
   //               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                   jlblcargando.setVisible(true);  
                   jlblcargandoletra.setVisible(true);
                   sensitiva();
                   jlblcargando.setVisible(false);
                   jlblcargandoletra.setVisible(false);

              }
          };

          Thread M = new Thread(runnable);
          M.start();

        
        }
        
     
    }//GEN-LAST:event_jbtnguardarActionPerformed

    private void jbtneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtneliminarActionPerformed
        // TODO add your handling code here:
        int index= jtablaproducto.getSelectedRow();
        Producto prod = listprod.get(index);
        
        if (index<0){
            JOptionPane.showMessageDialog(this, "Seleccione item");
        }else {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar","ELIMINAR",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
               if(prod.getCantidad()==0){
                    daoproducto.eliminarproducto(prod.getIdproducto());
               
               
//                bloquearjtf( false, false, false,false,false,false,false,false);
//                bloquearjbtn(true, false, false, false, false, true,false,false,false);
//                jbtngenerarcodigo.setEnabled(false);
//                limpiarjtf();
//                daoproducto.mostrarproducto(jtablaproducto,sucursalsingleton.getId());
                Runnable runnable = new Runnable() {

                  @Override
                  public void run() {
       //               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                       jlblcargando.setVisible(true);  
                       jlblcargandoletra.setVisible(true);
                       sensitiva();
                       jlblcargando.setVisible(false);
                       jlblcargandoletra.setVisible(false);
                  }
              };
              Thread M = new Thread(runnable);
              M.start();
               
           
       }else {

           JOptionPane.showMessageDialog(null, "Imposible eliminar item revise su stock","",JOptionPane.ERROR_MESSAGE);
       }
               

        }
        }
    }//GEN-LAST:event_jbtneliminarActionPerformed

    private void jbtncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncancelarActionPerformed
        // TODO add your handling code here:
        int index = jtablaproducto.getSelectedRow();
        if(index>=0){
            buscar();
        } else {
        limpiarjtf();
        producto= new Producto();
        }
        
        bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
        bloquearjbtn(true, false, false, false, false, true,false,false,false,false);
        jbtngenerarcodigo.setEnabled(false);
        
    }//GEN-LAST:event_jbtncancelarActionPerformed

    private void jtfbuscarproductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarproductoFocusGained
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfbuscarproductoFocusGained

    private void jtfbuscarproductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfbuscarproductoFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtfbuscarproductoFocusLost

    private void jtfcodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusGained
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtfcodigoFocusGained

    private void jtfcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfcodigoFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfcodigoFocusLost

    private void jtfdescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfdescripcionFocusGained

    private void jtfdescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfdescripcionFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtfdescripcionFocusLost

    private void jtaobservacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaobservacionFocusGained
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jtaobservacionFocusGained

    private void jtaobservacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtaobservacionFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jtaobservacionFocusLost

    private void jbtnbuscarfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnbuscarfamiliaActionPerformed
        // TODO add your handling code here:
        
        JDBuscarFamilia bfamilia= new JDBuscarFamilia(new java.awt.Frame(), isVisible(),this);
        
        bfamilia.setVisible(true);
        
        
        
    }//GEN-LAST:event_jbtnbuscarfamiliaActionPerformed

    private void jtfdescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyReleased
        // TODO add your handling code here:
        validaguardar();
        if(evt.getKeyCode()==10){
            jcbunidmedida.requestFocus();
        }
    }//GEN-LAST:event_jtfdescripcionKeyReleased

    private void jtaobservacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaobservacionKeyReleased
        // TODO add your handling code here:
        validaguardar();
    }//GEN-LAST:event_jtaobservacionKeyReleased

    private void jtablaproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtablaproductoKeyReleased
        // TODO add your handling code here
        
       Runnable runnable=new Runnable() {

            @Override
            public void run() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                jlblcargafoto.setVisible(true);
                buscar();
               colortext();
//                jlblcargafoto.setVisible(false);
                bloquearjtf(false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
            }
        };
        Thread T= new Thread(runnable);
        T.start();
    }//GEN-LAST:event_jtablaproductoKeyReleased

    private void jtfcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyReleased
        // TODO add your handling code here:
        validaguardar();
        if(evt.getKeyCode()==10){
            jtfdescripcion.requestFocus();
        }
    }//GEN-LAST:event_jtfcodigoKeyReleased

    private void jbtngenerarcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtngenerarcodigoActionPerformed
        // TODO add your handling code here:
        daoproducto.generarcodigo(jtfcodigo);
        validaguardar();
        jbtngenerarcodigo.setEnabled(false);
        jtfdescripcion.requestFocus();
    }//GEN-LAST:event_jbtngenerarcodigoActionPerformed

    private void jtfdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfdescripcionKeyTyped
        // TODO add your handling code here:
//        mayus.convertirmayus(jtfdescripcion);
        validaguardar();
    }//GEN-LAST:event_jtfdescripcionKeyTyped

    private void jtaobservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaobservacionKeyTyped
        // TODO add your handling code here:
//         mayus.convertirmayusTA(jtaobservacion);
    }//GEN-LAST:event_jtaobservacionKeyTyped

    private void jtfcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcodigoKeyTyped
        // TODO add your handling code here:
//         char c = evt.getKeyChar();
//        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
//                ) {
//                evt.consume();
//            }
//            if (c == '.' && jtfcodigo.getText().contains(".")) {
//                evt.consume();
//                } 
        
    }//GEN-LAST:event_jtfcodigoKeyTyped

    private void jcbfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbfamiliaActionPerformed
        // TODO add your handling code here:
         Runnable runnable = new Runnable() {

           @Override
           public void run() {
//               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                long tiempoInicio = System.currentTimeMillis();
                
                jlblcargando.setVisible(true);  
                jlblcargandoletra.setVisible(true);
                sensitiva();
                jlblcargando.setVisible(false);
                jlblcargandoletra.setVisible(false);
                long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                jlbltiempoproceso.setText("Busqueda por familias "+totalTiempo + " miliseg");
           }
       };
       
       Thread M = new Thread(runnable);
       M.start();
      
        
    }//GEN-LAST:event_jcbfamiliaActionPerformed

    private void jbtngeneracodigobarrasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtngeneracodigobarrasMousePressed
        // TODO add your handling code here:
//        jlblmensajeimpresion.setText("Generando ...");
    }//GEN-LAST:event_jbtngeneracodigobarrasMousePressed

    private void jbtngeneracodigobarrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtngeneracodigobarrasMouseExited
        // TODO add your handling code here:
//        jlblmensajeimpresion.setText("");
    }//GEN-LAST:event_jbtngeneracodigobarrasMouseExited

    private void jLabel15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseReleased
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseReleased

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int xp=evt.getXOnScreen() - posx;
        int yp=evt.getYOnScreen() - posy;
        this.setLocation(xp, yp);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
          posy=evt.getY();
        posx=evt.getX();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jtfbuscarproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfbuscarproductoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
           sensitiva();
           System.out.println("enter");
         }
    }//GEN-LAST:event_jtfbuscarproductoKeyPressed

    private void jcbunidmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbunidmedidaActionPerformed
        // TODO add your handling code here:
        validaguardar();
       
       
    }//GEN-LAST:event_jcbunidmedidaActionPerformed

    private void jtfutilidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidadKeyReleased
        // TODO add your handling code here:
       
         if(evt.getKeyCode()==10){
            jtfutilidad1.requestFocus();
         }
         
         try {
            double utilid= Double.parseDouble(jtfutilidad.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18); 

            jtfprecio.setText(fn.FormatoN(psinigv));
            jtfpreciof.setText(fn.FormatoN(precio));
             
             
        } catch (Exception e) {
            
            jtfprecio.setText("0");
            jtfpreciof.setText("0");
        }
         
          validaguardar();
         
    }//GEN-LAST:event_jtfutilidadKeyReleased

    private void jtfutilidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad1KeyReleased
        // TODO add your handling code here:
       
         if(evt.getKeyCode()==10){
            jtfutilidad2.requestFocus();
        }
         
         try {
            double utilid= Double.parseDouble(jtfutilidad1.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18); 

            jtfprecio1.setText(fn.FormatoN(psinigv));
            jtfpreciof1.setText(fn.FormatoN(precio));
             
             
        } catch (Exception e) {
            
            jtfprecio1.setText("0");
            jtfpreciof1.setText("0");
        }
          validaguardar();
    }//GEN-LAST:event_jtfutilidad1KeyReleased

    private void jtfutilidad2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad2KeyReleased
        // TODO add your handling code here:
       
        
         if(evt.getKeyCode()==10){
            jtfutilidad3.requestFocus();
        }
         
         
          try {
            double utilid= Double.parseDouble(jtfutilidad2.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18); 

            jtfprecio2.setText(fn.FormatoN(psinigv));
            jtfpreciof2.setText(fn.FormatoN(precio));
             
             
        } catch (Exception e) {
            
            jtfprecio2.setText("0");
            jtfpreciof2.setText("0");
        }
           validaguardar();
    }//GEN-LAST:event_jtfutilidad2KeyReleased

    private void jtfutilidad3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfutilidad3KeyReleased
        // TODO add your handling code here:
       
         try {
            double utilid= Double.parseDouble(jtfutilidad3.getText());
            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  precioc * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18); 

            jtfprecio3.setText(fn.FormatoN(psinigv));
            jtfpreciof3.setText(fn.FormatoN(precio));
             
             
        } catch (Exception e) {
            
            jtfprecio3.setText("0");
            jtfpreciof3.setText("0");
        }
       validaguardar();
    }//GEN-LAST:event_jtfutilidad3KeyReleased

    private void jtffamiliaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtffamiliaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtffamiliaFocusGained

    private void jtfutilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidadActionPerformed

    private void jtfutilidad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidad2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidad2ActionPerformed

    private void jtfcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfcodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfcodigoActionPerformed

    private void jtfinventariominActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfinventariominActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfinventariominActionPerformed

    private void jtfpreciof3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfpreciof3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfpreciof3ActionPerformed

    private void jtfpreciocunivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfpreciocunivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfpreciocunivActionPerformed

    private void jcbunidcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbunidcompraActionPerformed
        // TODO add your handling code here:
        
//        int index= jcbunidcompra.getSelectedIndex();
//        Unidad_Medida_Fisica unidfis= listunidadfis.get(index);
        jlblunidcompra.setText("X "+jcbunidcompra.getSelectedItem());
        validaguardar();
//        jlblunidadcomsinigv.setText("X "+unidfis.getDescrip());
        
        
       
    }//GEN-LAST:event_jcbunidcompraActionPerformed

    private void jcbunidadventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbunidadventaActionPerformed
        // TODO add your handling code here:
//        int index= jcbunidadventa.getSelectedIndex();
//        Unidad_Medida_Fisica unidfis= listunidadfis.get(index);
        jlblunidmedv.setText("X "+jcbunidadventa.getSelectedItem());
        validaguardar();
        
       
     
    }//GEN-LAST:event_jcbunidadventaActionPerformed

    private void jcbunidmedidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbunidmedidaKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jbtnbuscarfamilia.requestFocus();
        }
    }//GEN-LAST:event_jcbunidmedidaKeyReleased

    private void jtfinventariomaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfinventariomaxKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jtfinventariomin.requestFocus();
        }
        validaguardar();
    }//GEN-LAST:event_jtfinventariomaxKeyReleased

    private void jtfinventariominKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfinventariominKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jtflocalizacion.requestFocus();
        }
        validaguardar();
    }//GEN-LAST:event_jtfinventariominKeyReleased

    private void jtflocalizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtflocalizacionKeyReleased
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==10){
            jtfprecioc.requestFocus();
        }
        validaguardar();
    }//GEN-LAST:event_jtflocalizacionKeyReleased

    private void jtfpreciocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciocKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            jtfutilidad.requestFocus();
        }
        
        try {
            double precioc = Double.parseDouble(jtfprecioc.getText());
//            Double preciocsinigv = precioc -(precioc*0.18);
//            jtfpreciocunic.setText(fn.FormatoN(preciocsinigv));
            double factor = Double.parseDouble(jtffactor.getText());
            Double preciov = precioc/factor;
            jtfpreciocuniv.setText(fn.FormatoN(preciov));
            
            
            
        } catch (Exception e) {
           
//            jtfpreciocunic.setText("0");
            jtfpreciocuniv.setText("0");
        }
        
        
        try {
            
            ///////////////////  precio 1 /////////////////

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());
            
            /////////// precio neto 
            double utilid= Double.parseDouble(jtfutilidad.getText());
//            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  preciocuniv * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18); 

            jtfprecio.setText(fn.FormatoN(psinigv));
            jtfpreciof.setText(fn.FormatoN(precio));
            ////////////// precio 2 
            
            double utilid1= Double.parseDouble(jtfutilidad1.getText());
//            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio1 =  preciocuniv * (1 + (utilid1/100));

            double psinigv1 = precio1 - (precio1*0.18); 

            jtfprecio1.setText(fn.FormatoN(psinigv1));
            jtfpreciof1.setText(fn.FormatoN(precio1));
            
            /////////////// precio 3 ////////////////////
            double utilid2= Double.parseDouble(jtfutilidad2.getText());
//            double precioc2 = Double.parseDouble(jtfpreciocuniv.getText());
            double precio2 =  preciocuniv * (1 + (utilid2/100));

            double psinigv2 = precio2 - (precio2*0.18); 

            jtfprecio2.setText(fn.FormatoN(psinigv2));
            jtfpreciof2.setText(fn.FormatoN(precio2));
            /////////////////// precio 4 /////////////
            
            double utilid3= Double.parseDouble(jtfutilidad3.getText());
//            double precioc3 = Double.parseDouble(jtfpreciocuniv.getText());
            double precio3 =  preciocuniv * (1 + (utilid3/100));

            double psinigv3 = precio3 - (precio3*0.18); 

            jtfprecio3.setText(fn.FormatoN(psinigv3));
            jtfpreciof3.setText(fn.FormatoN(precio3));
            
            
        } catch (Exception e) {
        }
          
        validaguardar();
        
    }//GEN-LAST:event_jtfpreciocKeyReleased

    private void jtffactorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtffactorKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==10){
            jcbunidadventa.requestFocus();
        }
         
    
       
          try {
            double precioc = Double.parseDouble(jtfprecioc.getText());
//            Double preciocsinigv = precioc -(precioc*0.18);
//            jtfpreciocunic.setText(fn.FormatoN(preciocsinigv));
            double factor = Double.parseDouble(jtffactor.getText());
            Double preciov = precioc/factor;
            jtfpreciocuniv.setText(fn.FormatoN(preciov));
          
            
            
            
        } catch (Exception e) {
           
//            jtfpreciocunic.setText("0");
            jtfpreciocuniv.setText("0");
        }
          
          try {
            
                ///////////////////  precio 1 /////////////////

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());
            
            /////////// precio neto 
            double utilid= Double.parseDouble(jtfutilidad.getText());
//            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio =  preciocuniv * (1 + (utilid/100));

            double psinigv = precio - (precio*0.18); 

            jtfprecio.setText(fn.FormatoN(psinigv));
            jtfpreciof.setText(fn.FormatoN(precio));
            ////////////// precio 2 
            
            double utilid1= Double.parseDouble(jtfutilidad1.getText());
//            double precioc = Double.parseDouble(jtfpreciocuniv.getText());
            double precio1 =  preciocuniv * (1 + (utilid1/100));

            double psinigv1 = precio1 - (precio1*0.18); 

            jtfprecio1.setText(fn.FormatoN(psinigv1));
            jtfpreciof1.setText(fn.FormatoN(precio1));
            
            /////////////// precio 3 ////////////////////
            double utilid2= Double.parseDouble(jtfutilidad2.getText());
//            double precioc2 = Double.parseDouble(jtfpreciocuniv.getText());
            double precio2 =  preciocuniv * (1 + (utilid2/100));

            double psinigv2 = precio2 - (precio2*0.18); 

            jtfprecio2.setText(fn.FormatoN(psinigv2));
            jtfpreciof2.setText(fn.FormatoN(precio2));
            /////////////////// precio 4 /////////////
            
            double utilid3= Double.parseDouble(jtfutilidad3.getText());
//            double precioc3 = Double.parseDouble(jtfpreciocuniv.getText());
            double precio3 =  preciocuniv * (1 + (utilid3/100));

            double psinigv3 = precio3 - (precio3*0.18); 

            jtfprecio3.setText(fn.FormatoN(psinigv3));
            jtfpreciof3.setText(fn.FormatoN(precio3));
            
              
              
        } catch (Exception e) {
        }
          
         validaguardar();
              
              
             
    }//GEN-LAST:event_jtffactorKeyReleased

    private void jcbunidcompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbunidcompraKeyReleased

        // TODO add your handling code here:
        
         
       
    }//GEN-LAST:event_jcbunidcompraKeyReleased

    private void jcbunidadventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbunidadventaKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jcbunidadventaKeyReleased

    private void jtfutilidad3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfutilidad3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfutilidad3ActionPerformed

    private void jtfpreciofKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciofKeyReleased
        // TODO add your handling code here:
        try {
            
            double preciosneto = Double.parseDouble(jtfpreciof.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad.setText(fn.FormatoN(utilidad));
            jtfprecio.setText(fn.FormatoN(sinigv));
            
        } catch (Exception e) {
            
            jtfutilidad.setText("0");
            jtfprecio.setText("0");
        }
        validaguardar();
        
        
    }//GEN-LAST:event_jtfpreciofKeyReleased

    private void jtfpreciof1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciof1KeyReleased
        // TODO add your handling code here:
          try {
            
             double preciosneto = Double.parseDouble(jtfpreciof1.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad1.setText(fn.FormatoN(utilidad));
            jtfprecio1.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {
            
            jtfutilidad1.setText("0");
            jtfprecio1.setText("0");
        }
           validaguardar();
    }//GEN-LAST:event_jtfpreciof1KeyReleased

    private void jtfpreciof2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciof2KeyReleased
        // TODO add your handling code here:
          try {
            
             double preciosneto = Double.parseDouble(jtfpreciof2.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad2.setText(fn.FormatoN(utilidad));
            jtfprecio2.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {
            
            jtfutilidad2.setText("0");
            jtfprecio2.setText("0");
        }
           validaguardar();
    }//GEN-LAST:event_jtfpreciof2KeyReleased

    private void jtfpreciof3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciof3KeyReleased
        // TODO add your handling code here:
        
          try {
            
             double preciosneto = Double.parseDouble(jtfpreciof3.getText());
            double sinigv =preciosneto-(preciosneto*0.18);

            double preciocuniv = Double.parseDouble(jtfpreciocuniv.getText());

            Double utilidad = ((preciosneto-preciocuniv)/preciocuniv)*100;

            jtfutilidad3.setText(fn.FormatoN(utilidad));
            jtfprecio3.setText(fn.FormatoN(sinigv));
        } catch (Exception e) {
            
            jtfutilidad3.setText("0");
            jtfprecio3.setText("0");
        }
           validaguardar();
    }//GEN-LAST:event_jtfpreciof3KeyReleased

    private void jtfpreciocunivKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfpreciocunivKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfpreciocunivKeyReleased

    private void jbtncodigosunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncodigosunatActionPerformed
        // TODO add your handling code here:
        JDCodigo_sunat_producto codsunat = new JDCodigo_sunat_producto(new JFrame(), isVisible(),this);
        codsunat.setVisible(true);
                
    }//GEN-LAST:event_jbtncodigosunatActionPerformed


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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnbuscarfamilia;
    private javax.swing.JButton jbtncancelar;
    private javax.swing.JButton jbtncodigosunat;
    private javax.swing.JButton jbtneditar;
    private javax.swing.JButton jbtneliminar;
    private javax.swing.JButton jbtnfoto;
    private javax.swing.JButton jbtngeneracodigobarras;
    private javax.swing.JButton jbtngenerarcodigo;
    private javax.swing.JButton jbtnguardar;
    private javax.swing.JButton jbtnnuevo;
    private javax.swing.JComboBox jcbfamilia;
    private javax.swing.JComboBox jcbunidadventa;
    private javax.swing.JComboBox jcbunidcompra;
    private javax.swing.JComboBox jcbunidmedida;
    private javax.swing.JLabel jlblImageProducto;
    private javax.swing.JLabel jlblcargafoto;
    private javax.swing.JLabel jlblcargando;
    private javax.swing.JLabel jlblcargandoletra;
    private javax.swing.JLabel jlbltiempoproceso;
    private javax.swing.JLabel jlblunidcompra;
    private javax.swing.JLabel jlblunidmedv;
    private javax.swing.JTable jtablaproducto;
    private javax.swing.JTextArea jtaobservacion;
    private javax.swing.JTextField jtfbuscarproducto;
    private javax.swing.JTextField jtfcodigo;
    private javax.swing.JTextField jtfcodigosunat;
    private javax.swing.JTextField jtfdescripcion;
    private javax.swing.JTextField jtffactor;
    private javax.swing.JTextField jtffamilia;
    private javax.swing.JTextField jtfinventariomax;
    private javax.swing.JTextField jtfinventariomin;
    private javax.swing.JTextField jtflocalizacion;
    private javax.swing.JLabel jtfprecio;
    private javax.swing.JLabel jtfprecio1;
    private javax.swing.JLabel jtfprecio2;
    private javax.swing.JLabel jtfprecio3;
    private javax.swing.JTextField jtfprecioc;
    private javax.swing.JTextField jtfpreciocuniv;
    private javax.swing.JTextField jtfpreciof;
    private javax.swing.JTextField jtfpreciof1;
    private javax.swing.JTextField jtfpreciof2;
    private javax.swing.JTextField jtfpreciof3;
    private javax.swing.JTextField jtfutilidad;
    private javax.swing.JTextField jtfutilidad1;
    private javax.swing.JTextField jtfutilidad2;
    private javax.swing.JTextField jtfutilidad3;
    // End of variables declaration//GEN-END:variables
}
