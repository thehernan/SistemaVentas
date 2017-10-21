/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author info2017
 */
public class GenerarCodigoBarras {
     public void generarcodigobarras(String Salida,String codigo){
       
        try {
            Document document= new Document(PageSize.A4,36,36,10,10);
          
            PdfWriter pw = PdfWriter.getInstance(document,new FileOutputStream(Salida));
            document.open();
            
            
            //document.add(getheader(header));
//            Image imagen=Image.getInstance(img);
//            imagen.scaleAbsolute(130,150);
//            imagen.setAlignment(Element.ALIGN_CENTER);
//            document.add(imagen);
            //document.add(getInfo(info)); document.add(getBarcode(document, pw, codigo).setAlignment(Element.ALIGN_RIGHT))
            //RIGHT
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             document.add(getBarcode(document, pw, codigo));
             //CENTER
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             document.add(getBarcode0(document, pw, codigo));
             //LEFT
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
             document.add(getBarcode1(document, pw, codigo));
            document.close();
        } catch (Exception e) {
        }
    }
    
     private Image getBarcode(Document document, PdfWriter pw,String codigo){
        PdfContentByte cimp= pw.getDirectContent();
        Barcode128 code128=new Barcode128();
        code128.setCode((codigo));
        code128.setCodeType(Barcode128.CODE128);
        code128.setTextAlignment(Element.ALIGN_CENTER);
        
        Image image = code128.createImageWithBarcode(cimp, BaseColor.BLACK, BaseColor.BLACK);
        float scale = ((document.getPageSize().getWidth()-document.leftMargin()-document.rightMargin()-0)/image.getWidth()*25);
        image.scalePercent(scale);
        image.setAlignment(Element.ALIGN_RIGHT);
        return image;
    
    }
     private Image getBarcode0(Document document, PdfWriter pw,String codigo){
        PdfContentByte cimp= pw.getDirectContent();
        Barcode128 code128=new Barcode128();
        code128.setCode((codigo));
        code128.setCodeType(Barcode128.CODE128);
        code128.setTextAlignment(Element.ALIGN_CENTER);
        
        Image image = code128.createImageWithBarcode(cimp, BaseColor.BLACK, BaseColor.BLACK);
        float scale = ((document.getPageSize().getWidth()-document.leftMargin()-document.rightMargin()-0)/image.getWidth()*25);
        image.scalePercent(scale);
        image.setAlignment(Element.ALIGN_CENTER);
        return image;
    
    }
      private Image getBarcode1(Document document, PdfWriter pw,String codigo){
        PdfContentByte cimp= pw.getDirectContent();
        Barcode128 code128=new Barcode128();
        code128.setCode((codigo));
        code128.setCodeType(Barcode128.CODE128);
        code128.setTextAlignment(Element.ALIGN_CENTER);
        
        Image image = code128.createImageWithBarcode(cimp, BaseColor.BLACK, BaseColor.BLACK);
        float scale = ((document.getPageSize().getWidth()-document.leftMargin()-document.rightMargin()-0)/image.getWidth()*25);
        image.scalePercent(scale);
        image.setAlignment(Element.ALIGN_LEFT);
        return image;
    
    }
    
    
}
