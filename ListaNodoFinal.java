
import java.util.*;
/**
 * Implementación de listas, las cuáles guardan una referencia al nodo final ,
 * para reducir la complejidad de algunos métodos.
 *
 */
public class ListaNodoFinal <E> implements Listable<E> { //Implementación de la clase con referencia a la clase listable.

    private int cuantos; 
    private Nodo<E> inicio;
    private Nodo<E> ultimo;
     /**
     * Constructor por defecto.
     */
    public ListaNodoFinal() {
        cuantos = 0;
        inicio = ultimo = null;
    }

    
    @Override
    public boolean estaVacia() {
        return cuantos == 0;
    }

     /**
     * Vacía la lista
     */
    @Override
    public void vaciar() {
        cuantos = 0;
        inicio = null;
        ultimo = null;
    }

    @Override
    public int tamanio() {
        return cuantos;
    }

    @Override
    public void agregarInicio(E elemento) {
        if (estaVacia()) { //Consulta si la lista esta vacia.
            Nodo<E> nuevo =  new Nodo(elemento); //Definimos un nuevo nodo
            //Inicializamos la lista
            inicio = nuevo; 
            ultimo = nuevo;
            cuantos++; //Incrementa el contador del tamaño de la lista.
        }else{ //Caso contrario va a agregando los nodos al inicio de la lista.
            Nodo<E> nuevo = new Nodo(elemento); //Definimos un nuevo nodo
            nuevo.sgte = inicio; //Une el nuevo nodo con la lista existente.
            inicio = nuevo; //Renombra al nuevo nodo.
            cuantos++; //Incrementa el contador del tamaño de la lista.
        }
    }

    @Override               
    public void agregarFinal(E elemento) {
        if (estaVacia()){ //Consultamos si esta vacia la lista.
            Nodo<E> nuevo =  new Nodo(elemento); //Definimos un nuevo nodo
            inicio = nuevo; // Inicializa la lista agregando como inicio al nuevo nodo.
            ultimo = nuevo;
            cuantos++; //Incrementa el contador del tamaño de la lista.
        // Caso contrario recorre la lista hasta llegar al ultimo nodo
        //y agrega el nuevo.
        }else{ 
            Nodo<E> nuevo = new Nodo(elemento); //Definimos un nuevo nodo.
            ultimo.sgte = nuevo; //Agrega al nuevo nodo al final de la lista.
            ultimo = nuevo;
            cuantos++; //Incrementa el contador del tamaño de la lista.
        }
    }

    @Override
    public boolean contiene(E elemento) {
        if(!estaVacia()){
            Nodo<E> posicion =  inicio; //Crea una copia de la lista
        // Recorre la lista hasta encontrar el elemento o hasta 
        // llegar al final de la lista
            while(posicion != null && !posicion.elemento.equals(elemento)){
                posicion = posicion.sgte;
            }
          /*  if (posicion== null){
                return false;
            }else{
                return true;
            }*/
            return posicion != null;

        }else{
            return false;
        }
    }

    @Override
    public E primerElemento() {
        return inicio.elemento;
    }

    @Override
    public E ultimoElemento() {
        return ultimo.elemento;
    }

    @Override
    public void eliminarPrimero() {
        if (!estaVacia()){ //Consulta si la lista no esta vacia
            inicio = inicio.sgte; //Elimina el primer nodo apuntando al siguiente
            if (inicio == null){
                ultimo = null;
            }
            cuantos--; //Disminuye el contador del tamaño de la lista
        }else{
            System.out.println("Lista sin elementos");
        }
    }

    @Override
    public void eliminarUltimo() {
        if (!estaVacia()){ //Consulta si la lista no esta vacia.
            if(inicio == ultimo){
                inicio = ultimo = null;
                cuantos--; //Disminuye el contador del tamaño de la lista
            }else {
                Nodo<E> posicion = inicio;
                Nodo<E> anterior = null;
                while(posicion.sgte!=null){
                    anterior = posicion;
                    posicion = posicion.sgte;
                }

                ultimo = anterior;
                ultimo.sgte = null;
                cuantos--; //Disminuye el contador del tamaño de la lista
            }
        }else{
            System.out.println("Lista sin elementos");
        }
    }

    @Override
    public void eliminar(E elemento) {

        Nodo<E> posicion = inicio;
        Nodo<E> anterior = null;

        while (posicion != null && !posicion.elemento.equals(elemento)) {
            anterior = posicion;
            posicion = posicion.sgte;
        }
        if (posicion != null) {
            if (posicion == inicio) {
                inicio = inicio.sgte;
            } else {
                anterior.sgte = posicion.sgte;
            }
            cuantos--;
        }

        }

    @Override
    public void sustituir(E actual, E nuevo) {
        Nodo<E> posicion =  inicio; //Creamos una copia de la lista
        //Recorre la lista hasta  llegar al nodo de referencia.
        while (posicion != null && !actual.equals(posicion.elemento)){
                posicion = posicion.sgte;
            }
            if (posicion !=null){
                posicion.elemento = nuevo; //Actualizamos el valor del nodo
            }
            }
        
    @Override
    public void sustituirTodas(E actual, E nuevo){
        if(!estaVacia()){
            Nodo<E> posicion =  inicio;
            //Si la lista contiene al elemento  actual
            if(contiene(actual)){
                //Mientras La posicion no sea vacía
                while(posicion != null){
                    if(posicion.elemento.equals(actual))
                        posicion.elemento = nuevo;
                        posicion = posicion.sgte;
                }
            }//fin del if
        }else
            System.out.println("Lista sin elementos");
    }

    @Override
    public void imprimir(){

        System.out.println("Imprimiendo elementos de la lista ");

        Iterator it =  iterador(); //Creamos un iterador
        //Recorremos toda la lista e imprimimos nodo por nodo.
        while (it.hasNext()){ 
            System.out.println(it.next() );
        }

    }

    @Override
    public Iterator iterador() {
        return new MiIterador();
    }

       /*
         * Clase interna que implementa el iterador
         */
        private class MiIterador implements Iterator<E> {

            private Nodo<E> posicion;

            public MiIterador() {
                posicion = inicio;
            }

            @Override
            public boolean hasNext() {
                return posicion != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E elemento = posicion.elemento;
                    posicion = posicion.sgte;
                    return elemento;
                }
                throw new java.util.NoSuchElementException();
            }

        }
    
}

    
