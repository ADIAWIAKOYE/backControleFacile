package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {
}
