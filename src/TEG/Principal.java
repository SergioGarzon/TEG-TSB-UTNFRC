/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;

import Negocio.Grafo;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author equipo
 */
public class Principal {

    public Principal() {

        ADO.AccesoDatos acceso = new ADO.AccesoDatos();

        String insercion = "DELETE FROM Jugador";
        acceso.Ejecutar(insercion);

        insercion = "UPDATE Mision SET seleccionada = 0";
        acceso.Ejecutar(insercion);

        String eliminarTodo = "DELETE FROM JugadorXPais";
        acceso.Ejecutar(eliminarTodo);

        String insercionPaisJugador = "INSERT INTO JugadorXPais (idJugador, idPais) VALUES (0,0)";
        acceso.Ejecutar(insercionPaisJugador);

        acceso.Desconectar();

        VentanaPrincipal ventana = null;

        try {

            ManejadorGrafo manejadorGrafo = new ManejadorGrafo();
            Grafo grafo = manejadorGrafo.getGrafo();

            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            ventana = new VentanaPrincipal();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
    }

    public static void main(String args[]) {
        new InformacionProyecto();
    }
}
