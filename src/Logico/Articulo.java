package Logico;

import EstructurasAuxiliares.ListaString;
import java.io.Serializable;

/**
 * 
 * @author Alessandro
 */

public class Articulo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String titulo;   //Titulo del articulo
    private ListaString autores; //Lista de Autores
    private String cuerpoResumen; //Cuerpo del resumen
    private ListaString palabrasClave; //Lista de palabras Claves
    
    //Constructor
    public Articulo(String titulo) {
            super();
            this.titulo = titulo;
            this.autores = new ListaString();
            this.cuerpoResumen = null;
            this.palabrasClave = new ListaString();
    }
    
    //Getter y Setters
    public String getTitulo() {
            return titulo;
    }
    public void setTitulo(String titulo) {
            this.titulo = titulo;
    }
    public ListaString getAutores() {
            return autores;
    }
    public void setAutores(ListaString autores) {
            this.autores = autores;
    }
    public String getCuerpoResumen() {
            return cuerpoResumen;
    }
    public void setCuerpoResumen(String cuerpoResumen) {
            this.cuerpoResumen = cuerpoResumen;
    }
    public ListaString getPalabrasClave() {
            return palabrasClave;
    }
    public void setPalabrasClave(ListaString palabrasClave) {
            this.palabrasClave = palabrasClave;
    }

}
