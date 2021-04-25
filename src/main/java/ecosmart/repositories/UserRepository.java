package ecosmart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ecosmart.entities.User;

public interface UserRepository extends JpaRepository<User,Long>  {
	    Optional<User> findByUsername(String username);
	    Optional<User> findById(Long id);
	    Boolean existsByUsername(String username);
	    Boolean existsByEmail(String email);
}
