/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author equipo
 */
public class MisionesADO {

    private AccesoDatos ad;

    public MisionesADO() {
        ad = new AccesoDatos();
    }

    public ArrayList getAllMisiones() {
        ArrayList<Entidades.Mision> misiones = new ArrayList<Entidades.Mision>();
        String consulta = "select * from Mision";
        try {
            ResultSet rs = ad.consultar(consulta);

            while (rs.next()) {
                Entidades.Mision m = new Entidades.Mision();
                m.setIdMision(Integer.parseInt(rs.getString("idMision")));
                m.setNombre(rs.getString("nombre"));
                m.setObjetivo(rs.getString("objetivo"));
                m.setHistoria(rs.getString("historia"));
                m.setSeleccionada(Integer.parseInt(rs.getString("seleccionada")));
                misiones.add(m);                
            }
            
            ad.Desconectar();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ClassNotFoundException ex2) {
            JOptionPane.showMessageDialog(null, ex2.getMessage());
        }
        
       return misiones;
    }        
    
    public StringBuilder cargarHistoria() {
        StringBuilder cadena = new StringBuilder("");
        
        String consulta = "SELECT historia FROM Mision WHERE seleccionada = 1";
        
        try {
            ResultSet rs = ad.consultar(consulta);

            while (rs.next()) {
                cadena.append(rs.getString("historia"));
            }
            
            ad.Desconectar();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ClassNotFoundException ex2) {
            JOptionPane.showMessageDialog(null, ex2.getMessage());
        }
        
        
        return cadena;
    }
   
   
}
