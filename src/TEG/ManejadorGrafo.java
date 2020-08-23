package TEG;

import ADO.AccesoDatos;
import Entidades.Pais;
import Negocio.Grafo;
import Negocio.Nodo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Enzo.
 */
public class ManejadorGrafo {

    private Grafo grafo;

    public ManejadorGrafo() {

        grafo = new Grafo();
        
        //PAISES
        Pais hispania = cargarPais(2);
        Pais galia = cargarPais(3);
        Pais anglia = cargarPais(4);
        Pais germania = cargarPais(5);
        Pais italia = cargarPais(6);
        Pais escandinavia = cargarPais(7);
        Pais polonia = cargarPais(8);
        Pais hungaria = cargarPais(9);
        Pais bulgaria = cargarPais(10);
        Pais turquia = cargarPais(11);
        Pais tartaria = cargarPais(12);
        Pais rumania = cargarPais(13);

        //HISPANIA
        unirPaises(hispania, galia);
        unirPaises(hispania, anglia);

        //GALIA
        unirPaises(galia, hispania);
        unirPaises(galia, germania);
        unirPaises(galia, anglia);
        unirPaises(galia, italia);

        //ANGLIA
        unirPaises(anglia, galia);
        unirPaises(anglia, hispania);
        unirPaises(anglia, germania);
        unirPaises(anglia, escandinavia);

        //GERMANIA
        unirPaises(germania, galia);
        unirPaises(germania, anglia);
        unirPaises(germania, escandinavia);
        unirPaises(germania, polonia);
        unirPaises(germania, hungaria);
        unirPaises(germania, italia);

        //ITALIA
        unirPaises(italia, galia);
        unirPaises(italia, hispania);
        unirPaises(italia, germania);
        unirPaises(italia, hungaria);
        unirPaises(italia, turquia);

        //ESCANDINAVIA
        unirPaises(escandinavia, anglia);
        unirPaises(escandinavia, germania);
        unirPaises(escandinavia, polonia);
        unirPaises(escandinavia, rumania);

        //POLONIA
        unirPaises(polonia, escandinavia);
        unirPaises(polonia, germania);
        unirPaises(polonia, hungaria);
        unirPaises(polonia, bulgaria);
        unirPaises(polonia, tartaria);
        unirPaises(polonia, rumania);

        //HUNGARIA
        unirPaises(hungaria, germania);
        unirPaises(hungaria, italia);
        unirPaises(hungaria, turquia);
        unirPaises(hungaria, bulgaria);
        unirPaises(hungaria, polonia);

        //BULGARIA
        unirPaises(bulgaria, hungaria);
        unirPaises(bulgaria, polonia);
        unirPaises(bulgaria, turquia);
        unirPaises(bulgaria, tartaria);

        //TURQUIA
        unirPaises(turquia, italia);
        unirPaises(turquia, hungaria);
        unirPaises(turquia, bulgaria);
        unirPaises(turquia, tartaria);

        //TARTARIA
        unirPaises(tartaria, rumania);
        unirPaises(tartaria, polonia);
        unirPaises(tartaria, bulgaria);
        unirPaises(tartaria, turquia);

        //RUMANIA
        unirPaises(rumania, escandinavia);
        unirPaises(rumania, polonia);
        unirPaises(rumania, tartaria);
    }

    public Grafo getGrafo() {
        return grafo;
    }

    private void unirPaises(Pais pais1, Pais pais2) {
        Nodo nodo1 = new Nodo(pais1, null, 0);
        Nodo nodo2 = new Nodo(pais2, null, 0);
        grafo.unir(nodo1, nodo2);
    }

    private Pais cargarPais(int id) {
        String consulta = "SELECT * FROM Pais WHERE idPais = " + id;
        ResultSet resultado = null;
        Pais pais = new Pais();

        try {
            AccesoDatos acceso = new AccesoDatos();
            resultado = acceso.consultar(consulta);

            while (resultado.next()) {
                pais.setIdPais(id);
                pais.setNombre(resultado.getString("nombre"));
                pais.setCoordenadaX(resultado.getInt("x"));
                pais.setCoordenadaY(resultado.getInt("y"));
            }

            acceso.Desconectar();

        } catch (SQLException e) {
        } catch (ClassNotFoundException e2) {
        }

        return pais;
    }
}
