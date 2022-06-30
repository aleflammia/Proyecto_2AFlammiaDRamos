package Logico;

/**
 *
 * @author Diego
 */
public class HashTable {

    private NodoArticulo[] tabla; //Se crea la lista de NodoArticulo que será la HashTable
    private int cont; //Capacidad de la tabla
    
    //Constructor
    public HashTable(){
        //Se inicializa la tabla con 43, debido a que es un número primero y evita más las colisiones
        tabla = new NodoArticulo[43];
    }
    
    /*
     La función "Insertar" agrega un nuevo articulo a la tabla hash, siempre evualuando
     si exise una colisión y de paso, si la tabla de hash tiene más del 75% de su 
     espacio ocupado, pues este se ampliará mediante la función "resize"
    */ 
    public void insertar(String key, Articulo info){
        
        int posicion = hash(key) % tabla.length;

        NodoArticulo lista = tabla[posicion];
        while (lista != null) {            
            if (lista.getKey().equals(key)) {
                break;
            }
            lista = lista.getSiguiente();
        }
        
        if (lista != null) {
            lista.setInfo(info);
        }
        else{
            if (cont >= 0.75 * tabla.length) {
                resize();
                posicion = hash(key) % tabla.length;
            }
            NodoArticulo nuevoArticulo = new NodoArticulo();
            nuevoArticulo.setKey(key);
            nuevoArticulo.setInfo(info);
            nuevoArticulo.setSiguiente(tabla[posicion]);
            tabla[posicion] = nuevoArticulo;
            cont++;
        }
    }
    
    /*
     La Función "hash" se encarga de crear un código hash a partir el nombre del
     Articulo. Esta toma el valor ASCII de cada letra y la suma multiplicando cada una 
     por su posición en la oración. Esta función Hash 
     casi no genera colisiones. Lo cuál resulta muy efecivo.
    */
    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i) * (i+1);
        }
        return hash;
    }
    
    /*
     La función "resize" crea una tabla nueva duplicando la longitud de la anterior,
     reubica los elemenos dentro y asigna esta nueva tabla a la anterior
    */
    private void resize() {
        
        NodoArticulo[] nuevaTabla = new NodoArticulo[tabla.length*2];
        for (NodoArticulo lista : tabla) {
            while (lista != null) {
                NodoArticulo siguiente = lista.getSiguiente();
                int nuevaPosicion = hash(lista.getKey()) % nuevaTabla.length;
                lista.setSiguiente(nuevaTabla[nuevaPosicion]);
                nuevaTabla[nuevaPosicion] = lista;
                lista = siguiente;
            }
        }
        tabla = nuevaTabla;
    }
    
    /*
     La función "buscarArticulo" busca un articulo a partir de su codigo hash,
     luego de que se accede a la posición del elemento en tiempo constante pues se
     procede a recorrer la lista para encontrar el articulo en específico y retornarlo
    */
    public Articulo buscarArticulo(String key){
        
        int posicion = hash(key) % tabla.length;

        NodoArticulo lista = tabla[posicion];

        while (lista != null) {
            if (lista.getKey().equals(key)) {
                return lista.getInfo();
            }
            lista = lista.getSiguiente();
        }
        return null;
    }
    
    /*
     La función "eliminar" busca un articulo a partir de su codigo hash,
     luego de que se accede a la posición del elemento se elimina y las referencias
     a los NodoArticulo se conectan dejando el eliminado afuera
    */
    public void eliminar(String key){
        
        int posicion = hash(key) % tabla.length;
        
        //Si no existe retorna
        if (tabla[posicion] == null) {
            return;
        }
        //Si solo existe un elemento con ese código hash se elimina inmediatamente
        if (tabla[posicion].getKey().equals(key)) {
            tabla[posicion] = tabla[posicion].getSiguiente();
            cont--;
            return;
        }
        //Si existen varios elementos con el mismo código hash 
        NodoArticulo anterior = tabla[posicion]; 
        NodoArticulo actual = anterior.getSiguiente(); 
        while (actual != null && !actual.getKey().equals(key)) {  
            actual = actual.getSiguiente();
            anterior = actual;
        }
        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente()); 
            cont--; 
        }
    }
    
    /*
     La función "getTabla" retorna la lista de nodos que coniene la tabla hash para posteriormente hacer
     futuras iteraciones fuera de la clase HashTable
    */
    public NodoArticulo[] getTabla(){
        return tabla;
    }
    
    /*
     La función "getSize" retorna el tamaño actual de la tabla
    */
    public int getSize(){
        return cont;
    }
}
