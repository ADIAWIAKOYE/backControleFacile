package example.backcontrolefacile.Requeste;


import lombok.*;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@ToString
@NoArgsConstructor
public class SignupRequeste {


    /*@NotBlank
    @Size(min = 3, max = 20)*/
    private String nom;

    private String prenom;
    private String adresse;
    //private String domicile;
    private String telephone;
   /* @NotBlank
    @Size(max = 50)
    @Email*/
    private String email;

    /*@NotBlank
  @Size(min = 6, max = 40)*/
    private String password;



    private Set<String> approle;


/*    public String getNom() {

        return nom;
    }

    public void setNom(String nom1) {
        this.nom = nom1;
    }

    public void setPrenom(String prenom1) {
        this.prenom = prenom1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }*/
}




