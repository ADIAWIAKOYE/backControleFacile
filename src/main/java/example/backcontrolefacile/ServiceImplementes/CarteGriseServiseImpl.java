package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Permis;
import example.backcontrolefacile.Repositorys.CarteGriseRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.CarteGriseServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteGriseServiseImpl implements CarteGriseServise {


    @Autowired
    private CarteGriseRepository carteGriseRepository;


    @Override
    public ResponseEntity<?> ajouterCarteGrise(CarteGrise carteGrise) {
        if (carteGriseRepository.findByNumcartegrise(carteGrise.getNumcartegrise()) != null) {
            return  ResponseEntity.badRequest().body(new MessageResponse("Error: cette carte grise existe déjà !"));
        }
        carteGriseRepository.save(carteGrise);
        return ResponseEntity.ok(new MessageResponse("Carte grise enregistré avec succès !"));
    }

    @Override
    public CarteGrise ajouterCG(CarteGrise carteGrise) {

        return carteGriseRepository.save(carteGrise);
    }

    @Override
    public MessageResponse UpdateCarteGrise(Long idcartegrise, CarteGrise carteGrise) {
        if (carteGriseRepository.findByIdcartegrise(idcartegrise) != null){
            CarteGrise updatecartegrise = carteGriseRepository.findById(idcartegrise).get();

            updatecartegrise.setNumcartegrise(carteGrise.getNumcartegrise());

            updatecartegrise.setNom(carteGrise.getNom());
            updatecartegrise.setPrenom(carteGrise.getPrenom());
            updatecartegrise.setDatenaissance(carteGrise.getDatenaissance());
            updatecartegrise.setLieunaissance(carteGrise.getLieunaissance());
            updatecartegrise.setCommune(carteGrise.getCommune());
            updatecartegrise.setDomicile(carteGrise.getDomicile());
            updatecartegrise.setProfession(carteGrise.getProfession());

            updatecartegrise.setGenre(carteGrise.getGenre());
            updatecartegrise.setMarque(carteGrise.getMarque());
            updatecartegrise.setType(carteGrise.getType());
            updatecartegrise.setChassie(carteGrise.getChassie());
            updatecartegrise.setCapacite(carteGrise.getCapacite());
            updatecartegrise.setCarrouserie(carteGrise.getCarrouserie());
            updatecartegrise.setCoutunitaire(carteGrise.getCoutunitaire());
            updatecartegrise.setNbplace(carteGrise.getNbplace());
            updatecartegrise.setEnergie(carteGrise.getEnergie());
            updatecartegrise.setPuissancereel(carteGrise.getPuissancereel());
            updatecartegrise.setPuissanceadmin(carteGrise.getPuissanceadmin());
            updatecartegrise.setDpmc(carteGrise.getDpmc());
            updatecartegrise.setDateecheance(carteGrise.getDateecheance());
            updatecartegrise.setDatedelivrance(carteGrise.getDatedelivrance());
            updatecartegrise.setPtac(carteGrise.getPtac());
            updatecartegrise.setPv(carteGrise.getPv());

            carteGriseRepository.saveAndFlush(updatecartegrise);
            MessageResponse message = new MessageResponse("Carte grise Modifier avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("cette carte grise n'existe pas ");
            return message;
        }
    }

    @Override
    public MessageResponse supprimerCarteGrise(Long idcartegrise) {
        if (carteGriseRepository.findByIdcartegrise(idcartegrise) != null){
            carteGriseRepository.deleteById(idcartegrise);

            MessageResponse message = new MessageResponse("Carte grise Supprimer avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("cette carte grise n'existe pas ");
            return message;
        }
    }

    @Override
    public List<CarteGrise> afficherCarteGrise() {

        return carteGriseRepository.findAll();
    }

    @Override
    public CarteGrise CarteGriseParId(Long idcartegrise) {
        if (carteGriseRepository.findByIdcartegrise(idcartegrise) != null){

            return carteGriseRepository.findByIdcartegrise(idcartegrise);
        }

        return null;
    }

    @Override
    public CarteGrise CarteGriseparnumero(String numcartegrise) {
        if (carteGriseRepository.findByNumcartegrise(numcartegrise) != null){

            return carteGriseRepository.findByNumcartegrise(numcartegrise);
        }

        return null;
    }
}
