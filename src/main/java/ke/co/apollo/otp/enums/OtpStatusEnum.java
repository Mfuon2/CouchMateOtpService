package ke.co.apollo.otp.enums;

public enum OtpStatusEnum {
	
	ACTIVE(0), USED(1);
	
	private int status;
	
	OtpStatusEnum (int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public static OtpStatusEnum getLinkStatusByStatus(int status) {
		for(OtpStatusEnum lStatus: OtpStatusEnum.values()) {
			if(lStatus.getStatus() == status) {
				return lStatus;
			}
		}
		return null;  // not found
	}
}
