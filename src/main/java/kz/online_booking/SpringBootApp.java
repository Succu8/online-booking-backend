package kz.online_booking;

import java.util.ArrayList;
import kz.online_booking.model.auth.Role;
import kz.online_booking.model.auth.User;
import kz.online_booking.model.auth.role.RoleName;
import kz.online_booking.service.auth.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApp {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootApp.class, args);
  }

  @Bean
  CommandLineRunner run(UserService userService) {
    return args -> {

      userService.saveRole(new Role(null, RoleName.ROLE_USER.name()));
      userService.saveRole(new Role(null, RoleName.ROLE_ADMIN.name()));
      userService.saveRole(new Role(null, RoleName.ROLE_SUPER_ADMIN.name()));

      userService.saveUser(new User(null, "Sayan",
                                    "Baikeyev", "1234",
                                    new ArrayList<>()));
      userService.saveUser(new User(null, "Arman",
                                    "Tursynbek", "1234",
                                    new ArrayList<>()));
      userService.saveUser(new User(null, "Daurbek",
                                    "LastName", "1234",
                                    new ArrayList<>()));

      userService.addRoleToUser("Baikeyev", RoleName.ROLE_SUPER_ADMIN.name());
      userService.addRoleToUser("Turynbek", RoleName.ROLE_ADMIN.name());
      userService.addRoleToUser("LastName", RoleName.ROLE_USER.name());
    };
  }
}

