package ecosmart.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecosmart.entities.Carpool;
import ecosmart.entities.Product;
import ecosmart.entities.User;

public interface CarpoolRepository extends JpaRepository<Carpool,Long> {
	
	
    List<Carpool> findByStateTrue();
	
	List<Carpool> findByTotalPlacesGreaterThan(int places); // to get only carpools that still have available places (greater or equals to 1)
	
	List<Carpool> findByDriver(User driver);
	List<Carpool> findByDepartureTimeBetween(LocalDateTime afterDateTime, LocalDateTime beforeDateTime);    //Ex : find carpools that will happen in 15 min or more but we can fix if we want in the same day or in the next two days or so
	
}
