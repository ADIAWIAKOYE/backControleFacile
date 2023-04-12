package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.AmendeRegle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmendeRegleRepository extends JpaRepository<AmendeRegle, Long> {


}
