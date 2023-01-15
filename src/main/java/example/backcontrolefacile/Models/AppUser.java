package example.backcontrolefacile.Models;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appuser"/*,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }*/)

//permet d'inclure les getter et setter
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idappuser;
    private String nom;
    private String prenom;
    private String domicile;
    private String telephone;
    private String email;
    private String password;

    private String profile;

    private boolean etat;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "idappuser"),
            inverseJoinColumns = @JoinColumn(name = "idapprole"))
    private Set<AppRole> roles = new HashSet<>();


    public AppUser(String nom, String prenom, String adresse, String telephone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.domicile = adresse;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }
}
