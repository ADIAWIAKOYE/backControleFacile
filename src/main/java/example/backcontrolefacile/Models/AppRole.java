package example.backcontrolefacile.Models;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "approle")
//permet d'inclure les getter et setter
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
//@ToString
@NoArgsConstructor
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idapprole;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ERole name;


}
