/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;

/**
 *
 * @author equipo
 */
public class Label extends JLabel {
    
    private JLabel label;
    private Cursores cursores;
    private Cursor cur;
    
    public Label(JLabel label) {
        this.label = label;       
        
        this.label.setForeground(Color.white);
        this.label.setFont(new Font("Arial Black", Font.BOLD, 30));
        
        this.cursores = new Cursores(1);
        
        
    }
    
    @Override
    public void setBounds(int pos1, int pos2, int pos3, int pos4) {
        this.label.setBounds(new Rectangle(pos1, pos2, pos3, pos4));
    }
    
    @Override
    public Cursor getCursor() {
        cur = cursores.getCursor();
        return cur;
    }
    
}
