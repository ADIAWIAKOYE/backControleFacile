package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "policier")
@Getter
@Setter
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name = "idappuser")
public class Policier extends AppUser{

    private String matricule;
    private String grade;

    public Policier() {

    }

   /* public Policier(String matricule, String grade) {
        this.matricule = matricule;
        this.grade = grade;

    }*/
}
