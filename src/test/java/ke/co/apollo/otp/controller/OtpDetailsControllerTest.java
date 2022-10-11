package ke.co.apollo.otp.controller;

import ke.co.apollo.otp.constant.OTPConstants;
import ke.co.apollo.otp.model.OtpRequest;
import ke.co.apollo.otp.model.OtpResponse;
import ke.co.apollo.otp.model.UserResponse;
import ke.co.apollo.otp.service.implementation.OtpDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class OtpDetailsControllerTest {

	@Mock
	private OtpDetailsServiceImpl otpDetailsService;

	@InjectMocks
	private OtpDetailsController otpDetailsController;

	@Test
	public void testShouldBeAbleToGenerateOtp() {
		String res = "Success";
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setOtp(null);
		otpRequest.setMobileNumber("12345678");

		Mockito.when(otpDetailsService.generateOtp(Mockito.anyString(), any(), any())).thenReturn(res);

		OtpResponse otpResp = otpDetailsController.generateOtp(otpRequest);
		assertNotNull(otpResp);
		assertEquals(res, (String) otpResp.getData());
	}

	@Test
	public void testShouldBeAbleToRegenerateOtp() {
		String res = "Success";
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setOtp(null);
		otpRequest.setMobileNumber("12345678");

		Mockito.when(otpDetailsService.reGenerateOtp(Mockito.anyString(), any(), any())).thenReturn(res);

		OtpResponse otpResp = otpDetailsController.reGenerateOtp(otpRequest);
		assertNotNull(otpResp);
		assertEquals(res, (String) otpResp.getData());

	}

	@Test
	public void testShouldBeAbleToValidateOtpPos() {
		String res = OTPConstants.VALID_OTP;
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setOtp("TestOtp");
		otpRequest.setMobileNumber("12345678");

		Mockito.when(otpDetailsService.validateOtp(any(OtpRequest.class))).thenReturn(true);
		OtpResponse otpResponse = otpDetailsController.validateOtp(otpRequest);
		assertNotNull(otpResponse);
		assertEquals(res, (String) otpResponse.getResponseStatus().getDescription());
	}

	@Test
	public void testShouldBeAbleToValidateOtpNeg() {
		String res = OTPConstants.NOT_VALID_OTP;
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setOtp("TestOtp");
		otpRequest.setMobileNumber("12345678");

		Mockito.when(otpDetailsService.validateOtp(any(OtpRequest.class))).thenReturn(false);

		OtpResponse otpResponse = otpDetailsController.validateOtp(otpRequest);
		assertNotNull(otpResponse);
		assertEquals(res, (String) otpResponse.getResponseStatus().getDescription());
	}

	@Test
	public void testShouldBeAbleToVerifyUsers(){
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setOtp("TestOtp");
		String mobileNumber = "12345678";
		otpRequest.setMobileNumber(mobileNumber);
		otpRequest.setEmailAddress("test@me.com");

		UserResponse userResponse = new UserResponse();
		userResponse.setVerified(true);
		userResponse.setMobileNumber(mobileNumber);

		Mockito.when(otpDetailsService.verifyUser(any())).thenReturn(userResponse);

		UserResponse result = otpDetailsController.verifyUser(otpRequest);

		assertTrue(result.isVerified());
		assertEquals(mobileNumber, result.getMobileNumber());
	}
}
