package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.AppRole;
import example.backcontrolefacile.Models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByName(ERole name);
}
