package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Amende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmendeRepository extends JpaRepository<Amende, Long> {
}
