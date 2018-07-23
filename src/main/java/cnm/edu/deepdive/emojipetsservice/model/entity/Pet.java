package cnm.edu.deepdive.emojipetsservice.model.entity;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Pet {

  public static EntityLinks entityLinks;

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Pet.entityLinks = entityLinks;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pet_id", nullable = false, updatable = false)
  private long id;

  // TODO make this emoji only
  @Column(name = "pet_name", nullable = false, length = 100)
  private String petName;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("start DESC")
  private Player player;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Hunger hunger;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Love love;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Play play;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Potty potty;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Sleep sleep;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPetName() {
    return petName;
  }

  public void setPetName(String petName) {
    this.petName = petName;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Hunger getHunger() {
    return hunger;
  }

  public void setHunger(Hunger hunger) {
    this.hunger = hunger;
  }

  public Love getLove() {
    return love;
  }

  public void setLove(Love love) {
    this.love = love;
  }

  public Play getPlay() {
    return play;
  }

  public void setPlay(Play play) {
    this.play = play;
  }

  public Potty getPotty() {
    return potty;
  }

  public void setPotty(Potty potty) {
    this.potty = potty;
  }

  public Sleep getSleep() {
    return sleep;
  }

  public void setSleep(Sleep sleep) {
    this.sleep = sleep;
  }
}
