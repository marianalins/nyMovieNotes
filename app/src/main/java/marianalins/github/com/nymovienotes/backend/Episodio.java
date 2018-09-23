package marianalins.github.com.nymovienotes.backend;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Set;

public class Episodio extends Titulo implements Serializable {
    private boolean visto;
    private int temporada;
    private int numEpisodio;
    private int serie;

    public Episodio(String nome, Serie serie) {
        super(nome);
        this.serie = serie.getCodigo();
    }

    public Episodio(String nome, int ano, String pais, String idioma, int duracao, String genero,
                    int codigo, URL trailer, int nota, int imdbNota, String sinopse,
                    Set<Integer> diretores, Set<Integer> atores, File foto, boolean visto,
                    int temporada, int numEpisodio, int serie) {
        super(nome, ano, pais, idioma, duracao, genero, codigo, trailer, nota, imdbNota, sinopse,
                diretores, atores, foto);
        this.visto = visto;
        this.temporada = temporada;
        this.numEpisodio = numEpisodio;
        this.serie = serie;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getNumEpisodio() {
        return numEpisodio;
    }

    public void setNumEpisodio(int numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o instanceof Episodio) {
            Episodio episodio = (Episodio) o;
            return visto == episodio.isVisto() &&
                    temporada == episodio.getTemporada() &&
                    numEpisodio == episodio.getNumEpisodio() &&
                    serie == episodio.serie;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (visto ? 1 : 0) + temporada + numEpisodio + serie;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "visto=" + visto +
                ", temporada=" + temporada +
                ", numEpisodio=" + numEpisodio +
                ", serie=" + serie +
                '}';
    }
}
