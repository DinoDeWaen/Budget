package category;

import java.util.List;

import category.CategoryDTO;

/**
 * Created by dino on 13/11/14.
 */
public interface CategoryServices {
    public Integer addCategory(CategoryDTO categoryDTO);
    public List<CategoryDTO> getCategories ();
}
