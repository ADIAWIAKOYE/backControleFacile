package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cartegrise")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarteGrise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcartegrise;

    private String numcartegrise;
    private String nom;
    private String prenom;
    private LocalDate datenaissance;
    private String lieunaissance;
    private String domicile;
    private String commune;
    private String profession;
    private String genre;
    private String marque;
    private String type;
    private String chassie;
    private String carrouserie;
    private String capacite;
    private int nbplace;
    private String energie;
    private int coutunitaire;
    private int puissancereel;
    private int puissanceadmin;
    private LocalDate dpmc;
    private LocalDate datedelivrance;
    private LocalDate dateecheance;
    private int ptac;
    private int pv;

  /*  @OneToOne
    private Vehicule vehicule;*/
}
