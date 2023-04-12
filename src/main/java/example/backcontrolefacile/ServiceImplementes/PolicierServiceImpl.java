package example.backcontrolefacile.ServiceImplementes;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import example.backcontrolefacile.Configuration.TwilioConfiguration;
import example.backcontrolefacile.Models.AppRole;
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

import java.util.*;

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

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    public PolicierServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public ResponseEntity<?> ajouterPolicier(Policier policier) {

        if (policierRepository.existsByTelephone(policier.getTelephone())) {
            return  ResponseEntity.ok(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !", false));
           // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }

        if (policierRepository.existsByEmail(policier.getEmail())) {
           return ResponseEntity.ok(new MessageResponse("Error: cet email est déjà utilisé !", false));
           // return new MessageResponse("Error: cet email est déjà utilisé !");
       }

        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_POLICIER)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        policier.setRoles(roles);
        policier.setEtat(true);
        String passe = generateRandomPassword();
        policier.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");
        policier.setPassword(encoder.encode(passe));
        policierRepository.save(policier);
        PhoneNumber to = new PhoneNumber(policier.getTelephone());
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = "Bienvenue sur l'application ControleFacile votre mot de passe " +
                "est '" + passe + "' et votre compte est activé.";
        MessageCreator creator = Message.creator(to, from, message);
        creator.create();
        return ResponseEntity.ok(new MessageResponse("Conroleur enregistre avec succes" , true));
    }

    private String generateRandomPassword() {
        Random r = new Random();
        String password = "";
        for (int i = 0; i < 10; i++) {
            password += String.valueOf(r.nextInt(10));
        }
        return password;
    }

    @Override
    public ResponseEntity<?> UpdatePolicier(Long idappuser, Policier policier) {
        if (policierRepository.findByIdappuser(idappuser) != null){
            Policier updatePolicier = policierRepository.findById(idappuser).get();
            updatePolicier.setNom(policier.getNom());
            updatePolicier.setPrenom(policier.getPrenom());
            updatePolicier.setDomicile(policier.getDomicile());
            updatePolicier.setTelephone(policier.getTelephone());
            //updatePolicier.setPassword(policier.getPassword());
            updatePolicier.setEmail(policier.getEmail());
            updatePolicier.setGrade(policier.getGrade());
           // updatePolicier.setMatricule(policier.getMatricule());

            policierRepository.saveAndFlush(updatePolicier);

            return ResponseEntity.ok(new MessageResponse("Policier modifier avec succès !", true));
        }else {
            return ResponseEntity.ok(new MessageResponse("cet policier n'existe pas !", false));
        }

    }

    @Override
    public ResponseEntity<?> UpdatePolicierProfile(Long idappuser, Policier policier) {
        if (policierRepository.findByIdappuser(idappuser) != null) {
            Policier updatePolicierProfile = policierRepository.findById(idappuser).get();

            updatePolicierProfile.setProfile(policier.getProfile());

            policierRepository.saveAndFlush(updatePolicierProfile);
            return ResponseEntity.ok(new MessageResponse("Profile modifier avec succès !", true));
        } else {
            return ResponseEntity.ok(new MessageResponse("cet profile n'existe pas !", false));
        }
    }

    @Override
    public MessageResponse supprimerPolicier(Long idappuser) {
        if (policierRepository.findByIdappuser(idappuser) != null){
            policierRepository.deleteById(idappuser);

            MessageResponse message = new MessageResponse("Policier Supprimer avec succes", true);
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet Policier n'existe pas ", false);
            return message;
        }
    }

    @Override
    public List<Policier> afficherPolicier() {

        return policierRepository.findAll();
    }
}
