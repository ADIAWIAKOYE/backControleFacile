package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Alerte;
import example.backcontrolefacile.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {

    List<Alerte> findByUtilisateur(Utilisateur idutilisateur);


    @Query(value = "SELECT alerte.* FROM alerte, utilisateur WHERE alerte.utilisateur_idappuser = utilisateur.idappuser\n" +
            "AND alerte.utilisateur_idappuser =:idutilisateur ORDER BY alerte.date DESC" ,nativeQuery = true)
    List<Alerte> AfficheralerteParIdUtilisateur(Long idutilisateur);
}
