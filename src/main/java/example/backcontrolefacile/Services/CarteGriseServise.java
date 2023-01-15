package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarteGriseServise {

    ResponseEntity<?> ajouterCarteGrise(CarteGrise carteGrise);

    CarteGrise ajouterCG(CarteGrise carteGrise);

    MessageResponse UpdateCarteGrise(Long idcartegrise, CarteGrise carteGrise);

    MessageResponse supprimerCarteGrise(Long idcartegrise);

    List<CarteGrise> afficherCarteGrise();

    CarteGrise CarteGriseParId(Long idcartegrise);

    CarteGrise CarteGriseparnumero(String numcartegrise);
}
