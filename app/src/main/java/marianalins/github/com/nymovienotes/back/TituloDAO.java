package marianalins.github.com.nymovienotes.back;

import java.util.*;

public class TituloDAO {
    Map<Integer,Titulo> titulos;

    //--Singleton--------------------------------------------------------------
    private TituloDAO() {
        titulos = new HashMap<>();
    }   // só cria quando acessa a primeira vez
    private static volatile TituloDAO instance = null;
    public synchronized static TituloDAO getInstance() {
        if(instance == null) {
            instance = new TituloDAO();
        }
        return instance;
    }
    //--------------------------------------------------------------------------

    public void adicionar(Titulo titulo) {
        titulos.put(titulo.getCodigo() , titulo);

    }

    public void remover(Titulo titulo) {
        remover(titulo.getCodigo());
    }

    public void remover(int codigo) {
        titulos.remove(codigo);
    }

    public Titulo getTitulos(int codigo) throws NaoAchadoException {
        Titulo t = titulos.get(codigo);
        if(t == null) {
            throw new NaoAchadoException("back.Titulo com Codigo " + codigo + " Não Encontrado.");
        }
        return t;
    }

    public List<Titulo> getTitulos(String nome) throws NaoAchadoException {
        List<Titulo> retorno = new ArrayList<>();
        for(Titulo t : titulos.values()) {
            if(t.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(t);
            }
        }

        if(retorno.size() == 0) {
            throw new NaoAchadoException("back.Titulo Não Encontrado.");
        }

        return retorno;
    }


}
