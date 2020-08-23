/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADO;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public class AccesoDatos extends Conexion {

    public AccesoDatos() {        
    }
    
    public boolean Ejecutar(String sql) {
        boolean valor = true;
        Conectar();

        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
            valor = false;
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            /*try {             
                Desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
        return valor;
    }

    public ResultSet consultar(String sql) throws SQLException, ClassNotFoundException {
        Conectar();
        ResultSet resultado = null;

        try {
            resultado = consulta.executeQuery(sql);            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
        }finally {
          /*  try {
                Desconectar();             
            } catch (Exception e) {
                e.printStackTrace();
            }*/ 
        }
        return resultado;
    }
}