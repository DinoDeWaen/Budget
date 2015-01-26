package category;
import java.util.ArrayList;
import java.util.List;

import gateway.BudgetDataBase;

/**
 * Created by dino on 11/11/14.
 */
public class CategoryServiceImpl implements CategoryServices {
    @Override
    public Integer addCategory(CategoryDTO categoryDTO) {
        final Category category = buildCategory(categoryDTO);
        return BudgetDataBase.budgetDataBase.addCategory(category);
    }

    private Category buildCategory(CategoryDTO categoryDTO) {
        return Category.newBuilder()
               .withCategoryName(categoryDTO.getName())
               .build();
    }

	@Override
	public List<CategoryDTO> getCategories() {
		final List<CategoryDTO> catDTOList = new ArrayList <CategoryDTO> ();
		List<Category> cats = BudgetDataBase.budgetDataBase.getCategories();
		for (Category c:cats){
			final CategoryDTO catDTO = CategoryDTO.newBuilder().withCategoryName(c.getName()).build();
			catDTOList.add(catDTO);			
		}
		return catDTOList;
	}
}
