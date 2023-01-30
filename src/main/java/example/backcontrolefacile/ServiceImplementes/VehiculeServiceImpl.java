package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Repositorys.CarteGriseRepository;
import example.backcontrolefacile.Repositorys.UtilisateurRepository;
import example.backcontrolefacile.Repositorys.VehiculeRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VehiculeServiceImpl implements VehiculeService {


    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private CarteGriseRepository carteGriseRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> ajouterVehicule(Vehicule vehicule) {
        if (vehiculeRepository.existsByPlaqueimatri(vehicule.getPlaqueimatri())) {
            return  ResponseEntity.badRequest().body(new MessageResponse("Error: Cet véhicule existe déjà !"));
            // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }
        vehicule.setPhotovehicule("http://127.0.0.1/controleFacile/images/vehicule/vehicule.png");
        vehiculeRepository.save(vehicule);
        return ResponseEntity.ok(new MessageResponse("Vehicule enregistre avec succes !"));

    }


/*    @Override
    public MessageResponse UpdateVehicule(Long idvehicule, Vehicule vehicule) {

        if (vehiculeRepository.findByIdvehicule(idvehicule) != null){

            Vehicule updateVehicule = vehiculeRepository.findById(idvehicule).get();
            updateVehicule.setPlaqueimatri(vehicule.getPlaqueimatri());
            updateVehicule.setCouleur(vehicule.getCouleur());
            updateVehicule.setPhotovehicule(vehicule.getPhotovehicule());

            vehiculeRepository.saveAndFlush(updateVehicule);
            MessageResponse message = new MessageResponse("vehicule modifier avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet vehicule n'existe pas ");
            return message;
        }
    }*/

    @Override
    public MessageResponse UpdateVehiculeImage(Long idvehicule, Vehicule vehicule) {
        if (vehiculeRepository.findByIdvehicule(idvehicule) != null){
            Vehicule updateVehicule = vehiculeRepository.findById(idvehicule).get();

            updateVehicule.setPhotovehicule(vehicule.getPhotovehicule());

            vehiculeRepository.saveAndFlush(updateVehicule);
            MessageResponse message = new MessageResponse("vehicule modifier avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet vehicule n'existe pas ");
            return message;
        }
    }

    @Override
    public MessageResponse UpdateVehicule(Long idvehicule, Vehicule vehicule) {
        if (vehiculeRepository.findByIdvehicule(idvehicule) != null){
            Vehicule updateVehicule = vehiculeRepository.findById(idvehicule).get();
           // updateVehicule.setPlaqueimatri(vehicule.getPlaqueimatri());
            updateVehicule.setCouleur(vehicule.getCouleur());

            vehiculeRepository.save(updateVehicule);

            MessageResponse message = new MessageResponse("vehicule modifier avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet vehicule n'existe pas ");
            return message;
        }
    }

    @Override
    public MessageResponse supprimerVehicule(Long idvehicule) {
        if (vehiculeRepository.findByIdvehicule(idvehicule) != null){

            vehiculeRepository.deleteById(idvehicule);
            MessageResponse message = new MessageResponse("Vehicule Supprimer avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet Vehicule n'existe pas ");

            return message;
        }
    }

    @Override
    public List<Vehicule> afficherVehicule() {

        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule vehiculeParId(Long idvehicule) {

        return vehiculeRepository.findByIdvehicule(idvehicule);
    }

    @Override
    public Vehicule vehiculeparplaque(String plaque) {
        if (vehiculeRepository.findByPlaqueimatri(plaque) != null){

            return vehiculeRepository.findByPlaqueimatri(plaque);
        }

        return null;
    }

    public List<Vehicule> findByUserId(Long userId) {

        return utilisateurRepository.findVehiculesByIdappuser(userId);
    }

/*    @Override
    public MessageResponse addvehicule(Vehicule vehicule, Utilisateur utilisateur, CarteGrise carteGrise) {
        if (utilisateurRepository.existsByTelephone(utilisateur.getTelephone())) {

            MessageResponse message = new MessageResponse("Error: Le numero de telephone est déjà pris !");
            return  message;
        }else {
            Set<AppRole> roles = new HashSet<>();
            AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
            roles.add(policeRole);
            System.out.println("erttrtdtrtretretr"+roles);
            utilisateur.setRoles(roles);
            utilisateur.setEtat(false);
            utilisateur.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");
            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        }



        if (carteGriseRepository.findByNumcartegrise(carteGrise.getNumcartegrise()) != null) {

            MessageResponse message = new MessageResponse("Error: cette carte grise existe déjà !");
            return  message;
        }else {

            if (vehiculeRepository.existsByPlaqueimatri(vehicule.getPlaqueimatri())) {
                MessageResponse message = new MessageResponse("Error: Cet véhicule existe déjà !");
                return message;
                // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
            }
            //CarteGrise carteGrise = carteGriseRepository.findByNumcartegrise(numcartegrise);
             else {
                vehicule.setPhotovehicule("http://127.0.0.1/controleFacile/images/utilisateur/vehicule.png");
                vehicule.setCarteGrise(carteGriseRepository.save(carteGrise));
                vehicule.setNumserie(encoder.encode(vehicule.getNumserie()));
                //Utilisateur utilisateur = utilisateurRepository.findByNom(nom);
                vehicule.setUtilisateur(utilisateurRepository.save(utilisateur));
                vehiculeRepository.save(vehicule);
                carteGriseRepository.save(carteGrise);

                utilisateurRepository.save(utilisateur);

                MessageResponse message = new MessageResponse(" Cet véhicule enregistre avec succes !");
                return message;
            }

        }
    }*/
}
