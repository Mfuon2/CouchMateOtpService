package ke.co.apollo.otp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping(value = {""})
    public String index(Model model) {
        return "OK";
    }
}
