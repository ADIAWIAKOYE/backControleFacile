package example.backcontrolefacile.Repositorys;

import example.backcontrolefacile.Models.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    Boolean existsByPlaqueimatri(String plaqueimatri);

    Vehicule findByPlaqueimatri(String plaqueimatri);

    Vehicule findByIdvehicule(Long idvehicule);


}
