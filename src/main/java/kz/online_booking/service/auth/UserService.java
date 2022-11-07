package kz.online_booking.service.auth;

import java.util.List;
import kz.online_booking.model.auth.Role;
import kz.online_booking.model.auth.User;

public interface UserService {

  User saveUser(User user);

  Role saveRole(Role role);

  void addRoleToUser(String username, String roleName);

  User getUser(String username);

  List<User> getUsers();

}
