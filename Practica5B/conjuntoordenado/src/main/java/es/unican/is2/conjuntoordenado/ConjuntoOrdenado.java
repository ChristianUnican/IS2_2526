import java.util.ArrayList;
import java.util.List;

import es.unican.is2.adt.IConjuntoOrdenado;

/**
 * Clase que implementa el ADT ConjuntoOrdenado.
 * VERSIÓN ORIGINAL CON BUGS - para ejecutar pruebas primero.
 */
public class ConjuntoOrdenado<E extends Comparable<E>> implements IConjuntoOrdenado<E> {

    private List<E> lista = new ArrayList<E>();

    public E get(int indice) {
        return lista.get(indice);
    }

    public boolean add(E elemento) {
        int indice = 0;
        if (elemento == null)
            throw new NullPointerException();
        if (lista.size() != 0) {
            // BUG 1: La condicion de comparacion esta invertida.
            // Deberia ser > 0 (para insertar en orden ascendente),
            // pero usa < 0, lo que ordena de forma DESCENDENTE.
            while (indice < lista.size() && elemento.compareTo(lista.get(indice)) < 0) {
                indice++;
            }
            // BUG 2: No comprueba si el elemento ya existe (duplicados).
            // Un Conjunto no deberia admitir duplicados, pero este codigo
            // siempre hace lista.add() sin devolver false nunca.
        }
        lista.add(indice, elemento);
        return true;
    }

    public E remove(int indice) {
        E borrado = lista.remove(indice);
        return borrado;
    }

    public int size() {
        return lista.size();
    }

    public void clear() {
        lista.clear();
    }

}
