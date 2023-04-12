package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.Alerte;
import example.backcontrolefacile.Models.Utilisateur;
import example.backcontrolefacile.Repositorys.AlerteRepository;
import example.backcontrolefacile.Services.AlerteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AlerteServiceImpl implements AlerteService {


    @Autowired
    private AlerteRepository alerteRepository;

    public List<Alerte> findUtilisateu(Utilisateur idutilisateur) {
        List<Alerte> result = new ArrayList<Alerte>();
        List<Alerte> listeAlerte = alerteRepository.findByUtilisateur(idutilisateur);
        for (Alerte alerte : listeAlerte) {
            if (alerte.getUtilisateur().equals(idutilisateur)) {
                result.add(alerte);
            }
        }
        Collections.sort(result, new Comparator<Alerte>() {
            public int compare(Alerte a1, Alerte a2) {
                return a2.getDate().compareTo(a1.getDate());
            }
        });
        return result;
    }
}
