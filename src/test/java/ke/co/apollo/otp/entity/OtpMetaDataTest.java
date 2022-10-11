package ke.co.apollo.otp.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class OtpMetaDataTest {

	@Test
	public void testShouldBeAbleToTestGettersAndSetters() {
		OtpMetaData otpMetaData = new OtpMetaData();
		
		assertNull(otpMetaData.getCellphoneNumber());
		assertNull(otpMetaData.getGenerateOtpCount());
		assertNull(otpMetaData.getId());
		assertNull(otpMetaData.getOtp());
		assertNull(otpMetaData.getTotalAttempts());
		assertNull(otpMetaData.getUpdatedOn());

		otpMetaData.setCellphoneNumber("123456789");
		otpMetaData.setGenerateOtpCount(2);
		otpMetaData.setOtp("12345");
		otpMetaData.setTotalAttempts(2);
		otpMetaData.preUpdate();
		
		assertNotNull(otpMetaData.getCellphoneNumber());
		assertNotNull(otpMetaData.getGenerateOtpCount());
		assertNotNull(otpMetaData.getOtp());
		assertNotNull(otpMetaData.getTotalAttempts());
		assertNotNull(otpMetaData.getUpdatedOn());
	}
}
