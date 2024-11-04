package antonio.viaggio.controllers;

import antonio.viaggio.enums.StatoViaggio;
import antonio.viaggio.payloads.ViaggioDTO;
import antonio.viaggio.services.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public ResponseEntity<List<ViaggioDTO>> getAllViaggi() {
        List<ViaggioDTO> viaggiDTO = viaggioService.getAllViaggi();
        return ResponseEntity.ok(viaggiDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViaggioDTO> getViaggioById(@PathVariable UUID id) {
        ViaggioDTO viaggioDTO = viaggioService.getViaggioById(id);
        return ResponseEntity.ok(viaggioDTO);
    }

    @PostMapping
    public ResponseEntity<ViaggioDTO> createViaggio(@Valid @RequestBody ViaggioDTO viaggioDTO) {
        ViaggioDTO nuovoViaggioDTO = viaggioService.createViaggio(viaggioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoViaggioDTO);
    }

    @PutMapping("/{id}/stato")
    public ResponseEntity<ViaggioDTO> updateStatoViaggio(@PathVariable UUID id, @RequestParam StatoViaggio stato) {
        ViaggioDTO viaggioAggiornatoDTO = viaggioService.updateStatoViaggio(id, stato);
        return ResponseEntity.ok(viaggioAggiornatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable UUID id) {
        viaggioService.deleteViaggio(id);
        return ResponseEntity.noContent().build();
    }
}
