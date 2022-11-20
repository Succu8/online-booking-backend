package kz.online_booking.controller;

import kz.online_booking.dao.Restaurant;
import kz.online_booking.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping("/get")
    public Restaurant getRestaurantById(@RequestParam("id") Long id) {
        return restaurantRepository.getRestaurantById(id);
    }

    @PostMapping("/save")
    public void saveRestaurant(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @DeleteMapping("/delete")
    public void deleteRestaurant(@RequestParam("id") Long id) {
        restaurantRepository.deleteById(id);
    }
}
