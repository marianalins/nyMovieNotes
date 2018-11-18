package marianalins.github.com.nymovienotes.backend;

import android.os.Environment;
import android.os.StatFs;

public class CodigoDAOFactory {

    public static CodigoDAO getInstance() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long espaco = (long) stat.getBlockSizeLong() * (long) stat.getBlockCountLong();

        if(espaco > 1000) {
            return new CodigoDAOArquivo();
        } else {
            return new CodigoDAOArquivo();
        }
    }
}
