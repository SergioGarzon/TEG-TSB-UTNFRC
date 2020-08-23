/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public class Conexion {
    
    Connection conn = null;
    Statement consulta;

    public void Conectar() {
        String ruta = System.getProperty("user.dir") + "\\Recursos\\Base de Datos\\TEG.sqlite";
        
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
        }
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
            consulta = conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Desconectar(){
        try{
            if(!conn.isClosed()){
                conn.close();
            }           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
