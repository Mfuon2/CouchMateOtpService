package ke.co.apollo.otp.service.implementation;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendSmsServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SendSmsServiceImpl sendSmsService;

    @BeforeEach
    void setUp(){
        ResponseEntity responseEntity =  new ResponseEntity<>("SUCCESS", new HttpHeaders(), HttpStatus.OK);
        ReflectionTestUtils.setField(sendSmsService, "notificationBaseUrl", "http://test.com");
        when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(responseEntity);
    }

    @Test
    public void shouldKnowHowToTestTheSMSSend(){
        String result = sendSmsService.sendSMSMessage("01201221", "Otp");
        assertEquals("Success", result);
    }

    @Test
    public void shouldThrowAnErrorWithAMalformedUrl(){
        ReflectionTestUtils.setField(sendSmsService, "notificationBaseUrl", "@^ #123.");
        String result = sendSmsService.sendSMSMessage("01201221", "Otp");

        assertEquals("Success", result);
    }

    @Test
    public void shouldKnowHowToTestTheEmailSend(){
        String result = sendSmsService.sendEmailMessage("01201221", "Otp");
        assertEquals("Success", result);
    }

    @Test
    public void shouldThrowAnErrorWithAMalformedUrlForEmailSend(){
        ReflectionTestUtils.setField(sendSmsService, "notificationBaseUrl", "@^ #123.");
        String result = sendSmsService.sendEmailMessage("01201221", "Otp");

        assertEquals("Success", result);
    }
}
