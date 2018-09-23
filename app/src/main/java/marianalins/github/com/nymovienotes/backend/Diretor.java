package marianalins.github.com.nymovienotes.backend;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Diretor extends Pessoa implements Serializable {

    public Diretor(String nome) {
        super(nome);
    }

    public Diretor(String nome, Date dataNascimento, String pais, int codigo, Set<Integer> titulos, File foto, int nota) {
        super(nome, dataNascimento, pais, codigo, titulos, foto, nota);
    }

    //---------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if(o instanceof Diretor) {
            Diretor diretor = (Diretor) o;
            return super.equals(o);
        }
       return false;
    }

    @Override
    public String toString() {
        return "Diretor{}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
