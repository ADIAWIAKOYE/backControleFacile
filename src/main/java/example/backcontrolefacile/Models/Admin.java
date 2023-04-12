package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Getter
@Setter
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name = "idappuser")
public class Admin extends AppUser {
    public Admin(String nom, String prenom, String domicile, String telephone, String email, String password, String profile) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDomicile(domicile);
        this.setTelephone(telephone);
        this.setEmail(email);
        this.setPassword(password);
        this.setProfile(profile);
    }



/*    public Admin() {

    }*/
}
