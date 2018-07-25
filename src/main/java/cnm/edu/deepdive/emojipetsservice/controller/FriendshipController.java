package cnm.edu.deepdive.emojipetsservice.controller;

import cnm.edu.deepdive.emojipetsservice.model.dao.FriendshipRepository;
import cnm.edu.deepdive.emojipetsservice.model.dao.PlayerRepository;
import cnm.edu.deepdive.emojipetsservice.model.entity.Friendship;
import cnm.edu.deepdive.emojipetsservice.model.entity.Player;
import java.util.List;
import java.util.NoSuchElementException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Friendship.class)
@RequestMapping("/friendships")
public class FriendshipController {

  private FriendshipRepository friendshipRepository;
  private PlayerRepository playerRepository;
  private PlayerController playerController;

  @Autowired
  public FriendshipController(PlayerRepository playerRepository,
      FriendshipRepository friendshipRepository, PlayerController playerController) {
    this.friendshipRepository = friendshipRepository;
    this.playerRepository = playerRepository;
    this.playerController = playerController;
  }

  @GetMapping
  public List<Friendship> list(@PathVariable("playerId") long playerId) {
    return playerRepository.findById(playerId)
        .map(Player::getFriendships)
        .get();
  }

  @GetMapping("{player1_id}/{player2_id}")
  public Friendship getFriendship(
      @PathVariable("player1_id") long player1Id,
      @PathVariable("player2_id") long player2Id) {
    Player p1 = playerController.get(player1Id);
    Player p2 = playerController.get(player2Id);
    List<Friendship> friendships = playerRepository.findById(player1Id).
        get().
        getFriendships();
    Friendship p1p2 = new Friendship();
    for (Friendship friendship : friendships) {
      if (friendship.getPlayer1().equals(p1) && friendship.getPlayer2().equals(p2)) {
        p1p2 = friendship;
      }
    }
    return p1p2;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Friendship> post(
      @RequestBody Friendship friendship) {
    long p1Id = friendship.getTempPlayerId1();
    long p2Id = friendship.getTempPlayerId2();
    Player p1 = playerController.get(p1Id);
    Player p2 = playerController.get(p2Id);
    friendship.setPlayer1(p1);
    friendship.setPlayer2(p2);
    Friendship reciprocal = new Friendship();
    reciprocal.setPlayer1(friendship.getPlayer2());
    reciprocal.setPlayer2(friendship.getPlayer1());
    friendshipRepository.save(friendship);
    friendshipRepository.save(reciprocal);
    return ResponseEntity.created(friendship.getHref()).body(friendship);
  }

  @DeleteMapping("{player1_id}/{player2_id}") // @RequestMapping(method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteFriendship(@PathVariable("player1_id") long player1Id,
      @PathVariable("player2_id") long player2Id) {
    Friendship p1p2 = getFriendship(player1Id, player2Id);
    Friendship p2p1 = getFriendship(player2Id, player1Id);
    friendshipRepository.delete(p1p2);
    friendshipRepository.delete(p2p1);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound(){}

}
