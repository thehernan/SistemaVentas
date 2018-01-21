/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;


/**
 *
 * @author Lenovo
 */
public class GeneraCarnet {
    private Font fuenteBold = new Font(Font.FontFamily.COURIER,8,Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER,14,Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.COURIER,8,Font.ITALIC);
    
    public void generarcarnet(String header,String info,String footer,
            byte [] img,String Salida,String codigo){
       
        try {
            Document document= new Document(PageSize.A7,30,30,8,8);
            PdfWriter pw = PdfWriter.getInstance(document,new FileOutputStream(Salida));
            document.open();
            document.add(getheader(header));
            Image imagen=Image.getInstance(img);
            imagen.scaleAbsolute(130,150);
            imagen.setAlignment(Element.ALIGN_CENTER);
            document.add(imagen);
            document.add(getInfo(info));
            document.add(getBarcode(document, pw, codigo));
//            document.add(getFooter(footer));
            document.close();
        } catch (Exception e) {
        }
    }
    
    
    private Paragraph getheader(String texto){
        Paragraph p= new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteBold);
        p.add(c);
        return p;
    }
    
    private Paragraph getInfo(String texto){
        Paragraph p= new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteItalic);
        p.add(c);
        return p;
    }
    
    private Paragraph getFooter(String texto){
        Paragraph p= new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    
    private Image getBarcode(Document document, PdfWriter pw,String codigo){
        PdfContentByte cimp= pw.getDirectContent();
        Barcode128 code128=new Barcode128();
        code128.setCode((codigo));
        code128.setCodeType(Barcode128.CODE128);
        code128.setTextAlignment(Element.ALIGN_CENTER);
        
        Image image = code128.createImageWithBarcode(cimp, BaseColor.BLACK, BaseColor.BLACK);
        float scale = ((document.getPageSize().getWidth()-document.leftMargin()-document.rightMargin()-0)/image.getWidth()*80);
        image.scalePercent(scale);
        image.setAlignment(Element.ALIGN_CENTER);
        return image;
    
    }
//    private String formatearcodigo (String num){
//    NumberFormat form= new DecimalFormat("0000000000");
//    return form.format((num !=null) ? Integer.parseInt(num):0000000000);
//    }
    
    public String formatoruccarne(String rutv){ //cambia formato del rut para presentacion
    String rut,formatrut,verificador;
   if (rutv.length()==9){ // rut sin guion
   formatrut= rutv.substring(0, 8);
   verificador=rutv.substring(8,9);
   rut = formatrut+"-"+verificador;
   } else if (rutv.length()==10){ // rut con guion
   formatrut= rutv.substring(0, 8);
   verificador=rutv.substring(9,10);
   rut = formatrut+verificador;
   }else {
   rut=rutv;
   }
   
   return rut;
    }
        
    }

