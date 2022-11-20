package kz.online_booking.service.auth;

import java.util.List;
import kz.online_booking.model.auth.Role;
import kz.online_booking.model.auth.Person;

public interface PersonService {

  Person saveUser(Person person);

  Role saveRole(Role role);

  void addRoleToUser(String username, String roleName);

  Person getUser(String username);

  List<Person> getUsers();

}
