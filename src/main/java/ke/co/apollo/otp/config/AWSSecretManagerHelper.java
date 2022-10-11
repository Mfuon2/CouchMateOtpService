package ke.co.apollo.otp.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;


public class AWSSecretManagerHelper {

    private AWSSecretManagerHelper() {
    }

    public static AWSSecretsManager buildClient(String region) {
        return AWSSecretsManagerClientBuilder.standard()
                .withRegion(region == null ? Regions.EU_WEST_1 : Regions.valueOf(region))
                .build();
    }

}
