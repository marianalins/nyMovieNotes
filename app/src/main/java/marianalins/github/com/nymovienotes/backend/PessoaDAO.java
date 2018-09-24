package marianalins.github.com.nymovienotes.backend;

import java.util.List;

public interface PessoaDAO {
    List<Mostraveis> getLista();
    void lerArquivo();
    void adicionar(Pessoa pessoa);
    void remover(Pessoa pessoa);
    void remover(int codigo);
    Pessoa getPessoa(int codigo) throws NaoAchadoException;
    List<Pessoa> getPessoa(String nome) throws NaoAchadoException;

}
