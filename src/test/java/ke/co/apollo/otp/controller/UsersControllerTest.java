package ke.co.apollo.otp.controller;

import ke.co.apollo.otp.entity.VerifiedUsers;
import ke.co.apollo.otp.service.implementation.UsersServiceImpl;
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
public class UsersControllerTest {
    @Mock
    private UsersServiceImpl usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp(){
        when(usersService.retrieveAllUsers()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testShouldRetrieveAllTheUsers(){
        List<VerifiedUsers> verifiedUsers = usersController.retrieveAllUsers();

        assertEquals(0, verifiedUsers.size());
    }

}
