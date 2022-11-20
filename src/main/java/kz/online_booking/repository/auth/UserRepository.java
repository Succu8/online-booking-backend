package kz.online_booking.repository.auth;

import kz.online_booking.model.auth.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Person, Long> {
  Person findByUsername(String username);
}
