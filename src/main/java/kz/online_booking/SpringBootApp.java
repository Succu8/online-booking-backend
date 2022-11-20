package kz.online_booking;

import java.util.ArrayList;
import kz.online_booking.model.auth.Person;
import kz.online_booking.model.auth.Role;
import kz.online_booking.model.auth.role.RoleName;
import kz.online_booking.service.auth.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApp {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootApp.class, args);
  }

//  @Bean
//  CommandLineRunner run(PersonService personService) {
//    return args -> {
//
//      personService.saveRole(new Role(null, RoleName.ROLE_USER.name()));
//      personService.saveRole(new Role(null, RoleName.ROLE_ADMIN.name()));
//      personService.saveRole(new Role(null, RoleName.ROLE_SUPER_ADMIN.name()));
//
//      personService.saveUser(new Person(null, "Sayan",
//                                      "Baikeyev", "1234",
//                                      new ArrayList<>()));
//      personService.saveUser(new Person(null, "Arman",
//                                    "Tursynbek", "1234",
//                                    new ArrayList<>()));
//      personService.saveUser(new Person(null, "Daurbek",
//                                    "LastName", "1234",
//                                    new ArrayList<>()));
//
//      personService.addRoleToUser("Baikeyev", RoleName.ROLE_SUPER_ADMIN.name());
//      personService.addRoleToUser("Turynbek", RoleName.ROLE_ADMIN.name());
//      personService.addRoleToUser("LastName", RoleName.ROLE_USER.name());
//    };
//  }
}

