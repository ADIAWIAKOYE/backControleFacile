package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface VehiculeService {

    ResponseEntity<?> ajouterVehicule(Vehicule vehicule);


  //  MessageResponse UpdateVehicule(Long idvehicule, Vehicule vehicule);

    MessageResponse UpdateVehiculeImage(Long idvehicule, Vehicule vehicule);

    MessageResponse UpdateVehicule(Long idvehicule, Vehicule vehicule);

    MessageResponse supprimerVehicule(Long idvehicule);

    List<Vehicule> afficherVehicule();

    Vehicule vehiculeParId(Long idvehicule);

    Vehicule vehiculeparplaque(String plaque);


}
