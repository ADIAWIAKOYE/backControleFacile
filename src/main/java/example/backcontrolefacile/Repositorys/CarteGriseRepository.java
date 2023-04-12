package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteGriseRepository extends JpaRepository<CarteGrise, Long> {

    CarteGrise findByNumcartegrise(String numcartegrise);

    CarteGrise findByIdcartegrise(Long idcartegrise);

   List<CarteGrise> findByVehicule(Vehicule idvehicule);

    CarteGrise findByVehiculeAndStatus(Vehicule idvehicule, String status);


    CarteGrise findByUtilisateur(Long idappuser);

    @Query("SELECT cg FROM CarteGrise cg WHERE cg.vehicule.idvehicule = :idVehicule AND cg.dateecheance = (SELECT MAX(cg2.dateecheance) FROM CarteGrise cg2 WHERE cg2.vehicule.idvehicule = :idVehicule)")
    CarteGrise findValidatedCarteGriseByVehiculeId(@Param("idVehicule") Long idVehicule);

  //  List<CarteGrise> findByVehicule(Vehicule idvehicule);


}
