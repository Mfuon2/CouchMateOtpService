package ke.co.apollo.otp.model;

public class OtpRequest {

	private String otp;
	private String mobileNumber;
	private String emailAddress;
	private String appCode;

	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getMobileNumber() { return mobileNumber; }
	public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

	public String getAppCode() { return appCode; }

	public void setAppCode(String appCode) { this.appCode = appCode; }

	public String getEmailAddress() { return emailAddress; }

	public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}
