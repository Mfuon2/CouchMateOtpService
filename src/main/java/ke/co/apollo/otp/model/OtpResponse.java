package ke.co.apollo.otp.model;

import java.io.Serializable;

public class OtpResponse implements Serializable {

    private static final long serialVersionUID = 2715243023500551820L;

    private transient Object data;

    private OtpResponseStatus responseStatus = null;

    public OtpResponse() {
    }

    public OtpResponse(Object data, OtpResponseStatus responseStatus) {
        this.data = data;
        this.responseStatus = responseStatus;
    }

    public OtpResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(OtpResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}