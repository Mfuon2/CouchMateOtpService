package ke.co.apollo.otp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class OtpRequestTest {
	
	@Test
	public void testShouldBeAbleToTestGettersAndSetters() {
		OtpRequest otpRequest = new OtpRequest();
		
		assertNull(otpRequest.getOtp());

		otpRequest.setOtp("123456");

		assertNotNull(otpRequest.getOtp());
		assertEquals("123456", otpRequest.getOtp());
	}

	@Test
	public void testShouldBeAbleToTestGettersAndSettersWhenTheMobileNumberIsPresent() {
		OtpRequest otpRequest = new OtpRequest();
		String mobileNumber = "123456789";
		String appCode = "test";

		assertNull(otpRequest.getOtp());
		assertNull(otpRequest.getMobileNumber());

		otpRequest.setOtp("123456");
		otpRequest.setMobileNumber(mobileNumber);
		otpRequest.setAppCode(appCode);

		assertNotNull(otpRequest.getOtp());
		assertEquals("123456", otpRequest.getOtp());
		assertEquals(mobileNumber, otpRequest.getMobileNumber());
		assertEquals(appCode, otpRequest.getAppCode());
	}

}
