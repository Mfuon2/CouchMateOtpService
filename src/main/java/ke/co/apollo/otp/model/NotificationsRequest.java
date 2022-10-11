package ke.co.apollo.otp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationsRequest {
    private String to;
    private String text;
    private String serviceType;
}
