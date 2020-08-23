/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author equipo
 */
public final class PanelAyuda extends JPanel {

    private ImageIcon imagen;
    private Auxiliares.Cursores cur;
    private boolean control;
    private String valor;
    
    public PanelAyuda() {        
        this.cur = new Auxiliares.Cursores();
        this.cur.setImgCursor(1);
        this.setCursor(cur.getCursor());
        this.setLayout(null);
        this.control = false;
        this.valor = "";
    }
    
    public void setImagen(String ruta) {
        this.imagen = new ImageIcon(System.getProperty("user.dir").concat("\\Recursos\\Imagenes\\ImagenPantallas\\" + ruta + ".png"));
    }
    
    public void anchoAltoPanel(int ancho, int alto) {
        this.setSize(ancho, alto);
    }
    
    public void setControl(boolean control) {
        this.control = control;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        try {
            Dimension d = getSize();
            g.drawImage(imagen.getImage(), 0, 0, d.width, d.height, this);
            
            if(control) {
                g.setColor(Color.red);
                g.fillOval(50, this.getHeight() / 5 + 25, 40, 40);
                g.setColor(Color.blue);
                g.fillOval(50, this.getHeight() / 5 + 80, 40, 40);
                g.setColor(Color.green);
                g.fillOval(50, this.getHeight() / 5 + 135, 40, 40);
                g.setColor(Color.yellow);
                g.fillOval(50, this.getHeight() / 5 + 190, 40, 40);
                g.setColor(Color.pink);
                g.fillOval(50, this.getHeight() / 5 + 245, 40, 40);
                g.setColor(Color.GRAY);
                g.fillOval(50, this.getHeight() / 5 + 300, 40, 40);
            }
        } catch (Exception e) {
        }

        setOpaque(false);

        super.paintComponent(g);
    }
}
