package ke.co.apollo.otp.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotificationsRequestTest {
    @Test
    public void testShouldBeAbleToTestGettersAndSetters() {
        NotificationsRequest notificationsRequest = new NotificationsRequest();
        notificationsRequest.setTo("To");
        notificationsRequest.setText("Text");


        assertEquals("To", notificationsRequest.getTo());
        assertEquals("Text", notificationsRequest.getText());
    }
}
