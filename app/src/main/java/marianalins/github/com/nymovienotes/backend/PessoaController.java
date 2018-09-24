package marianalins.github.com.nymovienotes.backend;

import java.util.List;

public class PessoaController {
    PessoaDAO dao = PessoaDAOArquivo.getInstance();

    public List<Pessoa> getPessoa(String nome) throws NaoAchadoException {
        return dao.getPessoa(nome);
    }

    public Pessoa getPessoa(int codigo) throws NaoAchadoException {
        return dao.getPessoa(codigo);
    }

    public void adicionar(Pessoa pessoa) {
        dao.adicionar(pessoa);
    }

    public void remover(Pessoa pessoa) {
        dao.remover(pessoa);
    }

    public void remover(int codigo) {
        dao.remover(codigo);
    }

    public List<Mostraveis> getLista() {
        return dao.getLista();
    }

}
