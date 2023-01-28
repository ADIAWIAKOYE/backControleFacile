package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Repositorys.AppUserRepository;
import example.backcontrolefacile.Repositorys.UtilisateurRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> ajouterUtilisateur(Utilisateur utilisateur) {

        if (utilisateurRepository.existsByTelephone(utilisateur.getTelephone())) {
            return  ResponseEntity.badRequest().body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !"));
            // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }

        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
           return ResponseEntity.badRequest().body(new MessageResponse("Error: cet email est déjà utilisé !"));
           // return new MessageResponse("Error: cet email est déjà utilisé !");
       }

        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        utilisateur.setRoles(roles);
        utilisateur.setEtat(false);
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));


    }

    @Override
    public Utilisateur ajouter(Utilisateur utilisateur) {
        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        utilisateur.setRoles(roles);
        utilisateur.setEtat(false);
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public ResponseEntity<?> UpdateUtilisateur(Long idappuser, Utilisateur utilisateur) {
        if (utilisateurRepository.findByIdappuser(idappuser) != null){
            Utilisateur updateUtilisateur = utilisateurRepository.findById(idappuser).get();
            updateUtilisateur.setNom(utilisateur.getNom());
            updateUtilisateur.setPrenom(utilisateur.getPrenom());
            updateUtilisateur.setDomicile(utilisateur.getDomicile());
            updateUtilisateur.setTelephone(utilisateur.getTelephone());
            updateUtilisateur.setPassword(utilisateur.getPassword());
            updateUtilisateur.setEmail(utilisateur.getEmail());
            updateUtilisateur.setProfession(utilisateur.getProfession());
            updateUtilisateur.setCommune(utilisateur.getCommune());

            utilisateurRepository.saveAndFlush(updateUtilisateur);

            return ResponseEntity.ok(new MessageResponse("Utilisateur modifier avec succès !"));
        }else {
            return ResponseEntity.ok(new MessageResponse("cet utilisateur n'existe pas !"));
        }
    }

    @Override
    public ResponseEntity<?> ModifierUtilisateur(Long idappuser,  Utilisateur utilisateur) {
          // Utilisateur utilisateur = new Utilisateur();
            Utilisateur updateUtilisateur = utilisateurRepository.findById(idappuser).get();
            utilisateur.setEtat(true);
            updateUtilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
       // utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
            utilisateurRepository.save(updateUtilisateur);

        return ResponseEntity.ok(new MessageResponse("Utilisateur modifier avec succès !"));
    }

    @Override
    public ResponseEntity<?> UpdateUtilisateurProfile(Long idappuser, Utilisateur utilisateur) {
        if (utilisateurRepository.findByIdappuser(idappuser) != null) {
            Utilisateur updateUtilisateurProfile = utilisateurRepository.findById(idappuser).get();

            updateUtilisateurProfile.setProfile(utilisateur.getProfile());

            utilisateurRepository.saveAndFlush(updateUtilisateurProfile);
            return ResponseEntity.ok(new MessageResponse("Utilisateur modifier avec succès !"));
        } else {
            return ResponseEntity.ok(new MessageResponse("cet utilisateur n'existe pas !"));
        }
    }

    @Override
    public MessageResponse supprimerUtilisateur(Long idappuser) {
        if (utilisateurRepository.findByIdappuser(idappuser) != null){
            utilisateurRepository.deleteById(idappuser);

            MessageResponse message = new MessageResponse("Utilisateur Supprimer avec succes");
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet utilisateur n'existe pas ");
            return message;
        }
    }

    @Override
    public List<Utilisateur> afficherUtilisateur() {

        return utilisateurRepository.findAll();
    }

    @Override
    public MessageResponse setEtat(Utilisateur utilisateur, Long idappuser) {
        return null;
    }

    public List<CarteGrise> findByUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).get();
        return utilisateurRepository.findByUtilisateur(utilisateur);
    }


}
