package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String description;
    private Date date;
    private String lieu;
    private boolean status;

   /* @ManyToOne
    Vehicule vehicule;*/
}
