/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;

/**
 *
 * @author equipo
 */
public final class TextField extends JTextField {
    
    private JTextField texto;
    private Cursores cursores;
    private Cursor cur;
    
    public TextField(JTextField texto) {
        this.texto = texto;
        this.cursores = new Cursores(1);
        
        this.texto.setEditable(false);
        this.texto.setCursor(this.getCursor());
        this.texto.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
        this.texto.setHorizontalAlignment(JTextField.CENTER); 
        this.texto.setBackground(Color.black);
        this.texto.setForeground(Color.yellow);
    }
    
    @Override
    public void setText(String texto) {
        this.texto.setText(texto);
    }
    
    @Override
    public void setEnabled(boolean b) {
        this.texto.setEnabled(b);
    }
    
    
    @Override
    public void setBounds(int pos1, int pos2, int pos3, int pos4) {
        this.texto.setBounds(new Rectangle(pos1, pos2, pos3, pos4));
    }
    
    @Override
    public Cursor getCursor() {
        cur = cursores.getCursor();
        return cur;
    }
    
    
}
