 package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Repositorys.AppUserRepository;
import example.backcontrolefacile.Repositorys.PolicierRepository;
import example.backcontrolefacile.Requeste.LoginRequeste;
import example.backcontrolefacile.Requeste.SignupRequeste;
import example.backcontrolefacile.Response.JwtResponse;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Security.JwtUtils;
import example.backcontrolefacile.Security.UserDetailsImpl;

import example.backcontrolefacile.Services.AdminService;
import example.backcontrolefacile.Services.PolicierService;
import example.backcontrolefacile.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    PolicierRepository policierRepository;
    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private PolicierService policierService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequeste loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getTelephone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


       // String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(
                        userDetails.getIdappuser(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getTelephone(),
                        roles));
    }




    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequeste signUpRequest)  {
        if (userRepository.existsByNom(signUpRequest.getNom())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: cet email est déjà utilisé !"));
        }

        // Create new user's account
        AppUser user = new AppUser(signUpRequest.getNom(),
                signUpRequest.getPrenom(),
                signUpRequest.getAdresse(),
                signUpRequest.getTelephone(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getApprole();
        Set<AppRole> roles = new HashSet<>();

        if (strRoles == null)  {
            AppRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        AppRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
                        roles.add(adminRole);

                        break;
                    case "police":
                        AppRole policeRole = roleRepository.findByName(ERole.ROLE_POLICIER)
                                .orElseThrow(() -> new RuntimeException("Error: Le rôle est introuvable."));
                        roles.add(policeRole);

                        break;
                    default:
                        AppRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Erreur: Le rôle est introuvable."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
    }



    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Vous avez été déconnecté !"));
    }


    @PostMapping("/sinuPoliceI")
    public ResponseEntity<?> ajouterPolicierI(@Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse,
                                              @Param(value = "telephone") String telephone, @Param(value = "email") String email, @Param(value = "password") String password,
                                              @Param(value = "matricule") String matricule, @Param(value = "grade") String grade,
                                              @Param(value = "file") MultipartFile file) {
        Policier pol = new Policier();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            pol.setNom(nom);
            pol.setPrenom(prenom);
            pol.setDomicile(adresse);
            pol.setTelephone(telephone);
            pol.setEmail(email);
            pol.setPassword(password);
            pol.setMatricule(matricule);
            pol.setGrade(grade);

            if (file != null) {

                pol.setProfile(SaveImage.save("user", file, photoname));
            }

        }catch (Exception e) {
            // TODO: handle exception
        }
        return policierService.ajouterPolicier(pol);
    }






    @PostMapping("/sinuPoliceII")
    public ResponseEntity<?> ajouterPolicierII(@Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse,
                                               @Param(value = "telephone") String telephone, @Param(value = "email") String email, @Param(value = "password") String password,
                                               @Param(value = "matricule") String matricule, @Param(value = "grade") String grade) {
        Policier pol = new Policier();
//        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            pol.setNom(nom);
            pol.setPrenom(prenom);
            pol.setDomicile(adresse);
            pol.setTelephone(telephone);
            pol.setEmail(email);
            pol.setPassword(password);
            pol.setMatricule(matricule);
            pol.setGrade(grade);

//            if (file != null) {
//
//                pol.setProfile(SaveImage.save("user", file, photoname));
//            }

        }catch (Exception e) {
            // TODO: handle exception
        }
        return policierService.ajouterPolicier(pol);
    }







/*    @PostMapping("/sinuPolice")
    public ResponseEntity<?> ajouterPolicier(@RequestBody Policier policier) {
        return policierService.ajouterPolicier(policier);
    }*/


    @PostMapping("/sinuAdminI")
    public ResponseEntity<?> ajouterAdminI(@Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse,
                                              @Param(value = "telephone") String telephone, @Param(value = "email") String email, @Param(value = "password") String password,
                                              @Param(value = "file") MultipartFile file) {
        Admin adm = new Admin();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            adm.setNom(nom);
            adm.setPrenom(prenom);
            adm.setDomicile(adresse);
            adm.setTelephone(telephone);
            adm.setEmail(email);
            adm.setPassword(password);

            if (file != null) {

                adm.setProfile(SaveImage.save("user", file, photoname));
            }

        }catch (Exception e) {
            // TODO: handle exception
        }
        return adminService.ajouterAdmin(adm);
    }

/*    @PostMapping("/sinuAdmin")
    public ResponseEntity<?> ajouterAdmin(@RequestBody Admin admin) {

        return adminService.ajouterAdmin(admin);
    }*/



    @PostMapping("/sinuUtilisateurI/{idcartegrise}")
    public ResponseEntity<?> ajouterUtilisateurI(@PathVariable Long idcartegrise, @Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse,
                                              @Param(value = "telephone") String telephone, @Param(value = "email") String email, @Param(value = "password") String password,
                                              @Param(value = "commune") String commune, @Param(value = "profession") String profession,
                                              @Param(value = "file") MultipartFile file) {
        Utilisateur utilis = new Utilisateur();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            utilis.setNom(nom);
            utilis.setPrenom(prenom);
            utilis.setDomicile(adresse);
            utilis.setTelephone(telephone);
            utilis.setEmail(email);
            utilis.setPassword(password);
            utilis.setCommune(commune);
            utilis.setProfession(profession);

            if (file != null) {

                utilis.setProfile(SaveImage.save("user", file, photoname));
            }

        }catch (Exception e) {
            // TODO: handle exception
        }
        return utilisateurService.ajouterUtilisateur(utilis);
    }


   /* @PostMapping("/sinuUtilisateur")
    public ResponseEntity<?> ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {

        return utilisateurService.ajouterUtilisateur(utilisateur);
    }*/

    @PostMapping("/sinudminI")
    public ResponseEntity<?> ajouteAdminI(@Param(value = "nom") String nom, @Param(value = "prenom") String prenom, @Param(value = "adresse") String adresse,
                                           @Param(value = "telephone") String telephone, @Param(value = "email") String email, @Param(value = "password") String password
                                           ) {
        Admin adm = new Admin();
      //  String photoname = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            adm.setNom(nom);
            adm.setPrenom(prenom);
            adm.setDomicile(adresse);
            adm.setTelephone(telephone);
            adm.setEmail(email);
            adm.setPassword(password);

            /*if (file != null) {

                adm.setProfile(SaveImage.save("user", file, photoname));
            }*/

        }catch (Exception e) {
            // TODO: handle exception
        }
        return adminService.ajouterAdmin(adm);
    }

}
