/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;

import Paneles.PanelAyuda;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author notebook
 */
public class ReglasJuego extends JDialog {

    private JDialog ventana;
    private JTextArea texto;
    private JFrame padre;
    private PanelAyuda panelAyuda;

    public ReglasJuego(JFrame padre, boolean control) {
        super(padre, control);

        this.padre = padre;
        this.padre.setEnabled(false);
        this.panelAyuda = new PanelAyuda();

        ventana = new JDialog();
        ventana.setSize(950, 650);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(this);
        ventana.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Informacion sobre TEG");
        ventana.setLayout(null);

        Image imagenIcono = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/Recursos/imagenes/ImagenCursores/icono.png");
        ventana.setIconImage(imagenIcono);

        this.panelAyuda.anchoAltoPanel(ventana.getWidth(), ventana.getHeight());
        this.panelAyuda.setImagen("Ayuda");
        this.ventana.add(this.panelAyuda);

        this.cargarTextArea();
        this.cargarBotonSalir();
    }

    private void cargarTextArea() {

        JTextArea area = new JTextArea();
        area.setEditable(true);
        area.setFont(new Font("Arial", Font.PLAIN, 20));
        //area.setText(this.leerContenido());

        String inputData = null;

        try {
            inputData = new String(this.leerContenido().getBytes(), "UTF-8");
            area.setText(inputData);
        } catch (UnsupportedEncodingException e) {
        }

        area.setBounds(new Rectangle(new Rectangle((this.ventana.getWidth() / 30), ((this.ventana.getHeight()) * 16) / 100 + 50,
                (this.ventana.getWidth() * 93) / 100, this.ventana.getHeight() - (this.ventana.getHeight() / 5) - 200)));
        area.setOpaque(true);
        area.setBackground(Color.black);
        area.setForeground(Color.yellow);
        area.setVisible(true);
        area.setEnabled(true);
        
        this.panelAyuda.add(area);
    }

    private void cargarBotonSalir() {

        ImageIcon imagenSalir = new ImageIcon(System.getProperty("user.dir").concat("\\Recursos\\Imagenes\\ImagenBotones\\4.png"));

        Image image = imagenSalir.getImage();
        image = image.getScaledInstance(this.ventana.getWidth() / 4, 50, Image.SCALE_SMOOTH);
        imagenSalir.setImage(image);

        JButton botonSalir = new JButton(imagenSalir);
        Auxiliares.Boton boton = new Auxiliares.Boton(botonSalir);
        boton.setBounds(this.ventana.getWidth() * 3 / 8, this.ventana.getHeight() - (this.ventana.getHeight() / 5),
                (this.ventana.getWidth() / 4), 50);

        this.panelAyuda.add(botonSalir);

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoBotonSalir();
            }
        });
    }

    private void eventoBotonSalir() {
        this.padre.setEnabled(true);
        this.ventana.dispose();
    }

    private String leerContenido() {
        String cadena;
        String archivo = System.getProperty("user.dir") + "\\Recursos\\Reglas del juego\\reglasDelJuego.txt";

        StringBuilder cadenaAlmacenamiento = new StringBuilder("");

        try {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                cadenaAlmacenamiento.append(cadena.concat("\n"));
            }

            b.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e2) {
        }

        return cadenaAlmacenamiento.toString();
    }
}
