/*
 * Clase para cifrar las contraseñas.
 */
package ClasesGlobales;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author m2
 */
public class CifradoMD5 {
    private static MessageDigest md;
    
     private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
//    public static String Cifrado(String pass){
//        try {
//            md = MessageDigest.getInstance("MD5");
//            byte[] passBytes = pass.getBytes();
//            md.reset();
//            byte[] digested = md.digest(passBytes);
//            StringBuilder sb = new StringBuilder();
//            for(int i=0;i<digested.length;i++){
//                sb.append(Integer.toHexString(0xff & digested[i]));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException ex) {
//            System.err.println("Error al encriptar MD5: "+ex.getMessage());
//        }
//        return null;
//    }
    
    
    public static String encriptaEnMD5(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
}
