package kz.online_booking.controller.auth;

import java.net.URI;
import java.util.List;
import kz.online_booking.model.auth.Role;
import kz.online_booking.model.auth.User;
import kz.online_booking.service.auth.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping("/user/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri = URI.create(ServletUriComponentsBuilder
                           .fromCurrentContextPath().path("/api/user/save")
                           .toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder
                           .fromCurrentContextPath().path("/api/role/save")
                           .toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PostMapping("/role/addToUser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserFrom from) {
    userService.addRoleToUser(from.getUsername(), from.getRoleName());
    return ResponseEntity.ok().build();
  }

}

@Data
class RoleToUserFrom {
  private String username;
  private String roleName;
}
