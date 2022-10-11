/**
 * 
 */
package ke.co.apollo.otp.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "otp_meta_data")
public class OtpMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "CELLPHONENUMBER", length = 50)
    private String cellphoneNumber;
	
	@Column(name = "otp", length = 20)
	private String otp;
	
	@Column(name = "TOTALATTEMPTS")
	private Integer totalAttempts;
	
	@Column(name = "GENERATEDOTPCOUNT")
	private Integer generateOtpCount;
	
	@Column(name = "updatedon")
	private LocalDateTime updatedOn;

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

	public Long getId() {
		return id;
	}
	
	public Integer getTotalAttempts() {
		return totalAttempts;
	}

	public void setTotalAttempts(Integer totalAttempts) {
		this.totalAttempts = totalAttempts;
	}

	public Integer getGenerateOtpCount() {
		return generateOtpCount;
	}

	public void setGenerateOtpCount(Integer generateOtpCount) {
		this.generateOtpCount = generateOtpCount;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	@PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
