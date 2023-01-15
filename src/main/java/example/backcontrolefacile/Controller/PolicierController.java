package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.PolicierService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PutMapping("/Update/{idappuser}")
    public ResponseEntity<?> UpdatePolicier(@PathVariable Long idappuser, @RequestBody Policier policier) {

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

    @GetMapping("/afficherNbr")
    public int afficherNbr() {

        return policierService.afficherPolicier().size();
    }
}
