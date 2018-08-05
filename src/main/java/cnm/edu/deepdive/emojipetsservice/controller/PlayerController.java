package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.transaction.Transactional;
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

  @GetMapping("id/{player_id}")
  public Player get(@PathVariable("player_id") long id) {
    return playerRepository.findById(id).get();
  }

  @GetMapping("{player_oauthId}")
  public Player get(@PathVariable("player_oauthId") String oauthId) {
    Player player = playerRepository.findFirstByOauthId(oauthId).get();
    return player;
  }

  @PutMapping(value = "{player_oauthId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public Player putJson(@PathVariable("player_oauthId") String oauthId, @RequestBody Player playerIncomming) {
    Player player = playerRepository.findFirstByOauthId(oauthId).get();
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> map = objectMapper.convertValue(player, new TypeReference<Map<String, Object>>() {});
    map.putAll(objectMapper.convertValue(playerIncomming, new TypeReference<Map<String, Object>>() {}));
    Player mergedPlayer = objectMapper.convertValue(map, Player.class);
    return playerRepository.save(mergedPlayer);
  }

  @PutMapping(value = "{player_oauthId}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public Player put(@PathVariable("player_oauthId") String oauthId, @RequestBody Player playerIncomming) {
    return putJson(oauthId, playerIncomming);
  }

  @PostMapping("{player_oauthId}/follow/{other_player_oauthId}")
  public Player postFollow(
      @PathVariable String player_oauthId,
      @PathVariable String other_player_oauthId) {
    Player player1 = get(player_oauthId);
    Player player2 = get(other_player_oauthId);
    Set<Player> following = player1.getFollowing();
    Set<Player> followers = player2.getFollowers();
    following.add(player2);
    followers.add(player1);
    playerRepository.save(player1).getFollowing();
    playerRepository.save(player2).getFollowers();
    return player1;
  }

  @DeleteMapping("{playerId}/unfollow/{playerId2}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("playerId") long playerId, @PathVariable("playerId2") long playerId2) {
    Player player1 = get(playerId);
    Player player2 = get(playerId2);
    Set<Player> following = player1.getFollowing();
    Set<Player> followers = player2.getFollowers();
    following.remove(player2);
    followers.remove(player1);
    playerRepository.save(player1).getFollowing();
    playerRepository.save(player2).getFollowers();
  }

  @Transactional
  @DeleteMapping("{playerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("playerId") long playerId) {
    Player player = playerRepository.findById(playerId).get();
    Set<Player> following = player.getFollowing();
    for (Player followed : following) {
      followed.getFollowers().remove(player);
    }
    Set<Player> followers = player.getFollowers();
    for (Player follower : followers) {
      follower.getFollowing().remove(player);
    }
    playerRepository.saveAll(following);
    playerRepository.saveAll(followers);
    playerRepository.delete(player);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
