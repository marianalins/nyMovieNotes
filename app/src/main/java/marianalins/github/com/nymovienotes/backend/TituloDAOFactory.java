package marianalins.github.com.nymovienotes.backend;

import android.os.Environment;
import android.os.StatFs;

public class TituloDAOFactory {

    public static TituloDAO getInstance() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long espaco = (long) stat.getBlockSizeLong() * (long) stat.getBlockCountLong();

        if(espaco > 1000) {
            return TituloDAOArquivo.getInstance();
        } else {
            return TituloDAOMemoria.getInstance();
        }
    }
}
