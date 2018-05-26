/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HERNAN
 */
public class TestConexionServer {
  
    public boolean ping()  {
    boolean test = false;
//    String server = "localhost";  // Ip de la máquina remota        
//        try {
//           InetAddress ping = InetAddress.getByName(server);
//            if(ping.isReachable(5000)){
//                System.out.println(server+" - responde!");
//                test=true;
//            }else { 
//                System.out.println(server+" - no responde!");
//                test=false;
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(TestConexionServer.class.getName()).log(Level.SEVERE, null, ex);
//            test=false;
//        }
//    return test;
//    } 
        
        
        
        
        
        
        
//        String ip = "138.36.239.172"; //Any IP Address on your network / Web
//        String command=("ping " + ip);
//        try {
//        Process p = Runtime.getRuntime().exec(command);
//        BufferedReader inputStream = new BufferedReader(
//                new InputStreamReader(p.getInputStream()));
//
//        String s = "";
//        
//        
//        // reading output stream of the command
//        
//        while ((s = inputStream.readLine()) != null) {
//            System.out.println(s);
//            System.out.println("estable");
//        }
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
        
        
        
        
    String host="localhost";
    boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

    ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows? "-n" : "-c", "1", host);
    Process proc = null;
        try {
            proc = processBuilder.start();
        } catch (IOException ex) {
            Logger.getLogger(TestConexionServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    int returnVal = 0;
        try {
            returnVal = proc.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(TestConexionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
//    return returnVal == 0;
        System.out.println("res "+returnVal);
        if(returnVal==0)
           test=true;
        else 
           test=false;
                
       
   
return test;
}
    
  
}
