package ecosmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecosmart.entities.CommandLine;

public interface CommandLineRepository extends JpaRepository<CommandLine,Long> {
	

}
