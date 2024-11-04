package antonio.viaggio.exceptions;

import antonio.viaggio.payloads.MessaggioDiErroreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(DipendenteNotFoundException.class)
    public ResponseEntity<MessaggioDiErroreDTO> handleDipendenteNotFoundException(DipendenteNotFoundException ex) {
        MessaggioDiErroreDTO error = new MessaggioDiErroreDTO(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ViaggioNotFoundException.class)
    public ResponseEntity<MessaggioDiErroreDTO> handleViaggioNotFoundException(ViaggioNotFoundException ex) {
        MessaggioDiErroreDTO error = new MessaggioDiErroreDTO(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrenotazioneNotFoundException.class)
    public ResponseEntity<MessaggioDiErroreDTO> handlePrenotazioneNotFoundException(PrenotazioneNotFoundException ex) {
        MessaggioDiErroreDTO error = new MessaggioDiErroreDTO(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessaggioDiErroreDTO> handleGeneralException(Exception ex) {
        MessaggioDiErroreDTO error = new MessaggioDiErroreDTO("Errore interno al server", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
