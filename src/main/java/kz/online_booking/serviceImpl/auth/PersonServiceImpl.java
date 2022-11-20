package kz.online_booking.serviceImpl.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import kz.online_booking.model.auth.Person;
import kz.online_booking.model.auth.Role;
import kz.online_booking.repository.auth.PersonRepository;
import kz.online_booking.repository.auth.RoleRepository;
import kz.online_booking.service.auth.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService, UserDetailsService {

  private final PersonRepository personRepository;
  private final RoleRepository   roleRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username);

    if (person == null) {
      log.error("User not found in the database");
      throw new UsernameNotFoundException("User not found in the database");
    } else {
      log.info("User found in the database: {}", username);
    }

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    person.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return new org.springframework.security.core.userdetails
      .User(person.getUsername(), person.getPassword(), authorities);
  }

  @Override
  public Person saveUser(Person person) {
    log.info("Saving new user {} to the database", person.getName());
    person.setPassword(passwordEncoder.encode(person.getPassword()));
    return personRepository.save(person);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role {} to the database", role.getName());
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role {} to user {}", roleName, username);
    Person person = personRepository.findByUsername(username);
    Role   role   = roleRepository.findByName(roleName);
    person.getRoles().add(role);
  }

  @Override
  public Person getUser(String username) {
    log.info("Fetching user {}", username);
    return personRepository.findByUsername(username);
  }

  @Override
  public List<Person> getUsers() {
    log.info("Fetching all users");
    return personRepository.findAll();
  }
}
