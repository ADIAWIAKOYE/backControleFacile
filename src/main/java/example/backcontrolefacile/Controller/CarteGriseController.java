package example.backcontrolefacile.Controller;

import com.google.zxing.WriterException;
import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Repositorys.CarteGriseRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.CarteGriseServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cartegrise")
public class CarteGriseController {

    @Autowired
    private CarteGriseServise carteGriseServise;
    @Autowired
    private CarteGriseRepository carteGriseRepository;

    @PostMapping("/save/{idvehiule}/{idapppuser}")
    public ResponseEntity<?> ajouterCarteGrise(@RequestBody CarteGrise carteGrise, @PathVariable Long idvehiule, @PathVariable Long idapppuser) throws IOException, WriterException {

        return carteGriseServise.ajouterCarteGrise(carteGrise, idvehiule, idapppuser);
    }

    @PutMapping("/update/{idcartegrise}")
    public MessageResponse UpdateCarteGrise(@PathVariable ("idcartegrise") Long idcartegrise, @RequestBody CarteGrise carteGrise) {

        return carteGriseServise.UpdateCarteGrise(idcartegrise, carteGrise);
    }

    @DeleteMapping("/supprimer/{idcartegrise}")
    public MessageResponse supprimerCarteGrise(@PathVariable ("idcartegrise") Long idcartegrise) {

        return carteGriseServise.supprimerCarteGrise(idcartegrise);
    }

    @GetMapping("/afficher")
    public List<CarteGrise> afficherCarteGrise() {

        return carteGriseServise.afficherCarteGrise();
    }

    @GetMapping("/affichernbr")
    public int afficherNbrCarteGrise() {

        return carteGriseServise.afficherCarteGrise().size();
    }

    @GetMapping("/afficherParId/{idcartegrise}")
    public CarteGrise CarteGriseParId(@PathVariable Long idcartegrise) {

        return carteGriseServise.CarteGriseParId(idcartegrise);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherParNumero/{numcartegrise}")
    public CarteGrise CarteGriseParNumero(@PathVariable String numcartegrise) {

        return carteGriseServise.CarteGriseparnumero(numcartegrise);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherParVehicule/{idvehicule}")
    public List<CarteGrise> CarteGriseParVehicule(@PathVariable Vehicule idvehicule) {

        return carteGriseServise.CarteGriseparvehicule(idvehicule);
    }



    @GetMapping("/vehiculeCart/{idvehicule}/{status}")
    public CarteGrise CarteGriseParId(@PathVariable("idvehicule") Vehicule idcartegrise,@PathVariable("status") String status) {

        return carteGriseRepository.findByVehiculeAndStatus(idcartegrise,status);
    }

    @GetMapping("/vehiculeCar/{idappuser}")
    public CarteGrise cartegriseParUser(@PathVariable Long idappuser){
        return carteGriseRepository.findByUtilisateur(idappuser);
    }


    @GetMapping("/vehiculeCarvehicule/{idvehicule}")
    public CarteGrise cartegriseParvehicule(@PathVariable Long idvehicule){
        return carteGriseRepository.findValidatedCarteGriseByVehiculeId(idvehicule);
    }
}
