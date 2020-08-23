/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import ADO.AccesoDatos;
import TEG.EstadisticasJugadores;
import TEG.MisionObjetivo;
import TEG.ReglasJuego;
import TEG.VentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public class PanelJuegoDefinitivo {

    private Clip sonido, sonido2;
    private PanelMapa panel1;
    private PanelTeclado panel2;
    private JButton botonAcentarMision, botonCambiarPersonaje, botonAtacar, botonMover, botonMision, botonAyuda, botonVictoria,
            botonVictoriaFallida, botonDados, botonSeleccionar, botonSoldadoInsertar, botonEstadistica;
    private PanelFinJuego panel3;
    private PanelTotal panelTotal;
    private ImageIcon imagen, imagenIcono;
    private String nombreJugador, puntaje;
    private Auxiliares.Boton boton;
    private int id;

    public PanelJuegoDefinitivo(PanelTotal panelTotal) {

        this.panelTotal = panelTotal;

        this.imagen = null;
        this.imagenIcono = new ImageIcon(System.getProperty("user.dir") + "//Recursos//Imagenes//imagenCursores//iconoMensaje.png");

        this.sonidoJuego();

        this.cargarBotones();

        
        this.panel1 = new PanelMapa(this.panelTotal);
        this.panel1.setVisible(true);
        this.panel1.repaint();

        this.panel2 = new PanelTeclado(this.panelTotal);
        this.panel2.setVisible(true);
        this.panel2.repaint();

        this.panelTotal.add(panel1);
        this.panelTotal.add(panel2);
        this.panelTotal.repaint();
    }

    private void cargarBotones() {

        ImageIcon imagen0, imagen1, imagen2, imagen3, imagen4, imagen5, imagen6, imagen7, imagen8,
                imagen9, imagen10, imagen11, imagen12, imagen13, imagen14, imagen15, imagen16;

        imagen1 = imagen4 = imagen8 = imagen7 = imagen0 = imagen10
                = imagen11 = imagen9 = imagen12 = imagen3 = imagen2 = imagen5 = imagen6
                = imagen13 = imagen14 = imagen15 = null;

        ArrayList<String> rutaImagen = new ArrayList<String>();

        for (int i = 0; i < 12; i++) {
            rutaImagen.add(System.getProperty("user.dir").concat("\\Recursos\\Imagenes\\ImagenCursores\\" + i + ".png"));
        }

        String[] etiquetas = {"Insertar objetivo", "Seleccionar soldados", "Cambiar de Personaje", "Insertar soldados", "Quitar soldados",
            "Atacar", "Mover al personaje", "Ver misión objetivo", "Ayuda", "Estadisticas", "Victoria",
            "Victoria Fallida"};

        ImageIcon[] imagen = {imagen0, imagen1, imagen2, imagen3, imagen4, imagen5, imagen6, imagen7, imagen8,
            imagen9, imagen10, imagen11, imagen12, imagen13, imagen14, imagen15};

        for (int i = 0; i < rutaImagen.size(); i++) {
            imagen[i] = new ImageIcon(rutaImagen.get(i).toString());
        }

        final JButton botones[] = {botonAcentarMision, botonSeleccionar, botonCambiarPersonaje, botonSoldadoInsertar, botonDados, botonAtacar,
            botonMover, botonMision, botonAyuda, botonEstadistica, botonVictoria, botonVictoriaFallida};
        int puntero = - 225;

        for (int i = 0; i < botones.length; i++) {

            ImageIcon img = imagen[i];

            Image image = img.getImage();
            image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            img.setImage(image);

            botones[i] = new JButton(img);
            boton = new Auxiliares.Boton(botones[i]);
            boton.setBounds((this.panelTotal.getWidth() / 4) + puntero,
                    (this.panelTotal.getHeight() / 2) + (this.panelTotal.getHeight() / 3), 50, 50);
            botones[i].setToolTipText(etiquetas[i]);

            this.panelTotal.add(botones[i]);

            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    accionTodosLosBotones(ae, botones);
                }
            });

            puntero += 75;
            
            botones[i].setEnabled(false);
        }
        
        botones[0].setEnabled(true);
    }

    private void accionTodosLosBotones(ActionEvent ae, JButton[] botones) {

        for (int i = 0; i < botones.length; i++) {
            if (ae.getSource() == botones[i]) {
                switch (i) {
                    case 0:
                        this.insertarObjetoMision(botones);
                        break;
                    case 1:
                        this.accionBotonSoldados();
                        break;
                    case 2:
                        this.accionBotonCambiarPersonaje(botones);
                        break;
                    case 3:
                        this.insertarSoldado();
                        break;
                    case 4:
                        this.quitarSoldado();
                        break;
                    case 5:
                        this.cargarCursor(3);
                        break;
                    case 6:
                        this.cargarCursor(4);
                        break;
                    case 7:
                        MisionObjetivo mision = new MisionObjetivo(VentanaPrincipal.getJFrame(), true);
                        break;
                    case 8:
                        ReglasJuego reglas = new ReglasJuego(VentanaPrincipal.getJFrame(), true);
                        break;
                    case 9:
                        EstadisticasJugadores estadisticas = new EstadisticasJugadores(VentanaPrincipal.getJFrame(), true);
                        break;
                    case 10:
                        this.accionBotonCancelarContinuar(1);
                        break;
                    case 11:
                        this.accionBotonCancelarContinuar(2);
                        break;
                }
            }
        }
    }
    
    private void insertarObjetoMision(JButton[] botones) {

        String consulta = "SELECT idMision FROM Mision WHERE seleccionada = 1";

        try {
            AccesoDatos acceso = new AccesoDatos();
            ResultSet resultado = acceso.consultar(consulta);

            int valor = 0;
            
            while (resultado.next()) {
                valor = Integer.parseInt(resultado.getString("idMision"));
            }

            this.panel1.insertarImagenMision(valor, botones);
            
        } catch (SQLException e) {
        } catch (ClassNotFoundException e2) {            
        }
    }

    private void insertarSoldado() {
        this.panel1.setId(this.id);
        this.cargarCursor(10);
    }
    
    private void quitarSoldado() {
        this.panel1.setId(this.id);
        this.panel1.quitarSoldado();
    }

    private void cargarCursor(int num) {
        this.panel1.cargarCursor(num);
        this.panel1.setAtacante(this.nombreJugador);
    }

    private void accionBotonSoldados() {
        this.cargarCursor(1);
    }

    private void accionBotonCambiarPersonaje(JButton[] botones) {

        ADO.AccesoDatos acceso = new ADO.AccesoDatos();
        Entidades.Jugador jugador = new Entidades.Jugador();

        String consulta = "SELECT idJugador, nomJugador, puntaje FROM Jugador";

        ArrayList<String> vectorString = new ArrayList<String>();

        try {

            ResultSet rs = acceso.consultar(consulta);

            while (rs.next()) {
                nombreJugador = rs.getString("nomJugador");
                puntaje = rs.getString("puntaje");

                vectorString.add(nombreJugador);

                
                jugador.setNombreJugador(nombreJugador);
                jugador.setPuntaje(Integer.parseInt(puntaje));

            }

            acceso.Desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ClassNotFoundException ex2) {
            JOptionPane.showMessageDialog(null, ex2.getMessage());
        }

        String personaje = (String) JOptionPane.showInputDialog(this.panelTotal, "Elige el personaje", "Trabajo Final Tecnologia De Software De Base",
                JOptionPane.DEFAULT_OPTION, this.imagenIcono, vectorString.toArray(), vectorString.get(0).toString());

        String consulta2 = "SELECT idJugador FROM Jugador WHERE nomJugador LIKE '" + personaje + "'";
        ResultSet resultado2 = null;
        
        try {
            resultado2 = acceso.consultar(consulta2);
            
            while(resultado2.next()) {
                this.id = Integer.parseInt(resultado2.getString("idJugador"));
                this.panel1.setId(id);
            }
            
            acceso.Desconectar();
        }
        catch(SQLException e1) {            
        }
        catch(ClassNotFoundException e2) {            
        }
        
        this.panel1.cambiarPersonaje(personaje);
        
        for(int i = 0 ; i < botones.length ; i++) {
            botones[i].setEnabled(true);
        }

        botones[0].setEnabled(false);
        
        vectorString = null;
    }

    private void accionBotonCancelarContinuar(int valor) {

        this.sonido.stop();

        this.panelTotal.removeAll();

        panel3 = new PanelFinJuego(this.panelTotal, valor);
    }

    private void sonidoJuego() {
        String ruta = System.getProperty("user.dir");

        try {
            this.sonido = AudioSystem.getClip();
            this.sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Medieval2.wav")));

            this.sonido.loop(20);

        } catch (Exception e) {
        }
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
}
