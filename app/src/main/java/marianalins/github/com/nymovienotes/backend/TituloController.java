package marianalins.github.com.nymovienotes.backend;

public class TituloController {

    private TituloDAO dao = TituloDAOFactory.getInstance();

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

    public Iterador<Titulo> getTitulos(String nome)  throws NaoAchadoException {
        return dao.getTitulos(nome);
    }

    public Iterador<Mostraveis> getMostraveis() {
        return dao.getMostraveis();
    }


}
