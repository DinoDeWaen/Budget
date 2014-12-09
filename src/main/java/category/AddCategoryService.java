package category;
import util.BudgetDataBase;

/**
 * Created by dino on 11/11/14.
 */
public class AddCategoryService implements CategoryServices {
    @Override
    public Integer addCategory(CategoryDTO categoryDTO) {
        final Category category = buildCategory(categoryDTO);
        return BudgetDataBase.budgetDataBase.addCategory(category);
    }

    private Category buildCategory(CategoryDTO categoryDTO) {
        return Category.newBuilder()
               .withCategoryName(categoryDTO.name)
               .build();
    }
}
