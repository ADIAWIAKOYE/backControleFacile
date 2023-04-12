package example.backcontrolefacile.Configuration;

import org.springframework.stereotype.Service;

@Service
public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);

    // void senddSms(String to, String message);
}
