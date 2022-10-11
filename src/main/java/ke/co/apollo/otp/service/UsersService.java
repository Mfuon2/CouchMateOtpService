package ke.co.apollo.otp.service;

import ke.co.apollo.otp.entity.VerifiedUsers;

import java.util.List;


public interface UsersService {
    List<VerifiedUsers> retrieveAllUsers();
}
