/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public final class Sonidos {

    private Clip sonidoFondo1, sonidoFondo2, sonidoFondo3;
    private ADO.ConfiguracionesADO configuraciones;
    private FloatControl gainControl, gainControl2, gainControl3;
    private String ruta;
    
    public Sonidos() {}

    private void cargarSonidoFondo() {

        float valorSFondo = 0f;
        
        this.sonidoFondo1 = null;
        this.sonidoFondo2 = null;
        
        try {

            if (this.configuraciones.getsFondo() == 1) {

                this.sonidoFondo1 = AudioSystem.getClip();
                this.sonidoFondo2 = AudioSystem.getClip();
                this.sonidoFondo3 = AudioSystem.getClip();                        

                this.sonidoFondo1.open(AudioSystem.getAudioInputStream(new File(ruta.concat("\\Recursos\\Sonidos\\Cancion.wav"))));
                this.sonidoFondo2.open(AudioSystem.getAudioInputStream(new File(ruta.concat("\\Recursos\\Sonidos\\Medieval1.wav"))));
                this.sonidoFondo3.open(AudioSystem.getAudioInputStream(new File(ruta.concat("\\Recursos\\Sonidos\\Medieval2.wav"))));
                
                float cont = -20f;

                for (int i = 0; i < 11; i++) {

                    if (cont >= 7) {
                        cont = +6;
                    }

                    if (i == this.configuraciones.getDbSFondo()) {
                        valorSFondo = cont;
                    }

                    cont += 3;
                }


                gainControl = (FloatControl) this.sonidoFondo1.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(valorSFondo); // Importante es para reducir el volumen de -20 a +10

                gainControl2 = (FloatControl) this.sonidoFondo2.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl2.setValue(valorSFondo); // Importante es para reducir el volumen de -20 a +10

                gainControl3 = (FloatControl) this.sonidoFondo3.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl3.setValue(valorSFondo); // Importante es para reducir el volumen de -20 a +10
            }

        } catch (LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedAudioFileException e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        } catch (IOException e3) {
            JOptionPane.showMessageDialog(null, e3.getMessage());
        }

    }
    
}
