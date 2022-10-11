package ke.co.apollo.otp.config;


import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Base64;
import java.util.Iterator;
import java.util.Properties;

@Configuration
public class SecretsConfigListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SecretsConfigListener.class);

    private AWSSecretsManager client;

    public SecretsConfigListener() {
        client = AWSSecretManagerHelper.buildClient("EU_WEST_1");
    }

    public String getSecret(String secretName) {

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (DecryptionFailureException e) {

            logger.error("Failed to Decrypt secret text");
            throw e;
        } catch (InternalServiceErrorException | InvalidParameterException | InvalidRequestException | ResourceNotFoundException e) {

            throw e;
        }

        if (getSecretValueResult.getSecretString() != null) {
            return getSecretValueResult.getSecretString();
        }
        else {
            return new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
        }
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String envName                      = environment.getProperty("spring.profiles.active");
        String secretName                   = envName.concat("-otp");
        Properties props                    = new Properties();
        String response                     = getSecret(secretName);

        logger.info("profile: {} ",  envName);
        logger.info("Get value for secret: {}", secretName);

        JSONObject jsonObject = new JSONObject(response);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()){
            String key      = keys.next();
            String value    = jsonObject.getString(key);
            props.put(key, value);
        }

        logger.info("Add properties to environment size:{}", props.size());
        environment.getPropertySources().addFirst(new PropertiesPropertySource("aws.secret.manager", props));

    }

}
