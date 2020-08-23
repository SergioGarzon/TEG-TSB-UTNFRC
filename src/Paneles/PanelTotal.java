/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author equipo
 */
public final class PanelTotal extends JPanel {

    private ImageIcon imagen, imagenPosicional;
    private Auxiliares.Cursores cur;
    private int posicionX, posicionY;    

    public PanelTotal() {
        this.imagen = null;
        this.cur = null;
        this.iniciarComponentes();
    }
    
    
    public PanelTotal(ImageIcon imagen, Auxiliares.Cursores cur) {

        this.imagen = imagen;
        this.cur = cur;
        this.setCursor(this.cur.getCursor());

        this.iniciarComponentes();
    }
    
    private void iniciarComponentes() {
        this.imagenPosicional = null;

        this.setLayout(null);
        
        this.posicionX = 0;
        this.posicionY = 0;

    }
    
    public void setCursor(Auxiliares.Cursores cur) {
        this.cur = cur;
        this.setCursor(this.cur.getCursor());
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
        
    public void setImagenPosicional(ImageIcon imagenPosicional) {
        this.imagenPosicional = imagenPosicional;
    }

    public void setPosiciones(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    
    public void anchoAltoPanel(int ancho, int alto) {
        this.setSize(ancho, alto);
    }
    
    @Override
    public void paintComponent(Graphics g) {

        try {
            Dimension d = getSize();
            
            if(this.imagenPosicional == null) {                
                g.drawImage(imagen.getImage(), 0, 0, d.width, d.height, this); 
            }
            else {
                g.drawImage(imagen.getImage(), 0, 0, d.width, d.height, this);
                g.drawImage(imagenPosicional.getImage(), posicionX, posicionY, this);
            }
                
        } 
        catch (Exception e) {
        }
        
        


        setOpaque(false);

        super.paintComponent(g);
    }
}
