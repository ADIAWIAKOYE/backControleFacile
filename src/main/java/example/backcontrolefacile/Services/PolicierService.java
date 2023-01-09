package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.AppUser;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface PolicierService {

    ResponseEntity<?> ajouterPolicier(Policier policier);
}
