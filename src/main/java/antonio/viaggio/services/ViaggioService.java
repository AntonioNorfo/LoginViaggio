package antonio.viaggio.services;

import antonio.viaggio.entities.Viaggio;
import antonio.viaggio.enums.StatoViaggio;
import antonio.viaggio.exceptions.ViaggioNotFoundException;
import antonio.viaggio.payloads.ViaggioDTO;
import antonio.viaggio.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<ViaggioDTO> getAllViaggi() {
        return viaggioRepository.findAll().stream()
                .map(v -> new ViaggioDTO(v.getViaggio_id(), v.getDestinazione(), v.getData(), v.getStato()))
                .collect(Collectors.toList());
    }

    public ViaggioDTO getViaggioById(UUID id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ViaggioNotFoundException("Viaggio non trovato"));
        return new ViaggioDTO(viaggio.getViaggio_id(), viaggio.getDestinazione(), viaggio.getData(), viaggio.getStato());
    }

    public ViaggioDTO createViaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.destinazione());
        viaggio.setData(viaggioDTO.data());
        viaggio.setStato(viaggioDTO.stato());

        Viaggio nuovoViaggio = viaggioRepository.save(viaggio);
        return new ViaggioDTO(nuovoViaggio.getViaggio_id(), nuovoViaggio.getDestinazione(), nuovoViaggio.getData(), nuovoViaggio.getStato());
    }

    public ViaggioDTO updateStatoViaggio(UUID id, StatoViaggio stato) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ViaggioNotFoundException("Viaggio non trovato"));
        viaggio.setStato(stato);

        Viaggio viaggioAggiornato = viaggioRepository.save(viaggio);
        return new ViaggioDTO(viaggioAggiornato.getViaggio_id(), viaggioAggiornato.getDestinazione(), viaggioAggiornato.getData(), viaggioAggiornato.getStato());
    }

    public void deleteViaggio(UUID id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ViaggioNotFoundException("Viaggio non trovato"));
        viaggioRepository.delete(viaggio);
    }
}