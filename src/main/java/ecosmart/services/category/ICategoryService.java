package ecosmart.services.category;


import java.io.Serializable;
import java.util.List;

import ecosmart.entities.Category;

public interface ICategoryService extends Serializable {
     public List<Category> getAllCategories();
}