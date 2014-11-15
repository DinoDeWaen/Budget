import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by dino on 11/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddCategoryTest {
    private CategoryTransactions categoryTransactions;
    private CategoryDTO categoryDTO;

    private static final String CategoryName = "CategoryName";

    @Before
    public void setUp() throws Exception {
        categoryTransactions = new AddCategory();
    }

    @Test
    public void TestCategoryBuilderAndFactory(){
        addCategory();

        Category category = loadCategory();

        validateCategory(category);
    }

    private void validateCategory(Category category) {
        assertEquals(CategoryName, category.getName());
    }

    private Category loadCategory() {
        return BudgetDataBase.budgetDataBase.getCategory(CategoryName);
    }

    private void addCategory() {
        categoryDTO = buildCategoryDTO();
        categoryTransactions.addCategory(categoryDTO);
    }

    private CategoryDTO buildCategoryDTO() {
        return  CategoryDTO.newBuilder()
                 .withCategoryName(CategoryName)
                 .build();
    }

}
