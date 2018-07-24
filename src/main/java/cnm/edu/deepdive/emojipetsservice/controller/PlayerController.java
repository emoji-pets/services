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
  public Iterable<Player> list() {
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

  // why don't we have to put this in { "courage_points": 100 }, it works with just the int

  @PutMapping(value = "{playerId}/couragePoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setCouragePointsJson(@PathVariable("playerId") long playerId,
      @RequestBody int couragePoints) {
    Player player = get(playerId);
    player.setCouragePoints(couragePoints);
    return playerRepository.save(player).getCouragePoints();
  }

  @PutMapping(value = "{playerId}/couragePoints", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setCouragePointsText(@PathVariable("playerId") long playerId,
      @RequestBody String couragePoints) {
    return Integer.toString(setCouragePointsJson(playerId, Integer.parseInt(couragePoints)));
  }

  @PutMapping(value = "{playerId}/couragePointsMax", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setCouragePointsMaxJson(@PathVariable("playerId") long playerId,
      @RequestBody int couragePointsMax) {
    Player player = get(playerId);
    player.setCouragePointsMax(couragePointsMax);
    return playerRepository.save(player).getCouragePointsMax();
  }

  @PutMapping(value = "{playerId}/couragePointsMax", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setCouragePointsMaxText(@PathVariable("playerId") long playerId,
      @RequestBody String couragePointsMax) {
    return Integer.toString(setCouragePointsMaxJson(playerId, Integer.parseInt(couragePointsMax)));
  }

  @PutMapping(value = "{playerId}/manaPoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setManaPointsJson(@PathVariable("playerId") long playerId,
      @RequestBody int manaPoints) {
    Player player = get(playerId);
    player.setManaPoints(manaPoints);
    return playerRepository.save(player).getManaPoints();
  }

  @PutMapping(value = "{playerId}/manaPoints", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setManaPointsText(@PathVariable("playerId") long playerId,
      @RequestBody String manaPoints) {
    return Integer.toString(setManaPointsJson(playerId, Integer.parseInt(manaPoints)));
  }

  @PutMapping(value = "{playerId}/manaPointsMax", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setManaPointsMaxJson(@PathVariable("playerId") long playerId,
      @RequestBody int manaPointsMax) {
    Player player = get(playerId);
    player.setManaPointsMax(manaPointsMax);
    return playerRepository.save(player).getManaPointsMax();
  }

  @PutMapping(value = "{playerId}/manaPointsMax", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setManaPointsMaxText(@PathVariable("playerId") long playerId,
      @RequestBody String manaPointsMax) {
    return Integer.toString(setManaPointsMaxJson(playerId, Integer.parseInt(manaPointsMax)));
  }

  @PutMapping(value = "{playerId}/healthPoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setHealthPointsJson(@PathVariable("playerId") long playerId,
      @RequestBody int healthPoints) {
    Player player = get(playerId);
    player.setHealthPoints(healthPoints);
    return playerRepository.save(player).getHealthPoints();
  }

  @PutMapping(value = "{playerId}/healthPoints", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setHealthPointsText(@PathVariable("playerId") long playerId,
      @RequestBody String healthPoints) {
    return Integer.toString(setHealthPointsJson(playerId, Integer.parseInt(healthPoints)));
  }

  @PutMapping(value = "{playerId}/healthPointsMax", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setHealthPointsMaxJson(@PathVariable("playerId") long playerId,
      @RequestBody int healthPointsMax) {
    Player player = get(playerId);
    player.setHealthPointsMax(healthPointsMax);
    return playerRepository.save(player).getHealthPointsMax();
  }

  @PutMapping(value = "{playerId}/healthPointsMax", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setHealthPointsMaxText(@PathVariable("playerId") long playerId,
      @RequestBody String healthPointsMax) {
    return Integer.toString(setHealthPointsMaxJson(playerId, Integer.parseInt(healthPointsMax)));
  }

  @PutMapping(value = "{playerId}/powerPoints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setPowerPointsJson(@PathVariable("playerId") long playerId,
      @RequestBody int powerPoints) {
    Player player = get(playerId);
    player.setPowerPoints(powerPoints);
    return playerRepository.save(player).getPowerPoints();
  }

  @PutMapping(value = "{playerId}/powerPoints", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setPowerPointsText(@PathVariable("playerId") long playerId,
      @RequestBody String powerPoints) {
    return Integer.toString(setPowerPointsJson(playerId, Integer.parseInt(powerPoints)));
  }

  @PutMapping(value = "{playerId}/powerPointsMax", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setPowerPointsMaxJson(@PathVariable("playerId") long playerId,
      @RequestBody int powerPointsMax) {
    Player player = get(playerId);
    player.setPowerPointsMax(powerPointsMax);
    return playerRepository.save(player).getPowerPointsMax();
  }

  @PutMapping(value = "{playerId}/powerPointsMax", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setPowerPointsMaxText(@PathVariable("playerId") long playerId,
      @RequestBody String powerPointsMax) {
    return Integer.toString(setPowerPointsMaxJson(playerId, Integer.parseInt(powerPointsMax)));
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
  public void notFound() {
  }

}
