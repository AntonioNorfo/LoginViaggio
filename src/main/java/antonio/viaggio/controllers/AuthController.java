package antonio.viaggio.controllers;

import antonio.viaggio.exceptions.BadRequestException;
import antonio.viaggio.payloads.DipendenteDTO;
import antonio.viaggio.payloads.UserLoginDTO;
import antonio.viaggio.payloads.UserLoginResponseDTO;
import antonio.viaggio.services.AuthService;
import antonio.viaggio.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        authService.checkCredentials(body);
        return new UserLoginResponseDTO("Login effettuato con successo");
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteDTO save(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return dipendenteService.createDipendente(body);
    }
}
