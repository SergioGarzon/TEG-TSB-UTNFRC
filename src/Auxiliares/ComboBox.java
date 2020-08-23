/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;

/**
 *
 * @author equipo
 */
public class ComboBox extends JComboBox {

    private JComboBox combo;
    private Cursores cursores;

    public ComboBox(JComboBox combo) {
        this.combo = combo;
        this.cursores = new Cursores(1);

        this.combo.setForeground(Color.yellow);
        this.combo.setBackground(Color.black);
        this.combo.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
        this.combo.setCursor(this.getCursor());
    }
    

    @Override
    public void setBounds(int pos1, int pos2, int pos3, int pos4) {
        this.combo.setBounds(new Rectangle(pos1, pos2, pos3, pos4));
    }

    
    @Override
    public Cursor getCursor() {        
        return cursores.getCursor();
    }
}
