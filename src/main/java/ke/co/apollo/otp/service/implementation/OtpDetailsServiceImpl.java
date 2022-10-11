package ke.co.apollo.otp.service.implementation;

import ke.co.apollo.otp.constant.OTPConstants;
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
import ke.co.apollo.otp.service.OtpDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OtpDetailsServiceImpl implements OtpDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(OtpDetailsServiceImpl.class);

	@Autowired
	private OtpDetailsRepository otpDetailsRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private OtpMetaDataRepository otpMetaDataRepository;

	@Autowired
	private VerifiedUsersRepository verifiedUsersRepository;

	@Autowired
	private SendSmsServiceImpl sendSmsService;

	@Override
	@Transactional
	public String generateOtp(String cellphoneNumber, String appCode, String emailAddress) {
		String generatedOTP = getRandomNumberString();
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(cellphoneNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		int expiryMinutes = Integer.parseInt(environment.getProperty("otp.expiryInMinutes"));
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(expiryMinutes));
		otpDetails.setOtp(generatedOTP);
		otpDetails.setStatus(OtpStatusEnum.ACTIVE.getStatus());
		otpDetailsRepository.save(otpDetails);

		LOG.info("The otp has been generated for mobile number {}", cellphoneNumber);
		addUserForVerification(emailAddress, cellphoneNumber);
		message(appCode,generatedOTP,emailAddress, cellphoneNumber);

		return OTPConstants.SUCCEEDED;
	}

	private void addUserForVerification(String emailAddress, String cellphoneNumber){
		if(emailAddress != null) {
			List<VerifiedUsers> optionalUser = verifiedUsersRepository.findByEmailAddress(emailAddress);
			VerifiedUsers user = new VerifiedUsers();

			if (!optionalUser.isEmpty()) {
				user = optionalUser.get(0);
			}

			user.setEmailAddress(emailAddress);
			user.setCellphoneNumber(cellphoneNumber);
			user.setVerified(false);

			verifiedUsersRepository.save(user);
		}
	}

	private void sendSmsUsingInternalGateWay(String cellphoneNumber, String messageContent) {
		LOG.info("-------About to send OTP ----- to Notifications API");
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(() -> sendSmsService.sendSMSMessage(cellphoneNumber, messageContent));
		LOG.info("-----after sending to notification/sms/sendSMSMessage------");
	}

	private void sendEmailUsingInternalGateWay(String emailAddress, String messageContent) {
		LOG.info("-------About to send OTP ----- to Notifications API");
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(() -> sendSmsService.sendEmailMessage(emailAddress, messageContent));
		LOG.info("-----after sending to notification/email/sendEmail------");
	}

	private String getRandomNumberString() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = null;
		int number = 0;
		try {
			rnd = SecureRandom.getInstanceStrong();
			number = rnd.nextInt(999999);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Error while generating random number for OTP.", e);
		}

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);
	}

	@Override
	@Transactional
	public String reGenerateOtp(String cellphoneNumber, String appCode, String emailAddress) {
		List<OtpDetails> existingOtpList = otpDetailsRepository.findByCellphoneNumber(cellphoneNumber);
		if (CollectionUtils.isEmpty(existingOtpList)) {
			LOG.error("Regenerate Otp can be done only after generation process.");
			throw new OtpCustomException("OTP_REGENERATION_NOT_ALLOWED",
					"Regenerate Otp can be done only after generation process.");
		}

		String generatedOTP = getRandomNumberString();
		OtpDetails otpDetails = new OtpDetails();
		otpDetails.setCellphoneNumber(cellphoneNumber);
		otpDetails.setGeneratedTime(LocalDateTime.now());
		int expiryMinutes = Integer.parseInt(environment.getProperty("otp.expiryInMinutes"));
		otpDetails.setExpiredTime(LocalDateTime.now().plusMinutes(expiryMinutes));
		otpDetails.setOtp(generatedOTP);
		otpDetails.setStatus(OtpStatusEnum.ACTIVE.getStatus());
		otpDetailsRepository.save(otpDetails);

		OtpMetaData otpMetaData = otpMetaDataRepository.findByCellphoneNumber(cellphoneNumber);

		int generateOtpCounter = 0;
		if (null == otpMetaData) {
			otpMetaData = new OtpMetaData();
			generateOtpCounter = 1;
		} else {
			generateOtpCounter = otpMetaData.getGenerateOtpCount() + 1;
		}
		otpMetaData.setGenerateOtpCount(generateOtpCounter);
		otpMetaData.setCellphoneNumber(cellphoneNumber);
		otpMetaData.setOtp(generatedOTP);
		otpMetaDataRepository.save(otpMetaData);
		addUserForVerification(emailAddress, cellphoneNumber);
		message(appCode,generatedOTP,emailAddress, cellphoneNumber);

		return OTPConstants.SUCCEEDED;
	}

	private void message(String appCode, String generatedOTP, String emailAddress, String cellphoneNumber){
		String emailMessage = "";
		String smsMessage = "";

		Optional<String> optEmailMessage = Optional.ofNullable(environment.getProperty("otp.email.messagecontent"));
		Optional<String> optSmsMessage = Optional.ofNullable(environment.getProperty("otp.sms.messagecontent"));

		if(optEmailMessage.isPresent() && optSmsMessage.isPresent()){
			emailMessage = optEmailMessage.get().replace("{}", generatedOTP);
			smsMessage = optSmsMessage.get().replace("{}", generatedOTP);
		}

		if(appCode != null){
			emailMessage = emailMessage.concat("\n\n").concat(appCode);
			smsMessage = "<#> APA Insurance: ".concat(smsMessage).concat("\n\n").concat(appCode);
		}

		sendSmsUsingInternalGateWay(cellphoneNumber, smsMessage);

		if(emailAddress != null) {
			sendEmailUsingInternalGateWay(emailAddress, emailMessage);
		}
	}

	@Override
	@Transactional
	public Boolean validateOtp(OtpRequest otpRequest) {
		try {
			OtpDetails otpDetails = otpDetailsRepository.findByCellphoneNumberAndOtp(otpRequest.getMobileNumber(), otpRequest.getOtp())
					.orElseThrow(() -> {
						LOG.error("couldn't find data for mobile number {} and Otp {}", otpRequest.getMobileNumber(),
								otpRequest.getOtp());
						return new OtpCustomException("ENTITY_NOT_FOUND", "Invalid Otp or mobile number");
					});
			return validateOtpForDetails(otpDetails, otpRequest.getEmailAddress());
		} catch (Exception e) {
			LOG.error("Invalid Otp or Mobile number");
			throw new OtpCustomException("Invalid Otp or Mobile number caused by", e);
		}
	}

	@Override
	public UserResponse verifyUser(String emailAddress){
		List<VerifiedUsers> userExists = verifiedUsersRepository.findByEmailAddress(emailAddress);
		boolean verified = true;
		String cellphoneNumber = "";

		if(!userExists.isEmpty()){
			verified = userExists.get(0).isVerified();
			cellphoneNumber = userExists.get(0).getCellphoneNumber();
		}

		UserResponse userResponse = new UserResponse();
		userResponse.setMobileNumber(cellphoneNumber);
		userResponse.setVerified(verified);

		return userResponse;
	}

	private Boolean validateOtpForDetails(OtpDetails otpDetails, String emailAddress) {
		boolean isValidOtp = OtpStatusEnum.ACTIVE.getStatus() == otpDetails.getStatus()
				&& LocalDateTime.now().isBefore(otpDetails.getExpiredTime());
		if (isValidOtp) {
			otpDetails.setStatus(OtpStatusEnum.USED.getStatus());
			otpDetailsRepository.save(otpDetails);

			if(emailAddress != null) {
				List<VerifiedUsers> optionalUser = verifiedUsersRepository.findByEmailAddress(emailAddress);
				if (!optionalUser.isEmpty()) {
					VerifiedUsers user = optionalUser.get(0);
					user.setVerified(true);
					verifiedUsersRepository.save(user);
				}
			}
		}
		return isValidOtp;
	}


}
