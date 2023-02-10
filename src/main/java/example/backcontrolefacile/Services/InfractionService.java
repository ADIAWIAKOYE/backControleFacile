package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Amende;
import example.backcontrolefacile.Models.AmendeRegle;
import example.backcontrolefacile.Models.Infraction;
import org.springframework.http.ResponseEntity;

public interface InfractionService {

    ResponseEntity<?> ajouterInfraction(Infraction infraction, Long idappuser, String numpermis, Long montant, Long idvehicule);

    ResponseEntity<?> ajouterInfractionsanspermis(Infraction infraction, Long idappuser, Long montant, Long idvehicule);


    ResponseEntity<?> ajouterInfractionavecpermis(Infraction infraction, AmendeRegle amendeRegle, Long idappuser, String numpermis, Long montant, Long idvehicule);

}
