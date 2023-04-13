package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Permis;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Repositorys.VehiculeRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.PermisServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/permis")
public class PermisController {

    @Autowired
    private PermisServise permisServise;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @PostMapping("/save")
    public ResponseEntity<?> ajouterPermis(@Param("nom") String nom, @Param("prenom") String prenom, @Param("adresse") String adresse,
                                           @Param("commune") String commune, @Param("profession") String profession,
                                           @Param("lieunaissance") String lieunaissance, @Param("datenaissance") String datenaissance,
                                           @Param("numpermis") String numpermis, @Param("categoriepermis") String categoriepermis,
                                           @Param("datedelivrance") String datedelivrance, @Param("dateecheance") LocalDate dateecheance) {
                                          // @Param("file") MultipartFile file) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateFormatnaissance = LocalDate.parse(datenaissance, formatter);
//        LocalDate dateFormatdpmc = LocalDate.parse(dpmc, formatter);
        LocalDate dateFormatdelivrance = LocalDate.parse(datedelivrance, formatter);
       // LocalDate dateFormatecheance = LocalDate.parse(dateecheance, formatter);

        Permis perm = new Permis();
       // String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            perm.setDomicile(adresse);
            perm.setNom(nom);
            perm.setPrenom(prenom);
            perm.setCommune(commune);
            perm.setLieunaissance(lieunaissance);
            perm.setDatenaissance(dateFormatnaissance);
            perm.setProfession(profession);
            perm.setNumpermis(numpermis);
            perm.setCategoriepermis(categoriepermis);
            perm.setDatedelivrance(dateFormatdelivrance);
            perm.setDateecheance(dateecheance);

        /*    if (file != null) {

                perm.setProfilepermis(SaveImage.save("permis", file, photoname));
            }*/
        }catch (Exception e) {
            // TODO: handle exception
        }
        return permisServise.ajouterPermis(perm);
    }

    @PutMapping("/updateImage/{idpermis}")
    public MessageResponse updateProfile(@PathVariable ("idpermis") Long idpermis,
                                               @Valid @RequestParam(value = "file", required = false) MultipartFile file) {
        Permis perm = new Permis();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            if (file != null) {

                perm.setProfilepermis(SaveImage.save("permis", file, photoname));
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return permisServise.UpdatePermisImage(idpermis, perm);

    }

    @PutMapping("/update/{idpermis}")
    public MessageResponse updatePermis(@PathVariable ("idpermis") Long idpermis, @RequestBody Permis permis) {

        return permisServise.UpdatePermis(idpermis, permis);
    }

    @DeleteMapping("/supprimer/{idpermis}")
    public MessageResponse supprimerPermis(@PathVariable Long idpermis) {

        return permisServise.supprimerPermis(idpermis);
    }

    @GetMapping("/afficher")
    public List<Permis> afficherPermis() {

        return permisServise.afficherPermis();
    }

    @GetMapping("/affichernbr")
    public int afficherNbrPermis() {

        return permisServise.afficherPermis().size();
    }

    @GetMapping("/afficherParId/{idpermis}")
    public Permis PermisParId(@PathVariable Long idpermis) {

        return permisServise.permisParId(idpermis);
    }

    @GetMapping("/afficherParNumero/{numpermis}")
    public Permis PermisParNumero(@PathVariable String numpermis) {

        return permisServise.permisparnumero(numpermis);
    }


    @GetMapping("/permis/vehicule/{id}")
    public Permis getPermisByVehicule (@PathVariable Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);
        if (vehicule == null) {
            return null;
        }
        List<CarteGrise> cartegrise = vehicule.getCarteGrise();
        for (CarteGrise carteGrise : cartegrise) {
            /*if (carteGrise.getStatus() == "Valide"){*/
                Utilisateur utilisateur = carteGrise.getUtilisateur();
                return utilisateur.getPermis();
      //  }

    }
        return null;
    }

}
