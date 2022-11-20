package kz.online_booking.controller.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.online_booking.model.auth.Role;
import kz.online_booking.model.auth.Person;
import kz.online_booking.service.auth.PersonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService userService;

  @GetMapping("/users")
  public ResponseEntity<List<Person>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping("/user/save")
  public ResponseEntity<Person> saveUser(@RequestBody Person person) {
    URI uri = URI.create(ServletUriComponentsBuilder
                           .fromCurrentContextPath().path("/api/user/save")
                           .toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(person));
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

  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

    var authorizationHeader = request.getHeader(AUTHORIZATION);

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        var         refresh_token = authorizationHeader.substring("Bearer ".length());
        Algorithm   algorithm     = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier      = JWT.require(algorithm).build();
        DecodedJWT  decodedJWT    = verifier.verify(refresh_token);
        var    username = decodedJWT.getSubject();
        Person person   = userService.getUser(username);

        String access_token = JWT.create()
                                 .withSubject(person.getUsername())
                                 .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                                 .withIssuer(request.getRequestURL().toString())
                                 .withClaim("roles", person.getRoles()
                                                           .stream().map(Role::getName)
                                                           .collect(Collectors.toList()))
                                 .sign(algorithm);


        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);

      } catch (Exception e) {
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("Refresh token is missing");
    }
  }

}

@Data
class RoleToUserFrom {
  private String username;
  private String roleName;
}
