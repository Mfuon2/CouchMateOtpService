package ke.co.apollo.otp.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class OtpDetailsTest {

	@Test
	public void testShouldBeAbleToTestGettersAndSetters() {
		OtpDetails otpDetails = new OtpDetails();
		
		assertNull(otpDetails.getCellphoneNumber());
		assertNull(otpDetails.getExpiredTime());
		assertNull(otpDetails.getGeneratedTime());
		assertNull(otpDetails.getId());
		assertNull(otpDetails.getOtp());
		assertNull(otpDetails.getStatus());

		otpDetails.setCellphoneNumber("1234567890");
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		assertNotNull(otpDetails.getCellphoneNumber());
		assertNotNull(otpDetails.getExpiredTime());
		assertNotNull(otpDetails.getGeneratedTime());
		assertNotNull(otpDetails.getOtp());
		assertNotNull(otpDetails.getStatus());

		assertTrue(otpDetails.getExpiredTime().isAfter(otpDetails.getGeneratedTime()));
	}
}
