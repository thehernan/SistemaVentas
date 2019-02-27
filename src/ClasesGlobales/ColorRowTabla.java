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
//    private final double cantidad;
    private final int Colstockmax;
    private final int Colstockm;
    
    public ColorRowTabla(int Colpatron,int Colstockm,int Colstockmax){ // double stockm,double stockmax
      this.columna_patron = Colpatron;
//      this.cantidad = cant;
      this.Colstockmax=Colstockmax;
      this.Colstockm=Colstockm;
    }
    
    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {        
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if( Double.parseDouble(table.getValueAt(row,columna_patron).toString())<=Double.parseDouble(table.getValueAt(row,Colstockm).toString()))
//        if( cantidad<=stockmin)
        {
            setBackground(new Color(248, 193, 186));
        }
           if( Double.parseDouble(table.getValueAt(row,columna_patron).toString())>Double.parseDouble(table.getValueAt(row,Colstockmax).toString()))
//        if( cantidad<=stockmin)
        {
            setBackground(new Color(183, 219, 243));
        }
//        if( cantidad<=stockmax)
//        {
//            setBackground(new Color(183, 219, 243));
//        }
        

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }

}