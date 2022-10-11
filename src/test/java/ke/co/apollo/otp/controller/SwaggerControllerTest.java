package ke.co.apollo.otp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class SwaggerControllerTest {

    @Test
    void indexTest() {
        Model model = mock(Model.class);
        SwaggerController healthCheckController = new SwaggerController();
        assertEquals("/index.html", healthCheckController.index(model));
    }
}