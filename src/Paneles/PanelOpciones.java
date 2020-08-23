/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public final class PanelOpciones {
    
    private JTextField campoVolumenAux, campoVolumen;
    private Auxiliares.TextField txtField;
    private PanelTotal panelTotal;
    private Auxiliares.Boton boton;
    private Auxiliares.ComboBox comboClase;
    private Auxiliares.CheckBox chkBox;
    private JComboBox combo;
    private JCheckBox chkActivadoReal, chkDesactivadoReal, chkActivadoAux, chkDesactivadoAux;
    private ADO.ConfiguracionesADO configuraciones;
    private JButton botonAceptar, botonCancelar, botonPunteroDerecho, botonPunteroIzquierdo, botonPunteroIzquierdoAux, botonPunteroDerechoAux;
    private int contadorSF, contadorSA;
    
    public PanelOpciones(PanelTotal panelTotal) {        
        this.panelTotal = panelTotal;
        this.configuraciones = new ADO.ConfiguracionesADO();
        
        this.contadorSF = this.configuraciones.getDbSFondo();
        this.contadorSA = this.configuraciones.getDbSAuxiliares();
        
        this.cargarImagenes();
        
        this.cargarTextField();
        this.cargarCombo();
        this.cargarChkBox();
    }
    
    private void cargarImagenes() {
        
        ImageIcon imagen0, imagen1, imagen2, imagen3, imagen4;
        imagen0 = imagen1 = imagen2 = imagen3 = imagen4 = null;        
        String ruta = System.getProperty("user.dir");
        
        ArrayList<String> imagenesCadenas = new ArrayList<String>();
        imagenesCadenas.add(ruta.concat("\\Recursos\\Imagenes\\ImagenPantallas\\" + 4 + ".jpg"));
        
        for (int i = 5; i < 9; i++) {
            imagenesCadenas.add(ruta.concat("\\Recursos\\Imagenes\\ImagenBotones\\" + i + ".png"));
        }
        
        ImageIcon[] imagenes = {imagen0, imagen1, imagen2, imagen3, imagen4};

        for (int i = 0; i < imagenesCadenas.size(); i++) {
            try {
                imagenes[i] = new ImageIcon(imagenesCadenas.get(i).toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.panelTotal, e.getMessage());
            }
        }               
        
        panelTotal.setImagen(imagenes[0]);
        panelTotal.repaint();      
        
        cargarBotones(imagenes);
    }
    
    private void cargarBotones(ImageIcon[] imagenes) {
    
        this.botonAceptar = new JButton(imagenes[1]);
        this.boton = new Auxiliares.Boton(botonAceptar);
        this.boton.setBounds(this.panelTotal.getWidth() / 4 + ((this.panelTotal.getWidth() / 4) / 2),
                (this.panelTotal.getHeight() - (this.panelTotal.getHeight() / 4) - 50), (this.panelTotal.getWidth() / 4), 50);
        
        this.botonCancelar = new JButton(imagenes[2]);
        this.boton = new Auxiliares.Boton(botonCancelar);
        this.boton.setBounds(this.panelTotal.getWidth() / 4 + ((this.panelTotal.getWidth() / 4) / 2),
                (this.panelTotal.getHeight() - (this.panelTotal.getHeight() / 5)), (this.panelTotal.getWidth() / 4), 50);
        
        this.botonPunteroIzquierdo = new JButton(imagenes[3]);
        this.boton = new Auxiliares.Boton(botonPunteroIzquierdo);
        this.boton.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9),
                (this.panelTotal.getHeight() / 5) + 9, 75, 50);        
                
        this.botonPunteroDerecho = new JButton(imagenes[4]);
        this.boton = new Auxiliares.Boton(botonPunteroDerecho);
        this.boton.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9) + 200,
                (this.panelTotal.getHeight() / 5) + 9, 75, 50);                
        
        this.botonPunteroIzquierdoAux = new JButton(imagenes[3]);
        this.boton = new Auxiliares.Boton(botonPunteroIzquierdoAux);
        this.boton.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9),
                (this.panelTotal.getHeight() / 2) - 55, 75, 50);        
                
        this.botonPunteroDerechoAux = new JButton(imagenes[4]);
        this.boton = new Auxiliares.Boton(botonPunteroDerechoAux);
        this.boton.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9) + 200,
                (this.panelTotal.getHeight() / 2) - 55, 75, 50);        
        
        this.panelTotal.add(botonAceptar);
        this.panelTotal.add(botonCancelar);        
        this.panelTotal.add(botonPunteroIzquierdo);
        this.panelTotal.add(botonPunteroDerecho);
        this.panelTotal.add(botonPunteroIzquierdoAux);
        this.panelTotal.add(botonPunteroDerecho);
        this.panelTotal.add(botonPunteroDerechoAux);
        
        this.botonAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBotonAceptar();
            }
        });
        
        this.botonCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBotonCancelar();
            }
        });
        
        this.botonPunteroIzquierdoAux.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBotonAuxiliares(ae);
            }
        });
        
        this.botonPunteroDerechoAux.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBotonAuxiliares(ae);
            }
        });
        
        this.botonPunteroDerecho.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBotonFondo(ae);
            }            
        });
        
        this.botonPunteroIzquierdo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                accionBotonFondo(ae);
            }            
        });        
    }
        
     private void cargarTextField() {
        this.campoVolumenAux = new JTextField();
        this.txtField = new Auxiliares.TextField(campoVolumenAux);        
        this.txtField.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9) + 100,
                (this.panelTotal.getHeight() / 2) - 55, 75, 50);        
        this.txtField.setText(String.valueOf(this.contadorSA));
        this.campoVolumenAux.setForeground(Color.orange);
        
        this.campoVolumen = new JTextField();        
        this.txtField = new Auxiliares.TextField(campoVolumen);
        this.txtField.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9) + 100,
                (this.panelTotal.getHeight() / 5) + 9, 75, 50);        
        this.txtField.setText(String.valueOf(this.contadorSF));
        this.campoVolumen.setForeground(Color.orange);
        
        this.panelTotal.add(campoVolumen);
        this.panelTotal.add(this.campoVolumenAux);
    }
   
    private void cargarCombo() {
        this.combo = new JComboBox();
        this.comboClase = new Auxiliares.ComboBox(combo);
        this.comboClase.setBounds(((this.panelTotal.getWidth()) / 2) + (this.panelTotal.getWidth() / 9),
                (this.panelTotal.getHeight() / 2) + 65, 350, 60);    
                
        this.combo.addItem("FÁCIL");
        this.combo.addItem("NORMAL");
        this.combo.addItem("DIFICIL");
        
        this.combo.setSelectedIndex(this.configuraciones.getDificultad());
        
        this.panelTotal.add(combo);               
    }
    
    private void cargarChkBox() {
        
        boolean b = true;
        
        this.chkActivadoReal = new JCheckBox();
        this.chkBox = new Auxiliares.CheckBox(chkActivadoReal);
        this.chkBox.setText("Activar");
        this.chkBox.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9),
                (this.panelTotal.getHeight() / 13), 175, 60);
        
        if(this.configuraciones.getsFondo() == 1) {
            this.chkBox.setSelected(b);
        } 
        
        this.chkDesactivadoReal = new JCheckBox();
        this.chkBox = new Auxiliares.CheckBox(chkDesactivadoReal);
        this.chkBox.setText("Desactivar");
        this.chkBox.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9) + 175,
                (this.panelTotal.getHeight() / 13), 300, 60);
    
        if(this.configuraciones.getsFondo() == 0) {
            this.chkBox.setSelected(b);
        }    
        
        this.chkActivadoAux = new JCheckBox();
        this.chkBox = new Auxiliares.CheckBox(chkActivadoAux);
        this.chkBox.setText("Activar");
        this.chkBox.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9),
                (this.panelTotal.getHeight() / 2) - (this.panelTotal.getHeight() / 5) + 20, 175, 60);
                
        if(this.configuraciones.getsAuxiliares() == 1) {
            this.chkBox.setSelected(b);
        }
        
        this.chkDesactivadoAux = new JCheckBox();
        this.chkBox = new Auxiliares.CheckBox(chkDesactivadoAux);
        this.chkBox.setText("Desactivar");
        this.chkBox.setBounds((this.panelTotal.getWidth() / 2) + (this.panelTotal.getWidth() / 9) + 175,
                (this.panelTotal.getHeight() / 2) - (this.panelTotal.getHeight() / 5) + 20, 300, 60);
        
        if(this.configuraciones.getsAuxiliares() == 0) {
            this.chkBox.setSelected(b);
        }
        
        Component[] componentes = this.panelTotal.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JCheckBox) {
                ((JCheckBox) componentes[i]).addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        accionChkBox(ae);
                    }
                });
            }            
        }
        
        this.panelTotal.add(this.chkActivadoReal);
        this.panelTotal.add(this.chkDesactivadoReal);
        this.panelTotal.add(this.chkActivadoAux);
        this.panelTotal.add(this.chkDesactivadoAux);
    }
    
    
    private void accionBotonAceptar() {
        
        int actReal, volReal, actAux, volAux, dificultad;
        actReal = volReal = actAux = 0;
        
        if(this.chkActivadoReal.isSelected()) {
            actReal = 1;
        } else if(this.chkDesactivadoReal.isSelected()) {
            actReal = 0;
        }
        
        volReal = Integer.parseInt(this.campoVolumen.getText());
        
        if(this.chkActivadoAux.isSelected()) {
            actAux = 1;
        } else if(this.chkDesactivadoAux.isSelected()) {
            actAux = 0;
        }
        
        volAux = Integer.parseInt(this.campoVolumenAux.getText());
        
        dificultad = this.combo.getSelectedIndex();
        
        configuraciones.setConfiguracionesADO(actReal, volReal, actAux, volAux, dificultad);
        
        this.accionBotonCancelar();
        
    }
    
    private void accionBotonCancelar() {
        this.panelTotal.setImagen(null);
        
        Component[] componentes = this.panelTotal.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JButton) {
                ((JButton) componentes[i]).setVisible(false);
            }
            
            if (componentes[i] instanceof JTextField) {
                ((JTextField) componentes[i]).setVisible(false);
            }
            
            if (componentes[i] instanceof JCheckBox) {
                ((JCheckBox) componentes[i]).setVisible(false);
            }           
            
            if (componentes[i] instanceof JComboBox) {
                ((JComboBox) componentes[i]).setVisible(false);
            }
        }
        
        this.configuraciones = null;        
        
        try {
            Thread.sleep(1000);
        }
        catch(Exception e) {}
        
        
        this.panelTotal.removeAll();
        
        
        PanelPresentacion panel = new PanelPresentacion(this.panelTotal);
        
    }
    
    private void accionChkBox(ActionEvent e) {
        
        if(e.getSource() == this.chkActivadoAux) {
            if(this.chkActivadoAux.isSelected() == true) {
                this.chkDesactivadoAux.setSelected(false);
                this.chkActivadoAux.setSelected(true);
            }            
        }        
        
        if(e.getSource() == this.chkDesactivadoAux) {
            if(this.chkDesactivadoAux.isSelected() == true) {
                this.chkActivadoAux.setSelected(false);
                this.chkDesactivadoAux.setSelected(true);
            }
        }
        
        if(e.getSource() == this.chkActivadoReal) {
            if(this.chkActivadoReal.isSelected() == true) {
                this.chkDesactivadoReal.setSelected(false);
                this.chkActivadoReal.setSelected(true);
            }
        }
        
        if(e.getSource() == this.chkDesactivadoReal) {
            if(this.chkDesactivadoReal.isSelected() == true) {
                this.chkActivadoReal.setSelected(false);
                this.chkDesactivadoReal.setSelected(true);
            }            
        }
        
    }
    
    private void accionBotonAuxiliares(ActionEvent e) {
        
        if(e.getSource() == this.botonPunteroDerechoAux) {
            if(contadorSA > 9) return;        
            contadorSA++;  
        }
        
        if(e.getSource() == this.botonPunteroIzquierdoAux) {
            if(contadorSA < 1) return;        
            contadorSA--;
        }
        
        this.campoVolumenAux.setText(String.valueOf(contadorSA));
    }
    
    private void accionBotonFondo(ActionEvent e) {
        
        if(e.getSource() == this.botonPunteroDerecho) {
            if(contadorSF > 9) return;
            contadorSF++;
        }
        
        if(e.getSource() == this.botonPunteroIzquierdo) {
            if(contadorSF < 1) return;
            contadorSF--;
        }
            
        this.campoVolumen.setText(String.valueOf(contadorSF));
        
    }
    
}

