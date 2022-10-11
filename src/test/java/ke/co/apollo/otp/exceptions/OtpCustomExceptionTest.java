package ke.co.apollo.otp.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class OtpCustomExceptionTest {

	@Test
	public void testShouldKnowHowToPopulateDataUsingConstructors() {
		
		OtpCustomException otpCustomException = new OtpCustomException("INVALID_NUMBER", "invalid number");
		assertNotNull(otpCustomException.getErrorMessageKey());
		
		
		OtpCustomException customException = new OtpCustomException(new Exception("Invalid Id"));
		assertNotNull(customException.getMessage());
		
		OtpCustomException customExceptionWithMessage = new OtpCustomException("Invalid Id", new Exception("Invalid Id"));
		assertEquals("Invalid Id", customExceptionWithMessage.getMessage());
		
		OtpCustomException customExceptionWithSuppression = new OtpCustomException("Invalid Id", new Exception("Invalid Id"), true, true);
		assertEquals("Invalid Id", customExceptionWithSuppression.getMessage());
		
		OtpCustomException customExceptionWithExceptionCode = new OtpCustomException("INVALID_ID", "Invalid Id", "EXCEP_CODE", new Object[2]);
		assertNotNull(customExceptionWithExceptionCode);
		assertNotNull(customExceptionWithExceptionCode.getExceptionCode());
		assertNotNull(customExceptionWithExceptionCode.getParams());
	}
}
