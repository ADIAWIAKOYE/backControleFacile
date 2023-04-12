package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

  /*  @OneToMany(mappedBy="utilisateur")
    private List<CarteGrise> carteGrise;*/

  /*  @ElementCollection
    private List<String> carteGriseNumbers;*/

//    @OneToMany(mappedBy="utilisateur")
//    private List<CarteGrise> carteGriseList=new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<CarteGrise> carteGriseList;

    public boolean isPresent() {
        return true;
    }
}
