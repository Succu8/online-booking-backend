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

    @GetMapping("/getById")
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.getRestaurantById(id);
    }

    @GetMapping("/getAll")
    public Restaurant[] getRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    @PostMapping("/saveRestaurant")
    public void saveRestaurant(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @DeleteMapping("/deleteById")
    public void deleteRestaurant(@RequestParam Long id) {
        restaurantRepository.deleteById(id);
    }
}
