package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Player.class)
@RequestMapping("/players")
public class PlayerController {

  private PlayerRepository playerRepository;
  

}
