package ke.co.apollo.otp.service;

import ke.co.apollo.otp.model.OtpRequest;
import ke.co.apollo.otp.model.UserResponse;

public interface OtpDetailsService {

	String generateOtp(String mobileNumber, String appCode, String emailAddress);

	String reGenerateOtp(String mobileNumber, String appCode, String emailAddress);

	Boolean validateOtp(OtpRequest otpRequest);

	UserResponse verifyUser(String emailAddress);
}
