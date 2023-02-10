package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Models.AmendeRegle;
import example.backcontrolefacile.Models.Infraction;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Repositorys.AmendeRegleRepository;
import example.backcontrolefacile.Repositorys.InfractionRepository;
import example.backcontrolefacile.Services.InfractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/infraction")
public class InfractionController {


    @Autowired
    private InfractionService infractionService;
    @Autowired
    private InfractionRepository infractionRepository;

    @Autowired
    private AmendeRegleRepository amendeRegleRepository;

    @PostMapping("/saveinfraction/{idappuser}/{numpermis}/{montant}/{idvehicule}")
    public ResponseEntity<?> ajouterInfraction(@RequestBody Infraction infraction, @PathVariable Long idappuser, @PathVariable String numpermis, @PathVariable Long montant, @PathVariable Long idvehicule){

        return infractionService.ajouterInfraction(infraction, idappuser, numpermis, montant, idvehicule);
    }

    @PostMapping("/saveinfractionsanspermis/{idappuser}/{montant}/{idvehicule}")
    public ResponseEntity<?> ajouterInfractionsanspermis(@RequestParam(value = "lieu") String lieu, @RequestParam(value = "descriptions") String descriptions,
                                                         @PathVariable Long idappuser, @PathVariable Long montant, @PathVariable Long idvehicule) {
          Infraction infraction = new Infraction();
          infraction.setLieu(lieu);
          infraction.setDescription(descriptions);
        return infractionService.ajouterInfractionsanspermis(infraction, idappuser, montant, idvehicule);
    }

    @PostMapping("/saveinfractionavecpermis/{idappuser}/{numpermis}/{montant}/{idvehicule}")
    public ResponseEntity<?> ajouterInfractionavecpermis(@RequestParam(value = "lieu") String lieu, @RequestParam(value = "description") String description,
                                                         @RequestParam(value = "montantt") String montantt, @PathVariable Long idappuser, @PathVariable String numpermis,
                                                         @PathVariable Long montant, @PathVariable Long idvehicule) {
        Infraction infraction = new Infraction();
        Long montt = Long.valueOf(montantt);
        infraction.setLieu(lieu);
        infraction.setDescription(description);
        AmendeRegle amendeRegle = new AmendeRegle();
        amendeRegle.setMontant(montt);
        return infractionService.ajouterInfractionavecpermis(infraction, amendeRegle, idappuser, numpermis, montant, idvehicule);
    }


    @GetMapping("/infractionparutilisateure/{idutilisateur}")
    public List<Infraction> infractionparutilisateur (@PathVariable Long idutilisateur){

        return infractionRepository.findInfractionsByUser(idutilisateur);
    }

    @GetMapping("/infractionparutilisateur/{idutilisateur}")
    public List<Object> AfficherinfractionParIdUtilisateur(@PathVariable Long idutilisateur) {
        return infractionRepository.AfficherinfractionParIdUtilisateurrr(idutilisateur);
    }


    @GetMapping("/infractionparvehicule/{idvehicule}")
    public List<Infraction> infractionparvehicule(@PathVariable Vehicule idvehicule) {
        return infractionRepository.findByVehicule(idvehicule);
    }

    @GetMapping("/AfficherinfractionAmendeRegleParVehicule/{idvehicule}")
    public List<Object[]> AfficherinfractionAmendeRegleParVehicule(@PathVariable Long idvehicule) {

        return infractionRepository.afficherInfractionAmendeRegleParVehicule(idvehicule);
    }
}
