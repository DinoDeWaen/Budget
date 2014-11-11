import de.bechte.junit.runners.context.HierarchicalContextRunner;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by dino on 11/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddCategoryRequestTest {
    private static UseCaseFactory useCaseFactory;
    private Request request;

    private static final Integer id = 0;
    private static final String CategoryName = "CategoryName";

    @Before
    public void setUp() throws Exception {
        useCaseFactory = new AddCategoryFactory();
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
        UseCase AddCategory = useCaseFactory.createUseCase();
        request = buildAddCategoryBuilder();
        AddCategory.execute(request);
    }

    private AddCategoryRequest buildAddCategoryBuilder() {
        return  AddCategoryRequest.newBuilder()
                 .withCategoryId(id)
                 .withCategoryName(CategoryName)
                 .build();
    }

}
