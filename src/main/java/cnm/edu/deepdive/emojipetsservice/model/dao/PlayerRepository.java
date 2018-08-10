package cnm.edu.deepdive.emojipetsservice.model.dao;

import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * This is a PlayerRepository interface that extends the CrudRepository.
 * This just holds an abstract method that any class can inherit by implementing
 * this interface.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

  Optional<Player> findFirstByOauthId(String oauthId);

}
