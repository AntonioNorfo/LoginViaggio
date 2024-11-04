package antonio.viaggio.services;

import antonio.viaggio.entities.Dipendente;
import antonio.viaggio.entities.Prenotazione;
import antonio.viaggio.entities.Viaggio;
import antonio.viaggio.exceptions.DipendenteNotFoundException;
import antonio.viaggio.exceptions.PrenotazioneNotFoundException;
import antonio.viaggio.exceptions.ViaggioNotFoundException;
import antonio.viaggio.payloads.RichiestaPrenotazioneDTO;
import antonio.viaggio.repositories.DipendenteRepository;
import antonio.viaggio.repositories.PrenotazioneRepository;
import antonio.viaggio.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<RichiestaPrenotazioneDTO> getAllPrenotazioni() {
        return prenotazioneRepository.findAll().stream()
                .map(p -> new RichiestaPrenotazioneDTO(p.getViaggio().getViaggio_id(), p.getDipendente().getDipendente_id(), p.getDataRichiesta(), p.getNote()))
                .collect(Collectors.toList());
    }

    public RichiestaPrenotazioneDTO getPrenotazioneById(UUID id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new PrenotazioneNotFoundException("Prenotazione non trovata"));
        return new RichiestaPrenotazioneDTO(prenotazione.getViaggio().getViaggio_id(), prenotazione.getDipendente().getDipendente_id(), prenotazione.getDataRichiesta(), prenotazione.getNote());
    }

    public RichiestaPrenotazioneDTO createPrenotazione(RichiestaPrenotazioneDTO richiestaDTO) {
        Viaggio viaggio = viaggioRepository.findById(richiestaDTO.viaggioId())
                .orElseThrow(() -> new ViaggioNotFoundException("Viaggio non trovato"));
        Dipendente dipendente = dipendenteRepository.findById(richiestaDTO.dipendenteId())
                .orElseThrow(() -> new DipendenteNotFoundException("Dipendente non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, richiestaDTO.dataRichiesta())) {
            throw new IllegalStateException("Il dipendente ha già una prenotazione per questa data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);
        prenotazione.setDataRichiesta(richiestaDTO.dataRichiesta());
        prenotazione.setNote(richiestaDTO.note());

        Prenotazione nuovaPrenotazione = prenotazioneRepository.save(prenotazione);
        return new RichiestaPrenotazioneDTO(nuovaPrenotazione.getViaggio().getViaggio_id(), nuovaPrenotazione.getDipendente().getDipendente_id(), nuovaPrenotazione.getDataRichiesta(), nuovaPrenotazione.getNote());
    }

    public void deletePrenotazione(UUID id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new PrenotazioneNotFoundException("Prenotazione non trovata"));
        prenotazioneRepository.delete(prenotazione);
    }
}
