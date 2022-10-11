package ke.co.apollo.otp.repository;


import java.util.List;
import java.util.Optional;

import ke.co.apollo.otp.entity.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails, Long> {

	List<OtpDetails> findByCellphoneNumber(String cellphoneNumber);

	Optional<OtpDetails> findByCellphoneNumberAndOtp(String cellphoneNumber, String otp);
}
