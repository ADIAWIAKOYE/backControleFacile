package example.backcontrolefacile.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private Long montant;
}
