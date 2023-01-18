package example.backcontrolefacile.ServiceImplementes;

import com.google.zxing.WriterException;
import example.backcontrolefacile.Configuration.QRCodeGenerator;
import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Repositorys.CarteGriseRepository;
import example.backcontrolefacile.Repositorys.UtilisateurRepository;
import example.backcontrolefacile.Repositorys.VehiculeRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.CarteGriseServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarteGriseServiseImpl implements CarteGriseServise {


    @Autowired
    private CarteGriseRepository carteGriseRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public ResponseEntity<?> ajouterCarteGrise(CarteGrise carteGrise, Long idvehiule, Long idapppuser) throws IOException, WriterException {
        if (vehiculeRepository.findByIdvehicule(idvehiule) == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: cet vehicule n'existe pas !"));
        } else {
            Vehicule vehicule = vehiculeRepository.findByIdvehicule(idvehiule);
            carteGrise.setVehicule(vehicule);
        }
        if (utilisateurRepository.findByIdappuser(idapppuser) == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: cet utilisateur n'existe pas !"));
        } else {
            Utilisateur utilisateur = utilisateurRepository.findByIdappuser(idapppuser);
            carteGrise.setUtilisateur(utilisateur);
        }
        if (carteGriseRepository.findByNumcartegrise(carteGrise.getNumcartegrise()) != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: cette carte grise existe déjà !"));
        }
        Utilisateur utilisateur = utilisateurRepository.findByIdappuser(idapppuser);
        Vehicule vehicule = vehiculeRepository.findByIdvehicule(idvehiule);
        carteGrise.setNumserie("Nom : " + carteGrise.getNom() + "\n" +
                "Prenom : " + carteGrise.getPrenom() + "\n" + "Né le : " + carteGrise.getDatenaissance() + " à " + carteGrise.getLieunaissance() + "\n" +
                "Profession : " + carteGrise.getProfession() + "\n" + "Commune : " + carteGrise.getCommune() + "\n" + "Domicile : " + carteGrise.getDomicile() + "\n" +
                "Telephone : " + utilisateur.getTelephone() + "\n" + "Plaque d'ummatriculation :" + vehicule.getPlaqueimatri() + "\n" + "Genre : " + carteGrise.getGenre() + "\n" +
                "Numerode la carte grise : " + carteGrise.getNumcartegrise() + "\n" + "Chassie : " + carteGrise.getChassie() + "\n" + "Carrosserie :" + carteGrise.getCarrouserie() + "\n" +
                "Type : " + carteGrise.getType() + "\n" + "Capacité : " + carteGrise.getCapacite() + "\n" + "nombre  : " + carteGrise.getChassie() + "\n" + "Carrosserie :" + carteGrise.getCarrouserie() + "\n" +
                "Energie : " + carteGrise.getEnergie() + "\n" + "Cout unitaire : " + carteGrise.getCoutunitaire() + "\n" + "Puissanse reél : " + carteGrise.getPuissancereel() + "\n" +
                "Date de première mise en circulation : " + carteGrise.getDpmc() + "\n" + "Validé du :" + carteGrise.getDatedelivrance() + " au " + carteGrise.getDateecheance() + "\n" +
                "D P A C  : " + carteGrise.getPtac() + "\n" + " P V  : " + carteGrise.getPv());
        maneqr = carteGrise.getNumcartegrise();
        String imagepath = "./src/main/resources/qrcodes/" + maneqr + ".png";
        QRCodeGenerator.generateQRCodeImage(carteGrise.getNumserie(), 250, 250, imagepath);

        carteGrise.setNumserie(encoder.encode(carteGrise.getNumserie()));
        carteGrise.setStatus("Expiré");
        carteGriseRepository.save(carteGrise);
        return ResponseEntity.ok(new MessageResponse("Carte grise enregistré avec succès !"));
    }

    @Override
    public CarteGrise ajouterCG(CarteGrise carteGrise) {

        return carteGriseRepository.save(carteGrise);
    }

    @Override
    public MessageResponse UpdateCarteGrise(Long idcartegrise, CarteGrise carteGrise) {
        if (carteGriseRepository.findByIdcartegrise(idcartegrise) != null) {
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
            return message;
        } else {
            MessageResponse message = new MessageResponse("cette carte grise n'existe pas ");
            return message;
        }
    }

    @Override
    public MessageResponse supprimerCarteGrise(Long idcartegrise) {
        if (carteGriseRepository.findByIdcartegrise(idcartegrise) != null) {
            carteGriseRepository.deleteById(idcartegrise);

            MessageResponse message = new MessageResponse("Carte grise Supprimer avec succes");
            return message;
        } else {
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
        if (carteGriseRepository.findByIdcartegrise(idcartegrise) != null) {

            return carteGriseRepository.findByIdcartegrise(idcartegrise);
        }

        return null;
    }

    @Override
    public CarteGrise CarteGriseparnumero(String numcartegrise) {
        if (carteGriseRepository.findByNumcartegrise(numcartegrise) != null) {

            return carteGriseRepository.findByNumcartegrise(numcartegrise);
        }

        return null;
    }

    @Override
    public CarteGrise CarteGriseValideparvehicule(Long idvehicule, String status) {
        CarteGrise carteGrise = new CarteGrise();

        return null;
    }

    @Override
    public List<CarteGrise> CarteGriseparvehicule(Vehicule idvehicule) {
        List<CarteGrise> carteGrise = carteGriseRepository.findByVehicule(idvehicule);
        System.out.println("sdfgh"+carteGrise);
        List<CarteGrise> carteG = new ArrayList<>();
        if (carteGrise != null) {
            for (CarteGrise cart : carteGrise) {
                if (cart.getStatus()=="Expiré");
                System.out.println("sdfgh"+cart.getStatus());
                carteG.add(cart);
            }
            return carteG;
            //  CarteGrise carteGrise = new CarteGrise();
            // if (carteGrise.getStatus() == "Valide") {

            // }else return null;

        } else {
            return null;
        }
    }



    String maneqr;
    @Override
    public MessageResponse addCarteGrise(CarteGrise carteGrise, Vehicule vehicule, Utilisateur utilisateur) throws IOException, WriterException {
        if (utilisateurRepository.existsByTelephone(utilisateur.getTelephone())) {

            MessageResponse message = new MessageResponse("Error: Le numero de telephone est déjà pris !");
            return  message;
        }else {
            Set<AppRole> roles = new HashSet<>();
            AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
            roles.add(policeRole);
            System.out.println("erttrtdtrtretretr" + roles);
            utilisateur.setRoles(roles);
            utilisateur.setEtat(false);
            utilisateur.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");
            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        }
        if (vehiculeRepository.existsByPlaqueimatri(vehicule.getPlaqueimatri())) {
            MessageResponse message = new MessageResponse("Error: Cet véhicule existe déjà !");
            return message;
            // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }else {
            vehicule.setPhotovehicule("http://127.0.0.1/controleFacile/images/utilisateur/vehicule.png");
        } if (carteGriseRepository.findByNumcartegrise(carteGrise.getNumcartegrise()) != null) {

            MessageResponse message = new MessageResponse("Error: cette carte grise existe déjà !");
            return  message;
        }else {
            carteGrise.setStatus("Valide");
            carteGrise.setVehicule(vehiculeRepository.save(vehicule));
            carteGrise.setUtilisateur(utilisateurRepository.save(utilisateur));
            /*carteGrise.setNumserie("Nom : "+carteGrise.getNom()+"\n"+
                                   "Prenom : "+carteGrise.getPrenom()+"\n"+
                                   "Date de naissance : "+carteGrise.getDatenaissance()+"\n"+
                                    "Profession : "+carteGrise.getProfession()+"\n"+
                                    "Commune : "+carteGrise.getCommune()+"\n"+
                                    "Telephone : "+utilisateur.getTelephone()+"\n"+
                                    "Plaque d'ummatriculation:"+vehicule.getPlaqueimatri()+"\n"+
                                    "Numerode la carte grise : "+carteGrise.getNumcartegrise());
            maneqr = carteGrise.getNumcartegrise();
            String  imagepath = "./src/main/resources/qrcodes/"+maneqr+".png";
            QRCodeGenerator.generateQRCodeImage(carteGrise.getNumserie(), 250, 250, imagepath);*/

            carteGrise.setNumserie(encoder.encode(carteGrise.getNumserie()));

            carteGriseRepository.save(carteGrise);


            MessageResponse message = new MessageResponse(" votre carte grise est enregistre avec succes !");
            return message;
        }
    }
}
