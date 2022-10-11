package ke.co.apollo.otp.controller.advice;

import static org.junit.Assert.assertNotNull;

import ke.co.apollo.otp.exceptions.OtpCustomException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class OtpControllerTest {
	
	@InjectMocks
	private OtpAdviceController otpAdviceController;
	
	@Test
	public void testShouldKnowHowToCatchOtpCustomException () {
		OtpCustomException otpCustomException = new OtpCustomException("INVALID MOBILE NUMBER", "Invalid Mobile Number");
		
		ModelAndView modelAndView = otpAdviceController.applicationExceptionAdvice(otpCustomException);
		
		assertNotNull(modelAndView);
		assertNotNull(modelAndView.getView());
	}

    @Test
    public void testShouldKnowHowToCatchOtpCustomExceptionWithNoErrorKey () {
        OtpCustomException otpCustomException = new OtpCustomException(null, "Invalid Mobile Number");

        ModelAndView modelAndView = otpAdviceController.applicationExceptionAdvice(otpCustomException);

        assertNotNull(modelAndView);
        assertNotNull(modelAndView.getView());
    }
	
	@Test
	public void testShouldKnowHowToHowToHandleWhenCustomExceptionIsNull () {
		OtpCustomException otpCustomException = null;
		
		ModelAndView modelAndView = otpAdviceController.applicationExceptionAdvice(otpCustomException);
		
		assertNotNull(modelAndView);
		assertNotNull(modelAndView.getView());
	}

}
