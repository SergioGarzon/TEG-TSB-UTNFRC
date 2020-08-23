/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.JButton;

/**
 *
 * @author equipo
 */
public class Boton extends JButton {
    
    private JButton boton;
    private Cursores cursores;
    private Cursor cur;
    
    public Boton(JButton boton) {
        
        this.boton = boton;
        this.cursores = new Cursores(1);
        
        this.boton.setEnabled(true);
        this.boton.setOpaque(false);
        this.boton.setContentAreaFilled(false);
        this.boton.setBorderPainted(true);
        this.boton.setVisible(true);    
        this.boton.setCursor(this.getCursor());
    }
    
    @Override
    public void setBounds(int pos1, int pos2, int pos3, int pos4) {
        this.boton.setBounds(new Rectangle(pos1, pos2, pos3, pos4));
    }
    
    @Override
    public Cursor getCursor() {
        cur = cursores.getCursor();
        return cur;
    }
    
}
