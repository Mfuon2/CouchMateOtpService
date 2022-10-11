package ke.co.apollo.otp.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class OtpResponseStatusTest {

	@Test
	public void testOtpResponseStatusSetNGet() {
		OtpResponseStatus otpResponseStatus = new OtpResponseStatus();
		String description = "description";
		Object errorData = new Object();
		Integer status = 200;
		otpResponseStatus.setDescription(description);
		otpResponseStatus.setErrorData(errorData);
		otpResponseStatus.setStatus(status);
		assertEquals(description, otpResponseStatus.getDescription());
		assertEquals(errorData, otpResponseStatus.getErrorData());
		assertEquals(status, otpResponseStatus.getStatus());
	}

	@Test
	public void testEqualsAndHashCode() {
		String description = "description";
		Object errorData = new Object();
		Integer status = 200;
		OtpResponseStatus otpResponseStatus1 = new OtpResponseStatus();
		OtpResponseStatus otpResponseStatus2 = new OtpResponseStatus();
		OtpResponseStatus otpResponseStatus3 = new OtpResponseStatus();
		otpResponseStatus1.setDescription(description);
		otpResponseStatus1.setErrorData(errorData);
		otpResponseStatus1.setStatus(status);
		otpResponseStatus2.setDescription(description);
		otpResponseStatus2.setErrorData(errorData);
		otpResponseStatus3.setStatus(status);
		otpResponseStatus3.setDescription(description);
		otpResponseStatus3.setErrorData(errorData);
		otpResponseStatus3.setStatus(status);
		assertEquals(otpResponseStatus1.hashCode(), otpResponseStatus3.hashCode());
		assertEquals(otpResponseStatus1, otpResponseStatus1);
		assertNotEquals(otpResponseStatus3, otpResponseStatus2);
		assertEquals(otpResponseStatus3, otpResponseStatus1);
		assertNotEquals(otpResponseStatus1,otpResponseStatus2);
		assertNotEquals(otpResponseStatus1, null);
		assertNotEquals(otpResponseStatus1, new Object());
	}

	@Test
	public void testEqualsMethodCompletely(){
		String description = "description";
		String anotherDescription = "description 2";
		Object errorData = new Object();
		Integer status = 200;

		OtpResponseStatus otpResponseStatus1 = new OtpResponseStatus();
		OtpResponseStatus otpResponseStatus2 = new OtpResponseStatus();

		otpResponseStatus1.setStatus(status);
		otpResponseStatus2.setStatus(status);

		otpResponseStatus1.setDescription(description);
		otpResponseStatus2.setDescription(anotherDescription);

		otpResponseStatus1.setErrorData(errorData);
		otpResponseStatus2.setErrorData(errorData);

		assertNotEquals(otpResponseStatus1,otpResponseStatus2);
	}

    @Test
    public void testEqualsMethodForDifferentErrorDataCompletely(){
        String description = "description";
        Object errorData = new Object();
        Object anotherErrorData = new Object();

        Integer status = 200;

        OtpResponseStatus otpResponseStatus1 = new OtpResponseStatus();
        OtpResponseStatus otpResponseStatus2 = new OtpResponseStatus();

        otpResponseStatus1.setStatus(status);
        otpResponseStatus2.setStatus(status);

        otpResponseStatus1.setDescription(description);
        otpResponseStatus2.setDescription(description);

        otpResponseStatus1.setErrorData(errorData);
        otpResponseStatus2.setErrorData(anotherErrorData);

        assertNotEquals(otpResponseStatus1,otpResponseStatus2);
    }

}