package cnm.edu.deepdive.emojipetsservice.model.entity;

import cnm.edu.deepdive.emojipetsservice.view.Loner;
import cnm.edu.deepdive.emojipetsservice.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties()
@Component
@Entity
public class Friendship {

  public static EntityLinks entityLinks;

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Friendship.entityLinks = entityLinks;
  }

  @Id // makes primary key
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "friendship_id", nullable = false, updatable = false)
  private long id;

  // TODO player to player id map
  @JsonSerialize(as = Loner.class)
  @ManyToOne
  @JoinColumn(name = "player1_id", nullable = false)
  private Player player1;

  @JsonSerialize(as = Loner.class)
  @ManyToOne
  @JoinColumn(name = "player2_id", nullable = false)
  private Player player2;


  @Transient
  private long tempPlayerId1;

  @Transient
  private long tempPlayerId2;

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  public long getId() {
    return id;
  }

  @JsonIgnore
  public long getTempPlayerId1() {
    return tempPlayerId1;
  }

  @JsonProperty("player1_id")
  public void setTempPlayerId1(long tempPlayerId1) {
    this.tempPlayerId1 = tempPlayerId1;
  }

  @JsonIgnore
  public long getTempPlayerId2() {
    return tempPlayerId2;
  }

  @JsonProperty("player2_id")
  public void setTempPlayerId2(long tempPlayerId2) {
    this.tempPlayerId2 = tempPlayerId2;
  }

  public URI getHref() {
    return entityLinks.linkForSingleResource(Friendship.class, id).toUri();
  }
}
