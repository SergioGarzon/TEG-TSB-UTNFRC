/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author equipo
 */
public class HiloTexto extends Thread {
    
    private JTextArea area;
    private ADO.MisionesADO misiones;
    
    public HiloTexto(JTextArea area) {
        this.area = area;
        this.misiones = new ADO.MisionesADO();
    }
    
    @Override
    public void run() {
        
        StringBuilder historia = misiones.cargarHistoria();
        
        this.area.setText("");
                
        for(int i = 0 ; i < historia.length() ; i++) {
            this.area.setText(this.area.getText().concat(historia.charAt(i) + ""));
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloTexto.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
        }
        
    }
    
}