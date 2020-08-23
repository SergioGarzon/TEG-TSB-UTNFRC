/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author equipo
 */
public class PanelCargarJuego {

    private PanelTotal panelTotal;
    private JProgressBar barra;
    private Entidades.HiloBarraProgreso hilo;
    
    public PanelCargarJuego(PanelTotal panel) {
        this.panelTotal = panel;
        this.barra = null;
        this.hilo = null;
        
        this.cargarImagen();
        
        this.accionBotonOmitir();
        this.cargarJProgressBar();
        this.cargarJTextArea();

        this.iniciarBarra();

    }

    private void cargarJTextArea() {
        JTextArea area = new JTextArea();
        area.setFont(new Font("Segoe Script", Font.BOLD, 25));
        area.setOpaque(false);
        area.setBackground(new Color(0, 0, 0, 0));
        area.setForeground(Color.white);
        area.setVisible(true);
        area.setEditable(false);
        area.setEnabled(true);
        area.setBounds(new Rectangle((this.panelTotal.getWidth() / 30), ((this.panelTotal.getHeight()) * 16) / 100,
                (this.panelTotal.getWidth() * 93) / 100, (this.panelTotal.getWidth() * 55) / 100));

        this.panelTotal.add(area);

        this.iniciarTexto(area);
    }

    private void cargarJProgressBar() {
        barra = new JProgressBar();
        barra.setVisible(true);
        barra.setBounds(new Rectangle((this.panelTotal.getWidth() / 30), ((this.panelTotal.getHeight()) * 90) / 100,
                (this.panelTotal.getWidth() * 93) / 100, 50));

        this.panelTotal.add(barra);
    }

    private void accionBotonOmitir() {

        ImageIcon imagenBoton = new ImageIcon((System.getProperty("user.dir")).concat("\\Recursos\\Imagenes\\ImagenBotones\\" + 9 + ".png"));
        Image image = imagenBoton.getImage();
        image = image.getScaledInstance(this.panelTotal.getWidth() / 7, 50, Image.SCALE_SMOOTH);
        imagenBoton.setImage(image);

        JButton botonOmitir = new JButton(imagenBoton);
        Auxiliares.Boton boton = new Auxiliares.Boton(botonOmitir);
        boton.setBounds(this.panelTotal.getWidth() - this.panelTotal.getWidth() / 5, ((this.panelTotal.getHeight()) * 90) / 100 - 50, this.panelTotal.getWidth() / 7, 50);;

        this.panelTotal.add(botonOmitir);

        botonOmitir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBoton();
            }
        });
    }

    private void accionBoton() {
        this.panelTotal.setImagen(null);
        this.panelTotal.removeAll();
        this.hilo.cortarHilo();
        
        PanelJuegoDefinitivo panel = new PanelJuegoDefinitivo(panelTotal);
    }

    private void iniciarTexto(JTextArea area) {
        Entidades.HiloTexto hiloTexto = new Entidades.HiloTexto(area);
        hiloTexto.start();
    }

    private void iniciarBarra() {
        hilo = new Entidades.HiloBarraProgreso(this.barra);
        hilo.start();
    }

    private void cargarImagen() {

        ADO.AccesoDatos acceso = new ADO.AccesoDatos();

        String consulta = "SELECT idMision FROM Mision WHERE seleccionada = 1";

        try {
            ResultSet resultado = acceso.consultar(consulta);

            int valor = Integer.parseInt(resultado.getString("idMision"));

            ImageIcon imagen = new ImageIcon((System.getProperty("user.dir")).concat("\\Recursos\\Imagenes\\ImagenMisiones\\" + valor + ".png"));

            this.panelTotal.setImagen(imagen);
            this.panelTotal.repaint();

            acceso.Desconectar();
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        } catch (ClassNotFoundException e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, e3.getMessage());
        }

    }
}
