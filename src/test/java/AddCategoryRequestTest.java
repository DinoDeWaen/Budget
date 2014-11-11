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

    private static final int id = 0;
    private static final String CategoryName = "CategoryName";

    @Before
    public void setUp() throws Exception {
        useCaseFactory = new AddCategoryFactory();
    }

    @Test
    public void TestCategoryBuilderAndFactory(){
        UseCase AddCategory = useCaseFactory.createUseCase();
        assertThat(AddCategory, instanceOf(AddCategoryUseCase.class));

        request = buildAddCategoryBuilder();
        assertThat(request, instanceOf(AddCategoryRequest.class));
    }

    private AddCategoryRequest buildAddCategoryBuilder() {
        return new AddCategoryRequest.Builder()
                      .withCategoryId(id)
                      .withCategoryName(CategoryName)
                      .build();
    }

}
