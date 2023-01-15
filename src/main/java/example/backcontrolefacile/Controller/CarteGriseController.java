package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.CarteGriseServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cartegrise")
public class CarteGriseController {

    @Autowired
    private CarteGriseServise carteGriseServise;

    @PostMapping("/save")
    public ResponseEntity<?> ajouterCarteGrise(CarteGrise carteGrise) {

        return carteGriseServise.ajouterCarteGrise(carteGrise);
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

    @GetMapping("/afficherParNumero/{numcartegrise}")
    public CarteGrise CarteGriseParNumero(@PathVariable String numcartegrise) {

        return carteGriseServise.CarteGriseparnumero(numcartegrise);
    }
}
