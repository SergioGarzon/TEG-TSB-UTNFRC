/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author notebook
 */
public class Arco 
{
   private int peso;
   private boolean existe;
   
   public Arco ( )
   {
   }
   
   public Arco ( int p )
   {
      peso = p;
      existe = true;
   }
   
   public int getPeso()
   {
      return peso;    
   }
   
   public void setPeso(int p)
   {
      peso = p;    
   }
   
   public boolean exists()
   {
       return existe;
   }
   
   public void set (boolean e)
   {
       existe = e;
   }
   
   @Override
   public String toString()
   {
      return "Peso: " + peso;    
   }
}
