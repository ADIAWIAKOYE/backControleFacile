package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicule",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "plaqueimatri"),
        })
//permet d'inclure les getter et setter
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
//@ToString
@NoArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idvehicule;
    private String plaqueimatri;
    private String photovehicule;
    private String couleur;


   @ManyToOne
   private Utilisateur utilisateur;

   @OneToOne
    private CarteGrise carteGrise;
}
