package marianalins.github.com.nymovienotes.backend;

public interface TituloDAO {
    Iterador<Mostraveis> getMostraveis();
    void adicionar(Titulo titulo);
    void remover(Titulo titulo);
    void remover(int codigo);
    Titulo getTitulos(int codigo) throws NaoAchadoException;
    Iterador<Titulo> getTitulos(String nome) throws NaoAchadoException;
}
