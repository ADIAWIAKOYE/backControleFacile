package example.backcontrolefacile.Services;

import com.google.zxing.WriterException;
import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface CarteGriseServise {

    ResponseEntity<?> ajouterCarteGrise(CarteGrise carteGrise, Long idvehiule, Long idapppuser) throws IOException, WriterException;

    CarteGrise ajouterCG(CarteGrise carteGrise);

    MessageResponse UpdateCarteGrise(Long idcartegrise, CarteGrise carteGrise);

    MessageResponse supprimerCarteGrise(Long idcartegrise);

    List<CarteGrise> afficherCarteGrise();

    CarteGrise CarteGriseParId(Long idcartegrise);

    CarteGrise CarteGriseparnumero(String numcartegrise);

    List<CarteGrise> CarteGriseparvehicule(Vehicule idvehicule);
      CarteGrise CarteGriseValideparvehicule(Long idvehicule, String status);

    MessageResponse addCarteGrise(CarteGrise carteGrise, Vehicule vehicule, Utilisateur utilisateur) throws IOException, WriterException;
}
