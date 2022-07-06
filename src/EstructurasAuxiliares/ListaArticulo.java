package EstructurasAuxiliares;

import Logico.Articulo;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @author Alessandro
 */
public class ListaArticulo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Articulo[] arreglo; //El arreglo que se estará expandiendo
    private int capacidad; //La capacidad actual del arreglo
    private int cont; //Este contador ayudará de manera auxliar a aumentar la capacidad del arreglo

    //Controlador
    public ListaArticulo()
    {
        arreglo = new Articulo[1];
        capacidad = 1;
    }

    //Controlador 2
    public ListaArticulo(int capacidad)
    {
        this.capacidad = capacidad;
        arreglo = new Articulo[capacidad];
    }

    //Obtiene la cantidad actual de objetos reservados en el arreglo
    public int getSize()
    {
        return cont;
    }

    //Para obtener la referencia donde está el arreglo
    public Articulo[] getArticulos()
    {
        return arreglo;
    }

    //Para obtener la referencia de X objeto de tipo Articulo del mismo arreglo
    public Articulo getArticuloByIndex(int index)
    {
        return arreglo[index];
    }

    //Inserta una referencia de un objeto de tipo Articulo en el arreglo
    public void agregar(Articulo refAlmacen)
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