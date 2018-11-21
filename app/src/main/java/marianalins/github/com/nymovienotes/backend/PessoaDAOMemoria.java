package marianalins.github.com.nymovienotes.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaDAOMemoria implements PessoaDAO {
    Map<Integer,Pessoa> pessoas;

    //--Singleton--------------------------------------------------------------
    private PessoaDAOMemoria() {
        pessoas = new HashMap<>();
    }
    private static volatile PessoaDAOMemoria instance = null;
    public synchronized static PessoaDAOMemoria getInstance() {
        if(instance == null) {
            instance = new PessoaDAOMemoria();
        }
        return instance;
    }
    //--------------------------------------------------------------------------

    // para a View receber todas (infelizmente)
    public List<Mostraveis> getLista() {
        List<Mostraveis> list = new ArrayList<>();
        list.addAll(pessoas.values());
        return list;
    }

    @Override
    public Iterador<Mostraveis> getMostraveis() {
        return new IteradorLista<Mostraveis>(pessoas.values());
    }

    public Iterador<Pessoa> getPessoa(String nome) throws NaoAchadoException {
        List<Pessoa> retorno = new ArrayList<>();
        for(Pessoa a: pessoas.values()) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(a);
            }
        }

        if(retorno.size() == 0) {
            throw new NaoAchadoException("Pessoa Não Encontrada.");
        }
        return new IteradorLista<>(retorno);
    }

    @Override
    public Iterador<Mostraveis> getMostraveis(String nome) throws NaoAchadoException {
        List<Mostraveis> retorno = new ArrayList<>();
        for(Mostraveis a: pessoas.values()) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(a);
            }
        }

        if(retorno.size() == 0) {
            throw new NaoAchadoException("Pessoa Não Encontrada.");
        }
        return new IteradorLista<>(retorno);
    }

    public Pessoa getPessoa(int codigo) throws NaoAchadoException {
        Pessoa p = pessoas.get(codigo);
        if(p == null) {
            throw new NaoAchadoException("Pessoa com Codigo " + codigo + " Não Encontrada.");
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
