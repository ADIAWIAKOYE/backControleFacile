package example.backcontrolefacile.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Getter
@Setter
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name = "idappuser")
public class Admin extends AppUser {



/*    public Admin() {

    }*/
}
