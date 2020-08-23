/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEG;


import java.awt.Button;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InformacionProyecto extends Frame implements ActionListener {

    private Frame frame;
    private Button boton, boton2;
    private Label etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiqueta5, etiqueta6, etiqueta7, etiqueta8;

    public InformacionProyecto() {
        this.frame = new Frame();
        this.frame.setVisible(true);
        this.frame.setTitle("Presentación");
        this.frame.setSize(500, 500);
        this.frame.setLocationRelativeTo(null);

        this.boton = new Button();
        this.boton.setLabel("Continuar");
        this.boton.setBounds(new Rectangle(180, 390, 120, 25));

        this.boton2 = new Button();
        this.boton2.setLabel("Salir");
        this.boton2.setBounds(new Rectangle(180, 420, 120, 25));

        this.etiqueta1 = new Label();
        this.etiqueta1.setText("       Trabajo Práctico");
        this.etiqueta1.setFont(new Font("Arial", Font.BOLD, 30));
        this.etiqueta1.setBounds(new Rectangle(65, 17, 400, 80));

        this.etiqueta2 = new Label();
        this.etiqueta2.setText("-Cátedra: Tecnologia de Software de Base");
        this.etiqueta2.setFont(new Font("Arial", Font.BOLD, 15));
        this.etiqueta2.setBounds(new Rectangle(75, 120, 400, 25));

        this.etiqueta3 = new Label();
        this.etiqueta3.setText("-Alumnos: ");
        this.etiqueta3.setFont(this.etiqueta2.getFont());
        this.etiqueta3.setBounds(new Rectangle(75, 160, 400, 25));

        this.etiqueta4 = new Label();
        this.etiqueta4.setText("    *Dellarossa, Enzo Maximiliano. Legajo: 57646");
        this.etiqueta4.setFont(this.etiqueta2.getFont());
        this.etiqueta4.setBounds(new Rectangle(75, 200, 400, 25));

        this.etiqueta5 = new Label();
        this.etiqueta5.setText("    *Garzón, Pablo Fernando. Legajo: 52127");
        this.etiqueta5.setFont(this.etiqueta2.getFont());
        this.etiqueta5.setBounds(new Rectangle(75, 240, 400, 25));
        
        this.etiqueta6 = new Label();
        this.etiqueta6.setText("    *Garzón, Sergio Gabriel. Legajo: 54330");
        this.etiqueta6.setFont(this.etiqueta2.getFont());
        this.etiqueta6.setBounds(new Rectangle(75, 280, 400, 25));
        
        this.etiqueta7 = new Label();
        this.etiqueta7.setText("-Curso: 3er año");
        this.etiqueta7.setFont(this.etiqueta2.getFont());
        this.etiqueta7.setBounds(new Rectangle(75, 320, 400, 25));
        
        this.etiqueta8 = new Label();
        this.etiqueta8.setText("-Coordinador de Cátedra: Ing. Valerio Fritelli");
        this.etiqueta8.setFont(this.etiqueta2.getFont());
        this.etiqueta8.setBounds(new Rectangle(75, 360, 400, 25));


        Container contenedor = new Container();
        contenedor.setLayout(null);

        contenedor.add(this.boton);
        contenedor.add(this.boton2);
        contenedor.add(this.etiqueta1);
        contenedor.add(this.etiqueta2);
        contenedor.add(this.etiqueta3);
        contenedor.add(this.etiqueta4);
        contenedor.add(this.etiqueta5);
        contenedor.add(this.etiqueta6);
        contenedor.add(this.etiqueta7);
        contenedor.add(this.etiqueta8);

        this.frame.add(contenedor);

        this.boton.addActionListener(this);
        this.boton2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.boton) {
            this.frame.setVisible(false);
            new Principal();
        }
        else
            System.exit(0);
    }



}
