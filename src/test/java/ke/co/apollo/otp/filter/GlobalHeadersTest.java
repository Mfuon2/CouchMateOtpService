package ke.co.apollo.otp.filter;

import java.util.ArrayList;
import java.util.List;

import ke.co.apollo.otp.model.UserResponse;
import ke.co.apollo.otp.service.implementation.UsersServiceImpl;
import ke.co.apollo.otp.controller.UsersController;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class GlobalHeadersTest {

    @Mock
    private UsersServiceImpl userService;

    @InjectMocks
    private UsersController userController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenFiltersCompleteMvcProcessesRequest() throws Exception {

        List<UserResponse> userList = new ArrayList<>();
        UserResponse user = getUserResponseObject();
        userList.add(user);
        Mockito.when(userService.retrieveAllUsers()).thenReturn(new ArrayList<>());

        standaloneSetup(userController)
                .addFilters(new GlobalHeaders()).build()
                .perform(get("/api/users/retrieve"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Content-Type-Options", "nosniff"))
                .andExpect(header().string("X-Frame-Options", "deny"))
                .andExpect(header().string("Content-Security-Policy", "script-src 'none'"))
        ;

    }

    private UserResponse getUserResponseObject() {
        UserResponse user = new UserResponse();
        user.setMobileNumber("+254702521028");
        user.setVerified(true);
        return user;
    }
}
