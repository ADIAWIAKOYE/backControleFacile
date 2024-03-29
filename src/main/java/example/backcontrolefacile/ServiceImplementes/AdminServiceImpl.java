 package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.Admin;
import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.ERole;
import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Repositorys.AdminRepository;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
            return  ResponseEntity.ok(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !", false));
            // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }

        if (adminRepository.existsByEmail(admin.getEmail())) {
           return ResponseEntity.ok(new MessageResponse("Error: cet email est déjà utilisé !", false));
           // return new MessageResponse("Error: cet email est déjà utilisé !");
       }

        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        admin.setRoles(roles);
        String passe = generateRandomPassword();
        admin.setEtat(true);
        admin.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");
        admin.setPassword(encoder.encode(passe));
        adminRepository.save(admin);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès ! le mot de passe est : "+ passe , true));
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
    public Admin addAdmin(Admin admin) {
        Set<AppRole> roles = new HashSet<>();
        AppRole policeRole = appRoleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
        roles.add(policeRole);
        System.out.println("erttrtdtrtretretr"+roles);
        admin.setRoles(roles);
        admin.setEtat(true);
        admin.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");
        admin.setPassword(encoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public ResponseEntity<?> UpdateAdmin(Long idappuser, Admin admin) {
        if (adminRepository.findByIdappuser(idappuser) != null){
            Admin updateAdmin = adminRepository.findById(idappuser).get();
            updateAdmin.setNom(admin.getNom());
            updateAdmin.setPrenom(admin.getPrenom());
            updateAdmin.setDomicile(admin.getDomicile());
            updateAdmin.setTelephone(admin.getTelephone());
            updateAdmin.setPassword(admin.getPassword());
            updateAdmin.setEmail(admin.getEmail());

            adminRepository.saveAndFlush(updateAdmin);

            return ResponseEntity.ok(new MessageResponse("Admin modifier avec succès !", true));
        }else {
            return ResponseEntity.ok(new MessageResponse("cet admin n'existe pas !", false));
        }
    }

    @Override
    public ResponseEntity<?> UpdateAdminProfile(Long idappuser, Admin admin) {
        if (adminRepository.findByIdappuser(idappuser) != null) {
            Admin updateAdminProfile = adminRepository.findById(idappuser).get();

            updateAdminProfile.setProfile(admin.getProfile());

            adminRepository.saveAndFlush(updateAdminProfile);
            return ResponseEntity.ok(new MessageResponse("Admin modifier avec succès !", true));
        } else {
            return ResponseEntity.ok(new MessageResponse("cet admin n'existe pas !", false));
        }
    }

    @Override
    public MessageResponse supprimerAdmin(Long idappuser) {
        if (adminRepository.findByIdappuser(idappuser) != null){
            adminRepository.deleteById(idappuser);

            MessageResponse message = new MessageResponse("Admin Supprimer avec succes", true);
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet admin n'existe pas ", false);
            return message;
        }
    }

    @Override
    public List<Admin> afficherAdmin() {

        return adminRepository.findAll();
    }
}
