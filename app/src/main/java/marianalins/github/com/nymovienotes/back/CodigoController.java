package marianalins.github.com.nymovienotes.back;

public class CodigoController {
    private CodigoDAO dao = new CodigoDAO();

    public int getProximaPessoaCod() {
        return dao.getProximoPessoaCod();
    }

    public int getProximoTituloCod() {
        return dao.getProximoTituloCod();
    }
}
