/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 *
 * @author equipo
 */
public class PanelNuevaPartida {

    private ImageIcon imagenContinuar, imagenCancelar, imagenFondo, imagenIcono;
    private Auxiliares.Boton boton;
    private Auxiliares.TextField textos;
    private Auxiliares.CheckBox chkbox;
    private Auxiliares.ComboBox combo;
    private JComboBox comboMisiones;
    private String ruta;
    private PanelTotal panel;
    private Clip sonido;

    public PanelNuevaPartida(PanelTotal panel) {

        this.ruta = System.getProperty("user.dir");
        this.panel = panel;
        this.cargarImagenes();
        this.cargarTextFields();
        this.cargarCombos();
    }

    private void cargarImagenes() {
        try {
            this.imagenFondo = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenPantallas\\iniciarJuegoElegirMisionesJugadores.jpg"));
            this.imagenContinuar = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenBotones\\botonContinuar.png"));
            this.imagenCancelar = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenBotones\\6.png"));
            this.imagenIcono = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\ImagenCursores\\iconoMensaje.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.panel, e.getMessage());
        }

        this.panel.setImagen(imagenFondo);
        this.panel.repaint();
    }

    private void cargarTextFields() {

        int x = this.panel.getHeight();

        JTextField campoJugador1, campoJugador2, campoJugador3, campoJugador4, campoJugador5, campoJugador6;
        campoJugador1 = campoJugador2 = campoJugador3 = campoJugador4 = campoJugador5 = campoJugador6 = null;

        JTextField[] texto = {campoJugador1, campoJugador2, campoJugador3, campoJugador4, campoJugador5, campoJugador6};
        int contador = 120;

        for (int i = 0; i < texto.length; i++) {
            texto[i] = new JTextField();
            this.textos = new Auxiliares.TextField(texto[i]);
            this.textos.setEnabled(false);
            this.textos.setBounds((this.panel.getWidth() / 2), contador, 350, 40);

            this.panel.add(texto[i]);

            texto[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    accionJTextField();
                }
            });

            contador += 80;
        }

        this.cargarCheckBoxs(texto);
    }

    private void cargarCombos() {
        this.comboMisiones = new JComboBox();
        this.combo = new Auxiliares.ComboBox(this.comboMisiones);
        this.combo.setBounds((this.panel.getWidth() / 2),
                (this.panel.getHeight() / 19), 520, 40);
        this.comboMisiones.setSelectedIndex(-1);
        this.comboMisiones.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));

        ADO.MisionesADO mision = new ADO.MisionesADO();

        ArrayList nombres = mision.getAllMisiones();
        for (Iterator it = nombres.iterator(); it.hasNext();) {
            Entidades.Mision ms = (Entidades.Mision) it.next();
            this.comboMisiones.addItem(new Entidades.Item(ms.getIdMision(), ms.getNombre()));
        }

        this.panel.add(this.comboMisiones);

    }

    private void cargarCheckBoxs(JTextField[] texto) {

        final JTextField[] campos = texto;

        JCheckBox activarCampoJugador1, activarCampoJugador2, activarCampoJugador3, activarCampoJugador4,
                activarCampoJugador5, activarCampoJugador6;

        activarCampoJugador1 = activarCampoJugador2 = activarCampoJugador3 = activarCampoJugador4
                = activarCampoJugador5 = activarCampoJugador6 = null;

        final JCheckBox[] chkBox = {activarCampoJugador1, activarCampoJugador2, activarCampoJugador3,
            activarCampoJugador4, activarCampoJugador5, activarCampoJugador6};

        int contador = 120;

        for (int i = 0; i < chkBox.length; i++) {
            chkBox[i] = new JCheckBox();
            this.chkbox = new Auxiliares.CheckBox(chkBox[i]);
            this.chkbox.setBounds((this.panel.getWidth() / 2) + 375, contador, 350, 40);
            chkBox[i].setText("Activar");
            chkBox[i].setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));

            this.panel.add((Component) chkBox[i]);

            chkBox[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    accionCheckBox(ae, chkBox, campos);
                }
            });

            contador += 80;

        }

        this.cargarBotones(campos);
    }

    private void cargarBotones(JTextField[] campos) {

        final JTextField[] texto = campos;

        JButton botonContinuar, botonCancelar;
        botonContinuar = botonCancelar = null;

        final JButton[] botones = {botonContinuar, botonCancelar};
        ImageIcon[] imagenes = {this.imagenContinuar, this.imagenCancelar};

        int puntero = 580;

        for (int i = 0; i < botones.length; i++) {

            ImageIcon img = imagenes[i];

            Image image = img.getImage();
            image = image.getScaledInstance(this.panel.getWidth() / 4, 50, Image.SCALE_SMOOTH);
            img.setImage(image);

            botones[i] = new JButton(img);
            boton = new Auxiliares.Boton(botones[i]);
            this.boton.setBounds((this.panel.getWidth() / 2) + 310,
                    puntero, this.panel.getWidth() / 4, 50);

            this.panel.add(botones[i]);

            puntero += 60;

            botones[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    accionBotones(ae, botones, texto);
                }
            });
        }
    }

    private void accionBotones(ActionEvent e, JButton[] jb, JTextField[] texto) {

        if (e.getSource() == jb[1]) {
            this.resetear();
            this.panel.setImagen(null);
            this.limpiarCampos();

            PanelPresentacion panelPresentacion = new PanelPresentacion(this.panel);
        }

        if (e.getSource() == jb[0]) {

            if (texto[0].getText().compareTo("") == 0 || texto[1].getText().compareTo("") == 0) {
                Toolkit.getDefaultToolkit().beep();
                JLabel mensaje = new JLabel();
                mensaje.setText("Debe ingresar al menos al jugador 1 y jugador 2");
                mensaje.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
                JOptionPane.showMessageDialog(this.panel, mensaje, "Atención!", JOptionPane.INFORMATION_MESSAGE, this.imagenIcono);
                return;
            } else {

                Component[] componentes = this.panel.getComponents();

                LinkedList<String> nombreJugador = new LinkedList();

                for (int i = 0; i < componentes.length; i++) {
                    if (componentes[i] instanceof JTextField) {

                        JTextField text = (JTextField) componentes[i];

                        if (text.getText().compareTo("") != 0) {
                            nombreJugador.add(text.getText());
                        }
                    }
                }

                for (int i = 0; i < nombreJugador.size(); i++) {

                    for(int j = i + 1 ; j < nombreJugador.size() ; j++) {
                        if(nombreJugador.get(i).equalsIgnoreCase(nombreJugador.get(j))) {
                            Toolkit.getDefaultToolkit().beep();
                            JLabel mensaje = new JLabel();
                            mensaje.setText("No se puede ingresar nombre de jugadores repetidos");
                            mensaje.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
                            JOptionPane.showMessageDialog(this.panel, mensaje, "Atención!", JOptionPane.INFORMATION_MESSAGE, this.imagenIcono);
                            return;
                        }
                    }
                }

                String insercion = "";
                ADO.AccesoDatos acceso = new ADO.AccesoDatos();

                for (int i = 0; i < componentes.length; i++) {
                    if (componentes[i] instanceof JTextField) {
                        if (((JTextField) componentes[i]).getText().compareTo("") != 0) {
                            insercion = "INSERT INTO Jugador(nomJugador, puntaje, fichas) VALUES ('"
                                    + ((JTextField) componentes[i]).getText() + "', 0, 5)";

                            acceso.Ejecutar(insercion);
                        }
                    }
                }

                int idMision = this.comboMisiones.getSelectedIndex() + 1;

                insercion = "UPDATE Mision SET seleccionada = 1 WHERE idMision = " + idMision;

                acceso.Ejecutar(insercion);

                acceso.Desconectar();

                this.resetear();
                this.panel.setImagen(null);
                this.panel.removeAll();
                this.panel.repaint();

                PanelCargarJuego panelCargarJuego = new PanelCargarJuego(panel);
            }
        }
    }

    private void accionJTextField() {
        this.sonidoJuego();
    }

    private void accionCheckBox(ActionEvent e, JCheckBox[] chkBox, JTextField[] texto) {

        for (int i = 0; i < chkBox.length; i++) {
            if (e.getSource() == chkBox[i]) {
                if (chkBox[i].isSelected()) {
                    this.habilitarCampos(texto[i]);
                } else if (!chkBox[i].isSelected()) {
                    this.deshabilitarCampos(texto[i]);
                }
            }
        }
    }

    private void habilitarCampos(JTextField texto) {
        texto.setEnabled(true);
        texto.setEditable(true);
        texto.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
        texto.setHorizontalAlignment(JTextField.LEFT);
    }

    private void deshabilitarCampos(JTextField texto) {
        texto.setEnabled(false);
        texto.setText("");
    }

    private void resetear() {

        Component[] componentes = this.panel.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JButton) {
                ((JButton) componentes[i]).setVisible(false);
            }

            if (componentes[i] instanceof JTextField) {
                ((JTextField) componentes[i]).setText("");
                ((JTextField) componentes[i]).setVisible(false);
            }

            if (componentes[i] instanceof JComboBox) {
                ((JComboBox) componentes[i]).setSelectedIndex(-1);
                ((JComboBox) componentes[i]).setVisible(false);
            }

            if (componentes[i] instanceof JCheckBox) {
                ((JCheckBox) componentes[i]).setEnabled(false);
                ((JCheckBox) componentes[i]).setVisible(false);
            }
        }
    }

    private void limpiarCampos() {

        ADO.AccesoDatos acceso = new ADO.AccesoDatos();

        String insercion = "DELETE FROM Jugador";
        acceso.Ejecutar(insercion);

        insercion = "UPDATE Mision SET seleccionada = 0";
        acceso.Ejecutar(insercion);

        acceso.Desconectar();

    }

    private void sonidoJuego() {

        try {
            this.sonido = AudioSystem.getClip();
            this.sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\escritura.wav")));

            this.sonido.loop(0);

        } catch (LineUnavailableException e) {
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        }
    }
}
