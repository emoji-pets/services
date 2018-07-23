package cnm.edu.deepdive.emojipetsservice.model.entity;

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
import javax.persistence.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pet_id", nullable = false, updatable = false)
  private long id;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("start DESC")
  @JoinTable(name = "post_tags",
      joinColumns = { @JoinColumn(name = "player_id") },
      inverseJoinColumns = { @JoinColumn(name = "player_id") })
  private Player player;


}
