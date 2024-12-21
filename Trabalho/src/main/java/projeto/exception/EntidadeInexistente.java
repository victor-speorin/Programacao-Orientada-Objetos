package projeto.exception;

public class EntidadeInexistente extends RuntimeException {
    public EntidadeInexistente(String message) {
        super(message);
    }
}
