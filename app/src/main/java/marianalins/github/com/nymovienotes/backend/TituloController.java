package marianalins.github.com.nymovienotes.backend;

import java.util.List;

public class TituloController {
    private static int ultimoCodigo;
    private TituloDAO dao = TituloDAO.getInstance();

    public void adicionar(Titulo titulo) {
        dao.adicionar(titulo);
    }

    public void remover(Titulo titulo) {
        dao.remover(titulo.getCodigo());
    }

    public void remover(int codigo) {
        dao.remover(codigo);
    }

    public Titulo getTitulos(int codigo) throws NaoAchadoException {
        return dao.getTitulos(codigo);
    }

    public List<Titulo> getTitulos(String nome)  throws NaoAchadoException {
        return dao.getTitulos(nome);
    }

    public List<Mostraveis> getLista() {
        return dao.getLista();
    }


}
