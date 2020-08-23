/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author notebook
 */
public class LanzarDados extends JDialog {

    private JDialog ventana;
    private JLabel texto1, texto2;
    private JButton btnDado1, btnDado2, btnQuitarFicha1, btnQuitarFicha2, btnSalir;
    private ImageIcon imgDado, imgDescontar;
    private JFrame padre;

    public LanzarDados(JFrame jFrame, boolean b) {

        super(jFrame, b);
        this.padre = jFrame;
        this.padre.setEnabled(false);

        this.ventana = new JDialog();
        this.ventana.setSize(500, 300);
        this.ventana.setLayout(null);
        this.ventana.setResizable(false);
        this.ventana.setVisible(true);
        this.ventana.setTitle("Ataques");
        this.ventana.setLocationRelativeTo(this);
        this.ventana.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        this.cargarImagenes();
        this.cargarTexto();
        this.cargarBotones();
    }

    private void cargarTexto() {

        this.texto1 = new JLabel();
        this.texto1.setText("Jugador 1");
        this.texto1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.texto1.setBounds(new Rectangle(25, 35, 200, 25));

        this.texto2 = new JLabel();
        this.texto2.setText("Jugador 2");
        this.texto2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.texto2.setBounds(new Rectangle(25, 100, 200, 25));

        this.ventana.add(this.texto1);
        this.ventana.add(this.texto2);

    }

    private void cargarBotones() {

        this.btnDado1 = new JButton();
        this.btnDado1.setBounds((new Rectangle(250, 35, 50, 50)));

        Image image = imgDado.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imgDado.setImage(image);

        this.btnDado1.setIcon(imgDado);

        this.btnDado2 = new JButton();
        this.btnDado2.setBounds((new Rectangle(250, 100, 50, 50)));

        this.btnDado2.setIcon(imgDado);

        Image image2 = imgDescontar.getImage();
        image2 = image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imgDescontar.setImage(image2);

        this.btnQuitarFicha1 = new JButton();
        this.btnQuitarFicha1.setBounds((new Rectangle(325, 35, 50, 50)));
        this.btnQuitarFicha1.setIcon(imgDescontar);

        this.btnQuitarFicha2 = new JButton();
        this.btnQuitarFicha2.setBounds((new Rectangle(325, 100, 50, 50)));
        this.btnQuitarFicha2.setIcon(imgDescontar);

        ImageIcon imagenSalir = new ImageIcon(System.getProperty("user.dir").concat("\\Recursos\\Imagenes\\ImagenBotones\\4.png"));

        Image image3 = imagenSalir.getImage();
        image3 = image3.getScaledInstance(100, 25, Image.SCALE_SMOOTH);
        imagenSalir.setImage(image3);

        this.btnSalir = new JButton(imagenSalir);
        this.btnSalir.setBounds(new Rectangle(100, 250, 100, 25));

        this.ventana.add(this.btnDado1);
        this.ventana.add(this.btnDado2);
        this.ventana.add(this.btnQuitarFicha1);
        this.ventana.add(this.btnQuitarFicha2);
        this.ventana.add(this.btnSalir);

        this.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirVentana();
            }
        });

        this.btnDado1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionBotonDados();
            }
        });

        this.btnDado2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionBotonDados();
            }
        });
    }

    private void salirVentana() {
        this.padre.setEnabled(true);
        this.ventana.setVisible(false);
    }

    private void cargarImagenes() {
        this.imgDado = new ImageIcon(System.getProperty("user.dir") + "//Recursos//Imagenes//imagenCursores//13.png");
        this.imgDescontar = new ImageIcon(System.getProperty("user.dir") + "//Recursos//Imagenes//imagenCursores//4.png");
    }

    public void setLabelEtiquetas(String atacante, String defensa) {
        this.texto1.setText(atacante);
        this.texto2.setText(defensa);
    }

    private void accionBotonDados() {

        String ruta = System.getProperty("user.dir");

        try {
            Clip sonido2 = AudioSystem.getClip();
            sonido2.open(AudioSystem.getAudioInputStream(new File(ruta + "\\Recursos\\Sonidos\\Dados.wav")));

            sonido2.loop(0);

            Thread.sleep(3000);

        } catch (Exception e) {
        }

        int numero = 0;

        do {
            numero = (int) (Math.random() * 20) + 1;
        } while (numero == 0);

        ImageIcon imagenAux = new ImageIcon(ruta.concat("\\Recursos\\Imagenes\\DadosCombinaciones\\" + numero + ".png"));

        ImageIcon imagenIcono = new ImageIcon(System.getProperty("user.dir") + "//Recursos//Imagenes//imagenCursores//iconoMensaje.png");

        JOptionPane.showMessageDialog(this.ventana, imagenAux, "Resultado de los dados", JOptionPane.INFORMATION_MESSAGE, imagenIcono);

    }

}
