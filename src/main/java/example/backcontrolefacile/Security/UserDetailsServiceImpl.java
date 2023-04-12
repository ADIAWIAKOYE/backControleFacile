package example.backcontrolefacile.Security;

import example.backcontrolefacile.Models.AppUser;
import example.backcontrolefacile.Repositorys.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String telephone) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByTelephone(telephone)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + telephone));
        return UserDetailsImpl.build(user);
    }
}
