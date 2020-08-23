/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;

import ADO.AccesoDatos;
import Paneles.PanelAyuda;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author notebook
 */
public class EstadisticasJugadores extends JDialog {

    private JDialog ventana;
    private JTextArea texto;
    private JFrame padre;
    private PanelAyuda panelAyuda;

    public EstadisticasJugadores(JFrame padre, boolean control) {
        super(padre, control);

        this.padre = padre;
        this.padre.setEnabled(false);
        this.panelAyuda = new PanelAyuda();

        this.panelAyuda.setControl(true);

        ventana = new JDialog();
        ventana.setSize(950, 650);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(this);
        ventana.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Estadisticas Jugadores");
        ventana.setLayout(null);

        Image imagenIcono = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/Recursos/imagenes/ImagenCursores/icono.png");
        ventana.setIconImage(imagenIcono);

        this.panelAyuda.anchoAltoPanel(ventana.getWidth(), ventana.getHeight());
        this.panelAyuda.setImagen("Ayuda");
        this.ventana.add(this.panelAyuda);

        this.cargarBotonSalir();
        this.insertarEstadisticasJugadores();
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
        this.panelAyuda.setControl(false);
        this.ventana.dispose();
    }

    private void insertarEstadisticasJugadores() {

        String consulta = "SELECT nomJugador, puntaje FROM Jugador";

        try {
            AccesoDatos acceso = new AccesoDatos();
            ResultSet resultado = acceso.consultar(consulta);
            
            String nombreJugador, puntaje, valor;
            nombreJugador = puntaje = valor = "";
            
            int contador = 20;
            
            while(resultado.next()) {
                nombreJugador = resultado.getString("nomJugador");
                puntaje = resultado.getString("puntaje");
                
                valor =  "---> JUGADOR: " + nombreJugador + ", PUNTAJE: " + puntaje;
                
                JLabel etiqueta = new JLabel();
                etiqueta.setText(valor);
                etiqueta.setForeground(Color.white);
                etiqueta.setFont(new Font("Arial", Font.BOLD, 25));
                
                etiqueta.setBounds(new Rectangle(100, this.panelAyuda.getHeight() / 5 + contador, 1000, 50));
                
                this.panelAyuda.add(etiqueta);
                
                contador += 55;
            }
            
            
            
            
        } catch (SQLException e) {
        } catch(ClassNotFoundException e2) {            
        }

    }
}
