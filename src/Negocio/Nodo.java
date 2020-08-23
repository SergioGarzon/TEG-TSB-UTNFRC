/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.Jugador;
import Entidades.Pais;

/**
 *
 * @author notebook
 */
public class Nodo
{
   private Comparable info;
   private Nodo next;
   private int fichas;
   private Jugador jugador;
   private Pais pais;
   
   /**
    *  Constructor por defecto. 
    */
   public Nodo ( )
   {
   }
   
   /**
    *  Crea un nodo incializando todos los atributos en base a parámetros 
    */
   public Nodo (Pais pais, Jugador jugador, int fichas)
   {
       this.pais = pais;
       this.jugador = jugador;
       this.fichas = fichas;
   }
   
   
   

}
