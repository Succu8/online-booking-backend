package kz.online_booking.controller;

import kz.online_booking.dao.Client;
import kz.online_booking.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  private final TestRepository testRepository;

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/get")
  public Client getClient(@RequestParam("id") long id) {
    return testRepository.getClientById(id);
  }

  @PostMapping("/post")
  public void save(@RequestParam("name") String name) {
    Client client = new Client(name);
    testRepository.save(client);
  }
}
