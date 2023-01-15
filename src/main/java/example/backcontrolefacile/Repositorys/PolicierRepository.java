package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.AppUser;
import example.backcontrolefacile.Models.Policier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicierRepository  extends JpaRepository<Policier, Long> {

    Boolean existsByTelephone(String telephone);

    Boolean existsByEmail(String email);

    Policier findByIdappuser(Long idappuser);
}
