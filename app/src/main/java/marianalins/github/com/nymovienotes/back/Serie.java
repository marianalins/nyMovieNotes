package marianalins.github.com.nymovienotes.back;

import java.net.URL;
import java.util.*;

public class Serie extends Titulo {
    private int numTemporadas;
    private Set<Integer> episodios;
    private transient TituloController tituloC = new TituloController();

    //Construtor menos caso possível
    public Serie(String nome) {
        super(nome);
        episodios = new TreeSet<>();
    }
    //Construtor maior caso possível
    public Serie(String nome, int ano, String pais, String idioma, int duracao, String genero, int codigo,
                 URL trailer, int nota, int imdbNota, String sinopse, int numTemporadas, Set<Integer> episodios) {
        super(nome, ano, pais, idioma, duracao, genero, codigo, trailer, nota, imdbNota, sinopse);
        this.numTemporadas = numTemporadas;
        this.episodios = episodios;
    }

    public List<Episodio> getTemporada(int temp) throws NaoAchadoException{
        List<Episodio> listaRetorno = new ArrayList<>();
       for (Integer e : episodios) {
           try {
               Titulo t = tituloC.getTitulos(e);
               if (t instanceof Episodio) {
                   Episodio ep = (Episodio) t;
                   if (ep.getTemporada() == temp) {
                       listaRetorno.add(ep);
                   }
               }
           } catch (NaoAchadoException nAchado) {
               // Não encontrado
           }

        }

        return listaRetorno;
    }

    public Episodio getEpisodio(int temp , int ep) throws NaoAchadoException {

        for(Integer cod : episodios) {
            Titulo t = tituloC.getTitulos(cod);
            if(t instanceof Episodio) {
                Episodio e = (Episodio) t;
                if(e.getTemporada() == temp && e.getNumEpisodio() == ep) {
                    return e;
                }
            }
        }
        throw new NaoAchadoException("Episódio \"" +ep+ "\" da temporada \"" +temp+ "\" não foi encontrado.");
    }

    public void adicionaEpisodio (Episodio episodio) {
        episodios.add(episodio.getCodigo());
        tituloC.adicionar(episodio);
    }

    public List<Episodio> procurarPorTitulo(String episodio) throws NaoAchadoException{
       List<Episodio> titulos = new ArrayList<>();
        for(Integer e : episodios) {
            try {
                Titulo t = tituloC.getTitulos(e);
                if (t instanceof Episodio) {
                    Episodio ep = (Episodio) t;
                    if (ep.getNome().toLowerCase().contains(episodio.toLowerCase())) {
                        titulos.add(ep);
                    }
                }
            } catch (NaoAchadoException nAchados) {
                // Nao encontrado
            }
        }

        if(titulos.size() == 0) {
            throw new NaoAchadoException("Título de Episódio \""+episodio+"\" Não Encontrado.");
        }
        return titulos;
    }

    public int getNumTemporadas() {
        return numTemporadas;
    }

    public void setNumTemporadas(int numTemporadas) {
        this.numTemporadas = numTemporadas;
    }



    //----------------------------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o instanceof Serie) {
            Serie serie = (Serie) o;
            return numTemporadas == serie.getNumTemporadas() &&
                    episodios.equals(serie.episodios) &&
                    tituloC.equals(serie.tituloC);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + numTemporadas + episodios.hashCode() + tituloC.hashCode();
    }
}
