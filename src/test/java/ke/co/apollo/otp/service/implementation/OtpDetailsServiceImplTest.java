package ke.co.apollo.otp.service.implementation;

import ke.co.apollo.otp.entity.OtpDetails;
import ke.co.apollo.otp.entity.OtpMetaData;
import ke.co.apollo.otp.entity.VerifiedUsers;
import ke.co.apollo.otp.enums.OtpStatusEnum;
import ke.co.apollo.otp.exceptions.OtpCustomException;
import ke.co.apollo.otp.model.OtpRequest;
import ke.co.apollo.otp.model.UserResponse;
import ke.co.apollo.otp.repository.OtpDetailsRepository;
import ke.co.apollo.otp.repository.OtpMetaDataRepository;
import ke.co.apollo.otp.repository.VerifiedUsersRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OtpDetailsServiceImplTest {

	@InjectMocks
	private OtpDetailsServiceImpl otpDetailsService;

	@Mock
	private OtpDetailsRepository otpDetailsRepository;
	@Mock
	private Environment environment;

	@Mock
	private OtpMetaDataRepository otpMetaDataRepository;

	@Mock
	private VerifiedUsersRepository verifiedUsersRepository;

	@Mock
	private SendSmsServiceImpl sendSmsService;

	@BeforeEach
	void setUp(){
		when(sendSmsService.sendSMSMessage(any(), any())).thenReturn("Success");
		when(sendSmsService.sendEmailMessage(any(), any())).thenReturn("Success");
	}

	@Test
	public void testShouldBeAbleToGenerateOtp() {
		String mobileNumber = "0745460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty("otp.sms.messagecontent")).thenReturn("Your otp is {}");
		Mockito.when(environment.getProperty("otp.email.messagecontent")).thenReturn("Dear customer,\n\nThank you for trusting us to meet your insurance needs. \nUse the OTP {}  to finalize the registration process and get started  on managing your policies online.");
		Mockito.when(environment.getProperty("otp.expiryInMinutes")).thenReturn("20");
		String actualResponse = otpDetailsService.generateOtp(mobileNumber, null, null);

		assertEquals("succeeded", actualResponse);
	}

	@Test
	public void testShouldBeAbleToGenerateOtpWithEmailAddress() {
		String mobileNumber = "0745460101";
		String emailAddress = "test@me.com";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty("otp.sms.messagecontent")).thenReturn("Your otp is {}");
		Mockito.when(environment.getProperty("otp.email.messagecontent")).thenReturn("Dear customer,\n\nThank you for trusting us to meet your insurance needs. \nUse the OTP {}  to finalize the registration process and get started  on managing your policies online.");
		Mockito.when(environment.getProperty("otp.expiryInMinutes")).thenReturn("20");
		Mockito.when(verifiedUsersRepository.findByEmailAddress(any())).thenReturn(new ArrayList<>());
		String actualResponse = otpDetailsService.generateOtp(mobileNumber, null, emailAddress);

		assertEquals("succeeded", actualResponse);
		verify(verifiedUsersRepository, times(1)).save(any());
	}

	@Test
	public void testShouldBeAbleToGenerateOtpWithEmailAddressExisting() {
		String mobileNumber = "0745460101";
		String emailAddress = "test@me.com";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty("otp.sms.messagecontent")).thenReturn("Your otp is {}");
		Mockito.when(environment.getProperty("otp.email.messagecontent")).thenReturn("Dear customer,\n\nThank you for trusting us to meet your insurance needs. \nUse the OTP {}  to finalize the registration process and get started  on managing your policies online.");
		Mockito.when(environment.getProperty("otp.expiryInMinutes")).thenReturn("20");
		Mockito.when(verifiedUsersRepository.findByEmailAddress(any())).thenReturn(Collections.singletonList(new VerifiedUsers()));
		String actualResponse = otpDetailsService.generateOtp(mobileNumber, null, emailAddress);

		assertEquals("succeeded", actualResponse);
		verify(verifiedUsersRepository, times(1)).save(any());
	}

	@Test
	public void testShouldBeAbleToGenerateOtpWhenAppCodePresent() {
		String mobileNumber = "0745460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty("otp.sms.messagecontent")).thenReturn("Your otp is {}");
		Mockito.when(environment.getProperty("otp.email.messagecontent")).thenReturn("Dear customer,\n\nThank you for trusting us to meet your insurance needs. \nUse the OTP {}  to finalize the registration process and get started  on managing your policies online.");
		Mockito.when(environment.getProperty("otp.expiryInMinutes")).thenReturn("20");
		String actualResponse = otpDetailsService.generateOtp(mobileNumber, "Test", null);

		assertEquals("succeeded", actualResponse);
	}

	@Test
	public void testShouldThrowExceptionInParsingExpiryTime() {
		String mobileNumber = "0745460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		try {
			Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("Test");
			String actualResponse = otpDetailsService.generateOtp(mobileNumber, null, null);
			assertEquals("Success", actualResponse); // Correctly fails.
		} catch (NumberFormatException e) {}

	}

	@Test(expected = OtpCustomException.class)
	public void testShouleBeAbleToThrowErrorInRegenerateIfUserSkipGenerateOtpProcess() {
		Mockito.when(otpDetailsRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(new ArrayList<OtpDetails>());

		otpDetailsService.reGenerateOtp("0745460101", null, null);
	}

	@Test
	public void testShouldBeAbleToRegenerateSmsSuccessfully() {
		String mobileNumber = "0545460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);


		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("20");

		Mockito.when(otpMetaDataRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(null);
		Mockito.when(otpMetaDataRepository.save(Mockito.any(OtpMetaData.class))).thenReturn(new OtpMetaData());
		Mockito.when(otpDetailsRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(Collections.singletonList(otpDetails));

		String actualResponse = otpDetailsService.reGenerateOtp(mobileNumber, null, null);

		assertEquals("succeeded", actualResponse);
	}

	@Test
	public void testShouldBeAbleToRegenerateSmsSuccessfullyWithAppCode() {
		String mobileNumber = "0545460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);


		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("20");

		Mockito.when(otpMetaDataRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(null);
		Mockito.when(otpMetaDataRepository.save(Mockito.any(OtpMetaData.class))).thenReturn(new OtpMetaData());
		Mockito.when(otpDetailsRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(Collections.singletonList(otpDetails));

		String actualResponse = otpDetailsService.reGenerateOtp(mobileNumber, "Test", null);

		assertEquals("succeeded", actualResponse);
	}

	@Test
	public void testShouldBeAbleToRegenerateWhenOtpMetaDataExists() {
		String mobileNumber = "0545460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		OtpMetaData metaData = new OtpMetaData();
		metaData.setCellphoneNumber(mobileNumber);
		metaData.setOtp("");
		metaData.setGenerateOtpCount(2);
		metaData.setTotalAttempts(2);

		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("20");

		Mockito.when(otpMetaDataRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(metaData);
		Mockito.when(otpMetaDataRepository.save(Mockito.any(OtpMetaData.class))).thenReturn(new OtpMetaData());
		Mockito.when(otpDetailsRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(Collections.singletonList(otpDetails));

		String actualResponse = otpDetailsService.reGenerateOtp(mobileNumber, null, null);

		assertEquals("succeeded", actualResponse);

	}

	@Test
	public void testShouldBeAbleToRegenerateWhenOtpMetaDataExistsAndEmailSpecified() {
		String mobileNumber = "0545460101";
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(mobileNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(30));
		otpDetails.setOtp("123456");
		otpDetails.setStatus(1);

		OtpMetaData metaData = new OtpMetaData();
		metaData.setCellphoneNumber(mobileNumber);
		metaData.setOtp("");
		metaData.setGenerateOtpCount(2);
		metaData.setTotalAttempts(2);

		Mockito.when(otpDetailsRepository.save(Mockito.any(OtpDetails.class))).thenReturn(otpDetails);
		Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("20");

		Mockito.when(otpMetaDataRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(metaData);
		Mockito.when(otpMetaDataRepository.save(Mockito.any(OtpMetaData.class))).thenReturn(new OtpMetaData());
		Mockito.when(otpDetailsRepository.findByCellphoneNumber(Mockito.anyString())).thenReturn(Collections.singletonList(otpDetails));

		String actualResponse = otpDetailsService.reGenerateOtp(mobileNumber, null, "test@meui.com");

		assertEquals("succeeded", actualResponse);

	}

	@Test
	public void testShouldBeAbleToValidateOtpPos() {
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setStatus(OtpStatusEnum.ACTIVE.getStatus());
		otpDetails.setExpiredTime(LocalDateTime.now().plusSeconds(1));
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString()))
				.thenReturn(Optional.of(otpDetails));
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		assertTrue(otpDetailsService.validateOtp(otpRequest));
	}

	@Test
	public void testShouldBeAbleToValidateOtpPosWithEmailAddress() {
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setStatus(OtpStatusEnum.ACTIVE.getStatus());
		otpDetails.setExpiredTime(LocalDateTime.now().plusSeconds(1));
		when(verifiedUsersRepository.save(any())).thenReturn(new VerifiedUsers());
		when(verifiedUsersRepository.findByEmailAddress(any())).thenReturn(Collections.singletonList(new VerifiedUsers()));
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString()))
				.thenReturn(Optional.of(otpDetails));
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		otpRequest.setEmailAddress("test@me.com");
		assertTrue(otpDetailsService.validateOtp(otpRequest));
		verify(verifiedUsersRepository, times(1)).save(any());
	}

	@Test
	public void testShouldBeAbleToValidateOtpPosWithEmailAddressNotExisting() {
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setStatus(OtpStatusEnum.ACTIVE.getStatus());
		otpDetails.setExpiredTime(LocalDateTime.now().plusSeconds(1));
		when(verifiedUsersRepository.findByEmailAddress(any())).thenReturn(new ArrayList<>());
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString()))
				.thenReturn(Optional.of(otpDetails));
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		otpRequest.setEmailAddress("test@me.com");
		assertTrue(otpDetailsService.validateOtp(otpRequest));
		verify(verifiedUsersRepository, times(0)).save(any());
	}

	@Test
	public void testShouldBeAbleToValidateUsedOtp() {
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setStatus(OtpStatusEnum.USED.getStatus());
		otpDetails.setExpiredTime(LocalDateTime.now().minusSeconds(1));
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString()))
				.thenReturn(Optional.of(otpDetails));
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		assertFalse(otpDetailsService.validateOtp(otpRequest));
	}

	@Test
	public void testShouldBeAbleToValidateExpiredOtp() {
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setStatus(OtpStatusEnum.ACTIVE.getStatus());
		otpDetails.setExpiredTime(LocalDateTime.now().minusSeconds(1));
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString()))
				.thenReturn(Optional.of(otpDetails));
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		assertFalse(otpDetailsService.validateOtp(otpRequest));
	}

	@Test(expected = OtpCustomException.class)
	public void testShouldBeAbleToThrowValidateOtpException() {
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString())).thenReturn(Optional.empty());
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		otpDetailsService.validateOtp(otpRequest);
	}

	@Test(expected = OtpCustomException.class)
	public void testShouldBeAbleToThrowValidateOtpException2() {
		Mockito.when(otpDetailsRepository.findByCellphoneNumberAndOtp(anyString(), anyString())).thenThrow(RuntimeException.class);
		OtpRequest otpRequest = new OtpRequest();
		otpRequest.setMobileNumber("0745460101");
		otpRequest.setOtp("otp");
		otpDetailsService.validateOtp(otpRequest);
	}

	@Test
	public void testShouldRetrieveValidatedUser(){
		VerifiedUsers user = new VerifiedUsers();
		user.setVerified(true);
		String mobileNumber = "12345678";
		user.setCellphoneNumber(mobileNumber);
		when(verifiedUsersRepository.findByEmailAddress(anyString())).thenReturn(Collections.singletonList(user));

		UserResponse userResponse = otpDetailsService.verifyUser("test");

		assertTrue(userResponse.isVerified());
	}

	@Test
	public void testShouldRetrieveValidatedUserWhenNotValidatedByJourney(){
		when(verifiedUsersRepository.findByEmailAddress(anyString())).thenReturn(new ArrayList<>());

		UserResponse userResponse = otpDetailsService.verifyUser("test");

		assertTrue(userResponse.isVerified());
		assertEquals("", userResponse.getMobileNumber());
	}

	@Test
	public void testShouldRetrieveUnvalidatedUser(){
		VerifiedUsers verifiedUsers = new VerifiedUsers();
		verifiedUsers.setVerified(false);
		String mobileNumber = "123456789";
		verifiedUsers.setCellphoneNumber(mobileNumber);
		when(verifiedUsersRepository.findByEmailAddress(anyString())).thenReturn(Collections.singletonList(verifiedUsers));

		UserResponse userResponse = otpDetailsService.verifyUser("test");

		assertFalse(userResponse.isVerified());
		assertEquals(mobileNumber, userResponse.getMobileNumber());
	}
}