package antonio.viaggio.controllers;

import antonio.viaggio.payloads.DipendenteDTO;
import antonio.viaggio.services.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public ResponseEntity<List<DipendenteDTO>> getAllDipendenti() {
        List<DipendenteDTO> dipendentiDTO = dipendenteService.getAllDipendenti();
        return ResponseEntity.ok(dipendentiDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DipendenteDTO> getDipendenteById(@PathVariable UUID id) {
        DipendenteDTO dipendenteDTO = dipendenteService.getDipendenteById(id);
        return ResponseEntity.ok(dipendenteDTO);
    }

    @PostMapping
    public ResponseEntity<DipendenteDTO> createDipendente(@Valid @RequestBody DipendenteDTO dipendenteDTO) {
        DipendenteDTO nuovoDipendenteDTO = dipendenteService.createDipendente(dipendenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoDipendenteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DipendenteDTO> updateDipendente(@PathVariable UUID id, @Valid @RequestBody DipendenteDTO dipendenteDTO) {
        DipendenteDTO dipendenteAggiornatoDTO = dipendenteService.updateDipendente(id, dipendenteDTO);
        return ResponseEntity.ok(dipendenteAggiornatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable UUID id) {
        dipendenteService.deleteDipendente(id);
        return ResponseEntity.noContent().build();
    }
}
