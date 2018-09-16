package marianalins.github.com.nymovienotes.back;

import java.util.Date;
import java.util.List;

public class Ator extends Pessoa {
    private List<Titulo> titulos;

    public Ator(String nome) {
        super(nome);
    }

    public Ator(String nome, Date dataNascimento, String pais, int codigo) {
        super(nome, dataNascimento, pais, codigo);
    }

    public Titulo getTitulo(String nome) throws NaoAchadoException {
        for(Titulo e : titulos) {
            if(e.getNome().toLowerCase().contains(nome)) {
                return e;
            }
        }
        throw  new NaoAchadoException("Título não encontrado");
    }
    //---------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null){
            return false;
        } else if (o instanceof Ator){
            Ator ator = (Ator) o;
            return super.equals(o) && titulos.equals(ator.titulos);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + titulos.hashCode();
    }
}
