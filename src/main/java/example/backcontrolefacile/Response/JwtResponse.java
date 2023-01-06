package example.backcontrolefacile.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class JwtResponse {

  /*  private String token;
    private String type = "Bearer";*/
    private Long idappuser;
    private String nom;
    private String email;
    private String telephone;
    private List<String> roles;

    public JwtResponse( Long idappuser, String nom, String email, String telephone, List<String> roles) {
       // this.token = accessToken;
        this.idappuser = idappuser;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.roles = roles;
    }
}
