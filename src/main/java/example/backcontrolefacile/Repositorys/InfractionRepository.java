package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Infraction;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Models.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfractionRepository extends JpaRepository<Infraction, Long> {

    Infraction findByIdinfraction(Long idinfraction);

    List<Infraction> findByVehicule(Vehicule idvehicule);

  //  List<Infraction> findByStatusAndVehiculeCartegriseUserId(Boolean status, Long userId);

    @Query("SELECT i FROM Infraction i WHERE i.status = :status")
    List<Infraction> findByStatus(Boolean status);

/*    @Query("SELECT i.vehicule.carteGrise.utilisateur FROM Infraction i WHERE i.idinfraction = :idinfraction")
    Utilisateur findUserByInfractionId(@Param("idinfraction") Long idinfraction);

    @Query("SELECT i.vehicule.carteGrise.utilisateur FROM Infraction i JOIN i.vehicule.carteGrise cg WHERE i.idinfraction = :idinfraction AND cg.utilisateur = i.vehicule.carteGrise.utilisateur")
    List<Utilisateur> findUsersByInfractionId(@Param("idinfraction") Long idinfraction);*/

    /*@Query("SELECT i FROM Infraction i JOIN i.vehicule v JOIN v.carteGrise cg JOIN cg.utilisateur u WHERE u.idappuser = :idutilisateur")
    List<Infraction> findInfractionsByUserId(@Param("idutilisateur") Long idutilisateur);*/

    /* @Query("SELECT i FROM Infraction i WHERE i.vehicule.carteGrise.utilisateur.idutilisateur = :utilisateurId")
    List<Infraction> findInfractionsByUtilisateurId(@Param("utilisateurId") Long utilisateurId);

   @Query("SELECT i FROM Infraction i JOIN i.vehicule.carteGrise cg JOIN cg.utilisateur u WHERE u.idutilisateur = :idutilisateur")
    List<Infraction> findInfractionsByUser(@Param("idutilisateur") Long idutilisateur);*/

    @Query("SELECT i FROM Infraction i JOIN i.vehicule v JOIN v.carteGrise cg JOIN cg.utilisateur u WHERE u.idappuser = :idutilisateur")
    List<Infraction> findInfractionsByUser(@Param("idutilisateur") Long idutilisateur);

/*
    @Query("SELECT i FROM Infraction i JOIN i.vehicule v JOIN v.carteGrise cg JOIN cg.utilisateur u WHERE u.id = :idUtilisateur")
    List<Infraction> findInfractionsByUtilisateurId(@Param("idUtilisateur") Long idUtilisateur);
*/



   @Query(value = " SELECT infraction.*\n" +
           "    FROM infraction,vehicule,utilisateur,cartegrise\n" +
           "    WHERE utilisateur.idappuser = cartegrise.utilisateur_idappuser\n" +
           "    AND vehicule.idvehicule = infraction.vehicule_idvehicule\n" +
           "    AND  vehicule.idvehicule = cartegrise.vehicule_idvehicule\n" +
           "    AND utilisateur.idappuser=:idutilisateur\n" +
           "    ORDER BY infraction.date DESC" ,nativeQuery = true)
   List<Object> AfficherinfractionParIdUtilisateur(Long idutilisateur);

    @Query(value = "SELECT infraction.date,infraction.description,infraction.lieu,infraction.status,infraction.amende_idamende,vehicule.plaqueimatri\n" +
            "FROM infraction,vehicule,utilisateur,cartegrise\n" +
            "WHERE utilisateur.idappuser = cartegrise.utilisateur_idappuser\n" +
            "AND vehicule.idvehicule = infraction.vehicule_idvehicule\n" +
            "AND  vehicule.idvehicule = cartegrise.vehicule_idvehicule\n" +
            "AND utilisateur.idappuser=:idutilisateur\n" +
            "ORDER BY infraction.date DESC" ,nativeQuery = true)
    List<Object> AfficherinfractionParIdUtilisateurrr(Long idutilisateur);


    @Query(value = "SELECT infraction.*, amenderegle.* FROM infraction " +
            "JOIN amenderegle ON infraction.idinfraction = amenderegle.infraction_idinfraction " +
            "JOIN vehicule ON vehicule.idvehicule = infraction.vehicule_idvehicule " +
            "WHERE vehicule.idvehicule=:idvehicule " +
            "ORDER BY infraction.date DESC, amenderegle.date DESC", nativeQuery = true)
    List<Object[]> afficherInfractionAmendeRegleParVehicule(@Param("idvehicule") Long idvehicule);
}
