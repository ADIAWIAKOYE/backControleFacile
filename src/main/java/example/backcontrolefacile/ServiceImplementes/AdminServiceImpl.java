package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.Admin;
import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.ERole;
import example.backcontrolefacile.Repositorys.AdminRepository;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
     private AppRoleRepository appRoleRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public AppRole addRole(AppRole appRole) {

        return appRoleRepository.save(appRole);
    }

    @Override
    public ResponseEntity<?> ajouterAdmin(Admin admin) {
        if (adminRepository.existsByTelephone(admin.getTelephone())) {
            return  ResponseEntity.badRequest().body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !"));
            // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }

     /*   if (policierRepository.existsByEmail(policier.getEmail())) {
           return ResponseEntity.badRequest().body(new MessageResponse("Error: cet email est déjà utilisé !"));
           // return new MessageResponse("Error: cet email est déjà utilisé !");
       }*/

        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        admin.setRoles(roles);
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
    }
}
