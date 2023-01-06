package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByNom(String nom);
}
