/**
 * 
 */
package ke.co.apollo.otp.repository;

import ke.co.apollo.otp.entity.OtpMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpMetaDataRepository extends JpaRepository<OtpMetaData, Long> {

	OtpMetaData findByCellphoneNumber(String cellphoneNumber);

}
