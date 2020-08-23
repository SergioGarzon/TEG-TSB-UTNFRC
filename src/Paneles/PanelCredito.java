/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;



/**
 *
 * @author equipo
 */
public class PanelCredito {

    private Auxiliares.Boton boton;
    private ImageIcon imagen, imagenSalir, imagenIntegrantes;        
    private final PanelTotal panel;
    private JButton botonSalir;
    private int posicionX, posicionY;
    
    
    public PanelCredito(final PanelTotal panel) {
        
        this.panel = panel;
        
        this.cargarImagen();
        
        this.resetear();
        
        this.posicionX = -575;
        this.posicionY = this.panel.getHeight() / 4;
        
        this.panel.setImagenPosicional(this.imagenIntegrantes);
        
        this.botonSalir = new JButton(this.imagenSalir);
        this.boton = new Auxiliares.Boton(this.botonSalir);        
        this.boton.setBounds(this.panel.getWidth() * 3/8, this.panel.getHeight() - (this.panel.getHeight() / 5),
                (this.panel.getWidth() / 4), 50);
        
        this.panel.add(this.botonSalir);
                
        TimerTask tiempo;
        tiempo = new TimerTask() {
            @Override
            public void run() {

                boolean bandera = true;

                while (bandera) {
                    try {
                        posicionX++;
                        
                        Thread.sleep(2);
                        
                        panel.setPosiciones(posicionX, posicionY);                                                
                        panel.repaint();

                        if (posicionX > 100) {
                            bandera = false;
                            posicionX--;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        };
        Timer reloj = new Timer();
        reloj.scheduleAtFixedRate(tiempo, 4, 1); //1000 , 400
        
        this.botonSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                volverMenuPrincipal();
            }
        });
        
        
    }
    
    private void volverMenuPrincipal() {
        
        this.panel.removeAll();
        this.panel.setImagen(null);
        this.resetear();
        
        PanelPresentacion panel2 = new PanelPresentacion(this.panel);
    }
    
    private void resetear() {
        this.panel.setPosiciones(0, 0); 
        this.panel.setImagenPosicional(null); 
    }
    
    private void cargarImagen() {
        
        try {
            String ruta = System.getProperty("user.dir");
            imagen = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenPantallas\\" + 2 + ".jpg"));    
            this.imagenIntegrantes = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenPantallas\\" + 3 + ".png"));
            
            this.imagenSalir = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenBotones\\4.png")); 
            
            Image image = imagenSalir.getImage();            
            image = image.getScaledInstance(this.panel.getWidth() / 4, 50, Image.SCALE_SMOOTH);
            imagenSalir.setImage(image);
            
        }
        catch(Exception e) {}
        
        this.panel.setImagen(imagen);        
        this.panel.repaint();
    }
    
    
    
}
