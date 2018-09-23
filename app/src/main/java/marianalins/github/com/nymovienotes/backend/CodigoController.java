package marianalins.github.com.nymovienotes.backend;

public class CodigoController {
    private CodigoDAO dao = new CodigoDAOArquivo();

    public int getProximaPessoaCod() {
        return dao.getProximoPessoaCod();
    }

    public int getProximoTituloCod() {
        return dao.getProximoTituloCod();
    }
}
