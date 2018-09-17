package marianalins.github.com.nymovienotes.back;

import android.os.Environment;
import android.util.Log;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class TituloDAO {
    private final String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/mymovienotes";
    private final String nomeArq = path + "/tituloMap.dat";
    Map<Integer,Titulo> titulos;

    //--Singleton--------------------------------------------------------------
    private TituloDAO() {
        criaDiretorio();
        lerArquivo();
    }   // só cria quando acessa a primeira vez
    private static volatile TituloDAO instance = null;
    public synchronized static TituloDAO getInstance() {
        if(instance == null) {
            instance = new TituloDAO();
        }
        return instance;
    }
    //--------------------------------------------------------------------------

    private void criaDiretorio() {
        File dir = new File(path);
        if(!dir.exists()) {
            if(!dir.mkdirs()) {
                Log.d("Arquivo", "Nao conseguiu criar dir");
            }
        }
    }

    public void gravarOObjeto() { // gravar no arquivo
        File arq = new File(nomeArq);
        if(arq.exists()) {
            arq.delete();
        }


        try {
            arq.createNewFile();
        } catch (IOException e) {
            //Não conseguiucriar o arquivo
        }

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arq))) {

            while(titulos != null) {
                output.writeObject(titulos);
                output.flush();
            }
        } catch (IOException e) {
            //Erro ao manipular o arquivo
        }
    }
    @SuppressWarnings("unchecked")
    public void lerArquivo() {

        File arq = new File(nomeArq);
        if(!arq.exists()) {
            titulos = new HashMap<>();
        }

        try(ObjectInputStream input = new ObjectInputStream((new FileInputStream(arq)))) {
            titulos = (HashMap<Integer, Titulo>) input.readObject();
        } catch (ClassNotFoundException classNotFoundException) {
            //Classe não encontrada
        } catch (IOException e) {
            //Erro ao tentar ler o arquivo
        }
    }

    public void adicionar(Titulo titulo) {
        titulos.put(titulo.getCodigo() , titulo);
        gravarOObjeto();
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
