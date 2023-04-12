package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.AppUser;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PolicierService {

    ResponseEntity<?> ajouterPolicier(Policier policier);

    ResponseEntity<?> UpdatePolicier(Long idappuser, Policier policier);

    ResponseEntity<?> UpdatePolicierProfile(Long idappuser, Policier policier);

    MessageResponse supprimerPolicier(Long idappuser);

    List<Policier> afficherPolicier();
}
