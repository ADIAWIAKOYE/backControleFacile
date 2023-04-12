package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Models.AmendeRegle;
import example.backcontrolefacile.Models.Infraction;
import example.backcontrolefacile.Services.AmendeRegleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/amenderegle")
public class AmendeRegleController {

    @Autowired
    private AmendeRegleService amendeRegleService;

    @PostMapping("/ajouterAmenderegle/{idinfraction}/{numpermis}")
    public ResponseEntity<?> ajouterAmenderegler(@RequestBody AmendeRegle amendeRegle, @PathVariable Long idinfraction, @PathVariable String numpermis) {

        return amendeRegleService.ajouterAmenderegler(amendeRegle, idinfraction, numpermis);
    }

    @PostMapping("/ajouterInfractionAmenderegle/{idappuser}/{numpermis}/{montant}/{idvehicule}")
    public ResponseEntity<?> ajouterInfractionAmenregle(@RequestParam (value = "lieu") String lieu, @RequestParam(value = "description") String description, @RequestParam(value = "montantt") Long montantt,
                                                         @PathVariable Long idappuser, @PathVariable String numpermis, @PathVariable Long montant, @PathVariable Long idvehicule) {
          Infraction infraction = new Infraction();
          AmendeRegle amendeRegle = new AmendeRegle();
          infraction.setLieu(lieu);
          infraction.setDescription(description);
          amendeRegle.setMontant(montantt);
        return amendeRegleService.ajouterInfractionAmenregle(infraction, amendeRegle, idappuser, numpermis, montant, idvehicule);
    }
}
