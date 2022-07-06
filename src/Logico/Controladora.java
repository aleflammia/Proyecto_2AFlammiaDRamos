/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logico;

import EstructurasAuxiliares.ListaArticulo;
import EstructurasAuxiliares.ListaString;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author Alessandro
 */

public class Controladora implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private HashTable hashTable; //Se crea la HashTable
    private static Controladora controladora; //Instancia de la clase controladora

    //Constructor
    public Controladora() {
        
        hashTable = new HashTable();
    }
    
    //Obtener la instancia del programa mediante el uso del patrón de diseño "Singleton"
    public static Controladora getInstance() {
        
        if(controladora == null) {
                controladora = new Controladora();
        }
        return controladora;
    }

    //Getter y setter
    public static void setControladora(Controladora controladora) {
        Controladora.controladora = controladora;
    }

    public HashTable getHashTable() {
        return hashTable;
    }

    public void setHashTable(HashTable hashTable) {
        this.hashTable = hashTable;
    }
    
    /*
     La función "agregarArticulo" inserta dentro de la HashTable un articulo
     utilizando como llave el titulo del mismo.
    */ 
    public void agregarArticulo(Articulo articulo){
        if (articulo != null) {
            hashTable.insertar(articulo.getTitulo(), articulo);
        }
    }
    
    /*
     La función "cargarArchivoResumen" devuelve un articulo con toda la información 
     que contenía en el archivo que ha sido enviado por parámetro "Ruta"
    */ 
    public Articulo cargarArchivoResumen(String ruta){

        BufferedReader br = null;
        Articulo art = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(ruta));
            // Leer la primera línea, guardando en un String
            String texto = br.readLine();
            art = new Articulo(limpiar(texto));
            ListaString listaAutores = new ListaString();
            ListaString listaPalabrasClaves = new ListaString();
            String resumenCuerpo = "";
            int control = 0;

            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                // Hacer lo que sea con la línea leída
                // En este ejemplo sólo se muestra por consola
                
                if (texto.contains("Autores")) {
                    control = 1;
                }   
                else if (texto.contains("Resumen")) {
                    control = 2;
                }
                else if (texto.contains("Palabras claves:")) {
                    String palabrasSubString = texto.substring(texto.indexOf(":")+2);
                    String[] palabras = palabrasSubString.split(", ");
                    for (String palabra : palabras) {
                        listaPalabrasClaves.agregar(Controladora.getInstance().limpiar(palabra));
                    }
                }
                
                if (control == 1 ) {
                    if (!texto.contains("Autores")) {
                        if (!texto.equals("")) {
                            listaAutores.agregar(Controladora.getInstance().limpiar(texto));
                        }
                    }
                }
                
                if (control == 2) {
                    if (!texto.contains("Resumen")) {
                        resumenCuerpo += texto;
                    }
                }
                
                // Leer la siguiente línea
                texto = br.readLine();
            }
            
            art.setCuerpoResumen(resumenCuerpo);
            art.setAutores(listaAutores);
            art.setPalabrasClave(listaPalabrasClaves);
        }
        // Captura de excepción por fichero no encontrado
        catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
        }
        // Captura de cualquier otra excepción
        catch(IOException ex) {
            System.out.println("Error de lectura del fichero");
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(br != null) {
                    br.close();
                }
            }
            catch (IOException ex) {
                System.out.println("Error al cerrar el fichero");
            }
        }
        return art;
    }
    
    /*
     La función "previsualizar" crea en un string la previsualización de un 
     Articulo de una forma agradable, para esto fue indispensable un poco de HTML
     para agregar espacios y ciertos toques que hacen al texto más estético
    */ 
    public String previsualizar(Articulo articulo) {
        
        //Variable que guardará la info del articulo
        String articuloText = "<html><p style=\\\"width:500px\\\">";
        articuloText += "<h1>" + articulo.getTitulo() + "</h1><br>";
        articuloText += "<b>Autores</b><br><br>";
        
        //Guardando los autores dentro de la previsualización
        for (String elemento : articulo.getAutores().getElementos()) {
            articuloText += elemento + "<br>";
        }
        
        articuloText += "<br><b>Resumen</b><br><br>";
        String textoAux = "";
        int cont = 0;
        for (int i = 0; i < articulo.getCuerpoResumen().length(); i++) {
            textoAux += articulo.getCuerpoResumen().charAt(i);
            if (cont >= 150 && String.valueOf(articulo.getCuerpoResumen().charAt(i)).equalsIgnoreCase(" ")) {
                textoAux += "<br>";
                cont = 0;
            }
            cont++;
        }
        articuloText += textoAux;
        articuloText += "<br><br><b>Palabras Claves</b><br><br>";
        
        //Guardando las palabras claves dentro de la previsualización
        for (String elemento : articulo.getPalabrasClave().getElementos()) {
            articuloText += elemento + "<br>";
        }
        
        //Cierre del texto
        articuloText += "</p></html>";
        //scrollArticulo.getViewport().add(new JLabel(articuloText));
        return articuloText;
    }
    
    /*
     La función "ordenarAlfabeticamente" recibe una lista de articulos la cuál 
     mediante dos bucles se ordenan en orden alfabético ascendente, esto se realizó
     usando la función compareTo:
     devuelve <0, entonces la cadena que llama al método es primero lexicográficamente
     devuelve == 0 entonces las dos cadenas son lexicográficamente equivalentes
     devuelve> 0, entonces el parámetro pasado al método compareTo es lexicográficamente el primero.
    */ 
    public ListaArticulo ordenarAlfabeticamente(ListaArticulo articulos) {
        for(int i = 0; i < articulos.getSize() - 1; i++) {
            for(int j = i+1; j < articulos.getSize(); j++) {
                
                if(articulos.getArticuloByIndex(i).getTitulo().compareTo(articulos.getArticuloByIndex(j).getTitulo()) > 0) { 
                    Articulo temp = articulos.getArticuloByIndex(i);
                    articulos.getArticulos()[i] = articulos.getArticuloByIndex(j);
                    articulos.getArticulos()[j] = temp;
                }
            }
        }
        return articulos;
    }

    /*
     La función "previsualizarEstadisticas" crea en un string la previsualización de un 
     Articulo para estadisticas de una forma agradable, para esto fue indispensable 
     un poco de HTML para agregar espacios y ciertos toques que hacen al texto más estético
     En esta parte se imprime a diferencia de la función"Previsualizar" la frecuencia 
     de las palabras claves que aparecen en dicho resumen/articulo.
    */ 
    public String previsualizarEstadisticas(Articulo articulo) {
        
        String articuloText = "<html><p style=\\\"width:500px\\\">";
        articuloText += "<b>Nombre del Trabajo:</b> " + articulo.getTitulo() + "<br><br>";
        articuloText += "<b>Autores</b><br><br>";
        for (String elemento : articulo.getAutores().getElementos()) {
            articuloText += elemento + "<br>";
        }
        articuloText += "<br><b>Palabras Claves</b><br><br>";
        for (String elemento : articulo.getPalabrasClave().getElementos()) {
            articuloText += elemento + "<b>  FRECUENCIA:  </b> " + frecuenciaPalabra(elemento, articulo.getCuerpoResumen()) + "<br>";
        }
        articuloText += "</p></html>";
        //scrollArticulo.getViewport().add(new JLabel(articuloText));
        return articuloText;
    }

    /*
     La función "frecuenciaPalabra" reciba una oración o palabra y un párrafo, usando
     esto devuelve la cantidad de veces que se repite la oración dentro del texto.
     Aquí se utiliza la función split para dividir los textos en arreglos y luego ir 
     comparando los indices de cada arreglo para comprobar su frencuencia.
    */ 
    private int frecuenciaPalabra(String palabraClave, String cuerpoResumen) {
        //Hola Mundo
        //Arreglo 1: Hola [1]
        //           Mundo[2]
        //Arreglo 2 Hola mundo como estás
        //           Hola[1]
        //           mundo[2]
        //           como[3]
        //           estas[4]
        //
        
        //Arreglo 1: [1]
        //Arreglo 2: [1]
        
        
        
        String[] palalabraClaveS = palabraClave.split(" ");
        String[] resumenS = cuerpoResumen.split(" ");
        
        int cont = 0; //Este contador aumenta si una palabra de la oración coincide con una del párrado..
        int frecuencia = 0; //Aumenta si cont es igual a la longitud del arreglo "palabraClaveS"
        
        for (String palabraResumen : resumenS) {
            if (limpiar(palalabraClaveS[cont]).equalsIgnoreCase(limpiar(palabraResumen))) {
                cont++;
            }
            else{
                cont = 0;
            }
            
            if (cont == palalabraClaveS.length) {
                frecuencia++;
                cont = 0;
            }
        }
        
        return frecuencia;
    }
    
    /*
     La función "limpiar" reciba un texto y lo devuelve sin corchetes, barras, algunos
     signos innecesarios, doble punto, coma y punto
    */ 
    public String limpiar(String texto){
        String textoLimpio = texto.replaceAll("[\\-\\+\\.\\^:,]","");
        return textoLimpio;
    }
}
