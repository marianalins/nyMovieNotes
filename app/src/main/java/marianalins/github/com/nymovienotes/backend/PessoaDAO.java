package marianalins.github.com.nymovienotes.backend;

public interface PessoaDAO {
    Iterador<Mostraveis> getMostraveis();
    void adicionar(Pessoa pessoa);
    void remover(Pessoa pessoa);
    void remover(int codigo);
    Pessoa getPessoa(int codigo) throws NaoAchadoException;
    Iterador<Pessoa> getPessoa(String nome) throws NaoAchadoException;

}
