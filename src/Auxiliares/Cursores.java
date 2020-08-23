/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author equipo
 */
public class Cursores {

    private Image imagenCursor;
    private Cursor cur;
    private String ruta;

    public Cursores() {
        this.ruta = System.getProperty("user.dir").concat("/Recursos/Imagenes/ImagenCursores/");
    }

    public Cursores(int img) {
        this.ruta = System.getProperty("user.dir").concat("/Recursos/Imagenes/ImagenCursores/");
        setImgCursor(img);
    }

    public void setImgCursor(int img) {
        switch (img) {
            case 1:
                imagenCursor = Toolkit.getDefaultToolkit().createImage(ruta.concat("cursor.png"));
                break;
            case 2:
                imagenCursor = Toolkit.getDefaultToolkit().createImage(ruta.concat("cursor2.png"));
                break;
            case 3:
                imagenCursor = Toolkit.getDefaultToolkit().createImage(ruta.concat("cursor3.png"));
                break;
            case 4:
                imagenCursor = Toolkit.getDefaultToolkit().createImage(ruta.concat("cursor4.png"));
                break;
            case 10:
                imagenCursor = Toolkit.getDefaultToolkit().createImage(ruta.concat("cursor10.png"));
                break;
        }

        cur = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(10, 10), "Cross cursor");
        imagenCursor.flush();
    }

    public Cursor getCursor() {
        return (cur);
    }
}
