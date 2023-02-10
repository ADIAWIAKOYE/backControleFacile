package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Models.Alerte;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Repositorys.AlerteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alerte")
public class AlerteController {

    @Autowired
    private AlerteRepository alerteRepository;

    @GetMapping("/afficheramende/{idutilisateur}")
    public List<Alerte> afficherAlertParId(@PathVariable Utilisateur idutilisateur) {

        return alerteRepository.findByUtilisateur(idutilisateur);
    }

}
