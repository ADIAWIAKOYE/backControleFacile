package example.backcontrolefacile.Requeste;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@ToString
@NoArgsConstructor
public class SingnupPolice {

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String password;
    private String matricule;
    private String grade;


    private Set<String> approle;
}
