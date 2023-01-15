package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Policier;
import example.backcontrolefacile.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByNom(String nom);
    Boolean existsByTelephone(String telephone);

    Boolean existsByEmail(String email);

    Utilisateur findByIdappuser(Long idappuser);

    Utilisateur findByEtat(boolean etat);
}
