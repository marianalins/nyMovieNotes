package marianalins.github.com.nymovienotes.backend;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

public abstract class Titulo implements Serializable, Mostraveis {
    private String nome;
    private int ano;
    private String pais;
    private String idioma;
    private int duracao;
    private String genero;
    private final int codigo;
    private URL trailer;
    private int nota, imdbNota;
    private String sinopse;
    private File foto;
    private Set<Integer> diretores , atores;
    private transient PessoaController pessoaC = new PessoaController();

    // Construtor Menor caso possível.
    public Titulo(String nome) {
        this.nome = nome;
        this.codigo = new CodigoController().getProximoTituloCod();
        diretores = new TreeSet<>();
        atores = new TreeSet<>();
    }
    // Construtor Maior caso possível.


    public Titulo(String nome, int ano, String pais, String idioma, int duracao, String genero,
                  int codigo, URL trailer, int nota, int imdbNota, String sinopse,
                  Set<Integer> diretores, Set<Integer> atores , File foto) {
        this.nome = nome;
        this.ano = ano;
        this.pais = pais;
        this.idioma = idioma;
        this.duracao = duracao;
        this.genero = genero;
        this.codigo = codigo;
        this.trailer = trailer;
        this.nota = nota;
        this.imdbNota = imdbNota;
        this.sinopse = sinopse;
        this.diretores = diretores;
        this.atores = atores;
        this.foto = foto;
    }

    // Construtor de copia.
    public Titulo(Titulo copiado) {
        this.nome = copiado.nome;
        this.ano = copiado.ano;
        this.pais = copiado.pais;
        this.idioma = copiado.idioma;
        this.duracao = copiado.duracao;
        this.genero = copiado.genero;
        this.codigo = copiado.codigo;
        this.trailer = copiado.trailer;
        this.nota = nota;
        this.imdbNota = imdbNota;
        this.sinopse = copiado.sinopse;
    }
    //----------------GETS E SETS------------------------------------------
    public String getNome() {
        return nome;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public URL getTrailer() {
        return trailer;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setTrailer(URL trailer) {
        this.trailer = trailer;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getImdbNota() {
        return imdbNota;
    }

    public void setImdbNota(int imdbNota) {
        this.imdbNota = imdbNota;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    //-------------DIRETOR-----------------------------------------------------------
    public void adicionarDiretor(int codigo) {
        diretores.add(codigo);
    }

    public void adicionarDiretor(Diretor diretor) {
        adicionarDiretor(diretor.getCodigo());
    }

    public void removerDiretor(int codigo) {
        diretores.remove(codigo);
        pessoaC.remover(codigo);
    }

    public void removerDiretor(Diretor diretor) {
        removerDiretor(diretor.getCodigo());
    }

    public List<Diretor> getDiretores() {
        List<Diretor> d = new ArrayList<>();
        for (Integer i : diretores) {
            try {
                Pessoa p = pessoaC.getPessoa(i);
                if(p instanceof Diretor) {
                    Diretor diretor = (Diretor) p;
                    d.add(diretor);
                }
            } catch (NaoAchadoException e) {}
        }
        return d;
    }
    // ---------------ATOR-------------------------------------------------
    public List<Ator> getAtores() {
        List<Ator> a = new ArrayList<>();

        for(Integer i : atores) {
            try {
                 Pessoa p = pessoaC.getPessoa(i);
                if(p instanceof Ator) {
                    Ator ator = (Ator) p;
                    a.add(ator);
                }
            } catch (NaoAchadoException e){}
        }
        return a;
    }

    public void adicionarAtor(int codigo) {
        atores.add(codigo);
    }

    public void adicionarAtor(Ator ator) {
        adicionarAtor(ator.getCodigo());
        pessoaC.adicionar(ator);
    }

    public void removerAtor(int codigo) {
        atores.remove(codigo);
        pessoaC.remover(codigo);
    }

    public void removerAtor(Ator ator) {
        removerAtor(ator.getCodigo());
    }


    //---------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o instanceof Titulo) {
            Titulo titulo = (Titulo) o;
            return ano == titulo.ano &&
                    duracao == titulo.duracao &&
                    codigo == titulo.codigo &&
                    nota == titulo.nota &&
                    imdbNota == titulo.imdbNota &&
                    nome.equals(titulo.getNome()) &&
                    pais.equals(titulo.getPais()) &&
                    idioma.equals(titulo.getIdioma()) &&
                    genero.equals(titulo.getGenero()) &&
                    trailer.equals(titulo.getTrailer()) &&
                    sinopse.equals(titulo.getSinopse()) &&
                    diretores.equals(titulo.getDiretores()) &&
                    atores.equals(titulo.getAtores());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nome.hashCode() + ano + pais.hashCode() + idioma.hashCode() + duracao +
                genero.hashCode()+ codigo + trailer.hashCode() + nota + imdbNota + sinopse.hashCode() +
                diretores.hashCode() + atores.hashCode();
    }


    @Override
    public String toString() {
        return "Titulo{" +
                "nome='" + nome + '\'' +
                ", ano=" + ano +
                ", pais='" + pais + '\'' +
                ", idioma='" + idioma + '\'' +
                ", duracao=" + duracao +
                ", genero='" + genero + '\'' +
                ", codigo=" + codigo +
                ", trailer=" + trailer +
                ", nota=" + nota +
                ", imdbNota=" + imdbNota +
                ", sinopse='" + sinopse + '\'' +
                ", foto=" + foto +
                ", diretores=" + diretores +
                ", atores=" + atores +
                '}';
    }
}
