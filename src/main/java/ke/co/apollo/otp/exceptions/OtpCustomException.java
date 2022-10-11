package ke.co.apollo.otp.exceptions;

public class OtpCustomException extends RuntimeException {

	private static final long serialVersionUID = 1997753363232807009L;
	
	private String errorMessageKey;
	
	private String exceptionCode;
	
	private Object[] params;

	public OtpCustomException(String errorMessageKey, String message) {
		super(message);
		this.errorMessageKey=errorMessageKey;
	}

	public OtpCustomException(String errorMessageKey, String message, String exceptionCode , Object[] params) {
		super(message);
		this.errorMessageKey=errorMessageKey;
		this.exceptionCode = exceptionCode;
		this.params = params;
	}

	public OtpCustomException(Throwable cause) {
		super(cause);
	}

	public OtpCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public OtpCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}


	public String getExceptionCode() {
		return exceptionCode;
	}


	public Object[] getParams() {
		return params;
	}
	
	
}