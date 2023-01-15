package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name = "idappuser")
public class Utilisateur extends AppUser {

    private LocalDate datenaissance;
    private String lieunaissance;
    private String commune;
    private String profession;



    public Utilisateur() {

    }

    @OneToOne
    private Permis permis;
}
