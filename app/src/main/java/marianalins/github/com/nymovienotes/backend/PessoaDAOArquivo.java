package marianalins.github.com.nymovienotes.backend;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaDAOArquivo implements PessoaDAO {
    private final String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/mymovienotes";
    private final String nomeArq = path + "/pessoaMap.dat";
    Map<Integer,Pessoa> pessoas;

    //--Singleton--------------------------------------------------------------
    private PessoaDAOArquivo() {
        pessoas = new HashMap<>();
        criaDiretorio();
        lerArquivo();
    }
    private static volatile PessoaDAOArquivo instance = null;
    public synchronized static PessoaDAOArquivo getInstance() {
        if(instance == null) {
            instance = new PessoaDAOArquivo();
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

    public void deleteFile() {
        new File(nomeArq).delete();
    }

    public void gravarOObjeto() { // gravar no arquivo
        File arq = new File(nomeArq);
        if(arq.exists()) {
            arq.delete();
        }

        try {
            if(!arq.createNewFile()) {
                System.out.println(" Deu ruim");
            }
        } catch (IOException e) {
            //Não conseguiucriar o arquivo
        }

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arq))) {
            for(Pessoa p : pessoas.values()) {
                output.writeObject(p);
            }
            output.flush();
        } catch (IOException e) {
           //Erro ao manipular o arquivo
        }
    }

    @Override
    public Iterador<Mostraveis> getMostraveis() {
        return new IteradorLista<Mostraveis>(pessoas.values());
    }

    @SuppressWarnings("unchecked")
    public void lerArquivo() {
        File arq = new File(nomeArq);

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(arq))) {
            Pessoa p;
            while((p = (Pessoa) input.readObject()) != null) {
                pessoas.put(p.getCodigo(), p);
            }

        } catch (ClassNotFoundException classNotFoundException) {
            //Classe não encontrada
            pessoas = new HashMap<>();
        } catch (IOException e) {
            //Erro ao tentar ler o arquivo
        }
    }

    @Override
    public Iterador<Pessoa> getPessoa(String nome) throws NaoAchadoException {
        List<Pessoa> retorno = new ArrayList<>();
        for(Pessoa a: pessoas.values()) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                retorno.add(a);
            }
        }

        if(retorno.size() == 0) {
            throw new NaoAchadoException("back.Pessoa Não Encontrada.");
        }
        return new IteradorLista<>(retorno);
    }

    @Override
    public Pessoa getPessoa(int codigo) throws NaoAchadoException {
        Pessoa p = pessoas.get(codigo);
        if(p == null) {
            throw new NaoAchadoException("back.Pessoa com Codigo " + codigo + " Não Encontrada.");
        }
        return p;
    }

    @Override
    public void adicionar(Pessoa pessoa) {
        pessoas.put(pessoa.getCodigo(),pessoa);
        gravarOObjeto();
    }

    @Override
    public void remover(Pessoa pessoa) {
        remover(pessoa.getCodigo());
    }

    @Override
    public void remover(int codigo) {
        pessoas.remove(codigo);
        gravarOObjeto();
    }
}
