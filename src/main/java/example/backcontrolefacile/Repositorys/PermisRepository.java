package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Permis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Long> {

    Permis findByNumpermis(String numpermis);

    Permis findByIdpermis(Long idpermis);
}
