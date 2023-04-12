package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Amende;
import example.backcontrolefacile.Models.AmendeRegle;
import example.backcontrolefacile.Models.Infraction;
import org.springframework.http.ResponseEntity;

public interface AmendeRegleService {

    ResponseEntity<?> ajouterAmenderegler(AmendeRegle amendeRegle, Long idinfraction, String numpermis);

    ResponseEntity<?> ajouterInfractionAmenregle(Infraction infraction, AmendeRegle amendeRegle, Long idappuser, String numpermis, Long montant, Long idvehicule);
}
