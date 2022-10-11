package ke.co.apollo.otp.service.implementation;

import ke.co.apollo.otp.entity.VerifiedUsers;
import ke.co.apollo.otp.repository.VerifiedUsersRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceImplTest {

    @Mock
    private VerifiedUsersRepository verifiedUsersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp(){
        when(verifiedUsersRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testShouldRetrieveAllTheUsers(){
        List<VerifiedUsers> verifiedUsers = usersService.retrieveAllUsers();

        assertEquals(0, verifiedUsers.size());
    }
}
