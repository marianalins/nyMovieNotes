package marianalins.github.com.nymovienotes.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaDAO {

    Map<Integer,Pessoa> pessoas;

    //--Singleton--------------------------------------------------------------
    private PessoaDAO() {
        pessoas = new HashMap<>();
    }
    private static volatile PessoaDAO instance = null;
    public synchronized static PessoaDAO getInstance() {
        if(instance == null) {
            instance = new PessoaDAO();
        }
        return instance;
    }
    //--------------------------------------------------------------------------

    public List<Pessoa> getPessoa(String nome) throws NaoAchadoException {
        List<Pessoa> retorno = new ArrayList<>();
        for(Pessoa a: pessoas.values()) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(a);
            }
        }

        if(retorno.size() == 0) {
            throw new NaoAchadoException("back.Pessoa Não Encontrada.");
        }

        return retorno;
    }

    public Pessoa getPessoa(int codigo) throws NaoAchadoException {
        Pessoa p = pessoas.get(codigo);
        if(p == null) {
            throw new NaoAchadoException("back.Pessoa com Codigo " + codigo + " Não Encontrada.");
        }
        return p;
    }

    public void adicionar(Pessoa pessoa) {
        pessoas.put(pessoa.getCodigo(),pessoa);
    }

    public void remover(Pessoa pessoa) {
        remover(pessoa.getCodigo());
    }

    public void remover(int codigo) {
        pessoas.remove(codigo);
    }

}
