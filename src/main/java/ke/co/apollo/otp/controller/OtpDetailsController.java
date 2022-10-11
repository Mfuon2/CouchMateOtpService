package ke.co.apollo.otp.controller;

import io.swagger.annotations.Api;
import ke.co.apollo.otp.constant.OTPConstants;
import ke.co.apollo.otp.model.OtpRequest;
import ke.co.apollo.otp.model.OtpResponse;
import ke.co.apollo.otp.model.OtpResponseStatus;
import ke.co.apollo.otp.model.UserResponse;
import ke.co.apollo.otp.service.OtpDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@Api("OTP APIs")

@CrossOrigin(origins = {"https://dev.customerportal.apainsurance.ke", "https://uat.customerportal.apainsurance.ke", "https://customerportal.apainsurance.org"})
public class OtpDetailsController {
	private static final Logger LOG = LoggerFactory.getLogger(OtpDetailsController.class);

	@Autowired
	private OtpDetailsService otpDetailsService;

	@PostMapping(value = "/generate", produces = "application/json")
	public OtpResponse generateOtp(@RequestBody OtpRequest otpRequest) {
		LOG.info("Generate Otp");
		String response = otpDetailsService.generateOtp(otpRequest.getMobileNumber(), otpRequest.getAppCode(), otpRequest.getEmailAddress());
		return restAPICustomResponse(response, OTPConstants.SUCCEEDED);
	}

	@PostMapping(value = "/regenerate", produces = "application/json")
	public OtpResponse reGenerateOtp(@RequestBody OtpRequest otpRequest) {
		LOG.info("Regenerate Otp");
		String response = otpDetailsService.reGenerateOtp(otpRequest.getMobileNumber(), otpRequest.getAppCode(), otpRequest.getEmailAddress());
		return restAPICustomResponse(response, OTPConstants.SUCCEEDED);
	}

	@PostMapping(value = "/validate", produces = "application/json")
	public OtpResponse validateOtp(@RequestBody OtpRequest otpRequest) {
		LOG.info("validate Otp");
		Boolean isValid = otpDetailsService.validateOtp(otpRequest);
		return restAPICustomResponse(isValid,
				Boolean.TRUE.equals(isValid)? OTPConstants.VALID_OTP : OTPConstants.NOT_VALID_OTP);
	}

	@PostMapping(value = "/verify", produces = "application/json")
	public UserResponse verifyUser(@RequestBody OtpRequest otpRequest){
		LOG.info("verify user");
		return otpDetailsService.verifyUser(otpRequest.getEmailAddress());
	}

	private OtpResponse restAPICustomResponse(Object data, String description) {
		LOG.debug("Constructing Custom Response");
		OtpResponse customResponse = new OtpResponse();
		customResponse.setData(data);
		OtpResponseStatus responseStatus = new OtpResponseStatus();
		responseStatus.setStatus(OtpResponseStatus.OTPResponseStatusCode.SUCCESS.getValue());
		responseStatus.setDescription(description);
		customResponse.setResponseStatus(responseStatus);
		return customResponse;
	}
}
