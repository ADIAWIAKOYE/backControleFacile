package example.backcontrolefacile.Configuration;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import example.backcontrolefacile.Models.Alerte;
import example.backcontrolefacile.Models.CarteGrise;
import example.backcontrolefacile.Models.Infraction;
import example.backcontrolefacile.Repositorys.AlerteRepository;
import example.backcontrolefacile.Repositorys.CarteGriseRepository;
import example.backcontrolefacile.Repositorys.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class AlertTask {

    @Autowired
    private final InfractionRepository infractionRepository;

    @Autowired
    private final AlerteRepository alerteRepository;

    @Autowired
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    private final CarteGriseRepository carteGriseRepository;


    public AlertTask(InfractionRepository infractionRepository, AlerteRepository alerteRepository,
                     TwilioConfiguration twilioConfiguration, CarteGriseRepository carteGriseRepository) {
        this.infractionRepository = infractionRepository;
        this.alerteRepository = alerteRepository;
        this.twilioConfiguration = twilioConfiguration;
        this.carteGriseRepository = carteGriseRepository;
    }
//86400000
    @Scheduled(fixedRate = 86400000)
    public void createAlertForUser() {
        List<Infraction> infractions = infractionRepository.findByStatus(false);
        for (Infraction infraction : infractions) {
                 Date tre;
                 tre = infraction.getDate();
            List<CarteGrise> carteGrises = infraction.getVehicule().getCarteGrise();

            for (CarteGrise carteGrise : carteGrises){
                Alerte alert = new Alerte();

                String plat;

                plat = carteGrise.getVehicule().getPlaqueimatri();

                alert.setUtilisateur(carteGrise.getUtilisateur());
                alert.setStatus("Alert");
                alert.setDate(LocalDate.now());

                alert.setContenue("vous etes convoquer au commisaria de venir regler votre infraction  \n" +
                        "du "+ tre + "  avec le vehicule de plaque " + plat);

                /*if (isPhoneNumberValid("+22375468913")) {
                    PhoneNumber to = new PhoneNumber("+22375468913");
                    PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
                    String message = "vous etes convoquer au commisaria de venir regler votre infraction  \n" +
                            "du "+ tre + "avec le vehicule de plaque " + plat;
                    MessageCreator creator = Message.creator(to, from, message);
                    creator.create();

                } else {
                    throw new IllegalArgumentException(
                            "Phone number [" + "+22375468913" + "] n'est pas valide"
                    );
                }*/




                this.alerteRepository.save(alert);
            }
       }
    }

/*    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }*/

}
