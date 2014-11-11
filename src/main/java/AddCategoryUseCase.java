/**
 * Created by dino on 11/11/14.
 */
public class AddCategoryUseCase implements UseCase {
    @Override
    public void execute(Request request) {
        AddCategoryRequest addCategoryRequest = (AddCategoryRequest) request;
        final Category category = buildCategory(addCategoryRequest);
        BudgetDataBase.budgetDataBase.addCategory(category);
    }

    private Category buildCategory(AddCategoryRequest addCategoryRequest) {
        return Category.newBuilder()
               .withCategoryId(addCategoryRequest.getCategoryId())
               .withCategoryName(addCategoryRequest.getCategoryName())
               .build();
    }
}
