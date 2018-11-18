package marianalins.github.com.nymovienotes.backend;

import android.os.Environment;
import android.os.StatFs;

public class PessoaDAOFactory {

    public static PessoaDAO getInstance() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long espaco = (long) stat.getBlockSizeLong() * (long) stat.getBlockCountLong(); // Espaço livre

        if(espaco > 1000) {
            return PessoaDAOArquivo.getInstance();
        } else {
            return PessoaDAOMemoria.getInstance();
        }
    }
}


