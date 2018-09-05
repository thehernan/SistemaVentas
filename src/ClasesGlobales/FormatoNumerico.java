/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author HERNAN
 */
public class FormatoNumerico {
    private String num;

    public FormatoNumerico() {
    }
    
    public String FormatoN(double n) {
     DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
     simbolos.setDecimalSeparator('.');   
     DecimalFormat nf=new DecimalFormat("#.00",simbolos);
     num = nf.format(n);
     return num;
   }
    
}
