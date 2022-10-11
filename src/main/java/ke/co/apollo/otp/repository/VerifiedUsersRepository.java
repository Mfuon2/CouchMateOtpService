package ke.co.apollo.otp.repository;

import ke.co.apollo.otp.entity.VerifiedUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerifiedUsersRepository extends JpaRepository<VerifiedUsers, Long> {
    List<VerifiedUsers> findByEmailAddress(String emailAddress);
}
