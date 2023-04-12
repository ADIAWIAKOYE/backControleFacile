package example.backcontrolefacile.ServiceImplementes;

import example.backcontrolefacile.Models.Permis;
import example.backcontrolefacile.Repositorys.PermisRepository;
import example.backcontrolefacile.Response.MessageResponse;
import example.backcontrolefacile.Services.PermisServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisServiseImpl implements PermisServise {

    @Autowired
    private PermisRepository permisRepository;


    @Override
    public ResponseEntity<?> ajouterPermis(Permis permis) {

        if (permisRepository.findByNumpermis(permis.getNumpermis()) != null) {

            return  ResponseEntity.ok(new MessageResponse("Error: cet permis existe déjà !", false));
            // return new MessageResponse("Error: Le nom d'utilisateur est déjà pris !");
        }

        permisRepository.save(permis);
        return ResponseEntity.ok(new MessageResponse("Permis enregistré avec succès !", true));
    }

    @Override
    public MessageResponse UpdatePermis(Long idpermis, Permis permis) {
        if (permisRepository.findByIdpermis(idpermis) != null){
            Permis updatepermis = permisRepository.findById(idpermis).get();
            updatepermis.setNumpermis(permis.getNumpermis());

            updatepermis.setNom(permis.getNom());
            updatepermis.setPrenom(permis.getPrenom());
            updatepermis.setDatenaissance(permis.getDatenaissance());
            updatepermis.setLieunaissance(permis.getLieunaissance());
            updatepermis.setCommune(permis.getCommune());
            updatepermis.setDomicile(permis.getDomicile());
            updatepermis.setProfession(permis.getProfession());

            updatepermis.setCategoriepermis(permis.getCategoriepermis());
            updatepermis.setDatedelivrance(permis.getDatedelivrance());
            updatepermis.setDateecheance(permis.getDateecheance());

            permisRepository.saveAndFlush(updatepermis);
            MessageResponse message = new MessageResponse("Permis Modifier avec succes", true);
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet Permis n'existe pas ", false);
            return message;
        }
    }

    @Override
    public MessageResponse UpdatePermisImage(Long idpermis, Permis permis) {
        if (permisRepository.findByIdpermis(idpermis) != null){
            Permis updatepermisImage = permisRepository.findById(idpermis).get();
            updatepermisImage.setProfilepermis(permis.getProfilepermis());

            permisRepository.saveAndFlush(updatepermisImage);
            MessageResponse message = new MessageResponse("Permis Modifier avec succes", true);
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet Permis n'existe pas ", false);
            return message;
        }
    }

    @Override
    public MessageResponse supprimerPermis(Long idpermis) {
        if (permisRepository.findByIdpermis(idpermis) != null){
            permisRepository.deleteById(idpermis);

            MessageResponse message = new MessageResponse("Permis Supprimer avec succes", true);
            return  message;
        }else {
            MessageResponse message = new MessageResponse("Cet Permis n'existe pas ", false);
            return message;
        }
    }

    @Override
    public List<Permis> afficherPermis() {

        return permisRepository.findAll();
    }

    @Override
    public Permis permisParId(Long idpermis) {
        if (permisRepository.findByIdpermis(idpermis) != null){

            return permisRepository.findByIdpermis(idpermis);
        }

        return null;
    }

    @Override
    public Permis permisparnumero(String numpermis) {
        if (permisRepository.findByNumpermis(numpermis) != null){

            return permisRepository.findByNumpermis(numpermis);
        }

        return null;
    }

}
