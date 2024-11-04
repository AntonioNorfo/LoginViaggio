package antonio.viaggio.exceptions;

public class PrenotazioneNotFoundException extends RuntimeException {
    public PrenotazioneNotFoundException(String message) {
        super(message);
    }
}
