/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public class PanelPresentacion {
       
    private Auxiliares.Boton boton;
    private PanelTotal panelTotal;
    private Clip sonido;
    private Auxiliares.Cursores cursor;

    public PanelPresentacion(PanelTotal panelTotal) {        
        this.cursor = new Auxiliares.Cursores();
        this.panelTotal = panelTotal;
        this.cargarImagenes();
        this.sonidoJuego();
        this.panelTotalResetear();
    }

    private void panelTotalResetear() {
        this.panelTotal.setPosiciones(0, 0);
        this.panelTotal.setImagenPosicional(null);
    }
    
    private void cargarImagenes() {

        String ruta = System.getProperty("user.dir");

        ArrayList<String> imagenesCadenas = new ArrayList<String>();
        imagenesCadenas.add(ruta.concat("\\Recursos\\Imagenes\\ImagenPantallas\\" + 1 + ".jpg"));

        for (int i = 1; i < 5; i++) {
            imagenesCadenas.add(ruta.concat("\\Recursos\\Imagenes\\ImagenBotones\\" + i + ".png"));
        }

        ImageIcon imagen0, imagen1, imagen2, imagen3, imagen4;        
        imagen0 = imagen1 = imagen2 = imagen3 = imagen4 = null;
        ImageIcon[] imagenes = {imagen0, imagen1, imagen2, imagen3, imagen4};

        for (int i = 0; i < imagenesCadenas.size(); i++) {
            try {
                imagenes[i] = new ImageIcon(imagenesCadenas.get(i).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.panelTotal, e.getMessage());
            }
        }

        cursor.setImgCursor(1);
        this.panelTotal.setImagen(imagenes[0]);
        this.panelTotal.setCursor(cursor);

        this.IniciarControles(imagenes);
    }

    private void IniciarControles(ImageIcon[] imagenes) {

        JButton boton0, boton1, boton2, boton3;
        boton0 = boton1 = boton2 = boton3 = null;
        final JButton[] botones = {boton0, boton1, boton2, boton3};
        
        int puntero = 0, i = 0;        

        for (i = 0; i < botones.length; i++) {   
                           
            ImageIcon img = imagenes[i + 1];
             
            Image image = img.getImage();            
            image = image.getScaledInstance(this.panelTotal.getWidth() / 4, 50, Image.SCALE_SMOOTH);
            img.setImage(image);
            
            botones[i] = new JButton(img);
            boton = new Auxiliares.Boton(botones[i]);            
            boton.setBounds(this.panelTotal.getWidth()*3/8,
                    this.panelTotal.getHeight() / 5 + 150 + puntero, (this.panelTotal.getWidth() / 4), 50);            
            
            this.panelTotal.add(botones[i]);            

            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    eventoBotones(ae, botones);
                }
            });

            puntero += 85;
        }


        this.panelTotal.repaint();
    }
        
    private void eventoBotones(ActionEvent ae, JButton[] botones) {

        this.panelTotal.removeAll();
        this.panelTotal.repaint();
        
        if (ae.getSource() == botones[3]) {
            System.exit(0);
        }

        resetear();
        this.sonido.stop();

        try {
            Thread.sleep(1000);
        } catch (Exception excepcion) {
        }

        if (ae.getSource() == botones[0]) {
            PanelNuevaPartida panelNuevaPartida = new PanelNuevaPartida(this.panelTotal);
        }

        if (ae.getSource() == botones[1]) {
            PanelOpciones panelOpciones = new PanelOpciones(this.panelTotal);
        }

        if (ae.getSource() == botones[2]) {
            PanelCredito panelCreditos = new PanelCredito(this.panelTotal);
        }
    }

    private void resetear() {
        this.panelTotal.setImagen(null);

        Component[] componentes = this.panelTotal.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JButton) {
                ((JButton) componentes[i]).setVisible(false);
            }
        }

        this.panelTotal.repaint();
    }

    private void sonidoJuego() {
        String ruta = System.getProperty("user.dir");

        try {
            this.sonido = AudioSystem.getClip();
            this.sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Cancion.wav")));

            float cont = -20f, valorSFondo = 0f;
            ADO.ConfiguracionesADO configuraciones = new ADO.ConfiguracionesADO();
            FloatControl gainControl;
                        
            for (int i = 0; i < 11; i++) {

                if (cont >= 7) {
                    cont = +6;
                }

                try {
                    Thread.sleep(5);
                }
                catch(Exception e) {}
                
                if (i == configuraciones.getDbSFondo()) {
                    valorSFondo = cont;
                }

                cont += 3;
            }


            gainControl = (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(valorSFondo); // Importante es para reducir el volumen de -20 a +10
            
            this.sonido.loop(200);
            

        } catch (Exception e) {
        }
    }
}
