package kz.online_booking.repository;

import kz.online_booking.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Client, Long> {

  Client getClientById(Long id);
}
