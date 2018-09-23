package marianalins.github.com.nymovienotes.backend;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Set;

public class Filme extends Titulo implements Serializable {

    public Filme(String nome) {
        super(nome);
    }

    public Filme(String nome, int ano, String pais, String idioma, int duracao, String genero,
                 int codigo, URL trailer, int nota, int imdbNota, String sinopse,
                 Set<Integer> diretores, Set<Integer> atores, File foto) {
        super(nome, ano, pais, idioma, duracao, genero, codigo, trailer, nota, imdbNota, sinopse,
                diretores, atores, foto);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Filme{}";
    }
}
