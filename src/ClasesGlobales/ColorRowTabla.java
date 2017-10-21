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
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ColorRowTabla extends DefaultTableCellRenderer{
    private final int columna_patron ;
    private final double stockmin;
    
    public ColorRowTabla(int Colpatron,double stockm){
      this.columna_patron = Colpatron;
      stockmin=stockm;
    }
    
    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {        
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if( Double.parseDouble(table.getValueAt(row,columna_patron).toString())<=stockmin)
        {
            setBackground(Color.RED);
        }
        

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }

}