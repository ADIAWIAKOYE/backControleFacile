package example.backcontrolefacile;

import example.backcontrolefacile.Controller.RsakeysConfig;
import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.ERole;
import example.backcontrolefacile.Repositorys.AppRoleRepository;
import example.backcontrolefacile.Services.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class BackControleFacileApplication {

    public static void main(String[] args) {

        SpringApplication.run(BackControleFacileApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AppRoleRepository appRoleRepository) {
        if (appRoleRepository.findAll().size()==0){
        return args -> {
            appRoleRepository.save(new AppRole(null, ERole.ROLE_ADMIN));
            appRoleRepository.save(new AppRole(null, ERole.ROLE_POLICIER));
            appRoleRepository.save(new AppRole(null, ERole.ROLE_USER));
        };

        }else {
            return null;
        }
    }

}
