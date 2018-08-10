package cnm.edu.deepdive.emojipetsservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * This is the EmojiPetsConfiguration class.
 * This holds the clienId.
 * This class also has a @Configuration annotation which is an analog for xml file.
 * This annotation is required when you don't pass the annotated class explicitly.
 */
@Configuration
public class EmojiPetsConfiguration {

  @Value("${oauth.clientId}")
  private String clientId;

}
