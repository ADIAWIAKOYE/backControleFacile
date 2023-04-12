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

    private String token;
    private String type = "Bearer";
    private Long idappuser;
    private String nom;
    private String email;
    private String telephone;

    private String profile;
    private List<String> roles;

    public JwtResponse( Long idappuser, String nom, String email, String telephone, String profile, List<String> roles) {
       // this.token = accessToken;
        this.idappuser = idappuser;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.profile = profile;
        this.roles = roles;
    }



    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
