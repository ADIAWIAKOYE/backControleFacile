package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.AppUser;
import example.backcontrolefacile.Models.ERole;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Repositorys.AppUserRepository;
import example.backcontrolefacile.Repositorys.PolicierRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.PolicierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PolicierServiceImpl implements PolicierService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PolicierRepository policierRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> ajouterPolicier(Policier policier) {

        if (policierRepository.existsByTelephone(policier.getTelephone())) {
            return  ResponseEntity.badRequest().body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !"));
           // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }

     /*   if (policierRepository.existsByEmail(policier.getEmail())) {
           return ResponseEntity.badRequest().body(new MessageResponse("Error: cet email est déjà utilisé !"));
           // return new MessageResponse("Error: cet email est déjà utilisé !");
       }*/

        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_POLICIER)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        policier.setRoles(roles);
        policier.setPassword(encoder.encode(policier.getPassword()));
        policierRepository.save(policier);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
    }
}
