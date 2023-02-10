package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Repositorys.PolicierRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.PolicierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/policier")
public class PolicierController {

    @Autowired
    private PolicierService policierService;
    @Autowired
    private PolicierRepository policierRepository;


    @PutMapping("/Update/{idappuser}")
    public ResponseEntity<?> UpdatePolicier(@PathVariable Long idappuser, @RequestParam(value = "nom") String nom, @RequestParam(value = "prenom") String prenom,
                                            @RequestParam(value = "adresse") String adresse, @RequestParam(value = "telephone") String telephone,
                                            @RequestParam(value = "email") String email, @RequestParam(value = "grade") String grade) {
        Policier policier = policierRepository.findByIdappuser(idappuser);
            System.out.println("le grade est "+ grade );
        if(nom == null|| nom.trim().isEmpty()) {
            policier.setNom(policier.getNom());
        }else {
            policier.setNom(nom);
        }
        if(prenom == null|| prenom.trim().isEmpty()) {
            policier.setPrenom(policier.getPrenom());
        }else {
            policier.setPrenom(prenom);
        }
        if(adresse == null|| adresse.trim().isEmpty()) {
            policier.setDomicile(policier.getDomicile());
        }else {
            policier.setDomicile(adresse);
        }
        if(telephone == null|| telephone.trim().isEmpty()) {
            policier.setTelephone(policier.getTelephone());
        }else {
            policier.setTelephone(telephone);
        }
        if(email == null|| email.trim().isEmpty()) {
            policier.setEmail(policier.getEmail());
        }else {
            policier.setEmail(email);
        }
        if(grade == null|| grade.trim().isEmpty()) {
            policier.setGrade(policier.getGrade());
        }else {
            policier.setGrade(grade);
        }
        return policierService.UpdatePolicier(idappuser, policier);
    }

    @PutMapping("/UpdatePolicier/{idappuser}")
    public ResponseEntity<?> UpdatePolicierProfile(@PathVariable ("idappuser") Long idappuser,
                                                   @Valid @RequestParam(value = "file", required = true) MultipartFile file) {
        Policier poli = new Policier();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file != null) {

                poli.setProfile(SaveImage.save("user", file, photoname));
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return policierService.UpdatePolicierProfile(idappuser, poli);
    }

    @DeleteMapping("/supprimer/{idappuser}")
    public MessageResponse supprimerPolicier(Long idappuser) {

        return policierService.supprimerPolicier(idappuser);
    }

    @GetMapping("/afficher")
    public List<Policier> afficherPolicier() {

        return policierService.afficherPolicier();
    }

    @GetMapping("/afficher/{idappuser}")
    public Policier afficherParID(@PathVariable Long idappuser) {

        return policierRepository.findByIdappuser(idappuser);
    }

    @GetMapping("/afficherNbr")
    public int afficherNbr() {

        return policierService.afficherPolicier().size();
    }
}
