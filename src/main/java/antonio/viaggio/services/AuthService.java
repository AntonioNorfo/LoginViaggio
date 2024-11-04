package antonio.viaggio.services;

import antonio.viaggio.entities.Dipendente;
import antonio.viaggio.exceptions.UnauthorizedException;
import antonio.viaggio.payloads.UserLoginDTO;
import antonio.viaggio.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public void checkCredentials(UserLoginDTO body) {
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findByEmail(body.email());

        if (dipendenteOptional.isEmpty()) {
            throw new UnauthorizedException("Email o password non corretta");
        }

        Dipendente dipendente = dipendenteOptional.get();
        if (!dipendente.getPassword().equals(body.password())) {
            throw new UnauthorizedException("Email o password non corretta");
        }
    }
}
