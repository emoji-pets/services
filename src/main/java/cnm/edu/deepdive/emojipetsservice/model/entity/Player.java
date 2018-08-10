package cnm.edu.deepdive.emojipetsservice.model.entity;

import cnm.edu.deepdive.emojipetsservice.view.Loner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

/**
 * This is the Player class and it is the Entity class.
 */
@Component
@Entity
@JsonIgnoreProperties(value = {"followers", "following", "href"}, allowGetters = true)
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

  @Column(name = "oauthId", nullable = false, length = 400)
  private String oauthId;

  @Column(name = "display_name", nullable = false, length = 100)
  private String display_name;

  @Column(name = "status", nullable = false, length = 550)
  private String status = "";

  @Column(name = "wall", nullable = false, length = 5000)
  private String wall = "";

  @JsonSerialize(contentAs = Loner.class)
  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
      CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}) // cascade = ?
  @JoinTable(name = "followers", joinColumns = {@JoinColumn(name = "player2_id")},
      inverseJoinColumns = {@JoinColumn(name = "player1_id")})
  private Set<Player> followers = new HashSet<>();

  @JsonSerialize(contentAs = Loner.class)
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "followers", cascade = {CascadeType.REFRESH,
      CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
  private Set<Player> following = new HashSet<>();

  @Column(name = "pet_name", length = 100)
  private String pet_name;

  @Column(name = "pet_emoji", length = 20)
  private String pet_emoji;

  // TODO set initial value
  @Column(name = "level", nullable = false, columnDefinition = "int default 1")
  private int level = 1;

  @Column(name = "xp", nullable = false, columnDefinition = "int default 1")
  private int xp = 0;

  @Column(name = "maxXp", nullable = false, columnDefinition = "int default 1")
  private int maxXp = 0;

  @Column(nullable = false)
  private Long timeStamp = new Date().getTime();

  @Column(name = "courage_points", nullable = false)
  private long couragePoints = 0;
  @Column(name = "courage_points_max", nullable = false)
  private int couragePointsMax = 100;

  @Column(name = "mana_points", nullable = false)
  private long manaPoints = 0;
  @Column(name = "mana_points_max", nullable = false)
  private int manaPointsMax = 100;

  @Column(name = "health_points", nullable = false)
  private long healthPoints = 0;
  @Column(name = "health_points_max", nullable = false)
  private int healthPointsMax = 100;

  @Column(name = "power_points", nullable = false)
  private long powerPoints = 0;
  @Column(name = "power_points_max", nullable = false)
  private int powerPointsMax = 100;

  /**
   *
   * @return This is an accessor method for the id variable.
   * This method returns the value of this private member variable.
   */
  public long getId() {
    return id;
  }

  /**
   *
   * @param id This is a  mutator method for the id variable.
   * This method is used to control changes to this variable.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   *
   * @return This is an accessor method for the display_name variable.
   * This method returns the value of this private member variable.
   */
  public String getDisplay_name() {
    return display_name;
  }

  /**
   *
   * @return This is an accessor method for the status variable.
   * This method returns the value of this private member variable.
   */
  public String getStatus() {
    return status;
  }

  /**
   *
   * @param status This is a  mutator method for the status variable.
   * This method is used to control changes to this variable.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   *
   * @return This is an accessor method for the pet_emoji variable.
   * This method returns the value of this private member variable.
   */
  public String getPet_emoji() {
    return pet_emoji;
  }

  /**
   *
   * @param pet_emoji This is a  mutator method for the pet_emoji variable.
   * This method is used to control changes to this variable.
   */
  public void setPet_emoji(String pet_emoji) {
    this.pet_emoji = pet_emoji;
  }

  /**
   *
   * @param display_name This is a  mutator method for the display_name variable.
   * This method is used to control changes to this variable.
   */
  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  /**
   *
   * @return This is an accessor method for the wall variable.
   * This method returns the value of this private member variable.
   */
  public String getWall() {
    return wall;
  }

  /**
   *
   * @param wall This is a  mutator method for the wall variable.
   * This method is used to control changes to this variable.
   */
  public void setWall(String wall) {
    this.wall = wall;
  }

  /**
   *
   * @return This is an accessor method for the couragePoints variable.
   * This method returns the value of this private member variable.
   */
  public long getCouragePoints() {
    return couragePoints;
  }

  /**
   *
   * @param couragePoints This is a  mutator method for the couragePoints variable.
   * This method is used to control changes to this variable.
   */
  public void setCouragePoints(long couragePoints) {
    this.couragePoints = couragePoints;
  }

  /**
   *
   * @return This is an accessor method for the couragePointsMax variable.
   * This method returns the value of this private member variable.
   */
  public int getCouragePointsMax() {
    return couragePointsMax;
  }

  /**
   *
   * @param couragePointsMax This is a  mutator method for the couragePointsMax variable.
   * This method is used to control changes to this variable.
   */
  public void setCouragePointsMax(int couragePointsMax) {
    this.couragePointsMax = couragePointsMax;
  }

  /**
   *
   * @return This is an accessor method for the manaPoints variable.
   * This method returns the value of this private member variable.
   */
  public long getManaPoints() {
    return manaPoints;
  }

  /**
   *
   * @param manaPoints This is a  mutator method for the manaPoints variable.
   * This method is used to control changes to this variable.
   */
  public void setManaPoints(long manaPoints) {
    this.manaPoints = manaPoints;
  }

  /**
   *
   * @return This is an accessor method for the manaPointsMax variable.
   * This method returns the value of this private member variable.
   */
  public int getManaPointsMax() {
    return manaPointsMax;
  }

  /**
   *
   * @param manaPointsMax This is a  mutator method for the manaPointsMax variable.
   * This method is used to control changes to this variable.
   */
  public void setManaPointsMax(int manaPointsMax) {
    this.manaPointsMax = manaPointsMax;
  }

  /**
   *
   * @return This is an accessor method for the healthPoints variable.
   * This method returns the value of this private member variable.
   */
  public long getHealthPoints() {
    return healthPoints;
  }

  /**
   *
   * @param healthPoints This is a  mutator method for the healthPoints variable.
   * This method is used to control changes to this variable.
   */
  public void setHealthPoints(long healthPoints) {
    this.healthPoints = healthPoints;
  }

  /**
   *
   * @return This is an accessor method for the healthPointsMax variable.
   * This method returns the value of this private member variable.
   */
  public int getHealthPointsMax() {
    return healthPointsMax;
  }

  /**
   *
   * @param healthPointsMax This is a  mutator method for the healthPointsMax variable.
   * This method is used to control changes to this variable.
   */
  public void setHealthPointsMax(int healthPointsMax) {
    this.healthPointsMax = healthPointsMax;
  }

  /**
   *
   * @return This is an accessor method for the powerPoints variable.
   * This method returns the value of this private member variable.
   */
  public long getPowerPoints() {
    return powerPoints;
  }

  /**
   *
   * @param powerPoints This is a  mutator method for the powerPoints variable.
   * This method is used to control changes to this variable.
   */
  public void setPowerPoints(long powerPoints) {
    this.powerPoints = powerPoints;
  }

  /**
   *
   * @return This is an accessor method for the powerPointsMax variable.
   * This method returns the value of this private member variable.
   */
  public int getPowerPointsMax() {
    return powerPointsMax;
  }

  /**
   *
   * @param powerPointsMax This is a  mutator method for the powerPointsMax variable.
   * This method is used to control changes to this variable.
   */
  public void setPowerPointsMax(int powerPointsMax) {
    this.powerPointsMax = powerPointsMax;
  }

  /**
   *
   * @return This is an accessor method for the level variable.
   * This method returns the value of this private member variable.
   */
  public int getLevel() {
    return level;
  }

  /**
   *
   * @param level This is a  mutator method for the level variable.
   * This method is used to control changes to this variable.
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   *
   * @return This is an accessor method for the pet_name variable.
   * This method returns the value of this private member variable.
   */
  public String getPet_name() {
    return pet_name;
  }

  /**
   *
   * @param pet_name This is a  mutator method for the pet_name variable.
   * This method is used to control changes to this variable.
   */
  public void setPet_name(String pet_name) {
    this.pet_name = pet_name;
  }

  /**
   *
   * @return This is an accessor method for the followers variable.
   * This method returns the value of this private member variable.
   */
  public Set<Player> getFollowers() {
    return followers;
  }

  /**
   *
   * @param followers This is a  mutator method for the followers variable.
   * This method is used to control changes to this variable.
   */
  public void setFollowers(Set<Player> followers) {
    this.followers = followers;
  }

  /**
   *
   * @return This is an accessor method for the following variable.
   * This method returns the value of this private member variable.
   */
  public Set<Player> getFollowing() {
    return following;
  }

  /**
   *
   * @param following This is a  mutator method for the following variable.
   * This method is used to control changes to this variable.
   */
  public void setFollowing(Set<Player> following) {
    this.following = following;
  }

  /**
   *
   * @return This is an accessor method for the maxXp variable.
   * This method returns the value of this private member variable.
   */
  public int getMaxXp() {
    return maxXp;
  }

  /**
   *
   * @param maxXp This is a  mutator method for the maxXp variable.
   *    * This method is used to control changes to this variable.
   */
  public void setMaxXp(int maxXp) {
    this.maxXp = maxXp;
  }

  /**
   *
   * @return This is an accessor method for the xp variable.
   * This method returns the value of this private member variable.
   */
  public int getXp() {
    return xp;
  }

  /**
   *
   * @param xp This is a  mutator method for the xp variable.
   * This method is used to control changes to this variable.
   */
  public void setXp(int xp) {
    this.xp = xp;
  }

  /**
   *
   * @return This is an accessor method for the timeStamp variable.
   * This method returns the value of this private member variable.
   */
  public Long getTimeStamp() {
    return timeStamp;
  }

  /**
   *
   * @param timeStamp This is a  mutator method for the timeStamp variable.
   * This method is used to control changes to this variable.
   */
  public void setTimeStamp(Long timeStamp) {
    this.timeStamp = timeStamp;
  }

  /**
   *
   * @return This is an accessor method.
   * This method returns the value of this private member variable.
   */
  public URI getHref() {
    return entityLinks.linkForSingleResource(Player.class, id).toUri();
  }

  /**
   *
   * @return This is an accessor method for the oauthId variable.
   * This method returns the value of this private member variable.
   */
  public String getOauthId() {
    return oauthId;
  }

  /**
   *
   * @param oauthId This is a  mutator method for the oauthId variable.
   * This method is used to control changes to this variable.
   */
  public void setOauthId(String oauthId) {
    this.oauthId = oauthId;
  }
}
