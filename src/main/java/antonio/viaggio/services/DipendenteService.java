package antonio.viaggio.services;

import antonio.viaggio.entities.Dipendente;
import antonio.viaggio.exceptions.DipendenteNotFoundException;
import antonio.viaggio.payloads.DipendenteDTO;
import antonio.viaggio.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<DipendenteDTO> getAllDipendenti() {
        return dipendenteRepository.findAll().stream()
                .map(d -> new DipendenteDTO(d.getDipendente_id(), d.getUsername(), d.getNome(), d.getCognome(), d.getEmail()))
                .collect(Collectors.toList());
    }

    public DipendenteDTO getDipendenteById(UUID id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non trovato"));
        return new DipendenteDTO(dipendente.getDipendente_id(), dipendente.getUsername(), dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail());
    }

    public DipendenteDTO createDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.username());
        dipendente.setNome(dipendenteDTO.nome());
        dipendente.setCognome(dipendenteDTO.cognome());
        dipendente.setEmail(dipendenteDTO.email());

        Dipendente nuovoDipendente = dipendenteRepository.save(dipendente);
        return new DipendenteDTO(nuovoDipendente.getDipendente_id(), nuovoDipendente.getUsername(), nuovoDipendente.getNome(), nuovoDipendente.getCognome(), nuovoDipendente.getEmail());
    }

    public DipendenteDTO updateDipendente(UUID id, DipendenteDTO dipendenteDTO) {
        Dipendente existingDipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non trovato"));

        existingDipendente.setUsername(dipendenteDTO.username());
        existingDipendente.setNome(dipendenteDTO.nome());
        existingDipendente.setCognome(dipendenteDTO.cognome());
        existingDipendente.setEmail(dipendenteDTO.email());

        Dipendente dipendenteAggiornato = dipendenteRepository.save(existingDipendente);
        return new DipendenteDTO(dipendenteAggiornato.getDipendente_id(), dipendenteAggiornato.getUsername(), dipendenteAggiornato.getNome(), dipendenteAggiornato.getCognome(), dipendenteAggiornato.getEmail());
    }

    public void deleteDipendente(UUID id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non trovato"));
        dipendenteRepository.delete(dipendente);
    }
}