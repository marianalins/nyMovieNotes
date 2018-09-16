package marianalins.github.com.nymovienotes.back;

import java.net.URL;
import java.util.Objects;

public class Episodio extends Titulo{
    private boolean visto;
    private int temporada;
    private int numEpisodio;
    private int serie;

    public Episodio(String nome, Serie serie) {
        super(nome);
        this.serie = serie.getCodigo();
    }

    public Episodio(String nome, int ano, String pais, String idioma, int duracao, String genero,
                    int codigo, URL trailer, int nota, int imdbNota, String sinopse, boolean visto,
                    int temporada, int numEpisodio, int serie) {
        super(nome, ano, pais, idioma, duracao, genero, codigo, trailer, nota, imdbNota, sinopse);
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
        return Objects.hash(super.hashCode(), visto, temporada, numEpisodio, serie);
    }
}
