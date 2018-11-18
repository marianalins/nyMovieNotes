package marianalins.github.com.nymovienotes.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TituloDAOMemoria implements TituloDAO {
    Map<Integer,Titulo> titulos;

    //--Singleton--------------------------------------------------------------
    private TituloDAOMemoria() {
        titulos = new HashMap<>();
    }   // só cria quando acessa a primeira vez
    private static volatile TituloDAOMemoria instance = null;
    public synchronized static TituloDAOMemoria getInstance() {
        if(instance == null) {
            instance = new TituloDAOMemoria();
        }
        return instance;
    }
    //--------------------------------------------------------------------------

    @Override
    public Iterador<Mostraveis> getMostraveis() {
        return new IteradorLista<Mostraveis>(titulos.values());
    }

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


    public Iterador<Titulo> getTitulos(String nome) throws NaoAchadoException {
        List<Titulo> retorno = new ArrayList<>();
        for(Titulo t : titulos.values()) {
            if(t.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(t);
            }
        }

        if(retorno.size() == 0) {
            throw new NaoAchadoException("back.Titulo Não Encontrado.");
        }

        return new IteradorLista<>(retorno);
    }




}
