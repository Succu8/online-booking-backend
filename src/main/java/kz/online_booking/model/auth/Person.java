package kz.online_booking.model.auth;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long             id;
  private String           name;
  private String           username;
  private String           password;
  @ManyToMany(fetch = EAGER)
  private List<Role> roles = new ArrayList<>();
}

