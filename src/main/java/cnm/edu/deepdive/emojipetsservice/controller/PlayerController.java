package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public  Iterable<Player> list() {
    return playerRepository.findAll();
  }
  public List<Player> listAll() {
    List<Player> players = new ArrayList<>();
    playerRepository.findAll().forEach(players::add);
    return players;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Player> post(@RequestBody Player player) {
    playerRepository.save(player);
    return ResponseEntity.created(player.getHref()).body(player);
  }

  @GetMapping("{player_id}")
  public Player get(@PathVariable("player_id") long id) {
    return playerRepository.findById(id).get();
  }

  // TODO Do this for all fields in player except friend ones and id

  // why don't we have to put this in { "courage_points": 100 }, it works with just the int

  @PutMapping(value = "{playerId}/couragePoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setCouragePointsJson(@PathVariable("playerId") long playerId, @RequestBody int couragePoints) {
    Player player = get(playerId);
    player.setCouragePoints(couragePoints);
    return playerRepository.save(player).getCouragePoints();
  }

  @PutMapping(value = "{playerId}/couragePoints", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setCouragePointsText(@PathVariable("playerId") long playerId, @RequestBody String couragePoints) {
    return Integer.toString(setCouragePointsJson(playerId, Integer.parseInt(couragePoints)));
  }

  @PutMapping(value = "{playerId}/couragePointsMax", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setCouragePointsMaxJson(@PathVariable("playerId") long playerId, @RequestBody int couragePointsMax) {
    Player player = get(playerId);
    player.setCouragePointsMax(couragePointsMax);
    return playerRepository.save(player).getCouragePointsMax();
  }

  @PutMapping(value = "{playerId}/couragePointsMax", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setCouragePointsMaxText(@PathVariable("playerId") long playerId, @RequestBody String couragePointsMax) {
    return Integer.toString(setCouragePointsMaxJson(playerId, Integer.parseInt(couragePointsMax)));
  }

  @PutMapping(value = "{playerId}/manaPoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setManaPointsJson(@PathVariable("playerId") long playerId, @RequestBody int manaPoints) {
    Player player = get(playerId);
    player.setCouragePoints(manaPoints);
    return playerRepository.save(player).getManaPoints();

  }

//  @PutMapping(value = "{absenceId}/excused", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public boolean setExcusedJson(@PathVariable("studentId") long studentId, @PathVariable("absenceId") long absenceId, @RequestBody boolean excused) {
//    Absence absence = get(studentId, absenceId);
//    absence.setExcused(excused);
//    return absenceRepository.save(absence).isExcused();
//  }
//
//  @PutMapping(value = "{absenceId}/excused", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
//  public String setExcusedText(@PathVariable("studentId") long studentId, @PathVariable("absenceId") long absenceId, @RequestBody String excused) {
//    return Boolean.toString(setExcusedJson(studentId, absenceId, Boolean.parseBoolean(excused)));
//  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound(){}

}
