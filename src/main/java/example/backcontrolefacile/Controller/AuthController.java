package example.backcontrolefacile.Controller;

import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Repositorys.AppUserRepository;
import example.backcontrolefacile.Requeste.LoginRequeste;
import example.backcontrolefacile.Response.JwtResponse;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Security.JwtUtils;
import example.backcontrolefacile.Security.UserDetailsImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@NoArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

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

        /*return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getIdappuser(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getTelephone(),
                roles));*/

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(
                        userDetails.getIdappuser(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getTelephone(),
                        roles));
    }



    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
