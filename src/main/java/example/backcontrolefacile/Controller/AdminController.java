package example.backcontrolefacile.Controller;


import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.*;
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



    @PostMapping("/ajouterUtilisateurCartegriseVehicule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> ajouterUtilisateurCartegriseVehicule(@Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse, @Param(value = "password") String password,
                                                                  @Param(value = "commune") String commune, @Param(value = "profession") String profession, @Param(value = "lieunaissance") String lieunaissance,
                                                                  @Param(value = "datenaissance") LocalDate datenaissance, @Param(value = "telephone") String telephone, @Param(value = "fileUser") MultipartFile fileUser,

                                                                  @Param(value = "plaqueimatri") String plaqueimatri,  @Param(value = "couleur") String couleur, @Param(value = "fileVehicule") MultipartFile fileVehicule,

                                                                  @Param(value = "numcartegrise") String numcartegrise, @Param(value = "genre") String genre, @Param(value = "marque") String marque, @Param(value = "type") String type, @Param(value = "chassie") String chassie,
                                                                  @Param(value = "carrouserie") String carrouserie, @Param(value = "capacite") String capacite, @Param(value = "nbplace") int nbplace, @Param(value = "coutunitaire") int coutunitaire,
                                                                  @Param(value = "energie") String energie, @Param(value = "puissancereel") int puissancereel, @Param(value = "puissanceadmin") int puissanceadmin, @Param(value = "dpmc") LocalDate dpmc,
                                                                  @Param(value = "datedelivrance") LocalDate datedelivrance, @Param(value = "dateecheance") LocalDate dateecheance, @Param(value = "ptac") int ptac, @Param(value = "pv") int pv) {
        Utilisateur util = new Utilisateur();
        Vehicule vehi = new Vehicule();
        CarteGrise cg = new CarteGrise();
        String photovehicule = StringUtils.cleanPath(fileVehicule.getOriginalFilename());
        String photouser = StringUtils.cleanPath(fileUser.getOriginalFilename());
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


            vehi.setCarteGrise(cg);
            vehi.setUtilisateur(util);
            vehi.setCouleur(couleur);
            vehi.setPlaqueimatri(plaqueimatri);

            if (fileUser != null) {
                util.setProfile(SaveImage.save("user", fileUser, photouser));
            }

            if (fileUser != null) {
                vehi.setPhotovehicule(SaveImage.save("vehicule", fileUser, photovehicule));
            }

        }catch (Exception e) {
            // TODO: handle exception
        }

        //ResponseEntity<?> tt = utilisateurService.ajouterUtilisateur(util);
        vehi.setUtilisateur(utilisateurService.ajouter(util));
        vehi.setCarteGrise(carteGriseServise.ajouterCG(cg));
        return vehiculeService.ajouterVehiculeI(vehi, numcartegrise, nom);
    }






    @PostMapping("/ajouterUtilisateurCartegriseVehicule2")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse ajouterUtilisateurCartegriseVehicule2(@Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse, @Param(value = "password") String password,
                                                                  @Param(value = "commune") String commune, @Param(value = "profession") String profession, @Param(value = "lieunaissance") String lieunaissance,
                                                                  @Param(value = "datenaissance") LocalDate datenaissance, @Param(value = "telephone") String telephone,

                                                                  @Param(value = "plaqueimatri") String plaqueimatri,  @Param(value = "couleur") String couleur,

                                                                  @Param(value = "numcartegrise") String numcartegrise, @Param(value = "genre") String genre, @Param(value = "marque") String marque, @Param(value = "type") String type, @Param(value = "chassie") String chassie,
                                                                  @Param(value = "carrouserie") String carrouserie, @Param(value = "capacite") String capacite, @Param(value = "nbplace") int nbplace, @Param(value = "coutunitaire") int coutunitaire,
                                                                  @Param(value = "energie") String energie, @Param(value = "puissancereel") int puissancereel, @Param(value = "puissanceadmin") int puissanceadmin, @Param(value = "dpmc") LocalDate dpmc,
                                                                  @Param(value = "datedelivrance") LocalDate datedelivrance, @Param(value = "dateecheance") LocalDate dateecheance, @Param(value = "ptac") int ptac, @Param(value = "pv") int pv) {
        Utilisateur util = new Utilisateur();
        Vehicule vehi = new Vehicule();
        CarteGrise cg = new CarteGrise();
        /*String photovehicule = StringUtils.cleanPath(fileVehicule.getOriginalFilename());
        String photouser = StringUtils.cleanPath(fileUser.getOriginalFilename());*/
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


            vehi.setCarteGrise(cg);
            vehi.setUtilisateur(util);
            vehi.setCouleur(couleur);
            vehi.setPlaqueimatri(plaqueimatri);

           /* if (fileUser != null) {
                util.setProfile(SaveImage.save("user", fileUser, photouser));
            }

            if (fileUser != null) {
                vehi.setPhotovehicule(SaveImage.save("vehicule", fileUser, photovehicule));
            }*/

        }catch (Exception e) {
            // TODO: handle exception
        }

        //ResponseEntity<?> tt = utilisateurService.ajouterUtilisateur(util);
        //vehi.setUtilisateur(utilisateurService.ajouter(util));
       // vehi.setCarteGrise(carteGriseServise.ajouterCG(cg));
        return vehiculeService.addvehicule(vehi, util, cg);
    }

}
