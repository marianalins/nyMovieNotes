package marianalins.github.com.nymovienotes.back;

import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CodigoDAO {
    private final int codPessoa = 1000000000;
    private final int codTitulo = 100000000;
    // Environment.getExternalStorageDirectory().getAbsolutePath()
    private final String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/mymovienotes";
    private final String pessoaArq = path + "/pessoa.txt";
    private final String tituloArq = path + "/titulo.txt";


    private void criaDiretorio() {
        File dir = new File(path);
        if(!dir.exists()) {
            if(!dir.mkdirs()) {
                Log.d("Arquivo", "Nao conseguiu criar dir");
            }
        }
    }

    private int carregarArquivo(String nomeArq) {
        int retorno;
        criaDiretorio();
        try (BufferedReader r = new BufferedReader(new FileReader(new File(nomeArq)))){
            retorno = r.read();

        } catch (IOException e) {
            return 0;
        }
        return retorno;
    }

    private void salvarArquivo(String nomeArq , int cod) {
        criaDiretorio();
        File arq = new File(nomeArq);
        if (!arq.exists()) {
            try{
                arq.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
         }

        try(BufferedWriter br = new BufferedWriter(new FileWriter(arq))) {
            br.write(cod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getProximoPessoaCod() {
        int codigo = carregarArquivo(pessoaArq);
        if(codigo == 0) {
            codigo = codPessoa;
        }
        salvarArquivo(pessoaArq , codigo+1);
        return codigo;
    }

    public int getProximoTituloCod() {
        int codigo = carregarArquivo(tituloArq);
        if (codigo == 0) {
            codigo = codTitulo;
        }
        salvarArquivo(tituloArq, codigo + 1);
        return codigo;
    }
}
