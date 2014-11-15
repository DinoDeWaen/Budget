/**
 * Created by dino on 11/11/14.
 */
public class AddCategory implements CategoryTransactions {
    @Override
    public void add(CategoryDTO categoryDTO) {
        final Category category = buildCategory(categoryDTO);
        BudgetDataBase.budgetDataBase.addCategory(category);
    }

    private Category buildCategory(CategoryDTO categoryDTO) {
        return Category.newBuilder()
               .withCategoryId(categoryDTO.getCategoryId())
               .withCategoryName(categoryDTO.getCategoryName())
               .build();
    }
}
