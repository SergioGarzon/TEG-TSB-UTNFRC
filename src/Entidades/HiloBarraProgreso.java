/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.awt.Font;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JProgressBar;

/**
 *
 * @author equipo
 */
public class HiloBarraProgreso extends Thread {

    private JProgressBar barra;
    private Clip sonido;
    private int contador;

    public HiloBarraProgreso(JProgressBar barra) {
        this.barra = barra;
        this.barra.setMinimum(0);
        this.barra.setMaximum(100);

        this.contador = 0;
        this.sonido = null;
    }

    @Override
    public void run() {

        String ruta = System.getProperty("user.dir");

        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Medieval1.wav")));

            float cont = -20f, valorSFondo = 0f;
            ADO.ConfiguracionesADO configuraciones = new ADO.ConfiguracionesADO();
            FloatControl gainControl;

            for (int i = 0; i < 11; i++) {

                if (cont >= 7) {
                    cont = +6;
                }

                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                }

                if (i == configuraciones.getDbSFondo()) {
                    valorSFondo = cont;
                }

                cont += 3;
            }

            gainControl = (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(valorSFondo); // Importante es para reducir el volumen de -20 a +10

            sonido.loop(25);

        } catch (Exception e) {
        }

        while (contador != 101) {

            try {
                Thread.sleep(500); //500
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloBarraProgreso.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.barra.setStringPainted(true);
            this.barra.setFont(new Font("Arial", Font.BOLD, 50));
            this.barra.setValue(contador);
            this.barra.setString(contador + "%");

            contador++;
        }

        sonido.stop();
    }

    public void cortarHilo() {
        contador = 101;
        sonido.stop();
    }

}
