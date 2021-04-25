package ecosmart.services.category;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecosmart.entities.Category;
import ecosmart.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService{

	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepo.findAll();
	}

}
