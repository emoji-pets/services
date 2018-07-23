package cnm.edu.deepdive.emojipetsservice.model.dao;

import cnm.edu.deepdive.emojipetsservice.model.entity.Friendship;
import org.springframework.data.repository.CrudRepository;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {

}
