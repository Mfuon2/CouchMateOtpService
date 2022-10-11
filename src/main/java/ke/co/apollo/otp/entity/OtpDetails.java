/**
 * 
 */
package ke.co.apollo.otp.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "otp_details")
public class OtpDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "CELLPHONENUMBER", length = 50)
    private String cellphoneNumber;
	
	@Column(name = "otp", length = 20)
	private String otp;
	
	@Column(name = "GENERATEDTIME")
	private LocalDateTime generatedTime;
	
	@Column(name = "EXPIREDTIME")
	private LocalDateTime expiredTime;
	
	@Column(name = "status")
	private Integer status;

	public String getCellphoneNumber() {
		return cellphoneNumber;
	}

	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(LocalDateTime generatedTime) {
		this.generatedTime = generatedTime;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	
	
}
