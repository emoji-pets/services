package cnm.edu.deepdive.emojipetsservice.model.dao;

import cnm.edu.deepdive.emojipetsservice.model.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
