package cnm.edu.deepdive.emojipetsservice.model.entity;

import cnm.edu.deepdive.emojipetsservice.view.Loner;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Player implements Loner {

  public static final String PRIMARY_KEY_COLUMN = "player_id";
  private static EntityLinks entityLinks;

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Player.entityLinks = entityLinks;
  }

  @Id // makes primary key
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = PRIMARY_KEY_COLUMN, nullable = false, updatable = false)
  private long id;

  @Column(name = "display_name", nullable = false, length = 100)
  private String display_name;

  @JsonSerialize(contentAs = Loner.class)
  @ManyToMany(fetch = FetchType.LAZY) // cascade = ?
  @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "player2_id")},
      inverseJoinColumns = {@JoinColumn(name = "player1_id")})
  private List<Player> followers;

  @JsonSerialize(contentAs = Loner.class)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "following", joinColumns = {@JoinColumn(name = "player1_id")},
      inverseJoinColumns = {@JoinColumn(name = "player2_id")})
  private List<Player> following;

  @Column(name = "pet_name", length = 100)
  private String pet_name;

  // TODO set initial value
  @Column(name = "level", nullable = false, columnDefinition = "int default 1")
  private int level = 1;

  @Column(name = "xp", nullable = false, columnDefinition = "int default 1")
  private int xp = 0;

  @Column(nullable = false)
  private Long updated = new Date().getTime();

  @Column(name = "courage_points", nullable = false, columnDefinition = "int default 0")
  private int couragePoints = 0;
  @Column(name = "courage_points_max", nullable = false, columnDefinition = "int default 100")
  private int couragePointsMax = 100;

  @Column(name = "mana_points", nullable = false, columnDefinition = "int default 0")
  private int manaPoints = 0;
  @Column(name = "mana_points_max", nullable = false, columnDefinition = "int default 100")
  private int manaPointsMax = 100;;

  @Column(name = "health_points", nullable = false, columnDefinition = "int default 0")
  private int healthPoints = 0;
  @Column(name = "health_points_max", nullable = false, columnDefinition = "int default 100")
  private int healthPointsMax = 100;;

  @Column(name = "power_points", nullable = false, columnDefinition = "int default 0")
  private int powerPoints = 0;
  @Column(name = "power_points_max", nullable = false, columnDefinition = "int default 100")
  private int powerPointsMax = 100;;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  public int getCouragePoints() {
    return couragePoints;
  }

  public void setCouragePoints(int couragePoints) {
    this.couragePoints = couragePoints;
  }

  public int getCouragePointsMax() {
    return couragePointsMax;
  }

  public void setCouragePointsMax(int couragePointsMax) {
    this.couragePointsMax = couragePointsMax;
  }

  public int getManaPoints() {
    return manaPoints;
  }

  public void setManaPoints(int manaPoints) {
    this.manaPoints = manaPoints;
  }

  public int getManaPointsMax() {
    return manaPointsMax;
  }

  public void setManaPointsMax(int manaPointsMax) {
    this.manaPointsMax = manaPointsMax;
  }

  public int getHealthPoints() {
    return healthPoints;
  }

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  public int getHealthPointsMax() {
    return healthPointsMax;
  }

  public void setHealthPointsMax(int healthPointsMax) {
    this.healthPointsMax = healthPointsMax;
  }

  public int getPowerPoints() {
    return powerPoints;
  }

  public void setPowerPoints(int powerPoints) {
    this.powerPoints = powerPoints;
  }

  public int getPowerPointsMax() {
    return powerPointsMax;
  }

  public void setPowerPointsMax(int powerPointsMax) {
    this.powerPointsMax = powerPointsMax;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getPet_name() {
    return pet_name;
  }

  public void setPet_name(String pet_name) {
    this.pet_name = pet_name;
  }

  public List<Player> getFollowers() {
    return followers;
  }

  public void setFollowers(List<Player> followers) {
    this.followers = followers;
  }

  public List<Player> getFollowing() {
    return following;
  }

  public void setFollowing(List<Player> following) {
    this.following = following;
  }

  public int getXp() {
    return xp;
  }

  public void setXp(int xp) {
    this.xp = xp;
  }

  public Long getUpdated() {
    return updated;
  }

  public void setUpdated(Long updated) {
    this.updated = updated;
  }

  public URI getHref() {
    return entityLinks.linkForSingleResource(Player.class, id).toUri();
  }
}
