package ke.co.apollo.otp.model;

import java.io.Serializable;
import java.util.Objects;

public class OtpResponseStatus implements Serializable {

	private static final long serialVersionUID = 3471208720236627952L;

	private Integer status = null;

	private String description = null;

	private transient Object errorData = null;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getErrorData() {
		return errorData;
	}

	public void setErrorData(Object errorData) {
		this.errorData = errorData;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OtpResponseStatus responseStatus = (OtpResponseStatus) o;
		return Objects.equals(this.status, responseStatus.status)
				&& Objects.equals(this.description, responseStatus.description)
				&& Objects.equals(this.errorData, responseStatus.errorData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, description, errorData);
	}
	
	public enum OTPResponseStatusCode {
		ERROR(0),
		SUCCESS(1),
		WARNING(2);
		
		private int value;

		private OTPResponseStatusCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}
}
