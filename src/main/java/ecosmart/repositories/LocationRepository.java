package ecosmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecosmart.entities.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
