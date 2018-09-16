package marianalins.github.com.nymovienotes.back;

import java.net.URL;

public class Filme extends Titulo {

    public Filme(String nome) {
        super(nome);
    }
    public Filme(String nome, int ano, String pais, String idioma, int duracao, String genero, int codigo,
                 URL trailer, int nota, int imdbNota , String sinopse) {
        super(nome, ano, pais, idioma, duracao, genero, codigo, trailer, nota, imdbNota, sinopse);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
