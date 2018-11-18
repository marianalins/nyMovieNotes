package marianalins.github.com.nymovienotes.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IteradorLista<T extends Mostraveis> implements Iterador<T> {
    private final List<T> lista;
    private int indice = 0;

    public IteradorLista(Collection<? extends T> colecao) {
        lista = new ArrayList<>();
        lista.addAll(colecao);
    }

    public IteradorLista(IteradorLista<T> iter) {
        this(iter.lista);
    }

    @Override
    public boolean hasNext() {
        return (indice < lista.size());
    }

    @Override
    public T next() throws IndexOutOfBoundsException {
        if(hasNext())
            return lista.get(indice++);
        else
            throw new IndexOutOfBoundsException("Fora da lista");
    }

    @Override
    public T peek() throws IndexOutOfBoundsException {
        if(hasNext())
            return lista.get(indice);
        else
            throw new IndexOutOfBoundsException("Fora da lista");
    }
}
