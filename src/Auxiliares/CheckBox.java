/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;

/**
 *
 * @author equipo
 */
public class CheckBox extends JCheckBox {
    
    private JCheckBox chkBox;
    private Cursores cursores;
    
    public CheckBox(JCheckBox chkBox) {
        this.chkBox = chkBox;
        this.cursores = new Cursores(1);
        
        this.chkBox.setForeground(Color.yellow);
        this.chkBox.setOpaque(false);
        this.chkBox.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
        this.chkBox.setBorderPainted(false);
    }
    
    @Override
    public void setSelected(boolean b) {
        this.chkBox.setSelected(b);
    }
    
    @Override
    public void setText(String texto) {
        this.chkBox.setText(texto);
    }
    
    @Override
    public void setBounds(int pos1, int pos2, int pos3, int pos4) {
        this.chkBox.setBounds(new Rectangle(pos1, pos2, pos3, pos4));
    }
    
    @Override
    public Cursor getCursor() {
        return cursores.getCursor();
    }
}
