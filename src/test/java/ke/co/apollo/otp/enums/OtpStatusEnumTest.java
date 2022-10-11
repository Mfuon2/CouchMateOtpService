package ke.co.apollo.otp.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class OtpStatusEnumTest {

	@Test
	public void testKnowHowToGetOtpStatusByStatus() {
		
		OtpStatusEnum otpStatusEnum = OtpStatusEnum.getLinkStatusByStatus(1);
		assertEquals(OtpStatusEnum.USED, otpStatusEnum);
		
		OtpStatusEnum otpStatusEnumNullCheck = OtpStatusEnum.getLinkStatusByStatus(5);
		assertNull(otpStatusEnumNullCheck);
	}
}
