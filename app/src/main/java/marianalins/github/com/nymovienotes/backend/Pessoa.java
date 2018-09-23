package marianalins.github.com.nymovienotes.backend;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public abstract class Pessoa implements Serializable, Mostraveis {
    private String nome;
    private Date dataNascimento;
    private String pais;
    private int nota;
    private final int codigo;
    private File foto;
    private Set<Integer> titulos;
    private transient TituloController tituloC = new TituloController();

    //Construtor Menor caso possível
    public Pessoa(String nome) {
        this.nome = nome;
        this.codigo = new CodigoController().getProximaPessoaCod();
    }
    //Construtor maior caso possível

    public Pessoa(String nome, Date dataNascimento, String pais, int codigo,
                  Set<Integer> titulos , File foto , int nota) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.pais = pais;
        this.codigo = codigo;
        this.titulos = titulos;
        this.foto = foto;
        this.nota = nota;
    }

    //--------------GETS E SETS---------------------------------
    public String getNome() {
        return nome;
    }

    public int getNota() {
        return nota;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCodigo() {
        return codigo;
    }

    //-----------------TÍTULO----------------------------------------
    public List<Titulo> getTitulos() {
        List<Titulo> t = new ArrayList<>();
        for (Integer i :titulos) {
            try {
                 t.add(tituloC.getTitulos(i));
            } catch (NaoAchadoException e) {}
        }
        return t;
    }

    public void adicionarTitulo(int codigo) {
        titulos.add(codigo);
    }

    public void adicionarTitulo(Titulo titulo) {
        titulos.add(titulo.getCodigo());
    }

    public void removerTitulo(int codigo) {
        titulos.remove(codigo);
    }

    public void removerTitulo(Titulo titulo) {
        titulos.remove(titulo.getCodigo());
    }
    //--------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if(o instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) o;
            return codigo == pessoa.getCodigo() &&
                    nome.equals(pessoa.getNome()) &&
                    dataNascimento.equals(pessoa.getDataNascimento()) &&
                    pais.equals(pessoa.getPais()) &&
                    titulos.equals(pessoa.titulos);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nome.hashCode() + dataNascimento.hashCode() +  pais.hashCode() + codigo +
                titulos.hashCode() ;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", pais='" + pais + '\'' +
                ", nota=" + nota +
                ", codigo=" + codigo +
                ", foto=" + foto +
                ", titulos=" + titulos +
                '}';
    }
}
