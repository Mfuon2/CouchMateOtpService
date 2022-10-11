package ke.co.apollo.otp.config;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

public class Util {
    private Util() {}

    public static MultiValueMap getHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        headers.setAll(map);
        return headers;
    }
}
