package antonio.viaggio.payloads;

import java.time.LocalDateTime;

public record MessaggioDiErroreDTO(
        String messaggio,
        LocalDateTime timestamp
) {
}
