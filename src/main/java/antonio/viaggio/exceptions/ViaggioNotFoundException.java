package antonio.viaggio.exceptions;

public class ViaggioNotFoundException extends RuntimeException {
    public ViaggioNotFoundException(String message) {
        super(message);
    }
}
