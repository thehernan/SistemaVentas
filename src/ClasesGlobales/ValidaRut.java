/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesGlobales;

/**
 *
 * @author info2017
 */
public class ValidaRut {
    
     public boolean validarrut(String RUT){
  Boolean lDevuelve = false;
    try {
 
         int Ult= RUT.length();
         int Largo = RUT.length() -3;
         int Constante = 2;
         int Suma = 0;
         int Digito = 0;
        
             for (int i= Largo; i >=0; i--){
             
             Suma= Suma + Integer.parseInt(RUT.substring(i,i+1)) * Constante;
             Constante = Constante + 1 ;
             if (Constante == 8){
                 Constante =2; }
         }
         String Ultimo = RUT.substring(Ult-1).toUpperCase();
         Digito =11 - (Suma % 11);
         if (Digito==10 && Ultimo.equals("K")){
             lDevuelve=true;
   } else 
        { 
            if(Digito == 11 && Ultimo.equals("0")){
               lDevuelve=true;    
            } 
            else
            {    
               
                if (Digito == Integer.parseInt(Ultimo)){
            
                 lDevuelve=true;
           
        }
         
        
       
        }     
     
}

  }catch( NumberFormatException e){
  lDevuelve=false;
  }
  
 
        
return lDevuelve;
}
    
}
