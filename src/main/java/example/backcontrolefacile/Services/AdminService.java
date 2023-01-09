package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Admin;
import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.Policier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    AppRole addRole(AppRole appRole);

    ResponseEntity<?> ajouterAdmin(Admin admin);
}
