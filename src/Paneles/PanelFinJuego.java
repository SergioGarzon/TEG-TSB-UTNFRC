/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import ADO.AccesoDatos;
import Auxiliares.Cursores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author equipo
 */
public class PanelFinJuego {

    private ImageIcon imagen;   
    private String ruta;    
    private PanelTotal panel;    

    public PanelFinJuego(PanelTotal panel, int numero) {


        this.panel = panel;

        this.ruta = System.getProperty("user.dir");

        if (numero == 1) {
            this.imagen = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenPantallas/Congratuslation2.png"));
            this.sonidoJuego();
        } else {
            this.imagen = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenPantallas/GameOver2.png"));
        }

        this.panel.setImagen(imagen);

        this.botonSalirJuego();
        this.cargarEtiqueta();
        this.tablaPuntuaciones();

        Cursores cursor = new Cursores(1);
        this.panel.setCursor(cursor.getCursor());

        this.panel.repaint();

    }

    private void cargarEtiqueta() {
        
        JLabel labelPuntajes;

        labelPuntajes = new JLabel();
        labelPuntajes.setText("PUNTUACIONES");
        labelPuntajes.setBounds(new Rectangle(this.panel.getWidth() / 4 + this.panel.getWidth() / 7, this.panel.getHeight() / 3 - 75,
                this.panel.getWidth() / 2, this.panel.getHeight() / 25));
        labelPuntajes.setFont(new Font("Arial", Font.CENTER_BASELINE, 35));
        labelPuntajes.setForeground(Color.yellow);

        this.panel.add(labelPuntajes);
    }

    private void tablaPuntuaciones() {
        AccesoDatos ad = new AccesoDatos();

        String[] columnName = {"Jugador", "Puntaje"};
        Object[][] data = {};

        DefaultTableModel modelo;
        modelo = new DefaultTableModel(data, columnName);

        String jugador = "";
        int puntaje = 0;

        String consulta = "select * from Jugador order by puntaje DESC";

        try {
            ResultSet rs = ad.consultar(consulta);

            while (rs.next()) {

                jugador = rs.getString("nomJugador");
                puntaje = Integer.parseInt(rs.getString("puntaje"));

                modelo.addRow(new Object[]{jugador, puntaje});
            }
            
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex2) {
        }        

        JTable dtm = new JTable(modelo);

        dtm.setEnabled(false);
        dtm.setCellSelectionEnabled(false);
        dtm.setFont(new Font("DigifaceWide", Font.BOLD, 30));

        dtm.setShowGrid(false);
        dtm.setOpaque(false);
        
        dtm.setBounds(new Rectangle(this.panel.getWidth() / 4, this.panel.getHeight() / 3,
                this.panel.getWidth() / 2, this.panel.getHeight() / 3 + (this.panel.getHeight() / 7)));
               
        dtm.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //OFF
        dtm.setRowHeight(50); //75
        dtm.getColumnModel().getColumn(0).setPreferredWidth(300);
        
      
        dtm.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setOpaque(false);
            }
        });

        
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            dtm.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

        dtm.setForeground(Color.RED);
        dtm.setBackground(Color.BLACK);

        this.panel.add(dtm, BorderLayout.CENTER);
    }

    public void botonSalirJuego() {

        JButton botonSalir;
        ImageIcon imagenBotonSalir = null; 
        
        try {
            imagenBotonSalir = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenBotones/botonTerminar.png"));
            
            Image image = imagenBotonSalir.getImage();            
            image = image.getScaledInstance(this.panel.getWidth() / 4, 50, Image.SCALE_SMOOTH);
            imagenBotonSalir.setImage(image);
        } catch (Exception e) {
        }

        botonSalir = new JButton(imagenBotonSalir);
        botonSalir.setBounds(new Rectangle(this.panel.getWidth() * 3/8, this.panel.getHeight() - (this.panel.getHeight() / 5),
                (this.panel.getWidth() / 4), 50));
        botonSalir.setEnabled(true);
        botonSalir.setOpaque(false);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(true);

        this.panel.add(botonSalir);

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                volverInicio();
            }
        });
    }

    private void volverInicio() {

        ADO.AccesoDatos acceso = new ADO.AccesoDatos();

        String insercion = "DELETE FROM Jugador";
        acceso.Ejecutar(insercion);

        insercion = "UPDATE Mision SET seleccionada = 0";
        acceso.Ejecutar(insercion);

        String eliminarTodo = "DELETE FROM JugadorXPais";
        acceso.Ejecutar(eliminarTodo);
        
        String insercionPaisJugador = "INSERT INTO JugadorXPais (idJugador, idPais) VALUES (0,0)";
        acceso.Ejecutar(insercionPaisJugador);
        
        acceso.Desconectar();


        System.exit(0);
    }

    private void sonidoJuego() {
        Clip sonido;

        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Victoria.wav")));

            sonido.loop(0);

        } catch (Exception e) {
        }
    }
}
