package cnm.edu.deepdive.emojipetsservice.model.dao;

import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

  Optional<Player> findFirstByOauthId(String oauthId);

}
