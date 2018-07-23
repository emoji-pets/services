package cnm.edu.deepdive.emojipetsservice.model.entity;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

//@JsonInclude(Include.NON_NULL)
//@JsonIgnoreProperties(value = {"id", "created", "absences"}, allowGetters = true, ignoreUnknown = true)
@Component
@Entity
public class Love {

  private static EntityLinks entityLinks;

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Love.entityLinks = entityLinks;
  }

  @Id // makes primary key
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "love_id", nullable = false, updatable = false)
  private long id;

  @OneToOne(fetch = FetchType.EAGER, mappedBy = "pet", cascade = CascadeType.ALL)
  @JoinColumn(name = "pet_id", nullable = false)
  private Pet pet;

  @Column(name = "last_pet", nullable = false)
  private Date lastPet;

  @Column(name = "hp", nullable = false)
  private int hp;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  public Date getLastPet() {
    return lastPet;
  }

  public void setLastPet(Date lastPet) {
    this.lastPet = lastPet;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }
}
