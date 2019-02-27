/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;

/**
 *
 * @author HERNAN
 */


import ClasesGlobales.FormatoNumerico;
import DAO.VentasDAO;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConsultarEstado {
    private Ventas cab;
 private List<Producto> det;
    FormatoNumerico fn = new FormatoNumerico();
    VentasDAO daovent= new VentasDAO();
    public ConsultarEstado(Ventas cab) { // List<Producto> det
        this.cab = cab;
//        this.det = det;
    }

    public ConsultarEstado() {
    }
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
            objetoCabecera.put("operacion","consultar_comprobante");
            objetoCabecera.put("tipo_de_comprobante",String.valueOf(cab.getComprobante()));
            System.out.println("tipocomproba"+cab.getComprobante());
            objetoCabecera.put("serie",cab.getSerie());
            System.out.println("seriee"+cab.getSerie());
            objetoCabecera.put("numero",cab.getNumero());
            System.out.println("nunmeri"+cab.getNumero());
           
            
  

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
