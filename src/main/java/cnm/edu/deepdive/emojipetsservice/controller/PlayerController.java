package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import cnm.edu.deepdive.emojipetsservice.model.pojo.FollowConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @PutMapping(value = "{playerId}/display_name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setDisplay_nameJson(@PathVariable("playerId") long playerId, @RequestBody String display_name) {
    Player player = get(playerId);
    player.setDisplay_name(display_name);
    return playerRepository.save(player).getDisplay_name();
  }

  @PutMapping(value = "{playerId}/display_name", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setDisplay_nameText(@PathVariable("playerId") long playerId, @RequestBody String display_name) {
    return setDisplay_nameJson(playerId, display_name);
  }

  @PutMapping(value = "{playerId}/pet_name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setPet_nameJson(@PathVariable("playerId") long playerId, @RequestBody String pet_name) {
    Player player = get(playerId);
    player.setPet_name(pet_name);
    return playerRepository.save(player).getPet_name();
  }

  @PutMapping(value = "{playerId}/pet_name", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String setPet_nameText(@PathVariable("playerId") long playerId,
      @RequestBody String pet_name) {
    return setPet_nameJson(playerId, pet_name);
  }

  @PutMapping(value = "{playerId}/timeStamp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public long setTimeStampJson(@PathVariable("playerId") long playerId, @RequestBody long timeStamp) {
    Player player = get(playerId);
    player.setTimeStamp(timeStamp);
    return playerRepository.save(player).getTimeStamp();
  }

  @PutMapping(value = "{playerId}/timeStamp", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public long setTimeStamp(@PathVariable("playerId") long playerId, @RequestBody long timeStamp) {
    return setTimeStampJson(playerId, timeStamp);
  }

  @PutMapping(value = "{playerId}/level", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setLevelJson(@PathVariable("playerId") long playerId, @RequestBody int level) {
    Player player = get(playerId);
    player.setLevel(level);
    return playerRepository.save(player).getLevel();
  }

  @PutMapping(value = "{playerId}/level", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public int setLeve(@PathVariable("playerId") long playerId, @RequestBody int level) {
    return setLevelJson(playerId, level);
  }

  @PutMapping(value = "{playerId}/xp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setXpJson(@PathVariable("playerId") long playerId, @RequestBody int xp) {
    Player player = get(playerId);
    player.setXp(xp);
    return playerRepository.save(player).getXp();
  }

  @PutMapping(value = "{playerId}/xp", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public int setXp(@PathVariable("playerId") long playerId,
      @RequestBody int xp) {
    return setXpJson(playerId, xp);
  }

  @PutMapping(value = "{playerId}/maxXp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public int setMaxXpJson(@PathVariable("playerId") long playerId, @RequestBody int maxXp) {
    Player player = get(playerId);
    player.setMaxXp(maxXp);
    return playerRepository.save(player).getMaxXp();
  }

  @PutMapping(value = "{playerId}/maxXp", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public int setMaxXp(@PathVariable("playerId") long playerId,
      @RequestBody int maxXp) {
    return setMaxXpJson(playerId, maxXp);
  }

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

  @PostMapping(value = "{playerId}/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FollowConnection> post(
      @PathVariable("playerId") long playerId,
      @RequestBody FollowConnection followConnection) {
    long p2 = followConnection.getPlayer2_id();
    Player player1 = get(playerId);
    Player player2 = get(p2);
    Set<Player> following = player1.getFollowing();
    Set<Player> followers = player2.getFollowers();
    following.add(player2);
    followers.add(player1);
    playerRepository.save(player1).getFollowing();
    playerRepository.save(player2).getFollowers();
    return ResponseEntity.created(get(playerId).getHref()).body(followConnection);
  }

  @DeleteMapping("{playerId}/unfollow")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("playerId") long playerId, @RequestBody FollowConnection followConnection) {
    long p2 = followConnection.getPlayer2_id();
    Player player1 = get(playerId);
    Player player2 = get(p2);
    Set<Player> following = player1.getFollowing();
    Set<Player> followers = player2.getFollowers();
    following.remove(player2);
    followers.remove(player1);
    playerRepository.save(player1).getFollowing();
    playerRepository.save(player2).getFollowers();
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}
