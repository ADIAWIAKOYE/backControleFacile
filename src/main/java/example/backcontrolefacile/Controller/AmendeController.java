package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Models.Amende;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.AmendeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/amende")
public class AmendeController {

    @Autowired
    private AmendeService amendeService;


    @PostMapping("/saveamende")
    public ResponseEntity<?> ajouterAmende(@RequestBody Amende amende) {

        return amendeService.ajouterAmende(amende);
    }

    @GetMapping("/afficheramende")
    public List<Amende> afficherAmende() {

        return amendeService.afficherAmende();
    }

    @PutMapping("/updateamende/{idamende}")
    public MessageResponse UpdateAmende(@PathVariable Long idamende, @RequestBody Amende amende) {

        return amendeService.UpdateAmende(idamende, amende);
    }

    @DeleteMapping("/deleteamende/{idamende}")
    public MessageResponse supprimerVehicule(@PathVariable Long idamende) {

        return amendeService.supprimerAmende(idamende);
    }
}
