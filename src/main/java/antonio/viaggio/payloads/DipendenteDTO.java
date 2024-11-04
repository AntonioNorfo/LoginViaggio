package antonio.viaggio.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DipendenteDTO(
        UUID dipendenteId,

        @NotBlank(message = "L'username è obbligatorio.")
        @Size(min = 3, max = 50, message = "L'username deve essere compreso tra 3 e 50 caratteri , a meno che non ti chiamo come un LoremIpsum")
        String username,

        @NotBlank(message = "Il nome è obbligatorio")
        @Size(max = 50, message = "Il nome può avere al massimo 50 caratteri")
        String nome,

        @NotBlank(message = "Il cognome è obbligatorio")
        @Size(max = 50, message = "Il cognome può avere al massimo 50 caratteri")
        String cognome,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "L'email deve essere valida")
        String email
) {
}