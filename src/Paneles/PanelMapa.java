/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import ADO.AccesoDatos;
import Auxiliares.Cursores;
import Entidades.Pais;
import TEG.LanzarDados;
import TEG.VentanaPrincipal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author equipo
 */
public class PanelMapa extends JPanel {

    private ImageIcon imagen, is1, is2, is3, is4, is5, is6, imagenObjetivo;
    private int x0, y0, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6;
    private int numero;
    private JLabel personaje, puntaje;
    private PanelTotal panelTotal;
    private boolean autorizoArrastrado;
    private int inicioX, inicioY, finX, finY;
    private boolean control;
    private JButton[] botones;
    private ImageIcon imagenIcono;
    private int id;
    private String nombre1, nombre2, nombre3, nombre4, nombre5, nombre6;
    private int cantFichas, cantFichas2, cantFichas3, cantFichas4, cantFichas5, cantFichas6;
    private String atacante, defensa;

    public PanelMapa(PanelTotal panelTotal) {

        this.cargarImagen();
        this.panelTotal = panelTotal;
        this.control = false;

        this.setLayout(null);
        this.cargarEtiquetas();
        this.cargarCursor(1);
        this.autorizoArrastrado = false;

        this.numero = 0;
        this.id = 0;

        this.nombre1 = this.nombre2 = this.nombre3 = this.nombre4 = this.nombre5 = this.nombre6 = "";

        this.setBounds(new Rectangle(0, 0, panelTotal.getWidth(), (panelTotal.getHeight() / 2) + (panelTotal.getHeight() / 4)));

        x1 = x2 = x3 = x4 = x5 = x6 = -100;
        y1 = y2 = y3 = y4 = y5 = y6 = -100;

        x0 = -500;
        y0 = -600;

        this.atacante = "";
        this.defensa = "";

        try {
            imagenIcono = new ImageIcon(System.getProperty("user.dir").concat("\\Recursos\\Imagenes\\ImagenCursores\\iconoMensaje.png"));
        } catch (Exception e) {
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                accionMouse(me);
                setCoordenadas2(me);
                accionInsertarFichas(me);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                accionMouse(me);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                accionLevantarMouse();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                accionLevantarMouse();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                arrastrarMouse(me);
            }
        });

    }

    public void setId(int id) {
        this.id = id;
    }

    private void accionLevantarMouse() {

        if (numero == 1) {
            this.inicioX = 0;
            this.inicioY = 0;
            this.finX = 0;
            this.finY = 0;
            this.repaint();
        }
    }

    private void accionInsertarFichas(MouseEvent me) {

        AccesoDatos acceso = new AccesoDatos();

        int x, y, id;
        id = x = y = 0;

        LinkedList<Pais> listaPais = new LinkedList<Pais>();

        Pais p = null;

        String pais = "";
        String consulta = "SELECT idPais, nombre, x, y FROM Pais";
        ResultSet resultado = null;

        try {
            resultado = acceso.consultar(consulta);

            while (resultado.next()) {
                id = Integer.parseInt(resultado.getString("idPais"));
                pais = resultado.getString("nombre");
                x = Integer.parseInt(resultado.getString("x"));
                y = Integer.parseInt(resultado.getString("y"));

                p = new Pais();
                p.setIdPais(id);
                p.setNombre(pais);
                p.setCoordenadaX(x);
                p.setCoordenadaY(y);

                listaPais.add(p);
            }

            x = y = 0;
            pais = "";

            acceso.Desconectar();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e2) {
        }

        int coordenadaXInsercion, coordenadaYInsercion;
        coordenadaXInsercion = me.getX();
        coordenadaYInsercion = me.getY();

        boolean control = false;

        String consFichas = "SELECT nomJugador, fichas FROM Jugador WHERE idJugador = " + this.id;
        ResultSet resultado2 = null;
        String nombreJugador = "";
        int cantidadFichas = 0;

        try {
            resultado2 = acceso.consultar(consFichas);

            while (resultado2.next()) {
                nombreJugador = resultado2.getString("nomJugador");
                cantidadFichas = Integer.parseInt(resultado2.getString("fichas"));
            }

            acceso.Desconectar();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e2) {
        }

        if (cantidadFichas <= 0) {
            return;
        }

        int idPais = 0;
        String consultaPaisXJugador = "";
        int idPaisConsulta = 0, idJugadorConsulta = 0;

        if (cantidadFichas < 6 && cantidadFichas > 0) {
            for (int i = 0; i < listaPais.size(); i++) {

                idPais = listaPais.get(i).getIdPais();
                pais = listaPais.get(i).getNombre();
                x = listaPais.get(i).getCoordenadaX();
                y = listaPais.get(i).getCoordenadaY();

                if (Math.abs(x - coordenadaXInsercion) <= 75 && Math.abs(y - coordenadaYInsercion) <= 75) {

                    control = true;

                    try {
                        consultaPaisXJugador = "SELECT idPais, idJugador FROM JugadorXPais";
                        ResultSet resultadoConsulta = acceso.consultar(consultaPaisXJugador);

                        if (resultadoConsulta != null) {
                            while (resultadoConsulta.next()) {
                                idPaisConsulta = Integer.parseInt(resultadoConsulta.getString("idPais"));
                                idJugadorConsulta = Integer.parseInt(resultadoConsulta.getString("idJugador"));
                            }

                            if (idPaisConsulta != 0 && idJugadorConsulta != 0) {
                                if (this.id == idJugadorConsulta && idPais != idPaisConsulta) {
                                    return;
                                }
                            }

                        }

                        acceso.Desconectar();

                    } catch (SQLException e) {
                    } catch (ClassNotFoundException e2) {
                    }

                    switch (this.id) {
                        case 1:
                            this.x1 = x;
                            this.y1 = y;
                            this.cantFichas++;
                            this.nombre1 = nombreJugador;
                            break;
                        case 2:
                            this.x2 = x;
                            this.y2 = y;
                            this.cantFichas2++;
                            this.nombre2 = nombreJugador;
                            break;
                        case 3:
                            this.x3 = x;
                            this.y3 = y;
                            this.cantFichas3++;
                            this.nombre3 = nombreJugador;
                            break;
                        case 4:
                            this.x4 = x;
                            this.y4 = y;
                            this.cantFichas4++;
                            this.nombre4 = nombreJugador;
                            break;
                        case 5:
                            this.x5 = x;
                            this.y5 = y;
                            this.cantFichas5++;
                            this.nombre5 = nombreJugador;
                            break;
                        case 6:
                            this.x6 = x;
                            this.y6 = y;
                            this.cantFichas6++;
                            this.nombre6 = nombreJugador;
                            break;
                    }

                    String actualizacion = "UPDATE Jugador SET fichas = " + (cantidadFichas - 1) + " WHERE idJugador = " + this.id;
                    acceso.Ejecutar(actualizacion);

                    String insercionPaisJugador = "INSERT INTO JugadorXPais (idJugador, idPais) VALUES (" + this.id + ", " + idPais + ")";
                    acceso.Ejecutar(insercionPaisJugador);

                    String eliminacionJugadorCero = "DELETE FROM JugadorXPais WHERE idPais = 0 AND idJugador = 0";
                    acceso.Ejecutar(eliminacionJugadorCero);
                    acceso.Desconectar();

                    repaint();
                }

            }

            if (!control) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

    }

    public void setAtacante(String atacante) {
        this.atacante = atacante;
    }

    private void cargarEtiquetas() {

        Auxiliares.Label aux;

        this.personaje = new JLabel("PERSONAJE: ");
        aux = new Auxiliares.Label(this.personaje);
        aux.setBounds(10, 10, 600, 50);

        this.puntaje = new JLabel("PUNTAJE: ");
        aux = new Auxiliares.Label(this.puntaje);
        aux.setBounds(this.panelTotal.getWidth() / 2, 10, 600, 50);

        this.add(this.puntaje);
        this.add(this.personaje);
    }

    private void arrastrarMouse(MouseEvent me) {

        if (this.autorizoArrastrado) {
            this.finX = me.getXOnScreen();
            this.finY = me.getYOnScreen();
            this.panelTotal.repaint();
        }
    }

    private void accionMouse(MouseEvent me) {

        String ruta = System.getProperty("user.dir");

        this.autorizoArrastrado = false;
        Clip sonido;

        try {
            sonido = AudioSystem.getClip();

            if (numero == 3) {
                sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Ataque.wav")));
            }

            if (numero == 4) {
                sonido.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Barrido_Ataque.wav")));
            }

            sonido.loop(0);

        } catch (Exception e) {
        }

        if (numero == 1) {
            this.inicioX = me.getX();
            this.inicioY = me.getY();
            this.autorizoArrastrado = true;
        }

        if (this.numero == 3) {

            this.inicioX = me.getX();
            this.inicioY = me.getY();

            verificarPais(inicioX, inicioY);

            if (this.defensa.compareToIgnoreCase("") != 0 && this.atacante.compareToIgnoreCase("") != 0) {
                LanzarDados lanzarDado = new LanzarDados(VentanaPrincipal.getJFrame(), true);
                lanzarDado.setLabelEtiquetas(this.atacante, this.defensa);
            } else {
                Toolkit.getDefaultToolkit().beep();
                JLabel mensaje = new JLabel();
                mensaje.setText("No ha seleccionado el Jugador para atacar");
                mensaje.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
                JOptionPane.showMessageDialog(this.panelTotal, mensaje, "Atención!", JOptionPane.INFORMATION_MESSAGE, this.imagenIcono);
                return;
            }

        }
    }

    private void verificarPais(int coordenadaX, int coordenadaY) {
        AccesoDatos acceso = new AccesoDatos();
        Pais p = null;
        String consultaJugadorPais;
        int xPais, yPais, idPaisAI;
        LinkedList<Pais> paises = new LinkedList<Pais>();
        String jugadorNombre, jugadorAtacante;

        idPaisAI = xPais = yPais = 0;
        jugadorNombre = jugadorAtacante = "";
        consultaJugadorPais = "";
        String pais = "";
        String consulta = "SELECT idPais, nombre, x, y FROM Pais";
        ResultSet resultado = null;

        try {
            resultado = acceso.consultar(consulta);

            while (resultado.next()) {
                idPaisAI = Integer.parseInt(resultado.getString("idPais"));
                pais = resultado.getString("nombre");
                xPais = Integer.parseInt(resultado.getString("x"));
                yPais = Integer.parseInt(resultado.getString("y"));

                p = new Pais();
                p.setIdPais(idPaisAI);
                p.setNombre(pais);
                p.setCoordenadaX(xPais);
                p.setCoordenadaY(yPais);

                paises.add(p);
            }

            xPais = yPais = 0;
            pais = "";

            acceso.Desconectar();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e2) {
        }

        for (int i = 0; i < paises.size(); i++) {

            xPais = paises.get(i).getCoordenadaX();
            yPais = paises.get(i).getCoordenadaY();

            if (Math.abs(xPais - coordenadaX) <= 75 && Math.abs(yPais - coordenadaY) <= 75) {
                idPaisAI = paises.get(i).getIdPais();

                consultaJugadorPais = "SELECT JugadorXPais.idJugador AS JUGADOR, Jugador.nomJugador AS NOMBRE FROM JugadorXPais INNER JOIN Jugador "
                        + "ON JugadorXPais.idJugador = Jugador.idJugador "
                        + "WHERE JugadorXPais.idPais = " + idPaisAI;

                try {
                    ResultSet resultadoConsulta = acceso.consultar(consultaJugadorPais);

                    while (resultadoConsulta.next()) {
                        jugadorNombre = resultadoConsulta.getString("NOMBRE");
                    }

                    this.defensa = jugadorNombre;
                    
                    String consultaJugadorAtacante = "SELECT nomJugador FROM Jugador WHERE idJugador = " + this.id;
                    
                    resultadoConsulta = acceso.consultar(consultaJugadorAtacante);
                    
                    while(resultadoConsulta.next()) {
                        jugadorAtacante = resultadoConsulta.getString("nomJugador");
                    }
                    
                    this.atacante = jugadorAtacante;

                    acceso.Desconectar();
                } catch (SQLException e1) {
                } catch (ClassNotFoundException e2) {
                }

            }
        }

    }

    private void cargarImagen() {

        String ruta = System.getProperty("user.dir");

        try {
            this.imagen = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenPantallas/mapaEuropa.png"));
            this.is1 = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenesSoldados/s1.png"));
            this.is2 = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenesSoldados/s2.png"));
            this.is3 = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenesSoldados/s3.png"));
            this.is4 = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenesSoldados/s4.png"));
            this.is5 = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenesSoldados/s5.png"));
            this.is6 = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenesSoldados/s6.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "panel mapa");
        }
    }

    public void insertarImagenMision(int valor, JButton[] botones) {
        String ruta = System.getProperty("user.dir");

        if (valor == 5) {

            Toolkit.getDefaultToolkit().beep();
            JLabel mensaje = new JLabel();
            mensaje.setText("En esta misión combaten todos contra todos");
            mensaje.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));

            JOptionPane.showMessageDialog(this.panelTotal, mensaje, "Atención!", JOptionPane.INFORMATION_MESSAGE, imagenIcono);
            control = true;
            this.botones = botones;
            this.repaint();
            return;
        }

        try {
            this.imagenObjetivo = new ImageIcon(ruta.concat("/Recursos/Imagenes/ImagenObjetivos/" + valor + ".png"));
            control = true;
            this.botones = botones;
            this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "panel mapa");
            control = false;
        }

    }

    private void setCoordenadas2(MouseEvent me) {

        if (this.control == true) {
            this.x0 = me.getX();
            this.y0 = me.getY();
            this.control = false;
            this.repaint();
            this.botones[0].setEnabled(false);
            this.botones[2].setEnabled(true);
        }
    }

    public void cargarCursor(int num) {
        Cursores cursores = new Cursores(num);
        this.setCursor(cursores.getCursor());
        this.numero = num;
    }

    public void cambiarPersonaje(String personaje) {
        this.personaje.setText("PERSONAJE: " + personaje);
    }

    public void cambiarPuntaje(int puntaje) {
        this.puntaje.setText("PUNTAJE: " + puntaje);
    }

    public void quitarSoldado() {

        AccesoDatos acceso = new AccesoDatos();

        String consultar = "SELECT nomJugador, fichas FROM Jugador WHERE idJugador = " + this.id;
        ResultSet resultado = null;

        int cantidadDeFichas = 0;
        String nombre = "";

        try {
            resultado = acceso.consultar(consultar);

            while (resultado.next()) {
                nombre = resultado.getString("nomJugador");
                cantidadDeFichas = Integer.parseInt(resultado.getString("fichas"));
            }

            acceso.Desconectar();
        } catch (SQLException e) {
        } catch (ClassNotFoundException e2) {
        }

        if (cantidadDeFichas > 4) {
            return;
        }

        switch (this.id) {
            case 1:
                this.cantFichas--;
                break;
            case 2:
                this.cantFichas2--;
                break;
            case 3:
                this.cantFichas3--;
                break;
            case 4:
                this.cantFichas4--;
                break;
            case 5:
                this.cantFichas5--;
                break;
            case 6:
                this.cantFichas6--;
                break;
        }

        this.repaint();

        String consulta = "UPDATE Jugador SET fichas = fichas + 1 WHERE idJugador = " + this.id;

        acceso.Ejecutar(consulta);
        acceso.Desconectar();
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        try {
            Dimension d = getSize();
            g2d.drawImage(imagen.getImage(), 0, 0, d.width, d.height, this);
            //g.drawImage(imagenSoldado.getImage(), x, y, this);

            if (control == false) {
                g2d.drawImage(imagenObjetivo.getImage(), x0, y0, this);
            }

        } catch (Exception e) {
        }

        BasicStroke stroke = new BasicStroke(3.0f);
        g2d.setStroke(stroke);
        g2d.setColor(Color.green);
        g2d.drawRect(inicioX, inicioY, finX, finY);

        if (this.cantFichas > 0) {
            g2d.setColor(Color.red);
            g2d.fillOval(x1, y1, 50, 50);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString("" + this.cantFichas, x1 + (5 / 2) + 10, y1 + (50 / 2) + 10);
            g2d.drawImage(is1.getImage(), x1 + 40, y1 + 40, this);
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(this.nombre1.toUpperCase(), x1 - 2, y1 - 2);
        }

        if (this.cantFichas2 > 0) {
            g2d.setColor(Color.blue);
            g2d.fillOval(x2, y2, 50, 50);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString("" + this.cantFichas2, x2 + (5 / 2) + 10, y2 + (50 / 2) + 10);
            g2d.drawImage(is2.getImage(), x2 + 40, y2 + 40, this);
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(this.nombre2.toUpperCase(), x2 - 2, y2 - 2);
        }

        if (this.cantFichas3 > 0) {
            g2d.setColor(Color.green);
            g2d.fillOval(x3, y3, 50, 50);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString("" + this.cantFichas3, x3 + (5 / 2) + 10, y3 + (50 / 2) + 10);
            g2d.drawImage(is3.getImage(), x3 + 40, y3 + 40, this);
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(this.nombre3.toUpperCase(), x3 - 2, y3 - 2);
        }

        if (this.cantFichas4 > 0) {
            g2d.setColor(Color.yellow);
            g2d.fillOval(x4, y4, 50, 50);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString("" + this.cantFichas4, x4 + (5 / 2) + 10, y4 + (50 / 2) + 10);
            g2d.drawImage(is4.getImage(), x4 + 40, y4 + 40, this);
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(this.nombre4.toUpperCase(), x4 - 2, y4 - 2);
        }

        if (this.cantFichas5 > 0) {
            g2d.setColor(Color.pink);
            g2d.fillOval(x5, y5, 50, 50);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString("" + this.cantFichas5, x5 + (5 / 2) + 10, y5 + (50 / 2) + 10);
            g2d.drawImage(is5.getImage(), x5 + 40, y5 + 40, this);
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(this.nombre5.toUpperCase(), x5 - 2, y5 - 2);
        }

        if (this.cantFichas6 > 0) {
            g2d.setColor(Color.gray);
            g2d.fillOval(x6, y6, 50, 50);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            g2d.drawString("" + this.cantFichas6, x6 + (5 / 2) + 10, y6 + (50 / 2) + 10);
            g2d.drawImage(is6.getImage(), x6 + 40, y6 + 40, this);
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(this.nombre6.toUpperCase(), x6 - 2, y6 - 2);
        }

        setOpaque(false);
        super.paintComponent(g2d);

    }

}
