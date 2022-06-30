/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logico;

/**
 *
 * @author Alessandro
 */
public class Main {
    public static void main(String[] args) {
        
        //Se crea la HashTable
        HashTable articulos = new HashTable();
        
        //Se crean algunos Articulos
        Articulo a1 = new Articulo("EL chavo del ocho");
        Articulo a2 = new Articulo("Code Geass");
        
        //Se insertan los articulos en la HashTable
        articulos.insertar(a1.getTitulo(), a1);
        articulos.insertar(a2.getTitulo(), a2);
        
        //Se imprimen los ariculos utilizando la key de cada articulo
        System.out.println("Articulo 1 = " + articulos.buscarArticulo(a1.getTitulo()).getTitulo());
        System.out.println("Articulo 2 = " + articulos.buscarArticulo(a2.getTitulo()).getTitulo());
        
        //Se elimina un articulo
        articulos.eliminar(a1.getTitulo());
        
        //Se imprimen todos los articulos de manera iterativa
        for (NodoArticulo nodo : articulos.getTabla()) {
            while (nodo != null) {                
                System.out.println(nodo.getInfo().getTitulo());
                nodo = nodo.getSiguiente();
            }
        }

    }
}
