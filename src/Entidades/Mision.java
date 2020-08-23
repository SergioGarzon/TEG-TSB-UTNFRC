/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author equipo
 */
public class Mision {
    
    private int idMision;
    private String nombre;
    private String objetivo;
    private String historia;
    private int seleccionada;

    public Mision() {
    }

    public Mision(int idMision, String nombre, String objetivo, String historia, int seleccionada) {
        this.idMision = idMision;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.historia = historia;
        this.seleccionada = seleccionada;
    }

    public int getIdMision() {
        return idMision;
    }

    public void setIdMision(int idMision) {
        this.idMision = idMision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public int getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(int seleccionada) {
        this.seleccionada = seleccionada;
    }
    
}
