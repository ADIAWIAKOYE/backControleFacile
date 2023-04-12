package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "permis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpermis;

    private String numpermis;
    private String nom;
    private String prenom;
    private LocalDate datenaissance;
    private String lieunaissance;
    private String domicile;
    private String commune;
    private String profession;
    private LocalDate datedelivrance;
    private LocalDate dateecheance;
    private String profilepermis;
    private String categoriepermis;

    /*@OneToOne
    private Utilisateur utilisateur;*/
}
