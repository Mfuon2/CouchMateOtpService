package ke.co.apollo.otp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		RequestMethod.GET.toString();
		registry.addMapping("/**").allowedOrigins("*").allowedMethods(
				RequestMethod.HEAD.toString(),
				RequestMethod.OPTIONS.toString(), 
				RequestMethod.GET.toString(), 
				RequestMethod.POST.toString(), 
				RequestMethod.PUT.toString(), 
				RequestMethod.PATCH.toString(), 
				RequestMethod.DELETE.toString())
				.maxAge(MAX_AGE_SECS);
	}
}