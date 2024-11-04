package antonio.viaggio.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID prenotazione_id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private antonio.viaggio.entities.Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    private LocalDate dataRichiesta;

    @Column(length = 500)
    private String note;

}
