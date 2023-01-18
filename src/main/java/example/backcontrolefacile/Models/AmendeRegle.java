package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "amenderegle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmendeRegle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idamenderegle;
    private Long montant;
    private Date date;
}
