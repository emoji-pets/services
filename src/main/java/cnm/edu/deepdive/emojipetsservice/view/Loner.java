package cnm.edu.deepdive.emojipetsservice.view;

import java.net.URI;

/**
 * This is the Loner interface.
 */
public interface Loner {

  long getId();

  String getOauthId();

  String getDisplay_name();

  String getPet_name();

  String getPet_emoji();

  String getStatus();

  String getWall();

  URI getHref();

}
