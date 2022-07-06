package Logico;

import java.io.Serializable;

/**
 * 
 * @author Alessandro
 */

/* 
 Se crea una clase nodo que nos permita manejar la información
 de los articulos, acceder a la información de los mismos y de cierta
 forma iterarlos.
*/

public class NodoArticulo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String key; //Texto que se utilizará para generar el codigo hash
    private Articulo info; //Contenido del articulo
    private NodoArticulo siguiente; //Referencia al siguiente articulo

    //Getter y Setters (No es necesario un constructor)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Articulo getInfo() {
        return info;
    }

    public void setInfo(Articulo info) {
        this.info = info;
    }

    public NodoArticulo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoArticulo siguiente) {
        this.siguiente = siguiente;
    }
}
