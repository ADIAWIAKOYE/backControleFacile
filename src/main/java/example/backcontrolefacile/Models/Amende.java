package example.backcontrolefacile.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "amende")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Amende {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idamende;
    private Date date;
    private Long montant;
}
