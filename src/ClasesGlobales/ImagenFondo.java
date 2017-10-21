
package ClasesGlobales;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;




public class ImagenFondo implements Border{
    private BufferedImage image;

    public ImagenFondo(BufferedImage image){
	this.image=image;}

public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
int x0= x+(width-image.getWidth())/2;
int y0= y +(height-image.getHeight())/2;
g.drawImage(image, x0,y0,null);}

public boolean isBorderOpaque(){

return true;}

    @Override
    public Insets getBorderInsets(Component c) {
       return new Insets(0,0,0,0); //To change body of generated methods, choose Tools | Templates.
    }

}





