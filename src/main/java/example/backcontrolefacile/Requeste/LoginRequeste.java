package example.backcontrolefacile.Requeste;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@ToString
@NoArgsConstructor
public class LoginRequeste {

   // @NotBlank
    private String nom;

   // @NotBlank
    private String password;

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

}
