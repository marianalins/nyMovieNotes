package marianalins.github.com.nymovienotes.backend;

import java.util.List;

public interface TituloDAO {
    List<Mostraveis> getLista();
    void lerArquivo();
    void adicionar(Titulo titulo);
    void remover(Titulo titulo);
    void remover(int codigo);
    Titulo getTitulos(int codigo) throws NaoAchadoException;
    List<Titulo> getTitulos(String nome) throws NaoAchadoException;
}
