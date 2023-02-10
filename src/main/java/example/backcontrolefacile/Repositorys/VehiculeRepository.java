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

}
