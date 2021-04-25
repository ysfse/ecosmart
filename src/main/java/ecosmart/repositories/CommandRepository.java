package ecosmart.repositories;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ecosmart.entities.Command;
import ecosmart.entities.User;

public interface CommandRepository extends JpaRepository<Command,Long>{

	 List<Command> findByCommandDate(Date date);
	// List<Command> findByUserId(Long id);	
	 List<Command> findByCommandDateEqualsAndUser(Date date,User user);
	 List<Command> findByUser(User user);
	
}
