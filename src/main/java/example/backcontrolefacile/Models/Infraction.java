package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "infraction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Infraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idinfraction;
    //@Column(columnDefinition = "text")
    private String description;
    private Date date;
    private String lieu;
    private Boolean status;

    @ManyToOne
    private Amende amende;

    @ManyToOne
    private Permis permis;

    @ManyToOne
    private Vehicule vehicule;

    @ManyToOne
    private Policier policier;

    /*@OneToOne
    AmendeRegle amendeRegle;*/
}
