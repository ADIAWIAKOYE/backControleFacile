package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT c FROM CarteGrise c WHERE c.utilisateur = :utilisateur")
    List<CarteGrise> findByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);


    Utilisateur findByNom(String nom);
    Boolean existsByTelephone(String telephone);

    Boolean existsByEmail(String email);

    Utilisateur findByIdappuser(Long idappuser);

    Utilisateur findByEtat(boolean etat);

    //Utilisateur findByTelephone(String telephone);


    Utilisateur findByTelephone(String telephone);

    @Query("SELECT DISTINCT v FROM Vehicule v JOIN CarteGrise cg ON v.idvehicule = cg.vehicule.idvehicule JOIN Utilisateur u ON cg.utilisateur.idappuser = u.idappuser WHERE u.idappuser = :utilisateurId")
    List<Vehicule> findVehiculesByIdappuser(@Param("utilisateurId") Long utilisateurId);
}
