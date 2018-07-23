package cnm.edu.deepdive.emojipetsservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Component;

// @JsonInclude(Include.NON_NULL)
// @JsonIgnoreProperties(value = {"id", "created", "absences"}, allowGetters = true, ignoreUnknown = true)
@Component
@Entity
public class Player {

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
  @Column(name = "student_id", nullable = false, updatable = false)
  private long id;

  @Column(name = "display_name", nullable = false, length = 100)
  private String display_name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "player", cascade = CascadeType.ALL)
  @OrderBy("start DESC")
  private List<Pet> pets = new LinkedList<>();

  // TODO add friendship Friendship
//
//  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//  @OrderBy("start DESC")
//  @JoinTable(name = "friendship",
//      joinColumns = { @JoinColumn(name = "player_id") },
//      inverseJoinColumns = { @JoinColumn(name = "player_id") })
//  private Player player1;





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

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }
}
