package ke.co.apollo.otp.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VerifiedUsersTest {

    @Test
    public void testShouldBeAbleToTestGettersAndSetters() {
        String testEmail = "test_email";
        VerifiedUsers verifiedUsers = new VerifiedUsers();
        verifiedUsers.setEmailAddress(testEmail);

        assertEquals(testEmail, verifiedUsers.getEmailAddress());
        assertNull(verifiedUsers.getId());
    }
}
