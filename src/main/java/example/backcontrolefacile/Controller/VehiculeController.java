package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.VehiculeService;
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
@RequestMapping("/api/vehicule")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;


    @PostMapping("/save")
    public ResponseEntity<?> ajouterVehicule(@Param(value = "plaqueimatri") String plaqueimatri,
                                             @Param(value = "couleur") String couleur) {
        Vehicule veh = new Vehicule();
      //  String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        // Vehicule vehicule1 = null;
       // try {
            // vehicule1 = new JsonMapper().readValue(vehicul, Vehicule.class);
            veh.setCouleur(couleur);
            veh.setPlaqueimatri(plaqueimatri);

           // if (file != null) {
                //   System.out.println("ggggg");
            //    veh.setPhotovehicule(SaveImage.save("vehicule", file, photoname));
           // }
       // }catch (Exception e) {
            // TODO: handle exception
       // }
        return vehiculeService.ajouterVehicule(veh);
    }


/*    @PutMapping("/update/{idvehicule}")
    public MessageResponse updateVehicule(@PathVariable ("idvehicule") Long idvehicule,
                                          @Valid @RequestParam(value = "plaqueimatri") String plaqueimatri,
                                          @Valid @RequestParam(value = "couleur") String couleur,
                                          @Valid @RequestParam(value = "file", required = false) MultipartFile file) {
        Vehicule veh = new Vehicule();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        // Vehicule vehicule1 = null;
        try {
            // vehicule1 = new JsonMapper().readValue(vehicul, Vehicule.class);
            veh.setCouleur(couleur);
            veh.setPlaqueimatri(plaqueimatri);

            if (file != null) {
                //   System.out.println("ggggg");
                veh.setPhotovehicule(SaveImage.save("vehicule", file, photoname));
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return vehiculeService.UpdateVehicule(idvehicule, veh);

    }*/

    @PutMapping("/updateImage/{idvehicule}")
    public MessageResponse updateVehiculeImage(@PathVariable ("idvehicule") Long idvehicule,
                                               @Valid @RequestParam(value = "file", required = false) MultipartFile file) {
        Vehicule veh = new Vehicule();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            if (file != null) {

                veh.setPhotovehicule(SaveImage.save("vehicule", file, photoname));
            }
        }catch (Exception e) {
            // TODO: handle exception
        }
        return vehiculeService.UpdateVehiculeImage(idvehicule, veh);

    }


    @PutMapping("/update/{idvehicule}")
    public MessageResponse updateVehiculeSansImage(@PathVariable ("idvehicule") Long idvehicule, @RequestBody Vehicule vehicule) {

        return vehiculeService.UpdateVehicule(idvehicule, vehicule);
    }

    @DeleteMapping("/supprimer/{idvehicule}")
    public MessageResponse supprimerVehicule(@PathVariable Long idvehicule) {

        return vehiculeService.supprimerVehicule(idvehicule);
    }

    @GetMapping("/afficher")
    public List<Vehicule> afficherVehicule() {

        return vehiculeService.afficherVehicule();
    }

    @GetMapping("/afficherNbr")
    public int afficherNbrVehicule() {

        return vehiculeService.afficherVehicule().size();
    }

    @GetMapping("/afficherParId/{idvehicule}")
    public Vehicule vehiculeParId(@PathVariable Long idvehicule) {

        return vehiculeService.vehiculeParId(idvehicule);
    }

    @GetMapping("/afficherParplaque/{plaque}")
    public Vehicule vehiculeparplaque(@PathVariable String plaque){

        return vehiculeService.vehiculeparplaque(plaque);
    }
}
