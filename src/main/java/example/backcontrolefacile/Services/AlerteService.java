package example.backcontrolefacile.Services;

import example.backcontrolefacile.Models.Alerte;
import example.backcontrolefacile.Models.Utilisateur;

import java.util.List;

public interface AlerteService {

    List<Alerte> findUtilisateu(Utilisateur idutilisateur);
}
