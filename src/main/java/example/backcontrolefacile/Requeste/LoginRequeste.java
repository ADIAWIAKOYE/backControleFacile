package example.backcontrolefacile.Requeste;


import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@ToString
@NoArgsConstructor
public class LoginRequeste {

   // @NotBlank
    private String telephone;

   // @NotBlank
    private String password;

    public String getTelephoneOrEmail() {
        return telephone;
    }

    public void setTelephoneOrEmail(String telephoneOrEmail) {
        this.telephone = telephone;
    }

   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
