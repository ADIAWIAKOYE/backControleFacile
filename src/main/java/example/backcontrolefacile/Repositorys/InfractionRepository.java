package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfractionRepository extends JpaRepository<Infraction, Long> {
}
