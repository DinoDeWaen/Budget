import model.Category;

import org.junit.Before;
import org.junit.Test;

import dto.CategoryDTO;
import services.AddCategoryService;
import services.CategoryServices;
import util.BudgetDataBase;
import static org.junit.Assert.assertEquals;

/**
 * Created by dino on 11/11/14.
 */
public class AddCategoryTest {
    private CategoryServices categoryTransactions;
    private CategoryDTO categoryDTO;

    private static final String CategoryName = "CategoryName";

    @Before
    public void setUp() throws Exception {
        categoryTransactions = new AddCategoryService();
    }

    @Test
    public void testCategoryBuilderAndFactory() {
        Integer id = addCategory();

        Category category = loadCategory(id);

        validateCategory(category);
    }

    private void validateCategory(Category category) {
        assertEquals(CategoryName, category.getName());
    }

    private Category loadCategory(Integer id) {
        return BudgetDataBase.budgetDataBase.getCategory(id);
    }

    private Integer  addCategory() {
        categoryDTO = buildCategoryDTO();
        return categoryTransactions.addCategory(categoryDTO);
    }

    private CategoryDTO buildCategoryDTO() {
        return CategoryDTO.newBuilder()
                .withCategoryName(CategoryName)
                .build();
    }

}
