package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService {

    ResponseEntity<?> ajouterUtilisateur(Utilisateur utilisateur);

    Utilisateur ajouter(Utilisateur utilisateur);

    ResponseEntity<?> UpdateUtilisateur(Long idappuser, Utilisateur utilisateur);

    ResponseEntity<?> ModifierUtilisateur(Long idappuser, Utilisateur utilisateur);

    ResponseEntity<?> UpdateUtilisateurProfile(Long idappuser, Utilisateur utilisateur);

    MessageResponse supprimerUtilisateur(Long idappuser);

    List<Utilisateur> afficherUtilisateur();

    MessageResponse setEtat(Utilisateur utilisateur, Long idappuser);

    List<CarteGrise> findByUtilisateur(Long id);
}
