package example.backcontrolefacile.Security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import example.backcontrolefacile.Models.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long idappuser;

    private String nom;

    private String email;

    private String telephone;

    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long idappuser, String nom, String email, String telephone, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.idappuser = idappuser;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(AppUser appUser) {
        List<GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                appUser.getIdappuser(),
                appUser.getNom(),
                appUser.getEmail(),
                appUser.getTelephone(),
                appUser.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public Long getIdappuser() {
        return idappuser;
    }


    public String getEmail() {
        return email;
    }


    public String getTelephone() {
        return telephone;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nom;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(idappuser, user.idappuser);
    }

}
