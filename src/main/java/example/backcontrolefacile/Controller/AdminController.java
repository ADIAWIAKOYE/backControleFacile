package example.backcontrolefacile.Controller;


import com.google.zxing.WriterException;
import example.backcontrolefacile.Configuration.QRCodeGenerator;
import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.PermisRepository;
import example.backcontrolefacile.Repositorys.UtilisateurRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.AdminService;
import example.backcontrolefacile.Services.CarteGriseServise;
import example.backcontrolefacile.Services.UtilisateurService;
import example.backcontrolefacile.Services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

     @Autowired
     private CarteGriseServise carteGriseServise;

     @Autowired
     private VehiculeService vehiculeService;

     @Autowired
     private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/saverole")
    public AppRole saveRole(@RequestBody AppRole appRole){

        return adminService.addRole(appRole);
    }


    @PutMapping("/Update/{idappuser}")
    public ResponseEntity<?> UpdateAdmin(@PathVariable Long idappuser, @RequestBody Admin admin) {

        return adminService.UpdateAdmin(idappuser, admin);
    }

    @PutMapping("/UpdateProfile/{idappuser}")
    public ResponseEntity<?> UpdateAdminProfile(@PathVariable ("idappuser") Long idappuser,
                                                   @Valid @RequestParam(value = "file", required = true) MultipartFile file) {
        Admin admin = new Admin();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file != null) {

                admin.setProfile(SaveImage.save("user", file, photoname));
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return adminService.UpdateAdminProfile(idappuser, admin);
    }

    @DeleteMapping("/supprimer/{idappuser}")
    public MessageResponse supprimerAdmin(Long idappuser) {

        return adminService.supprimerAdmin(idappuser);
    }

    @GetMapping("/afficher")
    public List<Admin> afficherPolicier() {

        return adminService.afficherAdmin();
    }

    @GetMapping("/afficherNbr")
    public int afficherNbr() {

        return adminService.afficherAdmin().size();
    }


    String maneqr;
    @Autowired
    private PermisRepository permisRepository;

    @PostMapping("/ajouterUtilisateurCartegriseVehicule2/{numpermi}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse ajouterUtilisateurCartegriseVehicule2(@PathVariable(value = "numpermi") String numpermi, @Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse, @Param(value = "password") String password,
                                                                  @Param(value = "commune") String commune, @Param(value = "profession") String profession, @Param(value = "lieunaissance") String lieunaissance,
                                                                  @Param(value = "datenaissance") LocalDate datenaissance, @Param(value = "telephone") String telephone,

                                                                  @Param(value = "plaqueimatri") String plaqueimatri,  @Param(value = "couleur") String couleur,

                                                                  @Param(value = "numcartegrise") String numcartegrise, @Param(value = "genre") String genre, @Param(value = "marque") String marque, @Param(value = "type") String type, @Param(value = "chassie") String chassie,
                                                                  @Param(value = "carrouserie") String carrouserie, @Param(value = "capacite") String capacite, @Param(value = "nbplace") int nbplace, @Param(value = "coutunitaire") int coutunitaire,
                                                                  @Param(value = "energie") String energie, @Param(value = "puissancereel") int puissancereel, @Param(value = "puissanceadmin") int puissanceadmin, @Param(value = "dpmc") LocalDate dpmc,
                                                                  @Param(value = "datedelivrance") LocalDate datedelivrance, @Param(value = "dateecheance") LocalDate dateecheance, @Param(value = "ptac") int ptac, @Param(value = "pv") int pv) throws IOException, WriterException {
        Utilisateur util = new Utilisateur();
        Vehicule vehi = new Vehicule();
        CarteGrise cg = new CarteGrise();
        if (permisRepository.findByNumpermis(numpermi) == null){
            MessageResponse message = new MessageResponse("Cet numero de Permis n'existe pas ", false);
            return message;
        }else {
            Permis permis = permisRepository.findByNumpermis(numpermi);


            try {
                util.setNom(nom);
                util.setPrenom(prenom);
                util.setDomicile(adresse);
                util.setCommune(commune);
                util.setProfession(profession);
                util.setLieunaissance(lieunaissance);
                util.setDatenaissance(datenaissance);
                util.setTelephone(telephone);
                util.setPassword(password);
                util.setPermis(permis);




                cg.setNom(nom);
                cg.setPrenom(prenom);
                cg.setDomicile(adresse);
                cg.setCommune(commune);
                cg.setProfession(profession);
                cg.setLieunaissance(lieunaissance);
                cg.setDatenaissance(datenaissance);
                cg.setNumcartegrise(numcartegrise);
                cg.setGenre(genre);
                cg.setMarque(marque);
                cg.setType(type);
                cg.setChassie(chassie);
                cg.setCapacite(capacite);
                cg.setCarrouserie(carrouserie);
                cg.setCoutunitaire(coutunitaire);
                cg.setNbplace(nbplace);
                cg.setEnergie(energie);
                cg.setPuissancereel(puissancereel);
                cg.setPuissanceadmin(puissanceadmin);
                cg.setDpmc(dpmc);
                cg.setDatedelivrance(datedelivrance);
                cg.setDateecheance(dateecheance);
                cg.setPtac(ptac);
                cg.setPv(pv);


            /*vehi.setCarteGrise(cg);
            vehi.setUtilisateur(util);*/
                vehi.setCouleur(couleur);
                vehi.setPlaqueimatri(plaqueimatri);

                cg.setNumserie("Nom : "+nom+"\n"+"Prenom : "+prenom+"\n"+"Date de naissance : "+datenaissance+"\n"+
                        "Profession : "+profession+"\n"+"Commune : "+commune+"\n"+"Telephone : "+telephone+"\n"+
                        "Plaque d'ummatriculation:"+plaqueimatri+"\n"+ "Numerode la carte grise : "+numcartegrise+"\n"+
                        "Genre : "+genre+"\n"+"Marque : "+marque+"\n"+"Chassie : "+chassie+"\n"+"Carrosserie : "+carrouserie+"\n"+
                        "Type : "+type+"\n"+"Capacite : "+capacite+"\n"+"Nombre de place : "+nbplace+"\n"+
                        "Energie : "+energie+"\n"+"Cout unitaire : "+coutunitaire+"\n"+"Puissance Admin : "+puissanceadmin+"\n"+
                        "Puissance Reél : "+puissancereel+"\n"+ "Validé du "+datedelivrance+" au "+dateecheance+"\n"+
                        "Date de premiere mise en circulation : "+dpmc+"\n"+"D P A C  : "+ptac+"\n"+ " P V  : "+pv);
                maneqr = vehi.getPlaqueimatri();
                String  imagepath = "./src/main/resources/qrcodes/"+maneqr+".png";
                QRCodeGenerator.generateQRCodeImage(cg.getNumserie(), 250, 250, imagepath);

            }catch (Exception e) {
                // TODO: handle exception
            }
            return carteGriseServise.addCarteGrise(cg, vehi, util);
        }

    }




    @PostMapping("/ajouterCartegriseVehicule2/{telephone}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse ajouterCartegriseVehicule2(@PathVariable(value = "telephone") String telephone, @Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse,
                                                                 @Param(value = "commune") String commune, @Param(value = "profession") String profession, @Param(value = "lieunaissance") String lieunaissance,
                                                                 @Param(value = "datenaissance") LocalDate datenaissance,

                                                                 @Param(value = "plaqueimatri") String plaqueimatri,  @Param(value = "couleur") String couleur,

                                                                 @Param(value = "numcartegrise") String numcartegrise, @Param(value = "genre") String genre, @Param(value = "marque") String marque, @Param(value = "type") String type, @Param(value = "chassie") String chassie,
                                                                 @Param(value = "carrouserie") String carrouserie, @Param(value = "capacite") String capacite, @Param(value = "nbplace") int nbplace, @Param(value = "coutunitaire") int coutunitaire,
                                                                 @Param(value = "energie") String energie, @Param(value = "puissancereel") int puissancereel, @Param(value = "puissanceadmin") int puissanceadmin, @Param(value = "dpmc") LocalDate dpmc,
                                                                 @Param(value = "datedelivrance") LocalDate datedelivrance, @Param(value = "dateecheance") LocalDate dateecheance, @Param(value = "ptac") int ptac, @Param(value = "pv") int pv) {
        Vehicule vehi = new Vehicule();
        CarteGrise cg = new CarteGrise();
        if (utilisateurRepository.findByTelephone(telephone) == null){
            MessageResponse message = new MessageResponse("Cet numero de Permis n'existe pas ", false);
            return message;
        }else {

            Utilisateur utilisateur = utilisateurRepository.findByTelephone(telephone);


            try {

                cg.setNom(nom);
                cg.setPrenom(prenom);
                cg.setDomicile(adresse);
                cg.setCommune(commune);
                cg.setProfession(profession);
                cg.setLieunaissance(lieunaissance);
                cg.setDatenaissance(datenaissance);
                cg.setNumcartegrise(numcartegrise);
                cg.setGenre(genre);
                cg.setMarque(marque);
                cg.setType(type);
                cg.setChassie(chassie);
                cg.setCapacite(capacite);
                cg.setCarrouserie(carrouserie);
                cg.setCoutunitaire(coutunitaire);
                cg.setNbplace(nbplace);
                cg.setEnergie(energie);
                cg.setPuissancereel(puissancereel);
                cg.setPuissanceadmin(puissanceadmin);
                cg.setDpmc(dpmc);
                cg.setDatedelivrance(datedelivrance);
                cg.setDateecheance(dateecheance);
                cg.setPtac(ptac);
                cg.setPv(pv);
                cg.setUtilisateur(utilisateur);


            /*vehi.setCarteGrise(cg);
            vehi.setUtilisateur(util);*/
                vehi.setCouleur(couleur);
                vehi.setPlaqueimatri(plaqueimatri);

                cg.setNumserie("Nom : "+nom+"\n"+"Prenom : "+prenom+"\n"+"Date de naissance : "+datenaissance+"\n"+
                        "Profession : "+profession+"\n"+"Commune : "+commune+"\n"+"Telephone : "+telephone+"\n"+
                        "Plaque d'ummatriculation:"+plaqueimatri+"\n"+ "Numerode la carte grise : "+numcartegrise+"\n"+
                        "Genre : "+genre+"\n"+"Marque : "+marque+"\n"+"Chassie : "+chassie+"\n"+"Carrosserie : "+carrouserie+"\n"+
                        "Type : "+type+"\n"+"Capacite : "+capacite+"\n"+"Nombre de place : "+nbplace+"\n"+
                        "Energie : "+energie+"\n"+"Cout unitaire : "+coutunitaire+"\n"+"Puissance Admin : "+puissanceadmin+"\n"+
                        "Puissance Reél : "+puissancereel+"\n"+ "Validé du "+datedelivrance+" au "+dateecheance+"\n"+
                        "Date de premiere mise en circulation : "+dpmc+"\n"+"D P A C  : "+ptac+"\n"+ " P V  : "+pv);
                maneqr = vehi.getPlaqueimatri();
                String  imagepath = "./src/main/resources/qrcodes/"+maneqr+".png";
                QRCodeGenerator.generateQRCodeImage(cg.getNumserie(), 250, 250, imagepath);

            }catch (Exception e) {
                // TODO: handle exception
            }
            return carteGriseServise.addVCarteGrise(cg, vehi, telephone);
        }

    }

}
