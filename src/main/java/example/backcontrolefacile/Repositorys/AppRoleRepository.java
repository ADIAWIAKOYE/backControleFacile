package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    Optional<AppRole> findByName(ERole name);

    //AppRole findByName(ERole name);
}
