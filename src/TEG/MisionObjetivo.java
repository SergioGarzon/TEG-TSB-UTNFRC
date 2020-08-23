/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;

import ADO.AccesoDatos;
import Entidades.HiloTexto;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author notebook
 */
public class MisionObjetivo extends JDialog {
    private JDialog ventana;
    private JTextArea texto;
    private JFrame padre;
    private PanelAyuda panelAyuda;

    public MisionObjetivo(JFrame padre, boolean control) {
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
        ventana.setTitle("Misión objetivo");
        ventana.setLayout(null);

        Image imagenIcono = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/Recursos/imagenes/ImagenCursores/icono.png");
        ventana.setIconImage(imagenIcono);

        this.panelAyuda.anchoAltoPanel(ventana.getWidth(), ventana.getHeight());
        this.panelAyuda.setImagen("pergamino");
        this.ventana.add(this.panelAyuda);

        this.cargarBotonSalir();
        this.cargarTextArea();
    }
    
    private void cargarTextArea() {
        
        String consulta = "SELECT objetivo FROM Mision WHERE seleccionada = 1";        
        ResultSet resultado = null;
        String valor = "MISIÓN OBJETIVO \n\n";
        
        try {
            AccesoDatos acceso = new AccesoDatos();
            resultado = acceso.consultar(consulta);
            
            while(resultado.next()) {
                valor += resultado.getString("objetivo");
            }
            
            acceso.Desconectar();
            
        } catch(SQLException e) {            
        } catch(ClassNotFoundException e2) {}
                
        JTextArea area = new JTextArea();
        area.setFont(new Font("Arial", Font.BOLD, 25));
        area.setOpaque(false);
        area.setBackground(new Color(0,0,0,0));
        area.setForeground(Color.black);
        area.setVisible(true);
        area.setEnabled(true);
        area.setEditable(false);
        area.setBounds(new Rectangle((this.ventana.getWidth() / 3) - 100, ((this.ventana.getHeight()) * 16) / 100,
                500, this.ventana.getHeight() - (this.ventana.getHeight() / 5) - 125));
                
 
        hiloCargarObjetivo hilo = new hiloCargarObjetivo(area, valor);
        hilo.start();
        
        
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
}

class hiloCargarObjetivo extends Thread {
    
    private String valor;
    private JTextArea area;
    
    hiloCargarObjetivo(JTextArea area, String valor) {
        this.valor = valor;
        this.area = area;
    }
    
    @Override
    public void run() {
        
        this.area.setText("");
        
        int contador = 45;
                
        for(int i = 0 ; i < valor.length() ; i++) {
            this.area.setText(this.area.getText().concat("" + valor.charAt(i)));
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTexto.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            if(this.area.getText().length() == contador) {
                this.area.setText(this.area.getText().concat("\n\n"));
                
                contador += 45;
            }
            
        }
        
    }
    
}
