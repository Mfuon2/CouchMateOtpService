package ke.co.apollo.otp.controller;

import io.swagger.annotations.Api;
import ke.co.apollo.otp.entity.VerifiedUsers;
import ke.co.apollo.otp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api("Users APIs")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/retrieve", produces = "application/json")
    public List<VerifiedUsers> retrieveAllUsers(){
        return usersService.retrieveAllUsers();
    }
}
