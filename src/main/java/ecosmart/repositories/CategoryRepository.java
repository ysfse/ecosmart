package ecosmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecosmart.entities.Category;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
       
		List<Category> findAll();
}
