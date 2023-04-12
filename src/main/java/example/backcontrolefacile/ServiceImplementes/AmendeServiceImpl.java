package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.Amende;
import example.backcontrolefacile.Repositorys.AdminRepository;
import example.backcontrolefacile.Repositorys.AmendeRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.AmendeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmendeServiceImpl implements AmendeService {

    @Autowired
    private AmendeRepository amendeRepository;


    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ResponseEntity<?> ajouterAmende(Amende amende) {
        if (amendeRepository.findByMontant(amende.getMontant()) == null){
            amendeRepository.save(amende);
            return ResponseEntity.ok(new MessageResponse("Amende ajouter avec succes !", true));
        }else {
            return ResponseEntity.ok(new MessageResponse("cet amende existe deja !", false));
        }
    }

    @Override
    public List<Amende> afficherAmende() {

        return amendeRepository.findAll();
    }

    @Override
    public MessageResponse UpdateAmende(Long idamende, Amende amende) {
        if (amendeRepository.findByIdamende(idamende) != null){
            Amende updateamende = amendeRepository.findById(idamende).get();
            updateamende.setMontant(amende.getMontant());
            amendeRepository.saveAndFlush(updateamende);
            MessageResponse message = new MessageResponse("Amende modifier avec succes", true);
            return  message;
        }
        MessageResponse message = new MessageResponse("cet Amende n'existe pas", false);
        return  message;
    }

    @Override
    public MessageResponse supprimerAmende(Long idamende) {
        if (amendeRepository.findByIdamende(idamende) != null){
            amendeRepository.deleteById(idamende);
            MessageResponse message = new MessageResponse("Amende Supprimer avec succes", true);
            return  message;
        }
        MessageResponse message = new MessageResponse(" cet Amende n'existe pas", false);
        return  message;
    }
}
