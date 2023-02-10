package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Amende;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AmendeService {

    ResponseEntity<?> ajouterAmende(Amende amende);

    List<Amende> afficherAmende();

    MessageResponse UpdateAmende(Long idamende, Amende amende);

    MessageResponse supprimerAmende(Long idamende);
}
