package example.backcontrolefacile.Controller;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import example.backcontrolefacile.Configuration.SaveImage;
import example.backcontrolefacile.Configuration.SmsRequest;
import example.backcontrolefacile.Configuration.TwilioConfiguration;
import example.backcontrolefacile.Models.*;
import example.backcontrolefacile.Repositorys.*;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CarteGriseRepository carteGriseRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private final TwilioConfiguration twilioConfiguration;

    public UtilisateurController(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @PutMapping("/Update/{idappuser}")
    public ResponseEntity<?> UpdateUtilisateur(@PathVariable Long idappuser, @RequestBody Utilisateur utilisateur) {

        return utilisateurService.UpdateUtilisateur(idappuser, utilisateur);
    }

    @PutMapping("/Updateutil/{idappuser}")
    public ResponseEntity<?> UpdateUtilisateurU(@PathVariable Long idappuser, @RequestParam(value = "nom") String nom, @RequestParam(value = "prenom") String prenom,
                                                @RequestParam(value = "domicile") String domicile, @RequestParam(value = "telephone") String telephone,
                                                @RequestParam(value = "profession") String profession, @RequestParam(value = "commune") String commune) {
        Utilisateur utilisateur = utilisateurRepository.findByIdappuser(idappuser);
    //    System.out.println("le grade est "+ grade );
        if(nom == null|| nom.trim().isEmpty()) {
            utilisateur.setNom(utilisateur.getNom());
        }else {
            utilisateur.setNom(nom);
        }
        if(prenom == null|| prenom.trim().isEmpty()) {
            utilisateur.setPrenom(utilisateur.getPrenom());
        }else {
            utilisateur.setPrenom(prenom);
        }
        if(domicile == null|| domicile.trim().isEmpty()) {
            utilisateur.setDomicile(utilisateur.getDomicile());
        }else {
            utilisateur.setDomicile(domicile);
        }
        if(telephone == null|| telephone.trim().isEmpty()) {
            utilisateur.setTelephone(utilisateur.getTelephone());
        }else {
            utilisateur.setTelephone(telephone);
        }
        if(profession == null|| profession.trim().isEmpty()) {
            utilisateur.setProfession(utilisateur.getProfession());
        }else {
            utilisateur.setProfession(profession);
        }
        if(commune == null|| commune.trim().isEmpty()) {
            utilisateur.setCommune(utilisateur.getCommune());
        }else {
            utilisateur.setCommune(commune);
        }
        return utilisateurService.UpdateUtilisateur(idappuser, utilisateur);
    }



    @PutMapping("/UpdateProfile/{idappuser}")
    public ResponseEntity<?> UpdateUtilisateurProfile(@PathVariable("idappuser") Long idappuser,
                                                      @Valid @RequestParam(value = "file", required = true) MultipartFile file) {
        Utilisateur utili = new Utilisateur();
        String photoname = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file != null) {

                utili.setProfile(SaveImage.save("user", file, photoname));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return utilisateurService.UpdateUtilisateurProfile(idappuser, utili);
    }

    @DeleteMapping("/supprimer/{idappuser}")
    public MessageResponse supprimerUtilisateur(Long idappuser) {

        return utilisateurService.supprimerUtilisateur(idappuser);
    }

    @GetMapping("/afficher")
    public List<Utilisateur> afficherUtilisateur() {

        return utilisateurService.afficherUtilisateur();
    }

    @GetMapping("/afficher/{idappuser}")
    public Utilisateur affich (@PathVariable Long idappuser){
        return utilisateurRepository.findByIdappuser(idappuser);
    }

    @GetMapping("/afficherNbr")
    public int afficherNbr() {

        return utilisateurService.afficherUtilisateur().size();
    }

    public ResponseEntity<?> ModifierUtilisateur(Long idappuser, Utilisateur utilisateur) {

        return utilisateurService.ModifierUtilisateur(idappuser, utilisateur);
    }


    @PostMapping("/user/registe")
    public MessageResponse egisterUser(@RequestParam(value = "nom") String nom, @RequestParam(value = "prenom") String prenom,
                                       @RequestParam(value = "adresse") String adresse,@RequestParam(value = "commune") String commune,
                                       @RequestParam(value = "profession") String profession, @RequestParam(value = "telephone") String telephone,
                                       @RequestParam(value = "numpermis") String numpermis, @RequestParam(value = "numcartegrise") String numcartegrise) {

        Utilisateur existingUser = utilisateurRepository.findByTelephone(telephone);
        Utilisateur existnom = utilisateurRepository.findByNom(nom);
        Permis exitepermis = permisRepository.findByNumpermis(numpermis);

        String[] items = numcartegrise.split(",");
        List<String> list = Arrays.asList(items);
        System.out.println("le numero de telephone est  " + telephone);
        System.out.println("hfhuyiguhiooko   ITEM  " + items);
        System.out.println("hfhuyiguhiooko   LISTE  " + list);

        boolean isValid = true;
        if (existingUser == null || telephone.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || commune.isEmpty() || profession.isEmpty()) {
            return new MessageResponse("cet utilisateur n'existe pas .", false);
        }else {

                for (String cart : items) {
                    CarteGrise existingCarteGrise = carteGriseRepository.findByNumcartegrise(cart);
                    if (existingCarteGrise == null) {
                        isValid = false;
                        break;
                    } else if (existingCarteGrise.getUtilisateur() == null || existingCarteGrise.getUtilisateur().getIdappuser() != existingUser.getIdappuser()) {
                        isValid = false;
                        break;
                    }
                }
                String permi = numpermis;
            if (existingUser.getPermis().getIdpermis() == exitepermis.getIdpermis() || numpermis.isEmpty()){

                if (existingUser != null && isValid) {
                    existingUser.setEtat(true);
                    String passe = generateRandomPassword();
                    existingUser.setPassword(passe);
                   // SmsRequest smsRequest = new SmsRequest(telephone, message);
                    PhoneNumber to = new PhoneNumber(telephone);
                    PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
                    String message = "Informations enregistrées avec succès. Votre mot de passe par défaut " +
                            "est '" + passe + "' et votre compte est activé.";
                    utilisateurService.ModifierUtilisateur(existingUser.getIdappuser(), existingUser);
                    MessageCreator creator = Message.creator(to, from, message);
                    creator.create();
                    return new MessageResponse("Veuillez consulter votre boite SMS", true);
                }
            }else {
                return new MessageResponse("Veillez verifier le numero de permis renseigner .", false);
            }
            }
                return new MessageResponse("Il y a un numéro de carte grise qui n'est pas lié à ce compte utilisateur " +
                        "ou qui n'existe pas",false);
        }



    private String generateRandomPassword() {
        Random r = new Random();
        String password = "";
        for (int i = 0; i < 10; i++) {
            password += String.valueOf(r.nextInt(10));
        }
        return password;
    }



    @GetMapping("/cartegrise/{id}")
    public List<CarteGrise> getCarteGriseByUser(@PathVariable(value = "id") Long id) {
        return utilisateurService.findByUtilisateur(id);
    }


    @GetMapping("/afficherU/{idappuser}")
    public AppUser affichUser (@PathVariable Long idappuser){
        return appUserRepository.findByIdappuser(idappuser);
    }


    @GetMapping("/vehiculeuserrr/{idappuser}")
    public List<Object[]> vehiculeuser(@PathVariable Long idappuser) {
        return utilisateurRepository.findVehicules2ByIdappuser(idappuser);
    }
    }
