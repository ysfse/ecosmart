package ecosmart.controllers.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecosmart.entities.Category;
import ecosmart.services.category.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/all")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}

}
