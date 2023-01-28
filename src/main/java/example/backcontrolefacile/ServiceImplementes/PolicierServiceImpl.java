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
import java.util.List;
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

        if (policierRepository.existsByEmail(policier.getEmail())) {
           return ResponseEntity.badRequest().body(new MessageResponse("Error: cet email est déjà utilisé !"));
           // return new MessageResponse("Error: cet email est déjà utilisé !");
       }

        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_POLICIER)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        policier.setRoles(roles);
        policier.setEtat(true);
        policier.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");
        policier.setPassword(encoder.encode(policier.getPassword()));
        policierRepository.save(policier);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
    }

    @Override
    public ResponseEntity<?> UpdatePolicier(Long idappuser, Policier policier) {
        if (policierRepository.findByIdappuser(idappuser) != null){
            Policier updatePolicier = policierRepository.findById(idappuser).get();
            updatePolicier.setNom(policier.getNom());
            updatePolicier.setPrenom(policier.getPrenom());
            updatePolicier.setDomicile(policier.getDomicile());
            updatePolicier.setTelephone(policier.getTelephone());
            updatePolicier.setPassword(policier.getPassword());
            updatePolicier.setEmail(policier.getEmail());
            updatePolicier.setGrade(policier.getGrade());
            updatePolicier.setMatricule(policier.getMatricule());

            policierRepository.saveAndFlush(updatePolicier);

            return ResponseEntity.ok(new MessageResponse("Policier modifier avec succès !"));
        }else {
            return ResponseEntity.ok(new MessageResponse("cet policier n'existe pas !"));
        }

    }

    @Override
    public ResponseEntity<?> UpdatePolicierProfile(Long idappuser, Policier policier) {
        if (policierRepository.findByIdappuser(idappuser) != null) {
            Policier updatePolicierProfile = policierRepository.findById(idappuser).get();

            updatePolicierProfile.setProfile(policier.getProfile());

            policierRepository.saveAndFlush(updatePolicierProfile);
            return ResponseEntity.ok(new MessageResponse("Profile modifier avec succès !"));
        } else {
            return ResponseEntity.ok(new MessageResponse("cet profile n'existe pas !"));
        }
    }

    @Override
    public MessageResponse supprimerPolicier(Long idappuser) {
        if (policierRepository.findByIdappuser(idappuser) != null){
            policierRepository.deleteById(idappuser);

            MessageResponse message = new MessageResponse("Policier Supprimer avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet Policier n'existe pas ");
            return message;
        }
    }

    @Override
    public List<Policier> afficherPolicier() {

        return policierRepository.findAll();
    }
}
