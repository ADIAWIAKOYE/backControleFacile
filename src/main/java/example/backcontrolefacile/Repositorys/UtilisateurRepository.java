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

    @Query(value = "SELECT DISTINCT vehicule.*, cartegrise.* FROM Vehicule JOIN CarteGrise ON vehicule.idvehicule = cartegrise.vehicule_idvehicule JOIN Utilisateur ON cartegrise.utilisateur_idappuser = utilisateur.idappuser WHERE utilisateur.idappuser = :idappuser AND cartegrise.dateecheance = (SELECT MAX(cartegrise.dateecheance) FROM CarteGrise cartegrise WHERE cartegrise.vehicule_idvehicule = vehicule.idvehicule)",nativeQuery = true)
    List<Object[]> findVehicules2ByIdappuser(Long idappuser);



}
