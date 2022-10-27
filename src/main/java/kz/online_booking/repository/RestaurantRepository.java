package kz.online_booking.repository;

import kz.online_booking.dao.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant getRestaurantById(Long id);

    Restaurant[] getAllRestaurants();
}
