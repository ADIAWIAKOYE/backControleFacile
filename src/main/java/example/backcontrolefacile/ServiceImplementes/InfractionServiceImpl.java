package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.*;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.InfractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InfractionServiceImpl implements InfractionService {

    @Autowired
    private InfractionRepository infractionRepository;

    @Autowired
    private PolicierRepository policierRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private AmendeRepository amendeRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;


    @Autowired
    private AmendeRegleRepository amendeRegleRepository;


    @Override
    public ResponseEntity<?> ajouterInfraction(Infraction infraction, Long idappuser, String numpermis, Long montant, Long idvehicule) {
        if (policierRepository.findByIdappuser(idappuser) != null){
            Policier policier = policierRepository.findByIdappuser(idappuser);
            infraction.setPolicier(policier);

            if (vehiculeRepository.findByIdvehicule(idvehicule) != null){
                Vehicule vehicule = vehiculeRepository.findByIdvehicule(idvehicule);
                infraction.setVehicule(vehicule);

                if (amendeRepository.findByMontant(montant) != null){
                    Amende amende = amendeRepository.findByMontant(montant);
                    infraction.setAmende(amende);
                    if (permisRepository.findByNumpermis(numpermis) != null){
                        Permis permis = permisRepository.findByNumpermis(numpermis);
                        infraction.setPermis(permis);
                        infraction.setDate(new Date());
                        infraction.setStatus(false);

                        infractionRepository.save(infraction);

                        return ResponseEntity.ok(new MessageResponse("Infraction enregistre avec succes !", true));
                    }else {
                        return ResponseEntity.ok(new MessageResponse("cet Permis n'existe pas!", false));
                    }
                }else {
                    return ResponseEntity.ok(new MessageResponse("cet amende n'existe pas!", false));
                }
            }else {
                return ResponseEntity.ok(new MessageResponse("cet vehicule n'existe pas!", false));
            }
        }else {
            return ResponseEntity.ok(new MessageResponse("cet controlleur de vehicule n'existe pas  n'existe pas!", false));
        }

    }

    @Override
    public ResponseEntity<?> ajouterInfractionsanspermis(Infraction infraction, Long idappuser, Long montant, Long idvehicule) {
        if (policierRepository.findByIdappuser(idappuser) != null){
            Policier policier = policierRepository.findByIdappuser(idappuser);
            infraction.setPolicier(policier);

            if (vehiculeRepository.findByIdvehicule(idvehicule) != null){
                Vehicule vehicule = vehiculeRepository.findByIdvehicule(idvehicule);
                infraction.setVehicule(vehicule);

                if (amendeRepository.findByMontant(montant) != null){
                    Amende amende = amendeRepository.findByMontant(montant);
                    infraction.setAmende(amende);
                    List<CarteGrise> carteGrises = vehicule.getCarteGrise();

                    for (CarteGrise carteGrise : carteGrises) {
                         infraction.setPermis(carteGrise.getUtilisateur().getPermis());
                    }
                        infraction.setDate(new Date());
                        infraction.setStatus(false);

                        infractionRepository.save(infraction);

                        return ResponseEntity.ok(new MessageResponse("Infraction enregistre avec succes !", true));
                    } else {
                    return ResponseEntity.ok(new MessageResponse("cet amende n'existe pas!", false));
                }
            }else {
                return ResponseEntity.ok(new MessageResponse("cet vehicule n'existe pas!", false));
            }
        }else {
            return ResponseEntity.ok(new MessageResponse("cet controlleur de vehicule n'existe pas  n'existe pas!", false));
        }
    }

    @Override
    public ResponseEntity<?> ajouterInfractionavecpermis(Infraction infraction, AmendeRegle amendeRegle, Long idappuser, String numpermis, Long montant, Long idvehicule) {
        if (policierRepository.findByIdappuser(idappuser) != null) {
            Policier policier = policierRepository.findByIdappuser(idappuser);
            infraction.setPolicier(policier);

            if (vehiculeRepository.findByIdvehicule(idvehicule) != null) {
                Vehicule vehicule = vehiculeRepository.findByIdvehicule(idvehicule);
                infraction.setVehicule(vehicule);

                if (amendeRepository.findByMontant(montant) != null) {
                    Amende amende = amendeRepository.findByMontant(montant);
                    infraction.setAmende(amende);
                    if (permisRepository.findByNumpermis(numpermis) != null) {
                        Permis permis = permisRepository.findByNumpermis(numpermis);
                        infraction.setPermis(permis);
                        infraction.setDate(new Date());
                        infraction.setStatus(true);
                        amendeRegle.setDate(new Date());
                        amendeRegle.setPermis(permis);
                        amendeRegle.setInfraction(infractionRepository.save(infraction));
                        amendeRegleRepository.save(amendeRegle);

                        return ResponseEntity.ok(new MessageResponse("Infraction enregistre avec succes !", true));
                    } else {
                        return ResponseEntity.ok(new MessageResponse("cet Permis n'existe pas!", false));
                    }
                } else {
                    return ResponseEntity.ok(new MessageResponse("cet amende n'existe pas!", false));
                }
            } else {
                return ResponseEntity.ok(new MessageResponse("cet vehicule n'existe pas!", false));
            }
        } else {
            return ResponseEntity.ok(new MessageResponse("cet controlleur de vehicule n'existe pas  n'existe pas!", false));
        }
    }
}
