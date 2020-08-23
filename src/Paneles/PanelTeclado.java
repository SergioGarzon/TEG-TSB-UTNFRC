/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Auxiliares.Cursores;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author equipo
 */
public class PanelTeclado extends JPanel {    
    
    private ImageIcon imagen;    
    
    public PanelTeclado(PanelTotal panelTotal) { 
        
        String ruta = System.getProperty("user.dir");
        
        try {
            this.imagen = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenPantallas/tableroComandos.png"));            
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "panel teclado");
        }
        
        this.setBounds(new Rectangle(0, (panelTotal.getHeight() / 2) + (panelTotal.getHeight() / 4), panelTotal.getWidth(), 250));
              
        this.setLayout(null);
        
        this.cargarCursor(1);
    }
    
    public void cargarCursor(int num) {        
        Cursores cursores = new Cursores(num);
        this.setCursor(cursores.getCursor());            
        
    }         
    
    @Override
    public void paintComponent(Graphics g) {

        try {
            Dimension d = getSize();
            g.drawImage(imagen.getImage(), 0, 0, d.width, d.height, this);

        } catch (Exception e) {
        }

        setOpaque(false);
        super.paintComponent(g);
    }
    
    
    
}
