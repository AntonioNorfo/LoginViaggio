package antonio.viaggio.payloads;

import antonio.viaggio.enums.StatoViaggio;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ViaggioDTO(
        UUID viaggioId,

        @NotBlank(message = "La destinazione è obbligatoria")
        String destinazione,

        @NotNull(message = "La data del viaggio è obbligatoria")
        @FutureOrPresent(message = "La data del viaggio deve essere nel presente o nel futuro")
        LocalDate data,

        @NotNull(message = "Lo stato del viaggio è obbligatorio")
        StatoViaggio stato
) {
}
