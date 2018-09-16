package marianalins.github.com.nymovienotes.back;

import java.util.List;

public class PessoaController {
    PessoaDAO dao = PessoaDAO.getInstance();

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


    /*public List<back.Diretor> procurarPorDiretor(String nome) {
        List<back.Diretor> listaDiretores = new ArrayList<>();
        for(back.Diretor d: diretores) {
            if(d.getNome().toLowerCase().contains(nome)) {
                listaDiretores.add(d);
            }
        }
        return listaDiretores;
    }

    public void adicionarAtor(back.Ator ator) {
        atores.add(ator);
    }

    public void adicionarDiretor(back.Diretor diretor) {
        diretores.add(diretor);
    }

    public void removerAtor(back.Ator ator) {
        atores.remove(ator);
    }

    public void removerDiretor(back.Diretor diretor) {
        atores.remove(diretor);
    }
    */
}
