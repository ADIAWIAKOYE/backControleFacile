package example.backcontrolefacile.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    Infraction infraction;

    @ManyToOne
    Permis permis;
}
