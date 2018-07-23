package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Player.class)
@RequestMapping("/players")
public class PlayerController {

  private PlayerRepository playerRepository;

  @Autowired
  public PlayerController(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @GetMapping
  public Iterable<Player> list() {
    return playerRepository
  }

}
