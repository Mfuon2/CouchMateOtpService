package ke.co.apollo.otp.entity;

import javax.persistence.*;

@Entity
@Table(name = "verified_users")
public class VerifiedUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAILADDRESS")
    private String emailAddress;

    @Column(name = "CELLPHONENUMBER")
    private String cellphoneNumber;

    @Column(name = "ISVERIFIED")
    private boolean isVerified;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }
}
