package ke.co.apollo.otp.service.implementation;

import ke.co.apollo.otp.entity.VerifiedUsers;
import ke.co.apollo.otp.repository.VerifiedUsersRepository;
import ke.co.apollo.otp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private VerifiedUsersRepository verifiedUsersRepository;

    @Override
    public List<VerifiedUsers> retrieveAllUsers() {
        return verifiedUsersRepository.findAll();
    }
}
