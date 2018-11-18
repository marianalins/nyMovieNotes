package marianalins.github.com.nymovienotes.backend;

public interface Iterador<T> {
    boolean hasNext();
    T next() throws IndexOutOfBoundsException;
    T peek() throws IndexOutOfBoundsException;
}