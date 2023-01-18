package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteGriseRepository extends JpaRepository<CarteGrise, Long> {

    CarteGrise findByNumcartegrise(String numcartegrise);

    CarteGrise findByIdcartegrise(Long idcartegrise);

   List<CarteGrise> findByVehicule(Vehicule idvehicule);

    CarteGrise findByVehiculeAndStatus(Vehicule idvehicule, String status);

  //  List<CarteGrise> findByVehicule(Vehicule idvehicule);


}
