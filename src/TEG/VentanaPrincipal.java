/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;

import Paneles.PanelPresentacion;
import Paneles.PanelTotal;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author notebook
 */
public class VentanaPrincipal extends JFrame {    
    
    private static JFrame ventana;
    
    public VentanaPrincipal() {
        
        ventana = new JFrame();
        ventana.setTitle("Trabajo Final Tecnologia De Software De Base");
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ventana.pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension tamano = tk.getScreenSize();

        int ancho = (int) tamano.getWidth();
        int alto = (int) tamano.getHeight();

        ventana.setSize(ancho, alto);

        Image imagenIcono = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/Recursos/imagenes/ImagenCursores/icono.png");
        ventana.setIconImage(imagenIcono);
                
        PanelTotal panel = new PanelTotal();
        panel.anchoAltoPanel(ancho, alto);
        PanelPresentacion panelPresentacion = new PanelPresentacion(panel);
        ventana.add(panel);
    }
    
    public static JFrame getJFrame() {
        return ventana;
    }
    
    
}
