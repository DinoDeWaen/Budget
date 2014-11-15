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

    private static final Integer id = 1;
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
        assertEquals(id, category.getId());
        assertEquals(CategoryName, category.getCategoryName());
    }

    private Category loadCategory() {
        return BudgetDataBase.budgetDataBase.getCategory(id);
    }

    private void addCategory() {
        categoryDTO = buildCategoryDTO();
        categoryTransactions.add(categoryDTO);
    }

    private CategoryDTO buildCategoryDTO() {
        return  CategoryDTO.newBuilder()
                 .withCategoryId(id)
                 .withCategoryName(CategoryName)
                 .build();
    }

}