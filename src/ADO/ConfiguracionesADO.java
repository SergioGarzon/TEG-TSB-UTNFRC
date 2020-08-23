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
public final class ConfiguracionesADO {

    private AccesoDatos accesoDatos;  
    private int sFondo, dbSFondo, sAuxiliares, dbSAuxiliares, dificultad;
    
    public ConfiguracionesADO() {
        accesoDatos = new AccesoDatos();
        Configuraciones();
    }
    
    public void Configuraciones() {

        ResultSet resultado = null;
        String consulta = "select * from Configuraciones";

        try {
            resultado = accesoDatos.consultar(consulta);
            
            if(resultado != null) {
                sFondo = resultado.getInt("sonidosFondo");
                dbSFondo = resultado.getInt("dbSonidosFondo");
                sAuxiliares = resultado.getInt("sonidosAuxiliares");
                dbSAuxiliares = resultado.getInt("dbSonidosAuxiliares");
                dificultad = resultado.getInt("dificultad");
            }
            
            accesoDatos.Desconectar();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
        }
        catch(ClassNotFoundException e2) {
            JOptionPane.showMessageDialog(null, "error: " + e2.getMessage());
        }
        finally{
            
        }                
    }

    public void setConfiguracionesADO(int actReal, int volReal, int actAux, int volAux, int dif) {
        String consulta;

        consulta = "UPDATE Configuraciones"
                + " SET sonidosFondo = " + actReal
                + ", dbSonidosFondo = " + volReal
                + ", sonidosAuxiliares = " + actAux
                + ", dbSonidosAuxiliares = " + volAux
                + ", dificultad = " + dif
                + " WHERE idConfiguraciones = 1";

        accesoDatos.Ejecutar(consulta);
    }

    public int getsFondo() {
        return sFondo;
    }

    public void setsFondo(int sFondo) {
        this.sFondo = sFondo;
    }

    public int getDbSFondo() {
        return dbSFondo;
    }

    public void setDbSFondo(int dbSFondo) {
        this.dbSFondo = dbSFondo;
    }

    public int getsAuxiliares() {
        return sAuxiliares;
    }

    public void setsAuxiliares(int sAuxiliares) {
        this.sAuxiliares = sAuxiliares;
    }

    public int getDbSAuxiliares() {
        return dbSAuxiliares;
    }

    public void setDbSAuxiliares(int dbSAuxiliares) {
        this.dbSAuxiliares = dbSAuxiliares;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    
    
}
