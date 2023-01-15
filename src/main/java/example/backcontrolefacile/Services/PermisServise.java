package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Permis;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PermisServise {

    ResponseEntity<?> ajouterPermis(Permis permis);

    MessageResponse UpdatePermis(Long idpermis, Permis permis);

    MessageResponse UpdatePermisImage(Long idpermis, Permis permis);


    MessageResponse supprimerPermis(Long idpermis);

    List<Permis> afficherPermis();

    Permis permisParId(Long idpermis);

    Permis permisparnumero(String numpermis);

}
