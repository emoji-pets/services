package cnm.edu.deepdive.emojipetsservice.model.entity;

import java.util.Date;
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
  private Player player;

  private int hunger;
  private Date hungerDate;

  private int love;
  private Date loveDate;

  private int play;
  private Date playDate;

  private int potty;
  private Date pottyDate;

  private int sleep;
  private Date sleepDate;

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

  public int getHunger() {
    return hunger;
  }

  public void setHunger(int hunger) {
    this.hunger = hunger;
  }

  public Date getHungerDate() {
    return hungerDate;
  }

  public void setHungerDate(Date hungerDate) {
    this.hungerDate = hungerDate;
  }

  public int getLove() {
    return love;
  }

  public void setLove(int love) {
    this.love = love;
  }

  public Date getLoveDate() {
    return loveDate;
  }

  public void setLoveDate(Date loveDate) {
    this.loveDate = loveDate;
  }

  public int getPlay() {
    return play;
  }

  public void setPlay(int play) {
    this.play = play;
  }

  public Date getPlayDate() {
    return playDate;
  }

  public void setPlayDate(Date playDate) {
    this.playDate = playDate;
  }

  public int getPotty() {
    return potty;
  }

  public void setPotty(int potty) {
    this.potty = potty;
  }

  public Date getPottyDate() {
    return pottyDate;
  }

  public void setPottyDate(Date pottyDate) {
    this.pottyDate = pottyDate;
  }

  public int getSleep() {
    return sleep;
  }

  public void setSleep(int sleep) {
    this.sleep = sleep;
  }

  public Date getSleepDate() {
    return sleepDate;
  }

  public void setSleepDate(Date sleepDate) {
    this.sleepDate = sleepDate;
  }
}
