package ke.co.apollo.otp.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OtpResponseTest {

	@Test
	public void testOtpResponseSetNGet() {
		OtpResponse otpResponse = new OtpResponse();
		Object data = new Object();
		OtpResponseStatus responseStatus = new OtpResponseStatus();
		otpResponse.setData(data);
		otpResponse.setResponseStatus(responseStatus);
		assertEquals(data, otpResponse.getData());
		assertEquals(responseStatus, otpResponse.getResponseStatus());
	}

	@Test
	public void testOtpResponseConstrutor() {
		Object data = new Object();
		OtpResponseStatus responseStatus = new OtpResponseStatus();
		OtpResponse otpResponse = new OtpResponse(data, responseStatus);
		assertEquals(data, otpResponse.getData());
		assertEquals(responseStatus, otpResponse.getResponseStatus());
	}

}
