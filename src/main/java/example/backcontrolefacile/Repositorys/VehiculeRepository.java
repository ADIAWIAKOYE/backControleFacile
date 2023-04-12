package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    Boolean existsByPlaqueimatri(String plaqueimatri);

    Vehicule findByPlaqueimatri(String plaqueimatri);

    Vehicule findByIdvehicule(Long idvehicule);

    @Query("SELECT v FROM Vehicule v INNER JOIN CarteGrise c ON v.idvehicule = c.vehicule.idvehicule WHERE c.utilisateur = :utilisateur")
    List<Vehicule> findByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);



    @Query(value = "SELECT DISTINCT vehicule.*, cartegrise.* FROM Vehicule JOIN CarteGrise ON vehicule.idvehicule = cartegrise.vehicule_idvehicule JOIN Utilisateur ON cartegrise.utilisateur_idappuser = utilisateur.idappuser WHERE cartegrise.dateecheance = (SELECT MAX(cartegrise.dateecheance) FROM CarteGrise cartegrise WHERE cartegrise.vehicule_idvehicule = vehicule.idvehicule)",nativeQuery = true)
    List<Object[]> findallVehicul();
}
