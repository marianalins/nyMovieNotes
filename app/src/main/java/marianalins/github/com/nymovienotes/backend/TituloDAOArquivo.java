package marianalins.github.com.nymovienotes.backend;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class TituloDAOArquivo implements TituloDAO {
    private final String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/mymovienotes";
    private final String nomeArq = path + "/tituloMap.dat";
    Map<Integer,Titulo> titulos;

    //--Singleton--------------------------------------------------------------
    private TituloDAOArquivo() {
        titulos = new HashMap<>();
        criaDiretorio();
        lerArquivo();
    }   // só cria quando acessa a primeira vez
    private static volatile TituloDAOArquivo instance = null;
    public synchronized static TituloDAOArquivo getInstance() {
        if(instance == null) {
            instance = new TituloDAOArquivo();
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

            for(Titulo t : titulos.values()) {
                output.writeObject(t);
            }
            output.flush();
        } catch (IOException e) {
            //Erro ao manipular o arquivo
        }
    }

    @Override
    public Iterador<Mostraveis> getMostraveis() {
        return new IteradorLista<Mostraveis>(titulos.values());
    }

    @SuppressWarnings("unchecked")
    public void lerArquivo() {
        File arq = new File(nomeArq);

        try(ObjectInputStream input = new ObjectInputStream((new FileInputStream(arq)))) {
            Titulo t = null;
            while((t = (Titulo) input.readObject()) != null) {
                titulos.put(t.getCodigo(), t);
            }
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
        gravarOObjeto();
    }


    public Titulo getTitulos(int codigo) throws NaoAchadoException {
        Titulo t = titulos.get(codigo);
        if(t == null) {
            throw new NaoAchadoException("back.Titulo com Codigo " + codigo + " Não Encontrado.");
        }
        return t;
    }

    @Override
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
