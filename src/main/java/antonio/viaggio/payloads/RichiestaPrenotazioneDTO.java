package antonio.viaggio.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record RichiestaPrenotazioneDTO(
        @NotNull(message = "L'ID del viaggio è obbligatorio , capito?")
        UUID viaggioId,

        @NotNull(message = "L'ID del dipendente è obbligatorio , capito x2?")
        UUID dipendenteId,

        @NotNull(message = "La data della richiesta è obbligatoria , cpaito x3?xd")
        @FutureOrPresent(message = "La data della richiesta deve essere nel presente o nel futuro , di certo non pui andare nel passato , oppure si?")
        LocalDate dataRichiesta,

        @Size(max = 500, message = "Le note possono avere al massimo 500 caratteri , niente poemi.")
        String note
) {
}
