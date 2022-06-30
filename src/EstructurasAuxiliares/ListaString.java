package EstructurasAuxiliares;

/**
 *
 * @author Diego
 */

import java.util.Arrays;

public class ListaString {
    
    private String[] arreglo; //El arreglo que se estará expandiendo
    private int capacidad; //La capacidad actual del arreglo
    private int cont; //Este contador ayudará de manera auxliar a aumentar la capacidad del arreglo

    //Controlador
    public ListaString()
    {
        arreglo = new String[1];
        capacidad = 1;
    }

    //Controlador 2
    public ListaString(int capacidad)
    {
        this.capacidad = capacidad;
        arreglo = new String[capacidad];
    }

    //Obtiene la cantidad actual de objetos reservados en el arreglo
    public int getSize()
    {
        return cont;
    }

    //Para obtener la referencia donde está el arreglo
    public String[] getElementos()
    {
        return arreglo;
    }

    //Para obtener la referencia de X objeto de tipo String del mismo arreglo
    public String getElementoByIndex(int index)
    {
        return arreglo[index];
    }

    //Inserta una referencia de un objeto de tipo String en el arreglo
    public void agregar(String refAlmacen)
    {
        ++cont;
        if(cont > capacidad)
        {
            capacidad += 1;
            arreglo = Arrays.copyOf(arreglo, capacidad);
        }
        arreglo[cont - 1] = refAlmacen;
    }
}