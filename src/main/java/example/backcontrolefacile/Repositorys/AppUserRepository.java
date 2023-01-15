package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByNom(String nom);

    Boolean existsByNom(String nom);

    Boolean existsByEmail(String email);

    Optional<AppUser> findByTelephone(String telephone);

    Optional<AppUser> findByTelephoneOrEmail(String telephone, String email);
}
