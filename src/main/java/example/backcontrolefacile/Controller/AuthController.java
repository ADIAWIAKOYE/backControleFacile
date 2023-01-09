 package example.backcontrolefacile.Controller;

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
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequeste loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getNom(), loginRequest.getPassword()));

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



   @PostMapping("/signupPolice")
    public ResponseEntity<?> registerPolicier(@Valid @RequestBody Policier signUpRequest) {
        if (userRepository.existsByNom(signUpRequest.getNom())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Le nom d'utilisateur est déjà pris !"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: cet email est déjà utilisé !"));
        }

        // Create new user's account
       // Policier user = new Policier(/*signUpRequest.getNom(),
                /*signUpRequest.getPrenom(),
                signUpRequest.getAdresse(),
                signUpRequest.getTelephone(),
                signUpRequest.getEmail(),*/
               // signUpRequest.getMatricule(),
              //  signUpRequest.getGrade()
                //encoder.encode(signUpRequest.getPassword())
     //   );

       policierRepository.save(signUpRequest);

       return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));

    }





    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Vous avez été déconnecté !"));
    }

    @PostMapping("/sinuPolice")
    public ResponseEntity<?> ajouterPolicier(@RequestBody Policier policier) {
        encoder.encode(policier.getPassword());
        return policierService.ajouterPolicier(policier);
    }

    @PostMapping("/sinuAdmin")
    public ResponseEntity<?> ajouterAdmin(@RequestBody Admin admin) {

        return adminService.ajouterAdmin(admin);
    }
}
