/**
 * Created by dino on 11/11/14.
 */
public class AddCategory implements CategoryTransactions {
    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        final Category category = buildCategory(categoryDTO);
        BudgetDataBase.budgetDataBase.addCategory(category);
    }

    private Category buildCategory(CategoryDTO categoryDTO) {
        return Category.newBuilder()
               .withCategoryName(categoryDTO.name)
               .build();
    }
}
