package antonio.viaggio.controllers;

import antonio.viaggio.payloads.RichiestaPrenotazioneDTO;
import antonio.viaggio.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<List<RichiestaPrenotazioneDTO>> getAllPrenotazioni() {
        List<RichiestaPrenotazioneDTO> prenotazioniDTO = prenotazioneService.getAllPrenotazioni();
        return ResponseEntity.ok(prenotazioniDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RichiestaPrenotazioneDTO> getPrenotazioneById(@PathVariable UUID id) {
        RichiestaPrenotazioneDTO prenotazioneDTO = prenotazioneService.getPrenotazioneById(id);
        return ResponseEntity.ok(prenotazioneDTO);
    }

    @PostMapping
    public ResponseEntity<RichiestaPrenotazioneDTO> createPrenotazione(@Valid @RequestBody RichiestaPrenotazioneDTO richiestaDTO) {
        RichiestaPrenotazioneDTO nuovaPrenotazioneDTO = prenotazioneService.createPrenotazione(richiestaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovaPrenotazioneDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable UUID id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }
}
