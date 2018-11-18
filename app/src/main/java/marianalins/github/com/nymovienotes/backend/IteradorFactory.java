package marianalins.github.com.nymovienotes.backend;

import android.os.Bundle;

import java.util.Collection;
import java.util.Iterator;

public class IteradorFactory {
    public static <T extends Mostraveis> Iterador<T>
            newInstance(Collection<? extends T> colecao) {
        return new IteradorLista<>(colecao);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Mostraveis> Iterador<Mostraveis> newInstance(Iterador<Pessoa> iterador) {
        return new IteradorLista<Mostraveis>((IteradorLista<Pessoa>) iterador);
    }
}
