package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "policier")
@Getter
@Setter
@AllArgsConstructor
public class Policier extends AppUser{
}
