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
public class Grafo {
    
    protected Nodo []nodos; // vector de nodos
    protected Arco [][]ady;    // matriz de adyacencias

    /**
     * Inicializa el grafo para un máximo de 12 nodos.
     */
    public Grafo()
    {
        this(12);
    }

    /**
     * Inicializa el grafo para un máximo de n nodos.
     * @param n el número máximo de nodos que serán representados en este grafo.
     */
    public Grafo(int n )
    {
        // el vector de nodos, con todas su casillas valiendo null...
        nodos = new Nodo[n];

        // la matriz de adyacencias...
        ady   = new Arco [n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                //... llena de Arcos con "existe" valiendo false
                ady[i][j] = new Arco();
            }
        }
    }

    /**
     * Indica la cantidad de nodos representados en este grafo.
     * @return la cantidad de nodos del grafo.
     */
    public int length()
    {
        return nodos.length;
    }

    /**
     * Permite unir dos nodos n1 y n2 con un arco cuyo factor de peso se asume igual a cero. El arco partira del
     * nodo n1 y llegará al nodo n2 en caso de poder hacerse la unión. Si alguno de los nodos n1 o n2 no existe
     * en el grafo, la operación no se llevará a cabo. Lo mismo vale si los objetos n1 o n2 no son de la misma
     * clase que los objetos que ya están en el grafo.
     * @param n1 el nodo de partido del arco.
     * @param n2 el nodo de llegada del arco.
     * @return true si la operación se llevó a cabo - false en caso contrario.
     */
    public boolean unir ( Object n1, Object n2 )
    {
        return unir(n1, n2, 0);
    }

    /**
     * Permite unir dos nodos n1 y n2 con un arco cuyo factor de peso será igual a p. Si alguno
     * de los nodos n1 o n2 no existe en el grafo, la operación no se llevará a cabo. Lo mismo
     * vale si los objetos n1 o n2 no son de la misma clase que los objetos que ya están en el grafo.
     * @param n1 el primer nodo que define al arco.
     * @param n2 el segundo nodo que define al arco.
     * @param p el peso del arco que unirá a n1 y n2.
     * @return true si la operación se llevó a cabo - false en caso contrario.
     */
    public boolean unir ( Object n1, Object n2, int p )
    {
        boolean out = false;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
            ady[i1][i2] = new Arco(p);
            ady[i2][i1] = ady[i1][i2];
            out = true;
        }
        return out;
    }

    /**
     * Permite cortar el arco que une a los nodos n1 y n2, suponiendo que el arco partía del nodo n1 y
     * llegaba al nodo n2. El peso del arco (si existía el arco) se perderá. Si alguno de los nodos n1
     * o n2 no existe en el grafo, la operación no se llevará a cabo. Lo mismo vale si los objetos n1 o
     * n2 no son de la misma clase que los objetos que ya están en el grafo.
     * @param n1 el nodo de partida del arco.
     * @param n2 el nodo de llegada del arco.
     * @return true si la operación se llevó a cabo - false en caso contrario.
     */
    public boolean cortar ( Object n1, Object n2 )
    {
        boolean out = false;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
            ady[i1][i2].set(false);
            out = true;
        }
        return out;
    }

    /**
     * Retorna true si había un arco que partiera de n1 y llegara a n2, y false en caso contrario.
     * @param n1 Nodo de partida del arco chequeado.
     * @param n2 Nodo de llegada del arco chequeado.
     * @return true si había un arco partiendo de n1 y llegando a n2 - false en caso contrario.
     */
    public boolean hayArco ( Object n1, Object n2 )
    {
        boolean out = false;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
            out = ady[i1][i2].exists();
        }
        return out;
    }

    /**
     * Retorna el peso del arco que parte de n1 y llega a n2 (si tal arco existe), o retorna cero si el arco no
     * existe. Para evitar ambiguedades respecto del valor retornado, la invocación a este método debería ir
     * precedida de una invocación al método hayArco() para validar que el arco exista.
     * @param n1 Nodo de partida del arco chequeado.
     * @param n2 Nodo de llegada del arco chequeado.
     * @return el peso del arco.
     */
    public int getPeso ( Object n1, Object n2 )
    {
        int out = 0;
        int i1 = buscar (n1);
        int i2 = buscar (n2);
        if( i1 != -1 && i2 != -1 )
        {
            out = ady[i1][i2].getPeso();
        }
        return out;
    }

    protected int buscar (Object n1)
    {
        if(n1 != null)
        {
            if( nodos[0] != null && n1.getClass() == nodos[0].getClass() )
            {
                for (int i=0; i<nodos.length; i++)
                {
                    if ( nodos[i].equals(n1) ) { return i; }
                }
            }
        }
        return -1;
    }
}