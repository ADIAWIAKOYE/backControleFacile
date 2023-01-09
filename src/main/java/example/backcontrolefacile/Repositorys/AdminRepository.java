package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Admin;
import example.backcontrolefacile.Models.Policier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Boolean existsByTelephone(String telephone);

    Boolean existsByEmail(String email);
}
