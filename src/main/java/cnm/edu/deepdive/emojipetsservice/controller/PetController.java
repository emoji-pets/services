package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.PetRepository;
import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Pet.class)
@RequestMapping("/pets")
public class PetController {

  private PetRepository petRepository;

  @Autowired
  public PetController(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @GetMapping
  

}
