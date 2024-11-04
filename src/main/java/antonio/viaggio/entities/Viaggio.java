package antonio.viaggio.entities;

import antonio.viaggio.enums.StatoViaggio;
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
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue
    private UUID viaggio_id;

    private String destinazione;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;


}
