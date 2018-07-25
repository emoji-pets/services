package cnm.edu.deepdive.emojipetsservice.view;

import cnm.edu.deepdive.emojipetsservice.model.entity.FriendRequest;
import cnm.edu.deepdive.emojipetsservice.model.entity.Friendship;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import java.net.URI;
import java.util.List;

public interface Loner {

  long getId();

  String getDisplay_name();

  String getPet_name();

  URI getHref();

}
