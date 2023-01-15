package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;



    @PutMapping("/Update/{idappuser}")
    public ResponseEntity<?> UpdateUtilisateur(@PathVariable Long idappuser, @RequestBody Utilisateur utilisateur) {

        return utilisateurService.UpdateUtilisateur(idappuser, utilisateur);
    }

    @PutMapping("/UpdateProfile/{idappuser}")
    public ResponseEntity<?> UpdateUtilisateurProfile(@PathVariable ("idappuser") Long idappuser,
                                                   @Valid @RequestParam(value = "file", required = true) MultipartFile file) {
        Utilisateur utili = new Utilisateur();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file != null) {

                utili.setProfile(SaveImage.save("user", file, photoname));
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return utilisateurService.UpdateUtilisateurProfile(idappuser, utili);
    }

    @DeleteMapping("/supprimer/{idappuser}")
    public MessageResponse supprimerUtilisateur(Long idappuser) {

        return utilisateurService.supprimerUtilisateur(idappuser);
    }

    @GetMapping("/afficher")
    public List<Utilisateur> afficherUtilisateur() {

        return utilisateurService.afficherUtilisateur();
    }

    @GetMapping("/afficherNbr")
    public int afficherNbr() {

        return utilisateurService.afficherUtilisateur().size();
    }
}
