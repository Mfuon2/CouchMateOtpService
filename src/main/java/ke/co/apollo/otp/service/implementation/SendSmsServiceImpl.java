package ke.co.apollo.otp.service.implementation;

import com.google.gson.Gson;
import ke.co.apollo.otp.model.EmailRequest;
import ke.co.apollo.otp.model.NotificationsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class SendSmsServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(SendSmsServiceImpl.class);
    private static final String SUCCESS = "Success";

    @Value("${notifications.url}")
    private String notificationBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String sendSMSMessage(String cellphoneNumber, String messageContent){
        NotificationsRequest notificationsRequest =  new NotificationsRequest();
        notificationsRequest.setText(messageContent);
        notificationsRequest.setTo(cellphoneNumber);
        notificationsRequest.setServiceType("OTP");
        try {
            final String baseUrl = notificationBaseUrl + "/notification/sms/sendSMSMessage";
            URI uri = new URI(baseUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            LOG.info("-------------received sendQuoteSuccessPaymentNotification body ----------------");
            String payloads = new Gson().toJson(notificationsRequest);
            HttpEntity<String> request = new HttpEntity<>(payloads, headers);
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

            LOG.info("Notifications response: {}", result);
        }catch (URISyntaxException e) {
            LOG.error("sendSMSNotification response body 2: {}", e.getMessage());
        }

        return SUCCESS;

    }

    public String sendEmailMessage(String emailAddress, String messageContent){
        EmailRequest emailRequest =  new EmailRequest();
        emailRequest.setText(messageContent);
        emailRequest.setEmailAddress(emailAddress);
        emailRequest.setSubject("APA Insurance OTP");
        try {
            final String baseUrl = notificationBaseUrl + "/notification/email/sendEmail";
            URI uri = new URI(baseUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            LOG.info("-------------received sendEmail body ----------------");
            String payloads = new Gson().toJson(emailRequest);
            HttpEntity<String> request = new HttpEntity<>(payloads, headers);
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

            LOG.info("Notifications response: {}", result);
        }catch (URISyntaxException e) {
            LOG.error("sendSMSNotification response body 2: {}", e.getMessage());
        }

        return SUCCESS;

    }
}
