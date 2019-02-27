/*
#########################################################
################### INTEGRACIÓN FÁCIL ###################
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# ESTE CÓDIGO FUNCIONA PARA LA VERSIÓN ONLINE Y OFFLINE
# Visita www.nubefact.com/integracion para mas información
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#########################################################
#### FORMA DE TRABAJO ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# PASO 1: Conseguir una RUTA y un TOKEN para trabajar con NUBEFACT (Regístrate o ingresa a tu cuenta en www.nubefact.com).
# PASO 2: Generar un archivo en formato .JSON o .TXT con una estructura que se detalla en este documento.
# PASO 3: Enviar el archivo generado a nuestra WEB SERVICE ONLINE u OFFLINE según corresponda usando la RUTA y el TOKEN.
# PASO 4: Generamos el archivo XML y PDF (Según especificaciones de la SUNAT) y te devolveremos INSTANTÁNEAMENTE los datos del documento generado.
# Para ver el documento generado ingresa a www.nubefact.com/login con tus datos de acceso, y luego a la opción "Ver Facturas, Boletas y Notas"
# IMPORTANTE: Enviaremos el XML generado a la SUNAT y lo almacenaremos junto con el PDF, XML y CDR en la NUBE para que tu cliente pueda consultarlo en cualquier momento, si así lo desea.
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/

package Facturacion;
import ClasesGlobales.FormatoNumerico;
import DAO.VentasDAO;
import Pojos.GuiaTipo;
import Pojos.Producto;
import Pojos.Ventas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ConsumingPost {
    
 private Ventas cab;
 private List<Producto> det;
 private List<GuiaTipo> guia;
    FormatoNumerico fn = new FormatoNumerico();
    VentasDAO daovent= new VentasDAO();
    public ConsumingPost(Ventas cab, List<Producto> det,List<GuiaTipo> guia) {
        this.cab = cab;
        this.det = det;
        this.guia=guia;
    }
    
    public ConsumingPost() {
    }
 
 
  
/*
#########################################################
#### PASO 1: CONSEGUIR LA RUTA Y TOKEN ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# - Regístrate gratis en www.nubefact.com/register
# - Ir la opción API (Integración).
# IMPORTANTE: Para que la opción API esté activada necesitas escribirnos a soporte@nubefact.com o llámanos al teléfono: 01 468 3535 (opción 2) o celular (WhatsApp) 955 598762.
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/

//  RUTA para enviar documentos
    


//    private String RUTA ="https://api.nubefact.com/api/v1/2c767109-4a72-49af-9c76-83901fe6abf3";  // ferreteria jhon
//   private String RUTA = "https://api.nubefact.com/api/v1/c9e4a215-45e8-41aa-9f4a-2d0a876ad9f4"; // ferreteria economica
    private String RUTA = "https://www.nubefact.com/api/v1/a9716fb6-02eb-43eb-9d1c-333ee2c7a27d"; //demo
  
//   private String RUTA = "https://api.nubefact.com/api/v1/69cac874-e935-4c2e-b350-8e50e391ff70";  // General Service
//    private String RUTA = "https://api.nubefact.com/api/v1/ce386603-0e47-41a5-a62a-4135668a5276"; // Walter vega
    
//  TOKEN para enviar documentos    

//   private String TOKEN = "6461fc7656294eb684bca7cd25abc357f073e34561394c0291f47cbff48c31de";  // General Service
    private String TOKEN = "e2f2c6f49ffd4526a667c504d91971b46aaf85d7610146a68b5ee0c90c203506"; //demo
//    private String TOKEN = "26d85bd35a904e2ab22592514d6a94ea56eeb0f09b2a4661841fea65c71134e7"; //ferreteria jhon
    
//    private String TOKEN = "ccb341824dac4472ac6fe6e3bc40e82cd32d4f9db1054a95bea11cec7315a0aa";  // ferreteria economica
//    private String TOKEN = "efa6b67f239a4fae9281ef5b2035b429ba3ef5c5b0bd4cfe9ed4a0327c11e8dd";  // walter vega
    public Ventas apiConsume(){
        boolean estado=false;
        try {
/*
#########################################################
#### PASO 2: GENERAR EL ARCHIVO PARA ENVIAR A NUBEFACT ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# - MANUAL para archivo JSON en el link: https://goo.gl/WHMmSb
# - MANUAL para archivo TXT en el link: https://goo.gl/Lz7hAq
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/            
            HttpClient cliente = new DefaultHttpClient();
            HttpPost post = new HttpPost(RUTA);
            post.addHeader("Authorization", "Token token=" + TOKEN); // Cabecera del token
            post.addHeader("Content-Type","application/json"); // Cabecera del Content-Type
                        
            
            JSONObject objetoCabecera = new JSONObject(); // Instancear el  segundario
            objetoCabecera.put("operacion","generar_comprobante");
            objetoCabecera.put("tipo_de_comprobante",String.valueOf(cab.getComprobante()));
            System.out.println("tipocomproba"+cab.getComprobante());
            objetoCabecera.put("serie",cab.getSerie());
            System.out.println("seriee"+cab.getSerie());
            objetoCabecera.put("numero",cab.getNumero());
            System.out.println("nunmeri"+cab.getNumero());
            objetoCabecera.put("sunat_transaction",cab.getSunattransaccion());
            System.out.println("sunat_transaction"+cab.getSunattransaccion());
            objetoCabecera.put("cliente_tipo_de_documento",cab.getClientetipodoc());
            System.out.println("clientetipodoc"+cab.getClientetipodoc());
            objetoCabecera.put("cliente_numero_de_documento",cab.getClientenumdoc());
            System.out.println("clientenDoc"+cab.getClientenumdoc());
            objetoCabecera.put("cliente_denominacion",cab.getClienteRS());
            System.out.println("clientedenom"+cab.getClienteRS());
            objetoCabecera.put("cliente_direccion",cab.getClientedirec());
            System.out.println("clientedi"+cab.getClientedirec());
            objetoCabecera.put("cliente_email",cab.getClienteemail());
            System.out.println("clienteemail"+cab.getClienteemail());
            objetoCabecera.put("cliente_email_1","");
            objetoCabecera.put("cliente_email_2","");
            System.out.println("fechaemision"+String.valueOf(cab.getFecha()));
            objetoCabecera.put("fecha_de_emision", String.valueOf(cab.getFecha()));
            objetoCabecera.put("fecha_de_vencimiento",String.valueOf(cab.getFecha()));
            System.out.println("moneda"+String.valueOf(cab.getModenaop()));
            objetoCabecera.put("moneda",String.valueOf(cab.getModenaop()));
            System.out.println("tipocambio"+fn.FormatoN(cab.getTipocambio()));
            objetoCabecera.put("tipo_de_cambio",fn.FormatoN(cab.getTipocambio()));
            System.out.println("porcentaje"+fn.FormatoN(cab.getPorcentajeigv()));
            objetoCabecera.put("porcentaje_de_igv",fn.FormatoN(cab.getPorcentajeigv()));
            objetoCabecera.put("descuento_global","");
            objetoCabecera.put("total_descuento","");
            objetoCabecera.put("total_anticipo","");
            System.out.println("totalgravada"+fn.FormatoN(cab.getSubtotal()));
            objetoCabecera.put("total_gravada",fn.FormatoN(cab.getSubtotal()));
            objetoCabecera.put("total_inafecta","");
            objetoCabecera.put("total_exonerada","");
            System.out.println("totaligv"+fn.FormatoN(cab.getTotaligv()));
            objetoCabecera.put("total_igv",fn.FormatoN(cab.getTotaligv()));
            objetoCabecera.put("total_gratuita","");
            objetoCabecera.put("total_otros_cargos","");
            System.out.println("total"+fn.FormatoN(cab.getTotal()));
            objetoCabecera.put("total",fn.FormatoN(cab.getTotal()));
            objetoCabecera.put("percepcion_tipo","");
            objetoCabecera.put("percepcion_base_imponible","");
            objetoCabecera.put("total_percepcion","");
            objetoCabecera.put("total_incluido_percepcion","");
            
            System.out.println("detraccion"+cab.isDetraccion());
            objetoCabecera.put("detraccion",cab.isDetraccion());
            objetoCabecera.put("observaciones", "");
            
            String doc_q_mod_ti = "";
            if(cab.getTipo_doc_modifica()!=null){
                doc_q_mod_ti=cab.getTipo_doc_modifica();
                 
            }
            System.out.println("docuemntomod"+doc_q_mod_ti);
            objetoCabecera.put("documento_que_se_modifica_tipo", doc_q_mod_ti);
            String doc_mod_serie="";
            
            if(cab.getDoc_modifica_serie()!=null){
                doc_mod_serie=cab.getDoc_modifica_serie();
            }
           
            System.out.println("documentomodserie"+doc_mod_serie);
            objetoCabecera.put("documento_que_se_modifica_serie", doc_mod_serie);
            
            String doc_mod_num="";
            if(cab.getDoc_modifica_numero()!=null){
                doc_mod_num=cab.getDoc_modifica_numero();
            }
            System.out.println("docmodnumero"+doc_mod_num);
            objetoCabecera.put("documento_que_se_modifica_numero",doc_mod_num);
            
            String tipo_not_cred="";
            if(cab.getTipo_nota_cred()!=null){
                tipo_not_cred=cab.getTipo_nota_cred();
            }
            
            System.out.println("tiponota"+tipo_not_cred);
            objetoCabecera.put("tipo_de_nota_de_credito", tipo_not_cred);
            
            String tipo_not_deb="";
            if(cab.getTipo_nota_deb()!=null){
                tipo_not_deb=cab.getTipo_nota_deb();
            }
            String condicion_pago="";
              if(cab.getCondicionpago()!=null){
                condicion_pago=cab.getCondicionpago();
            }
               String orden_compra="";
              if(cab.getOrdencompras()!=null){
                orden_compra=cab.getOrdencompras();
            }
                String placa_vehiculo="";
              if(cab.getPlacavehiculo()!=null){
                placa_vehiculo=cab.getPlacavehiculo();
            } 
             
              
            System.out.println("tipodeb"+tipo_not_deb);
            objetoCabecera.put("tipo_de_nota_de_debito", tipo_not_deb);
            objetoCabecera.put("enviar_automaticamente_a_la_sunat", "true");
            objetoCabecera.put("enviar_automaticamente_al_cliente", "true");
            objetoCabecera.put("codigo_unico", "");
            System.out.println("condiconpago"+condicion_pago);
            objetoCabecera.put("condiciones_de_pago", condicion_pago);
            objetoCabecera.put("medio_de_pago", "");
            System.out.println("placavehiculo"+placa_vehiculo);
            objetoCabecera.put("placa_vehiculo", placa_vehiculo);
            System.out.println("ordencompra "+orden_compra);
            objetoCabecera.put("orden_compra_servicio", orden_compra);
            objetoCabecera.put("tabla_personalizada_codigo", "");
            objetoCabecera.put("formato_de_pdf", "");
            
            
            JSONArray lista = new JSONArray();
            
            double valorU=0;
            for (Producto detalle : det )
            {
                JSONObject detalle_linea_1 = new JSONObject();
                System.out.println("unidmed"+detalle.getUnidabrev());
               detalle_linea_1.put("unidad_de_medida",detalle.getUnidabrev());
                System.out.println("codigo"+detalle.getCodigo());
               detalle_linea_1.put("codigo", detalle.getCodigo());
               System.out.println("codigosunat"+detalle.getCodigosunat());
               detalle_linea_1.put("codigo_producto_sunat", detalle.getCodigosunat());
               
                System.out.println("descripcion"+detalle.getDescripcion());
               detalle_linea_1.put("descripcion", detalle.getDescripcion());
                System.out.println("cantidad"+String.valueOf(detalle.getCantidad()));
                 
                
               detalle_linea_1.put("cantidad", String.valueOf(detalle.getCantidad()));
               valorU = detalle.getPrecio()/1.18;
                System.out.println("valoruni"+fn.FormatoN(valorU));
               detalle_linea_1.put("valor_unitario",fn.FormatoN(valorU));
                System.out.println("preciouni"+fn.FormatoN(detalle.getPrecio()));
               detalle_linea_1.put("precio_unitario", fn.FormatoN(detalle.getPrecio()));
               detalle_linea_1.put("descuento", "");
                System.out.println("subtotal"+ fn.FormatoN(detalle.getSubtotal()));
               detalle_linea_1.put("subtotal",fn.FormatoN(detalle.getSubtotal()));
                System.out.println("tipoigv"+detalle.getTipoigv());
               detalle_linea_1.put("tipo_de_igv",detalle.getTipoigv());
                System.out.println("igv"+fn.FormatoN(detalle.getIgv()));
               detalle_linea_1.put("igv", fn.FormatoN(detalle.getIgv()));
                System.out.println("total"+fn.FormatoN(detalle.getTotal()));
               detalle_linea_1.put("total",fn.FormatoN(detalle.getTotal()));
               detalle_linea_1.put("anticipo_regularizacion", "false");
               detalle_linea_1.put("anticipo_serie", "");
               detalle_linea_1.put("anticipo_documento_numero", "");
               
               lista.add(detalle_linea_1);
            }
            JSONArray listaguia = new JSONArray();
            
            for(GuiaTipo guias: guia){
                JSONObject guiadet = new JSONObject();
                guiadet.put("guia_tipo", guias.getIdentguia());
                guiadet.put("guia_serie_numero", guias.getSerienumero());
                
                listaguia.add(guiadet);
            }
           
            
//            JSONObject detalle_linea_2 = new JSONObject();
//            
//            detalle_linea_2.put("unidad_de_medida", "ZZ");
//            detalle_linea_2.put("codigo", "001");
//            detalle_linea_2.put("descripcion", "DETALLE DEL SERVICIO");
//            detalle_linea_2.put("cantidad", "5");
//            detalle_linea_2.put("valor_unitario", "20");
//            detalle_linea_2.put("precio_unitario", "23.60");
//            detalle_linea_2.put("descuento", "");
//            detalle_linea_2.put("subtotal", "100");
//            detalle_linea_2.put("tipo_de_igv", "1");
//            detalle_linea_2.put("igv", "18");
//            detalle_linea_2.put("total", "118");
//            detalle_linea_2.put("anticipo_regularizacion", "false");
//            detalle_linea_2.put("anticipo_serie", "");
//            detalle_linea_2.put("anticipo_documento_numero", "");
//            
//            
//            lista.add(detalle_linea_2);
            
            objetoCabecera.put("items", lista);
            objetoCabecera.put("guias",listaguia);
/*
#########################################################
#### PASO 3: ENVIAR EL ARCHIVO A NUBEFACT ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# SI ESTÁS TRABAJANDO CON ARCHIVO JSON
# - Debes enviar en el HEADER de tu solicitud la siguiente lo siguiente:
# Authorization = Token token="8d19d8c7c1f6402687720eab85cd57a54f5a7a3fa163476bbcf381ee2b5e0c69"
# Content-Type = application/json
# - Adjuntar en el CUERPO o BODY el archivo JSON o TXT
# SI ESTÁS TRABAJANDO CON ARCHIVO TXT
# - Debes enviar en el HEADER de tu solicitud la siguiente lo siguiente:
# Authorization = Token token="8d19d8c7c1f6402687720eab85cd57a54f5a7a3fa163476bbcf381ee2b5e0c69"
# Content-Type = text/plain
# - Adjuntar en el CUERPO o BODY el archivo JSON o TXT
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/

            StringEntity parametros = new StringEntity(objetoCabecera.toString(),"UTF-8");
            post.setEntity(parametros);
            HttpResponse response = cliente.execute(post);     
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));           
            String linea = "";
            if((linea = rd.readLine()) != null) {
                
                JSONParser parsearRsptaJson = new JSONParser();
                JSONObject json_rspta = (JSONObject) parsearRsptaJson.parse(linea);
 
/*
#########################################################
#### PASO 4: LEER RESPUESTA DE NUBEFACT ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# Recibirás una respuesta de NUBEFACT inmediatamente lo cual se debe leer, verificando que no haya errores.
# Debes guardar en la base de datos la respuesta que te devolveremos.
# Escríbenos a soporte@nubefact.com o llámanos al teléfono: 01 468 3535 (opción 2) o celular (WhatsApp) 955 598762
# Puedes imprimir el PDF que nosotros generamos como también generar tu propia representación impresa previa coordinación con nosotros.
# La impresión del documento seguirá haciéndose desde tu sistema. Enviaremos el documento por email a tu cliente si así lo indicas en el archivo JSON o TXT.
+++++++++++++++++++++++++++++++++++++++++++++++++++++++               
*/                
                if(json_rspta.get("errors") != null ){
                    System.out.println("Error => " + json_rspta.get("errors"));
                    JOptionPane.showMessageDialog(null,json_rspta.get("errors") ,"",JOptionPane.ERROR_MESSAGE);
                    cab=null;
                }else{
                    
                    estado=true;
                    JSONParser parsearRsptaDetalleOK = new JSONParser();
                    JSONObject json_rspta_ok = (JSONObject) parsearRsptaDetalleOK.parse(json_rspta.get("invoice").toString());
                    
                    System.out.println(json_rspta_ok.get("tipo_de_comprobante"));
                    System.out.println(json_rspta_ok.get("serie"));
                    System.out.println(json_rspta_ok.get("numero"));
                    System.out.println(json_rspta_ok.get("enlace"));
                    System.out.println(json_rspta_ok.get("aceptada_por_sunat"));
                    System.out.println(json_rspta_ok.get("sunat_description"));
                    System.out.println(json_rspta_ok.get("sunat_note"));
                    System.out.println(json_rspta_ok.get("sunat_responsecode"));
                    System.out.println(json_rspta_ok.get("sunat_soap_error"));
                    System.out.println(json_rspta_ok.get("pdf_zip_base64"));
                    System.out.println(json_rspta_ok.get("xml_zip_base64"));
                    System.out.println(json_rspta_ok.get("cdr_zip_base64"));
                    System.out.println("QR: "+json_rspta_ok.get("cadena_para_codigo_qr"));
                    System.out.println(json_rspta_ok.get("codigo_hash"));
                    /////////////////////////////////////////////////////////////////////////////
                    cab.setSerie(json_rspta_ok.get("serie").toString());
                    cab.setNumero(json_rspta_ok.get("numero").toString());
                    
                    if(json_rspta_ok.get("enlace")!=null){
                        cab.setEnlace(json_rspta_ok.get("enlace").toString());
                    }else {
                        cab.setEnlace("");
                    }
                    if(json_rspta_ok.get("aceptada_por_sunat")!=null){
                        cab.setAceptadasunat(Boolean.parseBoolean(json_rspta_ok.get("aceptada_por_sunat").toString()));
                    }else{
                        cab.setAceptadasunat(false);
                    }
                    if(json_rspta_ok.get("sunat_description")!=null){
                        cab.setSunatdescrip(json_rspta_ok.get("sunat_description").toString());
                    }else{
                        cab.setSunatdescrip("");
                    }
                    if(json_rspta_ok.get("sunat_note")!=null){
                        cab.setSunatnote(json_rspta_ok.get("sunat_note").toString());
                    }else{
                        cab.setSunatnote("");
                    
                    }
                    if(json_rspta_ok.get("sunat_responsecode")!=null){
                        cab.setSuantresposeode(json_rspta_ok.get("sunat_responsecode").toString());
                    }else{
                        cab.setSuantresposeode("");
                    
                    }
                    if(json_rspta_ok.get("sunat_soap_error")!=null){
                        cab.setSunatsoap(json_rspta_ok.get("sunat_soap_error").toString());
                    }else{
                        cab.setSunatsoap("");
                    
                    }
                    if(json_rspta_ok.get("pdf_zip_base64")!=null){
                        cab.setPdf_zip_base64(json_rspta_ok.get("pdf_zip_base64").toString());
                    }else{
                        cab.setPdf_zip_base64("");
                    
                    }
                    if(json_rspta_ok.get("xml_zip_base64")!=null){
                        cab.setXml_zip_base64(json_rspta_ok.get("xml_zip_base64").toString());
                    }else{
                        cab.setXml_zip_base64("");
                    
                    }
                    if(json_rspta_ok.get("cdr_zip_base64")!=null){
                        cab.setCdr_zip_base64(json_rspta_ok.get("cdr_zip_base64").toString());
                    }else{
                        cab.setCdr_zip_base64("");
                    
                    }
                    if(json_rspta_ok.get("cadena_para_codigo_qr")!=null){
                         cab.setQr(json_rspta_ok.get("cadena_para_codigo_qr").toString());
                    }else{
                         cab.setQr("");
                    
                    }
                    if(json_rspta_ok.get("codigo_hash")!=null){
                        cab.setCodhash(json_rspta_ok.get("codigo_hash").toString());
                    }else{
                        cab.setCodhash("");
                    
                    }
//                    daovent.actualizarsunat(cab);
                    
                } 
            }   
        } catch (UnsupportedEncodingException ex1) {
            System.err.println("Error UnsupportedEncodingException: "+ex1.getMessage());
            cab=null;
        } catch (IOException ex2) {
            System.err.println("Error IOException: "+ex2.getMessage());
            cab=null;
            JOptionPane.showMessageDialog(null,"Error al conectar con servidor sunat, intente nuevamente","",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex3){
            System.err.println("Exepction: "+ex3.getMessage());
            cab=null;
        }
        return cab;
    }
}
