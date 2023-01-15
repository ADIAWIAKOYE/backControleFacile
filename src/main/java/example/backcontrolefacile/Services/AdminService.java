package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Admin;
import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    AppRole addRole(AppRole appRole);

    ResponseEntity<?> ajouterAdmin(Admin admin);

    Admin addAdmin (Admin admin);

    ResponseEntity<?> UpdateAdmin(Long idappuser, Admin admin);

    ResponseEntity<?> UpdateAdminProfile(Long idappuser, Admin admin);

    MessageResponse supprimerAdmin(Long idappuser);

    List<Admin> afficherAdmin();
}
