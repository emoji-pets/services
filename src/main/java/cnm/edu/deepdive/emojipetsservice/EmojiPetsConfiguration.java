package cnm.edu.deepdive.emojipetsservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class EmojiPetsConfiguration {

  @Value("${oauth.clientId}")
  private String clientId;

}
