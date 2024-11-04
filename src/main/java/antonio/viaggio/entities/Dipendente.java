package antonio.viaggio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dipendenti")
public class Dipendente {

    @Id
    @GeneratedValue
    private UUID dipendente_id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;

}
