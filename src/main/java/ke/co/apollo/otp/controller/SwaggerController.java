package ke.co.apollo.otp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping(value = {"/swagger"})
    public String index(Model model) {
        return "/index.html";
    }
}
