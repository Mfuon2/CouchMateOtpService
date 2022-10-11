package ke.co.apollo.otp.controller.advice;

import java.util.HashMap;
import java.util.Map;

import ke.co.apollo.otp.exceptions.OtpCustomException;
import ke.co.apollo.otp.model.OtpResponseStatus;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ControllerAdvice
public class OtpAdviceController {
private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OtpAdviceController.class);
	
	private static final String APPLICATION_EXCEPTION_OCCURED = "Oops! Something went wrong!";


	@ResponseStatus(value = HttpStatus.OK, reason = "")
	@ExceptionHandler(OtpCustomException.class)
	public ModelAndView applicationExceptionAdvice(OtpCustomException otpCustomException) {
		LOGGER.error("Business exception occured");
		LOGGER.error("Exception Occured :", otpCustomException);
		
		String messageStr;
		if(otpCustomException!=null && otpCustomException.getErrorMessageKey() != null) {
			LOGGER.debug("extract message from MTDBuisnessException");
			messageStr = otpCustomException.getMessage();
		}
		else
		{
			messageStr = APPLICATION_EXCEPTION_OCCURED;
		}
		
		OtpResponseStatus responseStatus = new OtpResponseStatus();
		responseStatus.setStatus(OtpResponseStatus.OTPResponseStatusCode.ERROR.getValue());
		responseStatus.setDescription(messageStr);
		responseStatus.setErrorData("Failure");
		
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		Map<String, Object> errorMap = new HashMap<> ();
		errorMap.put("responseStatus", responseStatus);
		errorMap.put("data", null);
		view.setAttributesMap(errorMap);
		return new ModelAndView(view);
	}
}
