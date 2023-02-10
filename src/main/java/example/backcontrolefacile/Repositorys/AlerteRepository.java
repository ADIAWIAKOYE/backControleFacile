package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Alerte;
import example.backcontrolefacile.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {

    List<Alerte> findByUtilisateur(Utilisateur idutilisateur);
}
