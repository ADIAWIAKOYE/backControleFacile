package example.backcontrolefacile;

import example.backcontrolefacile.Controller.RsakeysConfig;
import example.backcontrolefacile.Models.Admin;
import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.ERole;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Services.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)

public class BackControleFacileApplication {

    public static void main(String[] args) {

        SpringApplication.run(BackControleFacileApplication.class, args);
    }

    //@Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AppRoleRepository appRoleRepository, AdminService adminService) {
        if (appRoleRepository.findAll().size()==0){
        return args -> {
            appRoleRepository.save(new AppRole(null, ERole.ROLE_ADMIN));
            appRoleRepository.save(new AppRole(null, ERole.ROLE_POLICIER));
            appRoleRepository.save(new AppRole(null, ERole.ROLE_USER));

            Admin admin = new Admin();
            admin.setNom("ADIAWIAKOYE");
            admin.setPrenom("Ahmadou");
            admin.setDomicile("Yirimadio rue : 22 porte : 0938");
            admin.setTelephone("+22375468910");
            admin.setEmail("adiawiakoye.le10@gmail.com");
            admin.setPassword("Aa1010aA");
           //admin.setProfile("http://127.0.0.1/controleFacile/images/utilisateur/icone.png");

           adminService.addAdmin(admin);//new Admin("ADIAWIAKOYE","Ahmadou","Yirimadio rue : 22 porte : 0938","+223 75468913","adiawiakoye.le10@gmail.com",passwordEncoder().encode("Aa1010aA"),"http://127.0.0.1/controleFacile/images/utilisateur/icone.png"));
        };

        }else {
            return null;
        }
    }

}
