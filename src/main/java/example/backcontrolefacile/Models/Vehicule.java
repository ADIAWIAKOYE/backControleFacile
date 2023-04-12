package example.backcontrolefacile.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @JsonIgnore
    @OneToMany(mappedBy = "vehicule", fetch = FetchType.EAGER)
    private List<CarteGrise> carteGrise;

}
